package net.neolab.autobotany.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neolab.autobotany.blocks.base.IndustrialScreenBase;
import net.neolab.autobotany.blocks.containers.ContainerElectricGreenhouse;
import net.neolab.autobotany.blocks.pattern.BlockEntityElectricGreenhousePattern;
import net.neolab.autobotany.core.LibResources;

import java.util.HashMap;
import java.util.Map;

import static net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase.*;

public class ScreenElectricGreenhouse extends IndustrialScreenBase<ContainerElectricGreenhouse> {
    BlockEntityElectricGreenhousePattern blockEntityElectricGreenhouse;

    public ScreenElectricGreenhouse(ContainerElectricGreenhouse menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 49, 146);

        this.imageWidth = 231;
        this.imageHeight = 246;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;
        this.blockEntityElectricGreenhouse = menu.getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ELECTRIC_GREENHOUSE_GUI);
    }
}
