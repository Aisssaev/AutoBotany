package net.neolab.autobotany.blocks.blockMachines.mechanicalRunicAltar;

import net.neolab.autobotany.blocks.base.BotanicalBlock;
import net.neolab.autobotany.ModBlocks;
import net.neolab.autobotany.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarAdvanced;
import net.neolab.autobotany.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarAdvanced;
import net.neolab.autobotany.blocks.tesr.mechanicalRunicAltar.RendererRunicAltarAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarAdvanced;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;

import javax.annotation.Nonnull;

public class BlockRunicAltarAdvanced extends BotanicalBlock<BlockEntityRunicAltarAdvanced, ContainerRunicAltarAdvanced> {
    public static final RotationShape SHAPE;

    public BlockRunicAltarAdvanced(ModX mod, Class<BlockEntityRunicAltarAdvanced> teClass, MenuType<ContainerRunicAltarAdvanced> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.advancedRunicAltar.menu, ScreenRunicAltarAdvanced::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RendererRunicAltarAdvanced();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityRunicAltarAdvanced){
                ((BlockEntityRunicAltarAdvanced) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }


    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(2.0, 5.0, 2.0, 14.0, 9.0, 14.0), box(6.0, 3.0, 6.0, 10.0, 5.0, 10.0), box(4.0, 1.0, 4.0, 12.0, 3.0, 12.0)}));
    }
}
