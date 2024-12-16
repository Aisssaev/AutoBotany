package net.neolab.autobotany.blocks.screens.mechanicalAlfheimMarket;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUpgraded;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;

public class ScreenAlfheimMarketUpgraded extends ExtraScreenBase<ContainerAlfheimMarketUpgraded> {

    BlockEntityAlfheimMarketUpgraded blockEntity;

    public ScreenAlfheimMarketUpgraded(ContainerAlfheimMarketUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 87);

        this.imageWidth = 184;
        this.imageHeight = 180;

        this.inventoryLabelY = -999;
        this.titleLabelY = -999;

        blockEntity = (BlockEntityAlfheimMarketUpgraded)((ContainerAlfheimMarketUpgraded)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_ALFHEIM_MARKET_GUI);

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.UPGRADED_ALFHEIM_MARKET_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 84, this.topPos + 39, this.imageWidth, 0, Math.round(16.0F * pct), 16);
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
