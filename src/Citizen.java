import java.awt.Graphics;
import java.util.List;

public class Citizen extends GameObject {

	public Citizen(Main main, List<Frame> frames, int x, int y, int setFrameInt) {
		super(main, frames, x, y, setFrameInt);
		System.out.println("Frames size: "+ frames.size());
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x + main.world.x, y + main.world.y, img.getWidth() * main.scaleX,
				img.getHeight() * main.scaleY, null);
	}

	@Override
	public void tick() {
		if (!(arrivedX)) {
			if (gridX < destinationPair[0]) {
				int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 8;
				img = frames.get(seed).img;
				moveX(1);
			}

			if (gridX > destinationPair[0]) {
				int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 4;
				img = frames.get(seed).img;
				moveX(-1);
			}
		}

		if (!(arrivedY)) {
			if (gridY < destinationPair[1]) {
				int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 0;
				img = frames.get(seed).img;
				moveY(1);
			}

			if (gridY > destinationPair[1]) {
				int seed = ((((int) (System.currentTimeMillis() / main.FRAME_RATE)) - event.time) % 4) + 12;
				img = frames.get(seed).img;
				moveY(-1);
			}
		}

		if (gridX == destinationPair[0])
			arrivedX = true;
		else
			arrivedX = false;

		if (gridY == destinationPair[1])
			arrivedY = true;
		else
			arrivedY = false;

		if (arrivedX && arrivedY) {
			doActivity(main.enviromentArray[gridX][gridY]);
		}
	}
}
