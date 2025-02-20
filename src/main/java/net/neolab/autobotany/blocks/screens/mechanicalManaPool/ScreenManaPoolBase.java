package net.neolab.autobotany.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.helper.GhostItemRenderer;
import net.neolab.autobotany.blocks.containers.mechanicalManaPool.ContainerManaPoolBase;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenManaPoolBase extends ExtraScreenBase<ContainerManaPoolBase> {

    BlockEntityManaPoolBase blockEntity;
    public ScreenManaPoolBase(ContainerManaPoolBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 85);

        this.imageWidth = 176;
        this.imageHeight = 182;
        this.manaPoolSlotInfo.setCoord(
                new int[] {89, 57},
                new int[] {0, 0});

        blockEntity = (BlockEntityManaPoolBase)((ContainerManaPoolBase)this.menu).getBlockEntity();

    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_MECHANICAL_MANA_POOL_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 89, this.topPos + 60);
        }

        this.manaPoolSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
