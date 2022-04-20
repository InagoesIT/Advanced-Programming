import java.util.*;

public class Player implements Runnable
{
	private String name;
	private Game game;
	private boolean running;
	private Player nextPlayer;
	private List<Tile> permutatedWord;
	private List<List<Tile>> permutations;
	private List<Tile> extracted;

	public Player(String name)
	{
		this.name = name;
		nextPlayer = null;
		running = true;
		permutatedWord = new ArrayList<>();
		permutations = new ArrayList<>();
		extracted = new ArrayList<>();
	}

	public void setNextPlayer(Player nextPlayer)
	{
		this.nextPlayer = nextPlayer;
	}

	@Override
	public void run()
	{
		int howMany = 7;

		synchronized (this)
		{
			while (isRunning())
			{
				try
				{
					wait();
					howMany = submitWord(howMany);
					if (howMany == -1) // couldn't find a word
					{
						howMany = 7;
						extracted = new ArrayList<>();
					}
					synchronized (nextPlayer)
					{
						nextPlayer.notify();
					}
					if (howMany == 0)
						break;
					if (!isRunning())
						System.out.println(this.name + " THE TIMEKEEPER KILLED ME");

				} catch (InterruptedException exception)
				{
					System.out.println(this.getName() + " has left the game :'( ...");
					exception.printStackTrace();
				}
			}
		}

		running = false;
		System.out.println("FROM:" + this.getName() + " -> The game has ended."
				+ "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private int submitWord(int howMany) throws InterruptedException
	{
		extracted.addAll(game.getBag().extractTiles(howMany));
		if (extracted.isEmpty())
			return 0;

		Random random = new Random();
		if (extracted.size() < 3)
			return -1;

		int start = random.nextInt(0, extracted.size()  - 2);

		if (game.getBoard().addWord(this, extracted.subList(start, start + 2)))
			return 2;
//		if (game.getBoard().addWord(this, extracted))
//			return extracted.size();

		System.out.println(this.getName() + " didn't find any word... ");
		return -1;

//		permutations = new ArrayList<>();
//		permutatedWord = new ArrayList<>();
//		permutationFinder(extracted.subList(start, start+3));
//
//		for (List<Tile> word : permutations)
//		{
//			if (game.getBoard().addWord(this, word))
//				return 2;
//		}
	}

	public void permutationFinder(List<Tile> tiles)
	{
		if (tiles.isEmpty())
		{
			permutations.add(permutatedWord);
			permutatedWord = new ArrayList<>();
			return;
		}

		for (int i = 0; i < tiles.size(); i++)
		{
			Tile tile = tiles.get(i);
			List<Tile> newTiles = new ArrayList<>(tiles);
			newTiles.remove(i);
			permutatedWord.add(tile);
			permutationFinder(newTiles);
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}

	public boolean isRunning()
	{
		return running;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	@Override
	public boolean equals(Object object)
	{
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;

		return ((Player) object).getName().equals(this.getName());
	}
}
