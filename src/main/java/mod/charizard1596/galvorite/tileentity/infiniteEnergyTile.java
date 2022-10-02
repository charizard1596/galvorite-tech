package mod.charizard1596.galvorite.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

public class infiniteEnergyTile extends energyTile implements ITickableTileEntity {
    public infiniteEnergyTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public infiniteEnergyTile(){
        this(modTileEntities.INFINITE_ENERGY_TILE.get());
    }

    @Override
    public int getMaxEnergy() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void tick() {
        this.energy = Integer.MAX_VALUE;
        for (Direction dir : Direction.values()){
            doEnergyInsert(1,this.getBlockPos().relative(dir));
        }
    }
}
