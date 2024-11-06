package net.neolab.botanicalextramachinery.blocks.base;

import com.google.common.base.Predicates;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ic2.api.core.IC2Classic;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.network.buffer.NetworkInfo;
import ic2.api.tiles.INotifiableMachine;
import ic2.api.tiles.readers.IEUStorage;
import ic2.api.util.DirectionList;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.block.base.cache.ICache;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.misc.comparator.ComparatorNames;
import ic2.core.block.base.misc.comparator.types.base.EUComparator;
import ic2.core.block.base.tiles.BaseTileEntity;
import ic2.core.utils.helpers.NBTUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.moddingx.libx.base.tile.TickingBlock;
import org.moddingx.libx.capability.ItemCapabilities;
import org.moddingx.libx.impl.BlockEntityUpdateQueue;
import org.moddingx.libx.inventory.BaseItemStackHandler;
import org.moddingx.libx.inventory.IAdvancedItemHandlerModifiable;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.BotaniaForgeClientCapabilities;
import vazkii.botania.api.block.WandHUD;
import vazkii.botania.api.item.SparkEntity;
import vazkii.botania.api.mana.KeyLocked;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.api.mana.spark.ManaSpark;
import vazkii.botania.api.mana.spark.SparkAttachable;
import vazkii.botania.client.gui.HUDHandler;
import vazkii.botania.common.block.block_entity.mana.ThrottledPacket;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;


public abstract class ElectricBotanicalTile extends BaseTileEntity implements ManaPool, KeyLocked, SparkAttachable, ThrottledPacket, WandHUD, TickingBlock, IEnergySink, IEUStorage, IWrenchableTile {
    //IC2 PArt
    @NetworkInfo
    public int energy;
    public final BlockEntityType blockEntityType;
    @NetworkInfo
    public int maxEnergy;
    @NetworkInfo
    public int maxInput;
    @NetworkInfo(NetworkInfo.BitLevel.BIT_8)
    public int tier;
    public int baseTier;
    int fuelSlot = -1;
    protected boolean redstoneIsFull = true;
    public boolean charge_slot = false;
    CapabilityCache<INotifiableMachine> listeners;
    public boolean addedToEnet;

    //Botania Part
    public int mana;
    private final int manaCap;
    private String inputKey = "";
    private String outputKey = "";
    private final LazyOptional<IAdvancedItemHandlerModifiable> capability = this.createCap(this::getInventory);



    public ElectricBotanicalTile(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, int manaCap, int maxInput, int maxEnergy) {
        super(pos, state);
        this.blockEntityType = blockEntityType;
        this.manaCap = manaCap;
        this.listeners = new CapabilityCache(this, DirectionList.ALL, IC2Classic.NOTIFY_CAPABILITY);
        this.addedToEnet = false;
        this.maxInput = maxInput;
        this.maxEnergy = maxEnergy;
        this.energy = 0;
        this.tier = EnergyNet.INSTANCE.getTierFromPower(maxInput);
        this.baseTier = this.tier;
        this.addGuiFields(new String[]{"energy", "maxEnergy", "tier", "maxInput"});
        if (this.supportsNotify()) {
            this.addCaches(new ICache[]{this.listeners});
        }

        this.addComparator(new EUComparator("eu_storage", ComparatorNames.EU_STORAGE, this));
    }

    //IC2 Part
    public abstract boolean supportsNotify();

    public boolean canAcceptEnergy(IEnergyEmitter emitter, Direction side) {
        return true;
    }

    public int getSinkTier() {
        return this.tier;
    }

    @Override
    public int getRequestedEnergy() {
        return this.maxEnergy - this.energy;
    }

    @Override
    public int acceptEnergy(Direction side, int amount, int voltage) {
        if (amount <= this.maxEnergy && amount > 0) {
            int added = Math.min(amount, this.maxEnergy - this.energy);
            if (added > 0) {
                this.energy += added;
                this.updateGuiField("energy");
                this.handleNonTickComparators();
                if (this.needsUpdateTick() && !this.isTicking()) {
                    this.addToTick();
                }
            }
            setChanged();
            setDispatchable();
            return amount - added;
        } else {
            return 0;
        }
    }

    @Override
    public int getMaxEU() {
        return this.maxEnergy;
    }

    @Override
    public int getStoredEU() {
        return energy;
    }

    public void setMaxEnergy(int max) {
        this.maxEnergy = max;
        this.updateGuiField("maxEnergy");
    }

