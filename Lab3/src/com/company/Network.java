package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Network
{
	private ArrayList<Node> nodes;
	private ArrayList<Node> identifiables;

	public Network()
	{
		this.nodes = new ArrayList<>();
		this.identifiables = new ArrayList<>();
	}

	public Network(ArrayList<Node> nodes)
	{
		this.nodes = nodes;
		this.identifiables = new ArrayList<>();
	}

	public boolean addNode(Node node)
	{
		if (nodes.contains(node))
			return false;

		nodes.add(node);
		return true;
	}

	public boolean deleteNode(Node node)
	{
		return nodes.remove(node);
	}

	public Node getNode(int index)
	{
		return nodes.get(index);
	}

	public void printNodes()
	{
		System.out.println("------------The nodes:-------------");
		for (Node node : nodes)
			System.out.println(node.toString());
	}

	// filling the identifiables data member
	private void getIdentifiables()
	{
		identifiables.clear();
		for (Node node : nodes)
			if (node instanceof Identifiable)
				identifiables.add(node);
	}

	public void printIdentifiableNodes()
	{
		getIdentifiables();

		System.out.println("------------The identifiable nodes:-------------");
		Collections.sort(identifiables);

		for (Node node : identifiables)
			System.out.println(node.toString());
	}

	private Node getIdentifiableNode(int index)
	{
		return (Node) identifiables.get(index);
	}

	// using the Floyd-Warshall algorithm to compute all the shortest paths from a node to the other
	public int[][] computeMinCosts ()
	{
		getIdentifiables();
		int size = identifiables.size();
		int i;
		int j;
		int k;
		int[][] costs = new int[size][size];

		// filling the default costs (0 -> same node, INFINITY (999) -> not known or a known cost)
		for (i = 0; i < size; i++)
			for (j = 0; j < size; j++)
				costs[i][j] = getIdentifiableNode(i).getNodeCost(getIdentifiableNode(j));

		// verifying if inserting a new node, k will shorten the path
		for(k = 0; k < size; k++)
			for (i = 0; i < size; i++)
				for (j = 0; j < size; j++)
					if (costs[i][k] + costs[k][j] < costs[i][j])
						costs[i][j] = costs[i][k] + costs[k][j];

		return costs;
	}

	public void displayMinCosts(int[][] costs)
	{
		System.out.println("------------The min costs for identifiable nodes:-------------");
		int i, j;
		System.out.print(getIdentifiableNode(0).getName());

		for (i = 1; i < costs.length; i++)
			System.out.print("    " + getIdentifiableNode(i).getName());
		System.out.println();

		for (i = 0; i < costs.length; i++)
		{
			System.out.print(getIdentifiableNode(i).getName());
			for (j = 0; j < costs.length; j++)
			{
				if (costs[i][j] == Node.INFINITY) // we can't reach that node
					System.out.print("   -");
				else
					System.out.print("   " + costs[i][j]);
			}
			System.out.println();
		}
	}
}
