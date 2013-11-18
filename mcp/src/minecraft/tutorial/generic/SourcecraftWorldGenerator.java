package tutorial.generic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class SourcecraftWorldGenerator implements IWorldGenerator {
	
		private int count = 0;


		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
		{
			
			//readBox(world, 0, 0, 0, "light-new.txt"); 
			
		}

		public boolean readBox(World world, int bx, int by, int bz, String filename)
        {
        	try
        	{
				FileInputStream fstream = new FileInputStream(filename);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				int lines = 0;
				while ((strLine = br.readLine()) != null)   
				{
					String[] args = strLine.split(" ");
					int blockID = Integer.parseInt(args[0]);
					int x = Integer.parseInt(args[1]);
					int y = Integer.parseInt(args[2]);
					int z = Integer.parseInt(args[3]);
					world.setBlock(x+bx, y+by+4, z+bz, blockID, 0, 2);
					System.out.println(String.format("Setting block %d: %d, %d, %d",lines, x, y, z));
					lines++;
				}
				
				in.close();
			}
    		catch (Exception e)
    		{
    			System.err.println("Error: " + e.getMessage());
			}
        	
    	return true;
    	
        }
}