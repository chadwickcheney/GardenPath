import java.util.HashMap;
import java.util.Map;

public class ControlEvent
{
	// declarations
	public Map<String, Boolean> keys;
	private String key;
	//public boolean left, right, up, down, space;
	public int FRAME_RATE = 1000;
	public int time;

	public boolean corneredX = false;
	public boolean corneredY = false;
	public boolean guiToggle;

	public int mouseX, mouseY;
	public int gridX, gridY;
	public int renderX, renderY;
	public int renderXGrid, renderYGrid;
	public int snapX, snapY;
	public int xOffset, yOffset;

	private Main main;

	public ControlEvent(Main main, int FRAME_RATE)
	{
		this.main = main;
		keys = new HashMap<>();
		this.FRAME_RATE = FRAME_RATE;
		time = (int) (System.currentTimeMillis() / FRAME_RATE);
		guiToggle = true;
	}

	/*public void set(String key, boolean status)
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
		if (key == "guiToggle" && !guiToggle)
		{
			guiToggle = true;
		}
		else
		{
			guiToggle = false;
		}
	}*/

	
	public void toggle()
	{
		if (keys.get("guiToggle") && !guiToggle)
		{
			guiToggle = false;
		}
	}
	
	public void setToggle(String key)
	{
		if (key == "guiToggle")
		{
			System.out.println("toggling guiToggle");
			guiToggle = !guiToggle;
		}
		if (key == "save_game")
			main.load.writeFile(main.enviromentArrayFile, main.enviromentArray);
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
	}

	public int getMouseX()
	{
		return mouseX;
	}

	public int getMouseY()
	{
		return mouseY;
	}
}
