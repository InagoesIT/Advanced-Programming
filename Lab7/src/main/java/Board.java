import java.util.*;

public class Board
{
	// every player will have a list of words
	private final Map<Player, List<String>> words;
	//private final Map<Player, Integer> playersPoints;

	public Board()
	{
		this.words = new HashMap<>();
		//this.playersPoints = new HashMap<>();
	}

	private String getWordFromTiles(List<Tile> tiles)
	{
		StringBuilder word = new StringBuilder();
		for (Tile tile: tiles)
			word.append(tile.getLetter());

		return word.toString();
	}

	public synchronized boolean addWord(Player player, List<Tile> tiles)
	{
		if (words.get(player) == null)
			words.put(player, new ArrayList<>(Collections.singleton(getWordFromTiles(tiles))));
		else
			words.get(player).add(getWordFromTiles(tiles));

//		int points = 0;
//		for (Tile tile : tiles)
//			points += tile.getPoints() * tiles.size();
//
//		// add the points to the player
//		this.playersPoints.put(player, this.playersPoints.get(player) + points);

//      will be validated by the dictionary here?
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		output.append("\n{\n");
		for (Map.Entry<Player,List<String>> entry : this.words.entrySet())
		{
			output.append(entry.getKey().getName() + "=" + entry.getValue());
			output.append("\n");
		}
		output.append("}");
		return output.toString();
	}
}
