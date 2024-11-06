package net.neolab.botanicalextramachinery.blocks.containers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.neolab.botanicalextramachinery.blocks.base.ElectricBotanicalTile;
import net.neolab.botanicalextramachinery.blocks.tiles.BlockEntityElectricGreenhouseBase;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

import static net.neolab.botanicalextramachinery.blocks.tiles.BlockEntityElectricGreenhouseBase.*;

public class ContainerElectricGreenhouse extends BlockEntityMenu<BlockEntityElectricGreenhouseBase> {
    public ContainerElectricGreenhouse(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, -1, 14);
        var inventory = ((ElectricBotanicalTile) this.blockEntity).getInventory();
        var slotSize = 18;
        var j = 0;
        for (int i = FIRST_NONCONS_INPUT_SLOT; i <= LAST_NONCONS_INPUT_SLOT; i++) {
            this.addSlot(new SlotItemHandler(inventory, i, 48 + i * slotSize, 22));
        }
        for (int i = FIRST_CONS_INPUT_SLOT; i <= LAST_CONS_INPUT_SLOT; i++) {
            this.addSlot(new SlotItemHandler(inventory, i, 12 + j * slotSize, 46));
            j++;
        }

        this.layoutPlayerInventorySlots(12, 127);
    }

    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(index);
        var size = 14;
        if (slot.hasItem()) {
            var stack = slot.getItem();
            itemstack = stack.copy();

            if (index < size) {
                if (!this.moveItemStackTo(stack, size, 36 + size, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else {
                if (!this.moveItemStackTo(stack, 0, size, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
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
