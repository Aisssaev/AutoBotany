package net.neolab.botanicalextramachinery.blocks.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neolab.botanicalextramachinery.blocks.pattern.BlockEntityElectricGreenhousePattern;
import net.neolab.botanicalextramachinery.config.LibXServerConfig;
import net.neolab.botanicalextramachinery.util.SettingPattern;

public class BlockEntityElectricGreenhouseBase extends BlockEntityElectricGreenhousePattern {
    public static final int FIRST_NONCONS_INPUT_SLOT = 0;
    public static final int LAST_NONCONS_INPUT_SLOT = 4;
    public static final int FIRST_CONS_INPUT_SLOT = 5;
    public static final int LAST_CONS_INPUT_SLOT = 13;

    public BlockEntityElectricGreenhouseBase(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state,
                LibXServerConfig.OrechidSettings.baseOrechid.manaStorage,
                new int[] {
                        FIRST_NONCONS_INPUT_SLOT, LAST_NONCONS_INPUT_SLOT, FIRST_CONS_INPUT_SLOT, LAST_CONS_INPUT_SLOT
                }, null, new SettingPattern(), 512, 1000000, 64);
    }
}
