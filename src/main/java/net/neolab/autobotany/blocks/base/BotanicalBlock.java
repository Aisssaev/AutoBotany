package net.neolab.autobotany.blocks.base;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.moddingx.libx.base.tile.MenuBlockBE;
import org.moddingx.libx.menu.BlockEntityMenu;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;
import org.moddingx.libx.render.ItemStackRenderer;

public abstract class BotanicalBlock<T extends BotanicalTile, C extends BlockEntityMenu<T>> extends MenuBlockBE<T, C> {
    public static final VoxelShape FRAME_SHAPE = Shapes.or(box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0), new VoxelShape[]{box(0.0, 0.0, 0.0, 1.0, 16.0, 1.0), box(15.0, 0.0, 0.0, 16.0, 16.0, 1.0), box(0.0, 0.0, 15.0, 1.0, 16.0, 16.0), box(15.0, 0.0, 15.0, 16.0, 16.0, 16.0), box(0.0, 15.0, 0.0, 1.0, 16.0, 16.0), box(0.0, 15.0, 0.0, 16.0, 16.0, 1.0), box(15.0, 15.0, 0.0, 16.0, 16.0, 16.0), box(0.0, 15.0, 15.0, 16.0, 16.0, 16.0)});
    public final boolean fullCube;
    public final boolean specialRender;

    public BotanicalBlock(ModX mod, Class<T> teClass, MenuType<C> menu, boolean fullCube, boolean specialRender) {
        super(mod, teClass, menu, fullCube ? Properties.of(Material.STONE).strength(2.0F, 10.0F) : Properties.of(Material.STONE).strength(2.0F, 10.0F).dynamicShape().noOcclusion(), new Item.Properties());
        this.fullCube = fullCube;
        this.specialRender = specialRender;
    }

    public void registerClient(SetupContext ctx) {
        if (this.specialRender) {
            ItemStackRenderer.addRenderBlock(this.getBlockEntityType(), true);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void initializeItemClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ItemStackRenderer.createProperties());
    }

    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        BotanicalTile blockEntity = (BotanicalTile)level.getBlockEntity(pos);
        if (blockEntity == null) {
            return null;
        } else {
            CompoundTag tag = blockEntity.serializeNBT();
            ItemStack stack = new ItemStack(this);
            stack.setTag(tag);
            return stack;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BlockStateProperties.HORIZONTAL_FACING});
    }

    public boolean hasAnalogOutputSignal(@Nonnull BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos) {
        return ((BotanicalTile)this.getBlockEntity(level, pos)).getComparatorOutput();
    }

    public int getLightBlock(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return !this.fullCube ? 0 : super.getLightBlock(state, level, pos);
    }

    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return !this.fullCube;
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return !this.fullCube;
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return !this.fullCube ? FRAME_SHAPE : super.getShape(state, level, pos, context);
    }

    protected boolean shouldDropInventory(Level level, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable BlockGetter getter, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§6[!] §aБлок ломается киркой"));
    }
}
