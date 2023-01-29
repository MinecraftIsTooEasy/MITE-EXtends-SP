package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;

public class ItemPadding extends Item {

    public ItemPadding(int par1) {
        super(par1, Material.wood, "padding");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeModeTab.tabTools);
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        World world = player.worldObj;
        int x = (int)player.block_placement_pos_x;int y = (int)player.block_placement_pos_y;int z = (int)player.block_placement_pos_y;
        if(world.getBlock(x, y, z) instanceof BlockMobSpawner){
            if (!player.inCreativeMode()) {
                player.convertOneOfHeldItem((ItemStack)null);
            }
            world.setBlockMetadata(x, y, z, 0 , 2);
            return true;
       }
        return false;

    }
}
