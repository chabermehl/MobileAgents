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
    private final Object[] data; // optional data that can be associated with this message


    public Message(MessageType messageType, String sender)
    {
        this.messageType = messageType;
        this.sender = sender;
        this.data = null;
    }

    public Message(MessageType messageType, String sender, Object... data)
    {
        this.messageType = messageType;
        this.sender = sender;
        this.data = data;
    }

    public Object[] getData() {return this.data;}

    /**
     * Returns the message type of this message
     * @return this message's type
     */
    public MessageType getMessageType()
    {
        return this.messageType;
    }

    /**
     * Gets the name of sender of this message
     * @return name of sender
     */
    public String getSender() {return this.sender;}
}
