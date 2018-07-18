import java.awt.Graphics;
import java.util.List;

public class Player extends GameObject {

	public Player(Main main, List<Frame> frames, int x, int y, int setFrameInt) {
		super(main, frames, x, y, setFrameInt);
		movementSpeed = main.playerSpeed;
		id = 42;
		System.out.println("Player ID issued: " + id + " x: " + x + " y: " + y);
	}

	@Override
	public void tick() {
		keyListener();
		updateGrid();
	}

	public void updateGrid() {
		gridX = getGridX(-main.world.x+x+renderXOffset);
		gridY = getGridY(-main.world.y+y+renderYOffset);
	}

	public void keyListener() {
		if (event.up) {
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 12;
			img = frames.get(seed).img;
		}

		if (event.down) {
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 0;
			img = frames.get(seed).img;
		}

		if (event.left) {
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 4;
			img = frames.get(seed).img;
		}

		if (event.right) {
			int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 8;
			img = frames.get(seed).img;
		}

		if (!(event.up || event.down || event.left || event.right)) {
			img = frames.get(0).img;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, img.getWidth() * main.scaleX, img.getHeight() * main.scaleY, null);
	}
}
