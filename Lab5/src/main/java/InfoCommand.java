import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class InfoCommand implements Command
{
	private InfoCommand()
	{
	}

	public static void execute(String path) throws IOException, TikaException, SAXException, FileNotSupportedException
	{
		if (path.contains("html"))
			throw new FileNotSupportedException("html");

		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputStream = null;
		ParseContext context = new ParseContext();

		try
		{
			inputStream = new FileInputStream(path);
		} catch (FileNotFoundException exception)
		{
			exception.printStackTrace();
		}

		try
		{
			parser.parse(inputStream, handler, metadata, context);
		} catch (IOException | SAXException | TikaException exception)
		{
			exception.printStackTrace();
		}

		System.out.println(handler);
		String[] metadataNames = metadata.names();
		for (String name : metadataNames)
		{
			System.out.println(name + ": " + metadata.get(name));
		}
	}
}
