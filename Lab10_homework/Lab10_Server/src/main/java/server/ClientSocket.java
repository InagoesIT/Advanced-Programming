package server;

import client.ClientList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientSocket extends Thread
{
	private final Socket socket;
	private String loggedUser = null;

	public ClientSocket(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run()
	{
		try
		{
			//set the time the thread will wait on read() on the socket
			//2.5 minutes
			final int TIMEOUT = 150_000;
			socket.setSoTimeout(TIMEOUT);

			while (true)
			{
				//Getting the request
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String request = reader.readLine();
				if (request == null)
					break;

				//Preparing the response
				String response = RequestHandler.newRequest(this, request);

				//Sending the response
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				writer.println(response);
				writer.flush();

				if (request.equals("exit"))
					break;

				if (response.equals("Goodbye"))
				{
					Server.running = false;
					return;
				}
			}

		}
		catch (SocketTimeoutException exception)
		{
			System.out.println("I have waited too long for a read so I'm closing the connection.");
			System.out.println("Bye!");
			ClientList.save(Server.PATH);

			try(PrintWriter writer = new PrintWriter(socket.getOutputStream()))
			{
				writer.println("Server stopping ...");
				writer.flush();
			} catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
			try
			{
				socket.close();
			} catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
			return;
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		//try to close the socket
		try
		{
			socket.close();
			System.out.println("A client exited, so a thread has closed.");
		} catch (IOException exception)
		{
			exception.printStackTrace();
		}
		ClientList.save(Server.PATH);
	}

	public String getLoggedUser()
	{
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser)
	{
		this.loggedUser = loggedUser;
	}
}
