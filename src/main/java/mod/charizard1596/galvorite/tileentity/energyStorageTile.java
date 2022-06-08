package mod.charizard1596.galvorite.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class energyStorageTile extends energyTile {
    public energyStorageTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public energyStorageTile(){
        this(modTileEntities.ENERGY_STORAGE_TILE.get());
    }

    @Override
    public int getMaxEnergy() {
        return 100;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }
}
