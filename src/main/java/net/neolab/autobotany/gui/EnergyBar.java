package net.neolab.autobotany.gui;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ic2.core.utils.helpers.Formatters;
import net.minecraft.client.Minecraft;
import net.neolab.autobotany.config.LibXClientConfig;
import net.neolab.autobotany.core.LibResources;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Optional;

public class EnergyBar {
    private final Screen parent;
    public static int x;
    public static int y;
    public final int capacity;
    private static int width = 129;
    private static int height = 5;
    public int guiLeft;
    public int guiTop;

    public EnergyBar(Screen parent, int capacity, int x , int y) {
        this.parent = parent;
        this.capacity = capacity;

        this.x = x;
        this.y = y;
    }

    public void setGuiCoord(int x, int y){
        guiLeft = x;
        guiTop = y;
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        if (this.guiLeft + x < mouseX){
            if (mouseX < this.guiLeft + x + width + 2 && this.guiTop + y < mouseY){
                if (mouseY < this.guiTop + y + height + 2){
                    return true;
                }
            }

        }
        return false;
    }

    public void draw(PoseStack poseStack, float energy) {
        RenderSystem.setShaderTexture(0, LibResources.ENERGY_BAR_CURRENT);
        var pct = Math.min(energy / (float)this.capacity, 1.0F);
        var relWidth = (int)((float)(129) * pct);
        Screen.blit(poseStack, this.guiLeft + x, this.guiTop + y, 0.0F, 0.0F, relWidth, 5, 129, 5);

    }

    public void renderHoveredToolTip(PoseStack ms, int mouseX, int mouseY, int energy, int currentConsumption) {
        if (this.isMouseOver(mouseX, mouseY) && LibXClientConfig.numericalFluid) {

            boolean isShiftDown = InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_LSHIFT)
                    || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), InputConstants.KEY_RSHIFT);

            String storedEUText = "§e" + (isShiftDown ? Formatters.EU_FORMAT.format(energy) : Formatters.EU_FORMAT.format(parseEnergyB(energy)) + parseEnergyA(energy));
            String maxEUText = "§e" + (isShiftDown ? Formatters.EU_FORMAT.format(this.capacity) : Formatters.EU_FORMAT.format(parseEnergyB(this.capacity)) + parseEnergyA(this.capacity));
            String consumptionText = "§e" + (isShiftDown ? Formatters.EU_FORMAT.format(currentConsumption) : Formatters.EU_FORMAT.format(parseEnergyB(currentConsumption)) + parseEnergyA(currentConsumption));

            var charge = Component.translatable("autobotany.tooltip.screen.charge",
                    Component.literal(storedEUText),
                    Component.literal(maxEUText));
            var consumption = Component.translatable("autobotany.screen.title.consumption", consumptionText);
            List<Component> text = List.of(charge,  consumption);
            this.parent.renderTooltip(ms, text, Optional.empty(), mouseX, mouseY);
        }

    }

    private static String parseEnergyA(int energy){
        if ((energy % 1000000 > 1 || energy % 1000000 == 0) && energy / 1000000 >= 1)
            return ("M");
        if ((energy % 1000 > 1 || energy % 1000 == 0) && energy / 1000 >= 1) {
            return ("k");
        }
        return "";
    }

    private static int parseEnergyB(int energy){
        if ((energy % 1000000 > 1 || energy % 1000000 == 0) && energy / 1000000 >= 1)
            return energy/1000000;
        if ((energy % 1000 > 1 || energy % 1000 == 0) && energy / 1000 >= 1) {
            return energy/1000;
        }
        return energy;
    }
}
