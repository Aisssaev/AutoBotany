package net.neolab.autobotany.entities.manaSpark;

import net.neolab.autobotany.ModEntities;
import net.neolab.autobotany.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityShadowManaSpark extends EntityManaSparkPattern {

    public EntityShadowManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 500000;
    }

    public EntityShadowManaSpark(Level level){
        this(ModEntities.SHADOW_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ModItems.shadowSpark;
    }
}