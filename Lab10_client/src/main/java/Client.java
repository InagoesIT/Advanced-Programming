import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
	public static void main(String[] args) throws IOException
	{
		String serverAddress = "127.0.0.1";
		final int PORT = 8100;

		// all the text used in the communication with the server
		final String INPUT_REQUEST = "-> Please type in the request...";
		final String SERVER_RESPONSE = "<- The response from the server: ";
		final String EXIT = "exit";
		final String SERVER_EXITED = "~~We don't have any server to connect to, so the app is being closed.~~";
		final String BYE = "~~Bye!~~";
		final String SERVER_STOPPED = "Server stopped";

		try (
				Socket socket = new Socket(serverAddress, PORT);
				PrintWriter socketOut =
						new PrintWriter(socket.getOutputStream(), true);
				BufferedReader socketIn = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				Scanner scanner = new Scanner(System.in))
		{
			String request;
			String response;

			while (true)
			{
				System.out.println(INPUT_REQUEST);
				request = scanner.nextLine();

				if (request.equals(EXIT))
				{
					System.out.println(BYE);
					break;
				}

				// Send the request to the server
				socketOut.println(request);

				// Wait the response from the server
				response = socketIn.readLine();
				if (response == null)
				{
					System.out.println(SERVER_EXITED);
					System.out.println(BYE);
					break;
				}

				System.out.println(SERVER_RESPONSE + response);
				if (response.equals(SERVER_STOPPED))
				{
					System.out.println(SERVER_EXITED);
					System.out.println(BYE);
					break;
				}
			}

		} catch (UnknownHostException exception)
		{
			System.err.println("No server listening... " + exception);
		}
	}
}