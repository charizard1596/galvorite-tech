package mod.charizard1596.galvorite.items.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.Item.Properties;

public class galvoriteAxeItem extends AxeItem {
    public galvoriteAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (Screen.hasShiftDown()){
            tooltip.add(new TranslationTextComponent("tooltip.galvorite.galvorite_axe"));
        } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
    }
}
