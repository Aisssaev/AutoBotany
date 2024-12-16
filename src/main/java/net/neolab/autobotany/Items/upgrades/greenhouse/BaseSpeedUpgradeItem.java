package net.neolab.autobotany.Items.upgrades.greenhouse;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import org.moddingx.libx.mod.ModX;

public class BaseSpeedUpgradeItem extends BaseUpgradeItem {
    double multiplier;
    int consumed;
    public BaseSpeedUpgradeItem(ModX mod, Properties props, double multiplier, int consumed) {
        super(mod, props);
        this.multiplier = multiplier;
        this.consumed = consumed;
    }

    @Override
    public UpgradeType getType(ItemStack itemStack) {
        return UpgradeType.PROCESSING;
    }

    @Override
    public double getSpeedMultiplier(ItemStack item, BaseTileEntity tile) {
        return multiplier;
    }

    @Override
    public int getEnergyConsumed(ItemStack item, BaseTileEntity tile) {
        return consumed;
    }
}
