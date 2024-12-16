package net.neolab.autobotany;

import net.neolab.autobotany.Items.spark.ItemCrimsonManaSpark;
import net.neolab.autobotany.Items.spark.ItemMalachiteManaSpark;
import net.neolab.autobotany.Items.spark.ItemSaffronManaSpark;
import net.neolab.autobotany.Items.spark.ItemShadowManaSpark;
import net.minecraft.world.item.Item;
import net.neolab.autobotany.Items.upgrades.greenhouse.*;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.ItemBase;


@RegisterClass(registry = "ITEMS", priority = 1)
public class ModItems {

    public static final Item malachiteDragonstone;
    public static final Item malachiteIngot;

    public static final Item saffronDragonstone;
    public static final Item saffronIngot;

    public static final Item shadowDragonstone;
    public static final Item shadowIngot;

    public static final Item crimsonDragonstone;
    public static final Item crimsonIngot;

    public static final Item catalystManaInfinity;
    public static final Item catalystLivingRockInfinity;
    public static final Item catalystWaterInfinity;
    public static final Item catalystSeedInfinity;
    public static final Item catalystSpeed;
    public static final Item catalystStoneInfinity;
    public static final Item catalystPetal;
    public static final Item catalystPetalBlock;

    public static final Item moduleEfficiency;

    public static final Item moduleSpeed1;
    public static final Item moduleSpeed2;
    public static final Item moduleSpeed3;

    public static final Item moduleManaStorage1;
    public static final Item moduleManaStorage2;
    public static final Item moduleManaStorage3;

    public static final Item moduleEnergyStorage1;
    public static final Item moduleEnergyStorage2;
    public static final Item moduleEnergyStorage3;

    public static final Item moduleRecipeMultiplier1;
    public static final Item moduleRecipeMultiplier2;
    public static final Item moduleRecipeMultiplier3;

    public static final Item malachiteSpark;
    public static final Item saffronSpark;
    public static final Item shadowSpark;
    public static final Item crimsonSpark;

    public static final Item manasteelAdvancedAlloy;
    public static final Item manasteelIngotAdvancedAlloy;
    public static final Item elementiumAdvancedAlloy;
    public static final Item elementiumIngotAdvancedAlloy;
    public static final Item terrasteelAdvancedAlloy;
    public static final Item terrasteelIngotAdvancedAlloy;

    public static final Item runeTp;
    public static final Item runeEnergy;

    public ModItems() {
    }

    static {
        malachiteSpark = new ItemMalachiteManaSpark(AutoBotany.getInstance(), new Item.Properties());
        saffronSpark = new ItemSaffronManaSpark(AutoBotany.getInstance(), new Item.Properties());
        shadowSpark = new ItemShadowManaSpark(AutoBotany.getInstance(), new Item.Properties());
        crimsonSpark = new ItemCrimsonManaSpark(AutoBotany.getInstance(), new Item.Properties());

        malachiteDragonstone = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        saffronDragonstone = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        shadowDragonstone = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        crimsonDragonstone = new ItemBase(AutoBotany.getInstance(), new Item.Properties());

        malachiteIngot = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        saffronIngot = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        shadowIngot = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        crimsonIngot = new ItemBase(AutoBotany.getInstance(), new Item.Properties());

        manasteelAdvancedAlloy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        manasteelIngotAdvancedAlloy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        elementiumAdvancedAlloy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        elementiumIngotAdvancedAlloy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        terrasteelAdvancedAlloy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        terrasteelIngotAdvancedAlloy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());

        catalystManaInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystLivingRockInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystWaterInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystSeedInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystSpeed = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystStoneInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystPetal = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystPetalBlock = new ItemBase(AutoBotany.getInstance(), new Item.Properties());

        moduleEfficiency = new EfficiencyUpgradeItem(AutoBotany.getInstance(), new Item.Properties());

        moduleSpeed1 = new BaseSpeedUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 0.9, 1000);
        moduleSpeed2 = new BaseSpeedUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 0.7, 3000);
        moduleSpeed3 = new BaseSpeedUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 0.5, 5000);

        moduleManaStorage1 = new BaseManaStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 1000000);
        moduleManaStorage2 = new BaseManaStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 10000000);
        moduleManaStorage3 = new BaseManaStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 100000000);

        moduleEnergyStorage1 = new BaseEnergyStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 1000000);
        moduleEnergyStorage2 = new BaseEnergyStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 10000000);
        moduleEnergyStorage3 = new BaseEnergyStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 100000000);

        moduleRecipeMultiplier1 = new BaseRecipeMultiplierUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 4);
        moduleRecipeMultiplier2 = new BaseRecipeMultiplierUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 16);
        moduleRecipeMultiplier3 = new BaseRecipeMultiplierUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 64);

        runeTp = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        runeEnergy = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
    }

}