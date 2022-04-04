import java.util.*;

public class Player implements Runnable
{
	private String name;
	private Game game;
	private boolean running;
	//private final Random random;

	public Player(String name)
	{
		this.name = name;
		//random = new Random();
	}

	@Override
	public void run()
	{
		int howMany = 7;

		while (howMany != 0)
		{
			try
			{
				howMany = submitWord(howMany);
			} catch (InterruptedException exception)
			{
				System.out.println(this.getName() + " has left the game :'( ...");
				exception.printStackTrace();
			}
		}
		System.out.println("FROM:" + this.getName() + " -> The game has ended."
				+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private int submitWord(int howMany) throws InterruptedException
	{
		List<Tile> extracted = game.getBag().extractTiles(howMany);
		if (extracted.isEmpty())
			return 0;

		// choose a subset and permute it to generate a sequence of tiles
//		int wordSize;
//		if (extracted.size() <= 4)
//			wordSize = extracted.size();
//		else
//			wordSize = random.nextInt(4, extracted.size());
//
//		Set<Tile> tiles = new HashSet<>();
//		for (int i = 0; i < wordSize; i++)
//		{
//			tiles.add(extracted.get(i));
//		}

		// be greedy and "create" a word with all the tiles
		Set<Tile> tiles = new HashSet<>(extracted);
		game.getBoard().addWord(this, tiles.stream().toList());

		Thread.sleep(50);
		return tiles.size();
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
		if(object.getClass() != this.getClass())
			return false;

		return ((Player) object).getName().equals(this.getName());
	}
}
