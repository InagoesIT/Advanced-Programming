import java.io.IOException;

public class Main
{
	private static final String BOLD_ON = "\033[0;1m";
	private static final String BOLD_OFF = "\033[0;0m";
	private static final String PATH_NAME = "/home/ina/Desktop/catalog.json";

	public static void main(String[] args)
	{
		Main.createAndSave();
		Main.load();
	}

	public static void createAndSave()
	{
		Article java = new Article("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", 20);
		Article python = new Article("python1", "Implementing logistic regression from scratch in Python", "https://developer.ibm.com/articles/implementing-logistic-regression-from-scratch-in-python/", 2000);
		Book knuth = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", "IT");
		Book freud = new Book("freud1", "The Interpretation of Dreams", "/mnt/others/freud.pdf", "psychology");

		Catalog catalog = new Catalog("My refs");
		catalog.add(java);
		catalog.add(python);
		catalog.add(knuth);
		catalog.add(freud);

		System.out.print(BOLD_ON + "Original catalog: " + BOLD_OFF);
		System.out.println(catalog);

		try
		{
			CatalogUtil.save(catalog, PATH_NAME);
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}

	public static void load()
	{
		try
		{
			Catalog loadedCatalog = new Catalog(CatalogUtil.load(PATH_NAME));
			System.out.print(BOLD_ON + "Loaded Catalog: " + BOLD_OFF);
			System.out.println(loadedCatalog);
			System.out.print(BOLD_ON + "Item with id python1: " + BOLD_OFF);
			System.out.println(loadedCatalog.findById("python1"));
		} catch (IOException | InvalidCatalogException ioException)
		{
			ioException.printStackTrace();
		}
	}
}