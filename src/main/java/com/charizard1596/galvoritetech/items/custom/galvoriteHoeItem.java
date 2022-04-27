package com.charizard1596.galvoritetech.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class galvoriteHoeItem extends HoeItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID ATTACK_REACH_MODIFIER = UUID.fromString("6d7cd010-d2e5-4b0e-902a-f08a469fd77b");
    public galvoriteHoeItem(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
        this.defaultModifiers=super.getDefaultAttributeModifiers(EquipmentSlotType.MAINHAND);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slotType,ItemStack stack) {
        if (slotType == EquipmentSlotType.MAINHAND) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getAttributeModifiers(slotType,stack));
            builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", 1.0D , AttributeModifier.Operation.ADDITION));
        return builder.build(); } else return super.getAttributeModifiers(slotType,stack);
    }


}
