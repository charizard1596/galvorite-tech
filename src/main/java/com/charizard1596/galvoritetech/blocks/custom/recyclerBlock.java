package com.charizard1596.galvoritetech.blocks.custom;
import com.charizard1596.galvoritetech.GalvoriteTech;
import com.charizard1596.galvoritetech.blocks.modBlocks;
import com.charizard1596.galvoritetech.items.modItems;
import com.charizard1596.galvoritetech.util.modTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
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
import net.minecraft.tags.ItemTags;
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


        if (!worldIn.isClientSide) {
            if (!player.isCrouching()) {
                ItemStack handItemStack = player.getItemInHand(handIn);
                Item handItem = player.getItemInHand(handIn).getItem();
                if (worldIn.getBlockState(pos).getValue(BlockStateProperties.LIT)){
                if (modTags.Items.RECYCLABLE_DIAMOND.contains(handItem) || modTags.Items.RECYCLABLE_GOLD.contains(handItem) || modTags.Items.RECYCLABLE_IRON.contains(handItem) || modTags.Items.RECYCLABLE_LEATHER.contains(handItem) || modTags.Items.RECYCLABLE_GALVORITE.contains(handItem) || modTags.Items.RECYCLABLE_CHAIN.contains(handItem) || modTags.Items.RECYCLABLE_TURTLE.contains(handItem) || modTags.Items.RECYCLABLE_NETHERITE.contains(handItem)) {
                    player.getItemInHand(handIn).shrink(1);
                    if (modTags.Items.RECYCLABLE_DIAMOND.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.DIAMOND));
                            if (handItem != Items.DIAMOND_SHOVEL) {
                                spawnItem(worldIn, pos, new ItemStack(Items.DIAMOND));
                            }
                        } else spawnItem(worldIn,pos,new ItemStack(Items.DIAMOND));
                    }
                    if (modTags.Items.RECYCLABLE_NETHERITE.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.NETHERITE_SCRAP));
                            spawnItem(worldIn, pos, new ItemStack(Items.NETHERITE_SCRAP));
                        } else spawnItem(worldIn,pos,new ItemStack(Items.NETHERITE_SCRAP));
                    }
                    if (modTags.Items.RECYCLABLE_IRON.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.IRON_INGOT));
                            if (handItem != Items.IRON_SHOVEL) {
                                spawnItem(worldIn, pos, new ItemStack(Items.IRON_INGOT));
                            }
                        } else spawnItem(worldIn,pos,new ItemStack(Items.IRON_INGOT));
                    }
                    if (modTags.Items.RECYCLABLE_CHAIN.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.CHAIN));
                            spawnItem(worldIn, pos, new ItemStack(Items.CHAIN));
                        } else spawnItem(worldIn,pos,new ItemStack(Items.CHAIN));
                    }
                    if (modTags.Items.RECYCLABLE_GOLD.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.GOLD_INGOT));
                            if (handItem != Items.GOLDEN_SHOVEL) {
                                spawnItem(worldIn, pos, new ItemStack(Items.GOLD_INGOT));
                            }
                        } else spawnItem(worldIn,pos,new ItemStack(Items.GOLD_INGOT));
                    }
                    if (modTags.Items.RECYCLABLE_GALVORITE.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(modItems.RAW_GALVORITE.get()));
                            spawnItem(worldIn, pos, new ItemStack(modItems.RAW_GALVORITE.get()));
                        } else spawnItem(worldIn,pos,new ItemStack(modItems.RAW_GALVORITE.get()));
                    }
                    if (modTags.Items.RECYCLABLE_LEATHER.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.LEATHER));
                                spawnItem(worldIn, pos, new ItemStack(Items.LEATHER));
                        } else spawnItem(worldIn,pos,new ItemStack(Items.LEATHER));
                    }
                    if (modTags.Items.RECYCLABLE_TURTLE.contains(handItem)) {
                        if (Math.random() > 0.5) {
                            spawnItem(worldIn,pos,new ItemStack(Items.SCUTE));
                            spawnItem(worldIn,pos,new ItemStack(Items.SCUTE));
                        } else spawnItem(worldIn,pos,new ItemStack(Items.SCUTE));
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
                    if (world.getBlockState(pos.below()).getValue(LIT)) {
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
