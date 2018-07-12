import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class Main extends Canvas implements Runnable
{
	//VARIABLES
		//This
		private static final long serialVersionUID = 1L;
		private Thread thread;
		private boolean running;
		public int fps;
		private int sleepTime = 1000;
		public int playerSpeed = 4;
		public int guiScale = 6;
		//public int widthTiles = 200;
		//public int heightTiles = 200;
		public int widthTiles = 1920/10;
		public int heightTiles = 1080/10;
		public int[][] enviromentArray = new int[widthTiles][heightTiles];
		public boolean loadNewMap = false;
		public boolean debug = true;
		//Strings
		public String title = "Ridge";
		public String enviromentArrayLoadFile = "";
		public String enviromentTilesFile = "basictiles2.png";
		public String creatureFramesFile = "characters.png";
		public String deadFramesFile = "dead.png";
		//Screen
		//public int Width = 1920;
		//public int Height = 1080;
		public int Width = 1920;
		public int Height = 1080;
		public int FRAME_RATE = 210;
		//public int scaleX = 6;
		//public int scaleY = 6;
		public int scaleX = 4;
		public int scaleY = 4;
		//Sprites
		public int tilePixWidth = 16;
		public int tilePixHeight = 16;		
		//Lists
		public List<GameObject> objects = new LinkedList<GameObject>();
		//Collective Frames
		public List<Frame> mapFrames = new LinkedList<Frame>();
		public List<Frame> creatureFrames = new LinkedList<Frame>();
		public List<Frame> itemFrames = new LinkedList<Frame>();
		public List<Frame> tileFrames = new LinkedList<Frame>();
		public List<Frame> guiFrames = new LinkedList<Frame>();			
		//Instances
		private Handler handler;
		private Display display;
		public ControlEvent controlEvent;
		public ControlSystem controlSystem;
		public Load load;
		public World world;
		public Player player;
		public Spawner spawn;
		public UserInterface ui;
		
	public Main()
	{
		InitiateSystems();
		display.WindowInit();
		spawn.initSpawn();
		start();
	}
	
	private void InitiateSystems() 
	{
		handler = new Handler(this);
		display = new Display(this);
		controlEvent = new ControlEvent(this, FRAME_RATE);
		controlSystem = new ControlSystem(controlEvent);
		load = new Load(this);
		System.out.println(mapFrames.size());
		world = new World(this, mapFrames);
		spawn = new Spawner(this);
		ui = new UserInterface(this);
	}

	private void printMessage(String msg)
	{
		System.out.println(msg);
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
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while( delta >= 1)
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
		ui.render(g);
		sleep(1);
		g.dispose();
		bs.show();
	}
	
	private void sleep(int x)
	{
		try{
			Thread.sleep(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("Main");
		Main main = new Main();
	}
}
