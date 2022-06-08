package mod.charizard1596.galvorite.world.gen;

import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;


import java.util.Arrays;

public class modOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        spawnOreInSpecificBiome(Biomes.END_BARRENS, oreType.GALVORITE, event, Dimension.THE_END.toString());
        spawnOreInSpecificBiome(Biomes.END_MIDLANDS, oreType.GALVORITE, event, Dimension.THE_END.toString());
        spawnOreInSpecificBiome(Biomes.END_HIGHLANDS, oreType.GALVORITE, event, Dimension.THE_END.toString());

        // spawnOreInAllBiomes(oreType.FIRESTONE, event, Dimension.THE_NETHER.toString());
    }


    private static OreFeatureConfig getOverworldFeatureConfig(oreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
    }

    private static OreFeatureConfig getNetherFeatureConfig(oreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
    }

    private static OreFeatureConfig getEndFeatureConfig(oreType ore) {
        return new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE),
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
    }

    // Currently only supports vanilla Dimensions
    private static ConfiguredFeature<?, ?> makeOreFeature(oreType ore, String dimensionToSpawnIn) {
        OreFeatureConfig oreFeatureConfig = null;

        if(dimensionToSpawnIn.equals(Dimension.OVERWORLD.toString())) {
            oreFeatureConfig = getOverworldFeatureConfig(ore);
        } else if(dimensionToSpawnIn.equals(Dimension.THE_NETHER.toString())) {
            oreFeatureConfig = getNetherFeatureConfig(ore);
        } else if(dimensionToSpawnIn.equals(Dimension.THE_END.toString())) {
            oreFeatureConfig = getEndFeatureConfig(ore);
        }

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        return registerOreFeature(ore, oreFeatureConfig, configuredPlacement);
    }

    private static void spawnOreInOverworldInGivenBiomes(oreType ore, final BiomeLoadingEvent event, Biome... biomesToSpawnIn) {
        OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

        if (Arrays.stream(biomesToSpawnIn).anyMatch(b -> b.getRegistryName().equals(event.getName()))) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
        }
    }

    private static void spawnOreInOverworldInAllBiomes(oreType ore, final BiomeLoadingEvent event) {
        OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
    }

    private static void spawnOreInSpecificModBiome(Biome biomeToSpawnIn, oreType currentoreType,
                                                   final BiomeLoadingEvent event, String dimension) {
        if(event.getName().toString().contains(biomeToSpawnIn.getRegistryName().toString())) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, makeOreFeature(currentoreType, dimension));
        }
    }

    private static void spawnOreInSpecificBiome(RegistryKey<Biome> biomeToSpawnIn, oreType currentoreType,
                                                final BiomeLoadingEvent event, String dimension) {
        if(event.getName().toString().contains(biomeToSpawnIn.getLocation().toString())) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, makeOreFeature(currentoreType, dimension));
        }
    }

    private static void spawnOreInAllBiomes(oreType currentoreType, final BiomeLoadingEvent event, String dimension) {
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                makeOreFeature(currentoreType, dimension));
    }

    private static ConfiguredFeature<?, ?> registerOreFeature(oreType ore, OreFeatureConfig oreFeatureConfig,
                                                              ConfiguredPlacement configuredPlacement) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(),
                Feature.NO_SURFACE_ORE.withConfiguration(oreFeatureConfig).withPlacement(configuredPlacement)
                        .square().count(ore.getVeinsPerChunk()));
    }
}