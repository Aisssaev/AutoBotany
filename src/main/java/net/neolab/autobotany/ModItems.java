package net.neolab.autobotany;

import net.minecraft.network.chat.Component;
import net.neolab.autobotany.Items.ItemBaseAdvanced;
import net.neolab.autobotany.Items.spark.ItemCrimsonManaSpark;
import net.neolab.autobotany.Items.spark.ItemMalachiteManaSpark;
import net.neolab.autobotany.Items.spark.ItemSaffronManaSpark;
import net.neolab.autobotany.Items.spark.ItemShadowManaSpark;
import net.minecraft.world.item.Item;
import net.neolab.autobotany.Items.upgrades.greenhouse.*;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.ItemBase;

import java.util.List;


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

    public static final Item moduleManaInfinity;
    public static final Item moduleLivingRockInfinity;
    public static final Item moduleWaterInfinity;
    public static final Item moduleSeedInfinity;
    public static final Item moduleSpeed;
    public static final Item moduleStoneInfinity;
    public static final Item modulePetal;
    public static final Item modulePetalBlock;

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

    public static final Item compressedManasteelAlloyPlate;
    public static final Item compressedElementiumAlloyPlate;
    public static final Item compressedTerrasteelAlloyPlate;



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

        compressedManasteelAlloyPlate = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        compressedElementiumAlloyPlate = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        compressedTerrasteelAlloyPlate = new ItemBase(AutoBotany.getInstance(), new Item.Properties());

        moduleManaInfinity = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aМожет быть установлен в любой механизм AutoBotany где используется мана. Бесконечно генерирует ману внутри механизма.")));
        moduleLivingRockInfinity = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aИспользуется в механическом руническом алтаре. Бесконечно добавляет жизнекамень в соответствующий слот")));
        moduleWaterInfinity = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aИспользуется в механическом аптекаре. Генерирует воду в механизме. ")));
        moduleSeedInfinity = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aИспользуется в механическом аптекаре. Бесконечно добавляет семена в соответствующий слот.")));
        moduleSpeed = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        moduleStoneInfinity = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aИспользуется в механическом Рудноцвете. Бесконечно генерирует камень в соответствующий слот,  что исключает необходимость поставлять его извне. ")));
        modulePetal = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aИспользуется в механизме \"Измученный амарант\". Производит вместо мистических цветов их лепестки.")));
        modulePetalBlock = new ItemBaseAdvanced(AutoBotany.getInstance(), new Item.Properties(), List.of(Component.literal("§6[!] §aИспользуется в механизме \"Измученный амарант\". Производит вместо мистических цветов блок его лепестков.")));

        moduleEfficiency = new EfficiencyUpgradeItem(AutoBotany.getInstance(), new Item.Properties());

        moduleSpeed1 = new BaseSpeedUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 0.9, 1000);
        moduleSpeed2 = new BaseSpeedUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 0.7, 3000);
        moduleSpeed3 = new BaseSpeedUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 0.5, 5000);

        moduleManaStorage1 = new BaseManaStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 10000000);
        moduleManaStorage2 = new BaseManaStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 100000000);
        moduleManaStorage3 = new BaseManaStorageUpgradeItem(AutoBotany.getInstance(), new Item.Properties(), 1000000000);

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