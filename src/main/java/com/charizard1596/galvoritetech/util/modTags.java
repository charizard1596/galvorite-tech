package com.charizard1596.galvoritetech.util;

import com.charizard1596.galvoritetech.GalvoriteTech;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class modTags {
    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> SHIMMER_HELMS = createTag("shimmer_helms");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_DIAMOND = createTag("recyclable_diamond");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_LEATHER = createTag("recyclable_leather");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_CHAIN = createTag("recyclable_chain");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_GOLD = createTag("recyclable_gold");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_IRON = createTag("recyclable_iron");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_GALVORITE = createTag("recyclable_galvorite");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_NETHERITE = createTag("recyclable_netherite");
        public static final Tags.IOptionalNamedTag<Item> RECYCLABLE_TURTLE = createTag("recyclable_turtle");


        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
    return ItemTags.createOptional(new ResourceLocation(GalvoriteTech.MOD_ID,name));
        }
    }
}
