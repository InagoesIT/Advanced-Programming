package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class SocketCreator extends Thread
{
	private final ServerSocket serverSocket;
	private final List<Thread> threads = new ArrayList<>();

	public SocketCreator(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}

	@Override
	public void run()
	{
		Socket socket;

		while (Server.running)
		{
			System.out.println("[server] Waiting for connection ...");
			try
			{
				socket = serverSocket.accept();
				if (Server.running)
				{
					threads.add(new ClientSocket(socket));
					threads.get(threads.size() - 1).start();
				}
			}
			catch (SocketException socketException)
			{
				//wait for the threads to finish their jobs
				for (Thread thread : threads)
				{
					try
					{
						thread.join();
					}
					catch (InterruptedException e) {}
				}
				System.out.println("The server is stopped.");
				System.out.println("BYE!");
				break;
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
	}
}
