package net.minecraft.client.renderer;

import com.google.common.primitives.Floats;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.optifine.SmartAnimations;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

public class WorldRenderer
{
    private ByteBuffer byteBuffer;
    public IntBuffer rawIntBuffer;
    private ShortBuffer rawShortBuffer;
    public FloatBuffer rawFloatBuffer;
    public int vertexCount;
    private VertexFormatElement vertexFormatElement;
    private int vertexFormatIndex;

    /** Boolean for whether this renderer needs to be updated or not */
    private boolean needsUpdate;
    public int drawMode;
    private double xOffset;
    private double yOffset;
    private double zOffset;
    private VertexFormat vertexFormat;
    private boolean isDrawing;
    private EnumWorldBlockLayer blockLayer = null;
    private boolean[] drawnIcons = new boolean[256];
    private TextureAtlasSprite[] quadSprites = null;
    private TextureAtlasSprite[] quadSpritesPrev = null;
    private TextureAtlasSprite quadSprite = null;
    public SVertexBuilder sVertexBuilder;
    public RenderEnv renderEnv = null;
    public BitSet animatedSprites = null;
    public BitSet animatedSpritesCached = new BitSet();
    private boolean modeTriangles = false;
    private ByteBuffer byteBufferTriangles;

    public WorldRenderer(int bufferSizeIn)
    {
        this.byteBuffer = GLAllocation.createDirectByteBuffer(bufferSizeIn * 4);
        this.rawIntBuffer = this.byteBuffer.asIntBuffer();
        this.rawShortBuffer = this.byteBuffer.asShortBuffer();
        this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
        SVertexBuilder.initVertexBuilder(this);
    }

