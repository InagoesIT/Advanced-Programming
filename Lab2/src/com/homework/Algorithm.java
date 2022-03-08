package com.homework;

public abstract class Algorithm
{
	protected Problem problem;

	public Algorithm(Problem problem)
	{
		this.problem = problem;
	}

	public abstract Solution solve();
}
