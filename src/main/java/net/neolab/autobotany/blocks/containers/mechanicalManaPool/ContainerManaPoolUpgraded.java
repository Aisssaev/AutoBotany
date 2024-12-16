package net.neolab.autobotany.blocks.containers.mechanicalManaPool;

import net.neolab.autobotany.blocks.base.BotanicalTile;
import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUpgraded;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerManaPoolUpgraded extends BlockEntityMenu<BlockEntityManaPoolUpgraded> {
    public ContainerManaPoolUpgraded(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 7, 11);
        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 89, 60));

        int index = this.addSlotBox(inventory, 1, 23, 30, 3, 18, 2, 18);
        this.addSlotBox(inventory, index, 118, 30, 2, 18, 2, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(8, 100);
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
