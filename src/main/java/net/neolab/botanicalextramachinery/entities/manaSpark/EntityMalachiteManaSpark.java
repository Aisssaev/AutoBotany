package net.neolab.botanicalextramachinery.entities.manaSpark;

import net.neolab.botanicalextramachinery.ModEntities;
import net.neolab.botanicalextramachinery.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntityMalachiteManaSpark extends EntityManaSparkPattern {

    public EntityMalachiteManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 25000;
    }

    public EntityMalachiteManaSpark(Level level){
        this(ModEntities.MALACHITE_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ModItems.malachiteSpark;
    }
}