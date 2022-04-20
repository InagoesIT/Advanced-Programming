import java.util.List;

public class TimeKeeper implements Runnable
{
	private final List<Player> players;
	private boolean running;

	public TimeKeeper(List<Player> players)
	{
		this.players = players;
		running = true;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	@Override
	public void run()
	{
		long start = System.currentTimeMillis();
		long timeElapsed = System.currentTimeMillis() - start;

		while (timeElapsed /(60*1000F) < 0.3 && running)
		{
			timeElapsed = System.currentTimeMillis() - start;
		}

		if (!running)
		{
			System.out.println("The game has ended after: " + timeElapsed + " millis. ");
			return;
		}

		for (Player player : players)
		{
			if (player.isRunning())
			{
				synchronized (player)
				{
					player.notify();
					player.setRunning(false);
				}
			}
		}

		System.out.println("I stopped the game. The game has ended after: " + timeElapsed + " millis. ");
	}
}
