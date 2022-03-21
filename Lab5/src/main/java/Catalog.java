import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable
{
	private String name;
	private List<Item> items;

	public Catalog(){}

	public Catalog(String name)
	{
		this.name = name;
		this.items = new ArrayList<>();
	}

	public Catalog(Catalog catalog)
	{
		this.name = catalog.getName();
		this.items = catalog.getItems();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name == null || name.trim().equals(""))
			throw new IllegalArgumentException(
					"Name should not be empty.");

		if (!name.matches("[a-zA-Z ]+"))
			throw new IllegalArgumentException(
					"Name should only contain characters: " + name);

		this.name = name;
	}

	public boolean setToIndex(int index, Item item)
	{
		int duplicatesNr = (int) items.stream().filter(item1 -> item1.getId().equals(item.getId())).count();
		if (duplicatesNr != 0)
			return false;
		else
		{
			if (index >= items.size())
				return false;
			items.set(index, item);
			return true;
		}
	}

	public boolean add(Item item)
	{
		int duplicatesNr = (int) items.stream().filter(item1 -> item1.getId().equals(item.getId())).count();
		if (duplicatesNr != 0)
			return false;
		else
		{
			items.add(item);
			return true;
		}
	}

	public Item findByIndex(int index)
	{
		return items.get(index);
	}

	public Item findById(String id)
	{
		return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
	}

	public List<Item> getItems()
	{
		return items;
	}

	@Override
	public String toString()
	{
		return "Catalog{" +
				"name='" + name + '\'' +
				", items=" + items +
				'}';
	}
}
