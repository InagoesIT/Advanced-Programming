package message;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

    private final List<Message> messages = new ArrayList<>();

    public void receiveMessage(Message message){
        messages.add(message);
    }

    public String readMessages(){
        StringBuilder toSend = new StringBuilder();
        for(Message message : messages){
            if(!message.getRead()) {
                toSend.append(message);
                message.setRead();
            }
        }
        if (toSend.toString().compareTo("") == 0)
            return "No messages";
        return toSend.toString();
    }

    public List<Message> getMessages(){
        return messages;
    }
}
