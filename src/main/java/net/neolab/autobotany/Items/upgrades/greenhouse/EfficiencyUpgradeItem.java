package net.neolab.autobotany.Items.upgrades.greenhouse;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import org.moddingx.libx.mod.ModX;

public class EfficiencyUpgradeItem extends BaseUpgradeItem {
    public EfficiencyUpgradeItem(ModX mod, Properties props) {
        super(mod, props);
    }

    @Override
    public UpgradeType getType(ItemStack itemStack) {
        return UpgradeType.EFFICIENCY;
    }

    @Override
    public double getEfficiencyMultiplier(ItemStack item, BaseTileEntity tile) {
        return 1.1;
    }
}
