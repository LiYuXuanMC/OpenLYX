package com.reflectmc.reflect.utils.rotation;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;
import lombok.Getter;
import lombok.Setter;

public class Rotation {
    @Getter
    @Setter
    private float yaw;
    @Getter
    @Setter
    private float pitch;
    public Rotation(float yaw,float pitch){
        this.yaw = yaw;
        this.pitch = pitch;
    }
    public void toPlayer(EntityPlayer player) {
        if ((Float.isNaN(yaw) || Float.isNaN(pitch))) {
            return;
        }

        fixedSensitivity(Minecraft.getMinecraft().getGameSettings().getMouseSensitivity());

        player.setRotationYaw(yaw);
        player.setRotationPitch(pitch);
    }
    public void fixedSensitivity(float sensitivity) {
        /*if (Rotations.fixedValue.get() == "None") return
        if (Rotations.fixedValue.get() == "Old") {
            val f = sensitivity * 0.6F + 0.2F
            val gcd = f * f * f * 1.2F

            yaw -= yaw % gcd
            pitch -= pitch % gcd
            return
        }
        val f = sensitivity * 0.6F + 0.2F
        val gcd = f * f * f * 1.2F

        // get previous rotation
        val rotation = RotationUtils.serverRotation

        // fix yaw
        var deltaYaw = yaw - rotation.yaw
        deltaYaw -= deltaYaw % gcd
        yaw = rotation.yaw + deltaYaw

        // fix pitch
        var deltaPitch = pitch - rotation.pitch
        deltaPitch -= deltaPitch % gcd
        pitch = rotation.pitch + deltaPitch

         */
        return;
    }
}
