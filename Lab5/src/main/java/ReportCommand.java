import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command
{
	private static final String TEMPLATE_DIR = "/mnt/uni/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab5/src/main/resources";
	private static final String TEMPLATE_NAME = "catalog.ftl";

	private ReportCommand(){}

	public static void execute(Catalog catalog, String path) throws IOException, TemplateException
	{
		Map<String, Object> info = new HashMap<>();
		info.put( "items", catalog.getItems() );
		info.put( "catalog", catalog );

		Configuration configuration = new Configuration(new Version("2.3.23"));
		configuration.setClassForTemplateLoading(Catalog.class, "/");
		configuration.setDefaultEncoding("UTF-8");

		StringWriter out = new StringWriter();
		configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
		Template temp = configuration.getTemplate( TEMPLATE_NAME );
		temp.process( info, out );

		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(path)))
		{
			buffer.write(out.getBuffer().toString());
		}
		catch (Exception exception)
		{
			System.out.println("Couldn't write to the specified file the HTML report.");
			exception.printStackTrace();
		}

		Desktop.getDesktop().open(new File(path));
	}
}
