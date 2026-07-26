package net.hearthian.wetsand.mixin.block;

import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.hearthian.wetsand.WetSand.MOD_ID;
import static net.hearthian.wetsand.utils.initializer.*;
import static net.hearthian.wetsand.utils.initializer.SAND;

@Mixin(Registry.class)
public interface RegistryMixin {
    @Inject(
        method="register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/ResourceKey;Ljava/lang/Object;)Ljava/lang/Object;",
        at=@At("HEAD"),
        cancellable=true
    )
    // Note that this is a generic method, in Mixin you'll have to use
    // Object to replace type parameters
    private static <V, T> void  onRegister(Registry<@NotNull V> reg, ResourceKey<@NotNull V> id, T entry, CallbackInfoReturnable<Object> cir) {
        if (reg != BuiltInRegistries.BLOCK) return;
        if (id.identifier().toString().equals("minecraft:sand")) {
            ((WritableRegistry) reg).register(id, SAND, RegistrationInfo.BUILT_IN);
            ((WritableRegistry) reg).register(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "vanilla_sand")), entry, RegistrationInfo.BUILT_IN);
            cir.setReturnValue(SAND);
        }
        if (id.identifier().toString().equals("minecraft:red_sand")) {
            ((WritableRegistry) reg).register(id, RED_SAND, RegistrationInfo.BUILT_IN);
            ((WritableRegistry) reg).register(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "vanilla_red_sand")), entry, RegistrationInfo.BUILT_IN);
            cir.setReturnValue(RED_SAND);
        }
        if (id.identifier().toString().equals("minecraft:suspicious_sand")) {
            ((WritableRegistry) reg).register(id, SUSPICIOUS_SAND, RegistrationInfo.BUILT_IN);
            ((WritableRegistry) reg).register(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "vanilla_suspicious_sand")), entry, RegistrationInfo.BUILT_IN);
            cir.setReturnValue(SUSPICIOUS_SAND);
        }
    }
}
