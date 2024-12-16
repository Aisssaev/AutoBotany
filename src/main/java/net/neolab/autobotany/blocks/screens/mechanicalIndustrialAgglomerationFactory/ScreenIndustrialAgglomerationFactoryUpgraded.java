package net.neolab.autobotany.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUpgraded;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class ScreenIndustrialAgglomerationFactoryUpgraded extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryUpgraded> {

    BlockEntityIndustrialAgglomerationFactoryUpgraded blockEntity;
    public ScreenIndustrialAgglomerationFactoryUpgraded(ContainerIndustrialAgglomerationFactoryUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 96);
        this.imageWidth = 184;
        this.imageHeight = 192;

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryUpgraded)((ContainerIndustrialAgglomerationFactoryUpgraded)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.UPGRADED_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 40, this.imageWidth, 0, 40, height);
        }
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
