package al.logger.client.features.modules.impls.World;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.Blocks;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

import java.util.LinkedList;


public class AutoMine extends Module {

    public OptionValue bypass = new OptionValue("Bypass", false);
    public OptionValue dia = new OptionValue("Diamond", true);
    public OptionValue gold = new OptionValue("Gold",false);
    public OptionValue iron = new OptionValue("Iron",false);
    public OptionValue lapis = new OptionValue("Lapis",false);
    public OptionValue emerald = new OptionValue("Emerald",false);
    public OptionValue coal = new OptionValue("Coal",false);
    public OptionValue redstone = new OptionValue("Redstone",false);


    public static LinkedList<BlockPos> blocks = new LinkedList<BlockPos>();
    public AutoMine() {
        super("AutoMine", Category.World);
    }

    @Listener
    public void onUpdate(EventLivingUpdate update) {
    }
    public void generateBlocks(){
        for (int i = (int)(mc.getThePlayer().getPosX() - 50); i <= (int)(mc.getThePlayer().getPosX() + 50); i++)
        {
            for (int j = (int)(mc.getThePlayer().getPosZ() - 50); j <= (int)(mc.getThePlayer().getPosZ() + 50); j++)
            {
                for (int k = 5; k <= 256; k++)
                {

                }
            }
        }
    }

    public boolean isTarget(BlockPos pos) {
        Block block = mc.getTheWorld().getBlockState(pos).getBlock();
        if (Blocks.diamond_ore.equals(block)) {
            return dia.getValue();
        } else if (Blocks.lapis_ore.equals(block)) {
            return lapis.getValue();
        } else if (Blocks.iron_ore.equals(block)) {
            return iron.getValue();
        } else if (Blocks.gold_ore.equals(block)) {
            return gold.getValue();
        } else if (Blocks.coal_ore.equals(block)) {
            return coal.getValue();
        } else if (Blocks.emerald_ore.equals(block)) {
            return emerald.getValue();
        } else if (Blocks.redstone_ore.equals(block) || Blocks.lit_redstone_ore.equals(block)) {
            return redstone.getValue();
        }
        return false;
    }
}
