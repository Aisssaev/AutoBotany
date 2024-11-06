package net.neolab.botanicalextramachinery.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neolab.botanicalextramachinery.config.LibXClientConfig;
import org.moddingx.libx.inventory.BaseItemStackHandler;

import java.util.HashMap;
import java.util.Map;

public class GreenhouseSlotInfo {

    private final Screen parent;
    public static Map<Integer, int[]> inputSlots = new HashMap<>();
    public static Map<Integer, int[]> upgradeSlots = new HashMap<>();
    private static final int SLOT_WIDTH = 16;
    private static final int SLOT_HEIGHT = 16;
    private int guiLeft;
    private int guiTop;

    public GreenhouseSlotInfo(Screen parent) {
        this.parent = parent;
    }

    public void setSlotCoords(Map<Integer, int[]> inputSlotCoords, Map<Integer, int[]> upgradeSlotCoords) {
        if (inputSlotCoords != null) {
            inputSlots = inputSlotCoords;
        }
        if (upgradeSlotCoords != null) {
            upgradeSlots = upgradeSlotCoords;
        }
    }

    public void setGuiCoords(int x, int y) {
        guiLeft = x;
        guiTop = y;
    }

    private boolean isMouseOver(int mouseX, int mouseY, int slotX, int slotY) {
        return guiLeft + slotX <= mouseX && mouseX <= guiLeft + slotX + SLOT_WIDTH
                && guiTop + slotY <= mouseY && mouseY <= guiTop + slotY + SLOT_HEIGHT;
    }

    public void renderHoveredToolTip(PoseStack poseStack, int mouseX, int mouseY, BaseItemStackHandler inventory, boolean[] setInfo) {
        // Рендер підказок для вхідних слотів
        if (setInfo[0] && LibXClientConfig.slotInfo && !inputSlots.isEmpty()) {
            for (Map.Entry<Integer, int[]> entry : inputSlots.entrySet()) {
                int key = entry.getKey();
                int[] coords = entry.getValue();
                if (inventory.getStackInSlot(key).isEmpty() && isMouseOver(mouseX, mouseY, coords[0], coords[1])) {
                    Component text = Component.translatable("botanicalextramachinery.tooltip.screen.input_slot");
                    parent.renderTooltip(poseStack, text, mouseX, mouseY);
                }
            }
        }

        // Рендер підказок для слотів покращень
        if (setInfo[1] && LibXClientConfig.slotInfo && !upgradeSlots.isEmpty()) {
            for (Map.Entry<Integer, int[]> entry : upgradeSlots.entrySet()) {
                int key = entry.getKey();
                int[] coords = entry.getValue();
                if (inventory.getStackInSlot(key).isEmpty() && isMouseOver(mouseX, mouseY, coords[0], coords[1])) {
                    Component text = Component.translatable("botanicalextramachinery.tooltip.screen.upgrade_slot");
                    parent.renderTooltip(poseStack, text, mouseX, mouseY);
                }
            }
        }
    }
}

