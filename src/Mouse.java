import java.awt.Graphics;
import java.util.List;

public class Mouse extends GameObject {
	public Mouse(Main main, List<Frame> frames, int x, int y, int setFrameInt) {
		super(main, frames, x, y, setFrameInt);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, renderX, renderY, img.getWidth()*main.scaleX, img.getHeight()*main.scaleY, null);
	}

	@Override
	public void tick() {
		x = event.mouseX;
		y = event.mouseY;
		
		gridX = ((x + (-main.world.x)) / allScaleX);
		gridY = ((y + (-main.world.y)) / allScaleY);
		
		renderX = (
				(((x + (-main.world.x)) / allScaleX)*(allScaleX)) + 
				(main.world.x%(allScaleX)) + 
				((main.world.x/(allScaleX)) * 
				(allScaleX))
			);
		
		renderY = (
				(((y + (-main.world.y)) / allScaleY)*(allScaleY)) + 
				(main.world.y%(allScaleY)) + 
				((main.world.y/(allScaleY)) * 
				(allScaleY))
			);		
	}
}
