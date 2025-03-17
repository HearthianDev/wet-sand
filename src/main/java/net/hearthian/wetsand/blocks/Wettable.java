package net.hearthian.wetsand.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static net.hearthian.wetsand.utils.initializer.*;

public interface Wettable {
  int HUMIDITY_RANGE = 3;

  Supplier<BiMap<Object, Object>> HUMIDITY_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.builder()
    .put(Blocks.SAND, MOIST_SAND).put(MOIST_SAND, DAMP_SAND).put(DAMP_SAND, WET_SAND)
    .put(Blocks.RED_SAND, MOIST_RED_SAND).put(MOIST_RED_SAND, DAMP_RED_SAND).put(DAMP_RED_SAND, WET_RED_SAND)
    .build()
  );
//  Supplier<BiMap<Object, Object>> HUMIDITY_LEVEL_DECREASES = Suppliers.memoize(() -> HUMIDITY_LEVEL_INCREASES.get().inverse());

  HumidityLevel getHumidityLevel();

  default Optional<BlockState> tryDrench(BlockState state, ServerWorld world, BlockPos pos) {
//    LOGGER.info("TRY DRENCHING...");
    int currentLevel = this.getHumidityLevel().ordinal();

    AtomicInteger maxHumidityLevel = new AtomicInteger(0);

    BlockPos.findClosest(pos, HUMIDITY_RANGE, HUMIDITY_RANGE, (conditionPos) -> {
//      LOGGER.info("LOOKING FOR WATER...");
      if (world.getFluidState(conditionPos).isOf(Fluids.WATER)) {
        int distance = conditionPos.getChebyshevDistance(pos);
//        LOGGER.info("SEARCHING FOR CLOSE WATER: {} {} {}", currentLevel, distance, conditionPos);
        if ((HUMIDITY_RANGE - currentLevel) >= distance) {
          maxHumidityLevel.set(HUMIDITY_RANGE - distance + 1);
          return true;
        }
      }

      return false;
    });

//    LOGGER.info("MAX HUMIDITY LEVEL... {}", maxHumidityLevel);

    BlockPos[] adjacent = { pos.north(), pos.south(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down() };

    for (BlockPos conditionPos : adjacent) {
      if (world.getFluidState(conditionPos).isOf(Fluids.WATER)) {
        return this.getHumidityResult(state);
      }
      if (world.getBlockState(conditionPos).isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("wet-sand", "wettable")))) {
        int humidityLevel = 0;

        try {
          humidityLevel = ((WettableBlock) world.getBlockState(conditionPos).getBlock()).getHumidityLevel().ordinal();
        } catch (Exception e) {
          try {
            humidityLevel = ((WettableFallingBlock) world.getBlockState(conditionPos).getBlock()).getHumidityLevel().ordinal();
          } catch (Exception ignored) { }
        }

        if (humidityLevel > currentLevel && currentLevel < maxHumidityLevel.get()) {
          return this.getHumidityResult(state);
        }
      }

    }

    return Optional.empty();
  }

  default void tickHumidity(BlockState state, ServerWorld world, BlockPos pos) {
    this.tryDrench(state, world, pos).ifPresent((drenched) -> world.setBlockState(pos, drenched));
  }

//  static Optional<Block> getDecreasedHumidityBlock(Block block) {
//    return Optional.ofNullable((Block)(HUMIDITY_LEVEL_DECREASES.get()).get(block));
//  }

//  default Optional<BlockState> getDecreasedHumidityState(BlockState state) {
//    return getDecreasedHumidityBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
//  }

  default Optional<Block> getIncreasedHumidityBlock(Block block) {
    return Optional.ofNullable((Block)(HUMIDITY_LEVEL_INCREASES.get()).get(block));
  }

  default Optional<BlockState> getHumidityResult(BlockState state) {
    return getIncreasedHumidityBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
  }

  enum HumidityLevel implements StringIdentifiable {
    UNAFFECTED("unaffected"),
    MOIST("moist"),
    DAMP("damp"),
    WET("wet");

    public static final Codec<HumidityLevel> CODEC = StringIdentifiable.createCodec(HumidityLevel::values);
    private final String id;

    HumidityLevel(final String id) {
      this.id = id;
    }

    public String asString() {
      return this.id;
    }
  }
}
