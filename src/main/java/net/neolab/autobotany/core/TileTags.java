package net.neolab.autobotany.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neolab.autobotany.AutoBotany;

public class TileTags {
    public static final String INVENTORY = "inv";
    public static final String MANA = "mana";
    public static final String INPUT_KEY = "inputKey";
    public static final String OUTPUT_KEY = "outputKey";
    public static final String PROGRESS = "progress";
    public static final String CURRENT_INPUT = "currentInput";
    public static final String CURRENT_OUTPUT = "currentOutput";
    public static final String SLOT_1_LOCKED = "slot1Locked";
    public static final String SLOT_2_LOCKED = "slot2Locked";
    public static final String FLUID = "fluid";
    public static final String MAX_PROGRESS = "maxProgress";
    public static final String WORKING_TICKS = "workingTicks";
    public static final String SLOTS_USED = "slotsUsed";
    public static final String COOLDOWN = "cooldown";
    public static final TagKey<Item> FOOD_TAG = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(AutoBotany.MOD_ID, "food"));

    public TileTags() {
    }
}