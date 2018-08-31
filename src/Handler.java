import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Handler
{
	private Main main;
	private List<GameObject> objects;
	private List<UserInterface> guiList;

	public Handler(Main main, List<GameObject> objects, List<UserInterface> guiList)
	{
		this.main = main;
		this.objects = objects;
		this.guiList = guiList;
	}

	public void tick()
	{
		// restructureObjectList();
		main.world.tick();
		for (UserInterface ui : guiList)
		{
			ui.tick();
		}
		main.player.tick();
		for (GameObject o : objects)
		{
			o.tick();
			if (!(o instanceof Player) && (!(o instanceof Mouse)))
				o.uniformTick();
		}
	}

	public void render(Graphics g)
	{
		main.world.render(g);
		for (UserInterface ui : guiList)
		{
			ui.render(g);
		}
		main.player.render(g);
		for (GameObject o : objects)
		{
			o.render(g);
		}
	}

	public void addGameObject(GameObject o)
	{
		objects.add(o);
	}

	public void addUserInterfaceObject(Main main, BufferedImage image, int fontSize, Color fontColor, Font font,
			String text)
	{
		int y = 0;
		for (UserInterface ui : guiList)
		{
			ui.xImage = 0;
			ui.yImage = guiList.indexOf(ui);
			y = ui.yImage + 1;
		}

		guiList.add(new Interface(main, image, 0, y, fontSize, fontColor, font, text));
	}

	public void removeGameObject(GameObject o)
	{
		objects.remove(o);
	}

	public void removeUserInterfaceObject(UserInterface ui)
	{
		guiList.remove(ui);
	}
}
