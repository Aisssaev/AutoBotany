package net.neolab.botanicalextramachinery.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.neolab.botanicalextramachinery.config.LibXClientConfig;
import net.neolab.botanicalextramachinery.core.LibResources;
import net.neolab.botanicalextramachinery.util.NumberFormatter;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

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

    public void renderHoveredToolTip(PoseStack ms, int mouseX, int mouseY, int energy) {
        if (this.isMouseOver(mouseX, mouseY) && LibXClientConfig.numericalFluid) {

            var text = Component.translatable("botanicalextramachinery.tooltip.screen.energy",
                    Component.literal(NumberFormatter.formatInteger(energy)),
                    Component.literal(NumberFormatter.formatInteger(this.capacity)));

            this.parent.renderTooltip(ms, text, mouseX, mouseY);
        }

    }
}
