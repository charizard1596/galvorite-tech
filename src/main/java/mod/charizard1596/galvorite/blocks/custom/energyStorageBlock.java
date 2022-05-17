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

public class energyStorageBlock extends Block {
    public energyStorageBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
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
        return (energyStorageTile) world.getBlockEntity(pos);
    }
    @Override
    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (!p_225533_2_.isClientSide){
            int energy = getTE(p_225533_2_,p_225533_3_).getEnergy();
            StringTextComponent message = new StringTextComponent(Integer.toString(energy));
            message.getStyle().applyFormat(TextFormatting.GREEN);
            p_225533_4_.sendMessage(message,p_225533_4_.getUUID());
        }
        return ActionResultType.SUCCESS;
    }
}
