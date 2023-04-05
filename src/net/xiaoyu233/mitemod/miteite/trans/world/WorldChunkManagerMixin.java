package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.WorldChunkManager;
import net.xiaoyu233.mitemod.miteite.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
@Mixin(WorldChunkManager.class)
public class WorldChunkManagerMixin {
    @Shadow
    private List biomesToSpawnIn;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo){
        this.biomesToSpawnIn.add(BiomeBases.volcano);
    }
}
