package net.hearthian.wetsand.mixin.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registry;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.hearthian.wetsand.utils.initializer.*;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @ModifyArg(
        method="create",
        at=@At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;"),
        index=2
    )
    private static <T> T registerMixin(Registry<? super T> registry, String id, T entry) {
//        if (id.equals("brushable_block")) {
//            return (T) BlockEntityType.Builder.create(BrushableBlockEntity::new, Blocks.SUSPICIOUS_SAND, Blocks.SUSPICIOUS_GRAVEL, MOIST_SUSPICIOUS_SAND, WET_SUSPICIOUS_SAND, SOAKED_SUSPICIOUS_SAND)
//                    .build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, "brushable_block"));
//        }

        return entry;
    }
}
