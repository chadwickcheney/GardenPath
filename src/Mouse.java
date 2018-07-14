import java.awt.Graphics;
import java.util.List;

public class Mouse extends GameObject {
	public Mouse(Main main, List<Frame> frames, int x, int y, int setFrameInt) {
		super(main, frames, x, y, setFrameInt);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, event.renderX, event.renderY, img.getWidth()*main.scaleX, img.getHeight()*main.scaleY, null);
	}

	@Override
	public void tick() {
		x = event.mouseX;
		y = event.mouseY;
		gridX = event.gridX;
		gridY = event.gridY;
	}
}
