package net.xiaoyu233.mitemod.miteite.item;

import java.util.List;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormat;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;
import net.minecraft.ItemTool;
import net.minecraft.Slot;
import net.minecraft.Translator;

public class ItemInfinitySword extends ItemSword {
    protected ItemInfinitySword(int par1) {
        super(par1, Materials.infinity);
    }

    public Class[] getSimilarClasses() {
        return ItemTool.weapon_classes;
    }

    public int getNumComponentsForDurability() {
        return 4;
    }

    public float getBaseDamageVsEntity() {
        return 0.0F;
    }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        info.add(EnumChatFormat.LIGHT_GRAY + "" + EnumChatFormat.ITALIC + Translator.getFormatted("无穷大的伤害", new Object[0]));
    }
}