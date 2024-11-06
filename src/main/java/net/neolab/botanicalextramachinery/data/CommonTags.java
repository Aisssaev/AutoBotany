package net.neolab.botanicalextramachinery.data;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.neolab.botanicalextramachinery.AutoBotany;
import net.neolab.botanicalextramachinery.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.CommonTagsProviderBase;
import org.moddingx.libx.mod.ModX;

@Datagen
public class CommonTags extends CommonTagsProviderBase {
    public static final TagKey<Item> MECHANICAL_APOTHECARY_CATALYSTS;
    public CommonTags(ModX mod, DataGenerator generator, ExistingFileHelper fileHelper) {
        super(mod, generator, fileHelper);
    }

    public void setup() {
        this.item(MECHANICAL_APOTHECARY_CATALYSTS).addTag(Tags.Items.SEEDS);


        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.malachiteDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.saffronDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.shadowDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.crimsonDragonstoneBlock);

//        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(de.melanx.botanicalmachinery.ModBlocks.manaEmeraldBlock);
//        this.block(BlockTags.NEEDS_IRON_TOOL).add(de.melanx.botanicalmachinery.ModBlocks.manaEmeraldBlock);

        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.malachiteIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.saffronIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.shadowIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.crimsonIngotBlock);

        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.malachiteDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.saffronDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.malachiteIngotBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.saffronIngotBlock);

        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.shadowDragonstoneBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.crimsonDragonstoneBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.shadowIngotBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.crimsonIngotBlock);
    }

    static {
        MECHANICAL_APOTHECARY_CATALYSTS = TagKey.create(Registry.ITEM_REGISTRY, AutoBotany.getInstance().resource("mechanical_apothecary_catalysts"));
    }
}
