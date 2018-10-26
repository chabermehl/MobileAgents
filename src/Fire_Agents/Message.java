package Fire_Agents;

public class Message {

    /**
     * The types of messages that can be sent
     * depending on the circumstances
     */
    enum MessageType
    {
        NODE_DIED,
        NODE_IN_DANGER,
        CREATE_AGENT,
        TRAVERSE_AGENT
    }

    private MessageType messageType;

    public Message(MessageType messageType)
    {
        this.messageType = messageType;
    }

    /**
     * Returns the message type of this message
     * @return this message's type
     */
    public MessageType getMessageType()
    {
        return this.messageType;
    }
}
