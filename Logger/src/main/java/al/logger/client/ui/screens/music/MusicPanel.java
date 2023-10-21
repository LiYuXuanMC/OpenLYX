package al.logger.client.ui.screens.music;

import al.logger.client.Logger;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.PanelBase;
import al.logger.client.ui.bases.Position;
import al.logger.client.ui.bases.components.LDataContent;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.ui.bases.components.QRCodeStatus;
import al.logger.client.ui.bases.styles.Enums;
import al.logger.client.ui.screens.music.Components.ImageButton.ImageButton;
import al.logger.client.ui.screens.music.Components.TextButton.TextButton;
import al.logger.client.ui.screens.music.Container.PageView;
import al.logger.client.ui.screens.music.Enums.PAGES;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

public class MusicPanel extends PanelBase {

    private ComponentBase WindowBar;
    private ImageButton qrCodeButton;
    private TextButton textButton;
    private TextButton statusButton;
    private TextButton cookieButton;
    private TextButton labelButton;
    private TimerUtils timerUtils = new TimerUtils();
    private PAGES page = PAGES.LOGIN;
    private PAGES viewPage = PAGES.HOME;
    private QRCodeStatus qrCodeStatus = null;
    private PageView pageView;


    public MusicPanel() {
        Logger.getInstance().musicManager.refreshQRCodeKey();
        this.textButton = new TextButton(FontLoadersWithChinese.hongMengBlod20, "扫码登录", 0, 0, Color.WHITE, () -> {
        });
        this.textButton.getStyles()
                .setMargin(0, 112.5f, 0, 0)
                .setHorizontal(Enums.HORIZONTAL.CENTER);

        this.statusButton = new TextButton(FontLoadersWithChinese.hongMengSR14, "", 0, 0, new Color(255, 255, 255, 120), () -> {
        });
        this.statusButton.getStyles()
                .setMargin(0, 136f, 0, 0)
                .setHorizontal(Enums.HORIZONTAL.CENTER);

        this.cookieButton = new TextButton(FontLoadersWithChinese.hongMengSR15, "没有账户？使用游客登录", 0, 0, new Color(0, 255, 163), () -> {
            new Thread(() -> {
                if (Logger.getInstance().musicManager.visitorLogin()) {
                    Logger.getInstance().getNotificationManager().addNotification(new Notification("游客登录成功", Notification.NotificationType.Success));
                    this.page = PAGES.VIEW;
                } else {
                    Logger.getInstance().getNotificationManager().addNotification(new Notification("游客登录失败", Notification.NotificationType.Error));
                }
            }).start();
        });
        this.cookieButton.getStyles()
                .setMargin(0, 126f, 0, 0)
                .setHorizontal(Enums.HORIZONTAL.CENTER);

        this.labelButton = new TextButton(FontLoadersWithChinese.hongMengSR14, "", 0, 0, new Color(255, 255, 255, 255), () -> {

        });

        this.qrCodeButton = new ImageButton(Logger.getInstance().musicManager.getQRCodeImage(), 0, 0, 100, 100, () -> {
            if (qrCodeStatus != null && qrCodeStatus.equals(QRCodeStatus.QR_CODE_EXPIRED)) {
                Logger.getInstance().getNotificationManager().addNotification(new Notification("刷新成功", Notification.NotificationType.Warning));
                Logger.getInstance().musicManager.refreshQRCodeKey();
                this.qrCodeButton.setImage(Logger.getInstance().musicManager.getQRCodeImage());
            }
        }) {
            @Override
            protected void _drawElement() {
                super._drawElement();
                ScreenUtil.calculatedPosition(textButton, this.position);
                textButton.update();
                textButton.drawComponent(this.mouseX, this.mouseY, false);
                ScreenUtil.calculatedPosition(cookieButton, this.position);
                cookieButton.update();
                cookieButton.drawComponent(this.mouseX, this.mouseY, false);
                ScreenUtil.calculatedPosition(statusButton, this.position);
                statusButton.update();
                statusButton.drawComponent(this.mouseX, this.mouseY, false);
            }

            @Override
            protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
                cookieButton.mouseClicked(mouseX, mouseY, mouseButton);
                return super._mouseClicked(mouseX, mouseY, mouseButton);
            }
        };
        this.qrCodeButton.getStyles()
                .setMargin(0, 100, 0, 0)
                .setHorizontal(Enums.HORIZONTAL.CENTER);

