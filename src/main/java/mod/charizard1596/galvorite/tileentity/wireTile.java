package mod.charizard1596.galvorite.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Random;

import static net.minecraft.state.properties.BlockStateProperties.*;

public class wireTile extends energyTile implements ITickableTileEntity {
    public wireTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public wireTile(){
        this(modTileEntities.WIRE_TILE.get());
    }

    @Override
    public int getMaxEnergy() {
        return 20;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        return super.save(compound);
    }

    @Override
    public void tick() {
        if (this.energy > 0){
            doEnergyInsert(1,this.getBlockPos().relative(this.getBlockState().getValue(FACING)));
        }
        for (Direction dir : Direction.values()){
            if (dir != this.getBlockState().getValue(FACING)) {
                doEnergyExtract(1, this.getBlockPos().relative(dir));
            }
        }
    }
}
