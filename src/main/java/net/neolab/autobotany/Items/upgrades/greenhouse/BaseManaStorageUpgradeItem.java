package net.neolab.autobotany.Items.upgrades.greenhouse;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import org.moddingx.libx.mod.ModX;

public class BaseManaStorageUpgradeItem extends BaseUpgradeItem {
    int storage;
    public BaseManaStorageUpgradeItem(ModX mod, Properties props, int storage) {
        super(mod, props);
        this.storage = storage;
    }

    @Override
    public UpgradeType getType(ItemStack itemStack) {
        return UpgradeType.MANA_STORAGE;
    }

    @Override
    public int getManaStorage(ItemStack item, BaseTileEntity tile) {
        return storage;
    }
}
