import java.util.*;

public class Board
{
	// every player will have a list of words
	private final Map<Player, List<String>> words;
	private final Map<Player, Integer> playersPoints;
	private final AspellDictionary dictionary;

	public Board()
	{
		this.words = new HashMap<>();
		this.playersPoints = new HashMap<>();
		this.dictionary = new AspellDictionary();
	}

	public String getWordFromTiles(List<Tile> tiles)
	{
		StringBuilder word = new StringBuilder();
		for (Tile tile: tiles)
			word.append(tile.getLetter());

		return word.toString();
	}

	public boolean addWord(Player player, List<Tile> tiles)
	{
		String word = getWordFromTiles(tiles);

		if (dictionary.isWord(word))
		{
			if (words.get(player) == null)
				words.put(player, new ArrayList<>(Collections.singleton(word)));
			else
				words.get(player).add(word);

			int points = 0;
			for (Tile tile : tiles)
				points += tile.getPoints();

			// add the points to the player
			if (this.playersPoints.get(player) == null)
				playersPoints.put(player, points);
			else
				this.playersPoints.put(player, this.playersPoints.get(player) + points);

			return true;
		}
		else
			return false;
	}

	public Player getWinner()
	{
		int max = 0;
		Player winner = null;

		for (Map.Entry<Player, Integer> entry : playersPoints.entrySet())
		{
			if (entry.getValue() > max)
			{
				max = entry.getValue();
				winner = entry.getKey();
			}
		}
		return winner;
	}

	@Override
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		output.append("\n{\n");
		for (Map.Entry<Player,List<String>> entry : this.words.entrySet())
		{
			output.append(entry.getKey().getName()).append("=").append(entry.getValue());
			output.append("\n");
		}
		output.append("}");
		return output.toString();
	}
}
