package net.hearthian.wetsand.mixin.block;

import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.hearthian.wetsand.utils.initializer.SuspiciousSlimedTag;

@Mixin(BrushableBlockEntity.class)
public class BrushableBlockEntityMixin extends BlockEntity implements BrushableBlockEntityAccessor {
    @Shadow
    private ItemStack item;

    public BrushableBlockEntityMixin(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.BRUSHABLE_BLOCK, pos, state);
    }

    @Inject(method = "brush", at = @At(value = "HEAD"), cancellable = true)
    private void brushMixin(long gameTime, ServerLevel level, LivingEntity user, Direction direction, ItemStack brush, CallbackInfoReturnable<Boolean> cir) {
        Block block = level.getBlockState(this.worldPosition).getBlock();
        if (block.defaultBlockState().is(SuspiciousSlimedTag)) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public void wet_sand$setItem(ItemStack item) {
        this.item = item;
    }
}