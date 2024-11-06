package net.neolab.botanicalextramachinery;

import net.neolab.botanicalextramachinery.Items.ItemCrimsonManaSpark;
import net.neolab.botanicalextramachinery.Items.ItemMalachiteManaSpark;
import net.neolab.botanicalextramachinery.Items.ItemSaffronManaSpark;
import net.neolab.botanicalextramachinery.Items.ItemShadowManaSpark;
import net.minecraft.world.item.Item;
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
    public static final Item catalystStoneInfinity;;

    public static final Item malachiteSpark;
    public static final Item saffronSpark;
    public static final Item shadowSpark;
    public static final Item crimsonSpark;

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


        catalystManaInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystLivingRockInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystWaterInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystSeedInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystSpeed = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
        catalystStoneInfinity = new ItemBase(AutoBotany.getInstance(), new Item.Properties());
    }

}