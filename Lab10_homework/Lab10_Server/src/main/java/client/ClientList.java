package client;

import message.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientList
{

	private static final List<Client> clients = new ArrayList<>();

	private ClientList()
	{
	}

	//add the client if their name is new
	public static void addClient(Client client)
	{
		for (Client client1 : clients)
			if (client1.getName().equals(client.getName()))
				return;

		clients.add(client);
	}

	public static Client findByName(String name)
	{
		for (Client client : clients)
			if (client.getName().equals(name))
				return client;

		return null;
	}

	//write all data about the clients in the file "path"
	public static void save(String path)
	{
		try (FileWriter writer = new FileWriter(path))
		{
			for (Client client : clients)
			{
				writer.write("Name: " + client.getName() + "\nFriends: ");

				List<Client> friends = client.getFriends();
				for (int i = 0; i < friends.size() - 1; i++)
					writer.write(friends.get(i).getName() + ", ");
				if (!friends.isEmpty())
					writer.write(friends.get(friends.size() - 1).getName());

				writer.write("\nMessages: ");
				List<Message> messages = client.getMessageList().getMessages();
				for (int i = 0; i < messages.size() - 1; i++)
					writer.write(messages.get(i).save() + ", ");
				if (!messages.isEmpty())
					writer.write(messages.get(messages.size() - 1).save());

				writer.write("\n");
			}
		} catch (IOException exception)
		{
			System.out.println(exception);
		}
	}

	//load all the clients' data from the path
	public static void load(String path)
	{
		File file = new File(path);

		try (Scanner reader = new Scanner(file))
		{
			while (reader.hasNextLine())
			{
				String nameLine = reader.nextLine();

				if (!nameLine.isEmpty())
					nameLine = nameLine.substring(6);
				//verify if the format is correct
				if (!reader.hasNextLine())
					return;
				String friendsLine = reader.nextLine();
				if (!reader.hasNextLine())
					return;
				String messagesLine = reader.nextLine();
				Client client = findByName(nameLine);
				if (client == null)
					client = new Client(nameLine);

				if (!friendsLine.equals("Friends: "))
					addFriends(friendsLine, client);

				if (!messagesLine.equals("Messages: "))
					addMessages(messagesLine, client);

				addClient(client);
			}
		} catch (IOException e)
		{
			System.out.println(e);
		}
	}

	private static void addFriends(String friendsLine, Client client)
	{
		if (friendsLine.length() < 9)
			return;

		friendsLine = friendsLine.substring(9);
		String[] friends = friendsLine.split(", ");
		for (String friendName : friends)
		{
			Client friend = findByName(friendName);
			if (friend == null)
			{
				friend = new Client(friendName);
				addClient(friend);
			}
			client.addFriend(friend);
		}
	}

	private static void addMessages(String messagesLine, Client client)
	{
		if (messagesLine.length() < 10)
			return;

		messagesLine = messagesLine.substring(10);
		//extract the sender and the message
		String[] messages = messagesLine.split(", ");
		for (String text : messages)
		{
			//extract the sender name and then the message itself
			String[] messageInfo = text.split(": ");
			Message message = new Message(messageInfo[0], messageInfo[1]);
			client.getMessageList().receiveMessage(message);
		}
	}

}
