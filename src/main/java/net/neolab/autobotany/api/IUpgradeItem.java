package net.neolab.autobotany.api;

import ic2.core.block.base.tiles.BaseTileEntity;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public interface IUpgradeItem {
    UpgradeType getType(ItemStack stack);

    EnumSet<Functions> getFunctions(ItemStack item);

    void onInstall(ItemStack item, BaseTileEntity tile);

    double getEfficiencyMultiplier(ItemStack item, BaseTileEntity tile);

    double getSpeedMultiplier(ItemStack item, BaseTileEntity tile);

    int getEnergyConsumed(ItemStack item, BaseTileEntity tile);

    int getManaStorage(ItemStack item, BaseTileEntity tile);

    int getEnergyStorage(ItemStack item, BaseTileEntity tile);

    int getRecipeMultiplier(ItemStack item, BaseTileEntity tile);

    void onMachineProcessed(ItemStack item, BaseTileEntity tile);

    void onTick(ItemStack item, BaseTileEntity tile);


    public static enum Functions {
        TICK,
        RECIPE;

        private Functions() {
        }
    }

    public static enum UpgradeType {
        EFFICIENCY("EFFICIENCY"),
        MANA_STORAGE("MANA_STORAGE"),
        ENERGY_STORAGE("ENERGY_STORAGE"),
        PROCESSING("PROCESSING"),
        RECIPE("RECIPE");

        final String[] names;

        private UpgradeType(String... names) {
            this.names = names;
        }
    }
}
