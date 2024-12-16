package net.neolab.autobotany.client;

import net.neolab.autobotany.AutoBotany;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class ModModels {

    public static final ModModels INSTANCE = new ModModels();

    public final Material malachiteSparkWorldIcon = mainAtlas("item/malachite_spark");
    public final Material saffronSparkWorldIcon = mainAtlas("item/saffron_spark");
    public final Material shadowSparkWorldIcon = mainAtlas("item/shadow_spark");
    public final Material crimsonSparkWorldIcon = mainAtlas("item/crimson_spark");

    private static Material mainAtlas(String name) {
        return new Material(InventoryMenu.BLOCK_ATLAS, res(name));
    }

    private static ResourceLocation res(String name){
        return new ResourceLocation(AutoBotany.MOD_ID, name);
    }

}
