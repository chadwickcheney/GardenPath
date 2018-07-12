import java.awt.Graphics;
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
		//restructureObjectList();
		main.world.tick();
		for (UserInterface ui: guiList)
		{
			ui.tick();
		}
		main.player.tick();
		for (GameObject o: objects)
		{
			o.tick();
			if (!(o instanceof Player))
				o.uniformTick();
		}
	}

	public void render(Graphics g)
	{
		main.world.render(g);
		for (UserInterface ui: guiList)
		{
			ui.render(g);
		}
		main.player.render(g);
		for (GameObject o: objects)
		{
			o.render(g);
		}
	}

	public void addGameObject(GameObject o)
	{
		objects.add(o);
	}

	public void addUserInterfaceObject(UserInterface ui)
	{
		guiList.add(ui);
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
