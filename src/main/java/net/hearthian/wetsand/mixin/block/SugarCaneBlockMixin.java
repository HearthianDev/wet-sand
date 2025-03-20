package net.hearthian.wetsand.mixin.block;

import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Inject(
        method="canPlaceAt",
        at=@At("HEAD"),
        cancellable=true
    )
    // Note that this is a generic method, in Mixin you'll have to use
    // Object to replace type parameters
    private void canPlaceAtMixin(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Object> cir) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("wet-sand", "can_grow_sugar_cane")))) {
            cir.setReturnValue(true);
        }
    }
}
