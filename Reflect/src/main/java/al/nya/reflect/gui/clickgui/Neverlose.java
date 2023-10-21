package al.nya.reflect.gui.clickgui;

import java.awt.Color;

import al.nya.reflect.Reflect;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Visual.AntiScreenshot;
import al.nya.reflect.utils.LWJGLMouse;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.value.*;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.features.modules.Visual.ClickGui;

/**
 * U can skid all code
 * But dont skid this ClickGui thx
 */
public class Neverlose extends GuiScreenImpl {
	
	private ModuleType currentCategory = ModuleType.Ghost;
	private float startX = (float) ClickGui.startX, startY = (float) ClickGui.startY;
	private float moveX = 0, moveY = 0;
	private Minecraft mc = Minecraft.getMinecraft();
	private boolean previousMouse = true;
	private float currentCateRectY = 22, endCateY;
	private int FPS = mc.getDebugFPS();
	private float leftY, rightY, sortL = 0, sortR = 0;
    private Translate translate = new Translate(0, 0);
    private int wheel;
    
    @Override
    public void initGui() {
    	super.initGui();
		startX = ClickGui.startX;
		startY = ClickGui.startY;
		currentCategory = ClickGui.type;
		wheel = ClickGui.wheel;
        translate.setX(0);
        translate.setY(0);
    }
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (AntiScreenshot.isGetingSS) return;

		FPS = Minecraft.getDebugFPS() == 0 ? 1 : Minecraft.getDebugFPS();
		
		if (RenderUtil.isHovered(startX, startY, startX + 520, startY + 40, mouseX, mouseY) && LWJGLMouse.isButtonDown(0)) {
            if (moveX == 0 && moveY == 0) {
                moveX = mouseX - startX;
                moveY = mouseY - startY;
            } else {
                startX = mouseX - moveX;
                startY = mouseY - moveY;
            }
            this.previousMouse = true;
		}else if (moveX != 0 || moveY != 0) {
			moveX = 0;
            moveY = 0;
		}

		RenderUtil.drawRect(startX, startY, startX + 520, startY + 460, new Color(1,20,40, 220).getRGB());
		//FontManager.Verdana14.drawString(String.valueOf(Reflect.USER.qq),startX + 520, startY + 460, (new Color(8,8,13).getRGB()+2));
		RenderUtil.drawRect(startX + 120, startY + 40, startX + 520, startY + 460, new Color(8,8,13).getRGB());
		RenderUtil.drawRect(startX + 120, startY, startX + 520, startY + 38, new Color(8,8,13).getRGB());
		RenderUtil.drawRect(startX + 120, startY + 38, startX + 520, startY + 40, new Color(5,26,38).getRGB());
		RenderUtil.drawRect(startX + 118, startY, startX + 120, startY + 460, new Color(5,26,38).getRGB());
		RenderUtil.drawRect(startX, startY + 420, startX + 118, startY + 422, new Color(5,26,38).getRGB());
		
		FontManager.arial40.drawString("Reflect", startX + 34, startY + 17, new Color(255,255,255).getRGB());
		
		int cateY = 0;
		
		String oldPref = "";
		
		currentCateRectY = RenderUtil.smooth(currentCateRectY, endCateY, MathUtil.processFPS(FPS, 1000, 0.008F), MathUtil.processFPS(FPS, 1000, 0.004F));
		
		RenderUtil.drawFastRoundedRect(startX + 8, startY + 18 + currentCateRectY, startX + 110, startY + 36 + currentCateRectY, 4F, new Color(8,50,74).getRGB());
		
