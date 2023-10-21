package al.nya.reflect.events.events.world;

import al.nya.reflect.events.events.Event;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.world.IBlockAccess;
import lombok.Getter;
import lombok.Setter;

public class EventBlockRenderSide extends Event {
    @Getter
    private Block block;
    @Getter
    private IBlockAccess blockAccess;
    @Getter
    private BlockPos pos;
    @Getter
    private Enum<?> side;
    @Getter
    private double maxX,maxY,maxZ,minX,minY,minZ;
    @Getter
    @Setter
    private boolean shouldRender = false;
    public EventBlockRenderSide(Block block, IBlockAccess worldIn, BlockPos pos, Enum EnumFacing_side,
                                double maxX, double maxY, double maxZ, double minX, double minY, double minZ){
        this.block = block;
        this.blockAccess = worldIn;
        this.pos = pos;
        this.side = EnumFacing_side;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
    }
}
