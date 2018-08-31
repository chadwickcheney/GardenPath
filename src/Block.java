import java.awt.Graphics;
import java.util.List;

public class Block extends GameObject
{

	public Block(Main main, List<Frame> frames, int x, int y, int setFrameInt)
	{
		super(main, frames, x, y, setFrameInt);
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(img, x - (main.scaleX * (main.tilePixWidth / 2)), y - (main.scaleY * (main.tilePixHeight)),
				img.getWidth() * main.scaleX, img.getHeight() * main.scaleY, null);
	}

	@Override
	public void tick()
	{

	}
}
