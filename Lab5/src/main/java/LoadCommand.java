import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command
{
	private LoadCommand(){}

	public static Catalog execute(String path) throws InvalidCatalogException
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
