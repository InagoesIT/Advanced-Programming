public class ListCommand implements Command
{
	private ListCommand(){};

	public static void execute(Catalog catalog)
	{
		catalog.getItems().forEach(System.out::println);
	}
}
