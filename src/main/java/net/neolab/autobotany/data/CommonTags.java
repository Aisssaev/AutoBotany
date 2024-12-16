package net.neolab.autobotany.data;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.neolab.autobotany.AutoBotany;
import net.neolab.autobotany.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.CommonTagsProviderBase;
import org.moddingx.libx.mod.ModX;

import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.item.Items.BEEF;

@Datagen
public class CommonTags extends CommonTagsProviderBase {
    public static final TagKey<Item> MECHANICAL_APOTHECARY_CATALYSTS;
    public static final TagKey<Item> FOOD_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(AutoBotany.MOD_ID, "food"));
    public static final TagKey<Item> LEAVES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("minecraft", "leaves"));
    public static final TagKey<Item> WOOL = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("minecraft", "wool"));
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

        this.item(FOOD_TAG).add(APPLE,
                BREAD,
                BAKED_POTATO,
                BEETROOT,
                CARROT,
                CHORUS_FRUIT,
                COOKIE,
                GOLDEN_APPLE,
                ENCHANTED_GOLDEN_APPLE,
                GOLDEN_CARROT,
                MELON_SLICE,
                PUMPKIN_PIE,
                SWEET_BERRIES,
                GLOW_BERRIES,
                COOKED_CHICKEN,
                COOKED_COD,
                COOKED_MUTTON,
                COOKED_PORKCHOP,
                COOKED_RABBIT,
                COOKED_SALMON,
                COOKED_BEEF,
                CHICKEN,
                COD,
                MUTTON,
                PORKCHOP,
                RABBIT,
                SALMON,
                BEEF);
    }

    static {
        MECHANICAL_APOTHECARY_CATALYSTS = TagKey.create(Registry.ITEM_REGISTRY, AutoBotany.getInstance().resource("mechanical_apothecary_catalysts"));
    }
}
