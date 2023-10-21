#pragma once

#ifndef RENDER_H
#define RENDER_H

#include <cstdint>
#include <algorithm>
#include <iostream>
#include <iomanip>
#include <Windows.h>
#include <imgui_impl_glfw.h>
#include <gl/GL.h>
#include <gl/GLU.h>
#include <vector>

#include "imgui.h"
#include "imgui_internal.h"


class RenderUtil {

public:

    static void onStencil(std::string_view name, float x, float y, float width, float height) {
        ImGui::PushClipRect({ x,y }, { x + width, y + height }, true);
    }

    static void uninstallStencil() {
        ImGui::PopClipRect();
    }

    static void drawRect(double x, double y, double width, double height, ImColor color, float round, ImColor lineColor = ImColor(0, 0, 0, 0), float thickness = 0) {
        ImVec2 topLeft = ImVec2(x, y);
        ImVec2 bottomRight = ImVec2(x + width, y + height);
        ImU32 colorValue = ImGui::GetColorU32((ImVec4)color);
        ImU32 lineColorValue = ImGui::GetColorU32((ImVec4)lineColor);
        drawRect(ImGui::GetWindowDrawList(), topLeft, bottomRight, colorValue, lineColorValue, round, thickness);
    }

    static void drawText(double x, double y, std::string_view text, ImColor color, ImFont* font) {
        ImVec2 position = ImVec2(x, y);
        ImU32 colorValue = ImGui::GetColorU32((ImVec4)color);
        ImGui::GetWindowDrawList()->AddText(font, font->FontSize, position, colorValue, text.data());
    }

    static void drawText(double x, double y, double splitWidth, std::string_view text, ImColor color, ImFont* font) {
        ImVec2 position = ImVec2(x, y);
        ImU32 colorValue = ImGui::GetColorU32((ImVec4)color);
        const char* textPtr = text.data();
        const char* textEnd = textPtr + text.size();
        ImGui::GetWindowDrawList()->AddText(font, font->FontSize, position, colorValue, textPtr, textEnd, splitWidth);
    }

private:
    static void drawRect(ImDrawList* drawList, ImVec2 topLeft, ImVec2 bottomRight, ImU32 color, ImU32 lineColor, float round, float thickness) {
        drawList->AddRectFilled(topLeft, bottomRight, color, round, ImDrawCornerFlags_All);
        if (thickness != 0) {
            drawList->AddRect(topLeft, bottomRight, color, round, ImDrawCornerFlags_All, thickness);
        }
    }

};


#endif
