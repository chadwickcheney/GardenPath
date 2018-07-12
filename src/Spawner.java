import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Spawner 
{
	private Main main;
	private int framesCreature = 17;
	private HashMap<Integer, Integer> creaturesFramesSite = new HashMap<Integer, Integer>()
	{
		private static final long serialVErsionUID = 1L;
		{
			put(0,0);
			put(1,17);
			put(2,34);
		}		
	};
	
	public Spawner(Main main)
	{
		this.main = main;
	}

	public void initSpawn()
	{
		if (main.debug)
			System.out.println("Spawner initSpawn() started");
		initPlayer();
		spawnGolum(50,50,0);
		if (main.debug)
			System.out.println("Spawnder initSpawn() size of game objects: "+main.objects.size());
	}
	
	public void initPlayer()
	{
		int seed = creaturesFramesSite.get(0);
		List<Frame> subsetFrames = new LinkedList<Frame>();
		for (int i = seed; i < seed+framesCreature; i++)
			subsetFrames.add(main.creatureFrames.get(i));
		main.player = new Player(main, subsetFrames, 0, 0, 0);
	}
	
	public void spawnGolum(int x, int y, int setFrameInit)
	{
		int seed = creaturesFramesSite.get(1);
		List<Frame> subsetFrames = new LinkedList<Frame>();
		for (int i = seed; i < seed+framesCreature; i++)
			subsetFrames.add(main.creatureFrames.get(i));
		main.objects.add(new Citizen(main, subsetFrames, x, y, setFrameInit));
	}
}
