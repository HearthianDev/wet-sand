package net.hearthian.wetsand.blocks;

import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Optional;

import static net.hearthian.wetsand.utils.initializer.SuspiciousSlimedTag;

public class WettableBrushableBlock extends BrushableBlock implements Wettable {
    private final HumidityLevel humidityLevel;

    public WettableBrushableBlock(HumidityLevel humidityLevel, Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Properties settings) {
        super(baseBlock, brushingSound, brushingCompleteSound, settings);
        this.humidityLevel = humidityLevel;
    }

    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.tickHumidity(state, world, pos);
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    @Override
    public void onLand(final @NonNull Level level, final @NonNull BlockPos pos, final @NonNull BlockState state, final @NonNull BlockState replacedBlock, final @NonNull FallingBlockEntity entity) {
        super.onLand(level, pos, state, replacedBlock, entity);
        if (state.is(SuspiciousSlimedTag)) {
            BlockEntity be = level.getBlockEntity(pos);
            CustomData data = entity.get(DataComponents.CUSTOM_DATA);

            if (data != null && be instanceof BrushableBlockEntity brushableBlockEntity) {
                data.copyTag().asCompound().ifPresent(compoundTag ->
                        ((BrushableBlockEntityAccessor) brushableBlockEntity).wet_sand$setItem(ItemStack.CODEC.decode(NbtOps.INSTANCE, compoundTag).getOrThrow().getFirst())
                );

                level.setBlockAndUpdate(pos, state);
            }
        }
    }

    @Override
    public void onBrokenAfterFall(@NonNull Level level, @NonNull BlockPos pos, FallingBlockEntity entity) {
        if (!entity.getBlockState().is(SuspiciousSlimedTag)) {
            super.onBrokenAfterFall(level, pos, entity);
        }

        // TODO: make the block drops with the inside item instead of breaking
        if (entity.getBlockState().is(SuspiciousSlimedTag)) {
            Vec3 centerOfEntity = entity.getBoundingBox().getCenter();
            level.levelEvent(2001, BlockPos.containing(centerOfEntity), Block.getId(entity.getBlockState()));
            level.gameEvent(entity, GameEvent.BLOCK_DESTROY, centerOfEntity);

            level.playSound(null, pos, SoundEvents.SUSPICIOUS_SAND_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void tick(@NotNull BlockState state, ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof BrushableBlockEntity brushableBlockEntity) {
            CustomData data = brushableBlockEntity.components().get(DataComponents.CUSTOM_DATA);

            if (data != null && !data.isEmpty()) {
                Optional<CompoundTag> tag = Objects.requireNonNull(data.copyTag().get("item")).asCompound();
                tag.ifPresent(compoundTag -> ((BrushableBlockEntityAccessor) brushableBlockEntity).wet_sand$setItem(ItemStack.CODEC.decode(NbtOps.INSTANCE, compoundTag).getOrThrow().getFirst()));
            }
            brushableBlockEntity.checkReset(world);
        }

        if (humidityLevel.ordinal() <= 1 && FallingBlock.isFree(world.getBlockState(pos.below())) && pos.getY() >= world.getMinY()) {
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(world, pos, state);
            if (be instanceof BrushableBlockEntity brushableBlockEntity) {
                ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, brushableBlockEntity.getItem()).getOrThrow().asCompound().ifPresent(compound ->
                        fallingBlockEntity.setComponent(DataComponents.CUSTOM_DATA, CustomData.of(compound))
                );
            }
            if (!fallingBlockEntity.getBlockState().is(SuspiciousSlimedTag)) {
                fallingBlockEntity.disableDrop();
            }
        }
    }

    @Override
    public HumidityLevel getHumidityLevel() {
        return humidityLevel;
    }
}