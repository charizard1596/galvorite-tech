package mod.charizard1596.galvorite.items.custom;

import mod.charizard1596.galvorite.galvorite;
import mod.charizard1596.galvorite.items.modItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = galvorite.MOD_ID)
public class jetpackItem extends energyArmorItem implements IArmorVanishable {
    public jetpackItem(IArmorMaterial pMaterial, EquipmentSlotType pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }
    public Boolean keyDown = false;
    @Override
    public int getMaxEnergy() {
        return 10000;
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity && event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem()==modItems.JETPACK.get() && ((jetpackItem) event.getEntityLiving().getItemBySlot(EquipmentSlotType.CHEST).getItem()).getEnergy()>0) {
            Vector3d v = event.getEntityLiving().getDeltaMovement();
            event.getEntityLiving().setDeltaMovement(v.x,0,v.z);
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (keyDown) {
            if (this.energy > 0) {
                this.energy--;
                Vector3d v = player.getDeltaMovement();
                player.setDeltaMovement(v.x,0.2,v.z);
                player.fallDistance = 0;
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        if (Screen.hasShiftDown()){
            p_77624_3_.add(new TranslationTextComponent("tooltip.galvorite.jetpack"));
        } else p_77624_3_.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        p_77624_3_.add(new StringTextComponent("Energy: "+getEnergy()+"/"+getMaxEnergy()).withStyle(TextFormatting.DARK_RED));
    }
}
