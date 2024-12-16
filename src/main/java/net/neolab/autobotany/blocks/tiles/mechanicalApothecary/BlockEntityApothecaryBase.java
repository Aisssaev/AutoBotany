package net.neolab.autobotany.blocks.tiles.mechanicalApothecary;

import net.neolab.autobotany.blocks.pattern.BlockEntityApothecaryPattern;
import net.neolab.autobotany.config.LibXClientConfig.RenderingVisualContent.ApothecarySettings;
import net.neolab.autobotany.config.LibXServerConfig.ApothecarySettings.baseApothecary;
import net.neolab.autobotany.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityApothecaryBase extends BlockEntityApothecaryPattern {

    private static final int SEEDS_SLOT = 0;
    private static final int FIRST_INPUT_SLOT = 1;
    private static final int LAST_INPUT_SLOT = 9;
    private static final int FIRST_OUTPUT_SLOT = 10;
    private static final int LAST_OUTPUT_SLOT = 18;

    public BlockEntityApothecaryBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, new int[] {
                SEEDS_SLOT, FIRST_INPUT_SLOT, LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
        }, baseApothecary.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalApothecaryRender", Boolean.toString(ApothecarySettings.apothecaryBase))
                        .addConfig("craftTime", Integer.toString(baseApothecary.craftTime))
                        .addConfig("fluidStorage", Integer.toString(baseApothecary.fluidStorage)));
    }
}
