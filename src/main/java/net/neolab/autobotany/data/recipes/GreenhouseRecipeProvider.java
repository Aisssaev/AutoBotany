package net.neolab.autobotany.data.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ic2.core.item.misc.TagItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.neolab.autobotany.crafting.GreenhouseRecipe;
import net.neolab.autobotany.data.CommonTags;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.mod.ModX;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.BotaniaFlowerBlocks;
import vazkii.botania.common.item.BotaniaItems;

@Datagen
public class GreenhouseRecipeProvider extends RecipeProviderBase {
    public GreenhouseRecipeProvider(ModX mod, DataGenerator generator) {
        super(mod, generator);
    }

    @Override
    protected void setup() {
        this.consumer().accept(new FinishedRecipe(id("endoflame"), Ingredient.of(Items.COAL), Ingredient.of(BotaniaFlowerBlocks.endoflame.asItem()), 4800, 10, 1600, true));
        this.consumer().accept(new FinishedRecipe(id("dandelifeon"), Ingredient.of(BotaniaBlocks.cellBlock), Ingredient.of(BotaniaFlowerBlocks.dandelifeon), 25, 5, 1, true));
        this.consumer().accept(new FinishedRecipe(id("entropynium"), Ingredient.of(Blocks.TNT), Ingredient.of(BotaniaFlowerBlocks.entropinnyum), 6400, 60, 80, true));
        this.consumer().accept(new FinishedRecipe(id("gourmaryllis"), Ingredient.of(CommonTags.FOOD_TAG), Ingredient.of(BotaniaFlowerBlocks.gourmaryllis), 4200, 30, 60, true));
        this.consumer().accept(new FinishedRecipe(id("kekimurus"), Ingredient.of(Items.CAKE), Ingredient.of(BotaniaFlowerBlocks.kekimurus), 16000, 40, 640, true));
        this.consumer().accept(new FinishedRecipe(id("munchdew"), Ingredient.of(CommonTags.LEAVES), Ingredient.of(BotaniaFlowerBlocks.munchdew), 160, 200, 1, true));
        this.consumer().accept(new FinishedRecipe(id("narslimmus"), Ingredient.of(Items.SLIME_BALL), Ingredient.of(BotaniaFlowerBlocks.narslimmus), 3600, 50, 100, true));
        this.consumer().accept(new FinishedRecipe(id("spectrolus"), Ingredient.of(CommonTags.WOOL), Ingredient.of(BotaniaFlowerBlocks.spectrolus), 1200, 150, 4, true));
        this.consumer().accept(new FinishedRecipe(id("wither_aconities"), Ingredient.of(Items.NETHER_STAR), Ingredient.of(getItem(new ResourceLocation("mythicbotany:wither_aconite"))), 1320000, 1000, 2400, true));
    }

    protected ResourceLocation id(String s) {
        return new ResourceLocation("greenhouse/" + s);
    }

    protected Item getItem(ResourceLocation id) {
        return ForgeRegistries.ITEMS.getValue(id);
    }

    protected static class FinishedRecipe implements net.minecraft.data.recipes.FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient consumableInput;
        private final Ingredient nonConsumableInput;
        private final int manaOutput;
        private final int energyPerTick;
        private final int ticks;
        private final boolean isConsumable;

        protected FinishedRecipe(ResourceLocation id, Ingredient consumableInput, Ingredient nonConsumableInput, int manaOutput, int energyPerTick, int ticks, boolean isConsumable) {
            this.id = id;
            this.consumableInput = consumableInput;
            this.nonConsumableInput = nonConsumableInput;
            this.manaOutput = manaOutput;
            this.energyPerTick = energyPerTick;
            this.ticks = ticks;
            this.isConsumable = isConsumable;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonElement nonConsumableIngredient = nonConsumableInput.toJson();
            JsonElement consumableIngredient = this.consumableInput.toJson();
            json.addProperty("ticks", this.ticks);
            json.addProperty("energy_per_tick", energyPerTick);
            json.addProperty("mana_output", manaOutput);
            json.add("non_consumable_input",nonConsumableIngredient);
            json.addProperty("isConsumable", isConsumable);
            if (isConsumable) {
                json.add("consumable_input",consumableIngredient);
            }
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return GreenhouseRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
