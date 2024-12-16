package net.neolab.autobotany.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.neolab.autobotany.ModItems;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.base.IndustrialScreenBase;
import net.neolab.autobotany.blocks.containers.ContainerJadedAmaranthus;
import net.neolab.autobotany.blocks.tiles.BlockEntityJadedAmaranthus;
import net.neolab.autobotany.core.LibResources;
import net.neolab.autobotany.helper.GhostItemRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenJadedAmaranthus extends ExtraScreenBase<ContainerJadedAmaranthus> {
    BlockEntityJadedAmaranthus blockEntity;



    public ScreenJadedAmaranthus(ContainerJadedAmaranthus menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 22, 82);

        this.imageWidth = 175;
        this.imageHeight = 180;

        blockEntity = this.menu.getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.JADED_AMARANTHUS_GUI);
    }
}