        this.WindowBar = new LDataContent("WindowBar", this.position.getX(), this.position.getY(), this.position.getWidth(), this.titleHeight) {
            @Override
            protected void _drawElement() {
            }

            @Override
            protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
                return false;
            }
        };
        this.WindowBar.setEditNormalStage(true);
        this.addComponent(this.qrCodeButton);
    }

    @Override
    public void initVariables() {
        this.position = new Position(10, 10, 477, 330);
        this.titleHeight = 16;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void onGuiClosed() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mouseDWheel(mouseX, mouseY, Mouse.getDWheel());

        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        this.position.setX(Math.max(0, this.position.getX()));
        this.position.setY(Math.max(0, this.position.getY()));
        this.position.setWidth(Math.max(475, Math.min(this.position.getWidth(), scaledResolution.getScaledWidth())));
        this.position.setHeight(Math.max(350, Math.min(this.position.getHeight(), scaledResolution.getScaledHeight())));
        WindowBar.getPosition().setX(Math.max(0, WindowBar.getPosition().getX()));
        WindowBar.getPosition().setY(Math.max(0, WindowBar.getPosition().getY()));
        WindowBar.getPosition().setWidth(this.position.getWidth());
        WindowBar.getPosition().setHeight(this.titleHeight);
        RenderUtil.drawRound(WindowBar.getPosition().getX()
                , WindowBar.getPosition().getY()
                , this.position.getWidth()
                , this.position.getHeight()
                , 0
                , new Color(15, 15, 16, 204)
        );
        this.position.setX(WindowBar.getPosition().getX());
        this.position.setY(WindowBar.getPosition().getY());
        RenderUtil.doStencil(this.position.getX() + 1, this.position.getY() + 1, this.position.getWidth() - 2, this.position.getHeight() - 2, 3);
        ScreenUtil.calculatedPosition(WindowBar, this.position);
        WindowBar.update();
        WindowBar.drawComponent(mouseX, mouseY, false);
        switch (page) {
            case LOGIN: {
                if (timerUtils.delay(3000)) {
                    new Thread(() -> {
                        QRCodeStatus qrCodeStatus = Logger.getInstance().musicManager.getQRCodeStatus();
                        if (qrCodeStatus.equals(QRCodeStatus.QR_CODE_SUCCESS)) {
                            Logger.getInstance().getNotificationManager().addNotification(new Notification("二维码登录成功", Notification.NotificationType.Success));
                            this.page = PAGES.VIEW;
                        } else if (qrCodeStatus.equals(QRCodeStatus.QR_CODE_EXPIRED)) {
                            this.qrCodeStatus = qrCodeStatus;
                            this.statusButton.setContent("二维码已过期，点击刷新");
                            this.statusButton.getPosition().setWidth(this.statusButton.getRenderer().getStringWidth(this.statusButton.getContent()));
                        } else if (qrCodeStatus.equals(QRCodeStatus.QR_CODE_WAIT_OK)) {
                            this.statusButton.setContent("等待移动端确认登录");
                            this.statusButton.getPosition().setWidth(this.statusButton.getRenderer().getStringWidth(this.statusButton.getContent()));
                        }
                    }).start();
                    timerUtils.reset();
                }
                for (ComponentBase value : this.componentBases.values()) {
                    ScreenUtil.calculatedPosition(value, this.position);
                    value.update();
                    value.drawComponent(mouseX, mouseY, false);
                }
            }
            break;
            case VIEW:
                switch (viewPage) {
                    case HOME:

                        break;
                    case SEARCH:
                        break;
                    case PLAYLIST:
                        break;
                }
                break;
        }
        RenderUtil.uninstallStencil();
    }

    @Override
    public void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
        if (WindowBar.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_)) {
            return;
        }
        switch (page) {
            case LOGIN: {
                for (ComponentBase value : this.componentBases.values()) {
                    if (value.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_)) {
                        return;
                    }
                }
            }
            break;
            case VIEW:
                switch (viewPage) {
                    case HOME:
                        break;
                    case SEARCH:
                        break;
                    case PLAYLIST:
                        break;
                }
                break;
        }
    }

    @Override
    public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
        WindowBar.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
        switch (page) {
            case LOGIN: {
                for (ComponentBase value : this.componentBases.values()) {
                    value.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
                }
            }
            break;
            case VIEW:
                switch (viewPage) {
                    case HOME:
                        break;
                    case SEARCH:
                        break;
                    case PLAYLIST:
                        break;
                }
                break;
        }
    }

    @Override
    public boolean keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException {
        switch (page) {
            case LOGIN: {

            }
            break;
            case VIEW:
                switch (viewPage) {
                    case HOME:
                        break;
                    case SEARCH:
                        break;
                    case PLAYLIST:
                        break;
                }
                break;
        }
        return super.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
    }

    public void mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
        if (mouseDWheel != 0) {
            switch (page) {
                case LOGIN: {
                    for (ComponentBase value : this.componentBases.values()) {
                        if (value.mouseDWheel(mouseX, mouseY, mouseDWheel)) {
                            return;
                        }
                    }
                }
                break;
                case VIEW:
                    switch (viewPage) {
                        case HOME:
                            break;
                        case SEARCH:
                            break;
                        case PLAYLIST:
                            break;
                    }
                    break;
            }
        }
    }
}