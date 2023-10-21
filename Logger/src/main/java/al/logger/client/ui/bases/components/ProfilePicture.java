package al.logger.client.ui.bases.components;

import al.logger.client.Logger;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class ProfilePicture extends ComponentBase {

    private ResourceLocation resourceLocation;
    private String username;
    public boolean isCurrent = false;

    public ProfilePicture(ResourceLocation resourceLocation, String username) {
        super("ProfilePicture");
        this.resourceLocation = resourceLocation;
        this.username = username;
    }

    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        if (this.resourceLocation != null) {
            RenderUtil.drawFullImage(this.resourceLocation, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getWidth(), this.getPosition().getHeight());
        }
        if (isMouseHover) {
            RenderUtil.drawRound(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getWidth(), this.getPosition().getHeight(), this.getPosition().getHeight() / 2, new Color(0, 0, 0, 120));
        }
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.isMouseHover) {
            if (!isCurrent) {
                Logger.getInstance().getResourceManager().removePicture(username);
                this.resourceLocation = Logger.getInstance().getResourceManager().getPicture(username);
                return true;
            }
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a PNG file");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG files", "png");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    URL url = new URL("http://111.180.205.223:22218/profile/picture");
                    String userid = Logger.getInstance().getLoggerUser().getUserid();
                    String idun = Logger.getInstance().getLoggerUser().getIdunToken();
                    HttpURLConnection con = (HttpURLConnection) new URL(
                            url + "?userid=" + userid + "&idun=" + idun
                    ).openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    con.connect();
                    DataOutputStream out = new DataOutputStream(con.getOutputStream());
                    out.write(Files.readAllBytes(selectedFile.toPath()));
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        response.append("\n");
                    }
                    in.close();
                    String resp = response.toString();
                    System.out.println(resp);
                    ChatUtils.message("Server Response: " + resp.replaceAll("\n", ""));
                    if (resp.replaceAll("\n", "").equals("Uploader Success")) {
                        Logger.getInstance().getResourceManager().removePicture(username);
                        this.resourceLocation = Logger.getInstance().getResourceManager().getPicture(username);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
