package mod.charizard1596.galvorite.data.recipes;

import mod.charizard1596.galvorite.galvorite;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class modRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, galvorite.MOD_ID);

    public static final RegistryObject<RecyclerRecipe.Serializer> RECYCLER_SERIALIZER
            = RECIPE_SERIALIZER.register("recycler", RecyclerRecipe.Serializer::new);

    public static IRecipeType<RecyclerRecipe> RECYCLER_RECIPE
            = new RecyclerRecipe.RecyclerRecipeType();


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, RecyclerRecipe.TYPE_ID, RECYCLER_RECIPE);
    }
}
