package client;

import message.MessageList;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private final String name;
    private boolean loggedIn = false;
    private final List<Client> friends = new ArrayList<>();
    private final MessageList messageList = new MessageList();

    public Client(String name){
        this.name = name;
    }

    public void addFriend(Client friend){
        if(isFriend(friend))
            return;

        if(ClientList.findByName(friend.getName()) != null)
            friends.add(friend);
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean isFriend(Client friend){
        for(Client friend1 : friends)
            if(friend1.getName().equals(friend.getName()))
                return true;

        return false;
    }

    public String getName() {
        return name;
    }

    public List<Client> getFriends() {
        return friends;
    }

    public MessageList getMessageList() {
        return messageList;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
