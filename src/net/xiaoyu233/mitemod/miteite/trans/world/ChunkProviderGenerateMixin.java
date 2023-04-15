//package net.xiaoyu233.mitemod.miteite.trans.world;
//
//import net.minecraft.*;
//import net.xiaoyu233.mitemod.miteite.world.BiomeBases;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.List;
//import java.util.Random;
//
//@Mixin(ChunkProviderGenerate.class)
//public class ChunkProviderGenerateMixin {
//    @Overwrite
//    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
//        BlockFalling.fallInstantly = true;
//        int var4 = par2 * 16;
//        int var5 = par3 * 16;
//        BiomeBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
//        this.rand.setSeed(this.worldObj.getSeed());
//        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
//        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
//        this.rand.setSeed((long)par2 * var7 + (long)par3 * var9 ^ this.worldObj.getSeed());
//        boolean var11 = false;
//        if (this.mapFeaturesEnabled) {
//            this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
//            var11 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
//            this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
//            this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
//        }
//
//        int var12;
//        int var13;
//        int var14;
//        int i;
//        for(i = 0; i < 4; ++i) {
//            if (!var11 && this.rand.nextInt(6) == 0) {
//                var12 = var4 + this.rand.nextInt(16) + 8;
//                var13 = this.rand.nextInt(128);
//                var14 = var5 + this.rand.nextInt(16) + 8;
//
//                int liquid_block_id;
//                for(liquid_block_id = 1; liquid_block_id < i; ++liquid_block_id) {
//                    if (var13 > 16) {
//                        var13 = this.rand.nextInt(var13);
//                    }
//                }
//
//                if (Math.random() * 32.0 >= (double)(var13 - 16)) {
//                    if (this.rand.nextInt(20) == 0) {
//                        liquid_block_id = Block.waterStill.blockID;
//                    } else {
//                        liquid_block_id = Block.lavaStill.blockID;
//                    }
//                } else {
//                    if (var6 == BiomeBase.desert || var6 == BiomeBase.desertHills) {
//                        continue;
//                    }
//                    if(var6 == BiomeBases.volcano) {
//                        liquid_block_id = Block.lavaStill.blockID;
//                    } else {
//                        liquid_block_id = Block.waterStill.blockID;
//                    }
//                }
//
//                (new WorldGenLakes(liquid_block_id)).generate(this.worldObj, this.rand, var12, var13, var14);
//            }
//        }
//
//        for(var12 = 0; var12 < 8; ++var12) {
//            var13 = var4 + this.rand.nextInt(16) + 8;
//            var14 = this.rand.nextInt(128);
//            i = var5 + this.rand.nextInt(16) + 8;
//            (new WorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, i);
//        }
//
//        var6.decorate(this.worldObj, this.rand, var4, var5);
//        var4 += 8;
//        var5 += 8;
//
//        for(var12 = 0; var12 < 16; ++var12) {
//            for(var13 = 0; var13 < 16; ++var13) {
//                var14 = this.worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);
//                if (this.worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5)) {
//                    this.worldObj.setBlock(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID, 0, 2);
//                }
//
//                if (var14 > 63 && this.worldObj.isAirBlock(var12 + var4, 63, var13 + var5) && this.worldObj.isBlockFreezable(var12 + var4, 62, var13 + var5)) {
//                    this.worldObj.setBlock(var12 + var4, 62, var13 + var5, Block.ice.blockID, 0, 2);
//                }
//
////                if (this.worldObj.getBlock(var4 + var12, var14, var13 + var5) == Block.waterStill ||
////                        this.worldObj.getBlock(var4 + var12, var14, var13 + var5) == Block.waterMoving){
////                    this.worldObj.setBlock(var4 + var12, var14, var13 + var5, Blocks.volcanoSand.blockID, 0, 0);
////                }
//
//                if (this.worldObj.canSnowAt(var12 + var4, var14, var13 + var5)) {
//                    this.worldObj.setBlock(var12 + var4, var14, var13 + var5, Block.snow.blockID, 0, 2);
//                }
//            }
//        }
//
//        SpawnerCreature.performWorldGenSpawning(this.worldObj, var6, EnumCreatureType.animal, var4, var5, 16, 16, this.rand);
//        SpawnerCreature.performWorldGenSpawning(this.worldObj, var6, EnumCreatureType.aquatic, var4, var5, 16, 16, this.rand);
//        BlockFalling.fallInstantly = false;
//    }
//
////    @Overwrite
////    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeBase[] par4ArrayOfBiomeGenBase) {
////        boolean prevent_pockmarking = this.worldObj.getWorldInfo().getEarliestMITEReleaseRunIn() >= 165;
////        boolean use_improved_stone_exposing = this.worldObj.getWorldInfo().getEarliestMITEReleaseRunIn() >= 168;
////        byte var5 = 63;
////        double var6 = 0.03125;
////        if (use_improved_stone_exposing) {
////            this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 2.0, var6 * 2.0, var6 * 2.0);
////            this.stone_noise_2 = this.noiseGen4.generateNoiseOctaves(this.stone_noise_2, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 16.0, var6 * 16.0, var6 * 16.0);
////            this.stone_noise_3 = this.noiseGen8.generateNoiseOctaves(this.stone_noise_3, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 32.0, var6 * 32.0, var6 * 32.0);
////        } else {
////            this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var6 * 2.0, var6 * 2.0, var6 * 2.0);
////        }
////
////        for(int var8 = 0; var8 < 16; ++var8) {
////            for(int var9 = 0; var9 < 16; ++var9) {
////                BiomeBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
////                float var11 = var10.getFloatTemperature();
////                int local_xz_index = var8 + var9 * 16;
////                double var12_double;
////                if (use_improved_stone_exposing) {
////                    var12_double = this.stoneNoise[local_xz_index] / 3.0 + 3.0 - 0.5;
////                } else {
////                    var12_double = this.stoneNoise[local_xz_index] / 3.0 + 3.0 + this.rand.nextDouble() * 0.25;
////                }
////
////                int var12 = (int)var12_double;
////                int var13 = -1;
////                byte var14 = var10.topBlock;
////                byte var15 = var10.fillerBlock;
////                int threshold = use_improved_stone_exposing ? (int)(this.stone_noise_2[local_xz_index] / 3.0 + 1.0) : 0;
////
////                for(int var16 = 127; var16 >= 0; --var16) {
////                    int var17 = (var9 * 16 + var8) * 128 + var16;
////                    if (var16 <= 0 + this.rand.nextInt(5)) {
////                        par3ArrayOfByte[var17] = (byte)Block.bedrock.blockID;
////                    } else {
////                        byte var18 = par3ArrayOfByte[var17];
////                        if (var18 == 0) {
////                            var13 = -1;
////                        } else if (var18 == Block.stone.blockID) {
////                            if (var13 == -1) {
////                                boolean above_water_table = var16 >= var5;
////                                if (var12 <= 0) {
////                                    if (!use_improved_stone_exposing) {
////                                        var14 = prevent_pockmarking ? var10.topBlock : 0;
////                                    } else {
////                                        var14 = var12_double < 0.25 ? 0 : (threshold <= 0 && above_water_table ? var10.topBlock : 0);
////                                        if (var14 == 0 && above_water_table && var12_double > 0.7) {
////                                            if (var12_double > 0.95) {
////                                                var14 = var10.topBlock;
////                                            } else if (this.stone_noise_3[local_xz_index] < 1.0) {
////                                                var14 = (byte)Block.stone.blockID;
////                                            }
////                                        }
////                                    }
////
////                                    var15 = (byte)Block.stone.blockID;
////                                } else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
////                                    var14 = var10.topBlock;
////                                    var15 = var10.fillerBlock;
////                                }
////
////                                if (!above_water_table && var14 == 0) {
////                                    if (var11 < 0.15F) {
////                                        var14 = (byte)Block.ice.blockID;
////                                    } else {
////                                        var14 = (byte)Block.waterStill.blockID;
////                                    }
////                                }
////
////                                var13 = var12;
////                                if (var16 >= var5 - 1) {
////                                    par3ArrayOfByte[var17] = var14;
////                                } else {
////                                    par3ArrayOfByte[var17] = var15;
////                                }
////                            } else if (var13 > 0) {
////                                --var13;
////                                par3ArrayOfByte[var17] = var15;
////                                if (var13 == 0 && var15 == Block.sand.blockID) {
////                                    var13 = this.rand.nextInt(4);
////                                    var15 = (byte)Block.sandStone.blockID;
////                                }
////                            }
////                        }
////                    }
////                }
////            }
////        }
////
////    }
//    @Shadow
//    public boolean chunkExists(int i, int i1) {
//        return false;
//    }
//
//    @Shadow
//    public Chunk getChunkIfItExists(int i, int i1) {
//        return null;
//    }
//
//    @Shadow
//    public Chunk provideChunk(int i, int i1) {
//        return null;
//    }
//
//    @Shadow
//    public Chunk loadChunk(int i, int i1) {
//        return null;
//    }
//
//    @Shadow
//    public boolean saveChunks(boolean b, IProgressUpdate iProgressUpdate) {
//        return false;
//    }
//
//    @Shadow
//    public boolean unloadQueuedChunks() {
//        return false;
//    }
//
//    @Shadow
//    public boolean canSave() {
//        return false;
//    }
//
//    @Shadow
//    public String makeString() {
//        return null;
//    }
//    @Shadow
//    private Random rand;
//    @Shadow
//    private World worldObj;
//    @Shadow
//    private NoiseGeneratorOctaves noiseGen1;
//    @Shadow
//    private NoiseGeneratorOctaves noiseGen2;
//    @Shadow
//    private NoiseGeneratorOctaves noiseGen3;
//    @Shadow
//    private NoiseGeneratorOctaves noiseGen4;
//    @Shadow
//    public NoiseGeneratorOctaves noiseGen5;
//    @Shadow
//    public NoiseGeneratorOctaves noiseGen6;
//    @Shadow
//    public NoiseGeneratorOctaves mobSpawnerNoise;
//    @Shadow
//    private double[] stoneNoise = new double[256];
//    @Shadow
//    private NoiseGeneratorOctaves noiseGen8;
//    @Shadow
//    private double[] stone_noise_2 = new double[256];
//    @Shadow
//    private double[] stone_noise_3 = new double[256];
//    @Shadow
//    @Final
//    private boolean mapFeaturesEnabled;
//    @Shadow
//    private WorldGenStronghold strongholdGenerator;
//    @Shadow
//    private WorldGenVillage villageGenerator;
//    @Shadow
//    private WorldGenMineshaft mineshaftGenerator;
//    @Shadow
//    private WorldGenLargeFeature scatteredFeatureGenerator;
//
//    @Shadow
//    public List getPossibleCreatures(EnumCreatureType enumCreatureType, int i, int i1, int i2) {
//        return null;
//    }
//
//    @Shadow
//    public ChunkPosition findClosestStructure(World world, String s, int i, int i1, int i2) {
//        return null;
//    }
//
//    @Shadow
//    public int getLoadedChunkCount() {
//        return 0;
//    }
//
//    @Shadow
//    public void recreateStructures(int i, int i1) {
//
//    }
//
//    @Shadow
//    public void saveExtraData() {
//
//    }
//}
