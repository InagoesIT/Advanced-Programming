import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	// Define the port on which the server is listening
	private static final int PORT = 8100;

	public static void main(String[] args)
	{
		try (ServerSocket serverSocket = new ServerSocket(PORT))
		{
			// a thread will close the server
			try
			{
				while (true)
				{
					System.out.println("Waiting for a client ...");
					Socket socket = serverSocket.accept();

					// Execute the client's request in a new thread
					new ClientThread(socket).start();
					System.out.println("Connected to a client!");
				}
			} catch (IOException exception)
			{
				exception.printStackTrace();
			}
		} catch (IOException exception)
		{
			System.err.println("Couldn't create a server socket... " + exception);
		}
	}
}
