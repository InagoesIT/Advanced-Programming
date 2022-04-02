public class AddCommand implements Command
{
	private AddCommand(){}

	public static boolean execute(Catalog catalog, Item item)
	{
		return catalog.add(item);
	}
}
