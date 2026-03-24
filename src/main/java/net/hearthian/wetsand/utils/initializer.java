package net.hearthian.wetsand.utils;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.hearthian.wetsand.blocks.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import static net.hearthian.wetsand.WetSand.MOD_ID;

public class initializer {
    public static final Block SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.UNAFFECTED,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("minecraft", "sand")))
    );
    public static final Block MOIST_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.MOIST,
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "moist_sand")))
    );
    public static final Block WET_SAND = new WettableBlock(
            Wettable.HumidityLevel.WET,
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "wet_sand")))
    );
    public static final Block SOAKED_SAND = new SoakedBlock(
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "soaked_sand")))
    );
    public static final Block RED_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.UNAFFECTED,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("minecraft", "red_sand")))
    );
    public static final Block MOIST_RED_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.MOIST,
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "moist_red_sand")))
    );
    public static final Block WET_RED_SAND = new WettableBlock(
            Wettable.HumidityLevel.WET,
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "wet_red_sand")))
    );
    public static final Block SOAKED_RED_SAND = new SoakedBlock(
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "soaked_red_sand")))
    );

    public static final Block SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.UNAFFECTED, SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_SAND).pushReaction(PushReaction.DESTROY).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("minecraft", "suspicious_sand")))
    );
    public static final Block MOIST_SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.MOIST, MOIST_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "moist_suspicious_sand")))
    );
    public static final Block WET_SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.WET, WET_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "wet_suspicious_sand")))
    );
    public static final Block SOAKED_SUSPICIOUS_SAND = new SoakedBrushableBlock(
            SOAKED_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "soaked_suspicious_sand")))
    );

    private static void registerBlockItem(String path, Block block) {
        ResourceKey<@NotNull Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, path));
        ResourceKey<@NotNull Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, path));

        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)));
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
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.insertAfter(Items.SAND, MOIST_SAND);
            content.insertAfter(MOIST_SAND, WET_SAND);
            content.insertAfter(WET_SAND, SOAKED_SAND);
            content.insertAfter(Items.RED_SAND, MOIST_RED_SAND);
            content.insertAfter(MOIST_RED_SAND, WET_RED_SAND);
            content.insertAfter(WET_RED_SAND, SOAKED_RED_SAND);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.insertAfter(Items.SUSPICIOUS_SAND, MOIST_SUSPICIOUS_SAND);
            content.insertAfter(MOIST_SUSPICIOUS_SAND, WET_SUSPICIOUS_SAND);
            content.insertAfter(WET_SUSPICIOUS_SAND, SOAKED_SUSPICIOUS_SAND);
        });
    }
}
