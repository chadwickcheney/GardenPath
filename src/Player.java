import java.awt.Graphics;
import java.util.List;

public class Player extends GameObject
{

	public Player(Main main, List<Frame> frames, int x, int y, int setFrameInt)
	{
		super(main, frames, x, y, setFrameInt);
		movementSpeed = main.playerSpeed;
		id = 42;
		System.out.println("Player ID issued: " + id + " x: " + x + " y: " + y);
	}

	@Override
	public void tick()
	{
		keyListener();
		updateGrid();
	}

	public void updateGrid()
	{
		gridX = getGridX(-main.world.x + x + renderXOffset);
		gridY = getGridY(-main.world.y + y + renderYOffset);
	}

	public void keyListener()
	{
		if (event.keys.get("up"))
		{
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 12;
			img = frames.get(seed).img;
		}

		if (event.keys.get("down"))
		{
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 0;
			img = frames.get(seed).img;
		}

		if (event.keys.get("left"))
		{
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 4;
			img = frames.get(seed).img;
		}

		if (event.keys.get("right"))
		{
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 8;
			img = frames.get(seed).img;
		}

		if (!(event.keys.get("up") || event.keys.get("down") || event.keys.get("left") || event.keys.get("right")))
		{
			img = frames.get(0).img;
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(img, x, y, img.getWidth() * main.scaleX, img.getHeight() * main.scaleY, null);
	}
}
