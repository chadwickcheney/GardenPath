import java.awt.Graphics;

public class Handler 
{
	private Main main;
	
	public Handler(Main main)
	{
		this.main = main;
	}
	
	public void tick()
	{
		//restructureObjectList();
		main.world.tick();
		main.player.tick();
		for (GameObject o: main.objects)
		{
			o.tick();
			if (!(o instanceof Player))
				o.uniformTick();
		}
	}
	
	public void render(Graphics g)
	{
		main.world.render(g);
		main.player.render(g);
		for (GameObject o: main.objects)
		{
			o.render(g);
		}
	}
}
