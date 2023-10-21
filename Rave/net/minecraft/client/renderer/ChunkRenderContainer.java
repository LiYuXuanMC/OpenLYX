package net.minecraft.client.renderer;

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.SmartAnimations;

public abstract class ChunkRenderContainer
{
    private double viewEntityX;
    private double viewEntityY;
    private double viewEntityZ;
    protected List<RenderChunk> renderChunks = Lists.<RenderChunk>newArrayListWithCapacity(17424);
    protected boolean initialized;
    private BitSet animatedSpritesRendered;
    private final BitSet animatedSpritesCached = new BitSet();

    public void initialize(double viewEntityXIn, double viewEntityYIn, double viewEntityZIn)
    {
        this.initialized = true;
        this.renderChunks.clear();
        this.viewEntityX = viewEntityXIn;
        this.viewEntityY = viewEntityYIn;
        this.viewEntityZ = viewEntityZIn;

        if (SmartAnimations.isActive())
        {
            if (this.animatedSpritesRendered != null)
            {
                SmartAnimations.spritesRendered(this.animatedSpritesRendered);
            }
            else
            {
                this.animatedSpritesRendered = this.animatedSpritesCached;
            }

            this.animatedSpritesRendered.clear();
        }
        else if (this.animatedSpritesRendered != null)
        {
            SmartAnimations.spritesRendered(this.animatedSpritesRendered);
            this.animatedSpritesRendered = null;
        }
    }

    public void preRenderChunk(RenderChunk renderChunkIn)
    {
        BlockPos blockpos = renderChunkIn.getPosition();
        GlStateManager.translate((float)((double)blockpos.getX() - this.viewEntityX), (float)((double)blockpos.getY() - this.viewEntityY), (float)((double)blockpos.getZ() - this.viewEntityZ));
    }

    public void addRenderChunk(RenderChunk renderChunkIn, EnumWorldBlockLayer layer)
    {
        this.renderChunks.add(renderChunkIn);

        if (this.animatedSpritesRendered != null)
        {
            BitSet bitset = renderChunkIn.compiledChunk.getAnimatedSprites(layer);

            if (bitset != null)
            {
                this.animatedSpritesRendered.or(bitset);
            }
        }
    }

    public abstract void renderChunkLayer(EnumWorldBlockLayer layer);
}
