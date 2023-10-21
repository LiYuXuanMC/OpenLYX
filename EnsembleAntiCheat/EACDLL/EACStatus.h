#ifndef EACSTATUS_H
#define EACSTATUS_H

#include <Windows.h>
#include <string>
#include <vector>

namespace EAC {

	namespace LoadingStatus {
		void onLoading(std::vector<BYTE> init_fotmat);
		void onInit(std::string loading_format);
		std::string getInitFormat();
		std::vector<BYTE> getLoadingFormat();
		bool getIsInit();
		bool getIsLoading();
	}

}

#endif