import java.util.ArrayList;
import java.util.List;

public class Game
{
	private static final String BOLD_ON = "\033[0;1m";
	private static final String BOLD_OFF = "\033[0;0m";
	private final Bag bag = new Bag();
	private final Board board = new Board();
	private final List<Player> players = new ArrayList<>();

	public static void main(String[] args)
	{
		Game game = new Game();
		game.addPlayer(new Player("Player 1"));
		game.addPlayer(new Player("Player 2"));
		game.addPlayer(new Player("Player 3"));

		try
		{
			game.play();
		} catch (InterruptedException exception)
		{
			System.out.println("Threads aren't friendly and didn't want to join...");
			exception.printStackTrace();
		}
	}

	public Bag getBag()
	{
		return bag;
	}

	public Board getBoard()
	{
		return board;
	}

	public void addPlayer(Player player)
	{
		players.add(player);
		player.setGame(this);
	}

	public void play() throws InterruptedException
	{
		for (int i = 0; i < players.size() - 1; i++)
			players.get(i).setNextPlayer(players.get(i + 1));
		players.get(players.size() - 1).setNextPlayer(players.get(0));

		// memorise every thread created
		List<Thread> threads = new ArrayList<>();
		for (Player player : players)
			threads.add(new Thread(player));

		TimeKeeper timeKeeper = new TimeKeeper(players);
		Thread timeKeeperThread = new Thread(timeKeeper);
		timeKeeperThread.start();

		for (Thread thread : threads)
			thread.start();

		// notify first player
		synchronized (players.get(0))
		{
			players.get(0).notify();
		}

		for (Thread thread : threads)
			thread.join();
		timeKeeper.setRunning(false);

		if (board.getWinner() == null)
			System.out.println("There are no winners because no one submitted words to the board.");
		else
			System.out.println(board.getWinner().getName() + " won.");

		System.out.println(BOLD_ON + "The words created by the players are: " + BOLD_OFF + board);
	}
}
