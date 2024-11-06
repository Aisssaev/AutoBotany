package net.neolab.botanicalextramachinery.blocks.containers.mechanicalManaPool;

import net.neolab.botanicalextramachinery.blocks.base.BotanicalTile;
import net.neolab.botanicalextramachinery.helper.UnrestrictedOutputSlot;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerManaPoolUltimate extends BlockEntityMenu<BlockEntityManaPoolUltimate> {
    public ContainerManaPoolUltimate(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 14, 23);
        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 80, 66));
        this.addSlot(new SlotItemHandler(inventory, 1, 80, 19));

        int index = this.addSlotBox(inventory, 2, 14, 16, 3, 18, 4, 18);
        this.addSlotBox(inventory, index, 109, 25, 3, 18, 3, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(8, 107);
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
