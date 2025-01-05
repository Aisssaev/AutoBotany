package net.neolab.autobotany.crafting;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neolab.autobotany.AutoBotany;
import net.neolab.autobotany.blocks.tiles.BlockEntityPrimitiveGreenhouse;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.crafting.RecipeSerializerBase;

import static net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase.*;

public class PrimitiveGreenhouseRecipe implements net.neolab.autobotany.api.GreenhouseRecipe {
    private final ResourceLocation id;
    private final Ingredient consumableInput; // Потребляемый предмет
    private final Ingredient nonConsumableInput; // Непотребляемый предмет
    private final int manaOutput;
    private final int energyPerTick;
    private final int ticks;
    private final boolean isConsumable;

    public PrimitiveGreenhouseRecipe(ResourceLocation id, Ingredient consumableInput, Ingredient nonConsumableInput, int manaOutput, int energyPerTick, int ticks, boolean isConsumable) {
        this.id = id;
        this.consumableInput = consumableInput;
        this.nonConsumableInput = nonConsumableInput;
        this.manaOutput = manaOutput;
        this.energyPerTick = energyPerTick;
        this.ticks = ticks;
        this.isConsumable = isConsumable;
    }

    public Ingredient getConsumableInput() {
        return this.consumableInput;
    }

    public Ingredient getNonConsumableInput() {
        return this.nonConsumableInput;
    }

    public int getManaOutput() {
        return this.manaOutput;
    }

    public int getEnergyPerTick() {
        return this.energyPerTick;
    }

    public int getTicks() {
        return this.ticks;
    }


    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public boolean matches(@NotNull Container container, @NotNull Level level) {
        if (level.isClientSide) {
            return false;
        }
        var consumable = ItemStack.EMPTY;
        var nonConsumable = ItemStack.EMPTY;

        for (int i = BlockEntityPrimitiveGreenhouse.FIRST_NONCONS_INPUT_SLOT; i <= BlockEntityPrimitiveGreenhouse.LAST_NONCONS_INPUT_SLOT; i++) {
            nonConsumable = container.getItem(i);
            if (!nonConsumable.isEmpty()) {
                for (int j = BlockEntityPrimitiveGreenhouse.FIRST_CONS_INPUT_SLOT; j <= BlockEntityPrimitiveGreenhouse.LAST_CONS_INPUT_SLOT; j++) {
                    if (isConsumable) {
                        consumable = container.getItem(j);
                        if (!consumable.isEmpty()) {
                            var matches = consumableInput.test(consumable) && nonConsumableInput.test(nonConsumable);
                            if (matches) {
                                return true;
                            }
                        } else if (consumable.isEmpty()) {
                            if (nonConsumableInput.test(nonConsumable) && consumableInput.test(ItemStack.EMPTY)) {
                                return true;
                            }
                        }
                    } else {
                        if (nonConsumableInput.test(nonConsumable)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isConsumable() {
        return isConsumable;
    }

    public ItemStack assemble(@NotNull Container container) {
        return ItemStack.EMPTY;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Type implements RecipeType<PrimitiveGreenhouseRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "primitive_greenhouse";
        private Type() {

        }
    }

    public static class Serializer extends RecipeSerializerBase<PrimitiveGreenhouseRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(AutoBotany.MOD_ID, "primitive_greenhouse");
        public Serializer() {
        }

        @Override
        public PrimitiveGreenhouseRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            Ingredient consumableInput;
            var isConsumable = GsonHelper.getAsBoolean(json, "isConsumable");
            if (isConsumable) {
                var consumableJson = GsonHelper.getAsJsonObject(json, "consumable_input");
                consumableInput = Ingredient.fromJson(consumableJson);
            } else {
                consumableInput = Ingredient.EMPTY;
            }
            var nonConsumableJson = GsonHelper.getAsJsonObject(json, "non_consumable_input");
            var nonConsumableInput = Ingredient.fromJson(nonConsumableJson);
            var manaOutput = GsonHelper.getAsInt(json, "mana_output");
            var energyPerTick = GsonHelper.getAsInt(json, "energy_per_tick");
            var ticks = GsonHelper.getAsInt(json, "ticks");

            return new PrimitiveGreenhouseRecipe(recipeId, consumableInput, nonConsumableInput, manaOutput, energyPerTick, ticks, isConsumable);
        }

        @Override
        public PrimitiveGreenhouseRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf buffer) {
            var isConsumable = buffer.readBoolean();
            Ingredient consumableInput;
            if (isConsumable) {
                consumableInput = Ingredient.fromNetwork(buffer);
            } else {
                consumableInput = Ingredient.EMPTY;
            }
            var nonConsumableInput = Ingredient.fromNetwork(buffer);
            var manaOutput = buffer.readVarInt();
            var energyPerTick = buffer.readVarInt();
            var ticks = buffer.readVarInt();

            return new PrimitiveGreenhouseRecipe(recipeId, consumableInput, nonConsumableInput, manaOutput, energyPerTick, ticks, isConsumable);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull PrimitiveGreenhouseRecipe recipe) {
            buffer.writeBoolean(recipe.isConsumable);
            recipe.getConsumableInput().toNetwork(buffer);
            recipe.getNonConsumableInput().toNetwork(buffer);
            buffer.writeVarInt(recipe.getManaOutput());
            buffer.writeVarInt(recipe.getEnergyPerTick());
            buffer.writeVarInt(recipe.getTicks());
        }

        private boolean isFood(Ingredient ingredient) {
            for (var stack : ingredient.getItems()) {
                if (stack.isEdible()) {
                    return true;
                }
            }
            return false;
        }

    }

}
