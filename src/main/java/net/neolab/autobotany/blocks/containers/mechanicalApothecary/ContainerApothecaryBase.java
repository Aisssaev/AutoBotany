package net.neolab.autobotany.blocks.containers.mechanicalApothecary;

import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerApothecaryBase extends BlockEntityMenu<BlockEntityApothecaryBase> {
    public ContainerApothecaryBase(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 10, 19);
        IItemHandlerModifiable inventory = ((BlockEntityApothecaryBase)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 87));

        int index = this.addSlotBox(inventory, 1, 24, 27, 3, 18, 3, 18);
        this.addSlotBox(inventory, index, 109, 27, 3, 18, 3, 18, UnrestrictedOutputSlot::new);
        this.layoutPlayerInventorySlots(12, 127);
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
