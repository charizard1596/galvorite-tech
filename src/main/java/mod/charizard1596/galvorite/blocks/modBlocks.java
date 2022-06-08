package mod.charizard1596.galvorite.blocks;

import mod.charizard1596.galvorite.blocks.custom.energyStorageBlock;
import mod.charizard1596.galvorite.blocks.custom.infiniteEnergyBlock;
import mod.charizard1596.galvorite.blocks.custom.wireBlock;
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
    public static final RegistryObject<Block> GALVORITE_ORE = registerBlock("galvorite_ore", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(20f)));
    public static final RegistryObject<Block> GALVORITE_BLOCK = registerBlock("galvorite_block", () -> new Block(AbstractBlock.Properties.create(Material.ANVIL).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(15f)));
    public static final RegistryObject<Block> RECYCLER = registerBlock("recycler", () -> new recyclerBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.5F)));
    public static final RegistryObject<Block> DIAMOND_TROPHY = registerBlock("diamond_trophy", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.DIAMOND).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
    public static final RegistryObject<Block> NETHERITE_TROPHY = registerBlock("netherite_trophy", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).setRequiresTool().hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.NETHERITE).notSolid()));
    public static final RegistryObject<Block> GALVORITE_TROPHY = registerBlock("galvorite_trophy", () -> new Block(AbstractBlock.Properties.create(Material.ANVIL).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(15f).notSolid()));
    public static final RegistryObject<Block> WIRE = registerBlock("wire", () -> new wireBlock(AbstractBlock.Properties.create(Material.WOOL).harvestTool(ToolType.AXE).hardnessAndResistance(3f).notSolid()));
    public static final RegistryObject<Block> ENERGY_STORAGE = registerBlock("energy_storage", () -> new energyStorageBlock(AbstractBlock.Properties.create(Material.SPONGE)));
    public static final RegistryObject<Block> INFINITE_ENERGY_BLOCK = registerBlock("infinite_energy_block", () -> new infiniteEnergyBlock(AbstractBlock.Properties.create(Material.SPONGE)));
    private static <T extends Block>RegistryObject<T> registerBlock (String name, Supplier<T> block) {
      RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
      return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        modItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(modItemGroup.galvoriteGroup)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
