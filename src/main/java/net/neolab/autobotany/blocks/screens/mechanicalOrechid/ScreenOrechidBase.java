package net.neolab.autobotany.blocks.screens.mechanicalOrechid;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.autobotany.blocks.base.ExtraScreenBase;
import net.neolab.autobotany.blocks.containers.mechanicalOrechid.ContainerOrechidBase;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidBase;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ScreenOrechidBase extends ExtraScreenBase<ContainerOrechidBase> {

    BlockEntityOrechidBase blockEntity;

    public ScreenOrechidBase(ContainerOrechidBase menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 113);

        this.imageWidth = 184;
        this.imageHeight = 209;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> ores = new HashMap<>();

        ores.put(0, new int[] {66, 19});
        ores.put(1, new int[] {84, 19});
        ores.put(2, new int[] {102, 19});

        this.orechidSlotInfo.setCoord(ores, null);

        blockEntity = (BlockEntityOrechidBase)((ContainerOrechidBase)this.menu).getBlockEntity();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.BASE_ORECHID_GUI);

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
