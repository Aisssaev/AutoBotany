package net.neolab.botanicalextramachinery.client;

import net.neolab.botanicalextramachinery.ModEntities;
import net.neolab.botanicalextramachinery.render.RenderCrimsonManaSpark;
import net.neolab.botanicalextramachinery.render.RenderMalachiteManaSpark;
import net.neolab.botanicalextramachinery.render.RenderSaffronManaSpark;
import net.neolab.botanicalextramachinery.render.RenderShadowManaSpark;
import vazkii.botania.client.render.entity.EntityRenderers.EntityRendererConsumer;

public class EntityRenderers {

    public static void registerEntityRenderers(EntityRendererConsumer consumer) {
        consumer.accept(ModEntities.MALACHITE_SPARK, RenderMalachiteManaSpark::new);
        consumer.accept(ModEntities.SAFFRON_SPARK, RenderSaffronManaSpark::new);
        consumer.accept(ModEntities.SHADOW_SPARK, RenderShadowManaSpark::new);
        consumer.accept(ModEntities.CRIMSON_SPARK, RenderCrimsonManaSpark::new);
    }

}
