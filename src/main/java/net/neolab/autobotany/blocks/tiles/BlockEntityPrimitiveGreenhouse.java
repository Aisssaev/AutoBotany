package net.neolab.autobotany.blocks.tiles;

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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.neolab.autobotany.ModBlocks;
import net.neolab.autobotany.blocks.base.ElectricBotanicalTile;
import net.neolab.autobotany.crafting.GreenhouseRecipe;
import net.neolab.autobotany.crafting.PrimitiveGreenhouseRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.inventory.BaseItemStackHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BlockEntityPrimitiveGreenhouse extends ElectricBotanicalTile implements IInWorldGridNodeHost, IGridConnectedBlockEntity {
    public static int FIRST_NONCONS_INPUT_SLOT;
    public static int LAST_NONCONS_INPUT_SLOT;
    public static int FIRST_CONS_INPUT_SLOT;
    public static int LAST_CONS_INPUT_SLOT;
    private int currentMultiplier = 1;
    private final BaseItemStackHandler inventory;
    private final IManagedGridNode mainNode = this.createMainNode().setVisualRepresentation(this.getItemFromBlockEntity()).setInWorldNode(true).setTagName("proxy");
    public int progress;
    public int maxProgress;
    public int  energyConsume = 128;
    protected PrimitiveGreenhouseRecipe recipe;
    private boolean setChangedQueued;
    private int timeCheckOutputMana = 20;

    public BlockEntityPrimitiveGreenhouse(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state, 300000, 536870912, 300000);
        FIRST_NONCONS_INPUT_SLOT = 0;
        LAST_NONCONS_INPUT_SLOT = 0;
        FIRST_CONS_INPUT_SLOT = 1;
        LAST_CONS_INPUT_SLOT = 1;

        inventory = BaseItemStackHandler.builder(2)
                .validator((stack) -> {
                    return this.level != null && getInputs(false).contains(stack.getItem());
                }, 0)
                .validator((stack) -> {
                    return this.level != null && getInputs(true).contains(stack.getItem());
                }, 1)
                .slotLimit(16, 1)
                .slotLimit(8, 0)
                .contentsChanged((slot) -> {
                    this.setChanged();
                    this.setDispatchable();
                })
                .build();
        this.setChangedQueued = false;
    }

    @Override
    public int getStoredEU() {
        return this.energy;
    }

    private boolean hasRecipe() {
        var recipe = getRecipe();
        if (recipe != null) {
            return true;
        }
        return false;
    }

    private PrimitiveGreenhouseRecipe getRecipe() {
        var inventory = new SimpleContainer(this.inventory.getSlots());
        inventory.setItem(0, this.inventory.getStackInSlot(0));
        inventory.addItem(this.inventory.getStackInSlot(1));
        Optional<PrimitiveGreenhouseRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(PrimitiveGreenhouseRecipe.Type.INSTANCE, inventory, level);
        return recipe.orElse(null);
    }

    private void consumeItem() {
        this.inventory.extractItem(1, currentMultiplier, false);
    }

    public BlockEntity getBlockEntity() {
        return this;
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
    public int getCurrentConsumption() {
        return currentConsumption;
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

    public int getProgress() {
        return this.progress;
    }

    public int getMaxProgress() {
        return this.progress;
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

    public int currentConsumption;

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

    @Override
    public void tick() {
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

            if (this.getStoredEU() > 0 && this.getCurrentMana() < getMaxMana()) {
                var nutrition = 1;
                if (this.hasRecipe()  && hasEnergy(getRecipe().getEnergyPerTick()) && progress == 0) {
                    progress++;
                    this.recipe = getRecipe();
                    maxProgress = recipe.getTicks();
                    var nonConsInputCount = inventory.getStackInSlot(0).getCount();
                    this.currentMultiplier = Math.min(inventory.getStackInSlot(1).getCount(), Math.min(nonConsInputCount, 16));
                    consumeItem();
                    if (inventory.getStackInSlot(1).getFoodProperties(null) != null) {
                        nutrition = inventory.getStackInSlot(1).getFoodProperties(null).getNutrition();
                    }

                    currentConsumption = useEnergy(energyConsume);
                    addMana((int) (recipe.getManaOutput()/maxProgress) * currentMultiplier * nutrition * nutrition);
                } else if (progress > 0) {
                    progress++;
                    currentConsumption = useEnergy(energyConsume);
                    addMana((int) (recipe.getManaOutput()/maxProgress) * currentMultiplier * nutrition * nutrition);
                    if (progress >= maxProgress) {
                        this.progress = 0;
                        this.recipe = null;
                        this.maxProgress = 0;
                    }
                }
            }
        }
    }
}
