package net.neolab.autobotany.data;

import net.neolab.autobotany.AutoBotany;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.neolab.autobotany.ModItems;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.ItemModelProviderBase;

@Datagen
public class ItemModels extends ItemModelProviderBase {
    public ItemModels(DataGenerator gen, ExistingFileHelper helper) {
        super(AutoBotany.getInstance(), gen, helper);
    }

    protected void setup() {
        this.basicItem(ModItems.runeEnergy);
        this.basicItem(ModItems.runeTp);
//        this.basicItem(ModItems.catalystPetal);
//        this.basicItem(ModItems.catalystPetalBlock);
    }
}