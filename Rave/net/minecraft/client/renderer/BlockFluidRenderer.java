package net.minecraft.client.renderer;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.optifine.CustomColors;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;

public class BlockFluidRenderer
{
    private TextureAtlasSprite[] atlasSpritesLava = new TextureAtlasSprite[2];
    private TextureAtlasSprite[] atlasSpritesWater = new TextureAtlasSprite[2];

    public BlockFluidRenderer()
    {
        this.initAtlasSprites();
    }

    protected void initAtlasSprites()
    {
        TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
        this.atlasSpritesLava[0] = texturemap.getAtlasSprite("minecraft:blocks/lava_still");
        this.atlasSpritesLava[1] = texturemap.getAtlasSprite("minecraft:blocks/lava_flow");
        this.atlasSpritesWater[0] = texturemap.getAtlasSprite("minecraft:blocks/water_still");
        this.atlasSpritesWater[1] = texturemap.getAtlasSprite("minecraft:blocks/water_flow");
    }

    public boolean renderFluid(IBlockAccess blockAccess, IBlockState blockStateIn, BlockPos blockPosIn, WorldRenderer worldRendererIn)
    {
        boolean flag2;

        try
        {
            if (Config.isShaders())
            {
                SVertexBuilder.pushEntity(blockStateIn, blockPosIn, blockAccess, worldRendererIn);
            }

            BlockLiquid blockliquid = (BlockLiquid)blockStateIn.getBlock();
            blockliquid.setBlockBoundsBasedOnState(blockAccess, blockPosIn);
            TextureAtlasSprite[] atextureatlassprite = blockliquid.getMaterial() == Material.lava ? this.atlasSpritesLava : this.atlasSpritesWater;
            RenderEnv renderenv = worldRendererIn.getRenderEnv(blockStateIn, blockPosIn);
            int i = CustomColors.getFluidColor(blockAccess, blockStateIn, blockPosIn, renderenv);
            float f = (float)(i >> 16 & 255) / 255.0F;
            float f1 = (float)(i >> 8 & 255) / 255.0F;
            float f2 = (float)(i & 255) / 255.0F;
            boolean flag = blockliquid.shouldSideBeRendered(blockAccess, blockPosIn.up(), EnumFacing.UP);
            boolean flag1 = blockliquid.shouldSideBeRendered(blockAccess, blockPosIn.down(), EnumFacing.DOWN);
            boolean[] aboolean = renderenv.getBorderFlags();
            aboolean[0] = blockliquid.shouldSideBeRendered(blockAccess, blockPosIn.north(), EnumFacing.NORTH);
            aboolean[1] = blockliquid.shouldSideBeRendered(blockAccess, blockPosIn.south(), EnumFacing.SOUTH);
            aboolean[2] = blockliquid.shouldSideBeRendered(blockAccess, blockPosIn.west(), EnumFacing.WEST);
            aboolean[3] = blockliquid.shouldSideBeRendered(blockAccess, blockPosIn.east(), EnumFacing.EAST);

            if (flag || flag1 || aboolean[0] || aboolean[1] || aboolean[2] || aboolean[3])
            {
                flag2 = false;
                float f3 = 0.5F;
                float f4 = 1.0F;
                float f5 = 0.8F;
                float f6 = 0.6F;
                Material material = blockliquid.getMaterial();
                float f7 = this.getFluidHeight(blockAccess, blockPosIn, material);
                float f8 = this.getFluidHeight(blockAccess, blockPosIn.south(), material);
                float f9 = this.getFluidHeight(blockAccess, blockPosIn.east().south(), material);
                float f10 = this.getFluidHeight(blockAccess, blockPosIn.east(), material);
                double d0 = (double)blockPosIn.getX();
                double d1 = (double)blockPosIn.getY();
                double d2 = (double)blockPosIn.getZ();
                float f11 = 0.001F;

                if (flag)
                {
                    flag2 = true;
                    TextureAtlasSprite textureatlassprite = atextureatlassprite[0];
                    float f12 = (float)BlockLiquid.getFlowDirection(blockAccess, blockPosIn, material);

                    if (f12 > -999.0F)
                    {
                        textureatlassprite = atextureatlassprite[1];
                    }

                    worldRendererIn.setSprite(textureatlassprite);
                    f7 -= f11;
                    f8 -= f11;
                    f9 -= f11;
                    f10 -= f11;
                    float f13;
                    float f14;
                    float f15;
                    float f16;
                    float f17;
                    float f18;
                    float f19;
                    float f20;

                    if (f12 < -999.0F)
                    {
                        f13 = textureatlassprite.getInterpolatedU(0.0D);
                        f17 = textureatlassprite.getInterpolatedV(0.0D);
                        f14 = f13;
                        f18 = textureatlassprite.getInterpolatedV(16.0D);
                        f15 = textureatlassprite.getInterpolatedU(16.0D);
                        f19 = f18;
                        f16 = f15;
                        f20 = f17;
                    }
                    else
                    {
                        float f21 = MathHelper.sin(f12) * 0.25F;
                        float f22 = MathHelper.cos(f12) * 0.25F;
                        float f23 = 8.0F;
                        f13 = textureatlassprite.getInterpolatedU((double)(8.0F + (-f22 - f21) * 16.0F));
                        f17 = textureatlassprite.getInterpolatedV((double)(8.0F + (-f22 + f21) * 16.0F));
                        f14 = textureatlassprite.getInterpolatedU((double)(8.0F + (-f22 + f21) * 16.0F));
                        f18 = textureatlassprite.getInterpolatedV((double)(8.0F + (f22 + f21) * 16.0F));
                        f15 = textureatlassprite.getInterpolatedU((double)(8.0F + (f22 + f21) * 16.0F));
                        f19 = textureatlassprite.getInterpolatedV((double)(8.0F + (f22 - f21) * 16.0F));
                        f16 = textureatlassprite.getInterpolatedU((double)(8.0F + (f22 - f21) * 16.0F));
                        f20 = textureatlassprite.getInterpolatedV((double)(8.0F + (-f22 - f21) * 16.0F));
                    }

                    int k2 = blockliquid.getMixedBrightnessForBlock(blockAccess, blockPosIn);
                    int l2 = k2 >> 16 & 65535;
                    int i3 = k2 & 65535;
                    float f24 = f4 * f;
                    float f25 = f4 * f1;
                    float f26 = f4 * f2;
                    worldRendererIn.pos(d0 + 0.0D, d1 + (double)f7, d2 + 0.0D).color(f24, f25, f26, 1.0F).tex((double)f13, (double)f17).lightmap(l2, i3).endVertex();
                    worldRendererIn.pos(d0 + 0.0D, d1 + (double)f8, d2 + 1.0D).color(f24, f25, f26, 1.0F).tex((double)f14, (double)f18).lightmap(l2, i3).endVertex();
                    worldRendererIn.pos(d0 + 1.0D, d1 + (double)f9, d2 + 1.0D).color(f24, f25, f26, 1.0F).tex((double)f15, (double)f19).lightmap(l2, i3).endVertex();
                    worldRendererIn.pos(d0 + 1.0D, d1 + (double)f10, d2 + 0.0D).color(f24, f25, f26, 1.0F).tex((double)f16, (double)f20).lightmap(l2, i3).endVertex();

                    if (blockliquid.func_176364_g(blockAccess, blockPosIn.up()))
                    {
                        worldRendererIn.pos(d0 + 0.0D, d1 + (double)f7, d2 + 0.0D).color(f24, f25, f26, 1.0F).tex((double)f13, (double)f17).lightmap(l2, i3).endVertex();
                        worldRendererIn.pos(d0 + 1.0D, d1 + (double)f10, d2 + 0.0D).color(f24, f25, f26, 1.0F).tex((double)f16, (double)f20).lightmap(l2, i3).endVertex();
                        worldRendererIn.pos(d0 + 1.0D, d1 + (double)f9, d2 + 1.0D).color(f24, f25, f26, 1.0F).tex((double)f15, (double)f19).lightmap(l2, i3).endVertex();
                        worldRendererIn.pos(d0 + 0.0D, d1 + (double)f8, d2 + 1.0D).color(f24, f25, f26, 1.0F).tex((double)f14, (double)f18).lightmap(l2, i3).endVertex();
                    }
                }

                if (flag1)
                {
                    worldRendererIn.setSprite(atextureatlassprite[0]);
                    float f35 = atextureatlassprite[0].getMinU();
                    float f36 = atextureatlassprite[0].getMaxU();
                    float f37 = atextureatlassprite[0].getMinV();
                    float f38 = atextureatlassprite[0].getMaxV();
                    int l1 = blockliquid.getMixedBrightnessForBlock(blockAccess, blockPosIn.down());
                    int i2 = l1 >> 16 & 65535;
                    int j2 = l1 & 65535;
                    float f41 = FaceBakery.getFaceBrightness(EnumFacing.DOWN);
                    worldRendererIn.pos(d0, d1, d2 + 1.0D).color(f * f41, f1 * f41, f2 * f41, 1.0F).tex((double)f35, (double)f38).lightmap(i2, j2).endVertex();
                    worldRendererIn.pos(d0, d1, d2).color(f * f41, f1 * f41, f2 * f41, 1.0F).tex((double)f35, (double)f37).lightmap(i2, j2).endVertex();
                    worldRendererIn.pos(d0 + 1.0D, d1, d2).color(f * f41, f1 * f41, f2 * f41, 1.0F).tex((double)f36, (double)f37).lightmap(i2, j2).endVertex();
                    worldRendererIn.pos(d0 + 1.0D, d1, d2 + 1.0D).color(f * f41, f1 * f41, f2 * f41, 1.0F).tex((double)f36, (double)f38).lightmap(i2, j2).endVertex();
                    flag2 = true;
                }

                for (int i1 = 0; i1 < 4; ++i1)
                {
                    int j1 = 0;
                    int k1 = 0;

                    if (i1 == 0)
                    {
                        --k1;
                    }

                    if (i1 == 1)
                    {
                        ++k1;
                    }

                    if (i1 == 2)
                    {
                        --j1;
                    }

                    if (i1 == 3)
                    {
                        ++j1;
                    }

                    BlockPos blockpos = blockPosIn.add(j1, 0, k1);
                    TextureAtlasSprite textureatlassprite1 = atextureatlassprite[1];
                    worldRendererIn.setSprite(textureatlassprite1);

                    if (aboolean[i1])
                    {
                        float f39;
                        float f40;
                        double d3;
                        double d4;
                        double d5;
                        double d6;

                        if (i1 == 0)
                        {
                            f39 = f7;
                            f40 = f10;
                            d3 = d0;
                            d5 = d0 + 1.0D;
                            d4 = d2 + (double)f11;
                            d6 = d2 + (double)f11;
                        }
                        else if (i1 == 1)
                        {
                            f39 = f9;
                            f40 = f8;
                            d3 = d0 + 1.0D;
                            d5 = d0;
                            d4 = d2 + 1.0D - (double)f11;
                            d6 = d2 + 1.0D - (double)f11;
                        }
                        else if (i1 == 2)
                        {
                            f39 = f8;
                            f40 = f7;
                            d3 = d0 + (double)f11;
                            d5 = d0 + (double)f11;
                            d4 = d2 + 1.0D;
                            d6 = d2;
                        }
                        else
                        {
                            f39 = f10;
                            f40 = f9;
                            d3 = d0 + 1.0D - (double)f11;
                            d5 = d0 + 1.0D - (double)f11;
                            d4 = d2;
                            d6 = d2 + 1.0D;
                        }

                        flag2 = true;
                        float f42 = textureatlassprite1.getInterpolatedU(0.0D);
                        float f27 = textureatlassprite1.getInterpolatedU(8.0D);
                        float f28 = textureatlassprite1.getInterpolatedV((double)((1.0F - f39) * 16.0F * 0.5F));
                        float f29 = textureatlassprite1.getInterpolatedV((double)((1.0F - f40) * 16.0F * 0.5F));
                        float f30 = textureatlassprite1.getInterpolatedV(8.0D);
                        int j = blockliquid.getMixedBrightnessForBlock(blockAccess, blockpos);
                        int k = j >> 16 & 65535;
                        int l = j & 65535;
                        float f31 = i1 < 2 ? FaceBakery.getFaceBrightness(EnumFacing.NORTH) : FaceBakery.getFaceBrightness(EnumFacing.WEST);
                        float f32 = f4 * f31 * f;
                        float f33 = f4 * f31 * f1;
                        float f34 = f4 * f31 * f2;
                        worldRendererIn.pos(d3, d1 + (double)f39, d4).color(f32, f33, f34, 1.0F).tex((double)f42, (double)f28).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d5, d1 + (double)f40, d6).color(f32, f33, f34, 1.0F).tex((double)f27, (double)f29).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d5, d1 + 0.0D, d6).color(f32, f33, f34, 1.0F).tex((double)f27, (double)f30).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d3, d1 + 0.0D, d4).color(f32, f33, f34, 1.0F).tex((double)f42, (double)f30).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d3, d1 + 0.0D, d4).color(f32, f33, f34, 1.0F).tex((double)f42, (double)f30).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d5, d1 + 0.0D, d6).color(f32, f33, f34, 1.0F).tex((double)f27, (double)f30).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d5, d1 + (double)f40, d6).color(f32, f33, f34, 1.0F).tex((double)f27, (double)f29).lightmap(k, l).endVertex();
                        worldRendererIn.pos(d3, d1 + (double)f39, d4).color(f32, f33, f34, 1.0F).tex((double)f42, (double)f28).lightmap(k, l).endVertex();
                    }
                }

                worldRendererIn.setSprite((TextureAtlasSprite)null);
                boolean flag3 = flag2;
                return flag3;
            }

            flag2 = false;
        }
        finally
        {
            if (Config.isShaders())
            {
                SVertexBuilder.popEntity(worldRendererIn);
            }
        }

        return flag2;
    }

    private float getFluidHeight(IBlockAccess blockAccess, BlockPos blockPosIn, Material blockMaterial)
    {
        int i = 0;
        float f = 0.0F;

        for (int j = 0; j < 4; ++j)
        {
            BlockPos blockpos = blockPosIn.add(-(j & 1), 0, -(j >> 1 & 1));

            if (blockAccess.getBlockState(blockpos.up()).getBlock().getMaterial() == blockMaterial)
            {
                return 1.0F;
            }

            IBlockState iblockstate = blockAccess.getBlockState(blockpos);
            Material material = iblockstate.getBlock().getMaterial();

            if (material != blockMaterial)
            {
                if (!material.isSolid())
                {
                    ++f;
                    ++i;
                }
            }
            else
            {
                int k = ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue();

                if (k >= 8 || k == 0)
                {
                    f += BlockLiquid.getLiquidHeightPercent(k) * 10.0F;
                    i += 10;
                }

                f += BlockLiquid.getLiquidHeightPercent(k);
                ++i;
            }
        }

        return 1.0F - f / (float)i;
    }
}
