package net.neolab.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.botanicalextramachinery.helper.GhostItemRenderer;
import net.neolab.botanicalextramachinery.ModItems;
import net.neolab.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.neolab.botanicalextramachinery.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketUltimate;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUltimate;
import net.neolab.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenAlfheimMarketUltimate extends ExtraScreenBase<ContainerAlfheimMarketUltimate> {

    BlockEntityAlfheimMarketUltimate blockEntity;

    public ScreenAlfheimMarketUltimate(ContainerAlfheimMarketUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 115);

        this.imageWidth = 184;
        this.imageHeight = 208;

        this.inventoryLabelY = -999;
        this.titleLabelY = -999;

        Map<Integer, int[]> upgrades = new HashMap<>();
        upgrades.put(0, new int[] {84, 87});

        this.agglomerationSlotInfo.setCoord(upgrades);

        blockEntity = (BlockEntityAlfheimMarketUltimate)((ContainerAlfheimMarketUltimate)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_ALFHEIM_MARKET_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.catalystManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 84 , this.topPos + 87);
        }

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.ULTIMATE_ALFHEIM_MARKET_GUI);
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
