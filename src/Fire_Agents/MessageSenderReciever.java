package Fire_Agents;

public interface MessageSenderReciever {

    void processMessage(Message message);
    void sendMessage(Message message, MessageSenderReciever receiver);
    void recieveMessage(Message message);

}
