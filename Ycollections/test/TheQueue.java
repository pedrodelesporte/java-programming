package Ycollections.test;

import Ycollections.domain.MyList;
import Ycollections.domain.QueueException;

import java.util.Queue;

public class TheQueue <E> implements MyList<E> {


    E[] elements;
    int size;
    int capacity;



    public TheQueue(int capacity) {
        this();
        this.capacity = capacity;
    }
    @SuppressWarnings("unchecked")
    public TheQueue() {
        capacity = capacity;
        size = 0;
        elements = (E[]) new Object[capacity];
        //YOU CANNOT CREATE AN OBJECT OF A GENERIC
        //THEN IT MUST BE CAST IT THEN OBJECT
        //UNCHECKED TO TELL JAVA W EKNOW WHAT WE ARE DOING
    }



    @Override
    public void enqueue(E item) throws QueueException {
        if (isFull()) {
            throw new QueueException("Queue is full");
        }

        elements[size++] = item;

    }

    private void shiftElements(){
        for(int i = 1; i < size; i++){
            elements[i-1] = elements[i];
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E temp = elements[0];
        shiftElements();
        size--;
        return temp;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return 0;
    }

    public void blah(){
        System.out.println("not in my list");
    }
}
