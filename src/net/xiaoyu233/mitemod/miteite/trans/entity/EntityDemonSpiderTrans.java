package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.EntityAIGetOutOfLava;
import net.minecraft.EntityArachnid;
import net.minecraft.EntityDemonSpider;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityDemonSpider.class)
public class EntityDemonSpiderTrans extends EntityArachnid {

    public EntityDemonSpiderTrans(World par1World) {
        super(par1World, 1.0F);
        this.tasks.addTask(4, new EntityAIGetOutOfLava(this, 1.0F));
    }
}
