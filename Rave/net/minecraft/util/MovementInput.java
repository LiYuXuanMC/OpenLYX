package net.minecraft.util;

import cc.systemv.rave.Rave;
import cc.systemv.rave.event.EventMoveInput;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class MovementInput
{
    /**
     * The speed at which the player is strafing. Postive numbers to the left and negative to the right.
     */
    public float moveStrafe;

    /**
     * The speed at which the player is moving forward. Negative numbers will move backwards.
     */
    public float moveForward;
    public boolean jump;
    public boolean sneak;

    public void updatePlayerMoveState() {
        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;

        if (gameSettings.keyBindForward.isKeyDown()) {
            ++this.moveForward;
        }

        if (gameSettings.keyBindBack.isKeyDown()) {
            --this.moveForward;
        }

        if (gameSettings.keyBindLeft.isKeyDown()) {
            ++this.moveStrafe;
        }

        if (gameSettings.keyBindRight.isKeyDown()) {
            --this.moveStrafe;
        }

        this.jump = gameSettings.keyBindJump.isKeyDown();
        this.sneak = gameSettings.keyBindSneak.isKeyDown();

        final EventMoveInput moveInputEvent = new EventMoveInput(moveForward, moveStrafe, jump, sneak, 0.3D);

        Rave.getInstance().getEventBus().callEvent(moveInputEvent);

        final double sneakMultiplier = moveInputEvent.getSneakSlowDownMultiplier();
        this.moveForward = moveInputEvent.getForward();
        this.moveStrafe = moveInputEvent.getStrafe();
        this.jump = moveInputEvent.isJump();
        this.sneak = moveInputEvent.isSneak();

        if (this.sneak) {
            this.moveStrafe = (float) ((double) this.moveStrafe * sneakMultiplier);
            this.moveForward = (float) ((double) this.moveForward * sneakMultiplier);
        }
    }

}
