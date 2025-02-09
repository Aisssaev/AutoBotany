package net.neolab.autobotany.data;

import net.minecraft.data.DataGenerator;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.recipe.RecipeProviderBase;
import org.moddingx.libx.datagen.provider.recipe.crafting.CompressionExtension;
import org.moddingx.libx.datagen.provider.recipe.crafting.CraftingExtension;
import org.moddingx.libx.mod.ModX;

@Datagen
public class Recipes extends RecipeProviderBase implements CraftingExtension, CompressionExtension {
    public Recipes(ModX mod, DataGenerator generator) {super(mod, generator);}

    @Override
    protected void setup() {
//        this.compress(ModItems.malachiteDragonstone, ModBlocks.malachiteDragonstoneBlock);
//        this.compress(ModItems.saffronDragonstone, ModBlocks.saffronDragonstoneBlock);
//        this.compress(ModItems.shadowDragonstone, ModBlocks.shadowDragonstoneBlock);
//        this.compress(ModItems.crimsonDragonstone, ModBlocks.crimsonDragonstoneBlock);
//
//        this.compress(ModItems.malachiteIngot, ModBlocks.malachiteIngotBlock);
//        this.compress(ModItems.saffronIngot, ModBlocks.saffronIngotBlock);
//        this.compress(ModItems.shadowIngot, ModBlocks.shadowIngotBlock);
//        this.compress(ModItems.crimsonIngot, ModBlocks.crimsonIngotBlock);
//
//        this.machine(ModBlocks.baseIndustrialAgglomerationFactory, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.industrialAgglomerationFactory, ModBlocks.malachiteDragonstoneBlock);
//        this.machine(ModBlocks.baseManaPool, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalManaPool, ModBlocks.malachiteDragonstoneBlock);
//        this.machine(ModBlocks.baseRunicAltar, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalRunicAltar, ModBlocks.malachiteDragonstoneBlock);
//        this.machine(ModBlocks.baseDaisy, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalDaisy, ModBlocks.malachiteDragonstoneBlock);
//        this.machine(ModBlocks.baseApothecary, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.mechanicalApothecary, ModBlocks.malachiteDragonstoneBlock);
//        this.machine(ModBlocks.baseAlfheimMarket, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, de.melanx.botanicalmachinery.ModBlocks.alfheimMarket, ModBlocks.malachiteDragonstoneBlock);
//        this.machine(ModBlocks.baseOrechid, BotaniaItems.manaRingGreater, ModItems.malachiteIngot, BotaniaFlowerBlocks.orechid, ModBlocks.malachiteDragonstoneBlock);
//
//        this.machine(ModBlocks.upgradedIndustrialAgglomerationFactory, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseIndustrialAgglomerationFactory, ModBlocks.saffronDragonstoneBlock);
//        this.machine(ModBlocks.upgradedManaPool, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseManaPool, ModBlocks.saffronDragonstoneBlock);
//        this.machine(ModBlocks.upgradedRunicAltar, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseRunicAltar, ModBlocks.saffronDragonstoneBlock);
//        this.machine(ModBlocks.upgradedDaisy, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseDaisy, ModBlocks.saffronDragonstoneBlock);
//        this.machine(ModBlocks.upgradedApothecary, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseApothecary, ModBlocks.saffronDragonstoneBlock);
//        this.machine(ModBlocks.upgradedAlfheimMarket, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseAlfheimMarket, ModBlocks.saffronDragonstoneBlock);
//        this.machine(ModBlocks.upgradedOrechid, BotaniaBlocks.terrasteelBlock, ModItems.saffronIngot, ModBlocks.baseOrechid, ModBlocks.saffronDragonstoneBlock);
//
//        this.machine(ModBlocks.advancedIndustrialAgglomerationFactory, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedIndustrialAgglomerationFactory, ModBlocks.shadowDragonstoneBlock);
//        this.machine(ModBlocks.advancedManaPool, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedManaPool, ModBlocks.shadowDragonstoneBlock);
//        this.machine(ModBlocks.advancedRunicAltar, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedRunicAltar, ModBlocks.shadowDragonstoneBlock);
//        this.machine(ModBlocks.advancedDaisy, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedDaisy, ModBlocks.shadowDragonstoneBlock);
//        this.machine(ModBlocks.advancedApothecary, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedApothecary, ModBlocks.shadowDragonstoneBlock);
//        this.machine(ModBlocks.advancedAlfheimMarket, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedAlfheimMarket, ModBlocks.shadowDragonstoneBlock);
//        this.machine(ModBlocks.advancedOrechid, ModBlocks.malachiteIngotBlock, ModItems.shadowIngot, ModBlocks.upgradedOrechid, ModBlocks.shadowDragonstoneBlock);
//
//        this.machine(ModBlocks.ultimateIndustrialAgglomerationFactory, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedIndustrialAgglomerationFactory, ModBlocks.crimsonDragonstoneBlock);
//        this.machine(ModBlocks.ultimateManaPool, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedManaPool, ModBlocks.crimsonDragonstoneBlock);
//        this.machine(ModBlocks.ultimateRunicAltar, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedRunicAltar, ModBlocks.crimsonDragonstoneBlock);
//        this.machine(ModBlocks.ultimateDaisy, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedDaisy, ModBlocks.crimsonDragonstoneBlock);
//        this.machine(ModBlocks.ultimateApothecary, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedApothecary, ModBlocks.crimsonDragonstoneBlock);
//        this.machine(ModBlocks.ultimateAlfheimMarket, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedAlfheimMarket, ModBlocks.crimsonDragonstoneBlock);
//        this.machine(ModBlocks.ultimateOrechid, ModBlocks.saffronIngotBlock, ModItems.crimsonIngot, ModBlocks.advancedOrechid, ModBlocks.crimsonDragonstoneBlock);
//
//        this.upgrade_2(ModItems.catalystManaInfinity, ModBlocks.crimsonDragonstoneBlock, ModBlocks.ultimateManaPool);
//        this.upgrade(ModItems.catalystSpeed, ModBlocks.upgradedIndustrialAgglomerationFactory);
//        this.upgrade(ModItems.catalystSeedInfinity, ModBlocks.ultimateApothecary);
//        this.upgrade(ModItems.catalystLivingRockInfinity, ModBlocks.upgradedRunicAltar);
//        this.upgrade(ModItems.catalystWaterInfinity, ModBlocks.advancedApothecary);
//        this.upgrade(ModItems.catalystStoneInfinity, ModItems.catalystLivingRockInfinity);
//    }
//
//    private void machine(Object output, Object special1, Object special2, Object special3, Object special4) {
//        this.shaped(output, "aba", "bcb", "ddd", 'a', special1, 'b', special2, 'c', special3, 'd', special4);
//    }
//
//    private void upgrade(Object output, Object special1) {
//        this.shaped(output, "abc", "bdb", "cba", 'a', ModBlocks.saffronDragonstoneBlock, 'b', ModBlocks.shadowDragonstoneBlock, 'c', ModBlocks.crimsonDragonstoneBlock, 'd', special1);
//    }
//
//    private void upgrade_2(Object output, Object special1, Object special2) {
//        this.shaped(output, "aaa", "aba", "aaa", 'a', special1, 'b', special2);
//    }
    }

}
