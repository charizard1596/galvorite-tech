package mod.charizard1596.galvorite.tileentity;

import mod.charizard1596.galvorite.data.recipes.RecyclerRecipe;
import mod.charizard1596.galvorite.data.recipes.modRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Optional;

public class recyclerTile extends energyTile implements ITickableTileEntity {

    //private final ItemStackHandler itemHandler = createHandler();

    protected ItemStackHandler inputSlot;
    protected ItemStackHandler outputSlot;

    private final LazyOptional<IItemHandler> inputSlots = LazyOptional.of(() -> inputSlot);
    private final LazyOptional<IItemHandler> outputSlots = LazyOptional.of(() -> outputSlot);
    private final LazyOptional<IItemHandler> everything = LazyOptional.of(() -> {
        ArrayList<ItemStackHandler> allSlots = new ArrayList<ItemStackHandler>();
        allSlots.add(inputSlot);
        allSlots.add(outputSlot);
        return new CombinedInvWrapper(allSlots.toArray(new ItemStackHandler[0]));
    });


    //private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public recyclerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public recyclerTile() {
        this(modTileEntities.RECYCLER_TILE.get());
        inputSlot = new ItemStackHandler();
        outputSlot = new ItemStackHandler();
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        inputSlot.deserializeNBT(nbt.getCompound("inputslot"));
        inputSlot.deserializeNBT(nbt.getCompound("outputslottop"));
        inputSlot.deserializeNBT(nbt.getCompound("outputslotmid"));
        inputSlot.deserializeNBT(nbt.getCompound("outputslotbottom"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inputslot", inputSlot.serializeNBT());
        compound.put("outputslot", outputSlot.serializeNBT());
        return super.save(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(side == null){
                return everything.cast();
            }
            if(side == Direction.UP) {
                return inputSlots.cast();
            }
            return outputSlots.cast();
        }
        return super.getCapability(cap, side);
    }

    public void craft() {
        Inventory inv = new Inventory(2);
        inv.setItem(0, inputSlot.getStackInSlot(0));
        inv.setItem(1, outputSlot.getStackInSlot(0));


        Optional<RecyclerRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(modRecipeTypes.RECYCLER_RECIPE, inv, level);

        recipe.ifPresent(iRecipe -> {
            if (this.energy >= 5) {
                subtractEnergy(5);
                ItemStack output = iRecipe.getResultItem();
                craftTheItem(output);
                setChanged();
            }
        });
    }

    private void craftTheItem(ItemStack output) {
        inputSlot.extractItem(0, 1, false);
        outputSlot.insertItem(0, output, false);

    }

    @Override
    public void tick() {
        if (level.isClientSide)
            return;
        craft();
        if (this.energy > 0) {
            level.setBlockAndUpdate(this.getBlockPos(),this.getBlockState().setValue(BlockStateProperties.LIT,true));
        }
        if (this.energy <= 0 && this.getBlockState().getValue(BlockStateProperties.LIT)) {
            level.setBlockAndUpdate(this.getBlockPos(),this.getBlockState().setValue(BlockStateProperties.LIT,false));
        }
    }

    @Override
    public int getMaxEnergy() {
        return 60;
    }

}
