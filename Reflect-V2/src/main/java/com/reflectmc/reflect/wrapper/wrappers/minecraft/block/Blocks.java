package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.WrapObject;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.init.Blocks", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.init.Blocks", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Blocks extends WrapperBase {
    @WrapObject(deobfName = "air", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "AIR", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block air;
    @WrapObject(deobfName = "water", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "WATER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static BlockStaticLiquid water;
    @WrapObject(deobfName = "flowing_water", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "FLOWING_WATER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static BlockDynamicLiquid flowing_water;
    @WrapObject(deobfName = "lava", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LAVA", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static BlockStaticLiquid lava;
    @WrapObject(deobfName = "flowing_lava", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "FLOWING_LAVA", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static BlockDynamicLiquid flowing_lava;
    @WrapObject(deobfName = "enchanting_table", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "ENCHANTING_TABLE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block enchanting_table;
    @WrapObject(deobfName = "carpet", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "CARPET", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block carpet;
    @WrapObject(deobfName = "glass_pane", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "GLASS_PANE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block glass_pane;
    @WrapObject(deobfName = "stained_glass_pane", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "STAINED_GLASS_PANE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block stained_glass_pane;
    @WrapObject(deobfName = "iron_bars", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "IRON_BARS", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block iron_bars;
    @WrapObject(deobfName = "snow_layer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "SNOW_LAYER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block snow_layer;
    @WrapObject(deobfName = "ice", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "ICE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block ice;
    @WrapObject(deobfName = "packed_ice", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "PACKED_ICE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block packed_ice;
    @WrapObject(deobfName = "coal_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "COAL_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block coal_ore;
    @WrapObject(deobfName = "diamond_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "DIAMOND_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block diamond_ore;
    @WrapObject(deobfName = "emerald_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "EMERALD_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block emerald_ore;
    @WrapObject(deobfName = "chest", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "CHEST", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block chest;
    @WrapObject(deobfName = "trapped_chest", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TRAPPED_CHEST", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block trapped_chest;
    @WrapObject(deobfName = "torch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TORCH", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block torch;
    @WrapObject(deobfName = "anvil", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "ANVIL", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block anvil;
    @WrapObject(deobfName = "noteblock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "NOTEBLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block noteblock;
    @WrapObject(deobfName = "jukebox", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "JUKEBOX", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block jukebox;
    @WrapObject(deobfName = "tnt", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TNT", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block tnt;
    @WrapObject(deobfName = "gold_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "GOLD_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block gold_ore;
    @WrapObject(deobfName = "iron_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "IRON_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block iron_ore;
    @WrapObject(deobfName = "lapis_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LAPIS_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block lapis_ore;
    @WrapObject(deobfName = "lit_redstone_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LIT_REDSTONE_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block lit_redstone_ore;
    @WrapObject(deobfName = "quartz_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "QUARTZ_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block quartz_ore;
    @WrapObject(deobfName = "redstone_ore", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "REDSTONE_ORE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block redstone_ore;
    @WrapObject(deobfName = "wooden_pressure_plate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "WOODEN_PRESSURE_PLATE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block wooden_pressure_plate;
    @WrapObject(deobfName = "stone_pressure_plate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "STONE_PRESSURE_PLATE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block stone_pressure_plate;
    @WrapObject(deobfName = "light_weighted_pressure_plate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LIGHT_WEIGHTED_PRESSURE_PLATE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block light_weighted_pressure_plate;
    @WrapObject(deobfName = "heavy_weighted_pressure_plate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "HEAVY_WEIGHTED_PRESSURE_PLATE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block heavy_weighted_pressure_plate;
    @WrapObject(deobfName = "stone_button", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "STONE_BUTTON", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block stone_button;
    @WrapObject(deobfName = "wooden_button", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "WOODEN_BUTTON", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block wooden_button;
    @WrapObject(deobfName = "lever", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LEVER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block lever;
    @WrapObject(deobfName = "tallgrass", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TALLGRASS", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block tallgrass;
    @WrapObject(deobfName = "tripwire", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TRIPWIRE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block tripwire;
    @WrapObject(deobfName = "tripwire_hook", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TRIPWIRE_HOOK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block tripwire_hook;
    @WrapObject(deobfName = "rail", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "RAIL", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block rail;
    @WrapObject(deobfName = "waterlily", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "WATERLILY", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block waterlily;
    @WrapObject(deobfName = "red_flower", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "RED_FLOWER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block red_flower;
    @WrapObject(deobfName = "red_mushroom", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "RED_MUSHROOM", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block red_mushroom;
    @WrapObject(deobfName = "brown_mushroom", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "BROWN_MUSHROOM", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block brown_mushroom;
    @WrapObject(deobfName = "vine", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "VINE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block vine;
    @WrapObject(deobfName = "trapdoor", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "TRAPDOOR", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block trapdoor;
    @WrapObject(deobfName = "yellow_flower", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "YELLOW_FLOWER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block yellow_flower;
    @WrapObject(deobfName = "ladder", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LADDER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block ladder;
    @WrapObject(deobfName = "furnace", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "FURNACE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block furnace;
    @WrapObject(deobfName = "sand", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "SAND", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block sand;
    @WrapObject(deobfName = "cactus", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "CACTUS", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block cactus;
    @WrapObject(deobfName = "dispenser", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "DISPENSER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block dispenser;
    @WrapObject(deobfName = "dropper", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "DROPPER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block dropper;
    @WrapObject(deobfName = "crafting_table", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "CRAFTING_TABLE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block crafting_table;
    @WrapObject(deobfName = "web", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "WEB", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block web;
    @WrapObject(deobfName = "pumpkin", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "PUMPKIN", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block pumpkin;
    @WrapObject(deobfName = "sapling", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "SAPLING", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block sapling;
    @WrapObject(deobfName = "cobblestone_wall", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "COBBLESTONE_WALL", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block cobblestone_wall;
    @WrapObject(deobfName = "oak_fence", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "OAK_FENCE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block oak_fence;
    @WrapObject(deobfName = "redstone_torch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "REDSTONE_TORCH", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block redstone_torch;
    @WrapObject(deobfName = "ender_chest", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "ENDER_CHEST", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block ender_chest;
    @WrapObject(deobfName = "stone", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "STONE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block stone;
    @WrapObject(deobfName = "dirt", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "DIRT", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block dirt;
    @WrapObject(deobfName = "log", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LOG", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block log;
    @WrapObject(deobfName = "bed", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "BED", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block bed;
    @WrapObject(deobfName = "coal_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "COAL_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block coal_block;
    @WrapObject(deobfName = "iron_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "IRON_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block iron_block;
    @WrapObject(deobfName = "gold_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "GOLD_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block gold_block;
    @WrapObject(deobfName = "diamond_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "DIAMOND_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block diamond_block;
    @WrapObject(deobfName = "emerald_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "EMERALD_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block emerald_block;
    @WrapObject(deobfName = "redstone_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "REDSTONE_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block redstone_block;
    @WrapObject(deobfName = "lapis_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "LAPIS_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block lapis_block;
    @WrapObject(deobfName = "mob_spawner", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "MOB_SPAWNER", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block mob_spawner;
    @WrapObject(deobfName = "end_portal_frame", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "END_PORTAL_FRAME", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block end_portal_frame;
    @WrapObject(deobfName = "command_block", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "COMMAND_BLOCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block command_block;
    @WrapObject(deobfName = "obsidian", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "OBSIDIAN", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block obsidian;
    @WrapObject(deobfName = "clay", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "CLAY", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block clay;
    @WrapObject(deobfName = "mossy_cobblestone", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "MOSSY_COBBLESTONE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block mossy_cobblestone;
    @WrapObject(deobfName = "bedrock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "BEDROCK", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block bedrock;
    @WrapObject(deobfName = "sponge", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "SPONGE", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Block sponge;

    public Blocks(Object obj) {
        super(obj);
    }
}
