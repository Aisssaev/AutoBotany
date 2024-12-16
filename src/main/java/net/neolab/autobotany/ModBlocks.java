package net.neolab.autobotany;

import net.neolab.autobotany.blocks.blockMachines.BlockElectricGreenhouse;
import net.neolab.autobotany.blocks.blockMachines.BlockJadedAmaranthus;
import net.neolab.autobotany.blocks.blockMachines.mechanicalAlfheimMarket.BlockAlfheimMarketAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalAlfheimMarket.BlockAlfheimMarketBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalAlfheimMarket.BlockAlfheimMarketUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalAlfheimMarket.BlockAlfheimMarketUpgraded;
import net.neolab.autobotany.blocks.blockMachines.mechanicalApothecary.BlockApothecaryAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalApothecary.BlockApothecaryBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalApothecary.BlockApothecaryUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalApothecary.BlockApothecaryUpgraded;
import net.neolab.autobotany.blocks.blockMachines.mechanicalDaisy.BlockDaisyAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalDaisy.BlockDaisyBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalDaisy.BlockDaisyUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalDaisy.BlockDaisyUpgraded;
import net.neolab.autobotany.blocks.blockMachines.mechanicalIndustrialAgglomerationFactory.BlockIndustrialAgglomerationFactoryAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalIndustrialAgglomerationFactory.BlockIndustrialAgglomerationFactoryBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalIndustrialAgglomerationFactory.BlockIndustrialAgglomerationFactoryUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalIndustrialAgglomerationFactory.BlockIndustrialAgglomerationFactoryUpgraded;
import net.neolab.autobotany.blocks.blockMachines.mechanicalManaPool.BlockManaPoolAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalManaPool.BlockManaPoolBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalManaPool.BlockManaPoolUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalManaPool.BlockManaPoolUpgraded;
import net.neolab.autobotany.blocks.blockMachines.mechanicalOrechid.BlockOrechidAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalOrechid.BlockOrechidBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalOrechid.BlockOrechidUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalOrechid.BlockOrechidUpgraded;
import net.neolab.autobotany.blocks.blockMachines.mechanicalRunicAltar.BlockRunicAltarAdvanced;
import net.neolab.autobotany.blocks.blockMachines.mechanicalRunicAltar.BlockRunicAltarBase;
import net.neolab.autobotany.blocks.blockMachines.mechanicalRunicAltar.BlockRunicAltarUltimate;
import net.neolab.autobotany.blocks.blockMachines.mechanicalRunicAltar.BlockRunicAltarUpgraded;
import net.neolab.autobotany.blocks.containers.ContainerElectricGreenhouse;
import net.neolab.autobotany.blocks.containers.ContainerJadedAmaranthus;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketBase;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketUpgraded;
import net.neolab.autobotany.blocks.containers.mechanicalApothecary.ContainerApothecaryAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalApothecary.ContainerApothecaryBase;
import net.neolab.autobotany.blocks.containers.mechanicalApothecary.ContainerApothecaryUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalApothecary.ContainerApothecaryUpgraded;
import net.neolab.autobotany.blocks.containers.mechanicalDaisy.ContainerDaisyAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalDaisy.ContainerDaisyBase;
import net.neolab.autobotany.blocks.containers.mechanicalDaisy.ContainerDaisyUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalDaisy.ContainerDaisyUpgraded;
import net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryBase;
import net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalIndustrialAgglomerationFactory.ContainerIndustrialAgglomerationFactoryUpgraded;
import net.neolab.autobotany.blocks.containers.mechanicalManaPool.ContainerManaPoolAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalManaPool.ContainerManaPoolBase;
import net.neolab.autobotany.blocks.containers.mechanicalManaPool.ContainerManaPoolUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalManaPool.ContainerManaPoolUpgraded;
import net.neolab.autobotany.blocks.containers.mechanicalOrechid.ContainerOrechidAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalOrechid.ContainerOrechidBase;
import net.neolab.autobotany.blocks.containers.mechanicalOrechid.ContainerOrechidUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalOrechid.ContainerOrechidUpgraded;
import net.neolab.autobotany.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarAdvanced;
import net.neolab.autobotany.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarBase;
import net.neolab.autobotany.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarUltimate;
import net.neolab.autobotany.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarUpgraded;
import net.neolab.autobotany.blocks.tiles.BlockEntityElectricGreenhouseBase;
import net.neolab.autobotany.blocks.tiles.BlockEntityJadedAmaranthus;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketBase;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryBase;
import net.neolab.autobotany.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalDaisy.BlockEntityDaisyAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalDaisy.BlockEntityDaisyBase;
import net.neolab.autobotany.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryBase;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolBase;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalManaPool.BlockEntityManaPoolUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidBase;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalOrechid.BlockEntityOrechidUpgraded;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarBase;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUltimate;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUpgraded;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.moddingx.libx.annotation.registration.RegisterClass;
import org.moddingx.libx.base.BlockBase;
import org.moddingx.libx.base.tile.MenuBlockBE;
import org.moddingx.libx.menu.BlockEntityMenu;

