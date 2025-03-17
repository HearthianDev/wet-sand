package net.hearthian.wetsand.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class WettableFallingBlock extends FallingBlock implements Wettable {
    public static final MapCodec<WettableFallingBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(HumidityLevel.CODEC.fieldOf("humidity_state").forGetter(Wettable::getHumidityLevel), createSettingsCodec()).apply(instance, WettableFallingBlock::new));
    private final HumidityLevel humidityLevel;

    public MapCodec<WettableFallingBlock> getCodec() {
        return CODEC;
    }

    public WettableFallingBlock(HumidityLevel humidityLevel, Settings settings) {
        super(settings);
        this.humidityLevel = humidityLevel;
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        LOGGER.info("GETS RANDOM TICK...");
        this.tickHumidity(state, world, pos);
    }

    protected boolean hasRandomTicks(BlockState state) {
        return getIncreasedHumidityBlock(state.getBlock()).isPresent();
    }

    public HumidityLevel getHumidityLevel() {
        return this.humidityLevel;
    }
}
