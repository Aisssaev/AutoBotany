package net.neolab.botanicalextramachinery.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.neolab.botanicalextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidUpgraded;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidUpgraded;
import net.neolab.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ScreenOrechidUpgraded extends ExtraScreenBase<ContainerOrechidUpgraded> {

    BlockEntityOrechidUpgraded blockEntity;

    public ScreenOrechidUpgraded(ContainerOrechidUpgraded menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 113);

        this.imageWidth = 184;
        this.imageHeight = 209;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> ores = new HashMap<>();

        ores.put(0, new int[] {48, 19});
        ores.put(1, new int[] {66, 19});
        ores.put(2, new int[] {84, 19});
        ores.put(3, new int[] {102, 19});
        ores.put(4, new int[] {120, 19});

        this.orechidSlotInfo.setCoord(ores, null);

        blockEntity = (BlockEntityOrechidUpgraded)((ContainerOrechidUpgraded)this.menu).getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.UPGRADED_ORECHID_GUI);
        this.orechidSlotInfo.renderHoveredToolTip(poseStack, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, false});
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
