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
import net.neolab.autobotany.blocks.tiles.BlockEntityPrimitiveGreenhouse;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

import javax.annotation.Nonnull;

public class ContainerPrimitiveGreenhouse extends BlockEntityMenu<BlockEntityPrimitiveGreenhouse> {
    public ContainerPrimitiveGreenhouse(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, -1, 2);
        var inventory = ((ElectricBotanicalTile) this.blockEntity).getInventory();
        this.addSlot(new SlotItemHandler(inventory, 0, 50, 26));
        this.addSlot(new SlotItemHandler(inventory, 1, 115, 26));
        this.layoutPlayerInventorySlots(9, 99);
    }

    @Nonnull
    public ItemStack quickMoveStack(@Nonnull Player player, int index) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(index);
        var size = 2;
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
