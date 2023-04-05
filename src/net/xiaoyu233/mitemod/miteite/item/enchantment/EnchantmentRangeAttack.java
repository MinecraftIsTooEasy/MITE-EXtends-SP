package net.xiaoyu233.mitemod.miteite.item.enchantment;


import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.ItemInfinitySword;

public class EnchantmentRangeAttack extends Enchantment {
    protected EnchantmentRangeAttack(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }
    @Override
    public int getNumLevels() {
        return 1;
    }

    @Override
    public String getNameSuffix() {
        return "range";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemInfinitySword;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab var1) {
        return var1 == CreativeModeTab.tabCombat;
    }
}
