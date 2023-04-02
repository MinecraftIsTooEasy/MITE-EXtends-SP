package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.DamageSource;
import net.minecraft.EntityLiving;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityZombie;
import net.minecraft.EnumEntityFX;
import net.minecraft.GenericAttributes;
import net.minecraft.GroupDataEntity;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.xiaoyu233.mitemod.miteite.item.Items;
import net.xiaoyu233.mitemod.miteite.util.Configs.wenscConfig;

public class EntityZombieDoorLord extends EntityZombie {
    private int spawnCounter;
    private int spawnSums;
    private boolean haveTryToSpawnExchanger = false;

    public EntityZombieDoorLord(World par1World) {
        super(par1World);
    }

    protected void addRandomEquipment() {
        int day = this.getWorld().getDayOfOverworld();
        this.setCurrentItemOrArmor(0, (new ItemStack(Items.doorVibranium, 1)).randomizeForMob(this, day > 64));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day = this.getWorld().getDayOfOverworld();
        double x = (double)(day / 8 - 8);
        double rate = 0.5 + x / (20.0 + Math.abs(x));
        int healthRate = Math.min(day / 16, 10);
        this.setEntityAttribute(GenericAttributes.attackDamage, rate * 50.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, rate * 120.0 + (double)(healthRate * 20));
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.6D);
    }

    public boolean canBeDisarmed() {
        return false;
    }

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player) {
            this.dropItem(Items.voucherVibraniumDoor);
            this.dropItem(Items.doorVibranium);
            int day = this.getWorld().getDayOfOverworld();
            int diamond_count = (day / 32 > 3 ? 3 : day / 32) * 2;
            for(int i1 = 0; i1 < diamond_count; ++i1) {
                this.dropItem(Item.emerald);
            }
        }

    }

    public boolean canCatchFire() {
        return false;
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLiving target = this.getAttackTarget();
            if (target instanceof EntityPlayer) {
                if (this.spawnSums < 10) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityZombieDoor zombie = new EntityZombieDoor(this.worldObj);
                        if (zombie.entityId == 211) {
                            return;
                        }

                        zombie.setPosition(this.posX, this.posY, this.posZ);
                        zombie.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld(zombie);
                        zombie.onSpawnWithEgg((GroupDataEntity)null);
                        zombie.addRandomWeapon();
                        zombie.setAttackTarget(this.getTarget());
                        zombie.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }

                if (wenscConfig.isSpawnExchanger.ConfigValue && !this.haveTryToSpawnExchanger) {
                    if (this.rand.nextInt(10) == 0) {
                        EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
                        entityExchanger.setPosition(this.posX, this.posY, this.posZ);
                        entityExchanger.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld(entityExchanger);
                        entityExchanger.onSpawnWithEgg((GroupDataEntity)null);
                        entityExchanger.setAttackTarget(this.getTarget());
                        entityExchanger.entityFX(EnumEntityFX.summoned);
                    }

                    this.haveTryToSpawnExchanger = true;
                }
            }
        }

    }
}
