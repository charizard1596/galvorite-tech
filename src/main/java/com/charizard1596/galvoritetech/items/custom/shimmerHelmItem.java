package com.charizard1596.galvoritetech.items.custom;

import com.charizard1596.galvoritetech.GalvoriteTech;
import com.charizard1596.galvoritetech.items.modItems;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class shimmerHelmItem extends ArmorItem implements IArmorVanishable {

    public shimmerHelmItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        super.onArmorTick(stack, world, player);
        if (!world.isClientSide()){
            if(player.getItemBySlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_SPEED.get())) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,1));
            } else
            if(player.getItemBySlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_STRENGTH.get())) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,1));
            } else
            if(player.getItemBySlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_HASTE.get())) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,1));
            } else
            if(player.getItemBySlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_JUMP_BOOST.get())) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,1));
            } else
            if(player.getItemBySlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_RESISTANCE.get())) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,1));
            } else
            if(player.getItemBySlot(EquipmentSlotType.HEAD).getItem()==(modItems.SHIMMER_HELM_REGENERATION.get())) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED,10,1));
            }
        }
    }
}
