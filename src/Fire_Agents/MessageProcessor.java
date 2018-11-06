package Fire_Agents;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class MessageProcessor  {

    private LinkedBlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    private void receiveMessage(Message message) throws InterruptedException {
        if(message != null)
            messageQueue.put(message);
    }

    /**
     * Sends a message to another MessageProcessor
     * @param message to send
     * @param mp processor to send the message to
     */
    protected void sendMessage(Message message, MessageProcessor mp) throws InterruptedException {
        mp.receiveMessage(message);
    }

    /**
     * Blocks the current thread until a message becomes available
     * @return next message in the queue
     * @throws InterruptedException
     */
    protected Message waitNextMessage() throws InterruptedException {
        return messageQueue.take();
    }

    /**
     * Polls the next message instead of waiting for one to become available
     * @return next message in the queue, or null if there are no messages
     * @throws InterruptedException
     */
    protected Message pollNextMessage() throws InterruptedException {
        return messageQueue.poll();
    }

    protected abstract void processMessage(Message message) throws InterruptedException;
}
