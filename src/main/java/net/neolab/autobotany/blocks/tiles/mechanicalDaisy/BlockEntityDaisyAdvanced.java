package net.neolab.autobotany.blocks.tiles.mechanicalDaisy;

import net.neolab.autobotany.blocks.pattern.BlockEntityDaisyPattern;
import net.neolab.autobotany.config.LibXServerConfig.DaisySettings.advancedDaisy;
import net.neolab.autobotany.config.LibXClientConfig.RenderingVisualContent.DaisySettings;
import net.neolab.autobotany.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDaisyAdvanced extends BlockEntityDaisyPattern {

    public static final int SLOT_INVENTORY = 14;

    public BlockEntityDaisyAdvanced(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, SLOT_INVENTORY,
                new SettingPattern().addConfig("durationTime", Integer.toString(advancedDaisy.durationTime))
                        .addConfig("renderingDaisy", Boolean.toString(DaisySettings.daisyAdvanced))
                        .addConfig("sizeSlots", Integer.toString(advancedDaisy.sizeItemSlots)));
    }
}
