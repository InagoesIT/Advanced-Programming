package server;

import client.ClientList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
	public static final String PATH = "src/main/resources/data";
	public static volatile boolean running = true;

	private Server()
	{
	}

	public static void create(int port) throws IOException
	{
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			ClientList.load(PATH);

			SocketCreator socketCreator = new SocketCreator(serverSocket);
			socketCreator.start();

			while (running)
				Thread.onSpinWait();

			//interrupt the socketCreator if it is in accept mode
			serverSocket.close();
		}
	}
}