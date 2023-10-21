#include "Fonts.h"
#include "InterSemiBold.h"
#include "GeologicaThin.h"
#include "GeologicaRegular.h"

ImFont* InterSemiBold18;
ImFont* InterSemiBold20;
ImFont* InterSemiBold24;
ImFont* GeologicaThin32;
ImFont* GeologicaRegular18;
ImFont* GeologicaRegular20;
ImFont* GeologicaRegular14;

void FontsLoader::InitFonts()
{
    ImGuiIO& io = ImGui::GetIO(); (void)io;

    //InterSemiBold18 = io.Fonts->AddFontFromFileTTF("H:\\Inter-SemiBold.ttf", 18.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    //GeologicaThin32 = io.Fonts->AddFontFromFileTTF("H:\\Geologica-Thin.ttf", 32.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    //GeologicaRegular18 = io.Fonts->AddFontFromFileTTF("H:\\Geologica-Regular.ttf", 18.0f, NULL, io.Fonts->GetGlyphRangesJapanese());

    GeologicaRegular14 = io.Fonts->AddFontFromMemoryTTF(GeologicaRegularTTF, sizeof(GeologicaRegularTTF), 14.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    GeologicaRegular20 = io.Fonts->AddFontFromMemoryTTF(GeologicaRegularTTF, sizeof(GeologicaRegularTTF), 20.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    InterSemiBold20 = io.Fonts->AddFontFromMemoryTTF(InterSemiBoldTTF, sizeof(InterSemiBoldTTF), 20.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    InterSemiBold18 = io.Fonts->AddFontFromMemoryTTF(InterSemiBoldTTF,sizeof(InterSemiBoldTTF), 18.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    InterSemiBold24 = io.Fonts->AddFontFromMemoryTTF(InterSemiBoldTTF, sizeof(InterSemiBoldTTF), 24.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    GeologicaThin32 = io.Fonts->AddFontFromMemoryTTF(GeologicaThinTTF, sizeof(GeologicaThinTTF), 32.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    GeologicaRegular18 = io.Fonts->AddFontFromMemoryTTF(GeologicaRegularTTF, sizeof(GeologicaRegularTTF), 18.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
}

ImFont* FontsLoader::GetInterSemiBold18()
{
    return InterSemiBold18;
}

ImFont* FontsLoader::GetGeologicaThin32()
{
    return GeologicaThin32;
}

ImFont* FontsLoader::GetGeologicaRegular20()
{
    return GeologicaRegular20;
}

ImFont* FontsLoader::GetGeologicaRegular18()
{
    return GeologicaRegular18;
}

ImFont* FontsLoader::GetGeologicaRegular14()
{
    return GeologicaRegular14;
}

ImFont* FontsLoader::GetInterSemiBold24() {
    return InterSemiBold24;
}

ImFont* FontsLoader::GetInterSemiBold20()
{
    return InterSemiBold20;
}

