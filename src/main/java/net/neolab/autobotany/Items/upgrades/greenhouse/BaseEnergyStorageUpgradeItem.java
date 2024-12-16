package net.neolab.autobotany.Items.upgrades.greenhouse;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import org.moddingx.libx.mod.ModX;

public class BaseEnergyStorageUpgradeItem extends BaseUpgradeItem {
    private int storage;
    public BaseEnergyStorageUpgradeItem(ModX mod, Properties props, int storage) {
        super(mod, props);
        this.storage = storage;
    }

    @Override
    public UpgradeType getType(ItemStack itemStack) {
        return UpgradeType.ENERGY_STORAGE;
    }

    @Override
    public int getEnergyStorage(ItemStack item, BaseTileEntity tile) {
        return storage;
    }
}
