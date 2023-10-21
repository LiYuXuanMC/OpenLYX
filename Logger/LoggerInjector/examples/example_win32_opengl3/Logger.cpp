#include "Logger.h"
#include "ThemidaSDK.h"

#define AES_KEY_LEN 16

#define PKCS5_BLOCK_SIZE 16

void LoggerInstance::displayWindowGui(WindowGui* newWindowGui)
{
	this->currentWindowGui = newWindowGui;
}

void LoggerInstance::closeWindowGui()
{
	this->currentWindowGui = nullptr;
}

LoggerInstance& LoggerInstance::getInstance()
{
	static LoggerInstance instance;
	return instance;
}

void LoggerInstance::send(std::string const& payload)
{
    VM_LION_BLACK_START
	if (this->wClient != nullptr && isConnection) {
		this->wClient->send(this->wHdl, payload, websocketpp::frame::opcode::text);
	}
    VM_LION_BLACK_END
}

std::string pad(const std::string& input, int block_size) {
    int padding = block_size - (input.size() % block_size);
    std::string padded_input = input + std::string(padding, static_cast<char>(padding));
    return padded_input;
}

std::string unpad(const std::string& input) {
    int padding = static_cast<int>(input.back());
    std::string rets = input.substr(0, input.size() - padding);
    return rets;
}

std::string base64_encode(const std::string& input) {

    BIO* b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);

    BIO* mem = BIO_new(BIO_s_mem());
    BIO_push(b64, mem);

    BIO_write(b64, input.c_str(), static_cast<int>(input.size()));
    BIO_flush(b64);

    char* output_ptr;
    const long output_size = BIO_get_mem_data(mem, &output_ptr);

    std::string output(output_ptr, output_size);
    BIO_free_all(b64);

    return output;
}

std::string base64_decode(const std::string& input) {

    BIO* b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);

    BIO* mem = BIO_new_mem_buf(input.c_str(), static_cast<int>(input.size()));
    BIO_push(b64, mem);

    std::string output;
    output.resize(input.size());

    const int output_size = BIO_read(b64, &output[0], static_cast<int>(output.size()));
    output.resize(output_size);

    BIO_free_all(b64);

    return output;
}

std::string LoggerInstance::encrypt(const std::string& input, const std::string& key) {
    const int block_size = AES_BLOCK_SIZE;
    std::string output;
    output.resize(input.size() + block_size);

    AES_KEY aes_key;
    AES_set_encrypt_key(reinterpret_cast<const unsigned char*>(key.c_str()), key.size() * 8, &aes_key);

    std::string padded_input = pad(input, block_size);

    int num_blocks = static_cast<int>(padded_input.size() / block_size);
    for (int i = 0; i < num_blocks; ++i) {
        AES_encrypt(reinterpret_cast<const unsigned char*>(padded_input.c_str() + i * block_size),
            reinterpret_cast<unsigned char*>(output.data() + i * block_size), &aes_key);
    }

    output = output.substr(0, num_blocks * block_size);
    return base64_encode(output);
}

std::string LoggerInstance::decrypt(const std::string& input, const std::string& key) {

    const int block_size = AES_BLOCK_SIZE;
    std::string decoded_input = base64_decode(input);
    std::string output;
    output.resize(decoded_input.size());

    AES_KEY aes_key;
    AES_set_decrypt_key(reinterpret_cast<const unsigned char*>(key.c_str()), key.size() * 8, &aes_key);

    int num_blocks = static_cast<int>(decoded_input.size() / block_size);
    for (int i = 0; i < num_blocks; ++i) {
        AES_decrypt(reinterpret_cast<const unsigned char*>(decoded_input.c_str() + i * block_size),
            reinterpret_cast<unsigned char*>(output.data() + i * block_size), &aes_key);
    }

    output = unpad(output);

    return output;
}
