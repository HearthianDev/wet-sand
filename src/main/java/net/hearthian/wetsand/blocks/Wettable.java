package net.hearthian.wetsand.blocks;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.Codec;
import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
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
  Supplier<BiMap<Object, Object>> HUMIDITY_LEVEL_DECREASES = Suppliers.memoize(() -> Objects.requireNonNull(HUMIDITY_LEVEL_INCREASES.get()).inverse());

  HumidityLevel getHumidityLevel();

  default Optional<BlockState> tryDrench(BlockState state, ServerLevel world, BlockPos pos) {
    int currentLevel = this.getHumidityLevel().ordinal();

    AtomicInteger maxHumidityLevel = new AtomicInteger(0);

    for (BlockPos conditionPos : BlockPos.withinBoxByManhattanDistance(pos, HUMIDITY_RANGE, HUMIDITY_RANGE, HUMIDITY_RANGE)) {
      if (world.getFluidState(conditionPos).is(Fluids.WATER) || world.getFluidState(conditionPos).is(Fluids.FLOWING_WATER)) {
        int distance = conditionPos.distChessboard(pos);
        if ((HUMIDITY_RANGE - currentLevel) >= distance) {
          maxHumidityLevel.set(HUMIDITY_RANGE - distance + 1);
          break;
        }
      }
    }

    BlockPos[] adjacent = { pos.north(), pos.south(), pos.south(), pos.east(), pos.west(), pos.above(), pos.below() };

    for (BlockPos conditionPos : adjacent) {
      if (world.getFluidState(conditionPos).is(Fluids.WATER)) {
        return this.getHumidityResult(state);
      }
      if (world.getBlockState(conditionPos).is(TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("wet-sand", "wettable")))) {
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

  default void tickHumidity(BlockState state, ServerLevel world, BlockPos pos) {
    BlockEntity entity = world.getBlockEntity(pos);

    if (entity == null || (entity instanceof BrushableBlockEntity && state.getValue(BlockStateProperties.DUSTED) == 0)) {
      this.tryDrench(state, world, pos).ifPresent((drenched) -> {

  //        entity.cancelRemoval();
        world.setBlockAndUpdate(pos, drenched);
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
    return getDecreasedHumidityBlock(state.getBlock()).map((block) -> block.withPropertiesOf(state));
  }

  default Optional<Block> getIncreasedHumidityBlock(Block block) {
    return Optional.ofNullable((Block)(HUMIDITY_LEVEL_INCREASES.get()).get(block));
  }

  default Optional<BlockState> getHumidityResult(BlockState state) {
    return getIncreasedHumidityBlock(state.getBlock()).map((block) -> block.withPropertiesOf(state));
  }

  enum HumidityLevel implements StringRepresentable {
    UNAFFECTED("unaffected"),
    MOIST("moist"),
    WET("wet"),
    SOAKED("soaked");

    public static final Codec<HumidityLevel> CODEC = StringRepresentable.fromEnum(HumidityLevel::values);
    private final String id;

    HumidityLevel(final String id) {
      this.id = id;
    }

    public @NotNull String getSerializedName() {
      return this.id;
    }
  }
}
