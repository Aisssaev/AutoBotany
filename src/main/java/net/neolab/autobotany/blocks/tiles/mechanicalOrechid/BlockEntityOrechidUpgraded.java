package net.neolab.autobotany.blocks.tiles.mechanicalOrechid;

import net.neolab.autobotany.blocks.pattern.BlockEntityOrechidPattern;
import net.neolab.autobotany.config.LibXServerConfig.OrechidSettings.upgradedOrechid;
import net.neolab.autobotany.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityOrechidUpgraded extends BlockEntityOrechidPattern {

    public static final int ORE_SLOT_1 = 0;
    public static final int ORE_SLOT_2 = 1;
    public static final int ORE_SLOT_3 = 2;
    public static final int ORE_SLOT_4 = 3;
    public static final int ORE_SLOT_5 = 4;

    public static final int FIRST_INPUT_SLOT = 5;
    public static final int LAST_INPUT_SLOT = 13;
    public static final int FIRST_OUTPUT_SLOT = 14;
    public static final int LAST_OUTPUT_SLOT = 22;

    public BlockEntityOrechidUpgraded(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state,
                upgradedOrechid.manaStorage,
                new int[] {
                        FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT,
                        ORE_SLOT_1, ORE_SLOT_2, ORE_SLOT_3, ORE_SLOT_4, ORE_SLOT_5
                }, null, new SettingPattern().addConfig("cooldown", Integer.toString(upgradedOrechid.cooldown))
                        .addConfig("countCraft", Integer.toString(upgradedOrechid.countCraft)));
    }
}
