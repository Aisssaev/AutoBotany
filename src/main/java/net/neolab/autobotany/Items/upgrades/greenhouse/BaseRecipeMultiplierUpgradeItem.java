package net.neolab.autobotany.Items.upgrades.greenhouse;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import org.moddingx.libx.mod.ModX;

public class BaseRecipeMultiplierUpgradeItem extends BaseUpgradeItem {
    int multiplier;
    public BaseRecipeMultiplierUpgradeItem(ModX mod, Properties props, int multiplier) {
        super(mod, props);
        this.multiplier = multiplier;
    }

    @Override
    public UpgradeType getType(ItemStack itemStack) {
        return UpgradeType.RECIPE;
    }

    @Override
    public int getRecipeMultiplier(ItemStack item, BaseTileEntity tile) {
        return multiplier;
    }
}
