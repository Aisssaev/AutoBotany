package net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket;

import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerAlfheimMarketBase extends BlockEntityMenu<BlockEntityAlfheimMarketBase> {
    public ContainerAlfheimMarketBase(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 3, 6);
        IItemHandlerModifiable inventory = ((BlockEntityAlfheimMarketBase)this.blockEntity).getInventory();

        int index = this.addSlotBox(inventory, 0, 59, 21, 1, 18, 3, 18);
        this.addSlotBox(inventory, index, 109, 21, 1, 18, 3, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 98);
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
