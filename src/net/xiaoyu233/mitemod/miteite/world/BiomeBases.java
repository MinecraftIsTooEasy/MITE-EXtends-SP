package net.xiaoyu233.mitemod.miteite.world;

import net.minecraft.BiomeBase;
import net.xiaoyu233.mitemod.miteite.world.biome.BiomeVolcano;

public class BiomeBases extends BiomeBase {
    public static final BiomeBase volcano = (new BiomeVolcano(26).setBiomeNameC("volcano"));
    protected BiomeBases(int par1) {
        super(par1);
    }
}