    public void setMaxInput(int inputEnergy) {
        this.maxInput = inputEnergy;
        this.tier = EnergyNet.INSTANCE.getTierFromPower(inputEnergy);
        this.updateGuiFields(new String[]{"tier", "maxInput"});
    }

    public void setTier(int newTier) {
        this.tier = newTier;
        this.maxInput = EnergyNet.INSTANCE.getPowerFromTier(newTier);
        this.updateGuiFields(new String[]{"tier", "maxInput"});
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    public int getMaxInput() {
        return this.maxInput;
    }

    public boolean hasEnergy(int amount) {
        return this.energy >= amount;
    }

    public int useEnergy(int used) {
        var removed = Math.min(used, this.energy);
        if (removed > 0) {
            this.energy -= removed;
            this.updateGuiField("energy");
        }
        setChanged();
        setDispatchable();
        return removed;
    }

    public int addEnergy(int amount) {
        var added = Math.min(amount, this.maxEnergy - this.energy);
        if (added > 0) {
            this.energy += added;
            this.updateGuiField("energy");
        }

        return added;
    }

    public boolean handleChargeSlot(int needed) {
        if (this.fuelSlot != -1 && this.energy < needed && this.provideEnergy()) {
            this.updateGuiField("energy");
            return true;
        } else {
            return false;
        }
    }

    public boolean provideEnergy() {
        return false;
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        if (this.isSimulating() && !this.addedToEnet) {
              this.addedToEnet = true;
            EnergyNet.INSTANCE.addTile(this);
        }

    }

    @Override
    public void onUnloaded(boolean chunk) {
        if (this.isSimulating() && this.addedToEnet) {
            this.addedToEnet = false;
            EnergyNet.INSTANCE.removeTile(this);
        }

        super.onUnloaded(chunk);
    }

    @Override
    public void setFacing(Direction facing) {
        super.setFacing(facing);
        EnergyNet.INSTANCE.updateTile(this);
    }

    @Override
    public boolean canSetFacing(Direction dir) {
        return dir != this.getFacing() && dir.getAxis().isHorizontal();
    }

    @Override
    public boolean canRemoveBlock(Player player) {
        return true;
    }

    @Override
    public double getDropRate(Player player) {
        return 0.85 - 0.05 * (double)this.baseTier;
    }

    public void notifyListeners() {

        for (Direction dir : this.listeners) {
            var machine = (INotifiableMachine) this.listeners.getHandler(dir);
            if (machine != null) {
                machine.onNotify();
            }
        }

    }


    //Botania Part
    protected LazyOptional<IAdvancedItemHandlerModifiable> createCap(Supplier<IItemHandlerModifiable> inventory) {
        return ItemCapabilities.create(inventory, this.getExtracts(inventory), this.getInserts(inventory));
    }

    protected abstract Predicate<Integer> getExtracts(Supplier<IItemHandlerModifiable> var1);

    protected BiPredicate<Integer, ItemStack> getInserts(Supplier<IItemHandlerModifiable> inventory) {
        return null;
    }

    @Nonnull
    public abstract BaseItemStackHandler getInventory();

    public abstract int getComparatorOutput();

    public void setChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.updateNeighbourForOutputSignal(this.worldPosition, this.getBlockState().getBlock());
        }

    }

    @Nonnull
    public <X> LazyOptional<X> getCapability(@Nonnull Capability<X> cap, Direction direction) {
        if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.capability.cast();
        } else {
            return this.remove || !this.actAsMana() || cap != BotaniaForgeCapabilities.MANA_RECEIVER && cap != BotaniaForgeCapabilities.SPARK_ATTACHABLE ? (LazyOptional) DistExecutor.unsafeRunForDist(() -> {
                return () -> {
                    return !this.remove && this.actAsMana() && cap == BotaniaForgeClientCapabilities.WAND_HUD ? LazyOptional.of(() -> {
                        return this;
                    }).cast() : super.getCapability(cap, direction);
                };
            }, () -> {
                return () -> {
                    return super.getCapability(cap, direction);
                };
            }) : LazyOptional.of(() -> {
                return this;
            }).cast();
        }
    }

    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);
        this.getInventory().deserializeNBT(tag.getCompound("inv"));
        this.energy = tag.getInt("energy");
        this.charge_slot = tag.getBoolean("charge_slot");
        this.mana = tag.getInt("mana");
        if (tag.contains("inputKey")) {
            this.inputKey = tag.getString("inputKey");
        }

        if (tag.contains("outputKey")) {
            this.outputKey = tag.getString("outputKey");
        }

    }

    public void saveAdditional(@Nonnull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("inv", this.getInventory().serializeNBT());
        nbt.putInt("mana", this.getCurrentMana());
        nbt.putString("inputKey", this.inputKey);
        nbt.putString("outputKey", this.outputKey);
        NBTUtils.putInt(nbt, "energy", this.energy, 0);
        NBTUtils.putBoolean(nbt, "charge_slot", this.charge_slot, false);
    }

    public void handleUpdateTag(CompoundTag tag) {
        if (this.level == null || this.level.isClientSide) {
            this.getInventory().deserializeNBT(tag.getCompound("inv"));
            this.mana = tag.getInt("mana");
            this.energy = tag.getInt("energy");
            if (tag.contains("inputKey")) {
                this.inputKey = tag.getString("inputKey");
            }

            if (tag.contains("outputKey")) {
                this.outputKey = tag.getString("outputKey");
            }

        }
    }

    @Nonnull
    public CompoundTag getUpdateTag() {
        if (this.level != null && this.level.isClientSide) {
            return super.getUpdateTag();
        } else {
            CompoundTag nbt = super.getUpdateTag();
            nbt.put("inv", this.getInventory().serializeNBT());
            nbt.putInt("mana", this.getCurrentMana());
            nbt.putInt("energy", this.getStoredEU());
            nbt.putString("inputKey", this.inputKey);
            nbt.putString("outputKey", this.outputKey);
            return nbt;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void renderHUD(PoseStack poseStack, Minecraft minecraft) {
        var block = new ItemStack(this.getBlockState().getBlock());
        var name = block.getHoverName().getString();
        int color = 4474111;
        HUDHandler.drawSimpleManaHUD(poseStack, color, this.getCurrentMana(), this.getMaxMana(), name);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(770, 771);
        RenderSystem.setShaderTexture(0, HUDHandler.manaBar);
        RenderSystem.disableBlend();
    }

    public String getInputKey() {
        return this.inputKey;
    }

    public String getOutputKey() {
        return this.outputKey;
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return this.actAsMana();
    }

    public int getAvailableSpaceForMana() {
        return !this.actAsMana() ? 0 : Math.max(Math.max(0, this.getMaxMana() - this.getCurrentMana()), 0);
    }

    public ManaSpark getAttachedSpark() {
        if (!this.actAsMana()) {
            return null;
        } else {
            List<Entity> sparks = this.level.getEntitiesOfClass(Entity.class, new AABB(this.worldPosition.above(), this.worldPosition.above().offset(1, 1, 1)), Predicates.instanceOf(SparkEntity.class));
            if (sparks.size() == 1) {
                var entity = sparks.get(0);
                return (ManaSpark)entity;
            } else {
                return null;
            }
        }
    }

    public boolean areIncomingTranfersDone() {
        return !this.actAsMana();
    }

    public boolean isFull() {
        return this.getCurrentMana() >= this.getMaxMana();
    }

    public void receiveMana(int i) {
        var old = this.getCurrentMana();
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + i, this.getMaxMana()));
        if (old != this.getCurrentMana()) {
            this.setChanged();
            this.setDispatchable();
        }

    }

    public boolean canReceiveManaFromBursts() {
        return true;
    }

    public int getCurrentMana() {
        return this.mana;
    }

    public int getMaxMana() {
        return this.manaCap;
    }

    public void addMana(int mana) {
        var oldMana = this.getCurrentMana();
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + mana, this.getMaxMana()));
        if (oldMana != this.getCurrentMana()) {
            this.setChanged();
            this.setDispatchable();
        }
    }

    public boolean isOutputtingPower() {
        return false;
    }

    public Optional<DyeColor> getColor() {
        return Optional.empty();
    }

    public void setColor(Optional<DyeColor> color) {
    }

    public void markDispatchable() {
        this.setDispatchable();
    }

    public Level getManaReceiverLevel() {
        return this.getLevel();
    }

    public BlockPos getManaReceiverPos() {
        return this.getBlockPos();
    }

    public boolean actAsMana() {
        return true;
    }

    public void setDispatchable() {
        if (this.level != null && !this.level.isClientSide) {
            BlockEntityUpdateQueue.scheduleUpdate(this.level, this.worldPosition);
        }
    }

}
