package Fire_Agents;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class MessageProcessor  {

    private LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    private void recieveMessage(Message message)
    {
        if(message != null)
            messageQueue.add(message);

        processMessage(message);
    }

    protected void sendMessage(Message message, MessageProcessor mp)
    {
        mp.recieveMessage(message);
    }

    protected Message getNextMessage() throws InterruptedException {
        return messageQueue.take();
    }

    protected abstract void processMessage(Message message);
}
