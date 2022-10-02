package mod.charizard1596.galvorite.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class energyTile extends TileEntity {
    public energyTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    int energy = 0;

    public void addEnergy(int amount, BlockPos sendPos) {
        this.energy += amount;
        this.lastReceived = sendPos;
    }

    public int getEnergy() {
        return energy;
    }

    public void doEnergyInsert(int amount, BlockPos sendto)
    {
        if (this.getLevel().getBlockState(sendto).hasTileEntity()) {
            if (sendto != lastReceived) {
                if (this.getLevel().getBlockEntity(sendto) instanceof energyTile) {
                    energyTile insert = (energyTile) this.getLevel().getBlockEntity(sendto);
                    if (insert.getEnergy() < insert.getMaxEnergy()&&this.energy>0)
                    {
                        this.energy-=amount;
                        ((energyTile) this.getLevel().getBlockEntity(sendto)).addEnergy(amount,this.getBlockPos());
                    }
                }
            }
        }
    }

    public void doEnergyExtract(int amount, BlockPos getFrom)
    {
        if (this.getLevel().getBlockState(getFrom).hasTileEntity()) {
                if (this.getLevel().getBlockEntity(getFrom) instanceof energyTile) {
                    energyTile extract = (energyTile) this.getLevel().getBlockEntity(getFrom);
                    if (this.getEnergy() < this.getMaxEnergy()&&extract.getEnergy()>0)
                    {
                        this.energy+=amount;
                        ((energyTile) this.getLevel().getBlockEntity(getFrom)).subtractEnergy(amount);

                    }
                }
        }
    }

    public int getMaxEnergy() {
        return 0;
    }

    public void subtractEnergy(int amount) {
        this.energy -= amount;
    }

    BlockPos lastReceived;

}
