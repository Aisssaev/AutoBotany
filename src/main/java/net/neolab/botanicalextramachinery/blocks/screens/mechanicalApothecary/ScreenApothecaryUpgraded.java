package net.neolab.botanicalextramachinery.blocks.screens.mechanicalApothecary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.neolab.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.neolab.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryUpgraded;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUpgraded;
import net.neolab.botanicalextramachinery.core.LibResources;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.neolab.botanicalextramachinery.helper.GhostItemRenderer;
import org.moddingx.libx.util.data.TagAccess;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenApothecaryUpgraded extends ExtraScreenBase<ContainerApothecaryUpgraded> {
    private final BlockEntityApothecaryUpgraded tile;

    public ScreenApothecaryUpgraded(ContainerApothecaryUpgraded screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title, 27, 113);
        this.imageWidth = 184;
        this.imageHeight = 209;

        this.titleLabelY = -99999;
        this.inventoryLabelY = -99999;

        Map<Integer, int[]> seed = new HashMap<>();

        seed.put(0, new int[] {84, 89});

        this.apothecarySlotInfo.setCoord(seed, null);


        this.tile = (BlockEntityApothecaryUpgraded)((ContainerApothecaryUpgraded)this.menu).getLevel().getBlockEntity(((ContainerApothecaryUpgraded)this.menu).getPos());
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_MECHANICAL_APOTHECARY_GUI);
        this.drawLabelText(poseStack);


        if (this.tile.getInventory().getStackInSlot(0).isEmpty()) {
            List<ItemStack> items = TagAccess.ROOT.get(Tags.Items.SEEDS).stream().map(Holder::value).map(ItemStack::new).toList();
            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 84, this.topPos + 92);
        }

        this.apothecarySlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, this.tile.getInventory(), new boolean[]{true, false});


        if (this.tile.getProgress() > 0) {
            float pctProgress = Math.min((float) this.tile.getProgress() / (float) this.tile.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.UPGRADED_MECHANICAL_APOTHECARY_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 87, this.topPos + 34, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }
    }

    private void drawLabelText(PoseStack poseStack){
        Component titleText = Component.translatable("block.botanicalextramachinery.upgraded_apothecary");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        poseStack.pushPose();
        poseStack.scale(scale, scale, scale);
        this.font.draw(poseStack, titleText,
                (leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale,
                (topPos + 4) /scale, 0x00);
        poseStack.popPose();
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }

}