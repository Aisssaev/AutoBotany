package net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory;

import net.neolab.autobotany.helper.UnrestrictedOutputSlot;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerIndustrialAgglomerationFactoryBase extends BlockEntityMenu<BlockEntityIndustrialAgglomerationFactoryBase> {
    public ContainerIndustrialAgglomerationFactoryBase(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 3, 7);
        IItemHandlerModifiable inventory = ((BlockEntityIndustrialAgglomerationFactoryBase)this.blockEntity).getInventory();


        int index = this.addSlotBox(inventory, 0, 66, 31, 3, 18, 1, 18);
        this.addSlotBox(inventory, index, 57, 67, 4, 18, 1, 18, UnrestrictedOutputSlot::new);

        this.layoutPlayerInventorySlots(12, 110);
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
