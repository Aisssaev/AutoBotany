package net.neolab.botanicalextramachinery.blocks.tiles.mechanicalManaPool;


import net.neolab.botanicalextramachinery.blocks.pattern.BlockEntityManaPoolPattern;
import net.neolab.botanicalextramachinery.config.LibXClientConfig.RenderingVisualContent.ManaPoolSettings;
import net.neolab.botanicalextramachinery.config.LibXServerConfig.ManaPoolSettings.upgradedManaPool;
import net.neolab.botanicalextramachinery.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

public class BlockEntityManaPoolUpgraded extends BlockEntityManaPoolPattern {

    private static final int CATALYSTS_SLOT = 0;
    private static final int FIRST_INPUT_SLOT = 1;
    private static final int LAST_INPUT_SLOT = 6;
    private static final int FIRST_OUTPUT_SLOT = 7;
    private static final int LAST_OUTPUT_SLOT = 10;


    public BlockEntityManaPoolUpgraded(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, BotaniaRecipeTypes.MANA_INFUSION_TYPE, pos, state, upgradedManaPool.manaStorage,
                new int[] {
                        CATALYSTS_SLOT, FIRST_INPUT_SLOT,
                        LAST_INPUT_SLOT, FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT
                },
                false, upgradedManaPool.countCraft,
                new SettingPattern()
                        .addConfig("mechanicalManaPoolRender", Boolean.toString(ManaPoolSettings.manaPoolUpgraded))
                        .addConfig("craftTime", Integer.toString(upgradedManaPool.craftTime)));
    }
}
