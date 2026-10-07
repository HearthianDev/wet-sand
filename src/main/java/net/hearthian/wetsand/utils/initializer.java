package net.hearthian.wetsand.utils;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.hearthian.wetsand.blocks.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
            Wettable.HumidityLevel.SOAKED,
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "soaked_sand")))
    );
    public static final Block SLIMED_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.UNAFFECTED,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_sand")))
    );
    public static final Block SLIMED_MOIST_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.MOIST,
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_moist_sand")))
    );
    public static final Block SLIMED_WET_SAND = new WettableBlock(
            Wettable.HumidityLevel.WET,
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_wet_sand")))
    );
    public static final Block SLIMED_SOAKED_SAND = new SoakedBlock(
            Wettable.HumidityLevel.SOAKED,
            BlockBehaviour.Properties.ofFullCopy(SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_soaked_sand")))
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
            Wettable.HumidityLevel.SOAKED,
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "soaked_red_sand")))
    );
    public static final Block SLIMED_RED_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.UNAFFECTED,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_red_sand")))
    );
    public static final Block SLIMED_MOIST_RED_SAND = new WettableFallingBlock(
            Wettable.HumidityLevel.MOIST,
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_moist_red_sand")))
    );
    public static final Block SLIMED_WET_RED_SAND = new WettableBlock(
            Wettable.HumidityLevel.WET,
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_wet_red_sand")))
    );
    public static final Block SLIMED_SOAKED_RED_SAND = new SoakedBlock(
            Wettable.HumidityLevel.SOAKED,
            BlockBehaviour.Properties.ofFullCopy(RED_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_soaked_red_sand")))
    );

    public static final Block SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.UNAFFECTED, SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE)
                    .strength(0.25F).sound(SoundType.SUSPICIOUS_SAND)
                    .pushReaction(PushReaction.POPPED).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("minecraft", "suspicious_sand")))
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
            Wettable.HumidityLevel.SOAKED, SOAKED_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "soaked_suspicious_sand")))
    );
    public static final Block SLIMED_SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.UNAFFECTED, SLIMED_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_suspicious_sand")))
    );
    public static final Block SLIMED_MOIST_SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.MOIST, SLIMED_MOIST_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_moist_suspicious_sand")))
    );
    public static final Block SLIMED_WET_SUSPICIOUS_SAND = new WettableBrushableBlock(
            Wettable.HumidityLevel.WET, SLIMED_WET_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_wet_suspicious_sand")))
    );
    public static final Block SLIMED_SOAKED_SUSPICIOUS_SAND = new SoakedBrushableBlock(
            Wettable.HumidityLevel.SOAKED, SLIMED_SOAKED_SAND, SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND,
            BlockBehaviour.Properties.ofFullCopy(SUSPICIOUS_SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "slimed_soaked_suspicious_sand")))
    );

    public static final Block SLIMED_BLACK_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_black_concrete_powder"))));
    public static final Block SLIMED_BLUE_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.BLUE.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_blue_concrete_powder"))));
    public static final Block SLIMED_BROWN_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.BROWN.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_brown_concrete_powder"))));
    public static final Block SLIMED_CYAN_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.CYAN.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_cyan_concrete_powder"))));
    public static final Block SLIMED_GRAY_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.GRAY.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_gray_concrete_powder"))));
    public static final Block SLIMED_GREEN_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.GREEN.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_green_concrete_powder"))));
    public static final Block SLIMED_LIGHT_BLUE_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.LIGHT_BLUE.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_light_blue_concrete_powder"))));
    public static final Block SLIMED_LIGHT_GRAY_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.LIGHT_GRAY.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_light_gray_concrete_powder"))));
    public static final Block SLIMED_LIME_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.LIME.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_lime_concrete_powder"))));
    public static final Block SLIMED_MAGENTA_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.MAGENTA.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_magenta_concrete_powder"))));
    public static final Block SLIMED_ORANGE_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.ORANGE.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_orange_concrete_powder"))));
    public static final Block SLIMED_PINK_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.PINK.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_pink_concrete_powder"))));
    public static final Block SLIMED_PURPLE_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.PURPLE.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_purple_concrete_powder"))));
    public static final Block SLIMED_RED_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.RED.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_red_concrete_powder"))));
    public static final Block SLIMED_WHITE_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.WHITE.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_white_concrete_powder"))));
    public static final Block SLIMED_YELLOW_CONCRETE_POWDER = new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.YELLOW.getMapColor()).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "slimed_yellow_concrete_powder"))));

    public static TagKey<Block> WettableTag = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "wettable"));
    public static TagKey<Block> DriableTag = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "driable"));
    public static TagKey<Block> SuspiciousSlimedTag = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "suspicious_slimed"));

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
        registerBlockItem("slimed_sand", SLIMED_SAND);
        registerBlockItem("slimed_moist_sand", SLIMED_MOIST_SAND);
        registerBlockItem("slimed_wet_sand", SLIMED_WET_SAND);
        registerBlockItem("slimed_soaked_sand", SLIMED_SOAKED_SAND);
        registerBlockItem("moist_red_sand", MOIST_RED_SAND);
        registerBlockItem("wet_red_sand", WET_RED_SAND);
        registerBlockItem("soaked_red_sand", SOAKED_RED_SAND);
        registerBlockItem("slimed_red_sand", SLIMED_RED_SAND);
        registerBlockItem("slimed_moist_red_sand", SLIMED_MOIST_RED_SAND);
        registerBlockItem("slimed_wet_red_sand", SLIMED_WET_RED_SAND);
        registerBlockItem("slimed_soaked_red_sand", SLIMED_SOAKED_RED_SAND);
        registerBlockItem("moist_suspicious_sand", MOIST_SUSPICIOUS_SAND);
        registerBlockItem("wet_suspicious_sand", WET_SUSPICIOUS_SAND);
        registerBlockItem("soaked_suspicious_sand", SOAKED_SUSPICIOUS_SAND);
        registerBlockItem("slimed_suspicious_sand", SLIMED_SUSPICIOUS_SAND);
        registerBlockItem("slimed_moist_suspicious_sand", SLIMED_MOIST_SUSPICIOUS_SAND);
        registerBlockItem("slimed_wet_suspicious_sand", SLIMED_WET_SUSPICIOUS_SAND);
        registerBlockItem("slimed_soaked_suspicious_sand", SLIMED_SOAKED_SUSPICIOUS_SAND);

        registerBlockItem("slimed_black_concrete_powder", SLIMED_BLACK_CONCRETE_POWDER);
        registerBlockItem("slimed_blue_concrete_powder", SLIMED_BLUE_CONCRETE_POWDER);
        registerBlockItem("slimed_brown_concrete_powder", SLIMED_BROWN_CONCRETE_POWDER);
        registerBlockItem("slimed_cyan_concrete_powder", SLIMED_CYAN_CONCRETE_POWDER);
        registerBlockItem("slimed_gray_concrete_powder", SLIMED_GRAY_CONCRETE_POWDER);
        registerBlockItem("slimed_green_concrete_powder", SLIMED_GREEN_CONCRETE_POWDER);
        registerBlockItem("slimed_light_blue_concrete_powder", SLIMED_LIGHT_BLUE_CONCRETE_POWDER);
        registerBlockItem("slimed_light_gray_concrete_powder", SLIMED_LIGHT_GRAY_CONCRETE_POWDER);
        registerBlockItem("slimed_lime_concrete_powder", SLIMED_LIME_CONCRETE_POWDER);
        registerBlockItem("slimed_magenta_concrete_powder", SLIMED_MAGENTA_CONCRETE_POWDER);
        registerBlockItem("slimed_orange_concrete_powder", SLIMED_ORANGE_CONCRETE_POWDER);
        registerBlockItem("slimed_pink_concrete_powder", SLIMED_PINK_CONCRETE_POWDER);
        registerBlockItem("slimed_purple_concrete_powder", SLIMED_PURPLE_CONCRETE_POWDER);
        registerBlockItem("slimed_red_concrete_powder", SLIMED_RED_CONCRETE_POWDER);
        registerBlockItem("slimed_white_concrete_powder", SLIMED_WHITE_CONCRETE_POWDER);
        registerBlockItem("slimed_yellow_concrete_powder", SLIMED_YELLOW_CONCRETE_POWDER);
    }

    public static void initCreativePlacement() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.insertAfter(Items.SAND, MOIST_SAND);
            content.insertAfter(MOIST_SAND, WET_SAND);
            content.insertAfter(WET_SAND, SOAKED_SAND);
            content.insertAfter(SOAKED_SAND, SLIMED_SAND);
            content.insertAfter(SLIMED_SAND, SLIMED_MOIST_SAND);
            content.insertAfter(SLIMED_MOIST_SAND, SLIMED_WET_SAND);
            content.insertAfter(SLIMED_WET_SAND, SLIMED_SOAKED_SAND);
            content.insertAfter(Items.RED_SAND, MOIST_RED_SAND);
            content.insertAfter(MOIST_RED_SAND, WET_RED_SAND);
            content.insertAfter(WET_RED_SAND, SOAKED_RED_SAND);
            content.insertAfter(SOAKED_RED_SAND, SLIMED_RED_SAND);
            content.insertAfter(SLIMED_RED_SAND, SLIMED_MOIST_RED_SAND);
            content.insertAfter(SLIMED_MOIST_RED_SAND, SLIMED_WET_RED_SAND);
            content.insertAfter(SLIMED_WET_RED_SAND, SLIMED_SOAKED_RED_SAND);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.insertAfter(Items.SUSPICIOUS_SAND, MOIST_SUSPICIOUS_SAND);
            content.insertAfter(MOIST_SUSPICIOUS_SAND, WET_SUSPICIOUS_SAND);
            content.insertAfter(WET_SUSPICIOUS_SAND, SOAKED_SUSPICIOUS_SAND);
            content.insertAfter(SOAKED_SUSPICIOUS_SAND, SLIMED_SUSPICIOUS_SAND);
            content.insertAfter(SLIMED_SUSPICIOUS_SAND, SLIMED_MOIST_SUSPICIOUS_SAND);
            content.insertAfter(SLIMED_MOIST_SUSPICIOUS_SAND, SLIMED_WET_SUSPICIOUS_SAND);
            content.insertAfter(SLIMED_WET_SUSPICIOUS_SAND, SLIMED_SOAKED_SUSPICIOUS_SAND);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS).register(content -> {
            content.insertAfter(Blocks.CONCRETE_POWDER.pink(), SLIMED_WHITE_CONCRETE_POWDER);
            content.insertAfter(SLIMED_WHITE_CONCRETE_POWDER, SLIMED_LIGHT_GRAY_CONCRETE_POWDER);
            content.insertAfter(SLIMED_LIGHT_GRAY_CONCRETE_POWDER, SLIMED_GRAY_CONCRETE_POWDER);
            content.insertAfter(SLIMED_GRAY_CONCRETE_POWDER, SLIMED_BLACK_CONCRETE_POWDER);
            content.insertAfter(SLIMED_BLACK_CONCRETE_POWDER, SLIMED_BROWN_CONCRETE_POWDER);
            content.insertAfter(SLIMED_BROWN_CONCRETE_POWDER, SLIMED_RED_CONCRETE_POWDER);
            content.insertAfter(SLIMED_RED_CONCRETE_POWDER, SLIMED_ORANGE_CONCRETE_POWDER);
            content.insertAfter(SLIMED_ORANGE_CONCRETE_POWDER, SLIMED_YELLOW_CONCRETE_POWDER);
            content.insertAfter(SLIMED_YELLOW_CONCRETE_POWDER, SLIMED_LIME_CONCRETE_POWDER);
            content.insertAfter(SLIMED_LIME_CONCRETE_POWDER, SLIMED_GREEN_CONCRETE_POWDER);
            content.insertAfter(SLIMED_GREEN_CONCRETE_POWDER, SLIMED_CYAN_CONCRETE_POWDER);
            content.insertAfter(SLIMED_CYAN_CONCRETE_POWDER, SLIMED_LIGHT_BLUE_CONCRETE_POWDER);
            content.insertAfter(SLIMED_LIGHT_BLUE_CONCRETE_POWDER, SLIMED_BLUE_CONCRETE_POWDER);
            content.insertAfter(SLIMED_BLUE_CONCRETE_POWDER, SLIMED_PURPLE_CONCRETE_POWDER);
            content.insertAfter(SLIMED_PURPLE_CONCRETE_POWDER, SLIMED_MAGENTA_CONCRETE_POWDER);
            content.insertAfter(SLIMED_MAGENTA_CONCRETE_POWDER, SLIMED_PINK_CONCRETE_POWDER);
        });
    }
}