@RegisterClass(registry = "BLOCKS", priority = 1)
public class ModBlocks {
    public static final BlockBase malachiteDragonstoneBlock;
    public static final BlockBase malachiteIngotBlock;
    public static final BlockBase saffronDragonstoneBlock;
    public static final BlockBase saffronIngotBlock;
    public static final BlockBase shadowDragonstoneBlock;
    public static final BlockBase shadowIngotBlock;
    public static final BlockBase crimsonDragonstoneBlock;
    public static final BlockBase crimsonIngotBlock;

    public static final MenuBlockBE<BlockEntityManaPoolBase, ContainerManaPoolBase> baseManaPool;
    public static final MenuBlockBE<BlockEntityManaPoolUpgraded, ContainerManaPoolUpgraded> upgradedManaPool;
    public static final MenuBlockBE<BlockEntityManaPoolAdvanced, ContainerManaPoolAdvanced> advancedManaPool;
    public static final MenuBlockBE<BlockEntityManaPoolUltimate, ContainerManaPoolUltimate> ultimateManaPool;

    public static final MenuBlockBE<BlockEntityElectricGreenhouseBase, ContainerElectricGreenhouse> electricGreenhouse;
    public static final MenuBlockBE<BlockEntityJadedAmaranthus, ContainerJadedAmaranthus> jadedAmaranthus;

    public static final MenuBlockBE<BlockEntityRunicAltarBase, ContainerRunicAltarBase> baseRunicAltar;
    public static final MenuBlockBE<BlockEntityRunicAltarUpgraded, ContainerRunicAltarUpgraded> upgradedRunicAltar;
    public static final MenuBlockBE<BlockEntityRunicAltarAdvanced, ContainerRunicAltarAdvanced> advancedRunicAltar;
    public static final MenuBlockBE<BlockEntityRunicAltarUltimate, ContainerRunicAltarUltimate> ultimateRunicAltar;

    public static final MenuBlockBE<BlockEntityDaisyBase, ContainerDaisyBase> baseDaisy;
    public static final MenuBlockBE<BlockEntityDaisyUpgraded, ContainerDaisyUpgraded> upgradedDaisy;
    public static final MenuBlockBE<BlockEntityDaisyAdvanced, ContainerDaisyAdvanced> advancedDaisy;
    public static final MenuBlockBE<BlockEntityDaisyUltimate, ContainerDaisyUltimate> ultimateDaisy;

    public static final MenuBlockBE<BlockEntityApothecaryBase, ContainerApothecaryBase> baseApothecary;
    public static final MenuBlockBE<BlockEntityApothecaryUpgraded, ContainerApothecaryUpgraded> upgradedApothecary;
    public static final MenuBlockBE<BlockEntityApothecaryAdvanced, ContainerApothecaryAdvanced> advancedApothecary;
    public static final MenuBlockBE<BlockEntityApothecaryUltimate, ContainerApothecaryUltimate> ultimateApothecary;

    public static final MenuBlockBE<BlockEntityIndustrialAgglomerationFactoryBase, ContainerIndustrialAgglomerationFactoryBase> baseIndustrialAgglomerationFactory;
    public static final MenuBlockBE<BlockEntityIndustrialAgglomerationFactoryUpgraded, ContainerIndustrialAgglomerationFactoryUpgraded> upgradedIndustrialAgglomerationFactory;
    public static final MenuBlockBE<BlockEntityIndustrialAgglomerationFactoryAdvanced, ContainerIndustrialAgglomerationFactoryAdvanced> advancedIndustrialAgglomerationFactory;
    public static final MenuBlockBE<BlockEntityIndustrialAgglomerationFactoryUltimate, ContainerIndustrialAgglomerationFactoryUltimate> ultimateIndustrialAgglomerationFactory;

