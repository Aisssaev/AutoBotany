package net.neolab.autobotany.client;

import net.neolab.autobotany.ModEntities;
import net.neolab.autobotany.render.RenderCrimsonManaSpark;
import net.neolab.autobotany.render.RenderMalachiteManaSpark;
import net.neolab.autobotany.render.RenderSaffronManaSpark;
import net.neolab.autobotany.render.RenderShadowManaSpark;
import vazkii.botania.client.render.entity.EntityRenderers.EntityRendererConsumer;

public class EntityRenderers {

    public static void registerEntityRenderers(EntityRendererConsumer consumer) {
        consumer.accept(ModEntities.MALACHITE_SPARK, RenderMalachiteManaSpark::new);
        consumer.accept(ModEntities.SAFFRON_SPARK, RenderSaffronManaSpark::new);
        consumer.accept(ModEntities.SHADOW_SPARK, RenderShadowManaSpark::new);
        consumer.accept(ModEntities.CRIMSON_SPARK, RenderCrimsonManaSpark::new);
    }

}
