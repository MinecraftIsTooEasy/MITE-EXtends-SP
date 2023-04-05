package net.xiaoyu233.mitemod.miteite.trans.block;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BlockFire.class)
public class BlockFireMixin extends Block {
    @Overwrite
    public boolean canBlockCatchFire(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        if (par1IBlockAccess.getBlockId(par2, par3, par4) == netherrack.blockID ||
                par1IBlockAccess.getBlockId(par2, par3, par4) == Blocks.volcanoSand.blockID ||
        par1IBlockAccess.getBlockId(par2, par3, par4) == Blocks.volcanoStone.blockID) {
            return true;
        } else {
            return this.chanceToEncourageFire[par1IBlockAccess.getBlockId(par2, par3, par4)] > 0;
        }
    }
    @Overwrite
    public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (super.updateTick(par1World, par2, par3, par4, par5Random)) {
            return true;
        } else {
            boolean changed_state = false;
            if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {
                boolean var6 = par1World.getBlockId(par2, par3 - 1, par4) == Block.netherrack.blockID ||
                        par1World.getBlockId(par2, par3 - 1, par4) == Blocks.volcanoSand.blockID ||
                        par1World.getBlockId(par2, par3 - 1, par4) == Blocks.volcanoStone.blockID;
                if (par1World.provider instanceof WorldProviderTheEnd && par1World.getBlockId(par2, par3 - 1, par4) == Block.bedrock.blockID) {
                    var6 = true;
                }

                int var7 = par1World.getBlockMetadata(par2, par3, par4);
                if (!var6 && par1World.isPrecipitating(true) && (par1World.canLightningStrikeAt(par2, par3, par4) || par1World.canLightningStrikeAt(par2 - 1, par3, par4) || par1World.canLightningStrikeAt(par2 + 1, par3, par4) || par1World.canLightningStrikeAt(par2, par3, par4 - 1) || par1World.canLightningStrikeAt(par2, par3, par4 + 1))) {
                    par1World.setBlockToAir(par2, par3, par4);
                    changed_state = true;
                } else {
                    if (var7 < 15) {
                        par1World.setBlockMetadata(par2, par3, par4, var7 + par5Random.nextInt(3) / 2, 4);
                        changed_state = true;
                    }

                    par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World) + par5Random.nextInt(10));
                    if (!var6 && !this.canNeighborBurn(par1World, par2, par3, par4)) {
                        if (!par1World.isBlockTopFlatAndSolid(par2, par3 - 1, par4) || var7 > 3) {
                            par1World.setBlockToAir(par2, par3, par4);
                            changed_state = true;
                        }
                    } else if (!var6 && !this.canBlockCatchFire(par1World, par2, par3 - 1, par4) && var7 == 15 && par5Random.nextInt(4) == 0) {
                        par1World.setBlockToAir(par2, par3, par4);
                        changed_state = true;
                    } else {
                        boolean var8 = par1World.isBlockHighHumidity(par2, par3, par4);
                        byte var9 = 0;
                        if (var8) {
                            var9 = -50;
                        }

                        this.tryToCatchBlockOnFire(par1World, par2 + 1, par3, par4, 300 + var9, par5Random, var7);
                        this.tryToCatchBlockOnFire(par1World, par2 - 1, par3, par4, 300 + var9, par5Random, var7);
                        this.tryToCatchBlockOnFire(par1World, par2, par3 - 1, par4, 250 + var9, par5Random, var7);
                        this.tryToCatchBlockOnFire(par1World, par2, par3 + 1, par4, 250 + var9, par5Random, var7);
                        this.tryToCatchBlockOnFire(par1World, par2, par3, par4 - 1, 300 + var9, par5Random, var7);
                        this.tryToCatchBlockOnFire(par1World, par2, par3, par4 + 1, 300 + var9, par5Random, var7);

                        for(int var10 = par2 - 1; var10 <= par2 + 1; ++var10) {
                            for(int var11 = par4 - 1; var11 <= par4 + 1; ++var11) {
                                for(int var12 = par3 - 1; var12 <= par3 + 4; ++var12) {
                                    if (var10 != par2 || var12 != par3 || var11 != par4) {
                                        int var13 = 100;
                                        if (var12 > par3 + 1) {
                                            var13 += (var12 - (par3 + 1)) * 100;
                                        }

                                        int var14 = this.getChanceOfNeighborsEncouragingFire(par1World, var10, var12, var11);
                                        if (var14 > 0) {
                                            int var15 = (var14 + 40 + par1World.difficultySetting * 7) / (var7 + 30);
                                            if (var8) {
                                                var15 /= 2;
                                            }

                                            if (var15 > 0 && par5Random.nextInt(var13) <= var15 && (!par1World.isPrecipitating(true) || !par1World.canLightningStrikeAt(var10, var12, var11)) && !par1World.canLightningStrikeAt(var10 - 1, var12, par4) && !par1World.canLightningStrikeAt(var10 + 1, var12, var11) && !par1World.canLightningStrikeAt(var10, var12, var11 - 1) && !par1World.canLightningStrikeAt(var10, var12, var11 + 1)) {
                                                int var16 = var7 + par5Random.nextInt(5) / 4;
                                                if (var16 > 15) {
                                                    var16 = 15;
                                                }

                                                par1World.setBlock(var10, var12, var11, this.blockID, var16, 3);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return changed_state;
        }
    }
    @Shadow
    private int[] chanceToEncourageFire;
    @Shadow
    public boolean tryToCatchBlockOnFire(World par1World, int par2, int par3, int par4, int par5, Random par6Random, int par7) {
        return false;
    }
    @Shadow
    public boolean canNeighborBurn(World par1World, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private int getChanceOfNeighborsEncouragingFire(World par1World, int par2, int par3, int par4) {
        return 12;
    }
    protected BlockFireMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
}
