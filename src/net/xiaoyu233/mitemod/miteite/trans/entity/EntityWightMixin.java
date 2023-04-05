package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.DamageSource;
import net.minecraft.EntityWight;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.item.ItemInfinitySword;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityWight.class)
public class EntityWightMixin {

    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
        ItemStack item_stack = damage_source.getItemAttackedWith();
        if (item_stack != null && item_stack.getItem() instanceof ItemInfinitySword) {
            return false;
        } else {
            return !damage_source.hasFireAspect() && !damage_source.isLavaDamage() && !damage_source.hasSilverAspect() && !damage_source.hasMagicAspect();
        }
    }
}