package com.charizard1596.galvoritetech.blocks.custom;
import com.charizard1596.galvoritetech.GalvoriteTech;
import com.charizard1596.galvoritetech.blocks.modBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class recyclerBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public recyclerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

public void spawnItem(World world,BlockPos pos,ItemStack item) {
        ItemEntity itemToSpawn = new ItemEntity(world,pos.getX(),pos.getY()+1.25,pos.getZ(),item);
        world.addFreshEntity(itemToSpawn);
}
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        ArrayList<Item> recycleItems = new ArrayList<Item>();
        recycleItems.add(Items.DIAMOND_CHESTPLATE);
        if (!worldIn.isClientSide) {
            if (!player.isCrouching()) {
                if (worldIn.getBlockState(pos).getValue(BlockStateProperties.LIT)){
                if (recycleItems.contains(player.getItemInHand(handIn).getItem())) {
                    Item handItem = player.getItemInHand(handIn).getItem();
                    player.getItemInHand(handIn).shrink(1);
                    if (handItem == Items.DIAMOND_CHESTPLATE) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.DIAMOND));
                            spawnItem(worldIn,pos,new ItemStack(Items.DIAMOND));
                        } else spawnItem(worldIn,pos,new ItemStack(Items.DIAMOND));
                    }
                    } else if (!player.getItemInHand(handIn).isEmpty()) {
                        player.getItemInHand(handIn).shrink(1);
                        worldIn.explode(null, pos.getX(), pos.getY(), pos.getZ(), 4f, Explosion.Mode.BREAK);
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING, LIT);
    }

    @Override
    public void neighborChanged(BlockState blockstate, World world, BlockPos pos, Block p_220069_4_, BlockPos p_220069_5_, boolean p_220069_6_) {
        if (!world.isClientSide) {
            if (world.getBlockState(pos.below()).getBlock().is(Blocks.CAMPFIRE) || world.getBlockState(pos.below()).getBlock().is(Blocks.FIRE)) {
                if (world.getBlockState(pos.below()).getBlock().is(Blocks.CAMPFIRE)) {
                    if (world.getBlockState(pos.below()).getValue(LIT) == true) {
                        world.setBlockAndUpdate(pos, blockstate.setValue(LIT, true));
                    } else {
                        world.setBlockAndUpdate(pos, blockstate.setValue(LIT, false));
                    }
                } else world.setBlockAndUpdate(pos, blockstate.setValue(LIT, true));
            } else world.setBlockAndUpdate(pos, blockstate.setValue(LIT, false));
        }
        super.neighborChanged(blockstate, world, pos, p_220069_4_, p_220069_5_, p_220069_6_);
    }


    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState state2, boolean idk) {
        neighborChanged(state,world,pos,state.getBlock(),pos,idk);
        super.onPlace(state, world, pos, state2, idk);
    }
/*
    @Override
    public BlockState updateShape(BlockState blockstate, Direction dir, BlockState oldstate, IWorld world, BlockPos pos, BlockPos oldpos) {
        System.out.println("block placed");
        BlockState newState = blockstate.setValue(LIT,true);
            if (world.getBlockState(pos.below()).getBlock().is(Blocks.CAMPFIRE)||world.getBlockState(pos.below()).getBlock().is(Blocks.FIRE)){
                System.out.println("is campfire or fire");
                if (world.getBlockState(pos.below()).getBlock().is(Blocks.CAMPFIRE)){
                    if (world.getBlockState(pos.below()).getValue(LIT) == true){
                        System.out.println("is lit");
                        world.setBlock(pos,blockstate.setValue(LIT,true),Constants.BlockFlags.DEFAULT);
                    } else {world.setBlock(pos,blockstate.setValue(LIT,false),Constants.BlockFlags.DEFAULT);

                        System.out.println("is not lit");
                    }
                } else world.setBlock(pos,blockstate.setValue(LIT,true),Constants.BlockFlags.DEFAULT);
            } else world.setBlock(pos,blockstate.setValue(LIT,false),Constants.BlockFlags.DEFAULT);
        return world.getBlockState(pos);
    }*/
}