		for (int i = 0; i < ModuleType.values().length; i++) {
			ModuleType category = ModuleType.values()[i];
			cateY+= 22;
			
			if (!category.name().split("_")[0].equals(oldPref)) {
				FontManager.Verdana14.drawString(category.name().split("_")[0], startX + 10, startY + 31 + cateY, new Color(55,75,88).getRGB());
				cateY+= 22;
				oldPref = category.name().split("_")[0];
			}
			
			if (category == currentCategory) endCateY = cateY;

			FontManager.Verdana18.drawString(category.name(), startX + 20, startY + 22 + cateY, new Color(255,255,255).getRGB());
			
			if (RenderUtil.isHovered(startX + 8, startY + 18 + cateY, startX + 110, startY + 36 + cateY, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
				currentCategory = category;
				sortL = 0;
				sortR = 0;
				this.previousMouse = true;
			}
		}
		
		RenderUtil.beginScissor((int)startX + 120, (int)startY + 40, (int)400, (int)420);
		
		leftY = rightY = (float) translate.getY();
		
		int size = 0;
		
		for (Module m : ModuleManager.getModuleByType(currentCategory)) {
			for (Value v : m.getValues()) {
				if (v.show()) size++;
			}
			float preY = (size + 1) * 20;
			size = 0;
			if (leftY + preY > rightY + preY) {
				int listY = 0;
				for (Value v : m.getValues()) {
					if (v.show()) size++;
					/*if (v instanceof ListOption && !v.isHide()) {
						ListOption listOption = (ListOption) v;
						if (listOption.isDownopen()) listY += 12 * listOption.getBooleans().size();
					}

					 */
				}
				RenderUtil.drawFastRoundedRect(startX + 325, startY + 50 + rightY, startX + 510, startY + 62 + rightY + (size + 1) * 20 + listY, 3, new Color(3,13,26).getRGB());
				RenderUtil.drawRect(startX + 328, startY + 50 + rightY + 14, startX + 507, startY + 52 + rightY + 14, new Color(5,26,38).getRGB());
				FontManager.Verdana20.drawString(m.getName(), startX + 329, startY + 53 + rightY, -1);
				FontManager.Verdana18.drawString("Enable " + m.getName(), startX + 328, startY + 55 + rightY + 14, new Color(156,178,191).getRGB());
				
				RenderUtil.drawRect(startX + 484, startY + 58 + rightY + 13, startX + 501, startY + 64 + rightY + 15, new Color(3,23,46).getRGB());
				RenderUtil.drawCircle(startX + 485, startY + 61 + rightY + 14, 4, new Color(3,23,46).getRGB());
				RenderUtil.drawCircle(startX + 500, startY + 61 + rightY + 14, 4, new Color(3,23,46).getRGB());
				RenderUtil.drawRect(startX + 485, startY + 58 + rightY + 14, startX + 500, startY + 64 + rightY + 14, m.isEnable() ? new Color(0,102,148).getRGB() : new Color(3,6,14).getRGB());
				RenderUtil.drawCircle(startX + 485, startY + 61 + rightY + 14, 3, m.isEnable() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
				RenderUtil.drawCircle(startX + 500, startY + 61 + rightY + 14, 3, m.isEnable() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
				
				RenderUtil.drawCircle(startX + 487 + (m.isEnable() ? 11 : 0), startY + 61 + rightY + 14, 5, m.isEnable() ? new Color(3,168,245).getRGB() : new Color(120,139,151).getRGB());
				
				if (RenderUtil.isHovered(startX + 485, startY + 58 + rightY + 14, startX + 500, startY + 64 + rightY + 14, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
					m.setEnable(!m.isEnable());
					this.previousMouse = true;
				}
				
				int valueY = 20;
				for (Value value : m.getValues()) {
					
					if (value.show()) {
						
						if (value instanceof OptionValue) {
							FontManager.Verdana18.drawString(value.getName(), startX + 328, startY + 55 + rightY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawRect(startX + 484, startY + 58 + rightY + 11 + valueY, startX + 501, startY + 64 + rightY + 13 + valueY, new Color(3,23,46).getRGB());
							RenderUtil.drawCircle(startX + 485, startY + 61 + rightY + 12 + valueY, 4, new Color(3,23,46).getRGB());
							RenderUtil.drawCircle(startX + 500, startY + 61 + rightY + 12 + valueY, 4, new Color(3,23,46).getRGB());
							RenderUtil.drawRect(startX + 485, startY + 58 + rightY + 12 + valueY, startX + 500, startY + 64 + rightY + 12 + valueY, (boolean) value.getValue() ? new Color(0,102,148).getRGB() : new Color(3,6,14).getRGB());
							RenderUtil.drawCircle(startX + 485, startY + 61 + rightY + 12 + valueY, 3, (boolean) value.getValue() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
							RenderUtil.drawCircle(startX + 500, startY + 61 + rightY + 12 + valueY, 3, (boolean) value.getValue() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
							
							RenderUtil.drawCircle(startX + 487 + ((boolean) value.getValue() ? 11 : 0), startY + 61 + rightY + 12 + valueY, 5, (boolean) value.getValue() ? new Color(3,168,245).getRGB() : new Color(120,139,151).getRGB());
							
							if (RenderUtil.isHovered(startX + 485, startY + 58 + rightY + 10 + valueY, startX + 500, startY + 64 + rightY + 14 + valueY, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
								value.setValue(!(boolean)value.getValue());
								mc.getThePlayer().playSound("random.click",1,1);
								this.previousMouse = true;
							}
							valueY += 20;
						}
						
						if (value instanceof DoubleValue) {
							FontManager.Verdana18.drawString(value.getName(), startX + 328, startY + 55 + rightY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawRect(startX + 415, startY + 54 + rightY + 19 + valueY, startX + 480, startY + 54 + rightY + 21 + valueY, new Color(3,23,46).getRGB());
							
							double render = (65.0F * (((Number) value.getValue()).doubleValue() - ((DoubleValue)value).getMin()) / (((DoubleValue)value).getMax() - ((DoubleValue)value).getMin()));
							
							RenderUtil.drawRect(startX + 415, startY + 54 + rightY + 19 + valueY, startX + 415 + render, startY + 54 + rightY + 21 + valueY, new Color(0,102,148).getRGB());
							
							RenderUtil.drawCircle(startX + 416 + render, startY + 54 + rightY + 20 + valueY, 3, new Color(61,133,224).getRGB());

							FontManager.Verdana14.drawCenteredString(""+value.getValue(), startX + 498, startY + 55 + rightY + 14 + valueY, -1);
							
							if (RenderUtil.isHovered(startX + 415, startY + 54 + rightY + 19 + valueY, startX + 480, startY + 54 + rightY + 21 + valueY, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
                                ((DoubleValue) value).setValue((mouseX - (startX + 415)) * (((DoubleValue)value).getMax() - ((DoubleValue)value).getMin()) / 65.0F + ((DoubleValue)value).getMin());
							}
							valueY += 20;
						}
						
						if (value instanceof ModeValue) {
							FontManager.Verdana18.drawString(value.getName(), startX + 328, startY + 55 + rightY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawFastRoundedRect(startX + 439, startY + 57 + rightY + 9 + valueY, startX + 502, startY + 65 + rightY + 15 + valueY, 3, new Color(3,23,46).getRGB());
							RenderUtil.drawFastRoundedRect(startX + 440, startY + 58 + rightY + 9 + valueY, startX + 501, startY + 64 + rightY + 15 + valueY, 3, new Color(3,5,13).getRGB());

							FontManager.Verdana16.drawCenteredString(value.getValue().toString(), startX + 470, startY + 59 + rightY + 10 + valueY, new Color(200,200,200).getRGB());
							
							if (RenderUtil.isHovered(startX + 440, startY + 58 + rightY + 9 + valueY, startX + 501, startY + 64 + rightY + 15 + valueY, mouseX, mouseY)) {
								
								if (!this.previousMouse && LWJGLMouse.isButtonDown(0)) {
									value.setValue(((ModeValue)value).getValues().get((((ModeValue)value).getValues().indexOf(value.getValue()) + 1) >= ((ModeValue)value).getValues().size() ? 0 : ((ModeValue)value).getValues().indexOf(value.getValue()) + 1));
									mc.getThePlayer().playSound("random.click",1,1);
									this.previousMouse = true;
								}

                                if (!this.previousMouse && LWJGLMouse.isButtonDown(1)) {
                                    value.setValue(((ModeValue) value).getValues().get((((ModeValue) value).getValues().indexOf(value.getValue()) - 1) < 0 ? ((ModeValue) value).getValues().size() - 1 : ((ModeValue) value).getValues().indexOf(value.getValue()) - 1));
                                    mc.getThePlayer().playSound("random.click", 1, 1);
                                    this.previousMouse = true;
                                }

                            }

                            valueY += 20;
                        }

                        if (value instanceof KeyBindValue) {
                            FontManager.Verdana18.drawString(value.getName(), startX + 328, startY + 55 + rightY + 14 + valueY, new Color(156, 178, 191).getRGB());

                            RenderUtil.drawFastRoundedRect(startX + 439, startY + 57 + rightY + 9 + valueY, startX + 502, startY + 65 + rightY + 15 + valueY, 3, new Color(3, 23, 46).getRGB());
                            RenderUtil.drawFastRoundedRect(startX + 440, startY + 58 + rightY + 9 + valueY, startX + 501, startY + 64 + rightY + 15 + valueY, 3, new Color(3, 5, 13).getRGB());

                            FontManager.Verdana16.drawCenteredString(((KeyBindValue) value).getValue().getDisplayName(), startX + 470, startY + 59 + rightY + 10 + valueY, new Color(200, 200, 200).getRGB());

                        }
						
						/*if (value instanceof ListOption) {
							ListOption listOption = (ListOption) value;
							FontCache.Verdana18.drawString(value.getDisplayName(), startX + 328, startY + 55 + rightY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawFastRoundedRect(startX + 439, startY + 57 + rightY + 9 + valueY, startX + 502, startY + 65 + rightY + 15 + valueY, 3, new Color(3,23,46).getRGB());
							RenderUtil.drawFastRoundedRect(startX + 440, startY + 58 + rightY + 9 + valueY, startX + 501, startY + 64 + rightY + 15 + valueY, 3, new Color(3,5,13).getRGB());
							
							FontCache.Verdana16.drawCenteredString((String) value.getValue(), startX + 470, startY + 59 + rightY + 10 + valueY, new Color(200,200,200).getRGB());
							
							ArrayList<BoolOption> selected = new ArrayList<>();
							String msg = "";
							
							for (int v = 0; v < listOption.getBooleans().size(); v++) {
		                		BoolOption bool = (BoolOption) listOption.getBooleans().get(v);
		                		if (bool.getValue()) selected.add(bool);
							}
							
							if (selected.isEmpty()) {
								msg = "...";
							} else if (selected.size() == 1) {
								msg = selected.get(0).getDisplayName();
							} else {
								msg = selected.get(0).getDisplayName() + "...";
							}
							
							if (RenderUtil.isHovered(startX + 440, startY + 58 + rightY + 9 + valueY, startX + 501, startY + 64 + rightY + 15 + valueY, mouseX, mouseY) && !this.previousMouse && Mouse.isButtonDown(0)) {
								listOption.setDownopen(!listOption.isDownopen());
								this.previousMouse = true;
								mc.thePlayer.playSound("random.click",1,1);
							}
							
							if (listOption.isDownopen()) {
								RenderUtil.drawFastRoundedRect(startX + 439, startY + 65 + rightY + 17 + valueY, startX + 502, startY + 65 + rightY + 17 + valueY + 12 * listOption.getBooleans().size(), 3, new Color(3,23,46).getRGB());
								RenderUtil.drawFastRoundedRect(startX + 440, startY + 66 + rightY + 17 + valueY, startX + 501, startY + 65 + rightY + 16 + valueY + 12 * listOption.getBooleans().size(), 3, new Color(3,5,13).getRGB());
								int downY = 0;
								for (int v = 0; v < listOption.getBooleans().size(); v++) {
									BoolOption bool = (BoolOption) listOption.getBooleans().get(v);
									FontCache.Verdana14.drawCenteredString(bool.getDisplayName(), startX + 470, startY + 60 + rightY + 24 + valueY + downY, bool.getValue() ? new Color(57,124,210).getRGB() : new Color(114,132,144).getRGB());
									if (RenderUtil.isHovered(startX + 440, startY + 66 + rightY + 17 + valueY + downY, startX + 501, startY + 66 + rightY + 17 + valueY + downY + 12, mouseX, mouseY) && Mouse.isButtonDown(0) && !previousMouse) {
										bool.setValue(!bool.getValue());
										mc.thePlayer.playSound("random.click",1,1);
										previousMouse = true;
									}
									downY += 12;
								}
							}
							
							FontCache.arial18.drawCenteredString(msg, startX + 470, startY + 59 + rightY + 10 + valueY, new Color(200,200,200).getRGB());
							
							valueY += listOption.isDownopen() ? 20 + 12 * listOption.getBooleans().size()  : 20;
						}

						 */
					}
					
				}
				rightY += 16 + (size + 1) * 20;
			}
			else {
				size = 0;
				int listY = 0;
				for (Value v : m.getValues()) {
					if (v.show()) size++;
					/*if (v instanceof ListOption && !v.isHide()) {
						ListOption listOption = (ListOption) v;
						if (listOption.isDownopen()) listY += 12 * listOption.getBooleans().size();
					}

					 */
				}
				RenderUtil.drawFastRoundedRect(startX + 130, startY + 50 + leftY, startX + 315, startY + 62 + leftY + (size + 1) * 20 + listY, 3, new Color(3,13,26).getRGB());
				RenderUtil.drawRect(startX + 133, startY + 50 + leftY + 14, startX + 312, startY + 52 + leftY + 14, new Color(5,26,38).getRGB());
				FontManager.Verdana20.drawString(m.getName(), startX + 134, startY + 53 + leftY, -1);
				FontManager.Verdana18.drawString("Enable " + m.getName(), startX + 133, startY + 55 + leftY + 14, new Color(156,178,191).getRGB());
				
				RenderUtil.drawRect(startX + 289, startY + 58 + leftY + 13, startX + 306, startY + 64 + leftY + 15, new Color(3,23,46).getRGB());
				RenderUtil.drawCircle(startX + 290, startY + 61 + leftY + 14, 4, new Color(3,23,46).getRGB());
				RenderUtil.drawCircle(startX + 305, startY + 61 + leftY + 14, 4, new Color(3,23,46).getRGB());
				RenderUtil.drawRect(startX + 290, startY + 58 + leftY + 14, startX + 305, startY + 64 + leftY + 14, m.isEnable() ? new Color(0,102,148).getRGB() : new Color(3,6,14).getRGB());
				RenderUtil.drawCircle(startX + 290, startY + 61 + leftY + 14, 3, m.isEnable() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
				RenderUtil.drawCircle(startX + 305, startY + 61 + leftY + 14, 3, m.isEnable() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
				
				RenderUtil.drawCircle(startX + 292 + (m.isEnable() ? 11 : 0), startY + 61 + leftY + 14, 5, m.isEnable() ? new Color(3,168,245).getRGB() : new Color(120,139,151).getRGB());
				
				if (RenderUtil.isHovered(startX + 290, startY + 58 + leftY + 14, startX + 305, startY + 64 + leftY + 14, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
					m.setEnable(!m.isEnable());
					this.previousMouse = true;
				}
				
				int valueY = 20;
				for (Value value : m.getValues()) {
					
					if (value.show()) {
						
						if (value instanceof OptionValue) {
							FontManager.Verdana18.drawString(value.getName(), startX + 133, startY + 55 + leftY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawRect(startX + 289, startY + 58 + leftY + 11 + valueY, startX + 306, startY + 64 + leftY + 13 + valueY, new Color(3,23,46).getRGB());
							RenderUtil.drawCircle(startX + 290, startY + 61 + leftY + 12 + valueY, 4, new Color(3,23,46).getRGB());
							RenderUtil.drawCircle(startX + 305, startY + 61 + leftY + 12 + valueY, 4, new Color(3,23,46).getRGB());
							RenderUtil.drawRect(startX + 290, startY + 58 + leftY + 12 + valueY, startX + 305, startY + 64 + leftY + 12 + valueY, (boolean) value.getValue() ? new Color(0,102,148).getRGB() : new Color(3,6,14).getRGB());
							RenderUtil.drawCircle(startX + 290, startY + 61 + leftY + 12 + valueY, 3, (boolean) value.getValue() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
							RenderUtil.drawCircle(startX + 305, startY + 61 + leftY + 12 + valueY, 3, (boolean) value.getValue() ? new Color(0,102,148).getRGB() :  new Color(3,6,14).getRGB());
							
							RenderUtil.drawCircle(startX + 292 + ((boolean) value.getValue() ? 11 : 0), startY + 61 + leftY + 12 + valueY, 5, (boolean) value.getValue() ? new Color(3,168,245).getRGB() : new Color(120,139,151).getRGB());
							
							if (RenderUtil.isHovered(startX + 290, startY + 58 + leftY + 10 + valueY, startX + 305, startY + 64 + leftY + 14 + valueY, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
								value.setValue(!(boolean)value.getValue());
								mc.getThePlayer().playSound("random.click",1,1);
								this.previousMouse = true;
							}
							valueY += 20;
						}
						
						if (value instanceof DoubleValue) {
							FontManager.Verdana18.drawString(value.getName(), startX + 328 - 195, startY + 55 + leftY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawRect(startX + 415 - 195, startY + 54 + leftY + 19 + valueY, startX + 480 - 195, startY + 54 + leftY + 21 + valueY, new Color(3,23,46).getRGB());
							
							double render = (65.0F * (((Number) value.getValue()).doubleValue() - ((DoubleValue)value).getMin()) / (((DoubleValue)value).getMax() - ((DoubleValue)value).getMin()));
							
							RenderUtil.drawRect(startX + 415 - 195, startY + 54 + leftY + 19 + valueY, startX + 415 + render - 195, startY + 54 + leftY + 21 + valueY, new Color(0,102,148).getRGB());
							
							RenderUtil.drawCircle(startX + 416 + render - 195, startY + 54 + leftY + 20 + valueY, 3, new Color(61,133,224).getRGB());

							FontManager.Verdana14.drawCenteredString(""+value.getValue(), startX + 498 - 195, startY + 55 + leftY + 14 + valueY, -1);
							
							if (RenderUtil.isHovered(startX + 415 - 195, startY + 54 + leftY + 19 + valueY, startX + 480 - 195, startY + 54 + leftY + 21 + valueY, mouseX, mouseY) && LWJGLMouse.isButtonDown(0) && !this.previousMouse) {
								((DoubleValue) value).setValue((mouseX - (startX + 415 - 195)) * (((DoubleValue)value).getMax() - ((DoubleValue)value).getMin()) / 65.0F + ((DoubleValue)value).getMin());
							}

							valueY += 20;
						}
						
						if (value instanceof ModeValue) {
							FontManager.Verdana18.drawString(value.getName(), startX + 328 - 195, startY + 55 + leftY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawFastRoundedRect(startX + 439 - 195, startY + 57 + leftY + 9 + valueY, startX + 502 - 195, startY + 65 + leftY + 15 + valueY, 3, new Color(3,23,46).getRGB());
							RenderUtil.drawFastRoundedRect(startX + 440 - 195, startY + 58 + leftY + 9 + valueY, startX + 501 - 195, startY + 64 + leftY + 15 + valueY, 3, new Color(3,5,13).getRGB());

							FontManager.Verdana16.drawCenteredString(value.getValue().toString(), startX + 470 - 195, startY + 59 + leftY + 10 + valueY, new Color(200,200,200).getRGB());
							
							if (RenderUtil.isHovered(startX + 440 - 195, startY + 58 + leftY + 9 + valueY, startX + 501 - 195, startY + 64 + leftY + 15 + valueY, mouseX, mouseY)) {
								
								if (!this.previousMouse && LWJGLMouse.isButtonDown(0)) {
									((ModeValue)value).setValue(((ModeValue)value).getValues().get((((ModeValue)value).getValues().indexOf(value.getValue()) + 1) >= ((ModeValue)value).getValues().size() ? 0 : ((ModeValue)value).getValues().indexOf(value.getValue()) + 1));
									mc.getThePlayer().playSound("random.click",1,1);
									this.previousMouse = true;
								}
								
								if (!this.previousMouse && LWJGLMouse.isButtonDown(1)) {
									((ModeValue)value).setValue(((ModeValue)value).getValues().get((((ModeValue)value).getValues().indexOf(value.getValue()) - 1) < 0 ? ((ModeValue)value).getValues().size() - 1 : ((ModeValue)value).getValues().indexOf(value.getValue()) - 1));
    								mc.getThePlayer().playSound("random.click",1,1);
									this.previousMouse = true;
								}
								
							}
							
							valueY += 20;
						}

						
						/*if (value instanceof ListOption) {
							ListOption listOption = (ListOption) value;
							FontCache.Verdana18.drawString(value.getDisplayName(), startX + 328 - 195, startY + 55 + leftY + 14 + valueY, new Color(156,178,191).getRGB());
							
							RenderUtil.drawFastRoundedRect(startX + 439 - 195, startY + 57 + leftY + 9 + valueY, startX + 502 - 195, startY + 65 + leftY + 15 + valueY, 3, new Color(3,23,46).getRGB());
							RenderUtil.drawFastRoundedRect(startX + 440 - 195, startY + 58 + leftY + 9 + valueY, startX + 501 - 195, startY + 64 + leftY + 15 + valueY, 3, new Color(3,5,13).getRGB());
							
							FontCache.Verdana16.drawCenteredString((String) value.getValue(), startX + 470 - 195, startY + 59 + leftY + 10 + valueY, new Color(200,200,200).getRGB());
							
							ArrayList<BoolOption> selected = new ArrayList<>();
							String msg = "";
							
							for (int v = 0; v < listOption.getBooleans().size(); v++) {
		                		BoolOption bool = (BoolOption) listOption.getBooleans().get(v);
		                		if (bool.getValue()) selected.add(bool);
							}
							
							if (selected.isEmpty()) {
								msg = "...";
							} else if (selected.size() == 1) {
								msg = selected.get(0).getDisplayName();
							} else {
								msg = selected.get(0).getDisplayName() + "...";
							}
							
							if (RenderUtil.isHovered(startX + 440 - 195, startY + 58 + leftY + 9 + valueY, startX + 501 - 195, startY + 64 + leftY + 15 + valueY, mouseX, mouseY) && !this.previousMouse && Mouse.isButtonDown(0)) {
								listOption.setDownopen(!listOption.isDownopen());
								this.previousMouse = true;
								mc.thePlayer.playSound("random.click",1,1);
							}
							
							if (listOption.isDownopen()) {
								RenderUtil.drawFastRoundedRect(startX + 439 - 195, startY + 65 + leftY + 17 + valueY, startX + 502 - 195, startY + 65 + leftY + 17 + valueY + 12 * listOption.getBooleans().size(), 3, new Color(3,23,46).getRGB());
								RenderUtil.drawFastRoundedRect(startX + 440 - 195, startY + 66 + leftY + 17 + valueY, startX + 501 - 195, startY + 65 + leftY + 16 + valueY + 12 * listOption.getBooleans().size(), 3, new Color(3,5,13).getRGB());
								int downY = 0;
								for (int v = 0; v < listOption.getBooleans().size(); v++) {
									BoolOption bool = (BoolOption) listOption.getBooleans().get(v);
									FontCache.Verdana14.drawCenteredString(bool.getDisplayName(), startX + 470 - 195, startY + 60 + leftY + 24 + valueY + downY, bool.getValue() ? new Color(57,124,210).getRGB() : new Color(114,132,144).getRGB());
									if (RenderUtil.isHovered(startX + 440 - 195, startY + 66 + leftY + 17 + valueY + downY, startX + 501 - 195, startY + 66 + leftY + 17 + valueY + downY + 12, mouseX, mouseY) && Mouse.isButtonDown(0) && !previousMouse) {
										bool.setValue(!bool.getValue());
										mc.thePlayer.playSound("random.click",1,1);
										previousMouse = true;
									}
									downY += 12;
								}
							}
							
							FontCache.arial18.drawCenteredString(msg, startX + 470 - 195, startY + 59 + leftY + 10 + valueY, new Color(200,200,200).getRGB());
							
							valueY += listOption.isDownopen() ? 20 + 12 * listOption.getBooleans().size()  : 20;
						}

						 */
					}
					
				}
				
				leftY += 16 + (size + 1) * 20;
			}
		}


		RenderUtil.endScissor();
		
        int real = LWJGLMouse.getDWheel();
        float moduleHeight = leftY > rightY ? leftY : (float) (rightY - translate.getY());
        if (LWJGLMouse.hasWheel() && mouseX > startX + 120 && mouseY > startY && mouseX < startX + 400 && mouseY < startY + 40 + 420) {
            if (real > 0 && wheel < 0) {
                for (int i = 0; i < 5; i++) {
                    if (!(wheel < 0))
                        break;
                    wheel += 5;
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    if (!(real < 0 && moduleHeight > 240 && Math.abs(wheel) < (moduleHeight - (236))))
                        break;
                    wheel -= 5;
                }
            }
        }
        translate.interpolate(0, wheel, 0.15F);
		
        if (!LWJGLMouse.isButtonDown(0) && !LWJGLMouse.isButtonDown(1)) {
            this.previousMouse = false;
        }

	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		//Core.getInstance().getConfigManager().saveOptions();
		ClickGui.startX = startX;
		ClickGui.startY = startY;
		ClickGui.type = currentCategory;
		ClickGui.wheel = wheel;
	}

}
