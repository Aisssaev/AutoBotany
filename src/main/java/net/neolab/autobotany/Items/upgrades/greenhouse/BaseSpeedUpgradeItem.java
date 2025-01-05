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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.literal("Ускоряет работу теплицы на " + "§6" + ((int)((1-multiplier)*100)) + "§r%").withStyle(ChatFormatting.GRAY));
    }
}
