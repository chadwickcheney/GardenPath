import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Interface extends UserInterface
{

	public Interface(Main main, BufferedImage img, int x, int y, int fontSize, Color fontColor, Font font, String text)
	{
		super(main, img, x, y, fontSize, fontColor, font, text);
	}

	@Override
	public void render(Graphics g)
	{
		if (event.guiToggle)
		{
			g.drawImage(img, xImage, yImage, img.getWidth() * main.guiScaleX, img.getHeight() * main.guiScaleY, null);

			// String Fonts and Color
			g.setFont(main.mainFont);
			g.setColor(main.guiFontColor);
			g.drawString(renderText, xText, yText);
		}
	}

	@Override
	public void tick()
	{
		GameObject o = interfacesPlot.get(text);
		renderText = text.toString().toUpperCase() + " [" + o.gridX + "][" + o.gridY + "]";
	}
}