    private void growBuffer(int p_181670_1_)
    {
        if (p_181670_1_ > this.rawIntBuffer.remaining())
        {
            int i = this.byteBuffer.capacity();
            int j = i % 2097152;
            int k = j + (((this.rawIntBuffer.position() + p_181670_1_) * 4 - j) / 2097152 + 1) * 2097152;
            LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + i + " bytes, new size " + k + " bytes.");
            int l = this.rawIntBuffer.position();
            ByteBuffer bytebuffer = GLAllocation.createDirectByteBuffer(k);
            this.byteBuffer.position(0);
            bytebuffer.put(this.byteBuffer);
            bytebuffer.rewind();
            this.byteBuffer = bytebuffer;
            this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
            this.rawIntBuffer = this.byteBuffer.asIntBuffer();
            this.rawIntBuffer.position(l);
            this.rawShortBuffer = this.byteBuffer.asShortBuffer();
            this.rawShortBuffer.position(l << 1);

            if (this.quadSprites != null)
            {
                TextureAtlasSprite[] atextureatlassprite = this.quadSprites;
                int i1 = this.getBufferQuadSize();
                this.quadSprites = new TextureAtlasSprite[i1];
                System.arraycopy(atextureatlassprite, 0, this.quadSprites, 0, Math.min(atextureatlassprite.length, this.quadSprites.length));
                this.quadSpritesPrev = null;
            }
        }
    }

    public void sortVertexData(float p_181674_1_, float p_181674_2_, float p_181674_3_)
    {
        int i = this.vertexCount / 4;
        final float[] afloat = new float[i];

        for (int j = 0; j < i; ++j)
        {
            afloat[j] = getDistanceSq(this.rawFloatBuffer, (float)((double)p_181674_1_ + this.xOffset), (float)((double)p_181674_2_ + this.yOffset), (float)((double)p_181674_3_ + this.zOffset), this.vertexFormat.func_181719_f(), j * this.vertexFormat.getNextOffset());
        }

        Integer[] ainteger = new Integer[i];

        for (int k = 0; k < ainteger.length; ++k)
        {
            ainteger[k] = Integer.valueOf(k);
        }

        Arrays.sort(ainteger, new Comparator<Integer>()
        {
            public int compare(Integer p_compare_1_, Integer p_compare_2_)
            {
                return Floats.compare(afloat[p_compare_2_.intValue()], afloat[p_compare_1_.intValue()]);
            }
        });
        BitSet bitset = new BitSet();
        int l = this.vertexFormat.getNextOffset();
        int[] aint = new int[l];

        for (int l1 = 0; (l1 = bitset.nextClearBit(l1)) < ainteger.length; ++l1)
        {
            int i1 = ainteger[l1].intValue();

            if (i1 != l1)
            {
                this.rawIntBuffer.limit(i1 * l + l);
                this.rawIntBuffer.position(i1 * l);
                this.rawIntBuffer.get(aint);
                int j1 = i1;

                for (int k1 = ainteger[i1].intValue(); j1 != l1; k1 = ainteger[k1].intValue())
                {
                    this.rawIntBuffer.limit(k1 * l + l);
                    this.rawIntBuffer.position(k1 * l);
                    IntBuffer intbuffer = this.rawIntBuffer.slice();
                    this.rawIntBuffer.limit(j1 * l + l);
                    this.rawIntBuffer.position(j1 * l);
                    this.rawIntBuffer.put(intbuffer);
                    bitset.set(j1);
                    j1 = k1;
                }

                this.rawIntBuffer.limit(l1 * l + l);
                this.rawIntBuffer.position(l1 * l);
                this.rawIntBuffer.put(aint);
            }

            bitset.set(l1);
        }

        this.rawIntBuffer.limit(this.rawIntBuffer.capacity());
        this.rawIntBuffer.position(this.getBufferSize());

        if (this.quadSprites != null)
        {
            TextureAtlasSprite[] atextureatlassprite = new TextureAtlasSprite[this.vertexCount / 4];
            int i2 = this.vertexFormat.getNextOffset() / 4 * 4;

            for (int j2 = 0; j2 < ainteger.length; ++j2)
            {
                int k2 = ainteger[j2].intValue();
                atextureatlassprite[j2] = this.quadSprites[k2];
            }

            System.arraycopy(atextureatlassprite, 0, this.quadSprites, 0, atextureatlassprite.length);
        }
    }

    public WorldRenderer.State getVertexState()
    {
        this.rawIntBuffer.rewind();
        int i = this.getBufferSize();
        this.rawIntBuffer.limit(i);
        int[] aint = new int[i];
        this.rawIntBuffer.get(aint);
        this.rawIntBuffer.limit(this.rawIntBuffer.capacity());
        this.rawIntBuffer.position(i);
        TextureAtlasSprite[] atextureatlassprite = null;

        if (this.quadSprites != null)
        {
            int j = this.vertexCount / 4;
            atextureatlassprite = new TextureAtlasSprite[j];
            System.arraycopy(this.quadSprites, 0, atextureatlassprite, 0, j);
        }

        return new WorldRenderer.State(aint, new VertexFormat(this.vertexFormat), atextureatlassprite);
    }

    public int getBufferSize()
    {
        return this.vertexCount * this.vertexFormat.func_181719_f();
    }

    private static float getDistanceSq(FloatBuffer p_181665_0_, float p_181665_1_, float p_181665_2_, float p_181665_3_, int p_181665_4_, int p_181665_5_)
    {
        float f = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 0);
        float f1 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 1);
        float f2 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 0 + 2);
        float f3 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 0);
        float f4 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 1);
        float f5 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 1 + 2);
        float f6 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 0);
        float f7 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 1);
        float f8 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 2 + 2);
        float f9 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 0);
        float f10 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 1);
        float f11 = p_181665_0_.get(p_181665_5_ + p_181665_4_ * 3 + 2);
        float f12 = (f + f3 + f6 + f9) * 0.25F - p_181665_1_;
        float f13 = (f1 + f4 + f7 + f10) * 0.25F - p_181665_2_;
        float f14 = (f2 + f5 + f8 + f11) * 0.25F - p_181665_3_;
        return f12 * f12 + f13 * f13 + f14 * f14;
    }

    public void setVertexState(WorldRenderer.State state)
    {
        this.rawIntBuffer.clear();
        this.growBuffer(state.getRawBuffer().length);
        this.rawIntBuffer.put(state.getRawBuffer());
        this.vertexCount = state.getVertexCount();
        this.vertexFormat = new VertexFormat(state.getVertexFormat());

        if (state.stateQuadSprites != null)
        {
            if (this.quadSprites == null)
            {
                this.quadSprites = this.quadSpritesPrev;
            }

            if (this.quadSprites == null || this.quadSprites.length < this.getBufferQuadSize())
            {
                this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
            }

            TextureAtlasSprite[] atextureatlassprite = state.stateQuadSprites;
            System.arraycopy(atextureatlassprite, 0, this.quadSprites, 0, atextureatlassprite.length);
        }
        else
        {
            if (this.quadSprites != null)
            {
                this.quadSpritesPrev = this.quadSprites;
            }

            this.quadSprites = null;
        }
    }

    public void reset()
    {
        this.vertexCount = 0;
        this.vertexFormatElement = null;
        this.vertexFormatIndex = 0;
        this.quadSprite = null;

        if (SmartAnimations.isActive())
        {
            if (this.animatedSprites == null)
            {
                this.animatedSprites = this.animatedSpritesCached;
            }

            this.animatedSprites.clear();
        }
        else if (this.animatedSprites != null)
        {
            this.animatedSprites = null;
        }

        this.modeTriangles = false;
    }

    public void begin(int p_181668_1_, VertexFormat p_181668_2_)
    {
        if (this.isDrawing)
        {
            throw new IllegalStateException("Already building!");
        }
        else
        {
            this.isDrawing = true;
            this.reset();
            this.drawMode = p_181668_1_;
            this.vertexFormat = p_181668_2_;
            this.vertexFormatElement = p_181668_2_.getElement(this.vertexFormatIndex);
            this.needsUpdate = false;
            this.byteBuffer.limit(this.byteBuffer.capacity());

            if (Config.isShaders())
            {
                SVertexBuilder.endSetVertexFormat(this);
            }

            if (Config.isMultiTexture())
            {
                if (this.blockLayer != null)
                {
                    if (this.quadSprites == null)
                    {
                        this.quadSprites = this.quadSpritesPrev;
                    }

                    if (this.quadSprites == null || this.quadSprites.length < this.getBufferQuadSize())
                    {
                        this.quadSprites = new TextureAtlasSprite[this.getBufferQuadSize()];
                    }
                }
            }
            else
            {
                if (this.quadSprites != null)
                {
                    this.quadSpritesPrev = this.quadSprites;
                }

                this.quadSprites = null;
            }
        }
    }

    public WorldRenderer tex(double p_181673_1_, double p_181673_3_)
    {
        if (this.quadSprite != null && this.quadSprites != null)
        {
            p_181673_1_ = (double)this.quadSprite.toSingleU((float)p_181673_1_);
            p_181673_3_ = (double)this.quadSprite.toSingleV((float)p_181673_3_);
            this.quadSprites[this.vertexCount / 4] = this.quadSprite;
        }

        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.func_181720_d(this.vertexFormatIndex);

        switch (this.vertexFormatElement.getType())
        {
            case FLOAT:
                this.byteBuffer.putFloat(i, (float)p_181673_1_);
                this.byteBuffer.putFloat(i + 4, (float)p_181673_3_);
                break;

            case UINT:
            case INT:
                this.byteBuffer.putInt(i, (int)p_181673_1_);
                this.byteBuffer.putInt(i + 4, (int)p_181673_3_);
                break;

            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(i, (short)((int)p_181673_3_));
                this.byteBuffer.putShort(i + 2, (short)((int)p_181673_1_));
                break;

            case UBYTE:
            case BYTE:
                this.byteBuffer.put(i, (byte)((int)p_181673_3_));
                this.byteBuffer.put(i + 1, (byte)((int)p_181673_1_));
        }

        this.func_181667_k();
        return this;
    }

    public WorldRenderer lightmap(int p_181671_1_, int p_181671_2_)
    {
        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.func_181720_d(this.vertexFormatIndex);

        switch (this.vertexFormatElement.getType())
        {
            case FLOAT:
                this.byteBuffer.putFloat(i, (float)p_181671_1_);
                this.byteBuffer.putFloat(i + 4, (float)p_181671_2_);
                break;

            case UINT:
            case INT:
                this.byteBuffer.putInt(i, p_181671_1_);
                this.byteBuffer.putInt(i + 4, p_181671_2_);
                break;

            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(i, (short)p_181671_2_);
                this.byteBuffer.putShort(i + 2, (short)p_181671_1_);
                break;

            case UBYTE:
            case BYTE:
                this.byteBuffer.put(i, (byte)p_181671_2_);
                this.byteBuffer.put(i + 1, (byte)p_181671_1_);
        }

        this.func_181667_k();
        return this;
    }

    public void putBrightness4(int p_178962_1_, int p_178962_2_, int p_178962_3_, int p_178962_4_)
    {
        int i = (this.vertexCount - 4) * this.vertexFormat.func_181719_f() + this.vertexFormat.getElementOffsetById(1) / 4;
        int j = this.vertexFormat.getNextOffset() >> 2;
        this.rawIntBuffer.put(i, p_178962_1_);
        this.rawIntBuffer.put(i + j, p_178962_2_);
        this.rawIntBuffer.put(i + j * 2, p_178962_3_);
        this.rawIntBuffer.put(i + j * 3, p_178962_4_);
    }

    public void putPosition(double x, double y, double z)
    {
        int i = this.vertexFormat.func_181719_f();
        int j = (this.vertexCount - 4) * i;

        for (int k = 0; k < 4; ++k)
        {
            int l = j + k * i;
            int i1 = l + 1;
            int j1 = i1 + 1;
            this.rawIntBuffer.put(l, Float.floatToRawIntBits((float)(x + this.xOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(l))));
            this.rawIntBuffer.put(i1, Float.floatToRawIntBits((float)(y + this.yOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(i1))));
            this.rawIntBuffer.put(j1, Float.floatToRawIntBits((float)(z + this.zOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(j1))));
        }
    }

    /**
     * Takes in the pass the call list is being requested for. Args: renderPass
     */
    public int getColorIndex(int p_78909_1_)
    {
        return ((this.vertexCount - p_78909_1_) * this.vertexFormat.getNextOffset() + this.vertexFormat.getColorOffset()) / 4;
    }

    public void putColorMultiplier(float red, float green, float blue, int p_178978_4_)
    {
        int i = this.getColorIndex(p_178978_4_);
        int j = -1;

        if (!this.needsUpdate)
        {
            j = this.rawIntBuffer.get(i);

            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
            {
                int k = (int)((float)(j & 255) * red);
                int l = (int)((float)(j >> 8 & 255) * green);
                int i1 = (int)((float)(j >> 16 & 255) * blue);
                j = j & -16777216;
                j = j | i1 << 16 | l << 8 | k;
            }
            else
            {
                int j1 = (int)((float)(j >> 24 & 255) * red);
                int k1 = (int)((float)(j >> 16 & 255) * green);
                int l1 = (int)((float)(j >> 8 & 255) * blue);
                j = j & 255;
                j = j | j1 << 24 | k1 << 16 | l1 << 8;
            }
        }

        this.rawIntBuffer.put(i, j);
    }

    private void putColor(int argb, int p_178988_2_)
    {
        int i = this.getColorIndex(p_178988_2_);
        int j = argb >> 16 & 255;
        int k = argb >> 8 & 255;
        int l = argb & 255;
        int i1 = argb >> 24 & 255;
        this.putColorRGBA(i, j, k, l, i1);
    }

    public void putColorRGB_F(float red, float green, float blue, int p_178994_4_)
    {
        int i = this.getColorIndex(p_178994_4_);
        int j = MathHelper.clamp_int((int)(red * 255.0F), 0, 255);
        int k = MathHelper.clamp_int((int)(green * 255.0F), 0, 255);
        int l = MathHelper.clamp_int((int)(blue * 255.0F), 0, 255);
        this.putColorRGBA(i, j, k, l, 255);
    }

    public void putColorRGBA(int index, int red, int p_178972_3_, int p_178972_4_, int p_178972_5_)
    {
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
        {
            this.rawIntBuffer.put(index, p_178972_5_ << 24 | p_178972_4_ << 16 | p_178972_3_ << 8 | red);
        }
        else
        {
            this.rawIntBuffer.put(index, red << 24 | p_178972_3_ << 16 | p_178972_4_ << 8 | p_178972_5_);
        }
    }

    /**
     * Marks the current renderer data as dirty and needing to be updated.
     */
    public void markDirty()
    {
        this.needsUpdate = true;
    }

    public WorldRenderer color(float p_181666_1_, float p_181666_2_, float p_181666_3_, float p_181666_4_)
    {
        return this.color((int)(p_181666_1_ * 255.0F), (int)(p_181666_2_ * 255.0F), (int)(p_181666_3_ * 255.0F), (int)(p_181666_4_ * 255.0F));
    }

    public WorldRenderer color(int p_181669_1_, int p_181669_2_, int p_181669_3_, int p_181669_4_)
    {
        if (this.needsUpdate)
        {
            return this;
        }
        else
        {
            int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.func_181720_d(this.vertexFormatIndex);

            switch (this.vertexFormatElement.getType())
            {
                case FLOAT:
                    this.byteBuffer.putFloat(i, (float)p_181669_1_ / 255.0F);
                    this.byteBuffer.putFloat(i + 4, (float)p_181669_2_ / 255.0F);
                    this.byteBuffer.putFloat(i + 8, (float)p_181669_3_ / 255.0F);
                    this.byteBuffer.putFloat(i + 12, (float)p_181669_4_ / 255.0F);
                    break;

                case UINT:
                case INT:
                    this.byteBuffer.putFloat(i, (float)p_181669_1_);
                    this.byteBuffer.putFloat(i + 4, (float)p_181669_2_);
                    this.byteBuffer.putFloat(i + 8, (float)p_181669_3_);
                    this.byteBuffer.putFloat(i + 12, (float)p_181669_4_);
                    break;

                case USHORT:
                case SHORT:
                    this.byteBuffer.putShort(i, (short)p_181669_1_);
                    this.byteBuffer.putShort(i + 2, (short)p_181669_2_);
                    this.byteBuffer.putShort(i + 4, (short)p_181669_3_);
                    this.byteBuffer.putShort(i + 6, (short)p_181669_4_);
                    break;

                case UBYTE:
                case BYTE:
                    if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
                    {
                        this.byteBuffer.put(i, (byte)p_181669_1_);
                        this.byteBuffer.put(i + 1, (byte)p_181669_2_);
                        this.byteBuffer.put(i + 2, (byte)p_181669_3_);
                        this.byteBuffer.put(i + 3, (byte)p_181669_4_);
                    }
                    else
                    {
                        this.byteBuffer.put(i, (byte)p_181669_4_);
                        this.byteBuffer.put(i + 1, (byte)p_181669_3_);
                        this.byteBuffer.put(i + 2, (byte)p_181669_2_);
                        this.byteBuffer.put(i + 3, (byte)p_181669_1_);
                    }
            }

            this.func_181667_k();
            return this;
        }
    }

    public void addVertexData(int[] vertexData)
    {
        if (Config.isShaders())
        {
            SVertexBuilder.beginAddVertexData(this, vertexData);
        }

        this.growBuffer(vertexData.length);
        this.rawIntBuffer.position(this.getBufferSize());
        this.rawIntBuffer.put(vertexData);
        this.vertexCount += vertexData.length / this.vertexFormat.func_181719_f();

        if (Config.isShaders())
        {
            SVertexBuilder.endAddVertexData(this);
        }
    }

    public void endVertex()
    {
        ++this.vertexCount;
        this.growBuffer(this.vertexFormat.func_181719_f());
        this.vertexFormatIndex = 0;
        this.vertexFormatElement = this.vertexFormat.getElement(this.vertexFormatIndex);

        if (Config.isShaders())
        {
            SVertexBuilder.endAddVertex(this);
        }
    }

    public WorldRenderer pos(double p_181662_1_, double p_181662_3_, double p_181662_5_)
    {
        if (Config.isShaders())
        {
            SVertexBuilder.beginAddVertex(this);
        }

        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.func_181720_d(this.vertexFormatIndex);

        switch (this.vertexFormatElement.getType())
        {
            case FLOAT:
                this.byteBuffer.putFloat(i, (float)(p_181662_1_ + this.xOffset));
                this.byteBuffer.putFloat(i + 4, (float)(p_181662_3_ + this.yOffset));
                this.byteBuffer.putFloat(i + 8, (float)(p_181662_5_ + this.zOffset));
                break;

            case UINT:
            case INT:
                this.byteBuffer.putInt(i, Float.floatToRawIntBits((float)(p_181662_1_ + this.xOffset)));
                this.byteBuffer.putInt(i + 4, Float.floatToRawIntBits((float)(p_181662_3_ + this.yOffset)));
                this.byteBuffer.putInt(i + 8, Float.floatToRawIntBits((float)(p_181662_5_ + this.zOffset)));
                break;

            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(i, (short)((int)(p_181662_1_ + this.xOffset)));
                this.byteBuffer.putShort(i + 2, (short)((int)(p_181662_3_ + this.yOffset)));
                this.byteBuffer.putShort(i + 4, (short)((int)(p_181662_5_ + this.zOffset)));
                break;

            case UBYTE:
            case BYTE:
                this.byteBuffer.put(i, (byte)((int)(p_181662_1_ + this.xOffset)));
                this.byteBuffer.put(i + 1, (byte)((int)(p_181662_3_ + this.yOffset)));
                this.byteBuffer.put(i + 2, (byte)((int)(p_181662_5_ + this.zOffset)));
        }

        this.func_181667_k();
        return this;
    }

    public void putNormal(float x, float y, float z)
    {
        int i = (byte)((int)(x * 127.0F)) & 255;
        int j = (byte)((int)(y * 127.0F)) & 255;
        int k = (byte)((int)(z * 127.0F)) & 255;
        int l = i | j << 8 | k << 16;
        int i1 = this.vertexFormat.getNextOffset() >> 2;
        int j1 = (this.vertexCount - 4) * i1 + this.vertexFormat.getNormalOffset() / 4;
        this.rawIntBuffer.put(j1, l);
        this.rawIntBuffer.put(j1 + i1, l);
        this.rawIntBuffer.put(j1 + i1 * 2, l);
        this.rawIntBuffer.put(j1 + i1 * 3, l);
    }

    private void func_181667_k()
    {
        ++this.vertexFormatIndex;
        this.vertexFormatIndex %= this.vertexFormat.getElementCount();
        this.vertexFormatElement = this.vertexFormat.getElement(this.vertexFormatIndex);

        if (this.vertexFormatElement.getUsage() == VertexFormatElement.EnumUsage.PADDING)
        {
            this.func_181667_k();
        }
    }

    public WorldRenderer func_181663_c(float p_181663_1_, float p_181663_2_, float p_181663_3_)
    {
        int i = this.vertexCount * this.vertexFormat.getNextOffset() + this.vertexFormat.func_181720_d(this.vertexFormatIndex);

        switch (this.vertexFormatElement.getType())
        {
            case FLOAT:
                this.byteBuffer.putFloat(i, p_181663_1_);
                this.byteBuffer.putFloat(i + 4, p_181663_2_);
                this.byteBuffer.putFloat(i + 8, p_181663_3_);
                break;

            case UINT:
            case INT:
                this.byteBuffer.putInt(i, (int)p_181663_1_);
                this.byteBuffer.putInt(i + 4, (int)p_181663_2_);
                this.byteBuffer.putInt(i + 8, (int)p_181663_3_);
                break;

            case USHORT:
            case SHORT:
                this.byteBuffer.putShort(i, (short)((int)(p_181663_1_ * 32767.0F) & 65535));
                this.byteBuffer.putShort(i + 2, (short)((int)(p_181663_2_ * 32767.0F) & 65535));
                this.byteBuffer.putShort(i + 4, (short)((int)(p_181663_3_ * 32767.0F) & 65535));
                break;

            case UBYTE:
            case BYTE:
                this.byteBuffer.put(i, (byte)((int)(p_181663_1_ * 127.0F) & 255));
                this.byteBuffer.put(i + 1, (byte)((int)(p_181663_2_ * 127.0F) & 255));
                this.byteBuffer.put(i + 2, (byte)((int)(p_181663_3_ * 127.0F) & 255));
        }

        this.func_181667_k();
        return this;
    }

    public void setTranslation(double x, double y, double z)
    {
        this.xOffset = x;
        this.yOffset = y;
        this.zOffset = z;
    }

    public void finishDrawing()
    {
        if (!this.isDrawing)
        {
            throw new IllegalStateException("Not building!");
        }
        else
        {
            this.isDrawing = false;
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.getBufferSize() * 4);
        }
    }

    public ByteBuffer getByteBuffer()
    {
        return this.modeTriangles ? this.byteBufferTriangles : this.byteBuffer;
    }

    public VertexFormat getVertexFormat()
    {
        return this.vertexFormat;
    }

    public int getVertexCount()
    {
        return this.modeTriangles ? this.vertexCount / 4 * 6 : this.vertexCount;
    }

    public int getDrawMode()
    {
        return this.modeTriangles ? 4 : this.drawMode;
    }

    public void putColor4(int argb)
    {
        for (int i = 0; i < 4; ++i)
        {
            this.putColor(argb, i + 1);
        }
    }

    public void putColorRGB_F4(float red, float green, float blue)
    {
        for (int i = 0; i < 4; ++i)
        {
            this.putColorRGB_F(red, green, blue, i + 1);
        }
    }

    public void putSprite(TextureAtlasSprite p_putSprite_1_)
    {
        if (this.animatedSprites != null && p_putSprite_1_ != null && p_putSprite_1_.getAnimationIndex() >= 0)
        {
            this.animatedSprites.set(p_putSprite_1_.getAnimationIndex());
        }

        if (this.quadSprites != null)
        {
            int i = this.vertexCount / 4;
            this.quadSprites[i - 1] = p_putSprite_1_;
        }
    }

    public void setSprite(TextureAtlasSprite p_setSprite_1_)
    {
        if (this.animatedSprites != null && p_setSprite_1_ != null && p_setSprite_1_.getAnimationIndex() >= 0)
        {
            this.animatedSprites.set(p_setSprite_1_.getAnimationIndex());
        }

        if (this.quadSprites != null)
        {
            this.quadSprite = p_setSprite_1_;
        }
    }

    public boolean isMultiTexture()
    {
        return this.quadSprites != null;
    }

    public void drawMultiTexture()
    {
        if (this.quadSprites != null)
        {
            int i = Config.getMinecraft().getTextureMapBlocks().getCountRegisteredSprites();

            if (this.drawnIcons.length <= i)
            {
                this.drawnIcons = new boolean[i + 1];
            }

            Arrays.fill(this.drawnIcons, false);
            int j = 0;
            int k = -1;
            int l = this.vertexCount / 4;

            for (int i1 = 0; i1 < l; ++i1)
            {
                TextureAtlasSprite textureatlassprite = this.quadSprites[i1];

                if (textureatlassprite != null)
                {
                    int j1 = textureatlassprite.getIndexInMap();

                    if (!this.drawnIcons[j1])
                    {
                        if (textureatlassprite == TextureUtils.iconGrassSideOverlay)
                        {
                            if (k < 0)
                            {
                                k = i1;
                            }
                        }
                        else
                        {
                            i1 = this.drawForIcon(textureatlassprite, i1) - 1;
                            ++j;

                            if (this.blockLayer != EnumWorldBlockLayer.TRANSLUCENT)
                            {
                                this.drawnIcons[j1] = true;
                            }
                        }
                    }
                }
            }

            if (k >= 0)
            {
                this.drawForIcon(TextureUtils.iconGrassSideOverlay, k);
                ++j;
            }

            if (j > 0)
            {
                ;
            }
        }
    }

    private int drawForIcon(TextureAtlasSprite p_drawForIcon_1_, int p_drawForIcon_2_)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, p_drawForIcon_1_.glSpriteTextureId);
        int i = -1;
        int j = -1;
        int k = this.vertexCount / 4;

        for (int l = p_drawForIcon_2_; l < k; ++l)
        {
            TextureAtlasSprite textureatlassprite = this.quadSprites[l];

            if (textureatlassprite == p_drawForIcon_1_)
            {
                if (j < 0)
                {
                    j = l;
                }
            }
            else if (j >= 0)
            {
                this.draw(j, l);

                if (this.blockLayer == EnumWorldBlockLayer.TRANSLUCENT)
                {
                    return l;
                }

                j = -1;

                if (i < 0)
                {
                    i = l;
                }
            }
        }

        if (j >= 0)
        {
            this.draw(j, k);
        }

        if (i < 0)
        {
            i = k;
        }

        return i;
    }

    private void draw(int p_draw_1_, int p_draw_2_)
    {
        int i = p_draw_2_ - p_draw_1_;

        if (i > 0)
        {
            int j = p_draw_1_ * 4;
            int k = i * 4;
            GL11.glDrawArrays(this.drawMode, j, k);
        }
    }

    public void setBlockLayer(EnumWorldBlockLayer p_setBlockLayer_1_)
    {
        this.blockLayer = p_setBlockLayer_1_;

        if (p_setBlockLayer_1_ == null)
        {
            if (this.quadSprites != null)
            {
                this.quadSpritesPrev = this.quadSprites;
            }

            this.quadSprites = null;
            this.quadSprite = null;
        }
    }

    private int getBufferQuadSize()
    {
        int i = this.rawIntBuffer.capacity() * 4 / (this.vertexFormat.func_181719_f() * 4);
        return i;
    }

    public RenderEnv getRenderEnv(IBlockState p_getRenderEnv_1_, BlockPos p_getRenderEnv_2_)
    {
        if (this.renderEnv == null)
        {
            this.renderEnv = new RenderEnv(p_getRenderEnv_1_, p_getRenderEnv_2_);
            return this.renderEnv;
        }
        else
        {
            this.renderEnv.reset(p_getRenderEnv_1_, p_getRenderEnv_2_);
            return this.renderEnv;
        }
    }

    public boolean isDrawing()
    {
        return this.isDrawing;
    }

    public double getXOffset()
    {
        return this.xOffset;
    }

    public double getYOffset()
    {
        return this.yOffset;
    }

    public double getZOffset()
    {
        return this.zOffset;
    }

    public EnumWorldBlockLayer getBlockLayer()
    {
        return this.blockLayer;
    }

    public void putColorMultiplierRgba(float p_putColorMultiplierRgba_1_, float p_putColorMultiplierRgba_2_, float p_putColorMultiplierRgba_3_, float p_putColorMultiplierRgba_4_, int p_putColorMultiplierRgba_5_)
    {
        int i = this.getColorIndex(p_putColorMultiplierRgba_5_);
        int j = -1;

        if (!this.needsUpdate)
        {
            j = this.rawIntBuffer.get(i);

            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN)
            {
                int k = (int)((float)(j & 255) * p_putColorMultiplierRgba_1_);
                int l = (int)((float)(j >> 8 & 255) * p_putColorMultiplierRgba_2_);
                int i1 = (int)((float)(j >> 16 & 255) * p_putColorMultiplierRgba_3_);
                int j1 = (int)((float)(j >> 24 & 255) * p_putColorMultiplierRgba_4_);
                j = j1 << 24 | i1 << 16 | l << 8 | k;
            }
            else
            {
                int k1 = (int)((float)(j >> 24 & 255) * p_putColorMultiplierRgba_1_);
                int l1 = (int)((float)(j >> 16 & 255) * p_putColorMultiplierRgba_2_);
                int i2 = (int)((float)(j >> 8 & 255) * p_putColorMultiplierRgba_3_);
                int j2 = (int)((float)(j & 255) * p_putColorMultiplierRgba_4_);
                j = k1 << 24 | l1 << 16 | i2 << 8 | j2;
            }
        }

        this.rawIntBuffer.put(i, j);
    }

    public void quadsToTriangles()
    {
        if (this.drawMode == 7)
        {
            if (this.byteBufferTriangles == null)
            {
                this.byteBufferTriangles = GLAllocation.createDirectByteBuffer(this.byteBuffer.capacity() * 2);
            }

            if (this.byteBufferTriangles.capacity() < this.byteBuffer.capacity() * 2)
            {
                this.byteBufferTriangles = GLAllocation.createDirectByteBuffer(this.byteBuffer.capacity() * 2);
            }

            int i = this.vertexFormat.getNextOffset();
            int j = this.byteBuffer.limit();
            this.byteBuffer.rewind();
            this.byteBufferTriangles.clear();

            for (int k = 0; k < this.vertexCount; k += 4)
            {
                this.byteBuffer.limit((k + 3) * i);
                this.byteBuffer.position(k * i);
                this.byteBufferTriangles.put(this.byteBuffer);
                this.byteBuffer.limit((k + 1) * i);
                this.byteBuffer.position(k * i);
                this.byteBufferTriangles.put(this.byteBuffer);
                this.byteBuffer.limit((k + 2 + 2) * i);
                this.byteBuffer.position((k + 2) * i);
                this.byteBufferTriangles.put(this.byteBuffer);
            }

            this.byteBuffer.limit(j);
            this.byteBuffer.rewind();
            this.byteBufferTriangles.flip();
            this.modeTriangles = true;
        }
    }

    public boolean isColorDisabled()
    {
        return this.needsUpdate;
    }

    public class State
    {
        private final int[] stateRawBuffer;
        private final VertexFormat stateVertexFormat;
        private TextureAtlasSprite[] stateQuadSprites;

        public State(int[] p_i1_2_, VertexFormat p_i1_3_, TextureAtlasSprite[] p_i1_4_)
        {
            this.stateRawBuffer = p_i1_2_;
            this.stateVertexFormat = p_i1_3_;
            this.stateQuadSprites = p_i1_4_;
        }

        public State(int[] p_i46453_2_, VertexFormat p_i46453_3_)
        {
            this.stateRawBuffer = p_i46453_2_;
            this.stateVertexFormat = p_i46453_3_;
        }

        public int[] getRawBuffer()
        {
            return this.stateRawBuffer;
        }

        public int getVertexCount()
        {
            return this.stateRawBuffer.length / this.stateVertexFormat.func_181719_f();
        }

        public VertexFormat getVertexFormat()
        {
            return this.stateVertexFormat;
        }
    }
}
