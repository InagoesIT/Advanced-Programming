package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Bonus
{
	private int n;
	private String[] words;
	private boolean[][] areNeighbours;
	private boolean[] isDistinct;

	public Bonus(int n, String[] words, boolean[][] areNeighbours)
	{
		this.n = n;
		this.words = Arrays.copyOf(words, n);
		this.areNeighbours = new boolean[n][n];

		for (int i = 0; i < n; i++)
			this.areNeighbours[i] = Arrays.copyOf(areNeighbours[i], n);
		isDistinct = new boolean[n];
	}

	public void solve()
	{
		findDistinctWords();
		getSetOfWords();
	}

	private void findDistinctWords()
	{
		for (int i = 0; i < n; i++)
			isDistinct[i] = true;

		// find the copies of i
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if(i != j && isDistinct[i] && isDistinct[j] &&
						words[i].equals(words[j]))
				{
					isDistinct[j] = false;
					System.out.println(j);
				}
			}
		}
	}

	// the second element and the last element must contain a neighbour of W1
	// such a subset is > 3, so if there are less than 2 neighbours (for the richest
	// in neighbours word), then there isn't any set of a longer length
	private void getSetOfWords()
	{
		int[] maxNeighboursWord;
		maxNeighboursWord = getWordAndMaxNeighbours();

		if (maxNeighboursWord[1] < 3)
			System.out.println("There is no set that respects the condition!");
		else
			displayNeighboursOf(maxNeighboursWord[0]);
	}

	private int[] getWordAndMaxNeighbours()
	{
		int sum;
		int max = 0;
		int indexMax = 0;

		for (int i = 0; i < n; i++)
		{
			if (!isDistinct[i])
				continue;
			sum = -1; // so we don't count itself as a neighbour
			for (int j = 0; j < n; j++)
				if (isDistinct[i] && isDistinct[j] && areNeighbours[i][j])
					sum++;

			if (sum > max)
			{
				indexMax = i;
				max = sum;
			}
		}
		return new int[]{indexMax, max};
	}

	private void displayNeighboursOf(int wordIndex)
	{
		System.out.println("The subset found is: ");
		System.out.print(words[wordIndex]);

		for (int i = 0; i < n; i++ )
			if (i!= wordIndex && isDistinct[i] && areNeighbours[wordIndex][i])
				System.out.print(", " + words[i]);
	}
}
