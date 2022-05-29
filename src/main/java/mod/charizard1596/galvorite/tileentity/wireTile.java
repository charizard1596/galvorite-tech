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
    public wireTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }
    public wireTile(){
        this(modTileEntities.WIRE_TILE.get());
    }

    @Override
    public int getMaxEnergy() {
        return 20;
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        return super.save(p_189515_1_);
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
