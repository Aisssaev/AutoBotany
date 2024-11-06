package net.neolab.botanicalextramachinery.blocks.pattern;

import appeng.api.networking.GridHelper;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IInWorldGridNodeHost;
import appeng.api.networking.IManagedGridNode;
import appeng.api.util.AECableType;
import appeng.hooks.ticking.TickHandler;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.google.common.collect.Range;
import ic2.api.tiles.readers.IProgressMachine;
import ic2.core.block.base.misc.comparator.types.base.ProgressComparator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.neolab.botanicalextramachinery.ModBlocks;
import net.neolab.botanicalextramachinery.blocks.base.ElectricBotanicalTile;
import net.neolab.botanicalextramachinery.data.recipes.GreenhouseRecipe;
import net.neolab.botanicalextramachinery.util.SettingPattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.inventory.BaseItemStackHandler;
import org.moddingx.libx.inventory.IAdvancedItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BlockEntityElectricGreenhousePattern extends ElectricBotanicalTile implements IInWorldGridNodeHost, IGridConnectedBlockEntity {
    public final int FIRST_NONCONS_INPUT_SLOT;
    public final int LAST_NONCONS_INPUT_SLOT;
    public final int FIRST_CONS_INPUT_SLOT;
    public final int LAST_CONS_INPUT_SLOT;
    private final int[] currentMultiplier;
    private final int multiplierCap;
    private final BaseItemStackHandler inventory;
    private final IManagedGridNode mainNode = this.createMainNode().setVisualRepresentation(this.getItemFromBlockEntity()).setInWorldNode(true).setTagName("proxy");
    private final int[] progress;
    private final int[] maxProgress;
    private final boolean[] activeRecipes;
    public int currentEnergy;
    protected GreenhouseRecipe[] recipe;
    private boolean setChangedQueued;

    public BlockEntityElectricGreenhousePattern(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, int manaCap, int[] slots, int[] upgradeSlot, SettingPattern settingPattern, int maxInput, int maxEnergy, int multiplierCap) {
        super(blockEntityType, pos, state, manaCap, maxInput, maxEnergy);

        FIRST_NONCONS_INPUT_SLOT = slots[0];
        LAST_NONCONS_INPUT_SLOT = slots[1];
        FIRST_CONS_INPUT_SLOT = slots[2];
        LAST_CONS_INPUT_SLOT = slots[3];

        this.progress = new int[LAST_NONCONS_INPUT_SLOT + 1];
        this.maxProgress = new int[LAST_NONCONS_INPUT_SLOT + 1];
        this.currentMultiplier = new int[LAST_NONCONS_INPUT_SLOT + 1];
        this.activeRecipes = new boolean[LAST_NONCONS_INPUT_SLOT + 1];
        Arrays.fill(currentMultiplier, 1);
        this.recipe = new GreenhouseRecipe[LAST_NONCONS_INPUT_SLOT + 1];
        this.multiplierCap = multiplierCap;


        this.inventory = BaseItemStackHandler.builder(LAST_CONS_INPUT_SLOT + 1)
                .validator((stack) -> {
                    return this.level != null && getInputs(false).contains(stack.getItem());
                }, Range.closedOpen(FIRST_NONCONS_INPUT_SLOT, LAST_NONCONS_INPUT_SLOT + 1))
                .validator((stack) -> {
                    return this.level != null && getInputs(true).contains(stack.getItem());
                }, Range.closedOpen(FIRST_CONS_INPUT_SLOT, LAST_CONS_INPUT_SLOT + 1))
                .contentsChanged(() -> {
                    this.setChanged();
                    this.setDispatchable();
                })
                .build();
        for (int i = 0; i <= LAST_NONCONS_INPUT_SLOT; i++) {
            this.addComparator(new ProgressComparator("progress_" + i, this.translate("comparator.autobotany.multiprogress", i), new MachineProgress(this, i)));
        }
        this.setChangedQueued = false;

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
        for (int i = 5; i < this.inventory.getSlots(); i++) {
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }
        Optional<GreenhouseRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(GreenhouseRecipe.Type.INSTANCE, inventory, level);
        return recipe.orElse(null);
    }

    private void consumeItem(int consSlot, int nonConsSlot) {
        this.inventory.extractItem(consSlot, currentMultiplier[nonConsSlot], false);
    }

    private int getConsSlot(ItemStack itemStack) {
        for (int i = LAST_NONCONS_INPUT_SLOT; i < inventory.getSlots(); i++) {
            if (inventory.getStackInSlot(i).getItem() == itemStack.getItem()) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void tick() {
        updateGuiField("energy");
        if (this.level != null && !this.level.isClientSide) {
            if (!this.getMainNode().isReady()) {
                this.getMainNode().create(this.level, this.getBlockPos());
            }
            if (this.getStoredEU() > 0 && this.getCurrentMana() != this.getMaxMana()) {
                for (int i = 0; i <= LAST_NONCONS_INPUT_SLOT; i++) {
                    if (this.hasRecipe(i) && !this.activeRecipes[i]) {
                        this.recipe[i] = getRecipe(i);
                        progress[i]++;
                        this.activeRecipes[i] = true;
                        var nonConsInputCount = this.inventory.getStackInSlot(i).getCount();
                        if (!recipe[i].getConsumableInput().isEmpty()){
                            var consInput = recipe[i].getConsumableInput();
                            if (getConsSlot(consInput.getItems()[0]) != -1) {
                                var consSlot = getConsSlot(consInput.getItems()[0]);
                                this.currentMultiplier[i] = Math.min(nonConsInputCount, Math.min(multiplierCap, this.inventory.getStackInSlot(consSlot).getCount()));
                                if (hasEnergy(recipe[i].getEnergyPerTick() * currentMultiplier[i] * recipe[i].getTicks())) {
                                    consumeItem(consSlot, i);
                                    useEnergy(recipe[i].getEnergyPerTick() * currentMultiplier[i]);
                                    addMana(recipe[i].getManaOutputPerTick() * currentMultiplier[i]);
                                } else {
                                    progress[i] = 0;
                                    this.activeRecipes[i] = false;
                                }
                            }
                        } else {
                            this.currentMultiplier[i] = Math.min(nonConsInputCount, multiplierCap);
                        }
                    } else if (this.activeRecipes[i]) {
                        if (progress[i] >= recipe[i].getTicks()) {
                            this.activeRecipes[i] = false;
                            progress[i] = 0;
                        } else {
                            if (hasEnergy(recipe[i].getEnergyPerTick() * currentMultiplier[i])) {
                                useEnergy(recipe[i].getEnergyPerTick() * currentMultiplier[i]);
                                addMana(recipe[i].getManaOutputPerTick() * currentMultiplier[i]);
                                progress[i]++;
                            } else {
                                progress[i] = 0;
                                this.activeRecipes[i] = false;
                            }
                        }
                    }

                }
            }
//            if (this.getStoredEU() > 0 && this.getCurrentMana() != this.getMaxMana()) {
//                for (int i = 0; i <= LAST_NONCONS_INPUT_SLOT; i++) {
//                    if (this.hasRecipe(i) && !this.activeRecipes[i]) {
//                        this.recipe[i] = getRecipe(i);
//                        progress[i]++;
//                        this.activeRecipes[i] = true;
//                        var consInput = recipe[i].getConsumableInput();
//                        var nonConsInputCount = this.inventory.getStackInSlot(i).getCount();
//                        if (getConsSlot(consInput.getItems()[0]) != -1) {
//                            var consSlot = getConsSlot(consInput.getItems()[0]);
//                            this.currentMultiplier[i] = Math.min(nonConsInputCount, Math.min(multiplierCap, this.inventory.getStackInSlot(consSlot).getCount()));
//                            consumeItem(consSlot, i);
//                            addMana(recipe[i].getManaOutputPerTick() * currentMultiplier[i]);
//                            useEnergy(recipe[i].getEnergyPerTick() * currentMultiplier[i]);
//                        }
//                    } else if (this.activeRecipes[i]) {
//                        if (progress[i] >= recipe[i].getTicks()) {
//                            this.activeRecipes[i] = false;
//                            progress[i] = 0;
//                        } else {
//                            addMana(recipe[i].getManaOutputPerTick() * currentMultiplier[i]);
//                            useEnergy(recipe[i].getEnergyPerTick() * currentMultiplier[i]);
//                            progress[i]++;
//                        }
//                    }
//
//                }
//            }
            this.currentEnergy = this.getCurrentMana();
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
        return ModBlocks.ELECTRIC_GREENHOUSE.asItem();
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
