package net.hearthian.wetsand.utils;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hearthian.wetsand.blocks.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static net.hearthian.wetsand.WetSand.MOD_ID;

public class initializer {
    public static final Block SAND = new WettableFallingBlock(
        Wettable.HumidityLevel.UNAFFECTED,
        AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("minecraft", "sand")))
    );
    public static final Block MOIST_SAND = new WettableFallingBlock(
        Wettable.HumidityLevel.MOIST,
        AbstractBlock.Settings.copy(SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "moist_sand")))
    );
    public static final Block WET_SAND = new WettableBlock(
        Wettable.HumidityLevel.WET,
        AbstractBlock.Settings.copy(SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "wet_sand")))
    );
    public static final Block SOAKED_SAND = new SoakedBlock(
        AbstractBlock.Settings.copy(SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "soaked_sand")))
    );
    public static final Block RED_SAND = new WettableFallingBlock(
        Wettable.HumidityLevel.UNAFFECTED,
        AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("minecraft", "red_sand")))
    );
    public static final Block MOIST_RED_SAND = new WettableFallingBlock(
        Wettable.HumidityLevel.MOIST,
        AbstractBlock.Settings.copy(RED_SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "moist_red_sand")))
    );
    public static final Block WET_RED_SAND = new WettableBlock(
        Wettable.HumidityLevel.WET,
        AbstractBlock.Settings.copy(RED_SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "wet_red_sand")))
    );
    public static final Block SOAKED_RED_SAND = new SoakedBlock(
        AbstractBlock.Settings.copy(RED_SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "soaked_red_sand")))
    );

    public static final Block SUSPICIOUS_SAND = new WettableBrushableBlock(
        Wettable.HumidityLevel.UNAFFECTED, SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND,
        AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sounds(BlockSoundGroup.SUSPICIOUS_SAND).pistonBehavior(PistonBehavior.DESTROY).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("minecraft", "suspicious_sand")))
    );
    public static final Block MOIST_SUSPICIOUS_SAND = new WettableBrushableBlock(
        Wettable.HumidityLevel.MOIST, MOIST_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND,
        AbstractBlock.Settings.copy(SUSPICIOUS_SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "moist_suspicious_sand")))
    );
    public static final Block WET_SUSPICIOUS_SAND = new WettableBrushableBlock(
        Wettable.HumidityLevel.WET, WET_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND,
        AbstractBlock.Settings.copy(SUSPICIOUS_SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "wet_suspicious_sand")))
    );
    public static final Block SOAKED_SUSPICIOUS_SAND = new SoakedBrushableBlock(
        SOAKED_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundEvents.ITEM_BRUSH_BRUSHING_SAND,
        AbstractBlock.Settings.copy(SUSPICIOUS_SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "soaked_suspicious_sand")))
    );

    private static void registerBlockItem(String path, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, path));
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, path));

        Registry.register(Registries.BLOCK, blockKey, block);
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(itemKey)));
    }

    public static void initBlockItems() {
        registerBlockItem("moist_sand", MOIST_SAND);
        registerBlockItem("wet_sand", WET_SAND);
        registerBlockItem("soaked_sand", SOAKED_SAND);
        registerBlockItem("moist_red_sand", MOIST_RED_SAND);
        registerBlockItem("wet_red_sand", WET_RED_SAND);
        registerBlockItem("soaked_red_sand", SOAKED_RED_SAND);
        registerBlockItem("moist_suspicious_sand", MOIST_SUSPICIOUS_SAND);
        registerBlockItem("wet_suspicious_sand", WET_SUSPICIOUS_SAND);
        registerBlockItem("soaked_suspicious_sand", SOAKED_SUSPICIOUS_SAND);

    }

    public static void initCreativePlacement() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.SAND, MOIST_SAND);
            content.addAfter(MOIST_SAND, WET_SAND);
            content.addAfter(WET_SAND, SOAKED_SAND);
            content.addAfter(Items.RED_SAND, MOIST_RED_SAND);
            content.addAfter(MOIST_RED_SAND, WET_RED_SAND);
            content.addAfter(WET_RED_SAND, SOAKED_RED_SAND);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(Items.SUSPICIOUS_SAND, MOIST_SUSPICIOUS_SAND);
            content.addAfter(MOIST_SUSPICIOUS_SAND, WET_SUSPICIOUS_SAND);
            content.addAfter(WET_SUSPICIOUS_SAND, SOAKED_SUSPICIOUS_SAND);
        });
    }
}
