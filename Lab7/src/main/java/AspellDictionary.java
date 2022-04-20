import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AspellDictionary extends Dictionary
{
	private final List<String> words;

	AspellDictionary()
	{
		words = new ArrayList<>();
		File file = new File("/mnt/uni/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab7/src/main/resources/dictionary.txt");
		Scanner input;

		try
		{
			input = new Scanner(file);

			while(input.hasNext())
				words.add(input.nextLine().toUpperCase());

			input.close();
		} catch (FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
	}

	@Override
	public boolean isWord(String word)
	{
		return words.contains(word);
	}
}
