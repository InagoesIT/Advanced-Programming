package com.company;

public class Router extends Node implements Identifiable
{
	private String address;

	public Router(String name, String address)
	{
		super(name);
		this.address = address;
	}

	public void setAdress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}

	@Override
	public int compareTo(Node node)
	{
		if (node == null)
			return -1;
		Identifiable router = (Identifiable) node;
		return this.address.compareTo(router.getAddress());
	}

	// returns the type of the node, it's name and the cost of the direct paths to some nodes
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder(name + " - ROUTER;\n\t costs: ");
		costs.forEach((node,cost)->{
			result.append(node.getName() + " -> " + cost + ", ");
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

		Node node = (Router) object;
		return name.equals(node.getName());
	}
}
