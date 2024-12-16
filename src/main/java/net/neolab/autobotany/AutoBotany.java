package net.neolab.autobotany;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.neolab.autobotany.blocks.blockMachines.BlockElectricGreenhouse;
import net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase;
import net.neolab.autobotany.crafting.GreenhouseRecipe;
import net.neolab.autobotany.data.recipes.ModRecipes;
import org.moddingx.libx.mod.ModXRegistration;
import org.moddingx.libx.registration.RegistrationBuilder;


import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

@Mod(AutoBotany.MOD_ID)
public final class AutoBotany extends ModXRegistration {
    private static AutoBotany instance;
    public static final String MOD_ID = "autobotany";

    public AutoBotany() {
        super(new CreativeModeTab(MOD_ID) {
            @Nonnull
            public ItemStack makeIcon() {
                return new ItemStack(ModBlocks.shadowDragonstoneBlock);
            }
        });
        IEventBus modEvents = FMLJavaModLoadingContext.get().getModEventBus();
        ModRecipes.SERIALIZERS.register(modEvents);
        instance = this;
        ModEntities.registerEntities(bind(ForgeRegistries.ENTITY_TYPES));

    }

    private static <T> BiConsumer<T, ResourceLocation> bind(IForgeRegistry<? super T> registry) {
        return (t, id) -> registry.register(id, t);
    }


    @Nonnull
    public static AutoBotany getInstance() { return instance; }

    @Override
    protected void initRegistration(RegistrationBuilder registrationBuilder) {registrationBuilder.enableRegistryTracking();}

    @Override
    protected void setup(FMLCommonSetupEvent fmlCommonSetupEvent) {}

    @Override
    protected void clientSetup(FMLClientSetupEvent fmlClientSetupEvent) {}

}
