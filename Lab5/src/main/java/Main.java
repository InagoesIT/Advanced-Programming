import freemarker.template.TemplateException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Main
{
	private static final String BOLD_ON = "\033[0;1m";
	private static final String BOLD_OFF = "\033[0;0m";
	private static final String PATH_JSON = "/mnt/uni/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab5/target/generated-sources/annotations/catalog.json";
	private static final String PATH_HTML = "/mnt/uni/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab5/target/generated-sources/annotations/catalog.html";

	public static void main(String[] args)
	{
		Catalog catalog = Main.create();

		try
		{
			InfoCommand.execute(PATH_JSON);
		}
		catch (TikaException | IOException | SAXException | FileNotSupportedException exception )
		{
			exception.printStackTrace();
		}
		ViewCommand.execute(catalog.findByIndex(0));

		if(AddCommand.execute(catalog, new Article("java6", "The Java", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", 209)))
			System.out.println(BOLD_ON + "New item added successfully!"+ BOLD_OFF);
		else
			System.out.println(BOLD_ON + "The new item wasn't added!" + BOLD_OFF);

		Main.save(catalog);

		try
		{
			catalog = Main.load();
		} catch (InvalidCatalogException exception)
		{
			System.out.println(exception);
			exception.printStackTrace();
		}

		System.out.println(BOLD_ON + "The contents of the catalog: " + BOLD_OFF);
		ListCommand.execute(catalog);

		try
		{
			ReportCommand.execute(catalog, PATH_HTML);
		}
		catch (IOException ioException)
		{
			System.out.println("Write failure!");
			ioException.printStackTrace();
		}
		catch (TemplateException templateException)
		{
			System.out.println("Couldn't add text to template!");
			templateException.printStackTrace();
		}
	}

	public static Catalog create()
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

		return catalog;
	}

	public static void save(Catalog catalog)
	{
		try
		{
			CatalogUtil.save(catalog, PATH_JSON);
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}

	public static Catalog load() throws InvalidCatalogException
	{
		//COMPULSORY
//		try
//		{
//			Catalog loadedCatalog = new Catalog(CatalogUtil.load(PATH_NAME));
//			System.out.print(BOLD_ON + "Loaded Catalog: " + BOLD_OFF);
//			System.out.println(loadedCatalog);
//			System.out.print(BOLD_ON + "Item with id python1: " + BOLD_OFF);
//			System.out.println(loadedCatalog.findById("python1"));
//		} catch (IOException | InvalidCatalogException ioException)
//		{
//			ioException.printStackTrace();
//		}

		try
		{
			Catalog loadedCatalog = new Catalog(LoadCommand.execute(PATH_JSON));
			System.out.print(BOLD_ON + "Loaded Catalog: " + BOLD_OFF);
			System.out.println(loadedCatalog);
			return loadedCatalog;
		} catch (InvalidCatalogException ioException)
		{
			throw ioException;
		}
	}
}