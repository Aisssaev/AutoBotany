package net.neolab.autobotany.blocks.screens.mechanicalIndustrialAgglomerationFactory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.helper.GhostItemRenderer;
import net.neolab.autobotany.ModItems;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUltimate;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenIndustrialAgglomerationFactoryUltimate extends ExtraScreenBase<ContainerIndustrialAgglomerationFactoryUltimate> {

    BlockEntityIndustrialAgglomerationFactoryUltimate blockEntity;

    public ScreenIndustrialAgglomerationFactoryUltimate(ContainerIndustrialAgglomerationFactoryUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 128);
        this.imageWidth = 184;
        this.imageHeight = 224;

        Map<Integer, int[]> upgrades = new HashMap<>();
        upgrades.put(0, new int[] {9, 84});
        upgrades.put(1, new int[] {158, 84});

        this.agglomerationSlotInfo.setCoord(upgrades);

        blockEntity = (BlockEntityIndustrialAgglomerationFactoryUltimate)((ContainerIndustrialAgglomerationFactoryUltimate)this.menu).getBlockEntity();

    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

        for (int i = 0; i < 2; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null){
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(ModItems.moduleManaInfinity));
                items.add(new ItemStack(ModItems.moduleSpeed));

                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 9 + (149 * i), this.topPos + 93);
            }
        }

        this.agglomerationSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());

        if (blockEntity.getProgress() > 0) {
            RenderSystem.setShaderTexture(0, LibResources.ULTIMATE_INDUSTRIAL_AGGLOMERATION_FACTORY_GUI);

            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            int height = Math.round(16.0F * pct);

            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 72, this.topPos + 58, this.imageWidth, 0, 40, height);
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
