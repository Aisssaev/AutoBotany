package net.neolab.autobotany.blocks.containers.mechanicalRunicAltar;

import net.neolab.autobotany.blocks.base.BotanicalTile;
import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUltimate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerRunicAltarUltimate extends BlockEntityMenu<BlockEntityRunicAltarUltimate> {
    public ContainerRunicAltarUltimate(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 21, 37);
        IItemHandlerModifiable inventory = ((BotanicalTile)this.blockEntity).getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 66, 97));
        this.addSlot(new SlotItemHandler(inventory, 1, 84, 97));
        this.addSlot(new SlotItemHandler(inventory, 2, 102, 97));
        this.addSlot(new SlotItemHandler(inventory, 3, 27, 97));
        this.addSlot(new SlotItemHandler(inventory, 4, 140, 97));

        int index = this.addSlotBox(inventory, 5, 11, 18, 4, 18, 4, 18);
        this.addSlotBox(inventory, index, 103, 18, 4, 18, 4, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 137);
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