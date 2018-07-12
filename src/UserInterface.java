import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

abstract class UserInterface
{
  //VARIABLES
	//This
	protected int x, y, fontSize;
	protected BufferedImage img;
	protected String text, font, renderText;
	protected Color fontColor;
	//Instances
	protected Main main;
	protected ControlEvent event;
	//HashMap
	public HashMap<String, GameObject> interfacesPlot = new HashMap<String, GameObject>();

	public UserInterface(Main main, BufferedImage img, int x, int y, int fontSize, Color fontColor, String font, String text)
	{
		this.main = main;
		this.x = x;
		this.y = y;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.text = text;
		this.renderText = text;
		this.img = img;
		initHashMap();
	}
	public void initHashMap()
	{
		interfacesPlot.put("player grid",main.player);
		interfacesPlot.put("mouse grid",main.mouse);
	}

	abstract public void render(Graphics g);
	abstract public void tick();
}
