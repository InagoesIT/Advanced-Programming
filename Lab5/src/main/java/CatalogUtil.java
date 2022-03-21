import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class CatalogUtil
{
	private CatalogUtil(){}

	public static void save(Catalog catalog, String path) throws IOException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		try
		{
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), catalog);
		}
		catch (IOException ioException)
		{
			System.err.println(ioException);
			throw ioException;
		}
	}

	public static Catalog load(String path) throws InvalidCatalogException, IOException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		try
		{
			return objectMapper.readValue(new File(path), Catalog.class);
		}
		catch (IOException exception)
		{
			throw new InvalidCatalogException(exception);
		}
	}
}
