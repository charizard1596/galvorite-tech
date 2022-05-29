package mod.charizard1596.galvorite.items.custom;

import mod.charizard1596.galvorite.blocks.modBlocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.world.World;

import static net.minecraft.state.properties.BlockStateProperties.FACING;

public class wrenchItem extends Item {

    public wrenchItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (!context.getLevel().isClientSide())
        {
            if (context.getLevel().getBlockState(context.getClickedPos()).getBlock() == modBlocks.WIRE.get())
            {
                if (context.getPlayer().isCrouching())
                {
                    context.getLevel().setBlockAndUpdate(context.getClickedPos(), context.getLevel().getBlockState(context.getClickedPos()).rotate(context.getLevel(),context.getClickedPos(),Rotation.CLOCKWISE_90));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
