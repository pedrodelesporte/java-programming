package Ycollections.domain;

public class QueueException extends Exception {

    public QueueException() {
        super("Queue is full");
    }

    public QueueException(String message) {
        super(message);
    }

}
