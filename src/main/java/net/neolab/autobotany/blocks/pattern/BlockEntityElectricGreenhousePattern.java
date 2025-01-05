package net.neolab.autobotany.blocks.pattern;

import appbot.ae2.ManaKey;
import appeng.api.config.Actionable;
import appeng.api.networking.GridHelper;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IInWorldGridNodeHost;
import appeng.api.networking.IManagedGridNode;
import appeng.api.networking.security.IActionSource;
import appeng.api.util.AECableType;
import appeng.hooks.ticking.TickHandler;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.google.common.collect.Range;
import ic2.api.tiles.readers.IProgressMachine;
import ic2.core.block.base.misc.comparator.types.base.ProgressComparator;
import ic2.core.utils.collection.NBTListWrapper;
import ic2.core.utils.helpers.NBTUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.neolab.autobotany.Items.upgrades.base.BaseUpgradeItem;
import net.neolab.autobotany.Items.upgrades.greenhouse.*;
import net.neolab.autobotany.ModBlocks;
import net.neolab.autobotany.api.IUpgradeItem;
import net.neolab.autobotany.blocks.base.ElectricBotanicalTile;
import net.neolab.autobotany.crafting.GreenhouseRecipe;
import net.neolab.autobotany.util.SettingPattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.inventory.BaseItemStackHandler;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.neolab.autobotany.api.IUpgradeItem.UpgradeType.*;

public class BlockEntityElectricGreenhousePattern extends ElectricBotanicalTile implements IInWorldGridNodeHost, IGridConnectedBlockEntity {
    public final int FIRST_NONCONS_INPUT_SLOT;
    public final int LAST_NONCONS_INPUT_SLOT;
    public final int FIRST_CONS_INPUT_SLOT;
    public final int LAST_CONS_INPUT_SLOT;
    public final int upgradeSlot;
    private final int[] currentMultiplier;
    private int multiplier = 1;
    private final BaseItemStackHandler inventory;
    private final IManagedGridNode mainNode = this.createMainNode().setVisualRepresentation(this.getItemFromBlockEntity()).setInWorldNode(true).setTagName("proxy");
    private final int[] progress;
    private final int[] maxProgress;
    private final boolean[] activeRecipes;
    public int  energyConsume = 0;
    public int currentEnergy;
    public double efficiencyMultiplier = 1.0;
    protected GreenhouseRecipe[] recipe;
    private boolean setChangedQueued;
    private int timeCheckOutputMana = 20;

