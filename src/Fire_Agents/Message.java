package Fire_Agents;


public class Message {

    /**
     * The types of messages that can be sent
     * depending on the circumstances
     */
    enum MessageType {
        NODE_DIED,
        NODE_IN_DANGER,
        CREATE_AGENT,
        TRAVERSE_AGENT,
        CLONE_AGENT
    }

    private final MessageType messageType;
    private final String sender;

    public Message(MessageType messageType, String sender)
    {
        this.messageType = messageType;
        this.sender = sender;
    }

    /**
     * Returns the message type of this message
     * @return this message's type
     */
    public MessageType getMessageType()
    {
        return this.messageType;
    }

    public String getSender() {return this.sender;}
}
