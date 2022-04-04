import java.util.ArrayList;
import java.util.List;

public class Game
{
	private static final String BOLD_ON = "\033[0;1m";
	private static final String BOLD_OFF = "\033[0;0m";
	private final Bag bag = new Bag();
	private final Board board = new Board();
	//private final Dictionary dictionary = new MockDictionary();
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

//	public Dictionary getDictionary()
//	{
//		return dictionary;
//	}

	public void addPlayer(Player player)
	{
		players.add(player);
		player.setGame(this);
	}

	public void play() throws InterruptedException
	{
		// memorise every thread created
		ArrayList<Thread> threads = new ArrayList<>();
		for (Player player : players)
			threads.add(new Thread(player));
		for (Thread thread : threads)
			thread.start();
		// wait for everyone to finish
		for (Thread thread : threads)
			thread.join();
		System.out.println(BOLD_ON + "The words created by the players are: " + BOLD_OFF + board);
	}
}
