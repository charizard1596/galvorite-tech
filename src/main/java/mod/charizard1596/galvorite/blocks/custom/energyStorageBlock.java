package mod.charizard1596.galvorite.blocks.custom;

import mod.charizard1596.galvorite.tileentity.energyStorageTile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class energyStorageBlock extends Block {
    public energyStorageBlock(Properties properties) {
        super(properties);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new energyStorageTile();
    }
    private energyStorageTile getTE(World world, BlockPos pos) {
        return (energyStorageTile) world.getTileEntity(pos);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            int energy = getTE(worldIn,pos).getEnergy();
            StringTextComponent message = new StringTextComponent(Integer.toString(energy));
            message.getStyle().applyFormatting(TextFormatting.GREEN);
            player.sendMessage(message,player.getUniqueID());
        }
        return ActionResultType.SUCCESS;
    }
}
