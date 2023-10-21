package net.minecraft.client.stream;

import net.minecraft.entity.EntityLivingBase;

public class MetadataCombat extends Metadata
{
    public MetadataCombat(EntityLivingBase p_i46067_1_, EntityLivingBase p_i46067_2_)
    {
        super("player_combat");
        this.func_152808_a("player", p_i46067_1_.getCommandSenderName());

        if (p_i46067_2_ != null)
        {
            this.func_152808_a("primary_opponent", p_i46067_2_.getCommandSenderName());
        }

        if (p_i46067_2_ != null)
        {
            this.func_152807_a("Combat between " + p_i46067_1_.getCommandSenderName() + " and " + p_i46067_2_.getCommandSenderName());
        }
        else
        {
            this.func_152807_a("Combat between " + p_i46067_1_.getCommandSenderName() + " and others");
        }
    }
}
