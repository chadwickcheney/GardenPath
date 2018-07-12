import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Spawner
{
	private HashMap<Integer, BufferedImage> guiPlot = new HashMap<Integer, BufferedImage>();
	private Main main;
	private Handler handler;

	public Spawner(Main main, Handler handler)
	{
		this.main = main;
		this.handler = handler;
		init();
	}

	public void init()
	{
		this.guiPlot.put(0, main.guiFrames.get(1).img); 	//left side
		this.guiPlot.put(1, main.guiFrames.get(2).img); 	//middle
		this.guiPlot.put(2, main.guiFrames.get(3).img); 	//right side
		this.guiPlot.put(3, main.guiFrames.get(4).img); 	//single
	}

	public void initSpawn()
	{
		if (main.debug)
			System.out.println("Spawner initSpawn() started");
		initPlayer();
		initMouse();
		initUserInterfaces();
	}

	public void initPlayer()
	{
		main.player = new Player(main, main.basicHumanFrames,main.Width/2,main.Height/2,0);
	}

	public void initMouse()
	{
		main.mouse = new Mouse(main, main.basicHumanFrames,main.Width/2,main.Height/2,0);
	}

	public void spawnGolum(int x, int y, int setFrameInit)
	{
		handler.addGameObject(new Citizen(main, main.golumFrames, x, y, setFrameInit));
	}

	public void initUserInterfaces()
	{
		System.out.println("getting dimensions of message");
		int[][] m = getDimensionsMessage("Player Grid [xx][yy]", "Corbel", 7*main.guiScale);
		spawnUserInterface(m, 50, 50, main.fontSize, main.guiFontColor, main.mainFont, "player grid");
	}

	public void spawnUserInterface(int[][] m, int x, int y, int fontSize, Color fontColor, String font, String text)
	{
		BufferedImage image = main.load.makeImage(m, main.guiTilesFile, guiPlot);
		handler.addUserInterfaceObject(new Interface(main, image, x, y, fontSize, fontColor, font, text));
	}

	public int[][] getDimensionsMessage(String text, String font, int fontSize)
	{
		Graphics g = main.getGraphics();
		g.setFont(new Font(font, Font.PLAIN, fontSize));
		int width = g.getFontMetrics().stringWidth(text);
		System.out.println("The width of the text is: " + width);

		int tiles = width/(16*main.guiScale)+1;
		System.out.println("tiles: "+tiles);
		int[][] m = new int[1][tiles];
		m[0][0]=1;
		m[0][tiles-1]=3;
		for (int i = 1; i < tiles-1; i++)
			m[0][i] = 2;

		if (main.debug)
			main.load.printMap(m);

		return m;
	}
}
