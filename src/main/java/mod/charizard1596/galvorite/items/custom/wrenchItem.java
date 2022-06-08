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

import net.minecraft.item.Item.Properties;

public class wrenchItem extends Item {

    public wrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote())
        {
            if (context.getWorld().getBlockState(context.getPos()).getBlock() == modBlocks.WIRE.get())
            {
                if (context.getPlayer().isCrouching())
                {
                    context.getWorld().setBlockState(context.getPos(), context.getWorld().getBlockState(context.getPos()).rotate(context.getWorld(),context.getPos(),Rotation.CLOCKWISE_90));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
