package net.hearthian.wetsand.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.hearthian.wetsand.blocks.Wettable;
import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class Events {
    public static void registerDry() {
        UseBlockCallback.EVENT.register((player, world, hand, blockHit) -> {
            BlockState state = world.getBlockState(blockHit.getBlockPos());

            if (!player.isSpectator()
                && world instanceof ServerWorld serverWorld
                && state.isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("wet-sand", "wettable")))
            ) {
                if (player.getStackInHand(hand).isOf(Items.GLASS_BOTTLE) && state.getBlock() instanceof Wettable wettableBlock) {
                    BlockPos pos = blockHit.getBlockPos();
                    wettableBlock.getDecreasedHumidityState(state).ifPresent(blockState -> {
                        BlockEntity entity = serverWorld.getBlockEntity(pos);

                        player.getMainHandStack().decrement(1);
                        player.giveItemStack(Items.POTION.getDefaultStack());
                        serverWorld.setBlockState(pos, blockState);
//                        serverWorld.setBlockState(pos, blockState, 2, 0);
//                        entity.cancelRemoval();

                        if (entity instanceof BrushableBlockEntity BrushableBlockEntity) {
                            BlockEntity entity2 = serverWorld.getBlockEntity(pos);
                            if (entity2 instanceof BrushableBlockEntity brushableBlockEntity2) {
                                ((BrushableBlockEntityAccessor) brushableBlockEntity2).wet_sand$setItem(BrushableBlockEntity.getItem());
//                                brushableBlockEntity2.markDirty();
//                                serverWorld.updateListeners(pos, state, state, Block.NOTIFY_ALL);
                            }
                        }
                    });
                }
            }

            return ActionResult.PASS;
        });
    }
}
