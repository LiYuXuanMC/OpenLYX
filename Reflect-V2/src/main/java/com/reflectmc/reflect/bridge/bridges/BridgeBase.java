package com.reflectmc.reflect.bridge.bridges;

import com.reflectmc.reflect.bridge.annotation.TargetMethodWrapping;

public class BridgeBase {
    @TargetMethodWrapping(exportName = "initGui")
    public void initGui(){

    }
    @TargetMethodWrapping(exportName = "drawScreen")
    public void drawScreen(int mouseX, int mouseY, float partialTicks){

    }
    @TargetMethodWrapping(exportName = "onGuiClosed")
    public void onGuiClosed() {

    }
    @TargetMethodWrapping(exportName = "updateScreen")
    public void updateScreen() {
    }
}
