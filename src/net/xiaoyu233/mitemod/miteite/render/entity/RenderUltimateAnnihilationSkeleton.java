package net.xiaoyu233.mitemod.miteite.render.entity;

import net.minecraft.*;

public class RenderUltimateAnnihilationSkeleton extends bgu {
    public RenderUltimateAnnihilationSkeleton() {
        super(new bbz(), 0.5F);
    }

    protected boolean forceGlowOverride() {
        return true;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/skeleton/annihilation_skeleton");
    }

    protected bjo a(EntityInsentient par1EntityLiving) {
        return this.textures[0];
    }

    protected bjo a(Entity par1Entity) {
        return this.a((EntitySkeleton) par1Entity);
    }
}