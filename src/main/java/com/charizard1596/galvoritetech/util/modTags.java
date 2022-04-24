package com.charizard1596.galvoritetech.util;

import com.charizard1596.galvoritetech.GalvoriteTech;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class modTags {
    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> SHIMMER_HELM = createTag("shimmer_helm");
private static Tags.IOptionalNamedTag<Item> createTag(String name) {
    return ItemTags.createOptional(new ResourceLocation(GalvoriteTech.MOD_ID,name));
        }
    }
}
