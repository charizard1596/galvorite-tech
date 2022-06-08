package mod.charizard1596.galvorite.blocks.custom;

import mod.charizard1596.galvorite.tileentity.energyStorageTile;
import mod.charizard1596.galvorite.tileentity.infiniteEnergyTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class infiniteEnergyBlock extends Block {
    public infiniteEnergyBlock(Properties properties) {
        super(properties);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new infiniteEnergyTile();
    }
}
