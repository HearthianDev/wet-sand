package net.hearthian.wetsand.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class WettableBlock extends Block implements Wettable {
    private final Wettable.HumidityLevel humidityLevel;

    public WettableBlock(Wettable.HumidityLevel humidityLevel, AbstractBlock.Settings settings) {
        super(settings);
        this.humidityLevel = humidityLevel;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        LOGGER.info("GETS RANDOM TICK...");
        this.tickHumidity(state, world, pos);
    }

    public boolean hasRandomTicks(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    public Wettable.HumidityLevel getHumidityLevel() {
        return this.humidityLevel;
    }
}
