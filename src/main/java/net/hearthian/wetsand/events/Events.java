package net.hearthian.wetsand.events;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.hearthian.wetsand.blocks.Wettable;
import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;
import java.util.function.Supplier;

import static net.hearthian.wetsand.utils.initializer.*;

public class Events {
    static TagKey<Block> WettableTag = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "wettable"));
    static TagKey<Block> DriableTag = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "driable"));

    static Supplier<BiMap<Object, Object>> SLIMED_CONCRETE_POWDER_EQUIVALENCE = Suppliers.memoize(() -> ImmutableBiMap.builder()
            .put(Blocks.CONCRETE_POWDER.black(), SLIMED_BLACK_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.blue(), SLIMED_BLUE_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.brown(), SLIMED_BROWN_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.cyan(), SLIMED_CYAN_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.gray(), SLIMED_GRAY_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.green(), SLIMED_GREEN_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.lightBlue(), SLIMED_LIGHT_BLUE_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.lightGray(), SLIMED_LIGHT_GRAY_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.lime(), SLIMED_LIME_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.magenta(), SLIMED_MAGENTA_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.orange(), SLIMED_ORANGE_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.pink(), SLIMED_PINK_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.purple(), SLIMED_PURPLE_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.red(), SLIMED_RED_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.white(), SLIMED_WHITE_CONCRETE_POWDER)
            .put(Blocks.CONCRETE_POWDER.yellow(), SLIMED_YELLOW_CONCRETE_POWDER)
            .build()
    );

    private static void setBlockResult(BlockState blockState, ServerLevel serverWorld, Player player, BlockHitResult blockHit, boolean returnBottle) {
        BlockPos pos = blockHit.getBlockPos();
        BlockEntity entity = serverWorld.getBlockEntity(pos);

        player.getMainHandItem().shrink(1);

        if (returnBottle) {
            player.addItem(Items.POTION.getDefaultInstance());
        }
        serverWorld.setBlockAndUpdate(pos, blockState);
//        serverWorld.setBlockState(pos, blockState, 2, 0);
//        entity.cancelRemoval();

        if (entity instanceof BrushableBlockEntity BrushableBlockEntity) {
            BlockEntity entity2 = serverWorld.getBlockEntity(pos);
            if (entity2 instanceof BrushableBlockEntity brushableBlockEntity2) {
                ((BrushableBlockEntityAccessor) brushableBlockEntity2).wet_sand$setItem(BrushableBlockEntity.getItem());
//                    brushableBlockEntity2.markDirty();
//                    serverWorld.updateListeners(pos, state, state, Block.NOTIFY_ALL);
            }
        }
    }

    // Only driable blocks can be dried
    public static void registerDry() {
        UseBlockCallback.EVENT.register((player, world, hand, blockHit) -> {
            BlockState state = world.getBlockState(blockHit.getBlockPos());

            if (!player.isSpectator() && world instanceof ServerLevel serverWorld && state.is(DriableTag)) {
                if (player.getItemInHand(hand).is(Items.GLASS_BOTTLE) && state.getBlock() instanceof Wettable wettableBlock) {
                    wettableBlock.getDecreasedHumidityState(state).ifPresent(blockState -> setBlockResult(blockState, serverWorld, player, blockHit, true));
                }
            }

            return InteractionResult.PASS;
        });
    }

    // Only wettable blocks can be slimed
    public static void registerSlime() {
        UseBlockCallback.EVENT.register((player, world, hand, blockHit) -> {
            BlockState state = world.getBlockState(blockHit.getBlockPos());

            if (!player.isSpectator() && world instanceof ServerLevel serverWorld && (state.is(WettableTag) || state.is(BlockTags.CONCRETE_POWDERS))) {
                if (player.getItemInHand(hand).is(Items.SLIME_BALL)) {
                    if (state.is(BlockTags.CONCRETE_POWDERS)) {
                        Optional.ofNullable((Block)(SLIMED_CONCRETE_POWDER_EQUIVALENCE.get()).get(state.getBlock()))
                                .map((block) -> block.withPropertiesOf(state))
                                .ifPresent(blockState -> setBlockResult(blockState, serverWorld, player, blockHit, false));
                    }
                    if (state.getBlock() instanceof Wettable wettableBlock) {
                        wettableBlock.getSlimedState(state).ifPresent(blockState -> setBlockResult(blockState, serverWorld, player, blockHit, false));
                    }
                }
            }

            return InteractionResult.PASS;
        });
    }
}
