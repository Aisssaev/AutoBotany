package net.neolab.autobotany.blocks.base;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neolab.autobotany.gui.EnergyBar;
import net.neolab.autobotany.gui.ManaBar;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

public abstract class IndustrialScreenBase<X extends BlockEntityMenu<?>> extends AbstractContainerScreen<X> {
    public final ManaBar manaBar;
    public final EnergyBar energyBar;

    public IndustrialScreenBase(X menu, Inventory inventory, Component title, int x, int y) {
        super(menu, inventory, title);

        var blockEntity = ((BlockEntityMenu<?>)this.menu).getBlockEntity();
        if (blockEntity instanceof ElectricBotanicalTile electricBotanicalTile) {
            this.manaBar = new ManaBar(this, electricBotanicalTile.getMaxMana(), x, y);
            this.energyBar = new EnergyBar(this, electricBotanicalTile.getMaxEU(), x, y-13);
        } else {
            this.manaBar = new ManaBar(this, 0, x, y);
            this.energyBar = new EnergyBar(this, 0, x, y);
        }


    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (width - this.imageWidth) / 2;
        this.topPos = (height - this.imageHeight) / 2;

        inventoryLabelY = -999;
        titleLabelY = -999;
    }

    public void setGuiCoord(){
        this.manaBar.setGuiCoord(this.leftPos, this.topPos);
        this.energyBar.setGuiCoord(this.leftPos, this.topPos);
    }


    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        setGuiCoord();

        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
        var blockEntity = ((BlockEntityMenu<?>)this.menu).getBlockEntity();
        if (blockEntity instanceof ElectricBotanicalTile bt) {
            this.manaBar.renderHoveredToolTip(poseStack, mouseX, mouseY, bt.getCurrentMana());
            this.energyBar.renderHoveredToolTip(poseStack, mouseX, mouseY, bt.getStoredEU(), bt.getCurrentConsumption());
        }
    }

    public void drawDefaultGuiBackgroundLayer(PoseStack poseStack, ResourceLocation screenLocation) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, screenLocation);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        var blockEntity = ((BlockEntityMenu<?>)this.menu).getBlockEntity();
        if (blockEntity instanceof ElectricBotanicalTile bt) {
            this.manaBar.draw(poseStack, (float)bt.getCurrentMana());
            this.energyBar.draw(poseStack, (float)bt.getStoredEU());
        }

    }
}
