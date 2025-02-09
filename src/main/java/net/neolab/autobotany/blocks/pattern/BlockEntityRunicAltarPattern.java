package net.neolab.autobotany.blocks.pattern;

import appeng.api.config.Actionable;
import appeng.api.networking.*;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.util.AECableType;
import appeng.hooks.ticking.TickHandler;
import appeng.me.helpers.BlockEntityNodeListener;
import appeng.me.helpers.IGridConnectedBlockEntity;
import com.google.common.collect.Range;
import com.google.common.collect.Streams;
import net.neolab.autobotany.ModBlocks;
import net.neolab.autobotany.ModItems;
import net.neolab.autobotany.blocks.base.WorkingTile;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarAdvanced;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarBase;
import net.neolab.autobotany.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUpgraded;
import net.neolab.autobotany.config.LibXClientConfig;
import net.neolab.autobotany.config.LibXServerConfig;
import net.neolab.autobotany.util.SettingPattern;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.moddingx.libx.crafting.recipe.RecipeHelper;
import org.moddingx.libx.inventory.BaseItemStackHandler;
import org.moddingx.libx.inventory.IAdvancedItemHandlerModifiable;
import vazkii.botania.api.recipe.RunicAltarRecipe;
import vazkii.botania.client.fx.SparkleParticleData;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;
import vazkii.botania.common.lib.BotaniaTags;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BlockEntityRunicAltarPattern extends WorkingTile<RunicAltarRecipe>
        implements IInWorldGridNodeHost, IGridConnectedBlockEntity {
    public static final int MAX_MANA_PER_TICK = LibXServerConfig.RunicAltarSettings.manaCost;

    private final int LIVINGROCK_SLOT_1;
    private final int LIVINGROCK_SLOT_2;
    private final int LIVINGROCK_SLOT_3;
    private final int UPGRADE_SLOT_1;
    private final int UPGRADE_SLOT_2;
    private final int FIRST_INPUT_SLOT;
    private final int LAST_INPUT_SLOT;
    private final int FIRST_OUTPUT_SLOT;
    private final int LAST_OUTPUT_SLOT;

    private final BaseItemStackHandler inventory;
    private final SettingPattern settingPattern;
    private final boolean[] isUpgrade;

    private final List<Integer> slotsUsed = new ArrayList();
    private int timeCheckOutputSlot = LibXServerConfig.tickOutputSlots;

    private List<Integer> slotsLivingRock = new ArrayList<>();

    public BlockEntityRunicAltarPattern(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                        int manaCap, int[] slots, boolean[] isUpgrade, int countCraft,
                                        SettingPattern config) {

        super(type, BotaniaRecipeTypes.RUNE_TYPE, pos, state, manaCap, slots[1], slots[3], countCraft);

        LIVINGROCK_SLOT_1 = addLivingRockSlotsArray(slots[0]);

        FIRST_INPUT_SLOT = slots[1];
        LAST_INPUT_SLOT = slots[2];
        FIRST_OUTPUT_SLOT = slots[3];
        LAST_OUTPUT_SLOT = slots[4];

        UPGRADE_SLOT_1 = slots[5];
        UPGRADE_SLOT_2 = slots[6];


        LIVINGROCK_SLOT_2 = addLivingRockSlotsArray(slots[7]);
        LIVINGROCK_SLOT_3 = addLivingRockSlotsArray(slots[8]);

        if (isUpgrade[0] && isUpgrade[1]){
            inventory = BaseItemStackHandler.builder(LAST_OUTPUT_SLOT + 1)
                    .validator((stack) -> {return stack.getItem() == BotaniaBlocks.livingrock.asItem();}, LIVINGROCK_SLOT_1, LIVINGROCK_SLOT_2, LIVINGROCK_SLOT_3)
                    .validator((stack) -> {return (stack.getItem() == ModItems.moduleManaInfinity.asItem() || stack.getItem() == ModItems.moduleLivingRockInfinity.asItem());}, UPGRADE_SLOT_1)
                    .validator((stack) -> {return (stack.getItem() == ModItems.moduleManaInfinity.asItem() || stack.getItem() == ModItems.moduleLivingRockInfinity.asItem()) ;}, UPGRADE_SLOT_2)
                    .validator((stack) -> {return this.level != null && RecipeHelper.isItemValidInput(this.level.getRecipeManager(), BotaniaRecipeTypes.RUNE_TYPE, stack);}, Range.closedOpen(FIRST_INPUT_SLOT, LAST_INPUT_SLOT + 1))
                    .slotLimit(1, UPGRADE_SLOT_1, UPGRADE_SLOT_2).output(Range.closedOpen(FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT + 1)).contentsChanged(() -> {this.setChanged();this.setDispatchable();this.needsRecipeUpdate();})
                    .build();
        } else if (isUpgrade[0]){
            inventory = BaseItemStackHandler.builder(LAST_OUTPUT_SLOT + 1)
                    .validator((stack) -> {return stack.getItem() == BotaniaBlocks.livingrock.asItem();}, LIVINGROCK_SLOT_1, LIVINGROCK_SLOT_2, LIVINGROCK_SLOT_3)
                    .validator((stack) -> {return stack.getItem() == ModItems.moduleManaInfinity.asItem() || stack.getItem() == ModItems.moduleLivingRockInfinity.asItem();}, UPGRADE_SLOT_1)
                    .validator((stack) -> {return this.level != null && RecipeHelper.isItemValidInput(this.level.getRecipeManager(), BotaniaRecipeTypes.RUNE_TYPE, stack);}, Range.closedOpen(FIRST_INPUT_SLOT, LAST_INPUT_SLOT + 1))
                    .slotLimit(1, UPGRADE_SLOT_1, UPGRADE_SLOT_2).output(Range.closedOpen(FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT + 1)).contentsChanged(() -> {this.setChanged();this.setDispatchable();this.needsRecipeUpdate();})
                    .build();
        } else {
            inventory = BaseItemStackHandler.builder(LAST_OUTPUT_SLOT + 1)
                    .validator((stack) -> {return stack.getItem() == BotaniaBlocks.livingrock.asItem();}, LIVINGROCK_SLOT_1, LIVINGROCK_SLOT_2, LIVINGROCK_SLOT_3)
                    .validator((stack) -> {return this.level != null && RecipeHelper.isItemValidInput(this.level.getRecipeManager(), BotaniaRecipeTypes.RUNE_TYPE, stack);}, Range.closedOpen(FIRST_INPUT_SLOT, LAST_INPUT_SLOT + 1))
                    .slotLimit(1, UPGRADE_SLOT_1, UPGRADE_SLOT_2).output(Range.closedOpen(FIRST_OUTPUT_SLOT, LAST_OUTPUT_SLOT + 1)).contentsChanged(() -> {this.setChanged();this.setDispatchable();this.needsRecipeUpdate();})
                    .build();
        }

        this.settingPattern = config;
        this.isUpgrade = isUpgrade;


        //region AE INTEGRATION
        this.setChangedQueued = false;

        //endregion
    }


    //region BASE CODE

    public int addLivingRockSlotsArray(int index){
        slotsLivingRock.add(index);
        return slotsLivingRock.get(index);
    }

    public List<ItemStack> getUpgrades(){
        List<ItemStack> res = new ArrayList<>();

        res.add(new ItemStack(ModItems.moduleManaInfinity));
        res.add(new ItemStack(ModItems.moduleLivingRockInfinity));

        return res;
    }


    public void tick() {
        if (this.level != null && !this.level.isClientSide) {
            if (!this.getMainNode().isReady()){
                this.getMainNode().create(this.level, this.getBlockPos());
            }

            if (getMainNode() != null && getMainNode().getNode() != null && getMainNode().isOnline()){
                if (timeCheckOutputSlot <= 0){
                    if (checkOutputSlots()){
                        this.exportResultsItemsME();
                    }

                    timeCheckOutputSlot = 20;
                } else {
                    timeCheckOutputSlot--;
                }
            }

            if (isUpgrade[0] && this.getMaxMana() != this.getCurrentMana()
                    && ((!getSlotUpgrade1().isEmpty() && getSlotUpgrade1().getItem() == ModItems.moduleManaInfinity)
                    || (isUpgrade[1] && !getSlotUpgrade2().isEmpty() && getSlotUpgrade2().getItem() == ModItems.moduleManaInfinity))){
                this.receiveMana(this.getMaxMana());
            }

            if (isUpgrade[0] &&
                    ( inventory.getStackInSlot(LIVINGROCK_SLOT_1).isEmpty() ||
                            inventory.getStackInSlot(LIVINGROCK_SLOT_2).isEmpty() ||
                            inventory.getStackInSlot(LIVINGROCK_SLOT_3).isEmpty())){

                if ((!getSlotUpgrade1().isEmpty() && getSlotUpgrade1().getItem() == ModItems.moduleLivingRockInfinity.asItem()) ||
                        (isUpgrade[1] && !getSlotUpgrade2().isEmpty() && getSlotUpgrade2().getItem() == ModItems.moduleLivingRockInfinity.asItem())){
                    addLivingRockSlots();
                }
            }

            List slotsUsed = this.slotsUsed;
            this.runRecipeTick(
                    slotsUsed::clear,
                    (stack, slot) -> {this.slotsUsed.add(slot); },
                    (stack, slot) -> {});

        } else if (this.level != null && LibXClientConfig.RenderingVisualContent.all && settingPattern.getConfigBoolean("mechanicalRunicAltarRender") && this.getMaxProgress() > 0 && this.getProgress() >= this.getMaxProgress() - 5 * this.getMaxManaPerTick()) {
            for (int i = 0; i < 5; ++i) {
                SparkleParticleData data = SparkleParticleData.sparkle(this.level.random.nextFloat(), this.level.random.nextFloat(), this.level.random.nextFloat(), this.level.random.nextFloat(), 10);
                this.level.addParticle(data, (double) this.worldPosition.getX() + 0.3 + this.level.random.nextDouble() * 0.4, (double) this.worldPosition.getY() + 0.7, (double) this.worldPosition.getZ() + 0.3 + this.level.random.nextDouble() * 0.4, 0.0, 0.0, 0.0);
            }
        }
    }

    public ItemStack getSlotUpgrade1(){
        if (UPGRADE_SLOT_1 == -1){
            return null;
        }
        return inventory.getStackInSlot(UPGRADE_SLOT_1);
    }

    public ItemStack getSlotUpgrade2(){
        if (UPGRADE_SLOT_2 == -1){
            return null;
        }
        return inventory.getStackInSlot(UPGRADE_SLOT_2);
    }

    public void addLivingRockSlots(){
        ItemStack livingrock = new ItemStack(BotaniaBlocks.livingrock.asItem());
        livingrock.setCount(64);

        if (LIVINGROCK_SLOT_1 == LIVINGROCK_SLOT_2 && LIVINGROCK_SLOT_2 == LIVINGROCK_SLOT_3
                && inventory.getStackInSlot(LIVINGROCK_SLOT_1).isEmpty()){
            inventory.setStackInSlot(LIVINGROCK_SLOT_1, livingrock);
        } else{
            if (inventory.getStackInSlot(LIVINGROCK_SLOT_1).isEmpty()) inventory.setStackInSlot(LIVINGROCK_SLOT_1, livingrock);
            else if (inventory.getStackInSlot(LIVINGROCK_SLOT_2).isEmpty()) inventory.setStackInSlot(LIVINGROCK_SLOT_2, livingrock);
            else if (inventory.getStackInSlot(LIVINGROCK_SLOT_3).isEmpty()) inventory.setStackInSlot(LIVINGROCK_SLOT_3, livingrock);
        }
    }

    protected Predicate<Integer> getExtracts(Supplier<IItemHandlerModifiable> inventory) {
        return (slot) -> {
            return slot >= FIRST_OUTPUT_SLOT && slot <= LAST_OUTPUT_SLOT;
        };
    }

    @Nonnull
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    protected boolean canMatchRecipes() {
        return !this.inventory.getStackInSlot(LIVINGROCK_SLOT_1).isEmpty() ||
                !this.inventory.getStackInSlot(LIVINGROCK_SLOT_2).isEmpty() ||
                !this.inventory.getStackInSlot(LIVINGROCK_SLOT_3).isEmpty();
    }

    protected void onCrafted(RunicAltarRecipe recipe, int countCraft) {
        int renaming = countCraft;
        for (int slot: slotsLivingRock){
            if (!inventory.getStackInSlot(slot).isEmpty()){
                int extractCount = Math.min(renaming, countCraft);

                ItemStack extract = inventory.extractItem(slot, extractCount, false);
                if (!extract.isEmpty()) {
                    renaming -= extractCount;
                }
                else break;
            }
        }
    }

    protected List<ItemStack> resultItems(RunicAltarRecipe recipe, @NotNull List<ItemStack> stacks) {
        var res = Streams.concat(new Stream[]
                {
                        stacks.stream()
                                .filter((s) -> {return s.is(BotaniaTags.Items.RUNES);})
                                .map(s -> {
                            var copy = s.copy();
                            copy.setCount(1);
                            return copy;
                        }),
                        super.resultItems(recipe, stacks)
                                .stream()
                }).toList();
        return res;
    }

    protected int getMaxProgress(@NotNull RunicAltarRecipe recipe) {
        return recipe.getManaUsage();
    }

    private boolean checkOutputSlots(){
        for (int i = FIRST_OUTPUT_SLOT; i < LAST_OUTPUT_SLOT + 1; i++){
            if (!this.inventory.getStackInSlot(i).isEmpty()){
                return true;
            }
        }

        return false;
    }

    public int getMaxManaPerTick() {
        return MAX_MANA_PER_TICK * settingPattern.getConfigInt("craftTime");
    }

    public boolean isSlotUsedCurrently(int slot) {
        return this.slotsUsed.contains(slot);
    }

    public void load(@Nonnull CompoundTag nbt) {
        super.load(nbt);
        this.slotsUsed.clear();
        this.slotsUsed.addAll(Arrays.stream(nbt.getIntArray("slotsUsed")).boxed().toList());

        this.setChanged();
        this.setDispatchable();
    }

    public void saveAdditional(@Nonnull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putIntArray("slotsUsed", this.slotsUsed);
    }

    public void handleUpdateTag(CompoundTag nbt) {
        super.handleUpdateTag(nbt);
        if (this.level == null || this.level.isClientSide) {
            this.slotsUsed.clear();
            this.slotsUsed.addAll(Arrays.stream(nbt.getIntArray("slotsUsed")).boxed().toList());
        }
    }

    @Nonnull
    public CompoundTag getUpdateTag() {
        if (this.level != null && this.level.isClientSide) {
            return super.getUpdateTag();
        } else {
            CompoundTag nbt = super.getUpdateTag();

            nbt.putIntArray("slotsUsed", this.slotsUsed);

            return nbt;
        }
    }

    public BlockEntity getBlockEntity() {
        return this;
    }

    public void drops(){
        IAdvancedItemHandlerModifiable inventory = this.getInventory().getUnrestricted();
        for (int i = 0; i < inventory.getSlots(); i++){
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.isEmpty()) continue;
            ItemEntity ie = new ItemEntity(this.level, (double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.7, (double)this.worldPosition.getZ() + 0.5, itemStack.copy());
            this.level.addFreshEntity(ie);
        }
    }

    //endregion


    //region AE INTEGRATION
    private boolean setChangedQueued;

    private final IManagedGridNode mainNode = this.createMainNode().setVisualRepresentation(this.getItemFromBlockEntity()).setInWorldNode(true).setTagName("proxy");


    protected IManagedGridNode createMainNode() {
        return GridHelper.createManagedNode(this, BlockEntityNodeListener.INSTANCE);
    }

    protected Item getItemFromBlockEntity() {
        BlockEntity blockEntity = this.getBlockEntity();
        if (blockEntity instanceof BlockEntityRunicAltarBase){
            return ModBlocks.baseRunicAltar.asItem();
        }
        else if (blockEntity instanceof BlockEntityRunicAltarUpgraded){
            return ModBlocks.upgradedRunicAltar.asItem();
        }
        else if (blockEntity instanceof BlockEntityRunicAltarAdvanced){
            return ModBlocks.advancedRunicAltar.asItem();
        }
        else {
            return ModBlocks.ultimateRunicAltar.asItem();
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        if (this.getMainNode() != null) {
            this.getMainNode().destroy();
        }
    }

    private Object setChangedAtEndOfTick(Level level) {
        this.setChanged();
        this.setChangedQueued = false;
        return null;
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
                    TickHandler.instance().addCallable((LevelAccessor)null, this::setChangedAtEndOfTick);
                    this.setChangedQueued = true;
                }
            }
        }
    }

    private void exportResultsItemsME(){
        for (int slot = FIRST_OUTPUT_SLOT; slot <= LAST_OUTPUT_SLOT; slot++) {
            ItemStack stackInSlot = this.inventory.getStackInSlot(slot);

            if (!stackInSlot.isEmpty()) {
                int getCountExport = Math.toIntExact(this.getMainNode().getGrid().getStorageService().getInventory().insert(AEItemKey.of(stackInSlot), stackInSlot.getCount(), Actionable.MODULATE, IActionSource.empty()));

                if (getCountExport > 0) {
                    stackInSlot.shrink(getCountExport);
                    this.inventory.setStackInSlot(slot, stackInSlot);
                }
            }
        }
    }

    @Override
    public AECableType getCableConnectionType(Direction dir) {
        return AECableType.SMART;
    }

    //endregion

}