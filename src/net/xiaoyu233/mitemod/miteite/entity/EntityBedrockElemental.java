package net.xiaoyu233.mitemod.miteite.entity;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.Items;

public class EntityBedrockElemental extends EntityEarthElemental {
    private int spawnCounter;
    private int spawnSums;
    public EntityBedrockElemental(World world) {
        super(world);
//        this.getNavigator().setBreakDoors(true);
//        this.tasks.addTask(0, new PathfinderGoalFloat(this));
//        this.tasks.addTask(1, new PathfinderGoalBreakDoor(this));
//        this.tasks.addTask(2, new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.0, false));
//        this.tasks.addTask(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1.0, true));
//        this.tasks.addTask(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
//        this.tasks.addTask(6, new PathfinderGoalRandomStroll(this, 1.0));
//        this.tasks.addTask(7, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 8.0F));
//        this.tasks.addTask(7, new PathfinderGoalRandomLookaround(this));
//        this.targetTasks.addTask(1, new PathfinderGoalHurtByTarget(this, true));
//        this.targetTasks.addTask(2, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, 0, true));
//        this.targetTasks.addTask(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false));
    }
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        int day1 = this.getWorld().getDayOfOverworld();
        double x = (double)(day1 / 8 - 8);
        double rate = 0.5 + x / (20.0 + Math.abs(x));
        this.setEntityAttribute(GenericAttributes.attackDamage, rate * 50.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 300);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.35D);
    }
    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (recently_hit_by_player){
            this.dropItem(Items.voucherBedrock);
            this.dropItem(Item.getItem(Block.bedrock));
        }
    }
    @Override
    public boolean isImmuneTo(DamageSource damage_source) {
        ItemStack item_stack = damage_source.getItemAttackedWith();
        boolean noNull = item_stack != null && item_stack.getItem() instanceof ItemTool;
        if (noNull && item_stack.itemID == Items.infinitySword.itemID ||
                noNull && item_stack.itemID == Items.VIBRANIUM_WAR_HAMMER.itemID ||
                noNull && item_stack.itemID == Items.VIBRANIUM_PICKAXE.itemID) {
            return false;
        }else{
            return true;
        }
    }
    @Override
    public EntityDamageResult attackEntityAsMob(Entity target) {
        if (target != null && target.isEntityAlive()){
            target.setFire(100);
        }
        return super.attackEntityAsMob(target);
    }
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.getWorld().isRemote) {
            EntityLiving target = this.getAttackTarget();
            if (target instanceof EntityPlayer) {
                if (this.spawnSums < 10) {
                    if (this.spawnCounter < 200) {
                        ++this.spawnCounter;
                    } else {
                        EntityEarthElemental EarthElemental = new EntityEarthElemental(this.worldObj);
                        if (EarthElemental.entityId == 211) {
                            return;
                        }
                        EarthElemental.setPosition(this.posX, this.posY, this.posZ);
                        EarthElemental.refreshDespawnCounter(-9600);
                        this.worldObj.spawnEntityInWorld(EarthElemental);
                        EarthElemental.onSpawnWithEgg((GroupDataEntity) null);
                        EarthElemental.setAttackTarget(this.getTarget());
                        EarthElemental.entityFX(EnumEntityFX.summoned);
                        this.spawnCounter = 0;
                        ++this.spawnSums;
                    }
                }
            }
        }
    }
}
