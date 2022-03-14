package com.company;

public class Switch extends Node
{
	public Switch(String name)
	{
		super(name);
	}

	// returns the type of the node, it's name and the cost of the direct paths to some nodes
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder(name + " - SWITCH;\n\t costs: ");
		costs.forEach((node,cost)->{
			result.append(node.getName()).append(" -> ").append(cost).append(", ");
		});
		return result.toString();
	}

	@Override
	public boolean equals(Object object)
	{
		if (object == this)
			return true;
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;

		Node node = (Switch) object;
		return name.equals(node.getName());
	}
}
