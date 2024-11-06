package net.neolab.botanicalextramachinery.blocks.blockMachines.mechanicalOrechid;

import net.neolab.botanicalextramachinery.ModBlocks;
import net.neolab.botanicalextramachinery.blocks.containers.mechanicalOrechid.ContainerOrechidBase;
import net.neolab.botanicalextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidBase;
import net.neolab.botanicalextramachinery.blocks.tesr.mechanicalOrechid.RenderOrechidBase;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalOrechid.BlockEntityOrechidBase;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.base.tile.MenuBlockBE;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;
import org.moddingx.libx.render.ItemStackRenderer;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BlockOrechidBase extends MenuBlockBE<BlockEntityOrechidBase, ContainerOrechidBase> {

    private static final VoxelShape COLLISION_SHAPE;
    public static final VoxelShape SHAPE;

    public BlockOrechidBase(ModX mod, Class<BlockEntityOrechidBase> teClass, MenuType<ContainerOrechidBase> menu) {
        super(mod, teClass, menu, Properties.of(Material.STONE).strength(2.0F, 10.0F).dynamicShape(), new Item.Properties());
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        ItemStackRenderer.addRenderBlock(this.getBlockEntityType(), true);
        MenuScreens.register(ModBlocks.baseOrechid.menu, ScreenOrechidBase::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderOrechidBase();
        });
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
        return COLLISION_SHAPE;
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Nonnull
    public VoxelShape getOcclusionShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return SHAPE;
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityOrechidBase){
                ((BlockEntityOrechidBase) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    protected boolean shouldDropInventory(Level level, BlockPos pos, BlockState state) {
        return false;
    }

    static {
        COLLISION_SHAPE = Shapes.joinUnoptimized(box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), box(5.0, 2.0, 5.0, 11.0, 3.0, 11.0), BooleanOp.OR);
        SHAPE = box(0.0, 0.0, 0.0, 16.0, 11.4, 16.0);
    }
}