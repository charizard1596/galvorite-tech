package mod.charizard1596.galvorite.blocks.custom;

import mod.charizard1596.galvorite.blocks.modBlocks;
import mod.charizard1596.galvorite.tileentity.wireTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.meta.When;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

import net.minecraft.block.AbstractBlock.Properties;

public class wireBlock extends SixWayBlock {


    public wireBlock(Properties properties) {
        super(0.3125F, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getNearestLookingDirection();
        if (direction.getAxis() == Direction.Axis.Y){
            if (context.getPlayer().isCrouching()) {
                direction=direction.getOpposite();
            }
        }
        BlockState state = this.defaultBlockState().setValue(NORTH, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(WEST, false);
        return state.setValue(FACING, direction);
    }

    @Override
    public void onPlace(BlockState vstate, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(vstate, worldIn, pos, oldState, isMoving);
        BlockState state = vstate;
        worldIn.setBlockAndUpdate(pos,this.updateShape(this.updateShape(state,state.getValue(FACING).getOpposite(),worldIn.getBlockState(pos.relative(state.getValue(FACING).getOpposite())),worldIn,pos,pos.relative(state.getValue(FACING).getOpposite())),state.getValue(FACING),worldIn.getBlockState(pos.relative(state.getValue(FACING))),worldIn,pos,pos.relative(state.getValue(FACING))));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
            boolean isGood = facingState.is(modBlocks.WIRE.get()) || facingState.is(modBlocks.ENERGY_STORAGE.get()) || facingState.is(modBlocks.RECYCLER.get()) || facingState.is(modBlocks.INFINITE_ENERGY_BLOCK.get());
            /*
            FOR SIDE NOT FRONT/BACK
            If block beside me is wire AND is pointing at me then set side to true.
            FOR SIDE FRONT/BACK
            If block in front of me is a good block then set side to true
             */

            if (state.getValue(FACING)==facing||state.getValue(FACING).getOpposite()==facing) {
                //side is front or back
                return state.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(isGood));
            } else {
                //side is not front or back
                if (facingState.is(modBlocks.WIRE.get())) {
                    if (facingState.getValue(FACING)==facing.getOpposite()||facingState.getValue(FACING)==facing) {
                        return state.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(true));
                    }
                    return state.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(false));
                }
            }
        return state.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(false));
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

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}