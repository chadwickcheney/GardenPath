
public class ControlEvent {
	// declarations
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
	public int snapX, snapY;
	public int xOffset, yOffset;

	private Main main;

	public ControlEvent(Main main, int FRAME_RATE) {
		this.main = main;
		this.FRAME_RATE = FRAME_RATE;
		time = (int) (System.currentTimeMillis() / FRAME_RATE);

		System.out.println("Control Event");
	}

	public void set(String key, boolean status) {
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
	}

	public void setToggle(String key, boolean status) {
		if (key == "guiToggle")
			guiToggle = !guiToggle;
	}

	public void setCorneredX(boolean b) {
		this.corneredX = b;
	}

	public void setCorneredY(boolean b) {
		this.corneredY = b;
	}

	public void setMouse(int x, int y) {
		mouseX = x;
		mouseY = y;
		setGrid(mouseX, mouseY);
		setRender(mouseX, mouseY);
	}

	public void setGrid(int x, int y) {
		gridX = getGridX(x);
		gridY = getGridY(y);
	}

	public int getGridXGameObject(int x) {
		return ((x) / (main.scaleX * main.tilePixWidth));
	}

	public int getGridYGameObject(int y) {
		return ((y) / (main.scaleY * main.tilePixHeight));
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setRender(int x, int y) {
		snapX = ((mouseX / (main.scaleX * main.tilePixWidth)) * (main.scaleX * main.tilePixWidth));
		snapY = ((mouseY / (main.scaleY * main.tilePixHeight)) * (main.scaleY * main.tilePixHeight));

		xOffset = ((-main.world.x) / (main.scaleX * main.tilePixWidth)) * (main.scaleX * main.tilePixWidth);
		yOffset = ((-main.world.y) / (main.scaleY * main.tilePixHeight)) * (main.scaleY * main.tilePixHeight);

		renderX = snapX + main.world.x + xOffset;
		renderY = snapY + main.world.y + yOffset;
	}

	public int getGridX(int x) {
		return ((x + (-main.world.x)) / (main.scaleX * main.tilePixWidth));
	}

	public int getGridY(int y) {
		return ((y + (-main.world.y)) / (main.scaleY * main.tilePixHeight));
	}
	
	public void setMouseClicked(String key) {
		System.out.println("___________________________________________");
		System.out.println("Mouse Clicked Grid:       [" + gridX + "][" + gridY + "]");
		System.out.println("Mouse Clicked Coor:       [" + mouseX + "][" + mouseY + "]");
		System.out.println("Mouse Clicked Snap:       [" + snapX + "][" + snapY + "]");
		System.out.println("Mouse Clicked Offs:       [" + xOffset + "][" + yOffset + "]");
		System.out.println("Mouse Clicked Render:     [" + renderX + "][" + renderY + "]");
		System.out.println("Mouse Clicked World Coor: [" + main.world.x + "][" + main.world.y + "]\n");
	}

}
