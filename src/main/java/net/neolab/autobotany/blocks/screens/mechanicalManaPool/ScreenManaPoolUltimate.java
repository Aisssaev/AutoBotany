package net.neolab.autobotany.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;

import net.neolab.autobotany.ModItems;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalManaPool.ContainerManaPoolUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.neolab.autobotany.helper.GhostItemRenderer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ScreenManaPoolUltimate extends ExtraScreenBase<ContainerManaPoolUltimate> {

    BlockEntityManaPoolUltimate blockEntity;
    public ScreenManaPoolUltimate(ContainerManaPoolUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 92);

        this.imageWidth = 176;
        this.imageHeight = 189;
        this.manaPoolSlotInfo.setCoord(
                new int[] {80, 63},
                new int[] {80, 16});

        blockEntity = (BlockEntityManaPoolUltimate)((ContainerManaPoolUltimate)this.menu).getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_MECHANICAL_MANA_POOL_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 80, this.topPos + 66);
        }
        if (blockEntity.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.moduleManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 80, this.topPos + 19);
        }

        this.manaPoolSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
