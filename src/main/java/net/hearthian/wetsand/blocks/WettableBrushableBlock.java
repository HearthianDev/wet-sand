package net.hearthian.wetsand.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class WettableBrushableBlock extends BrushableBlock implements Wettable {
    private final Wettable.HumidityLevel humidityLevel;

    public WettableBrushableBlock(Wettable.HumidityLevel humidityLevel, Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Settings settings) {
        super(baseBlock, brushingSound, brushingCompleteSound, settings);
        this.humidityLevel = humidityLevel;
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickHumidity(state, world, pos);
    }

    protected boolean hasRandomTicks(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockEntity(pos) instanceof BrushableBlockEntity brushableBlockEntity) {
            brushableBlockEntity.scheduledTick(world);
        }

        if (humidityLevel.ordinal() <= 1 && FallingBlock.canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
            fallingBlockEntity.setDestroyedOnLanding();
        }
    }

    @Override
    public Wettable.HumidityLevel getHumidityLevel() {
        return humidityLevel;
    }
}