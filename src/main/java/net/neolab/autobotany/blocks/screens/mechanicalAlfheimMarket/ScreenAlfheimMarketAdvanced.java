package net.neolab.autobotany.blocks.screens.mechanicalAlfheimMarket;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.ModItems;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketAdvanced;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.helper.GhostItemRenderer;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenAlfheimMarketAdvanced extends ExtraScreenBase<ContainerAlfheimMarketAdvanced> {

    BlockEntityAlfheimMarketAdvanced blockEntity;

    public ScreenAlfheimMarketAdvanced(ContainerAlfheimMarketAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 113);

        this.imageWidth = 184;
        this.imageHeight = 209;

        this.inventoryLabelY = -999;
        this.titleLabelY = -999;

        Map<Integer, int[]> upgrades = new HashMap<>();
        upgrades.put(0, new int[] {84, 87});

        this.agglomerationSlotInfo.setCoord(upgrades);

        blockEntity = (BlockEntityAlfheimMarketAdvanced)((ContainerAlfheimMarketAdvanced)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_ALFHEIM_MARKET_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.moduleManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 84 , this.topPos + 87);
        }

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.ADVANCED_ALFHEIM_MARKET_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 84, this.topPos + 45, this.imageWidth, 0, Math.round(16.0F * pct), 16);
        }

        this.agglomerationSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory());
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
