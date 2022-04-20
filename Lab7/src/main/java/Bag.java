import java.util.*;

public class Bag
{
	private final List<Tile> tiles;
	private final Random random;
	private final Map<Character, Integer> tilesNr;
	private final Map<Character, Integer> tilesPoints;

	public Bag()
	{
		random = new Random();
		tiles = new ArrayList<>();

		tilesNr = new HashMap<>();
		initializeTilesNr();

		tilesPoints = new HashMap<>();
		initializeTilesPoints();

		for (char c = 'A'; c < 'Z'; c++)
		{
			for (int i = 0; i < tilesNr.get(c); i++)
			{
				Tile tile = new Tile(c, tilesPoints.get(c));
				tiles.add(tile);
			}
		}
	}

	public List<Tile> extractTiles(int howMany)
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

	private void initializeTilesNr()
	{
		tilesNr.put('A', 9); tilesNr.put('B', 2);
		tilesNr.put('C', 2); tilesNr.put('D', 4);
		tilesNr.put('E', 12); tilesNr.put('F', 2);
		tilesNr.put('G', 3); tilesNr.put('H', 2);
		tilesNr.put('I', 9); tilesNr.put('J', 1);
		tilesNr.put('K', 1); tilesNr.put('L', 4);
		tilesNr.put('M', 2); tilesNr.put('N', 6);
		tilesNr.put('O', 8); tilesNr.put('P', 2);
		tilesNr.put('Q', 1); tilesNr.put('R', 6);
		tilesNr.put('S', 4); tilesNr.put('T', 6);
		tilesNr.put('U', 4); tilesNr.put('V', 2);
		tilesNr.put('W', 2); tilesNr.put('X', 1);
		tilesNr.put('Y', 2); tilesNr.put('Z', 1);
	}

	private void initializeTilesPoints()
	{
		tilesPoints.put('A', 1); tilesPoints.put('B', 3);
		tilesPoints.put('C', 3); tilesPoints.put('D', 2);
		tilesPoints.put('E', 1); tilesPoints.put('F', 4);
		tilesPoints.put('G', 2); tilesPoints.put('H', 4);
		tilesPoints.put('I', 1); tilesPoints.put('J', 8);
		tilesPoints.put('K', 5); tilesPoints.put('L', 1);
		tilesPoints.put('M', 3); tilesPoints.put('N', 1);
		tilesPoints.put('O', 1); tilesPoints.put('P', 3);
		tilesPoints.put('Q', 10); tilesPoints.put('R', 1);
		tilesPoints.put('S', 1); tilesPoints.put('T', 1);
		tilesPoints.put('U', 1); tilesPoints.put('V', 4);
		tilesPoints.put('W', 4); tilesPoints.put('X', 8);
		tilesPoints.put('Y', 4); tilesPoints.put('Z', 10);
	}
}
