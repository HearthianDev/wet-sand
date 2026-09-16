package net.hearthian.wetsand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WettableFallingBlock extends FallingBlock implements Wettable {
    private final HumidityLevel humidityLevel;

    @Override
    public int getDustColor(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos) {
        return 0;
    }

    public WettableFallingBlock(HumidityLevel humidityLevel, Properties settings) {
        super(settings);
        this.humidityLevel = humidityLevel;
    }

    protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.tickHumidity(state, world, pos);
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    public HumidityLevel getHumidityLevel() {
        return this.humidityLevel;
    }
}
