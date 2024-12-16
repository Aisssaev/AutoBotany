package net.neolab.autobotany.blocks.containers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.neolab.autobotany.blocks.tiles.BlockEntityJadedAmaranthus;
import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerJadedAmaranthus  extends BlockEntityMenu<BlockEntityJadedAmaranthus> {
    public ContainerJadedAmaranthus(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 2, 18);
        var inventory = this.blockEntity.getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 62, 8));
        this.addSlot(new SlotItemHandler(inventory, 1, 98, 8));

        this.addSlotBox(inventory, 2, 17, 36, 8, 18, 2, 18, UnrestrictedOutputSlot::new);
        this.layoutPlayerInventorySlots(8, 99);
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
