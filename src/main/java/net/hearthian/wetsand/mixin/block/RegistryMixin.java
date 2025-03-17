package net.hearthian.wetsand.mixin.block;

import net.hearthian.wetsand.blocks.Wettable;
import net.hearthian.wetsand.blocks.WettableFallingBlock;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.hearthian.wetsand.WetSand.MOD_ID;

@Mixin(Registry.class)
public interface RegistryMixin {
    @Inject(
        method="register(Lnet/minecraft/registry/Registry;Lnet/minecraft/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;",
        at=@At("HEAD"),
        cancellable=true
    )
    // Note that this is a generic method, in Mixin you'll have to use
    // Object to replace type parameters
    private static <V, T> void  onRegister(Registry<V> reg, RegistryKey<V> id, T entry, CallbackInfoReturnable<Object> cir) {
        if (reg != Registries.BLOCK) return;
        if (id.getValue().toString().equals("minecraft:sand")) {
            LOGGER.info("REGISTERING SAND... {} {}", entry, entry.equals(Blocks.SAND));
            Block customSand = new WettableFallingBlock(
                    Wettable.HumidityLevel.UNAFFECTED,
                    AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND).registryKey((RegistryKey<Block>) id)
            );
            ((MutableRegistry) reg).add(id, customSand, RegistryEntryInfo.DEFAULT);
            ((MutableRegistry) reg).add(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "vanilla_sand")), entry, RegistryEntryInfo.DEFAULT);
            cir.setReturnValue(customSand);
        }
        if (id.getValue().toString().equals("minecraft:red_sand")) {
            LOGGER.info("REGISTERING RED SAND... {} {}", entry, entry.equals(Blocks.SAND));
            Block customRedSand = new WettableFallingBlock(
                    Wettable.HumidityLevel.UNAFFECTED,
                    AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sounds(BlockSoundGroup.SAND).registryKey((RegistryKey<Block>) id)
            );
            ((MutableRegistry) reg).add(id, customRedSand, RegistryEntryInfo.DEFAULT);
            ((MutableRegistry) reg).add(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "vanilla_red_sand")), entry, RegistryEntryInfo.DEFAULT);
            cir.setReturnValue(customRedSand);
        }
    }
}
