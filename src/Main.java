import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main extends Canvas implements Runnable
{
	// VARIABLES
	// This
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean running;
	public int fps;
	private int sleepTime = 1000;
	public int playerSpeed = 10;
	public int widthTiles = 200;
	public int heightTiles = 200;
	public int[][] enviromentArray = new int[widthTiles][heightTiles];
	public boolean loadMap = true;
	public boolean debug = true;
	public boolean gameplay = false;
	// Strings
	public String title = "Ridge";
	public String enviromentArrayFile = "enviromentFile.txt";
	public String guiTilesFile = "res/userInterface.png";
	public String enviromentTilesFile = "res/basictiles2.png";
	public String creatureFramesFile = "res/characters.png";
	public String deadFramesFile = "res/dead.png";
	// Screen
	public int Width = 1920;
	public int Height = 1080;
	public int FRAME_RATE = 210;
	public int scaleX = 6;
	public int scaleY = 6;
	public int guiScaleX = 3;
	public int guiScaleY = 3;
	// Graphics String Variables
	public int fontSize = (guiScaleX * guiScaleY) / 2 * (guiScaleX * guiScaleY);
	public Font mainFont = (new Font("Courier New", Font.BOLD, fontSize));
	public Color guiFontColor = new Color(23, 91, 115);
	// Sprites
	public int tilePixWidth = 16;
	public int tilePixHeight = 16;
	// Lists
	private List<GameObject> objects = new LinkedList<GameObject>();
	private List<UserInterface> guiList = new LinkedList<UserInterface>();
	// Collective Frames
	public List<Frame> mapFrames = new LinkedList<Frame>();
	public List<Frame> creatureFrames = new LinkedList<Frame>();
	public List<Frame> basicHumanFrames = new LinkedList<Frame>();
	public List<Frame> maleHumanFrames = new LinkedList<Frame>();
	public List<Frame> femaleHumanFrames = new LinkedList<Frame>();
	public List<Frame> golumFrames = new LinkedList<Frame>();
	public List<Frame> ghostFrames = new LinkedList<Frame>();
	public List<Frame> spiderFrames = new LinkedList<Frame>();
	public List<Frame> vampireFrames = new LinkedList<Frame>();
	public List<Frame> itemFrames = new LinkedList<Frame>();
	public List<Frame> tileFrames = new LinkedList<Frame>();
	public List<Frame> guiFrames = new LinkedList<Frame>();
	// Instances
	public Handler handler;
	private Display display;
	public ControlEvent controlEvent;
	public ControlSystem controlSystem;
	public Load load;
	public World world;
	public Player player;
	public Mouse mouse;
	public Spawner spawn;
	public Interface ui;

	public Main()
	{
		if (gameplay)
		{
			userLoadQ();
		}
		InitiateSystems();
		int[] setArea = { 5, -1085, 0, 0 };
		display.WindowInit(setArea);
		spawn.initSpawn();
		start();
	}

	private void InitiateSystems()
	{
		handler = new Handler(this, objects, guiList);
		display = new Display(this);
		controlEvent = new ControlEvent(this, FRAME_RATE);
		controlSystem = new ControlSystem(controlEvent);
		load = new Load(this);
		world = new World(this, mapFrames);
		spawn = new Spawner(this, handler);
	}

	public void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public void run()
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				handler.tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
	}

	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		handler.render(g);
		sleep(1);
		g.dispose();
		bs.show();
	}

	private void sleep(int x)
	{
		try
		{
			Thread.sleep(x);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void userLoadQ()
	{
		int var;
		while (true)
		{
			System.out.print("Would you like to load the enviroment array file?");
			try
			{
				Scanner userInput = new Scanner(System.in);
				var = userInput.nextInt();
				userInput.close();
				break;
			} catch (Exception e)
			{
				System.out.print("There was a user input error, please try again: ");
				e.printStackTrace();
			}
		}
		if (var == 1)
			loadMap = true;
	}

	public static void main(String[] args)
	{
		Main main = new Main();
	}
}
