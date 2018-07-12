import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Load
{
	private Main main;
	private Random random;

	private HashMap<String, int[]> creaturesFramesSite = new HashMap<String, int[]>()
	{
		private static final long serialVErsionUID = 1L;
		{
			int[] val = {0,5};
			put("Player",val);
			val[0]=0;
			val[1]=11;
			put("Golum", val);
		}
	};

	public Load(Main main)
	{
		this.main = main;
		random = new Random();
		init(main.loadNewMap);
	}

	public void init(boolean load)
	{
		if (load)
			main.enviromentArray = listConvertArray(readFile(main.enviromentArrayLoadFile));
		else
			main.enviromentArray = getNewEnviromentArray(main.heightTiles, main.widthTiles);
		if (main.debug)
			System.out.println("Adding frame");

		main.mapFrames.add(new Frame(arrayConvertImage(main.enviromentArray, main.enviromentTilesFile),1));

		if (main.debug)
			System.out.println("Done loading World Frames");

		if (main.debug)
			System.out.println("Loading Creature Frames");

		//Player
		loadImagesToFramesList(main.creatureFrames, main.creatureFramesFile, main.deadFramesFile, creaturesFramesSite.get("Player"));

		//Golum
		loadImagesToFramesList(main.creatureFrames, main.creatureFramesFile, main.deadFramesFile, creaturesFramesSite.get("Golum"));

		//gui
		loadImage(main.guiFrames, main.enviromentTilesFile, 1, 12);

	}

	public void loadImagesToFramesList(List<Frame> frames, String filename, String filename1, int[] m)
	{
		for (int i = m[0]; i <= m[0]+3; i++)
		{
			loadImage(frames, filename, m[1]-1, i);
			loadImage(frames, filename, m[1], i);
			loadImage(frames, filename, m[1]-1, i);
			loadImage(frames, filename, m[1]-2, i);
			
		}
		loadImage(frames, filename, m[1], m[0]);
	}

	public void loadImage(List<Frame> frames, String imagefilename, int x, int y)
	{
		try{
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagefilename));
			frames.add(new Frame(image.getSubimage(getXPixels(x),  getYPixels(y), main.tilePixWidth, main.tilePixHeight), frames.size()+1));
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public int getXPixels(int a)
	{
		return (a * 16);
	}

	public int getYPixels(int a)
	{
		return (a * 16);
	}

	public BufferedImage arrayConvertImage(int[][] array, String imagefilename)
	{
        int cols = array.length;
        int rows = array[0].length;

		if (main.debug)
			System.out.println("converting array to an image");
		try {

			BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagefilename));

			HashMap<Integer, BufferedImage> mapPlot = new HashMap<Integer, BufferedImage>()
			{
				private static final long serialVersionUID = 1L;
				{
					put(0, image.getSubimage(getXPixels(0), getYPixels(8), main.tilePixWidth, main.tilePixHeight)); 	//ground
					put(-1, image.getSubimage(getXPixels(3), getYPixels(12), main.tilePixWidth, main.tilePixHeight));	//Wall
				}
			};

			if (array == null)
				System.out.println("Array is null");
			JPanel gui = new JPanel(new BorderLayout(array.length,array[0].length));
			BufferedImage map = new BufferedImage(cols*main.tilePixWidth, rows*main.tilePixHeight, BufferedImage.TYPE_INT_RGB);

			if (main.debug)
				System.out.println("arrayConvertImage Dimensions: rows: "+array.length+", cols: "+array[0].length);
			for (int i = 0; i < cols; i++)
			{
				for (int c = 0; c < rows; c++)
				{
					BufferedImage tile = mapPlot.get(array[i][c]);
					Graphics2D tileGraphics = map.createGraphics();
					tileGraphics.drawImage(tile, getXPixels(i), getYPixels(c), null);
				}
			}

			ImageIO.write(map, "png", new File("res/map.png"));

			return map;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void printMap(int[][] m)
	{
        int cols = m.length;
        int rows = m[0].length;

        System.out.println("printMap rows: "+rows+" cols: "+cols);
        String str = "\t";

        for (int i=0; i<cols; i++)
        {
            for (int j=0; j<rows; j++)
            {
                str += m[i][j] + "\t";
            }
            System.out.println(str+"|");
            System.out.println();
            str="|\t";
        }
	}

	private int[][] getNewEnviromentArray(int rows, int cols)
	{
		if (main.debug)
			System.out.println("Getting New Enviroment Array");
		int[][] array = new int[cols][rows];

		if (main.debug)
			System.out.println("getNewEnviromentArray Dimensions: rows: "+array.length+", cols: "+array[0].length);

        for (int i=0; i<cols; i++)
        {
            for (int j=0; j<rows; j++)
            {
            	array[i][j] = 0;
            }
        }

        /*for (int c=0; c < main.widthTiles; c++)
        	if (random.nextInt(5) == 4)
        		array = addWall(array, c, 3, c, (random.nextInt(main.heightTiles)-3), -1);*/

		return array;
	}

	public int[][] addWall(int[][] m, int x1, int y1, int x2, int y2, int seed)
	{
		int cols = m.length;
        int rows = m[0].length;

		for (int i=0; i<cols; i++)
		{
			for (int j=0; j<rows; j++)
			{
				if ((i >= x1 && i <= x2) && ( j >= y1 && j <= y2))
				{
					m[i][j] = seed;
				}
			}
		}

		return m;
	}

	public void writeFile(String filename, int[][] m)
	{
	    try{
	        File file = new File(filename);
	        PrintWriter writer = new PrintWriter(file);

	        writer.println(m.length);
	        writer.println(m[0].length);

	        int rows = m.length;
	        int cols = m[0].length;

	        for (int i=0; i<cols; i++)
	        {
	            for (int j=0; j<rows; j++)
	            {
	                writer.println(m[i][j]);
	            }
	        }

	        writer.close();
	        System.out.println("load text file written");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<Integer> readFile(String filename)
	{
	    try{

	        File file = new File(filename);
	        BufferedReader br = new BufferedReader(new FileReader(file));

	        List<Integer> mapIntegers = new LinkedList<Integer>();

	        String i;
	        while ((i = br.readLine()) != null)
	        {
	            mapIntegers.add(Integer.parseInt(i));
	        }

	        return mapIntegers;

	    } catch (Exception e){
	        e.printStackTrace();
	    }

	    return null;
	}

	public int[][] listConvertArray(List<Integer> list)
	{
	    int rows = list.get(0);
	    int cols = list.get(1);
	    int[][] m = new int[rows][cols];

	    System.out.println("list convert to array rows: "+rows+" cols: "+cols);

	    for (int i = 0; i < cols; i++)
	    {
	        for (int j = 0; j < rows; j++)
	        {
	            int index = ((i*cols)+j)+2;
	            //System.out.println("index: "+index);
	            m[i][j] = list.get(index);
	        }
	        //System.out.println("");
	    }

	    return m;
	}
}
