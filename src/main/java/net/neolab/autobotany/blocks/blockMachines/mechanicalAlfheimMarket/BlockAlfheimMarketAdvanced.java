package net.neolab.autobotany.blocks.blockMachines.mechanicalAlfheimMarket;


import net.neolab.autobotany.ModBlocks;
import net.neolab.autobotany.blocks.base.BotanicalBlock;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketAdvanced;
import net.neolab.autobotany.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketAdvanced;
import net.neolab.autobotany.blocks.tesr.mechanicalAlfheimMarket.RenderAlpheimMarketAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketAdvanced;
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

public class BlockAlfheimMarketAdvanced extends BotanicalBlock<BlockEntityAlfheimMarketAdvanced, ContainerAlfheimMarketAdvanced> {
    public static final RotationShape SHAPE;

    public BlockAlfheimMarketAdvanced(ModX mod, Class<BlockEntityAlfheimMarketAdvanced> teClass, MenuType<ContainerAlfheimMarketAdvanced> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.advancedAlfheimMarket.menu, ScreenAlfheimMarketAdvanced::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderAlpheimMarketAdvanced();
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
            if (blockEntity instanceof BlockEntityAlfheimMarketAdvanced){
                ((BlockEntityAlfheimMarketAdvanced) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(4.4, 1.0, 8.8, 11.6, 13.0, 11.2), box(0.0, 0.0, 8.8, 0.0, 0.0, 11.2), box(3.2, 0.0, 3.6, 6.8, 7.4, 7.2), box(8.8, 0.0, 3.6, 12.4, 7.4, 7.2)}));
    }
}
