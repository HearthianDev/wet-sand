package net.hearthian.wetsand.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;

public class WettableFallingBlock extends FallingBlock implements Wettable {
    private final HumidityLevel humidityLevel;

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }

    public WettableFallingBlock(HumidityLevel humidityLevel, Settings settings) {
        super(settings);
        this.humidityLevel = humidityLevel;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickHumidity(state, world, pos);
    }

    public boolean hasRandomTicks(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    public HumidityLevel getHumidityLevel() {
        return this.humidityLevel;
    }
}
