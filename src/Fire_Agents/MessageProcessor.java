package Fire_Agents;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class MessageProcessor  {

    private LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    private void receiveMessage(Message message)
    {
        if(message != null)
            messageQueue.add(message);
    }

    protected void sendMessage(Message message, MessageProcessor mp)
    {
        mp.receiveMessage(message);
    }

    protected Message waitNextMessage() throws InterruptedException {
        return messageQueue.take();
    }

    protected Message getNextMessage() throws InterruptedException {
        return messageQueue.isEmpty() ? null : messageQueue.take();
    }

    protected abstract void processMessage(Message message);
}
