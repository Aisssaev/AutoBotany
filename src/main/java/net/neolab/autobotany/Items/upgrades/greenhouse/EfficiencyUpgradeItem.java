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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.literal("Повышает выработку маны до " + "§6110" + " §r%").withStyle(ChatFormatting.GRAY));
    }
}
