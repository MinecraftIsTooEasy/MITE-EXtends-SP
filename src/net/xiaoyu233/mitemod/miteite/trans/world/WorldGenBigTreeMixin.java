package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.WorldGenBigTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldGenBigTree.class)
public class WorldGenBigTreeMixin {
    @Shadow
    int heightLimit;

    public int getHeightLimit() {
        return heightLimit;
    }
}
