#pragma once

#ifndef LOGGER_H
#define LOGGER_H

#include "WindowGui.h"
#include <string>
#include <vector>
#include <websocketpp/config/asio_no_tls_client.hpp>
#include <websocketpp/client.hpp>
#include <openssl/rsa.h>
#include <openssl/pem.h>
#include <openssl/err.h>
#include <openssl/bio.h>
#include <openssl/aes.h>
#include <openssl/evp.h>
#include <openssl/buffer.h>
#include <openssl/rand.h>
#include <unordered_map>

class LoggerUser {

public:
	std::string username;
	std::string password;
	std::string userid;
	std::string power;
	std::string expirationdate;
	std::string entity_token;
	
};

enum Page {
	LOGIN,
	MANAGER,
	INJECTOR
};

enum Injector_Page {
	PROCESS,
	PRGS,
	INFOMATION
};

class LoggerInstance {
private:

	using client = websocketpp::client<websocketpp::config::asio_client>;
	using message_ptr = websocketpp::config::asio_client::message_type::ptr;


	LoggerInstance() {
		currentWindowGui = nullptr;
		wClient = nullptr;
		tab = 0;
		remoteKey = "";
		processRemoteKey = "";
		this->Version = "0.0.1";
		this->Window_Height = 800;
		this->Window_Width = 1200;
	}


	~LoggerInstance() {
	}

	LoggerInstance(const LoggerInstance&) = delete;
	LoggerInstance& operator=(const LoggerInstance&) = delete;


public:

	WindowGui* currentWindowGui;
	LoggerUser currentUser;

	int Window_Width;
	int Window_Height;
	bool isConnection = false;
	bool isLogin = false;
	std::string Version;
	std::vector<std::string> Developers;
	client* wClient;
	websocketpp::connection_hdl wHdl;
	std::string remoteKey;
	std::string processRemoteKey;
	int tab;
	std::unordered_map<std::string, ImTextureID> resources;
	//std::string* base64Dll;
	std::string dllPath;

	void displayWindowGui(WindowGui* newWindowGui);

	void closeWindowGui();

	static LoggerInstance& getInstance();

	void send(std::string const& payload);

	std::string encrypt(const std::string& plaintext, const std::string& key);

	std::string decrypt(const std::string& ciphertext, const std::string& key);
};

#endif