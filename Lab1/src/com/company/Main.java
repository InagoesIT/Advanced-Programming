package com.company;
import com.company.Homework;

public class Main
{
	public static void main(String[] args)
	{
		//Compulsory.solveExercises();

		// -- Homework --
		int n = 0;
		int p = 0;

		// validate the input for Homework
		if (args.length < 3)
			System.out.println("Please try again and give all the input parameters!");
		else
		{
			try
			{
				n = Integer.parseInt(args[0]);
			} catch (NumberFormatException e)
			{
				System.out.println("Please try again and give a valid n!");
			}

			try
			{
				p = Integer.parseInt(args[1]);
			} catch (NumberFormatException e)
			{
				System.out.println("Please try again and give a valid p!");
			}

			if (n <= 0)
				System.out.println("Please try again and give a valid n!");
			else if (p <= 0)
				System.out.println("Please try again and give a valid p!");
			else
			{
				// generate the alphabet while validating the input
				char[] alphabet = new char[args.length - 2];
				int i;
				for (i = 2; i < args.length; i++)
				{
					if (args[i].length() > 1)
					{
						System.out.println("Please try again and give letters, not words!");
						break;
					}
					alphabet[i - 2] = args[i].charAt(0);
				}

				// check if the alphabet was created
				if (i == args.length)
				{
					// create a Homework object and solve the exercise
					long startTime = 0;

					if (n > 30_000)
						startTime = System.nanoTime();

					Homework homework = new Homework(n, p, alphabet);
					homework.solve();

					if (n > 30_000)
						System.out.println(System.nanoTime() - startTime);


//					// -- Bonus --
//					Bonus bonus = new Bonus(n, homework.getWords(), homework.getAreNeighbours());
//					bonus.solve();
				}
			}
		}

	}
}