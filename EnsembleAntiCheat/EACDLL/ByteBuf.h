#ifndef BYTEBUF_H
#define BYTEBUF_H

#include <vector>
#include <string>

class ByteBuf {
public:
    ByteBuf() {
    }

    ByteBuf(int capacity) {
        buffer_.resize(capacity);
    }

    void writeByte(char byte) {
        buffer_.push_back(byte);
    }

    char readByte() {
        char byte = buffer_.front();
        buffer_.erase(buffer_.begin());
        return byte;
    }

    void writeBytes(const char* bytes, int length) {
        buffer_.insert(buffer_.end(), bytes, bytes + length);
    }

    void readBytes(char* bytes, int length) {
        std::copy(buffer_.begin(), buffer_.begin() + length, bytes);
        buffer_.erase(buffer_.begin(), buffer_.begin() + length);
    }

    void writeInt(int value) {
        char* bytes = reinterpret_cast<char*>(&value);
        writeBytes(bytes, sizeof(int));
    }

    int readInt() {
        int value;
        readBytes(reinterpret_cast<char*>(&value), sizeof(int));
        return value;
    }

    void writeString(const std::string& str) {
        writeInt(static_cast<int>(str.length()));
        writeBytes(str.c_str(), static_cast<int>(str.length()));
    }

    void writeLong(long long value) {
        char* bytes = reinterpret_cast<char*>(&value);
        writeBytes(bytes, sizeof(long long));
    }

    long long readLong() {
        long long value;
        readBytes(reinterpret_cast<char*>(&value), sizeof(long long));
        return value;
    }

    std::string readString() {
        int length = readInt();
        std::string str;
        str.resize(length);
        readBytes(&str[0], length);
        return str;
    }

    int readableBytes() const {
        return buffer_.size();
    }

    std::vector<char> buffer_;
};

#endif // !BYTEBUF_H
