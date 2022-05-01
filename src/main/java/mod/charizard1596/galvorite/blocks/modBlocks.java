package mod.charizard1596.galvorite.blocks;

import mod.charizard1596.galvorite.galvorite;
import mod.charizard1596.galvorite.blocks.custom.recyclerBlock;
import mod.charizard1596.galvorite.items.modItemGroup;
import mod.charizard1596.galvorite.items.modItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class modBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, galvorite.MOD_ID);
    public static final RegistryObject<Block> GALVORITE_ORE = registerBlock("galvorite_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(4).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(20f)));
    public static final RegistryObject<Block> GALVORITE_BLOCK = registerBlock("galvorite_block", () -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(15f)));
    public static final RegistryObject<Block> RECYCLER = registerBlock("recycler", () -> new recyclerBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F)));
    public static final RegistryObject<Block> DIAMOND_TROPHY = registerBlock("diamond_trophy", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.DIAMOND).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> NETHERITE_TROPHY = registerBlock("netherite_trophy", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.NETHERITE_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> GALVORITE_TROPHY = registerBlock("galvorite_trophy", () -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(15f).noOcclusion()));



    private static <T extends Block>RegistryObject<T> registerBlock (String name, Supplier<T> block) {
      RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
      return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        modItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(modItemGroup.galvoriteGroup)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
