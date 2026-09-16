package net.hearthian.wetsand.mixin.block;

import net.minecraft.world.level.block.entity.BlockEntityTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Set;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.hearthian.wetsand.utils.initializer.*;

@Mixin(BlockEntityTypes.class)
public class BlockEntityTypeMixin {
    @ModifyArg(method="register", at= @At(value = "INVOKE", target = "Ljava/util/Set;of([Ljava/lang/Object;)Ljava/util/Set;"))
    private static <E> E[] setOf(E[] elements) {
        // TODO: Add items instead of rebuilding them (for compatibility with other mods)
        if (Set.of(Blocks.SUSPICIOUS_SAND, Blocks.SUSPICIOUS_GRAVEL).equals(Set.of(elements))) {
            Block[] extra = { Blocks.SUSPICIOUS_SAND, Blocks.SUSPICIOUS_GRAVEL, MOIST_SUSPICIOUS_SAND, WET_SUSPICIOUS_SAND, SOAKED_SUSPICIOUS_SAND };
            return (E[]) extra;
        }

        return elements;
    }
}
