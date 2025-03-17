package net.hearthian.wetsand.utils;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hearthian.wetsand.blocks.Wettable;
import net.hearthian.wetsand.blocks.WettableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import static net.hearthian.wetsand.WetSand.MOD_ID;

public class initializer {
    public static final Block MOIST_SAND = new WettableBlock(
            Wettable.HumidityLevel.MOIST,
            AbstractBlock.Settings.copy(Blocks.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "moist_sand")))
    );
    public static final Block DAMP_SAND = new WettableBlock(
            Wettable.HumidityLevel.DAMP,
            AbstractBlock.Settings.copy(Blocks.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "damp_sand")))
    );
    public static final Block WET_SAND = new WettableBlock(
            Wettable.HumidityLevel.WET,
            AbstractBlock.Settings.copy(Blocks.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "wet_sand")))
    );
    public static final Block MOIST_RED_SAND = new WettableBlock(
            Wettable.HumidityLevel.MOIST,
            AbstractBlock.Settings.copy(Blocks.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "moist_red_sand")))
    );
    public static final Block DAMP_RED_SAND = new WettableBlock(
            Wettable.HumidityLevel.DAMP,
            AbstractBlock.Settings.copy(Blocks.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "damp_red_sand")))
    );
    public static final Block WET_RED_SAND = new WettableBlock(
            Wettable.HumidityLevel.WET,
            AbstractBlock.Settings.copy(Blocks.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "wet_red_sand")))
    );

    private static void registerBlockItem(String path, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, path));
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, path));

        Registry.register(Registries.BLOCK, blockKey, block);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(itemKey)));
    }

    public static void initBlockItems() {
        registerBlockItem("moist_sand", MOIST_SAND);
        registerBlockItem("damp_sand", DAMP_SAND);
        registerBlockItem("wet_sand", WET_SAND);
        registerBlockItem("moist_red_sand", MOIST_RED_SAND);
        registerBlockItem("damp_red_sand", DAMP_RED_SAND);
        registerBlockItem("wet_red_sand", WET_RED_SAND);
    }

    public static void initCreativePlacement() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.SAND, MOIST_SAND);
            content.addAfter(MOIST_SAND, DAMP_SAND);
            content.addAfter(DAMP_SAND, WET_SAND);
            content.addAfter(Items.RED_SAND, MOIST_RED_SAND);
            content.addAfter(MOIST_RED_SAND, DAMP_RED_SAND);
            content.addAfter(DAMP_RED_SAND, WET_RED_SAND);
        });
    }
}
