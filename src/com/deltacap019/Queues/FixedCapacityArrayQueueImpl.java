package com.deltacap019.Queues;

import com.deltacap019.utility.ConsoleColors;

/**
 * Implements Queue using Circular Array of fixed size.
 * In this there is no provision of growing array ones it becomes full.
 * We will create an array of capacity 10 and thereafter we will implement ENQUEUE and DEQUE
 * operations on it.
 */

public class FixedCapacityArrayQueueImpl {

    private int[] queue;
    private int readPointer = 0, writePointer = 0;
    private int capacity = 10;
    private int length = 0;

    public FixedCapacityArrayQueueImpl(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
    }

    public FixedCapacityArrayQueueImpl() {
        queue = new int[capacity];
    }

    public void test() {
        //  push 5 elements.
        for (int i = 0; i < 5 ; i++) {
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
            return;
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
        readPointer = (readPointer + 1) % capacity;
        length--;
    }

    private void printStack( String message) {
        System.out.println(ConsoleColors.BLUE_BOLD + "Queue" + message);
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
