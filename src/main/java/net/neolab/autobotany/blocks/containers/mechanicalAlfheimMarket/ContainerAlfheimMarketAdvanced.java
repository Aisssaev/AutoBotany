package net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket;


import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketAdvanced;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerAlfheimMarketAdvanced extends BlockEntityMenu<BlockEntityAlfheimMarketAdvanced> {
    public ContainerAlfheimMarketAdvanced(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 10, 19);
        IItemHandlerModifiable inventory = ((BlockEntityAlfheimMarketAdvanced)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 84, 87));

        int index = this.addSlotBox(inventory, 1, 23, 27, 3, 18, 3, 18);
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
