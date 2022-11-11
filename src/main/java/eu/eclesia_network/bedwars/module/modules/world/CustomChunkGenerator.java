package eu.eclesia_network.bedwars.module.modules.world;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {

    int currentHeight = 50;

    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome){

        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        generator.setScale(0.005D);
        for(int x=0; x<16; x++){
            for(int z=0; z<16; z++){
                currentHeight = (int) (generator.noise(chunkX*16+x, chunkZ*16+z, 0.5D, 0.5D)*15D+50D);
                chunk.setBlock(x, currentHeight, z, Material.AIR);
            }
        }
        return chunk;
    }


}
