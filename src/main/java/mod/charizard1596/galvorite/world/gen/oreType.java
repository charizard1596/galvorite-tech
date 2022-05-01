package mod.charizard1596.galvorite.world.gen;

import mod.charizard1596.galvorite.blocks.modBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum oreType {
    GALVORITE(Lazy.of(modBlocks.GALVORITE_ORE),3,1,50, 2);
    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsPerChunk;

    oreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public static oreType get(Block block) {
        for (oreType ore : values()){
            if (block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}
