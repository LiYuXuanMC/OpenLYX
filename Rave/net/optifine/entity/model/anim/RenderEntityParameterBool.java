package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum RenderEntityParameterBool implements IExpressionBool
{
    IS_ALIVE("is_alive"),
    IS_BURNING("is_burning"),
    IS_CHILD("is_child"),
    IS_GLOWING("is_glowing"),
    IS_HURT("is_hurt"),
    IS_IN_LAVA("is_in_lava"),
    IS_IN_WATER("is_in_water"),
    IS_INVISIBLE("is_invisible"),
    IS_ON_GROUND("is_on_ground"),
    IS_RIDDEN("is_ridden"),
    IS_RIDING("is_riding"),
    IS_SNEAKING("is_sneaking"),
    IS_SPRINTING("is_sprinting"),
    IS_WET("is_wet");

    private String name;
    private RenderManager renderManager;
    private static final RenderEntityParameterBool[] VALUES = values();

    private RenderEntityParameterBool(String name)
    {
        this.name = name;
        this.renderManager = Minecraft.getMinecraft().getRenderManager();
    }

    public String getName()
    {
        return this.name;
    }

    public ExpressionType getExpressionType()
    {
        return ExpressionType.BOOL;
    }

    public boolean eval()
    {
        Render render = this.renderManager.renderRender;

        if (render == null)
        {
            return false;
        }
        else
        {
            if (render instanceof RendererLivingEntity)
            {
                RendererLivingEntity rendererlivingentity = (RendererLivingEntity)render;
                EntityLivingBase entitylivingbase = rendererlivingentity.renderEntity;

                if (entitylivingbase == null)
                {
                    return false;
                }

                switch (this)
                {
                    case IS_ALIVE:
                        return entitylivingbase.isEntityAlive();

                    case IS_BURNING:
                        return entitylivingbase.isBurning();

                    case IS_CHILD:
                        return entitylivingbase.isChild();

                    case IS_HURT:
                        return entitylivingbase.hurtTime > 0;

                    case IS_IN_LAVA:
                        return entitylivingbase.isInLava();

                    case IS_IN_WATER:
                        return entitylivingbase.isInWater();

                    case IS_INVISIBLE:
                        return entitylivingbase.isInvisible();

                    case IS_ON_GROUND:
                        return entitylivingbase.onGround;

                    case IS_RIDDEN:
                        return entitylivingbase.riddenByEntity != null;

                    case IS_RIDING:
                        return entitylivingbase.isRiding();

                    case IS_SNEAKING:
                        return entitylivingbase.isSneaking();

                    case IS_SPRINTING:
                        return entitylivingbase.isSprinting();

                    case IS_WET:
                        return entitylivingbase.isWet();
                }
            }

            return false;
        }
    }

    public static RenderEntityParameterBool parse(String str)
    {
        if (str == null)
        {
            return null;
        }
        else
        {
            for (int i = 0; i < VALUES.length; ++i)
            {
                RenderEntityParameterBool renderentityparameterbool = VALUES[i];

                if (renderentityparameterbool.getName().equals(str))
                {
                    return renderentityparameterbool;
                }
            }

            return null;
        }
    }
}
