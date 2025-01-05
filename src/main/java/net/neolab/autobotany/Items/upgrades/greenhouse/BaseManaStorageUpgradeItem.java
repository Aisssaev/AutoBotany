package net.neolab.autobotany.Items.upgrades.greenhouse;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.mod.ModX;

import java.util.List;

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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.literal("Устанавливает хранилище маны на " + "§6" + storage + "§r мБ").withStyle(ChatFormatting.GRAY));
    }
}
