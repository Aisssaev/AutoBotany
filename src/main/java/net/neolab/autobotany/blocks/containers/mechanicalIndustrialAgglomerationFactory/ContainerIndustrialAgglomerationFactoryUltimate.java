package net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory;

import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUltimate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerIndustrialAgglomerationFactoryUltimate extends BlockEntityMenu<BlockEntityIndustrialAgglomerationFactoryUltimate> {
    public ContainerIndustrialAgglomerationFactoryUltimate(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 16, 28);
        IItemHandlerModifiable inventory = ((BlockEntityIndustrialAgglomerationFactoryUltimate)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 9, 93));
        this.addSlot(new SlotItemHandler(inventory, 1, 158, 93));

        int index = this.addSlotBox(inventory, 2, 30, 31, 7, 18, 2, 18);
        this.addSlotBox(inventory, index, 39, 85, 6, 18, 2, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 142);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (level != null && level.getBlockState(pos).isAir()) {
            player.closeContainer();
            return false;
        }
        return super.stillValid(player);
    }
}
