package al.nya.reflect.features.modules.World.notebot;

import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;

/**
 * @author noil
 */
public class Note {

    private int index;

    private BlockPos position;

    private int instrument;

    private int pitch;

    public Note(int index, BlockPos position, int instrument, int pitch) {
        this.index = index;
        this.position = position;
        this.instrument = instrument;
        this.pitch = pitch;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BlockPos getPosition() {
        return this.position;
    }

    public void setPosition(BlockPos position) {
        this.position = position;
    }

    public int getInstrument() {
        return this.instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public int getPitch() {
        return this.pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }
}