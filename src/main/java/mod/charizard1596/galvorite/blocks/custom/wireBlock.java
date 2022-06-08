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
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)));
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
        BlockState state = this.getDefaultState().with(NORTH, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(WEST, false);
        return state.with(FACING, direction);
    }

    @Override
    public void onBlockAdded(BlockState vstate, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(vstate, worldIn, pos, oldState, isMoving);
        BlockState state = vstate;
        worldIn.setBlockState(pos,this.updatePostPlacement(this.updatePostPlacement(state,state.get(FACING).getOpposite(),worldIn.getBlockState(pos.offset(state.get(FACING).getOpposite())),worldIn,pos,pos.offset(state.get(FACING).getOpposite())),state.get(FACING),worldIn.getBlockState(pos.offset(state.get(FACING))),worldIn,pos,pos.offset(state.get(FACING))));
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
            boolean isGood = facingState.matchesBlock(modBlocks.WIRE.get()) || facingState.matchesBlock(modBlocks.ENERGY_STORAGE.get()) || facingState.matchesBlock(modBlocks.INFINITE_ENERGY_BLOCK.get());
            /*
            FOR SIDE NOT FRONT/BACK
            If block beside me is wire AND is pointing at me then set side to true.
            FOR SIDE FRONT/BACK
            If block in front of me is a good block then set side to true
             */

            if (state.get(FACING)==facing||state.get(FACING).getOpposite()==facing) {
                //side is front or back
                return state.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(isGood));
            } else {
                //side is not front or back
                if (facingState.matchesBlock(modBlocks.WIRE.get())) {
                    if (facingState.get(FACING)==facing.getOpposite()||facingState.get(FACING)==facing) {
                        return state.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(true));
                    }
                    return state.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(false));
                }
            }
        return state.with(FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(false));
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

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}