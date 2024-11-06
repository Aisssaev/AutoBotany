package net.neolab.botanicalextramachinery.blocks.tiles.mechanicalDaisy;

import net.neolab.botanicalextramachinery.blocks.pattern.BlockEntityDaisyPattern;
import net.neolab.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.DaisySettings;
import net.neolab.botanicalextramachinery.config.LibXServerConfig.DaisySettings.baseDaisy;
import net.neolab.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDaisyBase extends BlockEntityDaisyPattern {

    public static final int SLOT_INVENTORY = 8;

    public BlockEntityDaisyBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, SLOT_INVENTORY,
                new SettingPattern().addConfig("durationTime", Integer.toString(baseDaisy.durationTime))
                        .addConfig("renderingDaisy", Boolean.toString(DaisySettings.daisyBase))
                        .addConfig("sizeSlots", Integer.toString(baseDaisy.sizeItemSlots)));
    }
}
