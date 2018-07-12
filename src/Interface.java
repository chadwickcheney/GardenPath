import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Interface extends UserInterface
{


  public Interface(Main main, BufferedImage img, int x, int y, int fontSize, Color fontColor, String font,
			String text) {
		super(main, img, x, y, fontSize, fontColor, font, text);
	}

@Override
  public void render(Graphics g)
  {
    g.drawImage(img, x-(main.scaleX*(main.tilePixWidth/2)), y-(main.scaleY*(main.tilePixHeight)), img.getWidth()*main.scaleX, img.getHeight()*main.scaleY, null);
    g.drawString(renderText, x, y);
  }

  @Override
  public void tick()
  {
    GameObject o = interfacesPlot.get(text);
    renderText = text.toString().toUpperCase()+" GRID: ["+o.gridX+"]["+o.gridY+"]";
  }
}
