package net.neolab.autobotany.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.helper.GhostItemRenderer;
import net.neolab.autobotany.ModItems;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalOrechid.ContainerOrechidUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidUltimate;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenOrechidUltimate extends ExtraScreenBase<ContainerOrechidUltimate> {

    BlockEntityOrechidUltimate blockEntity;

    public ScreenOrechidUltimate(ContainerOrechidUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 147);

        this.imageWidth = 184;
        this.imageHeight = 243;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> ores = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        upgrades.put(0, new int[] {8, 80});
        upgrades.put(1, new int[] {160, 80});


        ores.put(2, new int[] {30, 19});
        ores.put(3, new int[] {48, 19});
        ores.put(4, new int[] {66, 19});
        ores.put(5, new int[] {84, 19});
        ores.put(6, new int[] {102, 19});
        ores.put(7, new int[] {120, 19});
        ores.put(7, new int[] {138, 19});


        this.orechidSlotInfo.setCoord(ores, upgrades);

        blockEntity = (BlockEntityOrechidUltimate)((ContainerOrechidUltimate)this.menu).getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ULTIMATE_ORECHID_GUI);

        for (int i = 0; i < 2; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null){
                List<ItemStack> items = new ArrayList<>();
                items.add(new ItemStack(ModItems.moduleManaInfinity));
                items.add(new ItemStack(ModItems.moduleStoneInfinity));

                GhostItemRenderer.renderGhostItem(items, poseStack, this.leftPos + 8 + 152 * i, this.topPos + 83);
            }
        }

        this.orechidSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
