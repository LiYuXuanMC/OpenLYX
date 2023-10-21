package al.logger.client.event.client.world;

import al.logger.client.event.Event;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import lombok.Getter;
import lombok.Setter;

public class EventBlockBB implements Event {
    @Getter
    private BlockPos pos;
    @Getter
    private Block block;
    @Setter
    private AxisAlignedBB boundingBox;
    public EventBlockBB(AxisAlignedBB boundingBox,BlockPos pos,Block block) {
        this.pos = pos;
        this.block = block;
        this.boundingBox = boundingBox;
    }
    @ExportObfuscate(name = "getBoundingBox")
    public AxisAlignedBB getBoundingBox(){
        return boundingBox;
    }
    public int getX(){
        return pos.getX();
    }
    public int getY(){
        return pos.getY();
    }

    public int getZ(){
        return pos.getZ();
    }
}
