package net.neolab.autobotany.Items.upgrades.base;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.api.IUpgradeItem;
import org.moddingx.libx.base.ItemBase;
import org.moddingx.libx.mod.ModX;

import java.util.EnumSet;

public abstract class BaseUpgradeItem extends ItemBase implements IUpgradeItem {
    public final EnumSet<Functions> functions = EnumSet.noneOf(Functions.class);

    public BaseUpgradeItem(ModX mod, Properties props) {
        super(mod, props);
    }

    @Override
    public EnumSet<Functions> getFunctions(ItemStack stack) {
        return this.functions;
    }

    @Override
    public void onInstall(ItemStack item, BaseTileEntity tile) {
    }

    public abstract UpgradeType getType(ItemStack itemStack);

    @Override
    public double getEfficiencyMultiplier(ItemStack item, BaseTileEntity tile) {
        return 1;
    }

    @Override
    public double getSpeedMultiplier(ItemStack item, BaseTileEntity tile) {
        return 1;
    }

    @Override
    public int getEnergyConsumed(ItemStack item, BaseTileEntity tile) {
        return 0;
    }

    @Override
    public int getEnergyStorage(ItemStack item, BaseTileEntity tile) {
        return 0;
    }

    @Override
    public int getManaStorage(ItemStack item, BaseTileEntity tile) {
        return 0;
    }

    @Override
    public int getRecipeMultiplier(ItemStack item, BaseTileEntity tile) {
        return 1;
    }

    @Override
    public void onMachineProcessed(ItemStack item, BaseTileEntity tile) {

    }

    @Override
    public void onTick(ItemStack item, BaseTileEntity tile) {
    }
}
