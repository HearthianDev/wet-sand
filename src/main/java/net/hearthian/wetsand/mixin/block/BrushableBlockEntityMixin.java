package net.hearthian.wetsand.mixin.block;

import net.hearthian.wetsand.utils.BrushableBlockEntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BrushableBlockEntity.class)
public class BrushableBlockEntityMixin extends BlockEntity implements BrushableBlockEntityAccessor {
    @Shadow
    private ItemStack item;

    public BrushableBlockEntityMixin(BlockPos pos, BlockState state) {
        super(BlockEntityType.BRUSHABLE_BLOCK, pos, state);
    }

    @Override
    public void wet_sand$setItem(ItemStack item) {
        this.item = item;
    }
}