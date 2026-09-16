package net.hearthian.wetsand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WettableBlock extends Block implements Wettable {
    private final HumidityLevel humidityLevel;

    public WettableBlock(HumidityLevel humidityLevel, BlockBehaviour.Properties settings) {
        super(settings);
        this.humidityLevel = humidityLevel;
    }

    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
//        LOGGER.info("GETS RANDOM TICK...");
        this.tickHumidity(state, world, pos);
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    public HumidityLevel getHumidityLevel() {
        return this.humidityLevel;
    }
}
