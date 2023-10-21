package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.ColorValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.IBlockState;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.world.Chunk;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlockESP extends Module {
    public static List<BlockPos> toRender = new ArrayList<>();
    private final TimerUtils refresh = new TimerUtils();

    ColorValue colorValue = new ColorValue("Color", new Color(255, 255, 255));
    OptionValue EnchantmentBlock = new OptionValue("EnchantmentBlock", true);
    public BlockESP() {
        super("BlockESP", Category.Visual);
        addValues(EnchantmentBlock,colorValue);
    }
    @Listener
    public void onRender3D(EventRender3D event){

        for (BlockPos bp: toRender) {
            /*
RenderUtil.drawBlockESP(bp, colorValue.getValue());
                 */

        }
        final int sx = (int) mc.getThePlayer().getPosX() - 256;
        final int sz = (int) mc.getThePlayer().getPosZ() - 256;
        final int endX = (int) mc.getThePlayer().getPosX() + 256;
        final int endZ = (int) mc.getThePlayer().getPosZ() + 256;
        if (!refresh.isDelay(10)) {
            return;
        }
        toRender = new ArrayList<>();
        for (int x = sx; x <= endX; x++) {

            for (int z = sz; z <= endZ; z++) {

                Chunk chunk = mc.getTheWorld().getChunkFromChunkCoords(x >> 4, z >> 4);

                if (!chunk.isLoaded()) continue;

                for (int y = 0; y <= 255; y++) {

                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState blockState = chunk.getBlockState(pos);
                    Block block = blockState.getBlock();
                    /*

                     */

                }
            }
        }
        refresh.reset();
    }







}
