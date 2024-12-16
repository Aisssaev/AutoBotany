package net.neolab.autobotany.blocks.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neolab.autobotany.blocks.pattern.BlockEntityElectricGreenhousePattern;
import net.neolab.autobotany.config.LibXServerConfig;
import net.neolab.autobotany.util.SettingPattern;

public class BlockEntityElectricGreenhouseBase extends BlockEntityElectricGreenhousePattern {
    public static final int FIRST_NONCONS_INPUT_SLOT = 0;
    public static final int LAST_NONCONS_INPUT_SLOT = 5;
    public static final int FIRST_CONS_INPUT_SLOT = 6;
    public static final int LAST_CONS_INPUT_SLOT = 32;

    public BlockEntityElectricGreenhouseBase(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state,
                0,
                new int[] {
                        FIRST_NONCONS_INPUT_SLOT, LAST_NONCONS_INPUT_SLOT, FIRST_CONS_INPUT_SLOT, LAST_CONS_INPUT_SLOT
                }, 5, new SettingPattern(), 536870912, 0);
    }
}
