#include "EACStatus.h"

std::vector<BYTE> loadingFormat;
std::string initFormat;
bool isLoading = false;
bool isInit = false;

void EAC::LoadingStatus::onLoading(std::vector<BYTE> loading_format)
{
	loadingFormat = loading_format;
	isLoading = true;
}

void EAC::LoadingStatus::onInit(std::string init_format)
{
	init_format.pop_back();
	initFormat = init_format;
	isInit = true;
}

std::string EAC::LoadingStatus::getInitFormat()
{
	return initFormat;
}

std::vector<BYTE> EAC::LoadingStatus::getLoadingFormat()
{
	return loadingFormat;
}

bool EAC::LoadingStatus::getIsInit()
{
	return isInit;
}

bool EAC::LoadingStatus::getIsLoading()
{
	return isLoading;
}
