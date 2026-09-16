package net.hearthian.wetsand.mixin.block;

import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BrushableBlockEntity.class)
public class BrushableBlockEntityMixin extends BlockEntity implements BrushableBlockEntityAccessor {
    @Shadow
    private ItemStack item;

    public BrushableBlockEntityMixin(BlockPos pos, BlockState state) {
        super(BlockEntityTypes.BRUSHABLE_BLOCK, pos, state);
    }

    @Override
    public void wet_sand$setItem(ItemStack item) {
        this.item = item;
    }
}