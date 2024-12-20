package net.neolab.autobotany.blocks.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nonnull;

import net.neolab.autobotany.blocks.pattern.BlockEntityApothecaryPattern;
import net.neolab.autobotany.gui.*;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.moddingx.libx.menu.BlockEntityMenu;


public abstract class ExtraScreenBase<X extends BlockEntityMenu<?>> extends AbstractContainerScreen<X> {
    public final ManaBar manaBar;
    public final WaterBar waterBar;
    public final ManaPoolSlotInfo manaPoolSlotInfo;
    public final RunicAltarSlotInfo runicAltarSlotInfo;
    public final ApothecarySlotInfo apothecarySlotInfo;
    public final IndustrialAgglomerationFactorySlotInfo agglomerationSlotInfo;
    public final AlfheimMarketSlotInfo alfheiumMarketSlotInfo;
    public final OrechidSlotInfo orechidSlotInfo;

    public ExtraScreenBase(X menu, Inventory inventory, Component title, int x, int y) {
        super(menu, inventory, title);

        BlockEntity blockEntity = ((BlockEntityMenu)this.menu).getBlockEntity();
        if (blockEntity instanceof BotanicalTile botanicalTile && !(blockEntity instanceof BlockEntityApothecaryPattern)) {
            this.manaBar = new ManaBar(this, botanicalTile.getMaxMana(), x, y);
        } else {
            this.manaBar = new ManaBar(this, 0, x, y);
        }

        if (blockEntity instanceof BlockEntityApothecaryPattern){
            this.waterBar = new WaterBar(this, ((BlockEntityApothecaryPattern) blockEntity).getFluidInventory().getCapacity(), x, y);
        } else {
            this.waterBar = new WaterBar(this, 0, x, y);
        }

        this.manaPoolSlotInfo = new ManaPoolSlotInfo(this);
        this.runicAltarSlotInfo = new RunicAltarSlotInfo(this);
        this.apothecarySlotInfo = new ApothecarySlotInfo(this);
        this.agglomerationSlotInfo = new IndustrialAgglomerationFactorySlotInfo(this);
        this.alfheiumMarketSlotInfo = new AlfheimMarketSlotInfo(this);
        this.orechidSlotInfo = new OrechidSlotInfo(this);

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
        this.waterBar.setGuiCoord(this.leftPos, this.topPos);
        this.manaPoolSlotInfo.setGuiCoord(this.leftPos, this.topPos);
        this.runicAltarSlotInfo.setGuiCoord(this.leftPos, this.topPos);
        this.apothecarySlotInfo.setGuiCoord(this.leftPos, this.topPos);
        this.agglomerationSlotInfo.setGuiCoord(this.leftPos, this.topPos);
        this.alfheiumMarketSlotInfo.setGuiCoord(this.leftPos, this.topPos);
        this.orechidSlotInfo.setGuiCoord(this.leftPos, this.topPos);
    }


    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        setGuiCoord();

        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
        BlockEntity blockEntity = ((BlockEntityMenu)this.menu).getBlockEntity();
        if (blockEntity instanceof BotanicalTile botanicalTile && !(blockEntity instanceof BlockEntityApothecaryPattern)) {
            this.manaBar.renderHoveredToolTip(poseStack, mouseX, mouseY, botanicalTile.getCurrentMana());
        }

        if (blockEntity instanceof BlockEntityApothecaryPattern){
            this.waterBar.renderHoveredToolTip(poseStack, mouseX, mouseY, ((BlockEntityApothecaryPattern) blockEntity).getFluidInventory().getFluid().getAmount());
        }

    }

    public void drawDefaultGuiBackgroundLayer(PoseStack poseStack, ResourceLocation screenLocation) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, screenLocation);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        BlockEntity blockEntity = ((BlockEntityMenu)this.menu).getBlockEntity();

        if (blockEntity instanceof BotanicalTile botanicalTile && !(blockEntity instanceof BlockEntityApothecaryPattern)) {
            this.manaBar.draw(poseStack, (float)botanicalTile.getCurrentMana());
        }

        if (blockEntity instanceof BlockEntityApothecaryPattern){
            this.waterBar.draw(poseStack, ((BlockEntityApothecaryPattern) blockEntity).getFluidInventory().getFluid().getAmount());
        }
    }
}
