package com.company;

public class Computer extends Node implements Identifiable, Storage
{
	private int storageCapacity;
	private String address;

	public Computer(String name, int storageCapacity, String address)
	{
		super(name);
		this.storageCapacity = storageCapacity;
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}

	public void setStorageCapacity(int storageCapacity)
	{
		this.storageCapacity = storageCapacity;
	}

	// getting the storage capacity specified in a different measurement unit
	public long getStorageCapacity(String unit)
	{
		return switch (unit)
				{
					case "MB" -> (storageCapacity * 1024L);
					case "KB" -> (storageCapacity * 1_048_576L);
					case "B" -> (storageCapacity * 1_073_741_824L);
					default -> storageCapacity;
				};
	}

	@Override
	public int compareTo(Node node)
	{
		if (node == null)
			return -1;
		Identifiable computer = (Identifiable) node;
		return this.address.compareTo(computer.getAddress());
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public long getStorageCapacity()
	{
		return storageCapacity;
	}

	// returns the type of the node, it's name and the cost of the direct paths to some nodes
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder(name + " - COMPUTER;\n\t costs: ");
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

		Node node = (Computer) object;
		return name.equals(node.getName());
	}
}
