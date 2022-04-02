public class FileNotSupportedException extends Exception
{
	public FileNotSupportedException(String fileType)
	{
		super("The " + fileType + " format is not supported.");
	}
}
