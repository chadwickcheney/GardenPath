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
		this.guiPlot.put(0, main.guiFrames.get(1).img); // left side
		this.guiPlot.put(1, main.guiFrames.get(2).img); // middle
		this.guiPlot.put(2, main.guiFrames.get(3).img); // right side
		this.guiPlot.put(3, main.guiFrames.get(4).img); // single
	}

	public void initSpawn()
	{
		if (main.debug)
			System.out.println("Spawner initSpawn() started");
		initPlayer();
		initMouse();
		initInterfaces();
		spawnMale(0, 0, 0);
	}

	public void initPlayer()
	{
		main.player = new Player(main, main.maleHumanFrames, main.Width / 2, main.Height / 2, 0);
	}

	public void initMouse()
	{
		main.mouse = new Mouse(main, main.guiFrames, main.Width / 2, main.Height / 2, 0);
		handler.addGameObject(main.mouse);
	}

	public void spawnGolum(int x, int y, int setFrameInit)
	{
		handler.addGameObject(new Golum(main, main.golumFrames, x, y, setFrameInit));
	}

	public void spawnMale(int x, int y, int setFrameInit)
	{
		handler.addGameObject(new Citizen(main, main.maleHumanFrames, x, y, setFrameInit));
	}

	public void initInterfaces()
	{
		spawnUserInterface(getDimensionsMessage("Player Grid [xx][yy]", main.mainFont, main.fontSize),
			main.fontSize, main.guiFontColor, main.mainFont, "player grid");

		spawnUserInterface(getDimensionsMessage("Mouse Grid [xx][yy]", main.mainFont, main.fontSize),
			main.fontSize, main.guiFontColor, main.mainFont, "mouse grid");

		spawnUserInterface(getDimensionsMessage("Mouse Env. Grid [xx][yy]", main.mainFont, main.fontSize),
			main.fontSize, main.guiFontColor, main.mainFont, "env grid");
	}

	public void spawnUserInterface(int[][] m, int fontSize, Color fontColor, Font font, String text)
	{
		BufferedImage image = main.load.makeImage(m, main.guiTilesFile, guiPlot);
		handler.addUserInterfaceObject(main, image, fontSize, fontColor, font, text);
	}

	public int[][] getDimensionsMessage(String text, Font font, int fontSize)
	{
		Graphics g = main.getGraphics();
		g.setFont(font);
		int width = g.getFontMetrics().stringWidth(text);

		int tiles = width / (main.tilePixWidth * main.guiScaleX) + 1;
		int[][] m = new int[tiles][1];
		m[0][0] = 0;
		m[tiles - 1][0] = 1;
		for (int i = 1; i < tiles - 1; i++)
			m[i][0] = 2;

		return m;
	}

	public int[][] getDimensionsMessage1(String text, String font, int fontSize)
	{
		Graphics g = main.getGraphics();
		g.setFont(new Font(font, Font.PLAIN, fontSize));
		int width = g.getFontMetrics().stringWidth(text);
		System.out.println("The width of the text is: " + width);

		int tiles = width / (16 * main.guiScaleX) + 1;
		System.out.println("tiles: " + tiles);
		int[][] m = new int[1][tiles];
		m[0][0] = 1;
		m[0][tiles - 1] = 3;
		for (int i = 1; i < tiles - 1; i++)
			m[0][i] = 2;

		if (main.debug)
			main.load.printMap(m);

		return m;
	}
}
