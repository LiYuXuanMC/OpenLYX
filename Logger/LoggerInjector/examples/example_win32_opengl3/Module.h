#ifndef MODULE_H
#define MODULE_H

#include <string>

class Module
{

public:

	std::string PageName;
	std::string Infomation;

	Module(std::string PageName, std::string Infomation) :PageName(PageName), Infomation(Infomation) {
	}

};

#endif // !MODULE_H


