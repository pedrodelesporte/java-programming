package Ycollections.test;

import Ycollections.domain.MyList;

public class Demo {
    public static void main(String[] args) throws Exception {
        TheQueue<Integer> queue = new TheQueue<>(2000000000);

        for (int i = 0; i < 1999999999; i++) {
            queue.enqueue(i);

            long start = System.currentTimeMillis();
            queue.enqueue(5);

            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println(time);


            start = System.currentTimeMillis();
            queue.dequeue();
            end = System.currentTimeMillis();
            time = end - start;
            System.out.println(time);
        }

    }
}
