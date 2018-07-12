import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class World 
{
	//VARIABLES
	//This
	protected int x, y, distance;
	protected BufferedImage img;
	private boolean walledR, walledL, walledU, walledD;
	private GameObject player;

	//LISTS
	protected List<Frame> frames;

	//Instances
	private Main main;
	private ControlEvent event;

	public World(Main main, List<Frame> frames)
	{
		this.main = main;
		this.frames = frames;
		while (true)
		{
			if (this.frames.size() >= 1)
			{
				img = this.frames.get(0).img;
				break;
			}
			else
			{
				if (main.debug)
					System.out.println("Waiting on Frames");
				try{
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		event = main.controlEvent;
		distance = 1;		
	}
	
	public void tick()
	{
		//updateWalls();
		//System.out.print("L:"+walledL+" R:"+walledR+" U:"+walledU+" D:"+walledD+"\n");
		if (event.up && y <= 0 && (!(walledU)))
		{
			y = y + main.playerSpeed;
		}
		if (event.down && y + img.getHeight()*main.scaleY - main.playerSpeed > main.Height && (!(walledD)))
		{
			y = y - main.playerSpeed;
		}
		if (event.left && x <= 0 && (!(walledL)))
		{
			x = x + main.playerSpeed;
		}
		if (event.right && x + img.getWidth()*main.scaleX - main.playerSpeed > main.Width && (!(walledR)))
		{
			x = x - main.playerSpeed;
		}
	}

	public void render(Graphics g)
	{
		g.drawImage(img, x, y, img.getWidth()*main.scaleX, img.getHeight()*main.scaleY, null);
	}
}
