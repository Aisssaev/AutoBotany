package net.neolab.autobotany.blocks.blockMachines;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.neolab.autobotany.ModBlocks;
import net.neolab.autobotany.blocks.base.BotanicalBlock;
import net.neolab.autobotany.blocks.containers.ContainerElectricGreenhouse;
import net.neolab.autobotany.blocks.screens.ScreenElectricGreenhouse;
import net.neolab.autobotany.blocks.tesr.RenderElectricGreenhouse;
import net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase;
import net.neolab.autobotany.blocks.pattern.BlockEntityElectricGreenhousePattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.base.tile.MenuBlockBE;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;
import org.moddingx.libx.render.ItemStackRenderer;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class BlockElectricGreenhouse extends MenuBlockBE<BlockEntityElectricGreenhouseBase, ContainerElectricGreenhouse> {
    private static final VoxelShape COLLISION_SHAPE;
    //public static final VoxelShape SHAPE;
    public static final RotationShape SHAPE;

    public BlockElectricGreenhouse(ModX mod, Class<BlockEntityElectricGreenhouseBase> teClass, MenuType<ContainerElectricGreenhouse> menu) {
        super(mod, teClass, menu, Properties.of(Material.STONE).strength(2.0F, 10.0F).dynamicShape().noOcclusion().requiresCorrectToolForDrops(), new Item.Properties());
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        ItemStackRenderer.addRenderBlock(this.getBlockEntityType(), true);
        MenuScreens.register(ModBlocks.electricGreenhouse.menu, ScreenElectricGreenhouse::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderElectricGreenhouse();
        });
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.electricGreenhouse, RenderType.cutout());
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeItemClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ItemStackRenderer.createProperties());
    }

    public int getLightBlock(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return 0;
    }

    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return true;
    }

    @Nonnull
    public VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BlockStateProperties.HORIZONTAL_FACING});
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

        @Nonnull
    public VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
    }

    protected boolean shouldDropInventory(Level level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§6[!] §aБлок ломается киркой"));
        tooltip.add(Component.literal("§6[!] §aГенерирует огромное количество маны").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, getter, tooltip, flag);
    }

    static {
        COLLISION_SHAPE = Shapes.joinUnoptimized(box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), box(5.0, 2.0, 5.0, 11.0, 3.0, 11.0), BooleanOp.OR);
//        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(0, 0, 0, 16, 1, 0), box(0, 0, 0, 16, 1, 0), box(0, 0, 0, 16, 1, 0), box(0, 0, 0, 16, 1, 0), box(0, 0, 0, 16, 16, 0), box(0, 0, 0, 16, 16, 0)}));
        SHAPE = new RotationShape(Shapes.or(box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),

                // Столбы на углах
                box(0.0, 1.0, 0.0, 1.0, 15.0, 1.0),
                box(15.0, 1.0, 0.0, 16.0, 15.0, 1.0),
                box(0.0, 1.0, 15.0, 1.0, 15.0, 16.0),
                box(15.0, 1.0, 15.0, 16.0, 15.0, 16.0),

                // Верхние части
                box(0.0, 15.0, 0.0, 1.0, 16.0, 16.0),
                box(0.0, 15.0, 0.0, 16.0, 16.0, 1.0),
                box(15.0, 15.0, 0.0, 16.0, 16.0, 16.0),
                box(0.0, 15.0, 15.0, 16.0, 16.0, 16.0),

                // Центральная пластина
                box(2.0, 1.0, 2.0, 14.0, 1.1, 14.0)));
        //SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE), );
    }
}
