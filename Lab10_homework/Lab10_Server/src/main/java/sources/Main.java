package sources;

import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Server.create(8100);
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
