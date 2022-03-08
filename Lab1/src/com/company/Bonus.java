package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Bonus
{
	private int n;
	private String[] words;
	private Boolean[][] areNeighbours;
	private Boolean[] isDistinct;

	public Bonus(int n, String[] words, Boolean[][] areNeighbours)
	{
		this.n = n;
		this.words = Arrays.copyOf(words, n);
		this.areNeighbours = new Boolean[n][n];

		for (int i = 0; i < n; i++)
			this.areNeighbours[i] = Arrays.copyOf(areNeighbours[i], n);
		isDistinct = new Boolean[n];

	}

	private void findDistinctWordsIndexes()
	{
		for (int i = 0; i < n; i++)
			isDistinct[i] = true;

		// find the copies of i
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if(i != j && Boolean.TRUE.equals(isDistinct[i] && isDistinct[j] &&
						areNeighbours[i][j]) && words[i].compareTo(words[j]) == 0)
				{isDistinct[j] = false;
					System.out.println(j);}
			}
		}
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

	// the second element and the last element must contain a neighbour of W1
	// such a subset is > 3, so if there are less than 2 neighbours (for the richest
	// in neighbours word), then there isn't any set of a longer length
	private void getSetOfWords()
	{
		int[] rsp;
		rsp = getWordAndMaxNeighbours();
		if (rsp[1] < 3)
			System.out.println("There is no set that respects the condition!");
		else
			displayNeighboursOf(rsp[0]);
	}

	// useless)))))))), but wanted to memorise the idea

//	// for a max set could be made to search for the newW with the most neighbours for w1
//	// getting a common neighbour for wi and w1, that has at least one common neighbour with w1
//	private int getNextW(int wi, int w1)
//	{
//		for (int i = 0; i < n; i++)
//		{
//			if (isDistinct[i] && areNeighbours[wi][i] && areNeighbours[w1][i]) // this is a common neighb.
//			{
//				for (int j = 0; j < n; j++) // w1 and i have a common neighbour
//				{
//					if (isDistinct[j] && areNeighbours[w1][j] && areNeighbours[i][j])
//						return i;
//				}
//			}
//		}
//		return -1;
//	}
//
//	// getting a set of words which are distinct and are neighbours W1, ..., Wk
//	// with k >= 3 and Wk+1 = W1; Wi is a neighbour to Wi+1 i min 1, max k
//	private ArrayList<Integer> getSetOfWordsComplex()
//	{
//		ArrayList<Integer> setOfWords = new ArrayList<>();
//		// trying to elongate our string by making the word with the most neighbours to be W1 and Wk+1
//		// because we will always check if the new word Wm+1 added is a neighbour with Wk+1 and WM
//		int w1 = getWordAndMaxNeighbours()[0];
//		int wi = w1;
//		setOfWords.add(w1);
//		int wNew;
//
//		while (true)
//		{
//			wNew = getNextW(wi, w1);
//			if (wNew == -1) // can't add any more neighbours
//				break;
//			setOfWords.add(wNew);
//			wi = wNew;
//
//		}
//		if (setOfWords.size() >= 3)
//			System.out.println("I found a set that respects the conditions!");
//
//		return setOfWords;
//	}


	public void solve()
	{
		findDistinctWordsIndexes();
		getSetOfWords();
	}
}
