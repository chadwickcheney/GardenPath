public class Chore
{

	private String task;
	private boolean done;

	public Chore(String task, boolean done)
	{
		this.task = task;
		this.done = done;
	}

	public boolean getStatus()
	{
		return this.done;
	}

	public String getTask()
	{
		return this.task;
	}
}