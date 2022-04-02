import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command
{
	private static final String ITEM_PATH = "/mnt/uni/courses/PA/item.json";

	private ViewCommand(){}

	public static void execute(Item item)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		try
		{
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ITEM_PATH), item);
		}
		catch (IOException ioException)
		{
			System.err.println(ioException);
			ioException.printStackTrace();
		}
		try
		{
			File itemFile = new File(ITEM_PATH);
			Desktop.getDesktop().open(itemFile);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}