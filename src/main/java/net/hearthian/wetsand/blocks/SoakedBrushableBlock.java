package net.hearthian.wetsand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import static net.hearthian.wetsand.utils.initializer.SuspiciousSlimedTag;

public class SoakedBrushableBlock extends WettableBrushableBlock implements Wettable {
    protected static final VoxelShape COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);
    private final HumidityLevel humidityLevel;


    public SoakedBrushableBlock(HumidityLevel humidityLevel, Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Properties settings) {
        super(humidityLevel, baseBlock, brushingSound, brushingCompleteSound, settings);
        this.humidityLevel = humidityLevel;
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return getDecreasedHumidityBlock(state.getBlock()).isPresent();
    }

    @Override
    public void onBrokenAfterFall(@NonNull Level level, @NonNull BlockPos pos, FallingBlockEntity entity) {
        if (!entity.getBlockState().is(SuspiciousSlimedTag)) {
            super.onBrokenAfterFall(level, pos, entity);
        }
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected @NotNull VoxelShape getBlockSupportShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos) {
        return Shapes.block();
    }

    @Override
    protected @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.block();
    }

    @Override
    protected boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType type) {
        return false;
    }

    @Override
    protected float getShadeBrightness(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos) {
        return 0.2F;
    }

    public HumidityLevel getHumidityLevel() {
        return humidityLevel;
    }
}
