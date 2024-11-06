package net.neolab.botanicalextramachinery.blocks.screens.mechanicalDaisy;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.neolab.botanicalextramachinery.blocks.containers.mechanicalDaisy.ContainerDaisyUltimate;
import net.neolab.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenDaisyUltimate extends ExtraScreenBase<ContainerDaisyUltimate> {
    public ScreenDaisyUltimate(ContainerDaisyUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, -999, -999);

        this.imageWidth = 184;
        this.imageHeight = 220;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, LibResources.ULTIMATE_MECHANICAL_DAISY_GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        this.drawLabelText(poseStack);
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botanicalextramachinery.ultimate_daisy");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 5) /scale, 0x00);
        poseStack.popPose();
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}