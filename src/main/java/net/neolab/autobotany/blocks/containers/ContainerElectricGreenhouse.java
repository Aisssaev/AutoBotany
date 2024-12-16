package net.neolab.autobotany.blocks.containers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.SlotItemHandler;
import net.neolab.autobotany.blocks.base.ElectricBotanicalTile;
import net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

import static net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase.*;

public class ContainerElectricGreenhouse extends BlockEntityMenu<BlockEntityElectricGreenhouseBase> {
    public ContainerElectricGreenhouse(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, -1, 38);
        var inventory = ((ElectricBotanicalTile) this.blockEntity).getInventory();
        var slotSize = 18;
        var j = 0;
        var index = this.addSlotBox(inventory, 0, 62, 17, 6, 18, 1, 18);
        this.addSlotBox(inventory, index, 35, 42, 9, 18, 3, 18);
        this.addSlot(new SlotItemHandler(inventory, 33, 7, 24));
        this.addSlot(new SlotItemHandler(inventory, 34, 7, 68));
        this.addSlot(new SlotItemHandler(inventory, 35, 207, 24));
        this.addSlot(new SlotItemHandler(inventory, 36, 207, 68));
        this.addSlot(new SlotItemHandler(inventory, 37, 107, 106));
        this.layoutPlayerInventorySlots(35, 163);
    }

    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(index);
        var size = 38;
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
