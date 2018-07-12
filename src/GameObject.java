import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

abstract class GameObject 
{
	//VARIABLES
		//This
		protected BufferedImage img;
		protected int x, y, gridX, gridY, setFrameInit, movementSpeed, scaleX, scaleY, id, moveSpeed;
		protected boolean walledR, walledL, walledU, walledD, arrivedX, arrivedY, doingSomething;
		protected int[] destinationPair;
		
		//LISTS
		protected List<Frame> frames;
		
		//Instances
		protected Main main;
		protected ControlEvent event;
		protected Random random;
		
	public GameObject(Main main, List<Frame> frames, int x, int y, int setFrameInt)
	{
		this.main = main;
		this.frames = frames;
		img = frames.get(setFrameInt).img;
		event = main.controlEvent;
		this.x = x;
		this.y = y;
		this.setFrameInit = setFrameInit;
		this.moveSpeed = 1;
		System.out.println("destination pair established");
		this.destinationPair = new int[] {10,10};
		doingSomething = false;
		random = new Random();
	}
	
	abstract public void render(Graphics g);
	
	public void addFrame(BufferedImage image)
	{		
		this.frames.add(new Frame(image, this.frames.size()+1));
	}
	
	abstract public void tick();

	public void uniformTick() 
	{
		updateGrid();
	}
	
	public void detectCollisoins()
	{
		
	}
	
	public void updateGrid()
	{
		gridX = event.getGridX(x);
		gridY = event.getGridY(y);
	}
	
	public void moveX(int direction)
	{
		x+=(moveSpeed*direction);
	}
	
	public void moveY(int direction)
	{
		y+=(moveSpeed*direction);
	}
	
	public int[] getRandomDestination()
	{
		int[] array = {random.nextInt(main.widthTiles), random.nextInt(main.heightTiles)};
		return array;
	}
	
	public void setDestination(int[] xy)
	{
		destinationPair[0] = xy[0];
		destinationPair[1] = xy[1];
	}
	
	public void doActivity(int place)
	{
		doingSomething = true;
		if (random.nextInt(5) == 4)
			doingSomething = false;
	}
}
