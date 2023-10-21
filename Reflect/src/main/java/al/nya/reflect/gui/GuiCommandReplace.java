package al.nya.reflect.gui;

import al.nya.reflect.features.modules.Command;
import al.nya.reflect.utils.LWJGLKeyBoard;
import al.nya.reflect.utils.LWJGLMouse;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiTextField;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.network.C14PacketTabComplete;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.ChatComponentText;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumChatFormatting;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEventAction;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class GuiCommandReplace extends GuiScreenImpl {
    protected GuiTextField inputField;
    private String historyBuffer = "";
    Minecraft mc = Minecraft.getMinecraft();
    protected GuiScreen Instance;
    private int sentHistoryCursor = -1;
    private boolean playerNamesFound;
    private boolean waitingOnAutocomplete;
    private URI clickedLinkURI;
    private int autocompleteIndex;
    private static final Set<String> PROTOCOLS = new HashSet<>();
    private List<String> foundPlayerNames = new ArrayList<String>();
    ScaledResolution sr = new ScaledResolution(mc);
    static {
        PROTOCOLS.add("http");
        PROTOCOLS.add("https");
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(2, sr.getScaledHeight() - 14, sr.getScaledWidth() - 2, sr.getScaledHeight() - 2, Integer.MIN_VALUE);
        inputField.drawTextBox();
        IChatComponent ichatcomponent = Minecraft.getMinecraft().getIngameGUI().getGuiChat().getChatComponent(mouseX, mouseY);

        if (!ichatcomponent.isNull() && !ichatcomponent.getChatStyle().getChatHoverEvent().isNull())
        {
            Instance.handleComponentHover(ichatcomponent, mouseX, mouseY);
        }
    }
    @Override
    public void initGui(){
        Instance = new GuiScreen(this);
        LWJGLKeyBoard.enableRepeatEvents(true);
        sentHistoryCursor = Minecraft.getMinecraft().getIngameGUI().getGuiChat().getSentMessages().size();
        inputField = (new GuiTextField(0, Minecraft.getMinecraft().getFontRenderer(), 4, sr.getScaledHeight() - 12, sr.getScaledWidth() - 4, 12));
        inputField.setMaxStringLength(100);
        inputField.setEnableBackgroundDrawing(false);
        inputField.setFocused(true);
        inputField.setCanLoseFocus(false);
    }
    @Override
    public void onGuiClosed(){
        LWJGLKeyBoard.enableRepeatEvents(false);
        Minecraft.getMinecraft().getIngameGUI().getGuiChat().resetScroll();
    }
    public boolean keyTyped(char typedChar, int keyCode) {
        this.waitingOnAutocomplete = false;

        if (keyCode == 15) {
            this.autocompletePlayerNames();
        } else {
            this.playerNamesFound = false;
        }

        if (keyCode == 1) {
            mc.displayGuiScreen(new GuiScreen(null));
        } else if (keyCode != 28 && keyCode != 156) {
            if (keyCode == 200) {
                this.getSentHistory(-1);
            } else if (keyCode == 208) {
                this.getSentHistory(1);
            } else if (keyCode == 201) {
                mc.getIngameGUI().getGuiChat().scroll(mc.getIngameGUI().getGuiChat().getLineCount() - 1);
            } else if (keyCode == 209) {
                mc.getIngameGUI().getGuiChat().scroll(-mc.getIngameGUI().getGuiChat().getLineCount() + 1);
            } else {
                this.inputField.textboxKeyTyped(typedChar, keyCode);
            }
        } else {
            String s = this.inputField.getText().trim();

            if (s.length() > 0) {
                sendChatMessage(s);
            }

            mc.displayGuiScreen(new GuiScreen(null));
        }
        return false;
    }
    public void sendChatMessage(String msg)
    {
        if (!Command.message(msg)){
            return;
        }
        this.mc.getIngameGUI().getGuiChat().addToSentMessages(msg);
        this.mc.getThePlayer().sendChatMessage(msg);
    }
    public void sendChatMessage(String msg, boolean addToChat)
    {
        if (addToChat)
        {
            this.mc.getIngameGUI().getGuiChat().addToSentMessages(msg);
        }
        this.mc.getThePlayer().sendChatMessage(msg);
    }
    public void autocompletePlayerNames()
    {
        if (this.playerNamesFound)
        {
            this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());

            if (this.autocompleteIndex >= this.foundPlayerNames.size())
            {
                this.autocompleteIndex = 0;
            }
        }
        else
        {
            int i = this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false);
            this.foundPlayerNames.clear();
            this.autocompleteIndex = 0;
            String s = this.inputField.getText().substring(i).toLowerCase();
            String s1 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
            this.sendAutocompleteRequest(s1, s);

            if (this.foundPlayerNames.isEmpty())
            {
                return;
            }

            this.playerNamesFound = true;
            this.inputField.deleteFromCursor(i - this.inputField.getCursorPosition());
        }

        if (this.foundPlayerNames.size() > 1)
        {
            StringBuilder stringbuilder = new StringBuilder();

            for (String s2 : this.foundPlayerNames)
            {
                if (stringbuilder.length() > 0)
                {
                    stringbuilder.append(", ");
                }

                stringbuilder.append(s2);
            }

            this.mc.getIngameGUI().getGuiChat().printChatMessageWithOptionalDeletion(new ChatComponentText(stringbuilder.toString()), 1);
        }

        this.inputField.writeText(EnumChatFormatting.getTextWithoutFormattingCodes(this.foundPlayerNames.get(this.autocompleteIndex++)));
    }
    private void sendAutocompleteRequest(String p_146405_1_, String p_146405_2_)
    {
        if (p_146405_1_.length() >= 1)
        {
            BlockPos blockpos = BlockPos.wrap(null);

            if (!mc.getObjectMouseOver().isNull() && mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.BLOCK)
            {
                blockpos = this.mc.getObjectMouseOver().getBlockPos();
            }

            this.mc.getThePlayer().getSendQueue().addToSendQueue(new C14PacketTabComplete(p_146405_1_, blockpos));
            this.waitingOnAutocomplete = true;
        }
    }
    public void getSentHistory(int msgPos)
    {
        int i = this.sentHistoryCursor + msgPos;
        int j = this.mc.getIngameGUI().getGuiChat().getSentMessages().size();
        i = MathHelper.clamp_int(i, 0, j);

        if (i != this.sentHistoryCursor)
        {
            if (i == j)
            {
                this.sentHistoryCursor = j;
                this.inputField.setText(this.historyBuffer);
            }
            else
            {
                if (this.sentHistoryCursor == j)
                {
                    this.historyBuffer = this.inputField.getText();
                }

                this.inputField.setText((String)this.mc.getIngameGUI().getGuiChat().getSentMessages().get(i));
                this.sentHistoryCursor = i;
            }
        }
    }
    public void updateScreen()
    {
        this.inputField.updateCursorCounter();
    }
    protected void setText(String newChatText, boolean shouldOverwrite)
    {
        if (shouldOverwrite)
        {
            this.inputField.setText(newChatText);
        }
        else
        {
            this.inputField.writeText(newChatText);
        }
    }
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
        if (mouseButton == 0)
        {
            IChatComponent ichatcomponent = this.mc.getIngameGUI().getGuiChat().getChatComponent(LWJGLMouse.getX(), LWJGLMouse.getY());

            if (this.handleComponentClick(ichatcomponent))
            {
                return;
            }
        }

        this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
    }
    public static boolean isShiftKeyDown()
    {
        return LWJGLKeyBoard.isKeyDown(42) || LWJGLKeyBoard.isKeyDown(54);
    }
    protected boolean handleComponentClick(IChatComponent p_175276_1_)
    {
        if (p_175276_1_ == null)
        {
            return false;
        }
        else
        {
            ClickEvent clickevent = p_175276_1_.getChatStyle().getChatClickEvent();

            if (isShiftKeyDown())
            {
                if (p_175276_1_.getChatStyle().getInsertion() != null)
                {
                    this.setText(p_175276_1_.getChatStyle().getInsertion(), false);
                }
            }
            else if (clickevent != null)
            {
                if (clickevent.getAction() == ClickEventAction.OPEN_URL)
                {
                    if (!this.mc.getGameSettings().getChatLinks())
                    {
                        return false;
                    }

                    try
                    {
                        URI uri = new URI(clickevent.getValue());
                        String s = uri.getScheme();

                        if (s == null)
                        {
                            throw new URISyntaxException(clickevent.getValue(), "Missing protocol");
                        }

                        if (!PROTOCOLS.contains(s.toLowerCase()))
                        {
                            throw new URISyntaxException(clickevent.getValue(), "Unsupported protocol: " + s.toLowerCase());
                        }
                        this.openWebLink(uri);
                    }
                    catch (URISyntaxException urisyntaxexception)
                    {
                        //LOGGER.error((String)("Can\'t open url for " + clickevent), (Throwable)urisyntaxexception);
                    }
                }
                else if (clickevent.getAction() == ClickEventAction.OPEN_FILE)
                {
                    URI uri1 = (new File(clickevent.getValue())).toURI();
                    this.openWebLink(uri1);
                }
                else if (clickevent.getAction() == ClickEventAction.SUGGEST_COMMAND)
                {
                    this.setText(clickevent.getValue(), true);
                }
                else if (clickevent.getAction() == ClickEventAction.RUN_COMMAND)
                {
                    this.sendChatMessage(clickevent.getValue(), false);
                }
                else
                {
                    //LOGGER.error("Don\'t know how to handle " + clickevent);
                }

                return true;
            }

            return false;
        }
    }
    private void openWebLink(URI p_175282_1_)
    {
        try
        {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
            oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {p_175282_1_});
        }
        catch (Throwable throwable)
        {
            //LOGGER.error("Couldn\'t open link", throwable);
        }
    }
}
