#ifndef ANTUENTICATION_H
#define ANTUENTICATION_H

#include <string>
#include <random>
#include <chrono>
#include <algorithm>
#include "ByteBuf.h"
#include "md5.h"
#include "ThemidaSDK.h"
#include "jni.h"
#include "EACStatus.h"

ByteBuf step_1(std::string username, std::string serverHash, JNIEnv* jniEnv);
void step_2(ByteBuf* buffer, std::string username, JNIEnv* jniEnv);
void step_3(ByteBuf* buffer, JNIEnv* jniEnv, std::string username);
bool isPopBody(ByteBuf* buffer);
void popBody(ByteBuf* buffer);
void pushBody(ByteBuf* buffer);
std::string createDynamicToken();
void fakeCrash();

ByteBuf step_1(std::string username, std::string serverHash, JNIEnv* jniEnv) {
#ifndef _DEBUG
	VM_LION_BLACK_START;
#endif
	MD5 md5;
	md5.reset();
	ByteBuf* body = new ByteBuf();
	md5.update("Q4dd4u9O" + serverHash + "p9ysNM");
	pushBody(body);
	body->writeString("Ensemble");
	body->writeString("AntiCheat");
	if (!EAC::LoadingStatus::getIsInit()) {
		while (true)
		{
			while (true)
			{
				fakeCrash();
			}
		}
	}
	else if (!EAC::LoadingStatus::getIsLoading()) {
		while (true)
		{
			while (true)
			{
				fakeCrash();
			}
		}
	}
	body->writeLong(73874619828894766);
	body->writeString(md5.toString());
	popBody(body);
	step_2(body, username, jniEnv);
#ifndef _DEBUG
	VM_LION_BLACK_END;
#endif
	return *body;

}

void fakeCrash() {
	"Decompile error";
}

void step_2(ByteBuf* buffer, std::string username, JNIEnv* jniEnv) {
#ifndef _DEBUG
	VM_LION_BLACK_START;
#endif
	pushBody(buffer);
	buffer->writeString(username);
	buffer->writeString(EAC::LoadingStatus::getInitFormat());
	buffer->writeString("[]");
	buffer->writeInt(EAC::LoadingStatus::getIsInit() + EAC::LoadingStatus::getIsLoading());
	std::vector<BYTE> loadingFormat = EAC::LoadingStatus::getLoadingFormat();
	for (size_t i = 0; i < loadingFormat.size(); i++)
	{
		buffer->writeByte(loadingFormat[i]);
	}
	popBody(buffer);
	step_3(buffer,jniEnv, username);
#ifndef _DEBUG
	VM_LION_BLACK_END;
#endif
}

void step_3(ByteBuf* buffer, JNIEnv* jniEnv, std::string username) {
#ifndef _DEBUG
	VM_LION_BLACK_START;
#endif

	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;
	MD5 md5;
	std::string token = createDynamicToken();
	md5.reset();
	md5.update(token + username + "Cjp9uiNMQ4II4dO");
	jniEnv->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	pushBody(buffer);
	buffer->writeString(token);
	buffer->writeString("[]");
	//buffer->writeBytes(classes->buffer_.data(), classes->buffer_.size());
	buffer->writeString(md5.toString());
	popBody(buffer);
#ifndef _DEBUG
	VM_LION_BLACK_END;
#endif
}

void pushBody(ByteBuf* buffer) {
#ifndef _DEBUG
	VM_LION_BLACK_START;
#endif
	if (buffer->readableBytes() == 0 && !isPopBody(buffer)) {
		return;
	}
	for (size_t i = 0; i < 12; i++)
	{
		buffer->buffer_.pop_back();
	}
	for (size_t i = 0; i < buffer->readableBytes(); i++) {
		buffer->buffer_[i] = ((buffer->buffer_[i] ^ 0xFF) ^ 0xE9) + 0x7F;
	}
#ifndef _DEBUG
	VM_LION_BLACK_END;
#endif
}

void popBody(ByteBuf* buffer) {
#ifndef _DEBUG
	VM_LION_BLACK_START;
#endif
	if (buffer->readableBytes() == 0) {
		return;
	}
	for (size_t i = 0; i < buffer->readableBytes(); i++)
	{
		buffer->buffer_[i] = ((buffer->buffer_[i] - 0x7F) ^ 0xE9) ^ 0xFF;
	}
	buffer->buffer_.push_back('e');
	buffer->buffer_.push_back('n');
	buffer->buffer_.push_back('s');
	buffer->buffer_.push_back('e');
	buffer->buffer_.push_back('m');
	buffer->buffer_.push_back('b');
	buffer->buffer_.push_back('l');
	buffer->buffer_.push_back('e');
	buffer->buffer_.push_back('j');
	buffer->buffer_.push_back('o');
	buffer->buffer_.push_back('i');
	buffer->buffer_.push_back('n');
#ifndef _DEBUG
	VM_LION_BLACK_END;
#endif
}

bool isPopBody(ByteBuf* buffer) {
#ifndef _DEBUG
	STR_ENCRYPTW_START;
#endif
	std::string targetString = "ensemblejoin";
#ifndef _DEBUG
	STR_ENCRYPTW_END;
#endif
	if (buffer->buffer_.size() >= targetString.size() && std::equal(buffer->buffer_.end() - targetString.size(), buffer->buffer_.end(), targetString.begin())) {
		return true;
	}
	else {
		return false;
	}
}

std::vector<int> decompose(int num) {
	for (int i = 10; i <= 99; i++) {
		for (int j = 10; j <= 99; j++) {
			if (i + j == num) {
				if (i < j) {
					return { i + 2, j + 2 };
				}
				else {
					return { j + 2, i + 2 };
				}
			}
		}
	}
	return { 0, 0 };
}

std::string createDynamicToken() {
#ifndef _DEBUG
	VM_LION_BLACK_START;
#endif
	std::random_device rd;
	std::mt19937 gen(rd());
	std::uniform_int_distribution<> dis(100, 198);
	int code = dis(gen);
	int decode = EAC::LoadingStatus::getIsInit() + EAC::LoadingStatus::getIsLoading();
	auto decompose_result = decompose(code);
	decompose_result[0] -= decode;
	decompose_result[1] -= decode;
	if ((code ^ decompose_result[0]) < 100 || (code ^ decompose_result[0]) > 198) {
		return createDynamicToken();
	}
	auto time_since_epoch = std::chrono::system_clock::now().time_since_epoch();
	auto time_in_ms = std::chrono::duration_cast<std::chrono::milliseconds>(time_since_epoch).count();
	auto result = std::to_string(code) + std::to_string(decompose_result[0]) + std::to_string(decompose_result[1]) + std::to_string(code ^ decompose_result[0]) + std::to_string((time_in_ms + 100000L) ^ code);
#ifndef _DEBUG
	VM_LION_BLACK_END;
#endif
	return result;

}
#endif