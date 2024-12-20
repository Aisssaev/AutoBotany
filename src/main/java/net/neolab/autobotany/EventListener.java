package net.neolab.autobotany;

import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUpgraded;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AutoBotany.MOD_ID)
public class EventListener {
    public EventListener() {
    }

    @SubscribeEvent
    public static void resourcesReload(OnDatapackSyncEvent event) {
        BlockEntityManaPoolBase.invalidateCatalysts();
        BlockEntityManaPoolUpgraded.invalidateCatalysts();
        BlockEntityManaPoolAdvanced.invalidateCatalysts();
        BlockEntityManaPoolUltimate.invalidateCatalysts();
    }
}