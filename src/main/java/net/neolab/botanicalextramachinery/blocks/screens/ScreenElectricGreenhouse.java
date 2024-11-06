package net.neolab.botanicalextramachinery.blocks.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neolab.botanicalextramachinery.blocks.base.IndustrialScreenBase;
import net.neolab.botanicalextramachinery.blocks.containers.ContainerElectricGreenhouse;
import net.neolab.botanicalextramachinery.blocks.pattern.BlockEntityElectricGreenhousePattern;
import net.neolab.botanicalextramachinery.core.LibResources;

import java.util.HashMap;
import java.util.Map;

import static net.neolab.botanicalextramachinery.blocks.tiles.BlockEntityElectricGreenhouseBase.*;

public class ScreenElectricGreenhouse extends IndustrialScreenBase<ContainerElectricGreenhouse> {
    BlockEntityElectricGreenhousePattern blockEntityElectricGreenhouse;

    public ScreenElectricGreenhouse(ContainerElectricGreenhouse menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 113);

        this.imageWidth = 184;
        this.imageHeight = 209;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        Map<Integer, int[]> inputSlots = new HashMap<>();
        var slotSize = 18;
        var j = 0;
        for (int i = FIRST_NONCONS_INPUT_SLOT; i <= LAST_NONCONS_INPUT_SLOT; i++) {
            inputSlots.put(i, new int[]{47 + i * slotSize, 21});
        }
        for (int i = FIRST_CONS_INPUT_SLOT; i <= LAST_CONS_INPUT_SLOT; i++) {
            inputSlots.put(i, new int[]{11 + j * slotSize, 45});
            j++;
        }
        this.blockEntityElectricGreenhouse = menu.getBlockEntity();
    }


    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.ELECTRIC_GREENHOUSE_GUI);
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        var textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }

}
