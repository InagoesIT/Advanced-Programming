package server;

import client.Client;
import client.ClientList;
import message.Message;

public class RequestHandler
{
	public static String newRequest(ClientSocket clientSocket, String request)
	{
		String response = "Command not recognized!";
		final String LOGIN_NEEDED = "You need to be logged-in to perform this command!";

		if (getCommand(request).equals("register"))
			response = register(clientSocket, request);

		else if (getCommand(request).equals("login"))
			response = login(clientSocket, request);

		else if (request.equals("exit"))
			response = "Goodbye";

		else if (request.equals("stop"))
		{
			response = LOGIN_NEEDED;
			if (clientSocket.getLoggedUser() != null)
				response = stop();
		}
		else if (getCommand(request).equals("friend"))
		{
			response = LOGIN_NEEDED;
			if (clientSocket.getLoggedUser() != null)
				response = friend(clientSocket, request);
		}
		else if (getCommand(request).equals("send"))
		{
			response = LOGIN_NEEDED;
			if (clientSocket.getLoggedUser() != null)
				response = send(clientSocket, request);
		}
		else if (getCommand(request).equals("read"))
		{
			response = LOGIN_NEEDED;
			if (clientSocket.getLoggedUser() != null)
				response = read(clientSocket, request);
		}

		return response;
	}

	private static String getCommand(String request)
	{
		String[] req = request.split(" ");
		return req[0];
	}

	private static String stop()
	{
		ClientList.save(Server.PATH);
		Server.running = false;
		return "Server stopping ...";
	}

	//register the user
	private static String register(ClientSocket clientSocket, String request)
	{
		String[] req = request.split(" ");
		if (ClientList.findByName(req[1]) == null)
		{
			ClientList.addClient(new Client(req[1]));
			ClientList.findByName(req[1]).setLoggedIn(true);
			clientSocket.setLoggedUser(req[1]);
			return "Registered successfully!";
		}
		return "Name already taken!";
	}

	//log in the user
	private static String login(ClientSocket clientSocket, String request)
	{
		String[] req = request.split(" ");
		if (clientSocket.getLoggedUser() != null && clientSocket.getLoggedUser().equals(req[1]))
			return "Already logged in";

		//find if there exists a user registered with the given name
		Client client = ClientList.findByName(req[1]);
		if (client == null)
			return "No user found!";

		if (client.isLoggedIn())
			return "Account already online!";

		//logout any other user logged in on the thread and login the current user
		if (clientSocket.getLoggedUser() != null)
			ClientList.findByName(clientSocket.getLoggedUser()).setLoggedIn(false);

		client.setLoggedIn(true);
		clientSocket.setLoggedUser(req[1]);
		return "Welcome";
	}

	//add the given friends
	private static String friend(ClientSocket clientSocket, String request)
	{
		String[] friends = request.split(" ");
		Client client = ClientList.findByName(clientSocket.getLoggedUser());
		boolean hasNewFriend = false;

		for (int i = 1; i < friends.length; i++)
		{
			Client friend = ClientList.findByName(friends[i]);
			if (friend != null && !client.isFriend(friend) && !client.getName().equals(friend.getName()))
			{
				client.addFriend(friend);
				friend.addFriend(client);
				hasNewFriend = true;

			}
		}

		if (hasNewFriend)
			return "You are now friends";
		return "None of the friend requests worked";
	}

	//send the message to all the friends
	private static String send(ClientSocket clientSocket, String request)
	{
		Client client = ClientList.findByName(clientSocket.getLoggedUser());
		String message = request.substring(5);
		for (Client friend : client.getFriends())
			friend.getMessageList().receiveMessage(new Message(client.getName(), message));

		return "Message sent successfully!";
	}

	//send all the messages of a client to them
	private static String read(ClientSocket clientSocket, String request)
	{
		return ClientList.findByName(clientSocket.getLoggedUser()).getMessageList().readMessages();
	}
}