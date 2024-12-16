package net.neolab.autobotany.render;

import net.neolab.autobotany.client.ModModels;
import net.neolab.autobotany.entities.manaSpark.EntityCrimsonManaSpark;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.core.handler.MiscellaneousModels;
import vazkii.botania.client.render.entity.BaseSparkRenderer;

public class RenderCrimsonManaSpark extends BaseSparkRenderer<EntityCrimsonManaSpark> {
    public RenderCrimsonManaSpark(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected TextureAtlasSprite getBaseIcon(EntityCrimsonManaSpark entity) {
        return ModModels.INSTANCE.crimsonSparkWorldIcon.sprite();
    }

    @Override
    protected @Nullable TextureAtlasSprite getSpinningIcon(EntityCrimsonManaSpark entity) {
        int upgrade = entity.getUpgrade().ordinal() - 1;
        return upgrade >= 0 && upgrade < MiscellaneousModels.INSTANCE.sparkUpgradeIcons.length ? MiscellaneousModels.INSTANCE.sparkUpgradeIcons[upgrade].sprite() : null;
    }
}
