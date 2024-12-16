package net.neolab.autobotany.compat.category;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neolab.autobotany.crafting.GreenhouseRecipe;
import org.jetbrains.annotations.NotNull;

public class GreenhouseRecipeCategory implements IRecipeCategory<GreenhouseRecipe> {
    public static final RecipeType<GreenhouseRecipe> TYPE = RecipeType.create("ae2extras", "greenhouse", GreenhouseRecipe.class);
    private final ItemStack stack;
    protected IDrawable background;
    protected IDrawable progress;
    protected IDrawable charge;
    protected IDrawable icon;

    public GreenhouseRecipeCategory(IGuiHelper helper, ResourceLocation texture, ItemLike provider) {
        this.stack = new ItemStack(provider);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, this.stack);
        this.background = helper.createDrawable(texture, 0, 0, 146, 49);
        this.progress = helper.drawableBuilder(texture, 0, 50, 129, 5).buildAnimated(150, IDrawableAnimated.StartDirection.LEFT, false);
    }


    public GreenhouseRecipeCategory(IGuiHelper helper, ItemStack stack, IDrawable background, IDrawable progress, IDrawable charge) {
        this.stack = stack;
        this.background = background;
        this.progress = progress;
        this.charge = charge;
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, stack);
    }

    @Override
    public @NotNull RecipeType<GreenhouseRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("autobotany.screen.title.greenhouse");
    }

    public @NotNull IDrawable getBackground() {
        return this.background;
    }


    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder layout, GreenhouseRecipe recipe, @NotNull IFocusGroup iFocusGroup) {
        var ingrCons = recipe.getConsumableInput();
        var ingrNonCOns = recipe.getNonConsumableInput();
        layout.addSlot(RecipeIngredientRole.INPUT, 64, 9).addIngredients(ingrCons);
        if (ingrNonCOns != null) {
            layout.addSlot(RecipeIngredientRole.INPUT, 44, 9).addIngredients(ingrNonCOns);
        }
    }

    public void draw(GreenhouseRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull PoseStack stack, double mouseX, double mouseY) {
        this.progress.draw(stack, 9, 40);
        Font font = Minecraft.getInstance().font;
        var energyPerTick = Component.literal(recipe.getEnergyPerTick() + " EU/t");
        font.draw(stack, energyPerTick, 84, 9,4210752);
        var ticks = Component.translatable("autobotany.screen.title.ticks", recipe.getTicks());
        font.draw(stack, ticks, 84, 19,4210752);
        if (mouseX >= 9 && mouseX <= 138 && mouseY >= 40 && mouseY <= 45) {
            Minecraft.getInstance().screen.renderTooltip(
                    stack,
                    Component.translatable("autobotany.tooltip.mana", recipe.getManaOutput()),
                    (int) mouseX,
                    (int) mouseY
            );
        }
    }

}
