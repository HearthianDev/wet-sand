package net.hearthian.wetsand.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.Codec;
import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
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
    .put(Blocks.SAND, MOIST_SAND).put(MOIST_SAND, WET_SAND).put(WET_SAND, SOAKED_SAND)
    .put(Blocks.SUSPICIOUS_SAND, MOIST_SUSPICIOUS_SAND).put(MOIST_SUSPICIOUS_SAND, WET_SUSPICIOUS_SAND).put(WET_SUSPICIOUS_SAND, SOAKED_SUSPICIOUS_SAND)
    .put(Blocks.RED_SAND, MOIST_RED_SAND).put(MOIST_RED_SAND, WET_RED_SAND).put(WET_RED_SAND, SOAKED_RED_SAND)
    .build()
  );
  Supplier<BiMap<Object, Object>> HUMIDITY_LEVEL_DECREASES = Suppliers.memoize(() -> HUMIDITY_LEVEL_INCREASES.get().inverse());

  HumidityLevel getHumidityLevel();

  default Optional<BlockState> tryDrench(BlockState state, ServerWorld world, BlockPos pos) {
//    LOGGER.info("TRY DRENCHING...");
    int currentLevel = this.getHumidityLevel().ordinal();

    AtomicInteger maxHumidityLevel = new AtomicInteger(0);

    BlockPos.findClosest(pos, HUMIDITY_RANGE, HUMIDITY_RANGE, (conditionPos) -> {
      if (world.getFluidState(conditionPos).isOf(Fluids.WATER) || world.getFluidState(conditionPos).isOf(Fluids.FLOWING_WATER)) {
        int distance = conditionPos.getChebyshevDistance(pos);
        if ((HUMIDITY_RANGE - currentLevel) >= distance) {
          maxHumidityLevel.set(HUMIDITY_RANGE - distance + 1);
          return true;
        }
      }

      return false;
    });

    BlockPos[] adjacent = { pos.north(), pos.south(), pos.south(), pos.east(), pos.west(), pos.up(), pos.down() };

    for (BlockPos conditionPos : adjacent) {
      if (world.getFluidState(conditionPos).isOf(Fluids.WATER)) {
        return this.getHumidityResult(state);
      }
      if (world.getBlockState(conditionPos).isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("wet-sand", "wettable")))) {
        if (world.getBlockState(conditionPos).getBlock() instanceof Wettable wettable) {
          int humidityLevel = wettable.getHumidityLevel().ordinal();

          if (humidityLevel > currentLevel && currentLevel < maxHumidityLevel.get()) {
            return this.getHumidityResult(state);
          }
        }
      }
    }

    return Optional.empty();
  }

  default void tickHumidity(BlockState state, ServerWorld world, BlockPos pos) {
    BlockEntity entity = world.getBlockEntity(pos);

    if (entity == null || (entity instanceof BrushableBlockEntity && state.get(Properties.DUSTED) == 0)) {
      this.tryDrench(state, world, pos).ifPresent((drenched) -> {

  //        entity.cancelRemoval();
        world.setBlockState(pos, drenched);
//        world.setBlockState(pos, drenched, 2, 0);

        if (entity instanceof BrushableBlockEntity brushableBlockEntity) {
          BlockEntity entity2 = world.getBlockEntity(pos);
          if (entity2 instanceof BrushableBlockEntity brushableBlockEntity2) {
            ((BrushableBlockEntityAccessor) brushableBlockEntity2).wet_sand$setItem(brushableBlockEntity.getItem());
          }
        }
      });
    }
  }

  static Optional<Block> getDecreasedHumidityBlock(Block block) {
    return Optional.ofNullable((Block)(HUMIDITY_LEVEL_DECREASES.get()).get(block));
  }

  default Optional<BlockState> getDecreasedHumidityState(BlockState state) {
    return getDecreasedHumidityBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
  }

  default Optional<Block> getIncreasedHumidityBlock(Block block) {
    return Optional.ofNullable((Block)(HUMIDITY_LEVEL_INCREASES.get()).get(block));
  }

  default Optional<BlockState> getHumidityResult(BlockState state) {
    return getIncreasedHumidityBlock(state.getBlock()).map((block) -> block.getStateWithProperties(state));
  }

  enum HumidityLevel implements StringIdentifiable {
    UNAFFECTED("unaffected"),
    MOIST("moist"),
    WET("wet"),
    SOAKED("soaked");

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
