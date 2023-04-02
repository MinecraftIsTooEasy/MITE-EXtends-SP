/*package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;

import java.util.List;

public class ItemRingKiller extends Item{
    public ItemRingKiller(int par1, Material material) {
        super(par1, material, null);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeModeTab.tabCombat);
        this.setCraftingDifficultyAsComponent(1.0E-9F);
    }

}
    public boolean isHarmedByAcid() {
        return false;
    }
    public boolean isHarmedByFire() {
        return false;
    }
    public boolean isHarmedByLava() {
        return false;
    }

    public String getToolType() {
        return "ringKiller";
    }

    public int getRingKillerSkillCoolDownTime() {
    return (int) 0.01F;
    }

    public float getRingKillerSkillRange() {
        return 5F;
    }

    public float getRingKillerSkillDamage() {
        return 32767F;
    }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add("终极神器");
            info.add("极其大的伤害");
            info.add(EnumChatFormat.BROWN + Translator.getFormatted("自动范围群体伤害", new Object[0]));
            info.add(EnumChatFormat.BLUE + Translator.getFormatted("范围" + this.getRingKillerSkillRange(), new Object[0]));
            info.add(EnumChatFormat.RED + Translator.getFormatted("伤害" + this.getRingKillerSkillDamage(), new Object[0]));
            info.add(EnumChatFormat.LIGHT_PURPLE + Translator.getFormatted("冷却" + (float)this.getRingKillerSkillCoolDownTime() / 20F + 'S', new Object[0]));
        }
    }
}

 */