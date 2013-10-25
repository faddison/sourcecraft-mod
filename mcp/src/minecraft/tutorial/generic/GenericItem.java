package tutorial.generic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class GenericItem extends Item {

        public GenericItem(int id) 
        {
                super(id);
                
                // Constructor Configuration
                maxStackSize = 64;
                setCreativeTab(CreativeTabs.tabMisc);
                setUnlocalizedName("genericItem");
        }
        
        /*
         * The first coordinates are the coordinates of the block the cursor is over. 
         * Side is the side of the block the cursor is over. 
         * The second set of coordinates are the exact coordinates of where the user's cursor was pointing at. 
         * The return value is whether or not something happened.
         * 
         * (non-Javadoc)
         * @see net.minecraft.item.Item#onItemUse(net.minecraft.item.ItemStack, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int, int, float, float, float)
         */
        public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int bx, int by, int bz, int side, float px, float py, float pz)
        {
    		//return buildBox(world, 5, bx, by+1, bz, Block.stone.blockID);   
        	return readBox(world, bx, by+1, bz, "box.txt"); 
    	}
                
        public boolean readBox(World world, int bx, int by, int bz, String filename)
        {
        	try
        	{
				FileInputStream fstream = new FileInputStream(filename);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				while ((strLine = br.readLine()) != null)   
				{
					String[] args = strLine.split(" ");
					int blockID = Integer.parseInt(args[0]);
					int x = Integer.parseInt(args[1]);
					int y = Integer.parseInt(args[2]);
					int z = Integer.parseInt(args[3]);
					world.setBlock(x+bx, y+by, z+bz, blockID);
				}
				
				in.close();
			}
    		catch (Exception e)
    		{
    			System.err.println("Error: " + e.getMessage());
			}
        	
    	return true;
    	
        }
        
        public boolean buildBox(World world, int dim, int bx, int by, int bz, int blockID)
        {
        	for (int x = 0; x < dim; x++)
        	{
        		for (int z = 0; z < dim; z++)
        		{
        			for (int y = 0; y < dim; y++)
        			{
        				if (x == 0 || z == 0 || x == dim - 1 || z == dim - 1 || y == dim - 1)
        					{
        						world.setBlock(x+bx, y+by, z+bz, blockID);
        					}
        			}
        		}
        	}
        	return true;
        }        
}