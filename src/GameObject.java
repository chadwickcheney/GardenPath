import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

abstract class GameObject
{
	// VARIABLES
	// This
	protected BufferedImage img;
	protected int x, y, gridX, gridY, renderX, renderY, setFrameInit, movementSpeed, scaleX, scaleY, id, moveSpeed,
			allScaleX, allScaleY, renderXOffset, renderYOffset;
	protected boolean walledR, walledL, walledU, walledD, arrivedX, arrivedY, doingSomething;
	protected int[] destinationPair;

	// LISTS
	protected List<Frame> frames;
	protected List<Chore> taskList = new LinkedList<Chore>();

	// Instances
	protected Main main;
	protected ControlEvent event;
	protected Random random;

	public GameObject(Main main, List<Frame> frames, int x, int y, int setFrameInt)
	{
		this.main = main;
		this.frames = frames;
		this.random = new Random();
		img = frames.get(setFrameInt).img;
		event = main.controlEvent;
		this.x = x;
		this.y = y;
		this.setFrameInit = setFrameInit;
		this.moveSpeed = 4;
		this.destinationPair = new int[] { random.nextInt(100), random.nextInt(100) };
		doingSomething = false;
		random = new Random();
		taskList.add(new Chore("get an apple", false));
		allScaleX = main.scaleX * main.tilePixWidth;
		allScaleY = main.scaleY * main.tilePixHeight;
		renderXOffset = (main.scaleX * (main.tilePixWidth / 2));
		renderYOffset = (main.scaleY * (main.tilePixHeight));
	}

	public void addFrame(BufferedImage image)
	{
		this.frames.add(new Frame(image, this.frames.size() + 1));
	}

	abstract public void tick();

	public void uniformTick()
	{
		updateGrid();
	}

	public void detectCollisoins()
	{

	}

	// For all GameObjects except Mouse and Player as There x and y are treated
	// differently
	public void updateGrid()
	{
		gridX = getGridX(x + renderXOffset);
		gridY = getGridY(y + renderYOffset);
	}

	public void moveX(int direction)
	{
		if (main.enviromentArray[gridX + 1][gridY + 1] >= 0)
		{
			x += (moveSpeed * direction);
		}
	}

	public void moveY(int direction)
	{
		if (main.enviromentArray[gridX + 1][gridY + 1] >= 0)
		{
			y += (moveSpeed * direction);
		}
	}

	public int[] getRandomDestination()
	{
		int[] array = { random.nextInt(main.widthTiles), random.nextInt(main.heightTiles) };
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
		if (checkTaskList() == true)
		{
			System.out.println("getting new destination");
			setDestination(getRandomDestination());
		}
	}

	public boolean checkTaskList()
	{
		for (Chore t : taskList)
		{
			if (t.getStatus() == false)
				return false;
		}
		return true;
	}

	public int getGridX(int x)
	{
		return ((x) / (allScaleX));
	}

	public int getGridY(int y)
	{
		return ((y) / (allScaleY));
	}

	public void render(Graphics g)
	{
		g.drawImage(img, x + main.world.x + renderXOffset, y + main.world.y + renderYOffset,
				img.getWidth() * main.scaleX, img.getHeight() * main.scaleY, null);
	}
}
