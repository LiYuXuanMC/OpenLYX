package al.logger.client.utils.pathfinding;

import al.logger.client.utils.pathfinding.astar.Cell;
import al.logger.client.utils.pathfinding.astar.IWorldProvider;
import al.logger.client.wrapper.LoggerMC.block.*;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.world.World;

public class MinecraftWorldProvider implements IWorldProvider {
    private final World world;

    public MinecraftWorldProvider(World world) {
        this.world = world;
    }

    @Override
    public boolean isBlocked(Cell cell) {
        return isBlocked(cell.x, cell.y, cell.z);
    }

    public boolean isBlocked(int x, int y, int z) {
        return isSolid(x, y, z) || isSolid(x, y + 1, z) || unableToStand(x, y - 1, z);
    }

    private boolean isSolid(int x, int y, int z) {
        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (block.isNull()) return true;

        return block.getMaterial().isSolid();
    }

    private boolean unableToStand(int x, int y, int z) {
        final Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        return BlockFence.isBlockFence(block) || BlockWall.isBlockWall(block) || BlockCarpet.isBlockCarpet(block) || BlockLadder.isBlockLaddeer(block);
    }
}
