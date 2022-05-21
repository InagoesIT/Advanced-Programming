package sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        try (Socket socket = new Socket("127.0.0.1", 8100)) {
            readRequests(socket);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

	private static void readRequests(Socket socket)
	{
		final String SERVER_CLOSED = "Server closed!";
		try {
			String request;
			while (true){
				//Sending a request
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				Scanner keyboard = new Scanner(System.in);
				System.out.println("Enter request: ");
				request = keyboard.nextLine();
				writer.println(request);

				//Receiving a response
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String response = reader.readLine();
				if (response == null)
				{
					System.out.println(SERVER_CLOSED);
					break;
				}
				System.out.println("Server response: " + response);
				if(request.equals("exit") || response.equals("Server stopping ...")){
					System.out.println(SERVER_CLOSED);
					break;
				}
			}
		} catch (IOException exception) {
			System.out.println(SERVER_CLOSED);
		}
	}
}
