
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class UserInterface 
{
	//VARIABLES
		//This
		private BufferedImage image;
		private int x, y, fontSize, xStringLocation, yStringLocation;
		//Lists		
		//Instances
		protected Main main;
		private ControlEvent event;
		private Graphics g;
		private GameObject o;
		private Player player;
		private World world;
		
	public UserInterface(Main main)
	{
		this.main = main;
		event = main.controlEvent;
		world = main.world;
		player = main.player;
		image = main.guiFrames.get(0).img;
		//x = main.controlEvent.mouseXGrid;
		//y = main.controlEvent.mouseXGrid;		
		x = main.controlEvent.getMouseX();
		y = main.controlEvent.getMouseY();
		xStringLocation = 5;
		yStringLocation = 40;
		fontSize = 7 * main.guiScale;
	}
	
	
	public void render(Graphics g)
	{
		if (event.guiToggle)
		{
			o = main.objects.get(0);
			//Set Font
			g.setFont(new Font("Corbel", Font.PLAIN, fontSize));
			g.setColor(Color.WHITE);

			//FPS
			g.drawString(""+main.fps + " FRAMES\n", xStringLocation, yStringLocation*1);

			//Player
			g.drawString("["+main.player.gridX+"]["+main.player.gridY+"]\tplayer grid", xStringLocation, yStringLocation*3);
			g.drawString("{"+main.player.x+"}{"+main.player.y+"}\tplayer coordinates", xStringLocation, yStringLocation*4);

			//Mouse
			g.drawString("["+event.gridX+"]["+event.gridY+"] MOUSE GRID", xStringLocation, yStringLocation*6);
			g.drawString("["+event.getMouseX()+"]["+event.getMouseY()+"] MOUSE [X] [Y]", xStringLocation, yStringLocation*7);

			//Object 0
			g.drawString("["+o.gridX+"]["+o.gridY+"]\tobject grid", xStringLocation, yStringLocation*11);
			g.drawString("["+o.destinationPair[0]+"]["+o.destinationPair[1]+"]\tdestination grid", xStringLocation, yStringLocation*12);

			//World
			g.drawString("{"+world.x+"}{"+world.y+"}\tworld x and y", xStringLocation, yStringLocation*14);

            //Draw Cursor
			g.drawImage(image,
				event.renderX,
				event.renderY,
				image.getWidth()*main.scaleX,
				image.getHeight()*main.scaleY,
				null);
        }
	}
}
