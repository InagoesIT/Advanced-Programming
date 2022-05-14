import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread
{
	private static final String CLIENT_EXITED= "The client exited...";
	private static final String SERVER_STOP_RESULT = "Server stopped";
	private static final String SERVER_STOP_OUTPUT = "~~The server is stopping...~~";
	private static final String COMMAND_STOP = "stop";

	private final Socket socket;

	public ClientThread(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run()
	{
		// Get the request from the input stream: client → server
		try (BufferedReader socketIn = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		     PrintWriter socketOut = new PrintWriter(socket.getOutputStream()))
		{
			String request;
			String response;

			while (true)
			{
				request = socketIn.readLine();
				if (request == null)
				{
					System.out.println(CLIENT_EXITED);
					break;
				}

				System.out.println("ClientThread: A client sent the request: " + request);

				// Send the response to the output stream: server → client
				response = "Server received the request ...";
				if (request.equals(COMMAND_STOP))
					response = SERVER_STOP_RESULT;

				socketOut.println(response);
				socketOut.flush();

				//if we received "stop" → stop the server
				if (request.equals(COMMAND_STOP))
				{
					System.out.println(SERVER_STOP_OUTPUT);
					System.exit(0);
				}
			}
		} catch (IOException exception)
		{
			System.err.println("Communication error... " + exception);
		}
	}
}
