package net.neolab.autobotany.blocks.screens.mechanicalApothecary;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalApothecary.ContainerApothecaryAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryAdvanced;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.neolab.autobotany.helper.GhostItemRenderer;
import org.moddingx.libx.util.data.TagAccess;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenApothecaryAdvanced extends ExtraScreenBase<ContainerApothecaryAdvanced> {
    private final BlockEntityApothecaryAdvanced tile;

    public ScreenApothecaryAdvanced(ContainerApothecaryAdvanced screenMenu, Inventory inventory, Component title) {
        super(screenMenu, inventory, title, 27, 123);
        this.imageWidth = 184;
        this.imageHeight = 220;

        this.titleLabelY = -99999;
        this.inventoryLabelY = -99999;

        Map<Integer, int[]> seed = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        seed.put(0, new int[] {84, 94});
        upgrades.put(1, new int[] {47, 94});

        this.apothecarySlotInfo.setCoord(seed, upgrades);

        this.tile = (BlockEntityApothecaryAdvanced)((ContainerApothecaryAdvanced)this.menu).getLevel().getBlockEntity(((ContainerApothecaryAdvanced)this.menu).getPos());
    }

    protected void renderBg(@Nonnull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ADVANCED_MECHANICAL_APOTHECARY_GUI);

        if (this.tile.getInventory().getStackInSlot(0).isEmpty()) {
            List<ItemStack> items = TagAccess.ROOT.get(Tags.Items.SEEDS).stream().map(Holder::value).map(ItemStack::new).toList();
            GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 84, this.topPos + 97);
        }

        if (this.tile.getInventory().getStackInSlot(1).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(this.tile.getUpgrades(), poseStack, this.leftPos + 47, this.topPos + 97);
        }

        this.apothecarySlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, this.tile.getInventory(), new boolean[]{true, true});


        if (this.tile.getProgress() > 0) {
            float pctProgress = Math.min((float)this.tile.getProgress() / (float)this.tile.getMaxProgress(), 1.0F);
            RenderSystem.setShaderTexture(0, LibResources.ADVANCED_MECHANICAL_APOTHECARY_GUI);
            RenderHelper.drawTexturedModalRect(poseStack, this.leftPos + 87, this.topPos + 34, this.imageWidth, 0, Math.round(11.0F * pctProgress), 37);
        }
    }
}
