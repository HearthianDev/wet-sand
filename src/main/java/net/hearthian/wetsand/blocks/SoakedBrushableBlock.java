package net.hearthian.wetsand.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SoakedBrushableBlock extends WettableBrushableBlock implements Wettable {
//    public static final MapCodec<SoakedBlock> CODEC = createCodec(SoakedBrushableBlock::new);
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);

//    @Override
//    public MapCodec<SoakedBrushableBlock> getCodec() {
//        return CODEC;
//    }

    public SoakedBrushableBlock(Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Settings settings) {
        super(HumidityLevel.SOAKED, baseBlock, brushingSound, brushingCompleteSound, settings);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 0.2F;
    }

    public HumidityLevel getHumidityLevel() {
        return HumidityLevel.SOAKED;
    }
}
