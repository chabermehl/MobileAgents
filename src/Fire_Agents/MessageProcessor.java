package Fire_Agents;

import java.util.concurrent.BlockingQueue;

public abstract class MessageProcessor {

    private BlockingQueue<Message> messageQueue;

    private void RecieveMessage(Message message)
    {
        if(message != null)
            messageQueue.add(message);

        processMessage(message);
    }

    protected void SendMessage(Message message, MessageProcessor mp)
    {
        mp.RecieveMessage(message);
    }

    protected Message getNextMessage() throws InterruptedException {
        return messageQueue.take();
    }


    protected abstract void processMessage(Message message);
}
