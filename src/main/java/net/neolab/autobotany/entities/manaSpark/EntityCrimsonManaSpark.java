package net.neolab.autobotany.entities.manaSpark;

import net.neolab.autobotany.ModEntities;
import net.neolab.autobotany.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityCrimsonManaSpark extends EntityManaSparkPattern {

    public EntityCrimsonManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 1000000;
    }

    public EntityCrimsonManaSpark(Level level){
        this(ModEntities.CRIMSON_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ModItems.crimsonSpark;
    }
}