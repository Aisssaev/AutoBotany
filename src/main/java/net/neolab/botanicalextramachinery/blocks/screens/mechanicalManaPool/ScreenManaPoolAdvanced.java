package net.neolab.botanicalextramachinery.blocks.screens.mechanicalManaPool;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.botanicalextramachinery.helper.GhostItemRenderer;
import net.neolab.botanicalextramachinery.ModItems;
import net.neolab.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.neolab.botanicalextramachinery.blocks.containers.mechanicalManaPool.ContainerManaPoolAdvanced;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.neolab.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ScreenManaPoolAdvanced extends ExtraScreenBase<ContainerManaPoolAdvanced> {

    BlockEntityManaPoolAdvanced blockEntity;
    public ScreenManaPoolAdvanced(ContainerManaPoolAdvanced menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 23, 85);

        this.imageWidth = 176;
        this.imageHeight = 182;
        this.manaPoolSlotInfo.setCoord(
                new int[] {89, 57},
                new int[] {89, 13});

        blockEntity = (BlockEntityManaPoolAdvanced)((ContainerManaPoolAdvanced)this.menu).getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_MECHANICAL_MANA_POOL_GUI);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getCatalysts().stream().map(ItemStack::new).toList(), poseStack, this.leftPos + 89, this.topPos + 60);
        }

        if (blockEntity.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null){
            List<ItemStack> items = new ArrayList<>();
            items.add(new ItemStack(ModItems.catalystManaInfinity));

            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 89, this.topPos + 16);
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
