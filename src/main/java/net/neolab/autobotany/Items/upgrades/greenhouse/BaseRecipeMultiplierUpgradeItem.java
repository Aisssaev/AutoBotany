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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.literal("Повышает множитель рецептов до " + "§6" + multiplier).withStyle(ChatFormatting.GRAY));
    }

}
