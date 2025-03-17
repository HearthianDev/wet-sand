package net.hearthian.wetsand.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class WettableBlock extends Block implements Wettable {
    public static final MapCodec<WettableBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(HumidityLevel.CODEC.fieldOf("humidity_state").forGetter(Wettable::getHumidityLevel), createSettingsCodec()).apply(instance, WettableBlock::new));
    private final Wettable.HumidityLevel humidityLevel;

    public MapCodec<WettableBlock> getCodec() {
        return CODEC;
    }

    public WettableBlock(Wettable.HumidityLevel humidityLevel, AbstractBlock.Settings settings) {
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

    public Wettable.HumidityLevel getHumidityLevel() {
        return this.humidityLevel;
    }
}
