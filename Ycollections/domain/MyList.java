package Ycollections.domain;

public interface MyList <E> {
    public void enqueue(E item) throws QueueException;
    public E dequeue();
    public boolean isEmpty();
    public boolean isFull();
    public int size();
}
