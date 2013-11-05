package tutorial.generic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.lang.Math;

public class CityFileGenerator {

	private static int total_blocks = 0;
	
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
	{
		PrintWriter writer = new PrintWriter("box.txt", "UTF-8");
		ArrayList<ClassProperties> classes = Parser.parse();
		//Parser p = new Parser();
		//p.parse(classes);
		//p.showData(classes);
		generateGrid(classes, writer);
		//generateBox(25, writer);
		writer.close();
		System.out.println(total_blocks);
	}
	public static int findLargestItem(ArrayList<ClassProperties> classList){
		int length = classList.size();
		int max = 0;
		for (int i = 0; i < length; i++){
			if (max < Math.log(classList.get(i).getNumAttributes()))
				max = (int) Math.log(classList.get(i).getNumAttributes());
		}
		return max;
	}

	public static void generateGrid(ArrayList<ClassProperties> classList, PrintWriter writer){

		classList = new ArrayList<ClassProperties>(classList.subList(0, 10));
		
		// cell size is based off of the largest attribute number
		int cellSize = findLargestItem(classList) + 1;

		// numGrids will depend on the number of classes we look through
		double numGrids = (double) classList.size();

		// numCell is the number of cells we have per side (assume square world)
		int numCell = (int) Math.ceil(Math.sqrt(numGrids));

		// gridSide is the length of each side of the grid
		int gridSide = cellSize * numCell;

		// counter will keep track of how many buildings we've built so far
		int counter = 0;

		// builds out the grid then builds structs in each one
		for (int i = 0; i < gridSide; i += cellSize) 
		{
			for (int j = 0; j < gridSide; j += cellSize)
			{
				if (counter < classList.size())
				{
					System.out.println("Building structure "+(counter+1));
					generateBuilding(classList, counter, i, j, writer);
					counter++;
				}
			}
		}

	}

	// in this method, inputs x and y determine the beginning x and z coordinates of each grid
	@SuppressWarnings("unused")
	public static void generateBuilding(ArrayList<ClassProperties> classList, int gridNum, int x, int z, PrintWriter writer){

		// if there are no more classes to make structures out of
		if (gridNum <= classList.size()) {

			// get a class and return its method and attributes
			ClassProperties c = classList.get(gridNum);
			//int attr = c.getNumAttributes() + 1;
			//int meth = c.getNumMethods();
			//System.out.println(attr);
			//System.out.println(meth);
			//System.exit(0);
			int attr = (int) Math.log(c.getNumAttributes());
			int meth = (int) Math.log(c.getNumMethods());

			// maxHeight is meth + 4 because flat ground starts at y = 4
			int maxHeight = meth + 4;

				//make each block based on attributes, x starting at x going downwards and z going to the right
				for (int i = 4; i < maxHeight; i++)
				{
					for (int j = 0; j < attr + x; j++)
					{
						for (int k = z; k < attr + z; k++)
						{
							if (i == maxHeight - 1 || j == x || j == attr + x - 1 || k == z || k == attr + z - 1)
							{
								writer.print(String.format("%d %d %d %d\n", 1, j, i, k));
								total_blocks++;
								//System.out.println(String.format("%d %d %d %d", 1, j, i, k));
							}
							//ModLoader.getMinecraftInstance().setBlockWithNotify(j, i, k, Block.blockDiamond.blockID);
						}
					}
				}
			
			//System.exit(0);
		}

	}
	/*
	
	private static void generateBox(int dim, PrintWriter writer)
	{
		for (int x = 0; x < dim; x++)
    	{
    		for (int z = 0; z < dim; z++)
    		{
    			for (int y = 0; y < dim; y++)
    			{
    				if (x == 0 || z == 0 || x == dim - 1 || z == dim - 1 || y == dim - 1)
    					writer.print(String.format("%d %d %d %d\n", 1, x, y, z));    				
    			}
    		}
    	}
	}	*/
}
