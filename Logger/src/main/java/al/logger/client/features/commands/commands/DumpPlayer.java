package al.logger.client.features.commands.commands;

import al.logger.client.features.commands.Command;
import al.logger.client.utils.FileUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.NetworkPlayerInfo;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class DumpPlayer extends Command {
    public DumpPlayer() {
        super("DumpPlayer","dplayer");
    }

    @Override
    public boolean trigger(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (Entity entity : Minecraft.getMinecraft().getTheWorld().getLoadedEntityList()) {
            if (EntityPlayer.isEntityPlayer(entity)){
                EntityPlayer entityPlayer = new EntityPlayer(entity.getWrappedObject());
                sb.append("Player: ").append(entityPlayer.getName()).append("\n");
                sb.append("     ID: ").append(entityPlayer.getEntityId()).append("\n");
                sb.append("     Display Name: ").append(entityPlayer.getDisplayName().getUnformattedText()).append("\n");
                sb.append("     UUID: ").append(entityPlayer.getUniqueID()).append("\n");
                sb.append("     Health: ").append(entityPlayer.getHealth()).append("\n");
                sb.append("     Pos: X:").append(entityPlayer.getPosX()).append(" Y:").append(entityPlayer.getPosY()).append(" Z:").append(entityPlayer.getPosZ()).append("\n");
                sb.append("     Rotation: Yaw:").append(entityPlayer.getRotationYaw()).append(" Pitch:").append(entityPlayer.getRotationPitch()).append("\n");
                sb.append("     On Ground: ").append(entityPlayer.isOnGround()).append("\n");
                sb.append("     Is Sneaking: ").append(entityPlayer.isSneaking()).append("\n");
                sb.append("     Is Sprinting: ").append(entityPlayer.isSprinting()).append("\n");
                sb.append("     Hurt Time").append(entityPlayer.getHurtTime()).append("\n");}
        }
        for (NetworkPlayerInfo networkPlayerInfo : Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()) {
            sb.append("Player: ").append(networkPlayerInfo.getGameProfile().getName()).append(networkPlayerInfo.getGameProfile().getID()).append("\n");
        }
        FileUtils.writeFile(new File("playerdump.txt"),sb.toString().getBytes(StandardCharsets.UTF_8));
        return true;
    }

    @Override
    public void help() {

    }
}
