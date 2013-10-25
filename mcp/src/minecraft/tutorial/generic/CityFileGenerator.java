package tutorial.generic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class CityFileGenerator {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
	{
		// TODO Auto-generated method stub
		PrintWriter writer = new PrintWriter("box.txt", "UTF-8");
		generateBox(25, writer);
		writer.close();
	}
	
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
	}	
}
