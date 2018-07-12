
public class ControlEvent
{
	//declarations
	private String key;
	public boolean left, right, up, down, space;
	public int FRAME_RATE = 1000;
	public int time;

	public boolean corneredX = false;
	public boolean corneredY = false;
	public boolean guiToggle = true;

	public int mouseX, mouseY;
	public int gridX, gridY;
	public int renderX, renderY;

	private Main main;

	public ControlEvent(Main main, int FRAME_RATE)
	{
		this.main = main;
		this.FRAME_RATE = FRAME_RATE;
		time = (int) (System.currentTimeMillis()/FRAME_RATE);

		System.out.println("Control Event");
	}

	public void set(String key, boolean status)
	{
		if (key == "left")
			left = status;
		if (key == "right")
			right = status;
		if (key == "up")
			up = status;
		if (key == "down")
			down = status;
		if (key == "space")
			space = status;
		if (key == "guiToggle")
			guiToggle = !guiToggle;
	}

	public boolean getLeft()
	{
		return left;
	}

	public boolean getRight()
	{
		return right;
	}

	public boolean getUp()
	{
		return up;
	}

	public boolean getDown()
	{
		return down;
	}

	public boolean getSpace()
	{
		return space;
	}

	public void setCorneredX(boolean b)
	{
		this.corneredX = b;
	}

	public void setCorneredY(boolean b)
	{
		this.corneredY = b;
	}

	public void setMouse(int x, int y)
	{
		mouseX = x;
		mouseY = y;
		setGrid(mouseX, mouseY);
		setRender(mouseX, mouseY);
	}

	public void setGrid(int x, int y)
	{
		gridX = getGridX(x);
		gridY = getGridY(y);
	}

	public int getGridX(int x)
	{
		return ((x+(-main.world.x))/(main.scaleX*main.tilePixWidth));
	}

	public int getGridY(int y)
	{
		return ((y+(-main.world.y))/(main.scaleY*main.tilePixHeight));
	}


	public int getMouseX()
	{
		return mouseX;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	public void setRender(int x, int y)
	{
        int snapX = ( (mouseX/(main.scaleX*main.tilePixWidth)) * (main.scaleX*main.tilePixWidth));
        int snapY = ( (mouseY/(main.scaleY*main.tilePixHeight)) * (main.scaleY*main.tilePixHeight));

        int xOffset = ( ((-main.world.x)/(main.scaleX*main.tilePixWidth)) * (main.scaleX*main.tilePixWidth));
        int yOffset = ( ((-main.world.y)/(main.scaleY*main.tilePixHeight)) * (main.scaleY*main.tilePixHeight));

        renderX = snapX+main.world.x+xOffset;
        renderY = snapY+main.world.y+yOffset;
	}

}
