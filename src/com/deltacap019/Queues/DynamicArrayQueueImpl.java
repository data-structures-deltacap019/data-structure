package com.deltacap019.Queues;

import com.deltacap019.utility.ConsoleColors;

/**
 * Implements Queue using Circular Array of Dynamic size.
 * We can grow array once its full by some pre-defined offset or or by doubling to previous size.
 * We will create an array of capacity 10 in the beginning and thereafter we will implement ENQUEUE and DEQUE
 * operations on it.
 */

public class DynamicArrayQueueImpl {

    private int[] queue;
    private int readPointer = 0, writePointer = 0;
    private int capacity = 5;
    private int capacityOffset = 5;
    private int length = 0;

    public DynamicArrayQueueImpl(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
    }

    public DynamicArrayQueueImpl() {
        queue = new int[capacity];
    }

    public void test() {
        //  push 10 elements.
        for (int i = 0; i < 10 ; i++) {
            enQueue(i);
            printStack(" for Data = " +i);
        }

        // pop 2 elements.
        for (int i = 0; i < 2 ; i++) {
            deQueue();
            printStack(" after dequeue ");
        }

        // pop 5 elements.
        for (int i = 0; i < 5 ; i++) {
            deQueue();
            printStack(" after dequeue ");
        }

        // push 15 elements.

        for (int i = 10; i < 25 ; i++) {
            enQueue(i);
            printStack(" for Data = " +i);
        }
    }

    private void enQueue(int data) {
        if(length == capacity){
            System.out.print(ConsoleColors.RED + "Queue Full");
            queue = expand();
        }
        queue[writePointer] = data;
        writePointer = (writePointer + 1) % capacity;
        length++;
    }

    private void deQueue() {
        if(length == 0){
            System.out.print(ConsoleColors.RED + "Queue Empty");
            return;
        }
        if(isShrinkRequired()) {
            // we don't want ot shrink below initial capacity of 10, hence first condition "length > capacity"
            // if there are less than half elements left in array at any given time than we can shrink the size of queue
            // by decrementing it by offset
            queue = shrink();
            readPointer = 0;
        }
        readPointer = (readPointer + 1) % capacity;
        length--;
    }

    private int[] expand() {
        System.out.print(ConsoleColors.RED + "Queue Expanding");
        capacity = capacity + capacityOffset;
        int[] tempQueue = new int[capacity];

        int count = 0;
        while (count < length){
            tempQueue[count++] = queue[readPointer];
            readPointer = (readPointer + 1) % queue.length; // using queue.length instead of capacity here as
                                                            // capacity has been updated here for creating tempQueue.
        }
        readPointer = 0;
        writePointer = length;
        return tempQueue;
    }

    private boolean isShrinkRequired(){
        // [Array length  = 10] [contains elements = 10] [contains 2 times of capacity offset 10/5 = 2] [10/5 = 2 (Q) and 0 (R)]
        // [Array length  = 10] [contains elements = 8] [contains 2 times of capacity offset 10/5 = 2] [8/5 = 1 (Q) and 3 (R)]
        // [Array length  = 10] [contains elements = 7] [contains 2 times of capacity offset 10/5 = 2] [7/5 = 1 (Q) and 2 (R)]
        // [Array length  = 10] [contains elements = 6] [contains 2 times of capacity offset 10/5 = 2] [6/5 = 1 (Q) and 1 (R)]
        // [Array length  = 10] [contains elements = 5] [contains 2 times of capacity offset 10/5 = 2] [5/5 = 1 (Q) and 0 (R)]
        // [Array length  = 10] [contains elements = 4] [contains 2 times of capacity offset 10/5 = 2] [4/5 = 0 (Q) and 4 (R)]

         return  (length % capacityOffset == 0 && queue.length / capacityOffset > length / capacityOffset);
    }

    private int[] shrink() {
        System.out.print(ConsoleColors.RED + "Queue Shrinking");

        capacity = length;

        int[] tempQueue = new int[capacity];

        for (int j= 0,i = readPointer; i < queue.length; j ++, i++) {
            tempQueue[j] = queue[i];
        }
        return tempQueue;
    }

    private void printStack( String message) {
        System.out.println(ConsoleColors.BLUE_BOLD + "Queue" + message);
        System.out.println(ConsoleColors.BLUE_BOLD + "READ = " + queue[readPointer]);
        System.out.println(ConsoleColors.BLUE_BOLD + "WRITE = " + writePointer);
        System.out.println(ConsoleColors.PURPLE_BRIGHT + toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (readPointer == -1) {
            return result.append("]").toString();
        }
        for (int i = 0; i < length; i++) {
            result.append(queue[(readPointer + i) % capacity]);
            result.append(",");
        }
        result.append("]");
        return result.toString();
    }

}
