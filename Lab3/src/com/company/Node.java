package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable<Node>
{
	protected String name;
	protected Map<Node, Integer> costs;
	public static final int INFINITY = 999;

	public Node(String name)
	{
		this.name = name;
		costs = new HashMap<>();
	}

	public Node(String name, Map<Node, Integer> timeCost)
	{
		this.name = name;
		this.costs = timeCost;
	}

	public void setNodeCost(Node node, int value)
	{
		costs.put(node, value);
	}

	public int getNodeCost(Node node)
	{
		if (node.equals(this))
			return 0;
		else if (costs.get(node) == null)
			return INFINITY;
		else
			return costs.get(node);
	}

	public Map<Node, Integer> getCosts()
	{
		return new HashMap<>(costs);
	}

	public void setCosts(Map<Node, Integer> costs)
	{
		this.costs = costs;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public abstract boolean equals(Object object);

	@Override
	public int compareTo(Node node)
	{
		if (node == null)
			return -1;

		return this.name.compareTo(node.getName());
	}

	@Override
	public abstract String toString();
}
