package mod.charizard1596.galvorite.items.custom;

import mod.charizard1596.galvorite.items.modItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.Item.Properties;

public class shimmerHelmItem extends ArmorItem implements IArmorVanishable {

    public shimmerHelmItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (stack.getItem()==modItems.SHIMMER_HELM_SPEED.get() || stack.getItem()==modItems.SHIMMER_HELM_SPEED.get() || stack.getItem()==modItems.SHIMMER_HELM_JUMP_BOOST.get() || stack.getItem()==modItems.SHIMMER_HELM_HASTE.get() || stack.getItem()==modItems.SHIMMER_HELM_RESISTANCE.get() || stack.getItem()==modItems.SHIMMER_HELM_REGENERATION.get()) {
            return enchantment == Enchantments.RESPIRATION || enchantment == Enchantments.THORNS || enchantment == Enchantments.UNBREAKING || enchantment == Enchantments.MENDING || enchantment == Enchantments.BINDING_CURSE || enchantment == Enchantments.VANISHING_CURSE || enchantment == Enchantments.AQUA_AFFINITY;
        } else return true;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if (stack.getItem()==modItems.SHIMMER_HELM_SPEED.get() || stack.getItem()==modItems.SHIMMER_HELM_SPEED.get() || stack.getItem()==modItems.SHIMMER_HELM_JUMP_BOOST.get() || stack.getItem()==modItems.SHIMMER_HELM_HASTE.get() || stack.getItem()==modItems.SHIMMER_HELM_RESISTANCE.get() || stack.getItem()==modItems.SHIMMER_HELM_REGENERATION.get()) {
            return EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.AQUA_AFFINITY) || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.BINDING_CURSE) || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.VANISHING_CURSE) || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.MENDING) || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.UNBREAKING) || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.THORNS) || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.RESPIRATION);
        } else return true;}

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if(stack.getItem()==(modItems.SHIMMER_HELM_SPEED.get())) {
            return "galvorite:textures/models/armor/shimmer_helm_speed.png";
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_STRENGTH.get())) {
            return "galvorite:textures/models/armor/shimmer_helm_strength.png";
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_HASTE.get())) {
            return "galvorite:textures/models/armor/shimmer_helm_haste.png";
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_JUMP_BOOST.get())) {
            return "galvorite:textures/models/armor/shimmer_helm_jump_boost.png";
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_RESISTANCE.get())) {
            return "galvorite:textures/models/armor/shimmer_helm_resistance.png";
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_REGENERATION.get())) {
            return "galvorite:textures/models/armor/shimmer_helm_regeneration.png";
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM.get())) {
            return "galvorite:textures/models/armor/shimmer_helm.png";
        }
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(stack.getItem()==(modItems.SHIMMER_HELM_SPEED.get())) {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm_speed"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_STRENGTH.get())) {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm_strength"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_HASTE.get())) {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm_haste"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_JUMP_BOOST.get())) {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm_jump_boost"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        } else
        if (stack.getItem()==(modItems.SHIMMER_HELM_RESISTANCE.get())) {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm_resistance"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        } else
        if(stack.getItem()==(modItems.SHIMMER_HELM_REGENERATION.get())) {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm_regeneration"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        } else {
            if (Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("tooltip.galvorite.shimmer_helm"));
            } else tooltip.add(new TranslationTextComponent("tooltip.galvorite.hold_shift"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        if (!world.isRemote()){
            player.removePotionEffect(Effects.BLINDNESS);
            player.removePotionEffect(Effects.SLOWNESS);
            player.removePotionEffect(Effects.LEVITATION);
            player.removePotionEffect(Effects.MINING_FATIGUE);
            player.removePotionEffect(Effects.NAUSEA);
            player.removePotionEffect(Effects.HUNGER);
            player.removePotionEffect(Effects.WEAKNESS);
            player.removePotionEffect(Effects.POISON);
            player.removePotionEffect(Effects.WITHER);
            player.removePotionEffect(Effects.UNLUCK);

            if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_SPEED.get())) {
                player.addPotionEffect(new EffectInstance(Effects.SPEED,4,0));
            } else
            if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_STRENGTH.get())) {
                player.addPotionEffect(new EffectInstance(Effects.STRENGTH,4,0));
            } else
            if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_HASTE.get())) {
                player.addPotionEffect(new EffectInstance(Effects.HASTE,4,0));
            } else
            if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_JUMP_BOOST.get())) {
                player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST,4,0));
            } else
            if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_RESISTANCE.get())) {
                player.addPotionEffect(new EffectInstance(Effects.RESISTANCE,4,0));
            } else
            if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_REGENERATION.get())) {
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION,4,0));
            }
        }
    }
}
