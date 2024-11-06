package net.neolab.botanicalextramachinery.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public interface GreenhouseRecipe extends Recipe<Container> {
    ResourceLocation TYPE_ID = new ResourceLocation("autobotany", "greenhouse");
    ResourceLocation IGNEM_TYPE_ID = new ResourceLocation("autobotany", "greenhouse_ignem");
    ResourceLocation MARIMORPHOSIS_TYPE_ID = new ResourceLocation("autobotany", "marimorphosis");

    Ingredient getConsumableInput();

    Ingredient getNonConsumableInput();

    int getManaOutputPerTick();

    default boolean matches(@NotNull Container container, @NotNull Level level) {
        var consumable = container.getItem(0);
        var nonConsumable = container.getItem(1);

        return getConsumableInput().test(consumable) && getNonConsumableInput().test(nonConsumable);
    }

    default int assembleMana() {
        return getManaOutputPerTick();
    }

    default ItemStack assemble(Container container) {
        return ItemStack.EMPTY;
    }

    default boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    default ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    default boolean isSpecial() {
        return true;
    }

}
