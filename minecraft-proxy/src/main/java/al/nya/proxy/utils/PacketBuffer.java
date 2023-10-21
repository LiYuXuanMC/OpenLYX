package al.nya.proxy.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ByteProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class PacketBuffer{
    private final ByteBuf buf;

    public PacketBuffer(ByteBuf p_i45154_1_) {
        this.buf = p_i45154_1_;
    }

    @Override
    protected void finalize() throws Throwable {
        this.buf.release();
    }

    public static int getVarIntSize(int p_getVarIntSize_0_) {
        for(int lvt_1_1_ = 1; lvt_1_1_ < 5; ++lvt_1_1_) {
            if ((p_getVarIntSize_0_ & -1 << lvt_1_1_ * 7) == 0) {
                return lvt_1_1_;
            }
        }

        return 5;
    }

    public void writeByteArray(byte[] p_writeByteArray_1_) {
        this.writeVarIntToBuffer(p_writeByteArray_1_.length);
        this.writeBytes(p_writeByteArray_1_);
    }

    public byte[] readByteArray() {
        byte[] lvt_1_1_ = new byte[this.readVarIntFromBuffer()];
        this.readBytes(lvt_1_1_);
        return lvt_1_1_;
    }

//    public BlockPos readBlockPos() {
//        return BlockPos.fromLong(this.readLong());
//    }
//
//    public void writeBlockPos(BlockPos p_writeBlockPos_1_) {
//        this.writeLong(p_writeBlockPos_1_.toLong());
//    }
//
//    public IChatComponent readChatComponent() throws IOException {
//        return Serializer.jsonToComponent(this.readStringFromBuffer(32767));
//    }
//
//    public void writeChatComponent(IChatComponent p_writeChatComponent_1_) throws IOException {
//        this.writeString(Serializer.componentToJson(p_writeChatComponent_1_));
//    }
//
//    public <T extends Enum<T>> T readEnumValue(Class<T> p_readEnumValue_1_) {
//        return ((Enum[])p_readEnumValue_1_.getEnumConstants())[this.readVarIntFromBuffer()];
//    }

    public void writeEnumValue(Enum<?> p_writeEnumValue_1_) {
        this.writeVarIntToBuffer(p_writeEnumValue_1_.ordinal());
    }

    public int readVarIntFromBuffer() {
        int lvt_1_1_ = 0;
        int lvt_2_1_ = 0;

        byte lvt_3_1_;
        do {
            lvt_3_1_ = this.readByte();
            lvt_1_1_ |= (lvt_3_1_ & 127) << lvt_2_1_++ * 7;
            if (lvt_2_1_ > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while((lvt_3_1_ & 128) == 128);

        return lvt_1_1_;
    }

    public long readVarLong() {
        long lvt_1_1_ = 0L;
        int lvt_3_1_ = 0;

        byte lvt_4_1_;
        do {
            lvt_4_1_ = this.readByte();
            lvt_1_1_ |= (long)(lvt_4_1_ & 127) << lvt_3_1_++ * 7;
            if (lvt_3_1_ > 10) {
                throw new RuntimeException("VarLong too big");
            }
        } while((lvt_4_1_ & 128) == 128);

        return lvt_1_1_;
    }

    public void writeUuid(UUID p_writeUuid_1_) {
        this.writeLong(p_writeUuid_1_.getMostSignificantBits());
        this.writeLong(p_writeUuid_1_.getLeastSignificantBits());
    }

    public UUID readUuid() {
        return new UUID(this.readLong(), this.readLong());
    }

    public void writeVarIntToBuffer(int p_writeVarIntToBuffer_1_) {
        while((p_writeVarIntToBuffer_1_ & -128) != 0) {
            this.writeByte(p_writeVarIntToBuffer_1_ & 127 | 128);
            p_writeVarIntToBuffer_1_ >>>= 7;
        }

        this.writeByte(p_writeVarIntToBuffer_1_);
    }

    public void writeVarLong(long p_writeVarLong_1_) {
        while((p_writeVarLong_1_ & -128L) != 0L) {
            this.writeByte((int)(p_writeVarLong_1_ & 127L) | 128);
            p_writeVarLong_1_ >>>= 7;
        }

        this.writeByte((int)p_writeVarLong_1_);
    }

//    public void writeNBTTagCompoundToBuffer(NBTTagCompound p_writeNBTTagCompoundToBuffer_1_) {
//        if (p_writeNBTTagCompoundToBuffer_1_ == null) {
//            this.writeByte(0);
//        } else {
//            try {
//                CompressedStreamTools.write(p_writeNBTTagCompoundToBuffer_1_, new ByteBufOutputStream(this));
//            } catch (IOException var3) {
//                throw new EncoderException(var3);
//            }
//        }
//
//    }
//
//    public NBTTagCompound readNBTTagCompoundFromBuffer() throws IOException {
//        int lvt_1_1_ = this.readerIndex();
//        byte lvt_2_1_ = this.readByte();
//        if (lvt_2_1_ == 0) {
//            return null;
//        } else {
//            this.readerIndex(lvt_1_1_);
//            return CompressedStreamTools.read(new ByteBufInputStream(this), new NBTSizeTracker(2097152L));
//        }
//    }
//
//    public void writeItemStackToBuffer(ItemStack p_writeItemStackToBuffer_1_) {
//        if (p_writeItemStackToBuffer_1_ == null) {
//            this.writeShort(-1);
//        } else {
//            this.writeShort(Item.getIdFromItem(p_writeItemStackToBuffer_1_.getItem()));
//            this.writeByte(p_writeItemStackToBuffer_1_.stackSize);
//            this.writeShort(p_writeItemStackToBuffer_1_.getMetadata());
//            NBTTagCompound lvt_2_1_ = null;
//            if (p_writeItemStackToBuffer_1_.getItem().isDamageable() || p_writeItemStackToBuffer_1_.getItem().getShareTag()) {
//                lvt_2_1_ = p_writeItemStackToBuffer_1_.getTagCompound();
//            }
//
//            this.writeNBTTagCompoundToBuffer(lvt_2_1_);
//        }
//
//    }
//
//    public ItemStack readItemStackFromBuffer() throws IOException {
//        ItemStack lvt_1_1_ = null;
//        int lvt_2_1_ = this.readShort();
//        if (lvt_2_1_ >= 0) {
//            int lvt_3_1_ = this.readByte();
//            int lvt_4_1_ = this.readShort();
//            lvt_1_1_ = new ItemStack(Item.getItemById(lvt_2_1_), lvt_3_1_, lvt_4_1_);
//            lvt_1_1_.setTagCompound(this.readNBTTagCompoundFromBuffer());
//        }
//
//        return lvt_1_1_;
//    }

    public String readStringFromBuffer(int p_readStringFromBuffer_1_) {
        int lvt_2_1_ = this.readVarIntFromBuffer();
        if (lvt_2_1_ > p_readStringFromBuffer_1_ * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + lvt_2_1_ + " > " + p_readStringFromBuffer_1_ * 4 + ")");
        } else if (lvt_2_1_ < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        } else {
            byte[] lvt_3a_1_ = new byte[lvt_2_1_];
            readBytes(lvt_3a_1_); //Forge: Correctly read the given amount of bytes.
            String lvt_3_1_ = new String(lvt_3a_1_, StandardCharsets.UTF_8);
            if (lvt_3_1_.length() > p_readStringFromBuffer_1_) {
                throw new DecoderException("The received string length is longer than maximum allowed (" + lvt_2_1_ + " > " + p_readStringFromBuffer_1_ + ")");
            } else {
                return lvt_3_1_;
            }
        }
    }

    public PacketBuffer writeString(String p_writeString_1_) {
        byte[] lvt_2_1_ = p_writeString_1_.getBytes(StandardCharsets.UTF_8);
        if (lvt_2_1_.length > 32767) {
            throw new EncoderException("String too big (was " + p_writeString_1_.length() + " bytes encoded, max " + 32767 + ")");
        } else {
            this.writeVarIntToBuffer(lvt_2_1_.length);
            this.writeBytes(lvt_2_1_);
            return this;
        }
    }

    public int capacity() {
        return this.buf.capacity();
    }

    public ByteBuf capacity(int p_capacity_1_) {
        return this.buf.capacity(p_capacity_1_);
    }

    public int maxCapacity() {
        return this.buf.maxCapacity();
    }

    public ByteBufAllocator alloc() {
        return this.buf.alloc();
    }

    public ByteOrder order() {
        return this.buf.order();
    }

    public ByteBuf order(ByteOrder p_order_1_) {
        return this.buf.order(p_order_1_);
    }

    public ByteBuf unwrap() {
        return this.buf.unwrap();
    }

    public boolean isDirect() {
        return this.buf.isDirect();
    }
    
    public boolean isReadOnly() {
        return this.buf.isReadOnly();
    }

    public ByteBuf asReadOnly() {
        return this.buf.asReadOnly();
    }

    public int readerIndex() {
        return this.buf.readerIndex();
    }

    public ByteBuf readerIndex(int p_readerIndex_1_) {
        return this.buf.readerIndex(p_readerIndex_1_);
    }

    public int writerIndex() {
        return this.buf.writerIndex();
    }

    public ByteBuf writerIndex(int p_writerIndex_1_) {
        return this.buf.writerIndex(p_writerIndex_1_);
    }

    public ByteBuf setIndex(int p_setIndex_1_, int p_setIndex_2_) {
        return this.buf.setIndex(p_setIndex_1_, p_setIndex_2_);
    }

    public int readableBytes() {
        return this.buf.readableBytes();
    }

    public int writableBytes() {
        return this.buf.writableBytes();
    }

    public int maxWritableBytes() {
        return this.buf.maxWritableBytes();
    }

    public boolean isReadable() {
        return this.buf.isReadable();
    }

    public boolean isReadable(int p_isReadable_1_) {
        return this.buf.isReadable(p_isReadable_1_);
    }

    public boolean isWritable() {
        return this.buf.isWritable();
    }

    public boolean isWritable(int p_isWritable_1_) {
        return this.buf.isWritable(p_isWritable_1_);
    }

    public ByteBuf clear() {
        return this.buf.clear();
    }

    public ByteBuf markReaderIndex() {
        return this.buf.markReaderIndex();
    }

    public ByteBuf resetReaderIndex() {
        return this.buf.resetReaderIndex();
    }

    public ByteBuf markWriterIndex() {
        return this.buf.markWriterIndex();
    }

    public ByteBuf resetWriterIndex() {
        return this.buf.resetWriterIndex();
    }

    public ByteBuf discardReadBytes() {
        return this.buf.discardReadBytes();
    }

    public ByteBuf discardSomeReadBytes() {
        return this.buf.discardSomeReadBytes();
    }

    public ByteBuf ensureWritable(int p_ensureWritable_1_) {
        return this.buf.ensureWritable(p_ensureWritable_1_);
    }

    public int ensureWritable(int p_ensureWritable_1_, boolean p_ensureWritable_2_) {
        return this.buf.ensureWritable(p_ensureWritable_1_, p_ensureWritable_2_);
    }

    public boolean getBoolean(int p_getBoolean_1_) {
        return this.buf.getBoolean(p_getBoolean_1_);
    }

    public byte getByte(int p_getByte_1_) {
        return this.buf.getByte(p_getByte_1_);
    }

    public short getUnsignedByte(int p_getUnsignedByte_1_) {
        return this.buf.getUnsignedByte(p_getUnsignedByte_1_);
    }

    public short getShort(int p_getShort_1_) {
        return this.buf.getShort(p_getShort_1_);
    }
    
    public short getShortLE(int index) {
        return this.buf.getShortLE(index);
    }

    public int getUnsignedShort(int p_getUnsignedShort_1_) {
        return this.buf.getUnsignedShort(p_getUnsignedShort_1_);
    }
    
    public int getUnsignedShortLE(int index) {
        return this.buf.getUnsignedShortLE(index);
    }

    public int getMedium(int p_getMedium_1_) {
        return this.buf.getMedium(p_getMedium_1_);
    }
    
    public int getMediumLE(int index) {
        return this.buf.getMediumLE(index);
    }

    public int getUnsignedMedium(int p_getUnsignedMedium_1_) {
        return this.buf.getUnsignedMedium(p_getUnsignedMedium_1_);
    }
    
    public int getUnsignedMediumLE(int index) {
        return this.buf.getUnsignedMediumLE(index);
    }

    public int getInt(int p_getInt_1_) {
        return this.buf.getInt(p_getInt_1_);
    }

    
    public int getIntLE(int index) {
        return this.buf.getIntLE(index);
    }

    public long getUnsignedInt(int p_getUnsignedInt_1_) {
        return this.buf.getUnsignedInt(p_getUnsignedInt_1_);
    }

    
    public long getUnsignedIntLE(int index) {
        return this.buf.getUnsignedIntLE(index);
    }

    public long getLong(int p_getLong_1_) {
        return this.buf.getLong(p_getLong_1_);
    }

    
    public long getLongLE(int index) {
        return this.buf.getLongLE(index);
    }

    public char getChar(int p_getChar_1_) {
        return this.buf.getChar(p_getChar_1_);
    }

    public float getFloat(int p_getFloat_1_) {
        return this.buf.getFloat(p_getFloat_1_);
    }

    public double getDouble(int p_getDouble_1_) {
        return this.buf.getDouble(p_getDouble_1_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_) {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_, int p_getBytes_3_) {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_, int p_getBytes_3_, int p_getBytes_4_) {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_4_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, byte[] p_getBytes_2_) {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, byte[] p_getBytes_2_, int p_getBytes_3_, int p_getBytes_4_) {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_4_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, ByteBuffer p_getBytes_2_) {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_);
    }

    public ByteBuf getBytes(int p_getBytes_1_, OutputStream p_getBytes_2_, int p_getBytes_3_) throws IOException {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
    }

    public int getBytes(int p_getBytes_1_, GatheringByteChannel p_getBytes_2_, int p_getBytes_3_) throws IOException {
        return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
    }

    
    public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
        return this.buf.getBytes(index, out, position, length);
    }

    
    public CharSequence getCharSequence(int index, int length, Charset charset) {
        return this.buf.getCharSequence(index, length, charset);
    }

    public ByteBuf setBoolean(int p_setBoolean_1_, boolean p_setBoolean_2_) {
        return this.buf.setBoolean(p_setBoolean_1_, p_setBoolean_2_);
    }

    public ByteBuf setByte(int p_setByte_1_, int p_setByte_2_) {
        return this.buf.setByte(p_setByte_1_, p_setByte_2_);
    }

    public ByteBuf setShort(int p_setShort_1_, int p_setShort_2_) {
        return this.buf.setShort(p_setShort_1_, p_setShort_2_);
    }

    
    public ByteBuf setShortLE(int index, int value) {
        return this.buf.setShortLE(index, value);
    }

    public ByteBuf setMedium(int p_setMedium_1_, int p_setMedium_2_) {
        return this.buf.setMedium(p_setMedium_1_, p_setMedium_2_);
    }

    
    public ByteBuf setMediumLE(int index, int value) {
        return this.buf.setMediumLE(index, value);
    }

    public ByteBuf setInt(int p_setInt_1_, int p_setInt_2_) {
        return this.buf.setInt(p_setInt_1_, p_setInt_2_);
    }

    
    public ByteBuf setIntLE(int index, int value) {
        return this.buf.setIntLE(index, value);
    }

    public ByteBuf setLong(int p_setLong_1_, long p_setLong_2_) {
        return this.buf.setLong(p_setLong_1_, p_setLong_2_);
    }

    
    public ByteBuf setLongLE(int index, long value) {
        return this.buf.setLongLE(index, value);
    }

    public ByteBuf setChar(int p_setChar_1_, int p_setChar_2_) {
        return this.buf.setChar(p_setChar_1_, p_setChar_2_);
    }

    public ByteBuf setFloat(int p_setFloat_1_, float p_setFloat_2_) {
        return this.buf.setFloat(p_setFloat_1_, p_setFloat_2_);
    }

    public ByteBuf setDouble(int p_setDouble_1_, double p_setDouble_2_) {
        return this.buf.setDouble(p_setDouble_1_, p_setDouble_2_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_) {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_, int p_setBytes_3_) {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_, int p_setBytes_3_, int p_setBytes_4_) {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_4_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, byte[] p_setBytes_2_) {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, byte[] p_setBytes_2_, int p_setBytes_3_, int p_setBytes_4_) {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_4_);
    }

    public ByteBuf setBytes(int p_setBytes_1_, ByteBuffer p_setBytes_2_) {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_);
    }

    public int setBytes(int p_setBytes_1_, InputStream p_setBytes_2_, int p_setBytes_3_) throws IOException {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
    }

    public int setBytes(int p_setBytes_1_, ScatteringByteChannel p_setBytes_2_, int p_setBytes_3_) throws IOException {
        return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
    }

    
    public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
        return this.buf.setBytes(index, in, position, length);
    }

    public ByteBuf setZero(int p_setZero_1_, int p_setZero_2_) {
        return this.buf.setZero(p_setZero_1_, p_setZero_2_);
    }

    
    public int setCharSequence(int index, CharSequence sequence, Charset charset) {
        return this.buf.setCharSequence(index, sequence, charset);
    }

    public boolean readBoolean() {
        return this.buf.readBoolean();
    }

    public byte readByte() {
        return this.buf.readByte();
    }

    public short readUnsignedByte() {
        return this.buf.readUnsignedByte();
    }

    public short readShort() {
        return this.buf.readShort();
    }

    
    public short readShortLE() {
        return this.buf.readShortLE();
    }

    public int readUnsignedShort() {
        return this.buf.readUnsignedShort();
    }

    
    public int readUnsignedShortLE() {
        return this.buf.readUnsignedShortLE();
    }

    public int readMedium() {
        return this.buf.readMedium();
    }

    
    public int readMediumLE() {
        return this.buf.readMediumLE();
    }

    public int readUnsignedMedium() {
        return this.buf.readUnsignedMedium();
    }

    
    public int readUnsignedMediumLE() {
        return this.buf.readUnsignedMediumLE();
    }

    public int readInt() {
        return this.buf.readInt();
    }

    
    public int readIntLE() {
        return this.buf.readIntLE();
    }

    public long readUnsignedInt() {
        return this.buf.readUnsignedInt();
    }

    
    public long readUnsignedIntLE() {
        return this.buf.readUnsignedIntLE();
    }

    public long readLong() {
        return this.buf.readLong();
    }

    
    public long readLongLE() {
        return this.buf.readLongLE();
    }

    public char readChar() {
        return this.buf.readChar();
    }

    public float readFloat() {
        return this.buf.readFloat();
    }

    public double readDouble() {
        return this.buf.readDouble();
    }

    public ByteBuf readBytes(int p_readBytes_1_) {
        return this.buf.readBytes(p_readBytes_1_);
    }

    public ByteBuf readSlice(int p_readSlice_1_) {
        return this.buf.readSlice(p_readSlice_1_);
    }

    
    public ByteBuf readRetainedSlice(int length) {
        return this.buf.readRetainedSlice(length);
    }

    public ByteBuf readBytes(ByteBuf p_readBytes_1_) {
        return this.buf.readBytes(p_readBytes_1_);
    }

    public ByteBuf readBytes(ByteBuf p_readBytes_1_, int p_readBytes_2_) {
        return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_);
    }

    public ByteBuf readBytes(ByteBuf p_readBytes_1_, int p_readBytes_2_, int p_readBytes_3_) {
        return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_3_);
    }

    public ByteBuf readBytes(byte[] p_readBytes_1_) {
        return this.buf.readBytes(p_readBytes_1_);
    }

    public ByteBuf readBytes(byte[] p_readBytes_1_, int p_readBytes_2_, int p_readBytes_3_) {
        return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_3_);
    }

    public ByteBuf readBytes(ByteBuffer p_readBytes_1_) {
        return this.buf.readBytes(p_readBytes_1_);
    }

    public ByteBuf readBytes(OutputStream p_readBytes_1_, int p_readBytes_2_) throws IOException {
        return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_);
    }

    public int readBytes(GatheringByteChannel p_readBytes_1_, int p_readBytes_2_) throws IOException {
        return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_);
    }

    
    public CharSequence readCharSequence(int length, Charset charset) {
        return this.buf.readCharSequence(length, charset);
    }

    
    public int readBytes(FileChannel out, long position, int length) throws IOException {
        return this.buf.readBytes(out, position, length);
    }

    public ByteBuf skipBytes(int p_skipBytes_1_) {
        return this.buf.skipBytes(p_skipBytes_1_);
    }

    public ByteBuf writeBoolean(boolean p_writeBoolean_1_) {
        return this.buf.writeBoolean(p_writeBoolean_1_);
    }

    public ByteBuf writeByte(int p_writeByte_1_) {
        return this.buf.writeByte(p_writeByte_1_);
    }

    public ByteBuf writeShort(int p_writeShort_1_) {
        return this.buf.writeShort(p_writeShort_1_);
    }

    
    public ByteBuf writeShortLE(int value) {
        return this.buf.writeShortLE(value);
    }

    public ByteBuf writeMedium(int p_writeMedium_1_) {
        return this.buf.writeMedium(p_writeMedium_1_);
    }

    
    public ByteBuf writeMediumLE(int value) {
        return this.buf.writeMediumLE(value);
    }

    public ByteBuf writeInt(int p_writeInt_1_) {
        return this.buf.writeInt(p_writeInt_1_);
    }

    
    public ByteBuf writeIntLE(int value) {
        return this.buf.writeIntLE(value);
    }

    public ByteBuf writeLong(long p_writeLong_1_) {
        return this.buf.writeLong(p_writeLong_1_);
    }

    
    public ByteBuf writeLongLE(long value) {
        return this.buf.writeLongLE(value);
    }

    public ByteBuf writeChar(int p_writeChar_1_) {
        return this.buf.writeChar(p_writeChar_1_);
    }

    public ByteBuf writeFloat(float p_writeFloat_1_) {
        return this.buf.writeFloat(p_writeFloat_1_);
    }

    public ByteBuf writeDouble(double p_writeDouble_1_) {
        return this.buf.writeDouble(p_writeDouble_1_);
    }

    public ByteBuf writeBytes(ByteBuf p_writeBytes_1_) {
        return this.buf.writeBytes(p_writeBytes_1_);
    }

    public ByteBuf writeBytes(ByteBuf p_writeBytes_1_, int p_writeBytes_2_) {
        return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
    }

    public ByteBuf writeBytes(ByteBuf p_writeBytes_1_, int p_writeBytes_2_, int p_writeBytes_3_) {
        return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_3_);
    }

    public ByteBuf writeBytes(byte[] p_writeBytes_1_) {
        return this.buf.writeBytes(p_writeBytes_1_);
    }

    public ByteBuf writeBytes(byte[] p_writeBytes_1_, int p_writeBytes_2_, int p_writeBytes_3_) {
        return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_3_);
    }

    public ByteBuf writeBytes(ByteBuffer p_writeBytes_1_) {
        return this.buf.writeBytes(p_writeBytes_1_);
    }

    public int writeBytes(InputStream p_writeBytes_1_, int p_writeBytes_2_) throws IOException {
        return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
    }

    public int writeBytes(ScatteringByteChannel p_writeBytes_1_, int p_writeBytes_2_) throws IOException {
        return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
    }

    
    public int writeBytes(FileChannel in, long position, int length) throws IOException {
        return this.buf.writeBytes(in, position, length);
    }

    public ByteBuf writeZero(int p_writeZero_1_) {
        return this.buf.writeZero(p_writeZero_1_);
    }

    
    public int writeCharSequence(CharSequence sequence, Charset charset) {
        return this.buf.writeCharSequence(sequence, charset);
    }

    public int indexOf(int p_indexOf_1_, int p_indexOf_2_, byte p_indexOf_3_) {
        return this.buf.indexOf(p_indexOf_1_, p_indexOf_2_, p_indexOf_3_);
    }

    public int bytesBefore(byte p_bytesBefore_1_) {
        return this.buf.bytesBefore(p_bytesBefore_1_);
    }

    public int bytesBefore(int p_bytesBefore_1_, byte p_bytesBefore_2_) {
        return this.buf.bytesBefore(p_bytesBefore_1_, p_bytesBefore_2_);
    }

    public int bytesBefore(int p_bytesBefore_1_, int p_bytesBefore_2_, byte p_bytesBefore_3_) {
        return this.buf.bytesBefore(p_bytesBefore_1_, p_bytesBefore_2_, p_bytesBefore_3_);
    }

    
    public int forEachByte(ByteProcessor processor) {
        return this.buf.forEachByte(processor);
    }

    
    public int forEachByte(int index, int length, ByteProcessor processor) {
        return this.buf.forEachByte(index, length, processor);
    }

    
    public int forEachByteDesc(ByteProcessor processor) {
        return this.buf.forEachByteDesc(processor);
    }

    
    public int forEachByteDesc(int index, int length, ByteProcessor processor) {
        return this.buf.forEachByteDesc(index, length, processor);
    }

    public int forEachByte(ByteBufProcessor p_forEachByte_1_) {
        return this.buf.forEachByte(p_forEachByte_1_);
    }

    public int forEachByte(int p_forEachByte_1_, int p_forEachByte_2_, ByteBufProcessor p_forEachByte_3_) {
        return this.buf.forEachByte(p_forEachByte_1_, p_forEachByte_2_, p_forEachByte_3_);
    }

    public int forEachByteDesc(ByteBufProcessor p_forEachByteDesc_1_) {
        return this.buf.forEachByteDesc(p_forEachByteDesc_1_);
    }

    public int forEachByteDesc(int p_forEachByteDesc_1_, int p_forEachByteDesc_2_, ByteBufProcessor p_forEachByteDesc_3_) {
        return this.buf.forEachByteDesc(p_forEachByteDesc_1_, p_forEachByteDesc_2_, p_forEachByteDesc_3_);
    }

    public ByteBuf copy() {
        return this.buf.copy();
    }

    public ByteBuf copy(int p_copy_1_, int p_copy_2_) {
        return this.buf.copy(p_copy_1_, p_copy_2_);
    }

    public ByteBuf slice() {
        return this.buf.slice();
    }

    
    public ByteBuf retainedSlice() {
        return this.buf.retainedSlice();
    }

    public ByteBuf slice(int p_slice_1_, int p_slice_2_) {
        return this.buf.slice(p_slice_1_, p_slice_2_);
    }

    
    public ByteBuf retainedSlice(int index, int length) {
        return this.buf.retainedSlice(index, length);
    }

    public ByteBuf duplicate() {
        return this.buf.duplicate();
    }

    
    public ByteBuf retainedDuplicate() {
        return this.buf.retainedDuplicate();
    }

    public int nioBufferCount() {
        return this.buf.nioBufferCount();
    }

    public ByteBuffer nioBuffer() {
        return this.buf.nioBuffer();
    }

    public ByteBuffer nioBuffer(int p_nioBuffer_1_, int p_nioBuffer_2_) {
        return this.buf.nioBuffer(p_nioBuffer_1_, p_nioBuffer_2_);
    }

    public ByteBuffer internalNioBuffer(int p_internalNioBuffer_1_, int p_internalNioBuffer_2_) {
        return this.buf.internalNioBuffer(p_internalNioBuffer_1_, p_internalNioBuffer_2_);
    }

    public ByteBuffer[] nioBuffers() {
        return this.buf.nioBuffers();
    }

    public ByteBuffer[] nioBuffers(int p_nioBuffers_1_, int p_nioBuffers_2_) {
        return this.buf.nioBuffers(p_nioBuffers_1_, p_nioBuffers_2_);
    }

    public boolean hasArray() {
        return this.buf.hasArray();
    }

    public byte[] array() {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        return bytes;
    }

    public int arrayOffset() {
        return this.buf.arrayOffset();
    }

    public boolean hasMemoryAddress() {
        return this.buf.hasMemoryAddress();
    }

    public long memoryAddress() {
        return this.buf.memoryAddress();
    }

    public String toString(Charset p_toString_1_) {
        return this.buf.toString(p_toString_1_);
    }

    public String toString(int p_toString_1_, int p_toString_2_, Charset p_toString_3_) {
        return this.buf.toString(p_toString_1_, p_toString_2_, p_toString_3_);
    }

    public int hashCode() {
        return this.buf.hashCode();
    }

    public boolean equals(Object p_equals_1_) {
        return this.buf.equals(p_equals_1_);
    }

    public int compareTo(ByteBuf p_compareTo_1_) {
        return this.buf.compareTo(p_compareTo_1_);
    }

    public String toString() {
        return this.buf.toString();
    }

    public ByteBuf retain(int p_retain_1_) {
        return this.buf.retain(p_retain_1_);
    }

    public ByteBuf retain() {
        return this.buf.retain();
    }

    
    public ByteBuf touch() {
        return this.buf.touch();
    }

    
    public ByteBuf touch(Object hint) {
        return this.buf.touch(hint);
    }

    public int refCnt() {
        return this.buf.refCnt();
    }

    public boolean release() {
        return this.buf.release();
    }

    public boolean release(int p_release_1_) {
        return this.buf.release(p_release_1_);
    }
}
