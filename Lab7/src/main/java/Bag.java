import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag
{
	private final List<Tile> tiles;
	private final Random random;

	public Bag()
	{
		random = new Random();
		tiles = new ArrayList<>();

		for (char c = 'a'; c < 'z'; c++)
		{
			for (int i = 0; i < 10; i++)
			{
				Tile tile = new Tile(c, random.nextInt(1, 10));
				tiles.add(tile);
			}
		}
	}

	public synchronized List<Tile> extractTiles(int howMany)
	{
		List<Tile> extracted = new ArrayList<>();
		for (int i = 0; i < howMany; i++)
		{
			if (tiles.isEmpty())
				break;
			extracted.add(tiles.remove(random.nextInt(0, tiles.size())));
		}
		return extracted;
	}
}
