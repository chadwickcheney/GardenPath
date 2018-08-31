import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

abstract class UserInterface
{
	// VARIABLES
	// This
	protected int xImage, yImage, fontSize, xText, yText;
	protected BufferedImage img;
	protected String text, font, renderText;
	protected Color fontColor;
	// Instances
	protected Main main;
	protected ControlEvent event;
	// HashMap
	public HashMap<String, GameObject> interfacesPlot = new HashMap<String, GameObject>();

	public UserInterface(Main main, BufferedImage img, int x, int y, int fontSize, Color fontColor, Font font,
			String text)
	{
		this.main = main;
		this.event = main.controlEvent;
		this.img = img;
		this.xImage = getLocationXImage(x);
		this.yImage = getLocationYImage(y);
		this.xText = getLocationXText(xImage);
		this.yText = getLocationYText(yImage);
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.text = text;
		this.renderText = text;
		initHashMap();
	}

	public void initHashMap()
	{
		interfacesPlot.put("player grid", main.player);
		interfacesPlot.put("mouse grid", main.mouse);
	}

	public int getLocationXImage(int x)
	{
		int result = x * (16 * main.guiScaleX);
		return result;
	}

	public int getLocationYImage(int y)
	{
		int result = y * (16 * main.guiScaleY);
		return result;
	}

	public int getLocationXText(int x)
	{
		int result = ((img.getWidth()) / 2);
		return (x + (main.tilePixWidth));
	}

	public int getLocationYText(int y)
	{
		int result = ((img.getHeight()) / 2);
		return (y + ((img.getHeight() * main.guiScaleY)) - (result));
	}

	abstract public void render(Graphics g);

	abstract public void tick();
}
