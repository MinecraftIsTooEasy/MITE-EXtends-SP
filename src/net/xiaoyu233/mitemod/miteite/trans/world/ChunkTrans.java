package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.Chunk;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.Random;

@Mixin(Chunk.class)
public class ChunkTrans {
//    @Shadow
//    public World worldObj;
//    @Shadow
//    @Final
//    public int xPosition;
//    @Shadow
//    @Final
//    public int zPosition;
//    @Shadow
//    @Final
//    @Mutable
//    private static byte water_still_block_id;
//    @Inject(method = "<clinit>",at = @At("RETURN"))
//    private static void injectClinit(CallbackInfo callback){
////        if (worldObj.getBiomeGenForCoords(xPosition, zPosition) == BiomeBases.volcano) {
////            water_still_block_id = (byte)Block.lavaStill.blockID;
////        } else{
//            water_still_block_id = (byte)Block.lavaStill.blockID;
////        }
//    }
    @Redirect(method = "<init>(Lnet/minecraft/World;[BII)V",at = @At(value = "INVOKE",target = "Ljava/util/Random;nextInt(I)I"),
            slice = @Slice(
                    from = @At(value = "FIELD",target = "Lnet/minecraft/ChunkProviderUnderworld;bedrock_strata_4_bump_noise:[D"),
                    to = @At(value = "FIELD",target = "Lnet/minecraft/Block;mantleOrCore:Lnet/minecraft/BlockMantleOrCore;",ordinal = 1)))
    private int redirectRandomBedrockNum(Random caller,int bound){
        //In fact this is the height of the mantle blocks
        return Configs.wenscConfig.underworldMantleBlockOffset.ConfigValue;
    }
//    @Inject(method = "<init>(Lnet/minecraft/World;[BII)V",at = @At(value = "RETURN"))
//    private void injectInit(World par1World, byte[] par2ArrayOfByte, int par3, int par4, CallbackInfo callbackInfo){
//        int var5 = par2ArrayOfByte.length / 256;
//        int base_x;
//        int base_z;
//        int var7;
//        int var6;
//        if (par1World.provider.isHellWorld) {
//            base_x = this.xPosition * 16;
//            base_z = this.zPosition * 16;
//
//            for(int var20 = 0; var20 < 16; ++var20) {
//                for(var7 = 0; var7 < 16; ++var7) {
//                    for(base_x = 0; base_x < var5; ++base_x) {
//                        base_z = par2ArrayOfByte[var20 << 11 | var7 << 7 | base_x];
//                        if (base_z != 0) {
//                            if (base_z == Block.lavaStill.blockID) {
//                                if (base_x == 31 && par2ArrayOfByte[var20 << 11 | var7 << 7 | base_x + 1] == 0) {
//                                    this.addPendingBlocklightUpdate(base_x + var20, base_x, base_z + var7);
//                                }
//
//                                if (base_x > 31) {
//                                    var20 = base_x + var20;
//                                    var7 = base_z + var7;
//                                    par1World.scheduleBlockChange(var20, base_x, var7, base_z, base_z - 1, 0, 10);
//                                }
//                            }
//
//                            var20 = base_x >> 4;
//                            if (this.storageArrays[var20] == null) {
//                                this.storageArrays[var20] = new ChunkSection(var20 << 4, !par1World.provider.hasNoSky);
//                            }
//
//                            this.storageArrays[var20].setExtBlockID(var20, base_x & 15, var7, base_z);
//                            if (base_z == Block.gravel.blockID) {
//                                this.storageArrays[var20].setExtBlockMetadata(var20, base_x & 15, var7, 2);
//                            } else if (base_z == Block.mantleOrCore.blockID) {
//                                this.addPendingBlocklightUpdate(base_x + var20, base_x, base_z + var7);
//                                if (base_x < 1) {
//                                    this.storageArrays[var20].setExtBlockMetadata(var20, base_x & 15, var7, 1);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            int rng_index;
//            int index;
//            int num_bedrock_blocks;
//            if (par1World.isUnderworld()) {
//                base_x = this.xPosition * 16;
//                base_z = this.zPosition * 16;
//                Random random = new Random(par1World.getHashedSeed() * (long)getChunkCoordsHash(this.xPosition, this.zPosition));
//                var7 = par1World.underworld_y_offset;
//                double scale_xz = 0.015625;
//                double scale_y = 0.03125;
//                ChunkProviderUnderworld.bedrock_strata_1a_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1a.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1a_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 2.0, scale_y * 2.0, scale_xz * 2.0);
//                ChunkProviderUnderworld.bedrock_strata_1b_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1b.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1b_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 2.0, scale_y * 2.0, scale_xz * 2.0);
//                ChunkProviderUnderworld.bedrock_strata_2_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_2.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_2_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0, scale_y * 2.0, scale_xz * 4.0);
//                ChunkProviderUnderworld.bedrock_strata_3_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_3.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_3_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0, scale_y * 2.0, scale_xz * 4.0);
//                ChunkProviderUnderworld.bedrock_strata_4_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_4.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_4_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0, scale_y * 2.0, scale_xz * 4.0);
//                scale_xz = 0.25;
//                ChunkProviderUnderworld.bedrock_strata_1a_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1a_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1a_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 0.5, scale_y * 2.0, scale_xz * 0.5);
//                ChunkProviderUnderworld.bedrock_strata_1b_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1b_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1b_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 1.0, scale_y * 2.0, scale_xz * 1.0);
//                ChunkProviderUnderworld.bedrock_strata_1c_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1c_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1c_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 2.0, scale_y * 2.0, scale_xz * 2.0);
//                ChunkProviderUnderworld.bedrock_strata_2_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_2_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_2_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0, scale_y * 2.0, scale_xz * 4.0);
//                ChunkProviderUnderworld.bedrock_strata_3_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_3_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_3_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0, scale_y * 2.0, scale_xz * 4.0);
//                ChunkProviderUnderworld.bedrock_strata_4_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_4_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_4_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0, scale_y * 2.0, scale_xz * 4.0);
//                rng_index = this.xPosition * 2653 + this.zPosition * 6714631;
//
//                for(index = 0; index < 16; ++index) {
//                    for(int indexC = 0; indexC < 16; ++indexC) {
//                        int var10000;
//                        if (indexC == 0) {
//                            var10000 = 0;
//                        } else {
//                            byte var29 = 3;
//                            var10000 = this.redirectRandomBedrockNum(random, var29) + 1;
//                        }
//
//                        num_bedrock_blocks = var10000;
//
//                        for(int var8 = 0 - indexC; var8 < (indexC == 0 ? var5 : 256 - indexC); ++var8) {
//                            byte var9;
//                            int block_bedrock_id;
//                            if (var8 >= 0 && var8 <= 127) {
//                                index = index << 11 | index << 7 | var8;
//                                var9 = index >= 0 && index < par2ArrayOfByte.length ? par2ArrayOfByte[index] : (byte)Block.anvilAdamantium.blockID;
//                                var8 += index;
//                            } else {
//                                index = -1;
//                                var8 += index;
//                                if (var8 < num_bedrock_blocks) {
//                                    var9 = (byte)Block.mantleOrCore.blockID;
//                                } else if (var8 > 255 - num_bedrock_blocks) {
//                                    var9 = (byte)Block.bedrock.blockID;
//                                } else {
//                                    var9 = (byte)Block.anvilAdamantium.blockID;
//                                    block_bedrock_id = (byte)Block.bedrock.blockID;
//                                    int local_xz_index = index + index * 16;
//                                    double bedrock_noise = this.getMax(ChunkProviderUnderworld.bedrock_strata_1a_noise[local_xz_index], ChunkProviderUnderworld.bedrock_strata_1b_noise[local_xz_index]);
//                                    int dy = var8 - 3;
//                                    double bump_noise = ChunkProviderUnderworld.bedrock_strata_1a_bump_noise[local_xz_index];
//                                    if (bump_noise > 0.0) {
//                                        bedrock_noise += bump_noise * 0.25;
//                                    }
//
//                                    bump_noise = ChunkProviderUnderworld.bedrock_strata_1b_bump_noise[local_xz_index];
//                                    if (bump_noise > 0.0) {
//                                        bedrock_noise += bump_noise * 0.125;
//                                    }
//
//                                    bump_noise = ChunkProviderUnderworld.bedrock_strata_1c_bump_noise[local_xz_index];
//                                    if (bump_noise > 0.0) {
//                                        bedrock_noise += bump_noise * 0.125;
//                                    }
//
//                                    bump_noise = ChunkProviderUnderworld.bedrock_strata_4_bump_noise[local_xz_index];
//                                    if (bump_noise > 0.0) {
//                                        bedrock_noise += bump_noise * 0.09375 + 0.125;
//                                    }
//
//                                    if (bedrock_noise > 0.0 && (double)dy <= bedrock_noise * 7.0) {
//                                        var9 = (byte)Block.bedrock.blockID;
//                                    }
//
//                                    if (var9 != block_bedrock_id) {
//                                        dy = var8 - 32;
//                                        bedrock_noise = ChunkProviderUnderworld.bedrock_strata_2_noise[local_xz_index] - bedrock_noise * 1.5;
//                                        if (bedrock_noise > 0.0) {
//                                            if (dy > 0) {
//                                                bump_noise = ChunkProviderUnderworld.bedrock_strata_2_bump_noise[local_xz_index];
//                                                if (bump_noise > 0.0) {
//                                                    bedrock_noise += bump_noise * 0.25 + 0.25;
//                                                }
//                                            }
//
//                                            if (dy < 0) {
//                                                ++rng_index;
//                                                if (RNG.chance_in_2[rng_index & 32767]) {
//                                                    ++dy;
//                                                }
//
//                                                dy = -dy;
//                                            }
//
//                                            if ((double)dy <= bedrock_noise * 2.0) {
//                                                var9 = (byte)Block.bedrock.blockID;
//                                            }
//                                        }
//                                    }
//
//                                    if (var9 != block_bedrock_id) {
//                                        dy = var8 - 72;
//                                        bedrock_noise = ChunkProviderUnderworld.bedrock_strata_3_noise[local_xz_index] - ChunkProviderUnderworld.bedrock_strata_4_noise[local_xz_index] * 0.375;
//                                        bedrock_noise += 0.5;
//                                        if (bedrock_noise > 0.0) {
//                                            if (dy > 0) {
//                                                bump_noise = ChunkProviderUnderworld.bedrock_strata_3_bump_noise[local_xz_index];
//                                                if (bump_noise > 0.0) {
//                                                    bedrock_noise += bump_noise * 0.25 + 0.25;
//                                                }
//                                            }
//
//                                            if (dy < 0) {
//                                                ++rng_index;
//                                                if (RNG.chance_in_2[rng_index & 32767]) {
//                                                    ++dy;
//                                                }
//
//                                                dy = -dy;
//                                            }
//
//                                            if ((double)dy <= bedrock_noise * 2.0) {
//                                                var9 = (byte)Block.bedrock.blockID;
//                                            }
//                                        }
//                                    }
//
//                                    if (var9 != block_bedrock_id) {
//                                        dy = var8 - 96;
//                                        bedrock_noise = ChunkProviderUnderworld.bedrock_strata_4_noise[local_xz_index] - ChunkProviderUnderworld.bedrock_strata_3_noise[local_xz_index] * 0.375;
//                                        bedrock_noise += 0.5;
//                                        if (bedrock_noise > 0.0) {
//                                            if (dy > 0) {
//                                                bump_noise = ChunkProviderUnderworld.bedrock_strata_4_bump_noise[local_xz_index];
//                                                if (bump_noise > 0.0) {
//                                                    bedrock_noise += bump_noise * 0.25 + 0.25;
//                                                }
//                                            }
//
//                                            if (dy < 0) {
//                                                ++rng_index;
//                                                if (RNG.chance_in_2[rng_index & 32767]) {
//                                                    ++dy;
//                                                }
//
//                                                dy = -dy;
//                                            }
//
//                                            if ((double)dy <= bedrock_noise * 2.0) {
//                                                var9 = (byte)Block.bedrock.blockID;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                            if (var9 != 0) {
//                                block_bedrock_id = var8 >> 4;
//                                if (this.storageArrays[block_bedrock_id] == null) {
//                                    this.storageArrays[block_bedrock_id] = new ChunkSection(block_bedrock_id << 4, !par1World.provider.hasNoSky);
//                                }
//
//                                this.storageArrays[block_bedrock_id].setExtBlockID(index, var8 & 15, index, var9);
//                                if (Block.lightValue[var9] > 0) {
//                                    this.storageArrays[block_bedrock_id].setExtBlocklightValue(index, var8 & 15, index, Block.lightValue[var9]);
//                                    if (par2ArrayOfByte[index + 1] == 0) {
//                                        this.addPendingBlocklightUpdate(base_x + index, var8, base_z + index);
//                                    }
//                                }
//                            }
//
//                            var8 -= index;
//                        }
//                    }
//                }
//            } else {
//                Chunk chunk_west = this.getNeighboringChunkIfItExists(-1, 0);
//                Chunk chunk_east = this.getNeighboringChunkIfItExists(1, 0);
//                Chunk chunk_north = this.getNeighboringChunkIfItExists(0, -1);
//                Chunk chunk_south = this.getNeighboringChunkIfItExists(0, 1);
//                base_x = this.xPosition * 16;
//                base_z = this.zPosition * 16;
//
//                for(var6 = 0; var6 < 16; ++var6) {
//                    for(var7 = 0; var7 < 16; ++var7) {
//                        for(rng_index = 0; rng_index < var5; ++rng_index) {
//                            index = var6 << 11 | var7 << 7 | rng_index;
//                            byte var9 = par2ArrayOfByte[index];
//                            if (var9 != 0) {
//                                num_bedrock_blocks = rng_index >> 4;
//                                if (this.storageArrays[num_bedrock_blocks] == null) {
//                                    this.storageArrays[num_bedrock_blocks] = new ChunkSection(num_bedrock_blocks << 4, !par1World.provider.hasNoSky);
//                                }
//
//                                this.storageArrays[num_bedrock_blocks].setExtBlockID(var6, rng_index & 15, var7, var9);
//                                if (Block.lightValue[var9] > 0) {
//                                    this.storageArrays[num_bedrock_blocks].setExtBlocklightValue(var6, rng_index & 15, var7, Block.lightValue[var9]);
//                                    if (par2ArrayOfByte[var6 << 11 | var7 << 7 | rng_index + 1] == 0) {
//                                        this.addPendingBlocklightUpdate(base_x + var6, rng_index, base_z + var7);
//                                    }
//                                } else if (var9 == Block.waterStill.blockID) {
//                                    if (rng_index > 0 && par2ArrayOfByte[index - 1] == 0) {
//                                        par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                    } else if (rng_index == 62) {
//                                        if (var6 == 0) {
//                                            if (chunk_west != null && chunk_west.getBlockIDOptimized(15 | var7 << 4, rng_index) == 0) {
//                                                par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                                continue;
//                                            }
//                                        } else if (par2ArrayOfByte[index - 2048] == 0) {
//                                            par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                            continue;
//                                        }
//
//                                        if (var6 == 15) {
//                                            if (chunk_east != null && chunk_east.getBlockIDOptimized(0 | var7 << 4, rng_index) == 0) {
//                                                par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                                continue;
//                                            }
//                                        } else if (par2ArrayOfByte[index + 2048] == 0) {
//                                            par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                            continue;
//                                        }
//
//                                        if (var7 == 0) {
//                                            if (chunk_north != null && chunk_north.getBlockIDOptimized(var6 | 240, rng_index) == 0) {
//                                                par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                                continue;
//                                            }
//                                        } else if (par2ArrayOfByte[index - 128] == 0) {
//                                            par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                            continue;
//                                        }
//
//                                        if (var7 == 15) {
//                                            if (chunk_south != null && chunk_south.getBlockIDOptimized(var6 | 0, rng_index) == 0) {
//                                                par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                            }
//                                        } else if (par2ArrayOfByte[index + 128] == 0) {
//                                            par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7, var9, var9 - 1, 0, 10);
//                                        }
//                                    }
//                                }
//                            } else if (rng_index == 62) {
//                                if (var6 == 0 && chunk_west != null && chunk_west.getBlockIDOptimized(15 | var7 << 4, rng_index) == water_still_block_id) {
//                                    par1World.scheduleBlockChange(base_x + var6 - 1, rng_index, base_z + var7, water_still_block_id, water_still_block_id - 1, 0, 10);
//                                } else if (var6 == 15 && chunk_east != null && chunk_east.getBlockIDOptimized(0 | var7 << 4, rng_index) == water_still_block_id) {
//                                    par1World.scheduleBlockChange(base_x + var6 + 1, rng_index, base_z + var7, water_still_block_id, water_still_block_id - 1, 0, 10);
//                                } else if (var7 == 0 && chunk_north != null && chunk_north.getBlockIDOptimized(var6 | 240, rng_index) == water_still_block_id) {
//                                    par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7 - 1, water_still_block_id, water_still_block_id - 1, 0, 10);
//                                } else if (var7 == 15 && chunk_south != null && chunk_south.getBlockIDOptimized(var6 | 0, rng_index) == water_still_block_id) {
//                                    par1World.scheduleBlockChange(base_x + var6, rng_index, base_z + var7 + 1, water_still_block_id, water_still_block_id - 1, 0, 10);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Shadow
//    public static int getChunkCoordsHash(int chunk_x, int chunk_z) {
//        return 1;
//    }
//    @Shadow
//    private double getMax(double a, double b) {
//        return 1;
//    }
//    @Shadow
//    public void addPendingBlocklightUpdate(int x, int y, int z) {
//
//    }
//    @Shadow
//    public Chunk getNeighboringChunkIfItExists(int dx, int dz) {
//        return null;
//    }
//    @Shadow
//    public ChunkSection[] storageArrays;
//    @Shadow
//    public World worldObj;
//    @Final
//    @Shadow
//    public int xPosition;
//    @Final
//    @Shadow
//    public int zPosition;
//    @Final
//    @Shadow
//    private static byte water_still_block_id;


}
