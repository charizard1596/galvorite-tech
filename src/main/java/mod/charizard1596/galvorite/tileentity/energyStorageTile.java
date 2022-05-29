package mod.charizard1596.galvorite.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class energyStorageTile extends energyTile {
    public energyStorageTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }
    public energyStorageTile(){
        this(modTileEntities.ENERGY_STORAGE_TILE.get());
    }

    @Override
    public int getMaxEnergy() {
        return 100;
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        return super.save(p_189515_1_);
    }
}