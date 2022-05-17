package mod.charizard1596.galvorite.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class wireTile extends TileEntity implements ITickableTileEntity {
    public wireTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }
    public wireTile(){
        this(modTileEntities.WIRE_TILE.get());
    }
    int energy = 15;

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public final int maxEnergy = 20;
    public void addEnergy(int amount, BlockPos sendPos) {
        energy += amount;
        lastReceived = sendPos;
    }

    BlockPos lastReceived;
    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        return super.save(p_189515_1_);
    }
    public void doEnergyInsert(int amount, BlockPos sendto)
    {
        if (this.getLevel().getBlockState(sendto).hasTileEntity()) {
            if (sendto != lastReceived) {
                if (this.getLevel().getBlockEntity(sendto) instanceof wireTile) {
                    wireTile insert = (wireTile) this.getLevel().getBlockEntity(sendto);
                    if (insert.energy < insert.maxEnergy&&this.energy>0)
                    {
                        this.energy--;
                        ((wireTile) this.getLevel().getBlockEntity(sendto)).addEnergy(1,this.getBlockPos());
                    }
                }
                if (this.getLevel().getBlockEntity(sendto) instanceof energyStorageTile) {
                    energyStorageTile insert = (energyStorageTile) this.getLevel().getBlockEntity(sendto);
                    if (insert.getEnergy() < insert.maxEnergy&&this.energy>0)
                    {
                        this.energy-=amount;
                        ((energyStorageTile) this.getLevel().getBlockEntity(sendto)).addEnergy(amount);
                    }
                }
            }
        }
    }
    @Override
    public void tick() {
        doEnergyInsert(1,this.getBlockPos().north());
        doEnergyInsert(1,this.getBlockPos().east());
        doEnergyInsert(1,this.getBlockPos().south());
        doEnergyInsert(1,this.getBlockPos().west());
        doEnergyInsert(1,this.getBlockPos().above());
        doEnergyInsert(1,this.getBlockPos().below());
    }
}
