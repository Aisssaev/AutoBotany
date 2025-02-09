package net.neolab.autobotany.data;

import net.neolab.autobotany.AutoBotany;
import net.neolab.autobotany.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.neolab.autobotany.core.TileTags;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.loot.BlockLootProviderBase;

@Datagen
public class LootTables extends BlockLootProviderBase {
    public LootTables(DataGenerator gen) {
        super(AutoBotany.getInstance(), gen);
    }

    protected void setup() {
        this.drops(ModBlocks.baseAlfheimMarket, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.upgradedAlfheimMarket, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.advancedAlfheimMarket, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.ultimateAlfheimMarket, this.copyNBT(TileTags.MANA));

        this.drops(ModBlocks.baseIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.upgradedIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.advancedIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.ultimateIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));

        this.drops(ModBlocks.baseApothecary, this.copyNBT(TileTags.FLUID));
        this.drops(ModBlocks.upgradedApothecary, this.copyNBT(TileTags.FLUID));
        this.drops(ModBlocks.advancedApothecary, this.copyNBT(TileTags.FLUID));
        this.drops(ModBlocks.ultimateApothecary, this.copyNBT(TileTags.FLUID));

        this.drops(ModBlocks.baseDaisy, this.copyNBT());
        this.drops(ModBlocks.upgradedDaisy, this.copyNBT());
        this.drops(ModBlocks.advancedDaisy, this.copyNBT());
        this.drops(ModBlocks.ultimateDaisy, this.copyNBT());

        this.drops(ModBlocks.baseManaPool, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.upgradedManaPool, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.advancedManaPool, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.ultimateManaPool, this.copyNBT(TileTags.MANA));

        this.drops(ModBlocks.baseRunicAltar, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.upgradedRunicAltar, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.advancedRunicAltar, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.ultimateRunicAltar, this.copyNBT(TileTags.MANA));

        this.drops(ModBlocks.baseOrechid, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.upgradedOrechid, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.advancedOrechid, this.copyNBT(TileTags.MANA));
        this.drops(ModBlocks.ultimateOrechid, this.copyNBT(TileTags.MANA));
    }
}