package net.neolab.autobotany.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neolab.autobotany.blocks.base.IndustrialScreenBase;
import net.neolab.autobotany.blocks.containers.ContainerPrimitiveGreenhouse;
import net.neolab.autobotany.blocks.tiles.BlockEntityPrimitiveGreenhouse;
import net.neolab.autobotany.core.LibResources;

public class ScreenPrimitiveGreenhouse extends IndustrialScreenBase<ContainerPrimitiveGreenhouse> {
    BlockEntityPrimitiveGreenhouse tile;
    int progress;

    public ScreenPrimitiveGreenhouse(ContainerPrimitiveGreenhouse menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 22, 82);
        this.imageWidth = 175;
        this.imageHeight = 180;
        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;
        this.progress = this.menu.getBlockEntity().getProgress();
        this.tile = this.menu.getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.PRIMITIVE_GREENHOUSE_GUI);

    }
}
