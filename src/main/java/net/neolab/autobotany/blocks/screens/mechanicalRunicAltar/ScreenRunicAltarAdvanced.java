package net.neolab.autobotany.blocks.screens.mechanicalRunicAltar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.helper.GhostItemRenderer;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarAdvanced;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenRunicAltarAdvanced extends ExtraScreenBase<ContainerRunicAltarAdvanced> {

    BlockEntityRunicAltarAdvanced blockEntity;
    public ScreenRunicAltarAdvanced(ContainerRunicAltarAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 123);

        this.imageWidth = 184;
        this.imageHeight = 219;

        Map<Integer, int[]> livingrock = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        livingrock.put(0, new int[] {66, 94});
        livingrock.put(1, new int[] {84, 94});
        livingrock.put(2, new int[] {102, 94});
        upgrades.put(3, new int[] {27, 94});

        this.runicAltarSlotInfo.setCoord(livingrock, upgrades);

        blockEntity = (BlockEntityRunicAltarAdvanced)((ContainerRunicAltarAdvanced)this.menu).getBlockEntity();
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_MECHANICAL_RUNIC_ALTAR_GUI);

        for (int i = 0; i < 3; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(new ItemStack(BotaniaBlocks.livingrock), poseStack, this.leftPos + 66 + i * 18, this.topPos + 97);
            }
        }

        if (blockEntity.getInventory().getStackInSlot(3).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), poseStack, this.leftPos + 27, this.topPos + 97);
        }

        this.runicAltarSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.ADVANCED_MECHANICAL_RUNIC_ALTAR_GUI);
            this.blit(poseStack, this.leftPos + 87, this.topPos + 34, this.imageWidth, 0, Math.round(11.0F * pct), 37);
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
