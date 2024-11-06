package net.neolab.botanicalextramachinery.data.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.neolab.botanicalextramachinery.AutoBotany;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AutoBotany.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GreenhouseRecipe>> GREENHOUSE_RECIPE_SERIALIZER =
            SERIALIZERS.register("greenhouse", () -> GreenhouseRecipe.Serializer.INSTANCE);
    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}
