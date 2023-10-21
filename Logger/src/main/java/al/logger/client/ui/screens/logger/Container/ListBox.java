package al.logger.client.ui.screens.logger.Container;

import al.logger.client.Logger;
import al.logger.client.config.ConfigInfo;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.Position;
import al.logger.client.ui.screens.logger.Components.Items.ListItem;
import al.logger.client.ui.screens.logger.Components.ScrollDown.ScrollDown;
import al.logger.client.ui.screens.logger.Components.config.Button.Button;
import al.logger.client.ui.screens.logger.Components.config.ConfigItem;
import al.logger.client.ui.screens.logger.Components.config.TextBox.TextBox;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListBox extends ComponentBase {

    public CopyOnWriteArrayList<ComponentBase> elements = new CopyOnWriteArrayList<>();
    public CopyOnWriteArrayList<ComponentBase> publicConfigs = new CopyOnWriteArrayList<>();
    public TabControl parent;
    public float elementHeight = 0;
    public float boxHeight = 0;
    public float scrollTo = 0;
    public Smoother scrollAnimation = new Smoother(0, 50);
    public Smoother alphaAnimation = new Smoother(0, 200);
    public float addY = 0;
    public float addX = 0;
    public float addWidth = 0;
    public TextBox titleTextBox;
    public TextBox keyCodeTextBox;
    public TextBox messageTextBox;
    public ScrollDown scrollDown;
    public Button saveButton, deleteButton, loadButton;
    public ArrayList<ComponentBase> configElements = new ArrayList<>();
    public HashMap<String, ArrayList<ComponentBase>> rightClickMenu = new HashMap<>();
    public boolean rightClickOpening = false;
    public String menuName = "";
    public Position rightClickPosition = new Position(0, 0, 0, 0);
    public Animation scaleAnimation = new Animation();
    public ConfigInfo rightSelectInfo = null;
    public boolean isItemDrag = false;
    public boolean isScrolling = true;
    public boolean isScrollDown = false;
    public ListItem renderItem = null;
    public List<Runnable> topRender = new ArrayList<>();

    public void displayRightClickMenu(String menuName, float x, float y) {
        this.closeRightClickMenu();
        this.rightClickOpening = true;
        this.menuName = menuName;
        this.rightClickPosition.setX(x);
        this.rightClickPosition.setY(y);
    }

    @Deprecated
    public void addRightClickMenu(String menuName, ArrayList<ComponentBase> menus) {
        if (!this.rightClickMenu.containsKey(menuName)) {
            this.rightClickMenu.put(menuName, menus);
        }
    }

    public void addRightClickMenu(String menuName, ComponentBase... menus) {
        if (!this.rightClickMenu.containsKey(menuName)) {
            this.rightClickMenu.put(menuName, new ArrayList<>(Arrays.asList(menus)));
        }
    }

    public void closeRightClickMenu() {
        this.scaleAnimation.reset();
        this.rightClickOpening = false;
        this.menuName = "";
    }

    public ListBox(TabControl parent) {
        super("ListBox");
        this.parent = parent;
        this.scrollDown = new ScrollDown(this);

        this.titleTextBox = new TextBox(this, 70, 20, "Title");
        configElements.add(this.titleTextBox);
        this.keyCodeTextBox = new TextBox(this, 175, 20, "Keycode Or Password");
        configElements.add(this.keyCodeTextBox);
        this.messageTextBox = new TextBox(this, this.position.getWidth() - 20f, 20, "Message...") {

            public final ArrayList<String> history = new ArrayList<>(Collections.synchronizedList(new ArrayList<>()));
            public int historyIndex = 0;

            @Override
            public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {
                if (isSelect) {
                    if ((int) p_keyTyped_1_ == 22) {
                        String clippedText = getSysClipboardText();
                        this.value += clippedText;
                        return;
                    }
                    if (p_keyTyped_2_ == 200) {
                        if (historyIndex > 0) {
                            historyIndex--;
                            this.value = history.get(historyIndex);
                        }
                        return;
                    }
                    if (p_keyTyped_2_ == 208) {
                        if (historyIndex < history.size() - 1) {
                            historyIndex++;
                            this.value = history.get(historyIndex);
                        }
                        return;
                    }
                    if (p_keyTyped_2_ == 28) {
                        if (this.value.length() == 0) {
                            return;
                        }
                        boolean isTitle = this.value.charAt(0) == '@';
                        Logger.getInstance().messageManager.sendMessage(isTitle ? this.value.substring(1) : this.value, isTitle ? 2 : 1);
                        if (history.size() > 0) {
                            if (!Objects.equals(history.get(history.size() - 1), this.value)) {
                                history.add(this.value);
                            }
                        } else {
                            history.add(this.value);
                        }
                        historyIndex = history.size();
                        this.value = "";
                        return;
                    }
                    if (p_keyTyped_2_ == 14) {
                        if (value.length() > 0) {
                            value = value.substring(0, value.length() - 1);
                        }
                        return;
                    }
                    if (!isAChar(p_keyTyped_1_ + "")) {
                        this.value += p_keyTyped_1_;
                    }
                }
            }

        };

        //下面是配置页面的几个按钮
        this.saveButton = new Button("SAVE", 50, 20) {
            @Override
            public void __mouseClicked(int mouseX, int mouseY, int mouseButton) {
                Logger.getInstance().configManager.saveConfig(titleTextBox.value, !Objects.equals(keyCodeTextBox.value, ""), keyCodeTextBox.value);
                parent.update();
            }

            @Override
            public void __mouseReleased(int mouseX, int mouseY, int mouseStatus) {

            }
        };
        this.deleteButton = new Button("DELETE", 50, 20) {
            @Override
            public void __mouseClicked(int mouseX, int mouseY, int mouseButton) {
                Logger.getInstance().configManager.delConfig(keyCodeTextBox.value);
                parent.update();
            }

            @Override
            public void __mouseReleased(int mouseX, int mouseY, int mouseStatus) {

            }
        };
        this.loadButton = new Button("LOAD", 50, 20) {
            @Override
            public void __mouseClicked(int mouseX, int mouseY, int mouseButton) {
                Logger.getInstance().configManager.loadConfig(titleTextBox.value, !Objects.equals(keyCodeTextBox.value, ""));
                parent.update();
            }

            @Override
            public void __mouseReleased(int mouseX, int mouseY, int mouseStatus) {

            }
        };

        configElements.add(this.saveButton);
        configElements.add(this.deleteButton);
        configElements.add(this.loadButton);
    }

    @Override
    public void _initElements() {
        this.position.setWidth(480);
        this.position.setHeight(283);
    }

    @Override
    public void _drawElement() {

        scrollTo = Math.min(0f, Math.max(-elementHeight + boxHeight, scrollTo));
        alphaAnimation.update(0);
        scrollAnimation.update(scrollTo, System.currentTimeMillis());
        topRender.clear();

        //下面这一部分较为屎山，请不要动
        switch (this.parent.seletedCategory) {
            case Message:
                RenderUtil.doStencil(this.position.getX() + this.addX, this.position.getY() + addY, this.position.getWidth() + this.addWidth, boxHeight, 0);

                float referenceGroupMessage = (float) (this.position.getY() + scrollAnimation.get());
                float posYMessage = referenceGroupMessage + this.addY;
                for (ComponentBase message : Logger.getInstance().messageManager.getMessages()) {
                    message.update(this);
                    if (posYMessage < this.position.getY() + this.addY + this.boxHeight && posYMessage + message.getPosition().getHeight() >= this.position.getY() + addY) {
                        message.drawComponent(this.position.getX(), posYMessage, this.position.getWidth(), mouseX, mouseY, false);
                    }
                    posYMessage += message.getPosition().getHeight() + 6f;
                }
                this.elementHeight = posYMessage - referenceGroupMessage;

                RenderUtil.uninstallStencil();

                this.messageTextBox.drawComponent(this.position.getX() + 10f, this.position.getY() + this.boxHeight + 6f, mouseX, mouseY, false);
                if (isScrolling && this.elementHeight > this.boxHeight) {
                    isScrollDown = false;
                    scrollTo = -this.elementHeight + this.boxHeight;
                } else if (this.elementHeight >= this.boxHeight) {
                    isScrollDown = false;
                }
                if (isScrollDown) {
                    this.scrollDown.drawComponent(this.position.getX() + this.position.getWidth() - 40f, this.position.getY() + this.boxHeight - 16f, 30f, 12f, mouseX, mouseY, false);
                }

                break;
            case Config:
                RenderUtil.doStencil(this.position.getX() + this.addX, this.position.getY() + addY, this.position.getWidth() + this.addWidth, boxHeight, 0);

                int wrapSize = 3;
                float referenceGroupConfig = (float) (this.position.getY() + scrollAnimation.get());
                float posYConfig = referenceGroupConfig + this.addY;
                float posXConfig = this.position.getX() + 18f;
                int i = 0;
                int addI = 0;
                FontLoadersWithChinese.hongMengSR15.drawString("私有配置", posXConfig, posYConfig, new Color(255, 255, 255).getRGB());
                posYConfig += 12f;
                for (ComponentBase element : elements) {
                    if (i >= wrapSize) {
                        i = 0;
                        posXConfig = this.position.getX() + 18f;
                        posYConfig += element.getPosition().getHeight() + 12f;
                    }
                    if (posYConfig < this.position.getY() + addY + boxHeight && posYConfig + element.getPosition().getHeight() > this.position.getY() + addY) {
                        element.drawComponent(posXConfig, posYConfig, mouseX, mouseY, false);
                    }
                    if (addI == elements.size() - 1) {
                        posYConfig += element.getPosition().getHeight();
                    }
                    element.update();
                    posXConfig += element.getPosition().getWidth() + 12f;
                    i++;
                    addI++;
                }
                FontLoadersWithChinese.hongMengSR15.drawString("公开配置", this.position.getX() + 18f, posYConfig + 10f, new Color(255, 255, 255).getRGB());
                i = 0;
                posXConfig = this.position.getX() + 18f;
                posYConfig += 24f;
                for (ComponentBase componentBase : publicConfigs) {
                    if (i == wrapSize) {
                        i = 0;
                        posXConfig = this.position.getX() + 18f;
                        posYConfig += componentBase.getPosition().getHeight() + 12f;
                    }
                    if (posYConfig < this.position.getY() + addY + boxHeight && posYConfig + componentBase.getPosition().getHeight() > this.position.getY() + addY) {
                        componentBase.drawComponent(posXConfig, posYConfig, mouseX, mouseY, false);
                    }
                    componentBase.update();
                    posXConfig += componentBase.getPosition().getWidth() + 12f;
                    i++;
                }
                this.elementHeight = posYConfig - referenceGroupConfig + 32f;

                RenderUtil.uninstallStencil();

                float drawY = this.position.getY() + 18;
                float posX = this.position.getX() + 18f;
                for (ComponentBase configElement : configElements) {
                    configElement.drawComponent(posX, drawY, mouseX, mouseY, false);
                    configElement.update();
                    posX += configElement.getPosition().getWidth() + 12f;
                }

                break;
            default:
                RenderUtil.doStencil(this.position.getX() + this.addX, this.position.getY(), this.position.getWidth(), boxHeight, 0);

                float referenceGroup = (float) (this.position.getY() + scrollAnimation.get() + 19f);
                float posY = referenceGroup;
                for (ComponentBase element : elements) {
                    element.update();
                    if (!element.isVisibility()) {
                        continue;
                    }
                    if (posY < this.position.getY() + boxHeight && posY + element.getPosition().getHeight() > this.position.getY()) {
                        element.drawComponent(this.position.getX(), posY, mouseX, mouseY, true);
                        if (element.isMouseDown()) {
                            element.setDrag(true);
                            for (ComponentBase componentBase : elements) {
                                if (element.getPosition().getY() > componentBase.getPosition().getY()
                                        && element.getPosition().getY() < componentBase.getPosition().getY() + 10
                                        && componentBase != element) {
                                    Collections.swap(elements, elements.indexOf(element), elements.indexOf(componentBase));
                                    Position posYz = componentBase.getPosition();
                                    componentBase.setPosition(element.getPosition());
                                    element.setPosition(posYz);
                                    break;
                                }
                            }
                            isItemDrag = true;
                        } else {
                            isItemDrag = false;
                            for (ComponentBase componentBase : elements) {
                                if (componentBase.isMouseDown()) {
                                    isItemDrag = true;
                                    break;
                                }
                            }
                        }
                    }
                    posY += element.getPosition().getHeight() + 8;
                }
                this.elementHeight = posY - referenceGroup + 28f;
                if (renderItem != null && isItemDrag) {
                    renderItem.setDrag(false);
                    renderItem.drawComponent(renderItem.getPosition().getX(), renderItem.getPosition().getY(), mouseX, mouseY, true);
                } else {
                    renderItem = null;
                }

                RenderUtil.uninstallStencil();
                break;
        }

        //右键菜单:(
        if (!Objects.equals(menuName, "") && this.rightClickOpening) {

            scaleAnimation.start(scaleAnimation.getValue(), 1.0, 0.1f, Type.EASE_IN_OUT_QUAD);
            scaleAnimation.update();

            rightClickMenu.get(menuName).sort(((o1, o2) -> (int) (o2.getPosition().getWidth() - o1.getPosition().getWidth())));
            this.rightClickPosition.setWidth(rightClickMenu.get(menuName).get(0).getPosition().getWidth());

            if (scaleAnimation.getValue() == scaleAnimation.getEnd()) {
                RenderUtil.upShadow(this.rightClickPosition.getX(), this.rightClickPosition.getY()
                        , this.rightClickPosition.getWidth(), this.rightClickPosition.getHeight(), 5, 5);
            }

            RenderUtil.doScale(this.rightClickPosition.getX(), this.rightClickPosition.getY(), (float) scaleAnimation.getValue(), () -> {
                RenderUtil.doStencil(this.rightClickPosition.getX(), this.rightClickPosition.getY()
                        , this.rightClickPosition.getWidth(), this.rightClickPosition.getHeight(), 4f);

                float posY = this.rightClickPosition.getY();
                for (ComponentBase componentBase : rightClickMenu.get(menuName)) {
                    componentBase.drawComponent(this.rightClickPosition.getX(), posY, mouseX, mouseY, false);
                    if (componentBase.getPosition().getWidth() < this.rightClickPosition.getWidth()) {
                        componentBase.getPosition().setWidth(this.rightClickPosition.getWidth());
                    }
                    posY += componentBase.getPosition().getHeight();
                }
                this.rightClickPosition.setHeight(posY - this.rightClickPosition.getY());

                RenderUtil.uninstallStencil();
            });

        }

        //滚动条:((((((((((((((((((((((((((((((((
        if (this.elementHeight > boxHeight && boxHeight != 0f) {
            float newBoxHeight = boxHeight - 8f;
            float newAddY = this.addY + 8f;
            RenderUtil.doStencil(
                    this.getPosition().getX() + this.getPosition().getWidth() - 10f,
                    this.getPosition().getY() + newAddY,
                    1.5f,
                    newBoxHeight,
                    1f
            );

            float scrollHeight = newBoxHeight / this.elementHeight * newBoxHeight;
            float scrollY = (float) (newAddY + -scrollAnimation.get() / this.elementHeight * newBoxHeight);
            if (scrollHeight < 8) {
                scrollHeight = 8;
            }
            RenderUtil.drawRound(this.getPosition().getX() + this.getPosition().getWidth() - 10f, this.getPosition().getY() + scrollY, 1.5f, scrollHeight, 1f, new Color(255, 255, 255, 40));


            RenderUtil.uninstallStencil();
        }
        topRender.forEach(Runnable::run);
        RenderUtil.drawRect(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight()
                , new Color(26, 26, 26, (int) alphaAnimation.get()).getRGB());
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!Objects.equals(menuName, "") && this.rightClickOpening) {
            for (ComponentBase componentBase : rightClickMenu.get(menuName)) {
                if (componentBase.mouseClicked(mouseX, mouseY, mouseButton)) {
                    this.closeRightClickMenu();
                    return true;
                }
            }
            if (mouseButton != 1) {
                this.closeRightClickMenu();
                return true;
            }
        }
        switch (this.parent.seletedCategory) {
            case Message:
                this.messageTextBox.mouseClicked(mouseX, mouseY, mouseButton);
                if (this.scrollDown.mouseClicked(mouseX, mouseY, mouseButton)) {
                    return true;
                }
                for (ComponentBase message : Logger.getInstance().messageManager.getMessages()) {
                    if (message.mouseClicked(mouseX, mouseY, mouseButton)) {
                        return true;
                    }
                }
                break;
            case Config:
                for (ComponentBase configElement : configElements) {
                    if (configElement.mouseClicked(mouseX, mouseY, mouseButton)) {
                        return true;
                    }
                }
                if (ScreenUtil.isHovered(this.position.getX() + this.addX, this.position.getY() + this.addY, this.position.getWidth() + this.addWidth, this.boxHeight, mouseX, mouseY)) {
                    for (ComponentBase element : this.elements) {
                        if (element.mouseClicked(mouseX, mouseY, mouseButton)) {
                            return true;
                        }
                    }
                    for (ComponentBase publicConfig : publicConfigs) {
                        if (publicConfig.mouseClicked(mouseX, mouseY, mouseButton)) {
                            return true;
                        }
                    }
                }
                break;
            default:
                if (ScreenUtil.isHovered(this.position.getX() + this.addX, this.position.getY() + this.addY, this.position.getWidth() + this.addWidth, this.boxHeight, mouseX, mouseY)) {
                    for (ComponentBase element : this.elements) {
                        if (element.mouseClicked(mouseX, mouseY, mouseButton)) {
                            return true;
                        }
                    }
                }
                break;
        }
        this.closeRightClickMenu();
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if (!Objects.equals(menuName, "") && this.rightClickOpening) {
            for (ComponentBase componentBase : rightClickMenu.get(menuName)) {
                if (componentBase.mouseReleased(mouseX, mouseY, mouseStatus)) {
                    return true;
                }
            }
        }
        switch (this.parent.seletedCategory) {
            case Message:
                this.messageTextBox.mouseReleased(mouseX, mouseY, mouseStatus);
                for (ComponentBase message : Logger.getInstance().messageManager.getMessages()) {
                    if (message.mouseReleased(mouseX, mouseY, mouseStatus)) {
                        return true;
                    }
                }
                break;
            case Config:
                for (ComponentBase configElement : configElements) {
                    if (configElement.mouseReleased(mouseX, mouseY, mouseStatus)) {
                        return true;
                    }
                }
                if (ScreenUtil.isHovered(this.position.getX() + this.addX, this.position.getY() + this.addY, this.position.getWidth() + this.addWidth, this.boxHeight, mouseX, mouseY)) {
                    for (ComponentBase element : this.elements) {
                        if (element.mouseReleased(mouseX, mouseY, mouseStatus)) {
                            return true;
                        }
                    }
                    for (ComponentBase publicConfig : publicConfigs) {
                        if (publicConfig.mouseReleased(mouseX, mouseY, mouseStatus)) {
                            return true;
                        }
                    }
                }
                break;
            default:
                for (ComponentBase element : this.elements) {
                    if (element.mouseReleased(mouseX, mouseY, mouseStatus)) {
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    @Override
    public void update() {
        this.scrollTo = this.parent.seletedCategory.getScrollTo();
        elements = new CopyOnWriteArrayList<>();
        publicConfigs = new CopyOnWriteArrayList<>();
        switch (this.parent.seletedCategory) {
            case Message:
                this.addX = 0;
                this.addY = 0;
                this.addWidth = 0;
                this.boxHeight = this.position.getHeight() - 32f;

                break;
            case Config:
                this.addX = 0;
                this.addY = 50;
                this.addWidth = 0;
                this.boxHeight = this.position.getHeight() - addY;
                Logger.getInstance().configManager.init();
                for (ConfigInfo configInfo : Logger.getInstance().configManager.MyConfigList) {
                    elements.add(new ConfigItem(configInfo, this));
                }
                for (ConfigInfo configInfo : Logger.getInstance().configManager.PublicConfigList) {
                    publicConfigs.add(new ConfigItem(configInfo, this));
                }
                break;
            default:
                this.addX = 0;
                this.addY = 0;
                this.addWidth = 0;
                this.boxHeight = this.position.getHeight();

                for (Module module : Logger.getInstance().moduleManager.getByCategory(this.parent.seletedCategory)) {
                    ListItem listItem = new ListItem(module, this);
                    this.elements.add(listItem);
                    listItem.module.setOpenness(false);
                }
                break;

        }
        this.alphaAnimation.setValue(255);
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
        if (mouseDWheel != 0 && ScreenUtil.isHovered(this.position.getX() + this.addX, this.position.getY() + addY, this.position.getWidth() + this.addWidth, this.boxHeight, mouseX, mouseY)) {
            this.closeRightClickMenu();
            switch (this.parent.seletedCategory) {
                case Message:
                    //Null
                    break;
                case Config:
                    //Null
                    break;
                default:
                    for (ComponentBase element : this.elements) {
                        if (element.mouseDWheel(mouseX, mouseY, mouseDWheel)) {
                            return;
                        }
                    }
                    break;
            }
            if (mouseDWheel < 0) {
                if (boxHeight < elementHeight) {
                    scrollTo -= 15.31415926f;
                    isScrollDown = true;
                }
            } else {
                scrollTo += 15.31415926f;
                isScrollDown = false;
            }
            this.parent.seletedCategory.setScrollTo(scrollTo);
            isScrolling = scrollTo <= -elementHeight + boxHeight;
        }
    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

        switch (this.parent.seletedCategory) {
            case Message:
                this.messageTextBox.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
                break;
            case Config:
                this.titleTextBox.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
                this.keyCodeTextBox.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
                break;
            default:
                for (ComponentBase element : elements) {
                    element.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
                }
                break;
        }
    }
}