import java.awt.Graphics;
import java.util.List;

public class Mouse extends GameObject
{
  public Mouse(Main main, List<Frame> frames, int x, int y, int setFrameInt) {
		super(main, frames, x, y, setFrameInt);
	}

  @Override
  public void render(Graphics g)
  {

  }

  @Override
  public void tick()
  {
    gridX = event.gridX;
    gridY = event.gridY;
  }
}
