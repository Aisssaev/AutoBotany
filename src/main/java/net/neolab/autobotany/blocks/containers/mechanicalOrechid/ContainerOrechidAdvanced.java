package net.neolab.autobotany.blocks.containers.mechanicalOrechid;

import net.neolab.autobotany.blocks.base.BotanicalTile;
import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidAdvanced;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerOrechidAdvanced extends BlockEntityMenu<BlockEntityOrechidAdvanced> {
    public ContainerOrechidAdvanced(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 19, 29);

        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 14, 83));
        this.addSlot(new SlotItemHandler(inventory, 1, 154, 83));


        int index = this.addSlotBox(inventory, 2, 30, 22, 7, 18, 1, 18);

        int index_2 = this.addSlotBox(inventory, index, 48, 46, 5, 18, 2, 18);
        this.addSlotBox(inventory, index_2, 48, 102, 5, 18, 2, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 161);
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
