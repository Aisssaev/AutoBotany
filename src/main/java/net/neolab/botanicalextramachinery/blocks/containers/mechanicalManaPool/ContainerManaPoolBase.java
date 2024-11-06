package net.neolab.botanicalextramachinery.blocks.containers.mechanicalManaPool;


import net.neolab.botanicalextramachinery.blocks.base.BotanicalTile;
import net.neolab.botanicalextramachinery.helper.UnrestrictedOutputSlot;
import net.neolab.botanicalextramachinery.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerManaPoolBase extends BlockEntityMenu<BlockEntityManaPoolBase> {
    public ContainerManaPoolBase(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 4, 6);
        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 89, 60));

        int index = this.addSlotBox(inventory, 1, 23, 39, 3, 18, 1, 18);
        this.addSlotBox(inventory, index, 118, 39, 2, 18, 1, 18, UnrestrictedOutputSlot::new);

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
