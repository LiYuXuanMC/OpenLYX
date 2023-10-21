package al.logger.client.ui.managers;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.templates.*;
import al.logger.client.ui.templates.KeyStrokes.KeyStrokesTemplate;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import by.radioegor146.nativeobfuscator.Native;

import java.util.HashMap;

@Native
public class GuiManager {

    public ScaffoldCount scaffoldCount;
    public RadarTemplate radarTemplate;
    public KeyStrokesTemplate keyStrokesTemplate;
    public TextDisplayTemplate fpsDisplay;
    public TextDisplayTemplate xyzDisplay;
    public TextDisplayTemplate headDisplay;
    public TargetHud targetHud;
    public LongJumpProgress longJumpProgress;

    public HashMap<String, ComponentBase> GuiElementMap = new HashMap<>();

    public GuiManager() {
        this.scaffoldCount = new ScaffoldCount("ScaffoldCount");
        this.radarTemplate = new RadarTemplate();
        this.keyStrokesTemplate = new KeyStrokesTemplate();
        this.fpsDisplay = new TextDisplayTemplate("FpsDisplay", FontLoadersWithChinese.hongMengSR15);
        this.xyzDisplay = new TextDisplayTemplate("XyzDisplay", FontLoadersWithChinese.hongMengSR15);
        this.headDisplay = new TextDisplayTemplate("HeadDisplay", FontLoadersWithChinese.hongMengSR15);
        this.targetHud = new TargetHud("TargetHud");
        this.longJumpProgress = new LongJumpProgress();

        this.GuiElementMap.put("ScaffoldCount", this.scaffoldCount);
        this.GuiElementMap.put("Radar", this.radarTemplate);
        this.GuiElementMap.put("KeyStrokes", this.keyStrokesTemplate);
        this.GuiElementMap.put("FPSDisplay", this.fpsDisplay);
        this.GuiElementMap.put("XYZDisplay", this.xyzDisplay);
        this.GuiElementMap.put("HeadDisplay", this.headDisplay);
        this.GuiElementMap.put("TargetHud", this.targetHud);
        this.GuiElementMap.put("LongJumpProgress", this.longJumpProgress);
    }

}
