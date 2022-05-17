package mod.charizard1596.galvorite.blocks.custom;
import java.util.Random;

import mod.charizard1596.galvorite.tileentity.wireTile;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class wireBlock extends SixWayBlock {
    public wireBlock(AbstractBlock.Properties p_i48428_1_) {
        super(0.3125F, p_i48428_1_);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return this.getStateForPlacement(p_196258_1_.getLevel(), p_196258_1_.getClickedPos());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new wireTile();
    }

    public BlockState getStateForPlacement(IBlockReader p_196497_1_, BlockPos p_196497_2_) {
        Block block = p_196497_1_.getBlockState(p_196497_2_.below()).getBlock();
        Block block1 = p_196497_1_.getBlockState(p_196497_2_.above()).getBlock();
        Block block2 = p_196497_1_.getBlockState(p_196497_2_.north()).getBlock();
        Block block3 = p_196497_1_.getBlockState(p_196497_2_.east()).getBlock();
        Block block4 = p_196497_1_.getBlockState(p_196497_2_.south()).getBlock();
        Block block5 = p_196497_1_.getBlockState(p_196497_2_.west()).getBlock();
        return this.defaultBlockState().setValue(DOWN, Boolean.valueOf(block == this || block == Blocks.CHORUS_FLOWER || block == Blocks.END_STONE)).setValue(UP, Boolean.valueOf(block1 == this || block1 == Blocks.CHORUS_FLOWER)).setValue(NORTH, Boolean.valueOf(block2 == this || block2 == Blocks.CHORUS_FLOWER)).setValue(EAST, Boolean.valueOf(block3 == this || block3 == Blocks.CHORUS_FLOWER)).setValue(SOUTH, Boolean.valueOf(block4 == this || block4 == Blocks.CHORUS_FLOWER)).setValue(WEST, Boolean.valueOf(block5 == this || block5 == Blocks.CHORUS_FLOWER));
    }

    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
            boolean flag = p_196271_3_.getBlock() == this;
            return p_196271_1_.setValue(PROPERTY_BY_DIRECTION.get(p_196271_2_), Boolean.valueOf(flag));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}