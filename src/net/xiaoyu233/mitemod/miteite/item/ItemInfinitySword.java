package net.xiaoyu233.mitemod.miteite.item;

import java.util.List;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

public class ItemInfinitySword extends ItemSword {
    public boolean Method_Towards = false;
    protected ItemInfinitySword(int par1) {
        super(par1, Materials.vibranium);
    }

    public Class[] getSimilarClasses() {
        return ItemTool.weapon_classes;
    }

    public int getNumComponentsForDurability() {
        return 24;
    }

    public float getBaseDamageVsEntity() {
        return 2147483647.0F;
    }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        info.add(EnumChatFormat.LIGHT_GRAY + "" + EnumChatFormat.ITALIC + Translator.getFormatted("极其大的伤害", new Object[0]));
    }
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
//        if(player.InfSwordAttackingMethod){
//            player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[攻伐之剑] ").setColor(EnumChatFormat.AQUA).appendComponent(ChatMessage.createFromTranslationKey("模式：对所有实体").setColor(EnumChatFormat.RED)));
//        }
//        else{
//            player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[攻伐之剑] ").setColor(EnumChatFormat.AQUA).appendComponent(ChatMessage.createFromTranslationKey("模式：对非玩家实体").setColor(EnumChatFormat.GREEN)));
//        }
//        player.InfSwordAttackingMethod = !player.InfSwordAttackingMethod;
//        if(player.InfSwordAttackingMethod){
//            player.addPotionEffect(new MobEffect(MobEffectList.damageBoost.getId(),20,4,false));
//        }
        return true;
    }
}