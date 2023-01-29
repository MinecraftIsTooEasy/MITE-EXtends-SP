//package net.xiaoyu233.mitemod.miteite.block;
//
//import net.minecraft.BlockConstants;
//import net.minecraft.CreativeModeTab;
//import net.minecraft.Direction;
//import net.minecraft.Material;
//
//public class BlockFu extends Blocks{
//    protected BlockFu(int par1, Material par2Material) {
//        super(par1, Material.wood, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
//        this.setBounds(true);
//        this.setMaxStackSize(64);
//        this.setCreativeTab(CreativeModeTab.tabDecorations);
//    }
//
//
//    private void setBounds(boolean for_all_threads) {
//        float var1 = 0.375F;
//        float var2 = var1 / 2.0F;
//        // 前 下 厚度 高度 宽度
//        this.setBlockBounds((double)(0.5F - var2), 0.0D, (double)(0.5F - var2), (double)(0.5F + var2), (double)0.6875D, (double)(0.5F + var2),
//                for_all_threads);
//    }
//    public void setBlockBoundsForItemRender(int item_damage) {
//        this.setBounds(false);
//    }
//}
