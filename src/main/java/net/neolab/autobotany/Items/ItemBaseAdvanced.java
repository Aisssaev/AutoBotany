package net.neolab.autobotany.Items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.base.ItemBase;
import org.moddingx.libx.mod.ModX;

import java.util.List;

public class ItemBaseAdvanced extends ItemBase {
    private final List<Component> tooltip;
    public ItemBaseAdvanced(ModX mod, Properties properties, List<Component> components) {
        super(mod, properties);
        this.tooltip = components;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.addAll(this.tooltip);
    }
}
