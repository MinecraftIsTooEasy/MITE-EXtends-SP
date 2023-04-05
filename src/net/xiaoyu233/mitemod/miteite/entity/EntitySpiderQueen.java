package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;

public class EntitySpiderQueen extends EntityArachnid {
    private final int num_webs;
    public EntitySpiderQueen(World par1World) {
        super(par1World, 1.45F);
        this.num_webs = 4;
    }

    protected String getLivingSound() {
        return "imported.mob.demonspider.say";
    }

    protected String getHurtSound() {
        return "imported.mob.demonspider.hurt";
    }

    protected String getDeathSound() {
        return "imported.mob.demonspider.death";
    }

    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 1.3F;
    }

    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 0.6F;
    }
    public boolean peacefulDuringDay() {
        return false;
    }
    protected void applyEntityAttributes() {
        int day = this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0;
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, 60.0 + (double) day / 3.0);
        this.setEntityAttribute(GenericAttributes.followRange, 96.0);
        this.setEntityAttribute(GenericAttributes.attackDamage, 25.0 + (double) day / 6.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.92);
    }
    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityLiving) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 200, 5));
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.poison.id, 850, 0));
            }

            return result;
        } else {
            return result;
        }
    }
    private int spawnCounter;
    private int spawnSums;
    public void onUpdate()
    {
        super.onUpdate();
        if (!getWorld().isRemote)
        {
            if (spawnSums < 8)
                if (spawnCounter < 20)
                {
                    spawnCounter++;
                } else
                {
                    EntityBlackWidowSpider widowspider = new EntityBlackWidowSpider(worldObj);
                    widowspider.setPosition(posX+this.rand.nextInt(8)-this.rand.nextInt(8), posY, posZ-this.rand.nextInt(8)+this.rand.nextInt(8));
                    widowspider.refreshDespawnCounter(-9600);
                    worldObj.spawnEntityInWorld(widowspider);
                    widowspider.onSpawnWithEgg(null);
                    widowspider.entityFX(EnumEntityFX.summoned);
                    spawnCounter = 0;
                    spawnSums++;
                }
        }

        int day = this.getWorld() != null ? this.getWorld().getDayOfOverworld() : 0;
        if(this.getHealth()<1.8F && get_extended_drop){
            this.get_extended_drop = false;
            EntityItem drop = new EntityItem(worldObj,posX,posY,posZ,new ItemStack(Item.emerald, Math.min(3,1 + day / 32), 8271));
            drop.refreshDespawnCounter(-9600);
            worldObj.spawnEntityInWorld(drop);
            drop.entityFX(EnumEntityFX.summoned);
        }
    }
    public int getExperienceValue() {
        return super.getExperienceValue() * 3;
    }
    private boolean get_extended_drop = true;
}
