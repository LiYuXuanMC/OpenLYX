package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.templates.LongJumpProgress;
import al.logger.client.ui.templates.ModuleHud.ModuleList;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.*;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;

import java.awt.*;

public class Hud extends Module {

    public static final OptionValue componentWithAnimation = new OptionValue("Component Animation", true);
    public static final OptionValue arrayList = new OptionValue("ArrayList", true);
    public static final OptionValue logoDisplay = new OptionValue("Logo", true);
    public static final MultipleColorValue arrayListColor = new MultipleColorValue("ArrayList Colors",
            new ColorValue("Color1", new Color(255, 255, 255, 255)),
            new ColorValue("Color2", new Color(255, 255, 255, 255))
    );
    public static final DoubleValue colorIndex = new DoubleValue("Color InsertIndex", 100, 5, 20, 1);
    public static final DoubleValue colorSpeed = new DoubleValue("Color Delay", 30, 2, 15, 1);
    public static final OptionValue sort = new OptionValue("Sort", true);
    public static final OptionValue shadow = new OptionValue("Shadow", true);
    public static final ModeValue renderMode = new ModeValue("ArrayList Render Mode", RenderMode.New, RenderMode.values());
    public static final OptionValue scaffoldCount = new OptionValue("Scaffold Count", true);
    public static final OptionValue longJumpProgress = new OptionValue("LongJump Progress", true);
    public static final MultipleColorValue longJumpColor = new MultipleColorValue("LongJump Colors",
            new ColorValue("Background", new Color(29, 29, 29, 120)),
            new ColorValue("Foreground", new Color(255, 255, 255, 255))
    );

    public static final OptionValue invhud = new OptionValue("InventoryHUD"  , false);
    private final ModuleList moduleList;

    public Hud() {
        super("Hud", Category.Visual);
        arrayListColor.addCallBack(arrayList::getValue);
        sort.addCallBack(arrayList::getValue);
        renderMode.addCallBack(arrayList::getValue);
        shadow.addCallBack(arrayList::getValue);
        colorIndex.addCallBack(arrayList::getValue);
        colorSpeed.addCallBack(arrayList::getValue);
        this.addValues(renderMode, logoDisplay, componentWithAnimation, arrayList, colorIndex, colorSpeed, arrayListColor, sort, shadow, scaffoldCount, longJumpProgress, longJumpColor);
        this.setEnable();
        this.setDescription("Render the hud on the screen");
        this.moduleList = new ModuleList();
    }


    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        if (arrayList.getValue()) {
            ScaledResolution scaledResolution = new ScaledResolution(mc);
            moduleList.getPosition().setX(scaledResolution.getScaledWidth() - 10);
            moduleList.getPosition().setY(10);
            moduleList.drawComponent(0, 0, false);
        }
        if (scaffoldCount.getValue()) {
            Logger.getInstance().getGuiManager().scaffoldCount.drawComponent(0, 0, false);
        }
        if (logoDisplay.getValue()) {
            RenderUtil.drawFullImage(Logger.getInstance().getResourceManager().getResourceLocation("logo.png"), 10, 10, 80.5f, 27.5f);
        }
        if(longJumpProgress.getValue()){
            Logger.getInstance().getGuiManager().longJumpProgress.drawComponent(0, 0, false);
        }
    }

    public enum RenderMode {
        Old, New
    }

}