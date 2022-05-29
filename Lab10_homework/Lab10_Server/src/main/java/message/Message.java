package message;

public class Message {

    private final String senderName;
    private final String content;
    private boolean isRead;

    public Message(String senderName, String message){
        this.senderName = senderName;
        this.content = message;
        isRead = false;
    }

    @Override
    public String toString() {
        return senderName + ": " + content + "; ";
    }

    public String save(){
        return senderName + ": " + content;
    }

    public boolean getRead() {
        return isRead;
    }

    public void setRead() {
        this.isRead = true;
    }
}