    public BlockEntityElectricGreenhousePattern(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, int manaCap, int[] slots, int upgradeSlot, SettingPattern settingPattern, int maxInput, int maxEnergy) {
        super(blockEntityType, pos, state, manaCap, maxInput, maxEnergy);

        FIRST_NONCONS_INPUT_SLOT = slots[0];
        LAST_NONCONS_INPUT_SLOT = slots[1];
        FIRST_CONS_INPUT_SLOT = slots[2];
        LAST_CONS_INPUT_SLOT = slots[3];
        this.upgradeSlot = upgradeSlot;

        this.progress = new int[LAST_NONCONS_INPUT_SLOT + 1];
        this.maxProgress = new int[LAST_NONCONS_INPUT_SLOT + 1];
        this.currentMultiplier = new int[LAST_NONCONS_INPUT_SLOT + 1];
        this.activeRecipes = new boolean[LAST_NONCONS_INPUT_SLOT + 1];
        Arrays.fill(currentMultiplier, 1);
        this.recipe = new GreenhouseRecipe[LAST_NONCONS_INPUT_SLOT + 1];


        this.inventory = BaseItemStackHandler.builder(LAST_CONS_INPUT_SLOT + 1 + getUpgradeSlot())
                .validator((stack) -> {
                    return this.level != null && getInputs(false).contains(stack.getItem());
                }, Range.closedOpen(FIRST_NONCONS_INPUT_SLOT, LAST_NONCONS_INPUT_SLOT + 1))
                .validator((stack) -> {
                    return this.level != null && getInputs(true).contains(stack.getItem());
                }, Range.closedOpen(FIRST_CONS_INPUT_SLOT, LAST_CONS_INPUT_SLOT + 1))
                .validator((stack) -> {
                    return this.level != null && stack.getItem() instanceof BaseEnergyStorageUpgradeItem upgrade && getUpgrades().contains(upgrade.getType(stack));
                }, 33)
                .validator((stack) -> {
                    return this.level != null && stack.getItem() instanceof BaseManaStorageUpgradeItem upgrade && getUpgrades().contains(upgrade.getType(stack));
                }, 34)
                .validator((stack) -> {
                    return this.level != null && stack.getItem() instanceof BaseSpeedUpgradeItem upgrade && getUpgrades().contains(upgrade.getType(stack));
                }, 35)
                .validator((stack) -> {
                    return this.level != null && stack.getItem() instanceof BaseRecipeMultiplierUpgradeItem upgrade && getUpgrades().contains(upgrade.getType(stack));
                }, 36)
                .validator((stack) -> {
                    return this.level != null && stack.getItem() instanceof EfficiencyUpgradeItem upgrade && getUpgrades().contains(upgrade.getType(stack));
                }, 37)
                .slotLimit(1, Range.closedOpen(33, 38))
                .contentsChanged((slot) -> {
//                    if (slot > LAST_CONS_INPUT_SLOT || slot < FIRST_CONS_INPUT_SLOT) {
//                        Arrays.fill(progress, 0);
//                        Arrays.fill(activeRecipes, false);
//                    }
                    this.setChanged();
                    this.setDispatchable();
                })
                .build();
        for (int i = 0; i <= LAST_NONCONS_INPUT_SLOT; i++) {
            this.addComparator(new ProgressComparator("progress_" + i, this.translate("comparator.autobotany.multiprogress", i), new MachineProgress(this, i)));
        }
        this.setChangedQueued = false;

    }

    public int getUpgradeSlot() {
        return this.upgradeSlot;
    }

    @Override
    public int getStoredEU() {
        return this.energy;
    }

    private boolean hasRecipe(int slot) {
        var recipe = getRecipe(slot);
        if (recipe != null) {
            return true;
        }
        return false;
    }

    private GreenhouseRecipe getRecipe(int slot) {
        var inventory = new SimpleContainer(this.inventory.getSlots());
        inventory.setItem(0, this.inventory.getStackInSlot(slot));
        for (int i = 5; i <= LAST_CONS_INPUT_SLOT; i++) {
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }
        Optional<GreenhouseRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(GreenhouseRecipe.Type.INSTANCE, inventory, level);
        return recipe.orElse(null);
    }

    private void consumeItem(int consSlot, int nonConsSlot) {
        this.inventory.extractItem(consSlot, currentMultiplier[nonConsSlot], false);
    }

