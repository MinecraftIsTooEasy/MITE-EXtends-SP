package net.xiaoyu233.mitemod.miteite.trans.entity;

import net.minecraft.EntityPhaseSpider;
import net.minecraft.EntityWoodSpider;
import net.minecraft.GenericAttributes;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityPhaseSpider.class)
public class EntityPhaseSpiderTrans extends EntityWoodSpider {

    public EntityPhaseSpiderTrans(World world) {
        super(world);
    }

    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0;
        this.setEntityAttribute(GenericAttributes.attackDamage, 3.0D + day / 16D);
    }

}