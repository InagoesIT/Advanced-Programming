package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework
{
	private final int n;
	private final int p;
	private final char[] alphabet;
	private String[] words;
	private Boolean[][] areNeighbours;

	public Homework(int n, int p, char[] alphabet)
	{
		this.n = n;
		this.p = p;
		this.alphabet = Arrays.copyOf(alphabet, alphabet.length);
		words = new String[n];
		areNeighbours = new Boolean[n][n];
	}

	public String[] getWords()
	{
		return Arrays.copyOf(words, n);
	}

	public Boolean[][] getAreNeighbours()
	{
		Boolean[][] areNeighboursCopy = new Boolean[n][n];
		for (int i = 0; i < n; i++)
			areNeighboursCopy[i] = Arrays.copyOf(areNeighbours[i], n);
		return areNeighboursCopy;
	}

	private void generateWords()
	{
		for (int i = 0; i < n; i++)
		{
			StringBuilder word = new StringBuilder();
			while (word.length() < p)
			{
				int pos = (int) (Math.random() * alphabet.length - 1);
				if (pos < 0) continue;
				word.append(alphabet[pos]);
			}
			words[i] = word.toString();
		}

		System.out.println("The generated words are: " + Arrays.toString(words));
	}

	private void findNeighbours()
	{
		ArrayList<ArrayList<String>> wordNeighbours = new ArrayList<>();
		int i;
		int j;
		int k;

		for (i = 0; i < n; i++)
		{ // the first word -> line
			wordNeighbours.add(new ArrayList<>());
			// add the word itself to the array list
			areNeighbours[i][i] = true;
			wordNeighbours.get(i).add(words[i]);
			for (j = 0; j < n; j++)
			{ // the second word -> column
				if (i != j)
				{
					for (k = 0; k < p; k++) // the index of a char from the 1st word
						// found the charAt(k) in the second word
						if (words[j].indexOf(words[i].charAt(k)) != -1)
						{
							areNeighbours[i][j] = true;
							wordNeighbours.get(i).add(words[j]);
							break;
						}
					if (k == p)
						areNeighbours[i][j] = false;
				}
			}
		}

		if (n < 30_000)
		{
			System.out.println("hai vasile");
			System.out.println("Displaying the neighbours of the first word in the array...");
			for (i = 0; i < n; i++)
				System.out.println(Arrays.toString(wordNeighbours.get(i).toArray()));
		}
	}

	public void solve()
	{
		generateWords();
		findNeighbours();
	}
}