    private int getConsSlot(ItemStack[] itemStack) {
        for (int i = LAST_NONCONS_INPUT_SLOT; i <= LAST_CONS_INPUT_SLOT; i++) {
            for (var stack : itemStack) {
                if (inventory.getStackInSlot(i).getItem() == stack.getItem()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void handleUpgrades() {
        var efficiencyMultiplier = 1.0;
        var speedMultiplier = 1.0;
        var energyStorage = 0;
        var manaStorage = 0;
        var energyConsume = 0;
        var recipeMultiplier = 1;

        var types = this.getUpgrades();


        for (int i = LAST_CONS_INPUT_SLOT + 1; i <= LAST_CONS_INPUT_SLOT + getUpgradeSlot(); i++) {
            var itemStack = this.getInventory().getStackInSlot(i);
            if (itemStack.getItem() instanceof BaseUpgradeItem upgradeItem) {
                if (types.contains(upgradeItem.getType(itemStack))) {
                    efficiencyMultiplier *= upgradeItem.getEfficiencyMultiplier(itemStack, this);
                    speedMultiplier *= upgradeItem.getSpeedMultiplier(itemStack, this);
                    energyStorage += upgradeItem.getEnergyStorage(itemStack, this);
                    manaStorage += upgradeItem.getManaStorage(itemStack, this);
                    energyConsume += upgradeItem.getEnergyConsumed(itemStack, this);
                    recipeMultiplier *= upgradeItem.getRecipeMultiplier(itemStack, this);
                }
            }
        }

        this.setMaxEnergy(Math.max(0, energyStorage));
        this.setMaxMana(Math.max(0, manaStorage));
        this.multiplier = recipeMultiplier;
        if (this.energy > this.getMaxEU() ) {
            this.energy = this.getMaxEU();
        }
        if (this.getCurrentMana() > this.getMaxMana()){
            this.mana = this.getMaxMana();
        }
        this.energyConsume = Math.max(energyConsume, Math.max(this.energyConsume, 500));
        this.efficiencyMultiplier = efficiencyMultiplier;
        for (int i = 0; i < maxProgress.length; i++) {
            if (recipe[i] != null && maxProgress[i] == recipe[i].getTicks()) {
                this.maxProgress[i] = (int) (maxProgress[i] * speedMultiplier);
            }
        }
        setChanged();
        setDispatchable();
    }

    private EnumSet<IUpgradeItem.UpgradeType> getUpgrades() {
        return EnumSet.of(PROCESSING, EFFICIENCY, MANA_STORAGE, ENERGY_STORAGE, RECIPE);
    }

    public int getCurrentConsumption() {
        return this.currentConsumption;
    }

    int currentConsumption;
    @Override
    public void tick() {
        updateGuiField("energy");
        if (this.level != null && !this.level.isClientSide) {
            if (!this.getMainNode().isReady()) {
                this.getMainNode().create(this.level, this.getBlockPos());
            }
            if (getMainNode() != null && getMainNode().getNode() != null && getMainNode().isOnline()) {
                if (timeCheckOutputMana <= 0) {
                    if (mana > 0) {
                        this.exportManaToME();
                        timeCheckOutputMana = 20;
                    }
                } else {
                    timeCheckOutputMana--;
                }
            }

            handleUpgrades();
            if (this.getStoredEU() > 0 && this.getCurrentMana() < getMaxMana()) {
                for (int i = 0; i <= LAST_NONCONS_INPUT_SLOT; i++) {
                    if (!inventory.getStackInSlot(i).isEmpty()) {
                        var nutrition = 1;
                        if (this.hasRecipe(i) && !this.activeRecipes[i] && hasEnergy(getRecipe(i).getEnergyPerTick())) {
                            progress[i]++;
                            this.recipe[i] = getRecipe(i);
                            maxProgress[i] = recipe[i].getTicks();
                            this.activeRecipes[i] = true;
                            handleUpgrades();
                            var nonConsInputCount = inventory.getStackInSlot(i).getCount();
                            var consSlot = getConsSlot(recipe[i].getConsumableInput().getItems());
                            this.currentMultiplier[i] = Math.min(nonConsInputCount, Math.min(multiplier, inventory.getStackInSlot(consSlot).getCount()));
                            consumeItem(getConsSlot(recipe[i].getConsumableInput().getItems()), i);
                            if (inventory.getStackInSlot(consSlot).getFoodProperties(null) != null) {
                                nutrition = inventory.getStackInSlot(consSlot).getFoodProperties(null).getNutrition();
                            }
                            currentConsumption = useEnergy(recipe[i].getEnergyPerTick() + energyConsume * currentMultiplier[i]);
                            addMana((int) (recipe[i].getManaOutput()*efficiencyMultiplier/maxProgress[i]) * currentMultiplier[i] * nutrition * nutrition);
                        } else if (this.activeRecipes[i] && hasEnergy(recipe[i].getEnergyPerTick())) {
                            this.progress[i]++;
                            currentConsumption = useEnergy(recipe[i].getEnergyPerTick() + energyConsume * currentMultiplier[i]);
                            addMana((int) (recipe[i].getManaOutput()*efficiencyMultiplier/maxProgress[i]) * currentMultiplier[i] * nutrition * nutrition);
                            if (this.progress[i] >= maxProgress[i]) {
                                var mana = (int) (recipe[i].getManaOutput()*efficiencyMultiplier/maxProgress[i]) * currentMultiplier[i] * nutrition * nutrition;
                                if (mana * maxProgress[i] < recipe[i].getManaOutput() * efficiencyMultiplier * nutrition * nutrition) {
                                    addMana((int) (recipe[i].getManaOutput() * efficiencyMultiplier - mana * maxProgress[i]));
                                }
                                this.progress[i] = 0;
                                this.activeRecipes[i] = false;
                                this.recipe[i] = null;
                                this.maxProgress[i] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public List<Item> getInputs(boolean consumable) {
        List<Item> inputs = new ArrayList<>();
        if (this.level == null) return List.of();
        this.level.getRecipeManager().getAllRecipesFor(GreenhouseRecipe.Type.INSTANCE).forEach((recipe) -> {
            if (recipe.getConsumableInput() != null && recipe.getNonConsumableInput() != null) {
                if (consumable) {
                    Arrays.stream(recipe.getConsumableInput().getItems()).map(ItemStack::getItem).forEach((item -> {
                        if (!inputs.contains(item)) {
                            inputs.add(item);
                        }
                    }));
                } else {
                    Arrays.stream(recipe.getNonConsumableInput().getItems()).map(ItemStack::getItem).forEach((item -> {
                        if (!inputs.contains(item)) {
                            inputs.add(item);
                        }
                    }));
                }

            }
        });
        return inputs;
    }

    public BlockEntity getBlockEntity() {
        return this;
    }

    public @Nullable GreenhouseRecipe getGreenhouseRecipe() {
        return this.level.getRecipeManager()
                .getAllRecipesFor(GreenhouseRecipe.Type.INSTANCE)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void drops() {
        var inventory = this.getInventory().getUnrestricted();
        for (int i = 0; i < inventory.getSlots(); i++) {
            var itemStack = inventory.getStackInSlot(i);
            if (itemStack.isEmpty()) continue;
            var ie = new ItemEntity(this.level, (double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.7, (double) this.worldPosition.getZ() + 0.5, itemStack.copy());
            this.level.addFreshEntity(ie);
        }
    }

    protected IManagedGridNode createMainNode() {
        return GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE);
    }

    protected Item getItemFromBlockEntity() {
        return ModBlocks.electricGreenhouse.asItem();
    }

    private void setChangedAtEndOfTick(Level level) {
        this.setChanged();
        this.setChangedQueued = false;
    }

    @Nullable
    @Override
    public IGridNode getGridNode(Direction direction) {
        return this.getMainNode().getNode();
    }

    @Override
    public IManagedGridNode getMainNode() {
        return this.mainNode;
    }

    @Override
    public void securityBreak() {
        this.level.destroyBlock(this.worldPosition, true);
    }

    @Override
    public void saveChanges() {
        if (level != null) {
            if (level.isClientSide) {
                this.setChanged();
            } else {
                this.level.blockEntityChanged(this.worldPosition);
                if (!this.setChangedQueued) {
                    TickHandler.instance().addCallable((LevelAccessor) null, this::setChangedAtEndOfTick);
                    this.setChangedQueued = true;
                }
            }
        }
    }

    @Override
    public void setRemoved() {
        if (this.getMainNode() != null) {
            this.getMainNode().destroy();
        }
        super.setRemoved();
    }

    @Override
    public AECableType getCableConnectionType(Direction dir) {
        return AECableType.SMART;
    }

    @Override
    public BlockEntityType<?> createType() {
        return this.blockEntityType;
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    protected Predicate<Integer> getExtracts(Supplier<IItemHandlerModifiable> var1) {
        return (slot) -> false;
    }

    @NotNull
    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public int getComparatorOutput() {
        return 0;
    }

    public int getProgress(int slot) {
        return this.progress[slot];
    }

    public int getMaxProgress(int slot) {
        return this.progress[slot];
    }

    private void exportManaToME() {
        if (mana > 0) {
            var getCountExport = Math.toIntExact(this.getMainNode().getGrid().getStorageService().getInventory().insert(ManaKey.KEY, mana, Actionable.MODULATE, IActionSource.empty()));
            if (getCountExport > 0) {
                this.mana -= getCountExport;
            }
        }
//        var grid = getMainNode().getGrid();
//        var storage = grid.getStorageService().getInventory();
//        if (mana > 0) {
//            var changed = StorageHelper.poweredExtraction(grid.getEnergyService(), storage, ManaKey.KEY, -mana, IActionSource.ofMachine(mainNode::getNode));
//            this.mana -= changed;
//        }

    }

    //TODO: Сделать сохранение прогресса и рецептов
//    public void load(@Nonnull CompoundTag tag) {
//        super.load(tag);
//        var it = NBTListWrapper.wrap(tag.getList("porgress", 10), CompoundTag.class).iterator();
//        while (it.hasNext()) {
//            CompoundTag nbt = it.next();
//            var slot = nbt.getInt("slot");
//            if (slot >= 0 && slot < this.progress.length) {
//                this.progress[slot] = nbt.getInt("progress");
//                this.activeRecipes[slot] = nbt.getBoolean("active");
//                if (nbt.contains("recipe")) {
//                    var recipeId = nbt.getString("recipe");
//                    var recipe = greenhouseRecipes.get(new ResourceLocation(recipeId));
////                    Optional<GreenhouseRecipe> loadedRecipe = (Optional<GreenhouseRecipe>) level.getRecipeManager().byKey(new ResourceLocation(recipeId));
//                    this.recipe[slot] = recipe;
//                }
//            }
//        }
//    }

//    public void saveAdditional(@Nonnull CompoundTag nbt) {
//        super.saveAdditional(nbt);
//        var list = new ListTag();
//        for (int i = 0; i < this.progress.length; ++i) {
//            if (this.progress[i] > 0) {
//                var tag = new CompoundTag();
//                tag.putInt("progress", this.progress[i]);
//                tag.putBoolean("active", this.activeRecipes[i]);
//                if (recipe[i] != null) {
//                    tag.putString("recipe", recipe[i].getId().toString());
//                }
//                list.add(tag);
//            }
//        }
//        NBTUtils.put(nbt, "porgress", list);
//    }


    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        if (this.level == null || this.level.isClientSide) {
            this.currentConsumption = tag.getInt("currentConsumption");

        }
    }

    @Nonnull
    public CompoundTag getUpdateTag() {
        super.getUpdateTag();
        if (this.level != null && this.level.isClientSide) {
            return super.getUpdateTag();
        } else {
            CompoundTag nbt = super.getUpdateTag();
            nbt.putInt("currentConsumption", this.currentConsumption);
            return nbt;
        }
    }

    private static class MachineProgress implements IProgressMachine {
        BlockEntityElectricGreenhousePattern tile;
        int index;

        public MachineProgress(BlockEntityElectricGreenhousePattern tile, int index) {
            this.tile = tile;
            this.index = index;
        }

        @Override
        public float getProgress() {
            return this.tile.getProgress(this.index);
        }

        @Override
        public float getMaxProgress() {
            return this.tile.getMaxProgress(this.index);
        }
    }
}
