#ifndef FONTS_H
#define FONTS_H

#include <imgui.h>

namespace FontsLoader {

    void InitFonts();

    ImFont* GetInterSemiBold18();
    ImFont* GetGeologicaThin32();
    ImFont* GetGeologicaRegular20();
    ImFont* GetGeologicaRegular18();
    ImFont* GetGeologicaRegular14();
    ImFont* GetInterSemiBold24();
    ImFont* GetInterSemiBold20();

};

#endif // !FONTS_H