    public static final MenuBlockBE<BlockEntityAlfheimMarketBase, ContainerAlfheimMarketBase> baseAlfheimMarket;
    public static final MenuBlockBE<BlockEntityAlfheimMarketUpgraded, ContainerAlfheimMarketUpgraded> upgradedAlfheimMarket;
    public static final MenuBlockBE<BlockEntityAlfheimMarketAdvanced, ContainerAlfheimMarketAdvanced> advancedAlfheimMarket;
    public static final MenuBlockBE<BlockEntityAlfheimMarketUltimate, ContainerAlfheimMarketUltimate> ultimateAlfheimMarket;

    public static final MenuBlockBE<BlockEntityOrechidBase, ContainerOrechidBase> baseOrechid;
    public static final MenuBlockBE<BlockEntityOrechidUpgraded, ContainerOrechidUpgraded> upgradedOrechid;
    public static final MenuBlockBE<BlockEntityOrechidAdvanced, ContainerOrechidAdvanced> advancedOrechid;
    public static final MenuBlockBE<BlockEntityOrechidUltimate, ContainerOrechidUltimate> ultimateOrechid;


    public ModBlocks() {
    }

    static {
        malachiteDragonstoneBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));
        saffronDragonstoneBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));
        shadowDragonstoneBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));
        crimsonDragonstoneBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));

        electricGreenhouse = new BlockElectricGreenhouse(AutoBotany.getInstance(), BlockEntityElectricGreenhouseBase.class, BlockEntityMenu.createMenuType(ContainerElectricGreenhouse::new));
        jadedAmaranthus = new BlockJadedAmaranthus(AutoBotany.getInstance(), BlockEntityJadedAmaranthus.class, BlockEntityMenu.createMenuType(ContainerJadedAmaranthus::new));

        malachiteIngotBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));
        saffronIngotBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));
        shadowIngotBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));
        crimsonIngotBlock = new BlockBase(AutoBotany.getInstance(), BlockBehaviour.Properties.of(Material.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL));

        baseManaPool = new BlockManaPoolBase(AutoBotany.getInstance(), BlockEntityManaPoolBase.class, BlockEntityMenu.createMenuType(ContainerManaPoolBase::new));
        upgradedManaPool = new BlockManaPoolUpgraded(AutoBotany.getInstance(), BlockEntityManaPoolUpgraded.class, BlockEntityMenu.createMenuType(ContainerManaPoolUpgraded::new));
        advancedManaPool = new BlockManaPoolAdvanced(AutoBotany.getInstance(), BlockEntityManaPoolAdvanced.class, BlockEntityMenu.createMenuType(ContainerManaPoolAdvanced::new));
        ultimateManaPool = new BlockManaPoolUltimate(AutoBotany.getInstance(), BlockEntityManaPoolUltimate.class, BlockEntityMenu.createMenuType(ContainerManaPoolUltimate::new));

        baseRunicAltar = new BlockRunicAltarBase(AutoBotany.getInstance(), BlockEntityRunicAltarBase.class, BlockEntityMenu.createMenuType(ContainerRunicAltarBase::new));
        upgradedRunicAltar = new BlockRunicAltarUpgraded(AutoBotany.getInstance(), BlockEntityRunicAltarUpgraded.class, BlockEntityMenu.createMenuType(ContainerRunicAltarUpgraded::new));
        advancedRunicAltar = new BlockRunicAltarAdvanced(AutoBotany.getInstance(), BlockEntityRunicAltarAdvanced.class, BlockEntityMenu.createMenuType(ContainerRunicAltarAdvanced::new));
        ultimateRunicAltar = new BlockRunicAltarUltimate(AutoBotany.getInstance(), BlockEntityRunicAltarUltimate.class, BlockEntityMenu.createMenuType(ContainerRunicAltarUltimate::new));

        baseDaisy = new BlockDaisyBase(AutoBotany.getInstance(), BlockEntityDaisyBase.class, BlockEntityMenu.createMenuType(ContainerDaisyBase::new));
        upgradedDaisy = new BlockDaisyUpgraded(AutoBotany.getInstance(), BlockEntityDaisyUpgraded.class, BlockEntityMenu.createMenuType(ContainerDaisyUpgraded::new));
        advancedDaisy = new BlockDaisyAdvanced(AutoBotany.getInstance(), BlockEntityDaisyAdvanced.class, BlockEntityMenu.createMenuType(ContainerDaisyAdvanced::new));
        ultimateDaisy = new BlockDaisyUltimate(AutoBotany.getInstance(), BlockEntityDaisyUltimate.class, BlockEntityMenu.createMenuType(ContainerDaisyUltimate::new));

        baseApothecary = new BlockApothecaryBase(AutoBotany.getInstance(), BlockEntityApothecaryBase.class, BlockEntityMenu.createMenuType(ContainerApothecaryBase::new));
        upgradedApothecary = new BlockApothecaryUpgraded(AutoBotany.getInstance(), BlockEntityApothecaryUpgraded.class, BlockEntityMenu.createMenuType(ContainerApothecaryUpgraded::new));
        advancedApothecary = new BlockApothecaryAdvanced(AutoBotany.getInstance(), BlockEntityApothecaryAdvanced.class, BlockEntityMenu.createMenuType(ContainerApothecaryAdvanced::new));
        ultimateApothecary = new BlockApothecaryUltimate(AutoBotany.getInstance(), BlockEntityApothecaryUltimate.class, BlockEntityMenu.createMenuType(ContainerApothecaryUltimate::new));

        baseIndustrialAgglomerationFactory = new BlockIndustrialAgglomerationFactoryBase(AutoBotany.getInstance(), BlockEntityIndustrialAgglomerationFactoryBase.class, BlockEntityMenu.createMenuType(ContainerIndustrialAgglomerationFactoryBase::new));
        upgradedIndustrialAgglomerationFactory = new BlockIndustrialAgglomerationFactoryUpgraded(AutoBotany.getInstance(), BlockEntityIndustrialAgglomerationFactoryUpgraded.class, BlockEntityMenu.createMenuType(ContainerIndustrialAgglomerationFactoryUpgraded::new));
        advancedIndustrialAgglomerationFactory = new BlockIndustrialAgglomerationFactoryAdvanced(AutoBotany.getInstance(), BlockEntityIndustrialAgglomerationFactoryAdvanced.class, BlockEntityMenu.createMenuType(ContainerIndustrialAgglomerationFactoryAdvanced::new));
        ultimateIndustrialAgglomerationFactory = new BlockIndustrialAgglomerationFactoryUltimate(AutoBotany.getInstance(), BlockEntityIndustrialAgglomerationFactoryUltimate.class, BlockEntityMenu.createMenuType(ContainerIndustrialAgglomerationFactoryUltimate::new));

        baseAlfheimMarket = new BlockAlfheimMarketBase(AutoBotany.getInstance(), BlockEntityAlfheimMarketBase.class, BlockEntityMenu.createMenuType(ContainerAlfheimMarketBase::new));
        upgradedAlfheimMarket = new BlockAlfheimMarketUpgraded(AutoBotany.getInstance(), BlockEntityAlfheimMarketUpgraded.class, BlockEntityMenu.createMenuType(ContainerAlfheimMarketUpgraded::new));
        advancedAlfheimMarket = new BlockAlfheimMarketAdvanced(AutoBotany.getInstance(), BlockEntityAlfheimMarketAdvanced.class, BlockEntityMenu.createMenuType(ContainerAlfheimMarketAdvanced::new));
        ultimateAlfheimMarket = new BlockAlfheimMarketUltimate(AutoBotany.getInstance(), BlockEntityAlfheimMarketUltimate.class, BlockEntityMenu.createMenuType(ContainerAlfheimMarketUltimate::new));

        baseOrechid = new BlockOrechidBase(AutoBotany.getInstance(), BlockEntityOrechidBase.class, BlockEntityMenu.createMenuType(ContainerOrechidBase::new));
        upgradedOrechid = new BlockOrechidUpgraded(AutoBotany.getInstance(), BlockEntityOrechidUpgraded.class, BlockEntityMenu.createMenuType(ContainerOrechidUpgraded::new));
        advancedOrechid = new BlockOrechidAdvanced(AutoBotany.getInstance(), BlockEntityOrechidAdvanced.class, BlockEntityMenu.createMenuType(ContainerOrechidAdvanced::new));
        ultimateOrechid = new BlockOrechidUltimate(AutoBotany.getInstance(), BlockEntityOrechidUltimate.class, BlockEntityMenu.createMenuType(ContainerOrechidUltimate::new));

    }



}
