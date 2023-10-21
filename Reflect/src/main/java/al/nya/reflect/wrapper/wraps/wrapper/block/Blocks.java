package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapObject;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.init.Blocks", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.init.Blocks", targetMap = Maps.Srg1_12_2)
public class Blocks extends IWrapper {
    @WrapObject(mcpName = "air", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "AIR", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block air;
    @WrapObject(mcpName = "water", targetMap = Maps.Srg1_8_9, makeWrap = BlockStaticLiquid.class)
    @WrapObject(mcpName = "WATER", targetMap = Maps.Srg1_12_2, makeWrap = BlockStaticLiquid.class)
    public static BlockStaticLiquid water;
    @WrapObject(mcpName = "flowing_water", targetMap = Maps.Srg1_8_9, makeWrap = BlockDynamicLiquid.class)
    @WrapObject(mcpName = "FLOWING_WATER", targetMap = Maps.Srg1_12_2, makeWrap = BlockDynamicLiquid.class)
    public static BlockDynamicLiquid flowing_water;
    @WrapObject(mcpName = "lava", targetMap = Maps.Srg1_8_9, makeWrap = BlockStaticLiquid.class)
    @WrapObject(mcpName = "LAVA", targetMap = Maps.Srg1_12_2, makeWrap = BlockStaticLiquid.class)
    public static BlockStaticLiquid lava;
    @WrapObject(mcpName = "flowing_lava", targetMap = Maps.Srg1_8_9, makeWrap = BlockDynamicLiquid.class)
    @WrapObject(mcpName = "FLOWING_LAVA", targetMap = Maps.Srg1_12_2, makeWrap = BlockDynamicLiquid.class)
    public static BlockDynamicLiquid flowing_lava;
    @WrapObject(mcpName = "enchanting_table", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "ENCHANTING_TABLE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block enchanting_table;
    @WrapObject(mcpName = "carpet", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "CARPET", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block carpet;
    @WrapObject(mcpName = "glass_pane", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "GLASS_PANE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block glass_pane;
    @WrapObject(mcpName = "stained_glass_pane", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "STAINED_GLASS_PANE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block stained_glass_pane;
    @WrapObject(mcpName = "iron_bars", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "IRON_BARS", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block iron_bars;
    @WrapObject(mcpName = "snow_layer", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "SNOW_LAYER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block snow_layer;
    @WrapObject(mcpName = "ice", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "ICE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block ice;
    @WrapObject(mcpName = "packed_ice", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "PACKED_ICE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block packed_ice;
    @WrapObject(mcpName = "coal_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "COAL_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block coal_ore;
    @WrapObject(mcpName = "diamond_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "DIAMOND_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block diamond_ore;
    @WrapObject(mcpName = "emerald_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "EMERALD_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block emerald_ore;
    @WrapObject(mcpName = "chest", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "CHEST", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block chest;
    @WrapObject(mcpName = "trapped_chest", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TRAPPED_CHEST", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block trapped_chest;
    @WrapObject(mcpName = "torch", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TORCH", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block torch;
    @WrapObject(mcpName = "anvil", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "ANVIL", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block anvil;
    @WrapObject(mcpName = "noteblock", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "NOTEBLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block noteblock;
    @WrapObject(mcpName = "jukebox", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "JUKEBOX", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block jukebox;
    @WrapObject(mcpName = "tnt", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TNT", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block tnt;
    @WrapObject(mcpName = "gold_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "GOLD_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block gold_ore;
    @WrapObject(mcpName = "iron_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "IRON_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block iron_ore;
    @WrapObject(mcpName = "lapis_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LAPIS_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block lapis_ore;
    @WrapObject(mcpName = "lit_redstone_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LIT_REDSTONE_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block lit_redstone_ore;
    @WrapObject(mcpName = "quartz_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "QUARTZ_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block quartz_ore;
    @WrapObject(mcpName = "redstone_ore", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "REDSTONE_ORE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block redstone_ore;
    @WrapObject(mcpName = "wooden_pressure_plate", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "WOODEN_PRESSURE_PLATE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block wooden_pressure_plate;
    @WrapObject(mcpName = "stone_pressure_plate", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "STONE_PRESSURE_PLATE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block stone_pressure_plate;
    @WrapObject(mcpName = "light_weighted_pressure_plate", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LIGHT_WEIGHTED_PRESSURE_PLATE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block light_weighted_pressure_plate;
    @WrapObject(mcpName = "heavy_weighted_pressure_plate", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "HEAVY_WEIGHTED_PRESSURE_PLATE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block heavy_weighted_pressure_plate;
    @WrapObject(mcpName = "stone_button", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "STONE_BUTTON", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block stone_button;
    @WrapObject(mcpName = "wooden_button", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "WOODEN_BUTTON", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block wooden_button;
    @WrapObject(mcpName = "lever", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LEVER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block lever;
    @WrapObject(mcpName = "tallgrass", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TALLGRASS", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block tallgrass;
    @WrapObject(mcpName = "tripwire", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TRIPWIRE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block tripwire;
    @WrapObject(mcpName = "tripwire_hook", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TRIPWIRE_HOOK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block tripwire_hook;
    @WrapObject(mcpName = "rail", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "RAIL", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block rail;
    @WrapObject(mcpName = "waterlily", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "WATERLILY", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block waterlily;
    @WrapObject(mcpName = "red_flower", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "RED_FLOWER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block red_flower;
    @WrapObject(mcpName = "red_mushroom", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "RED_MUSHROOM", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block red_mushroom;
    @WrapObject(mcpName = "brown_mushroom", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "BROWN_MUSHROOM", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block brown_mushroom;
    @WrapObject(mcpName = "vine", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "VINE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block vine;
    @WrapObject(mcpName = "trapdoor", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "TRAPDOOR", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block trapdoor;
    @WrapObject(mcpName = "yellow_flower", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "YELLOW_FLOWER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block yellow_flower;
    @WrapObject(mcpName = "ladder", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LADDER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block ladder;
    @WrapObject(mcpName = "furnace", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "FURNACE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block furnace;
    @WrapObject(mcpName = "sand", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "SAND", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block sand;
    @WrapObject(mcpName = "cactus", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "CACTUS", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block cactus;
    @WrapObject(mcpName = "dispenser", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "DISPENSER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block dispenser;
    @WrapObject(mcpName = "dropper", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "DROPPER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block dropper;
    @WrapObject(mcpName = "crafting_table", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "CRAFTING_TABLE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block crafting_table;
    @WrapObject(mcpName = "web", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "WEB", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block web;
    @WrapObject(mcpName = "pumpkin", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "PUMPKIN", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block pumpkin;
    @WrapObject(mcpName = "sapling", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "SAPLING", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block sapling;
    @WrapObject(mcpName = "cobblestone_wall", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "COBBLESTONE_WALL", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block cobblestone_wall;
    @WrapObject(mcpName = "oak_fence", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "OAK_FENCE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block oak_fence;
    @WrapObject(mcpName = "redstone_torch", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "REDSTONE_TORCH", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block redstone_torch;
    @WrapObject(mcpName = "ender_chest", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "ENDER_CHEST", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block ender_chest;
    @WrapObject(mcpName = "stone", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "STONE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block stone;
    @WrapObject(mcpName = "dirt", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "DIRT", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block dirt;
    @WrapObject(mcpName = "log", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LOG", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block log;
    @WrapObject(mcpName = "bed", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "BED", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block bed;
    @WrapObject(mcpName = "coal_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "COAL_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block coal_block;
    @WrapObject(mcpName = "iron_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "IRON_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block iron_block;
    @WrapObject(mcpName = "gold_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "GOLD_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block gold_block;
    @WrapObject(mcpName = "diamond_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "DIAMOND_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block diamond_block;
    @WrapObject(mcpName = "emerald_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "EMERALD_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block emerald_block;
    @WrapObject(mcpName = "redstone_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "REDSTONE_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block redstone_block;
    @WrapObject(mcpName = "lapis_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "LAPIS_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block lapis_block;
    @WrapObject(mcpName = "mob_spawner", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "MOB_SPAWNER", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block mob_spawner;
    @WrapObject(mcpName = "end_portal_frame", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "END_PORTAL_FRAME", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block end_portal_frame;
    @WrapObject(mcpName = "command_block", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "COMMAND_BLOCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block command_block;
    @WrapObject(mcpName = "obsidian", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "OBSIDIAN", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block obsidian;
    @WrapObject(mcpName = "clay", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "CLAY", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block clay;
    @WrapObject(mcpName = "mossy_cobblestone", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "MOSSY_COBBLESTONE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block mossy_cobblestone;
    @WrapObject(mcpName = "BEDROCK", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "BEDROCK", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block bedrock;
    @WrapObject(mcpName = "sponge", targetMap = Maps.Srg1_8_9, makeWrap = Block.class)
    @WrapObject(mcpName = "SPONGE", targetMap = Maps.Srg1_12_2, makeWrap = Block.class)
    public static Block sponge;

    public Blocks(Object obj) {
        super(obj);
    }
}
