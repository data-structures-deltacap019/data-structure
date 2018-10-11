package com.deltacap019.Queues;

import com.deltacap019.utility.ConsoleColors;

/**
 *
 */
public class LinkedListQueueImpl {

    /**
     * Holds data and pointers
     */
    private static class SLLNode {

        private int data;
        private SLLNode nextNode;

        // creates and empty node.
        SLLNode() {
            this.data = Integer.MIN_VALUE;
            nextNode = null;
        }

        // creates a node with data provided
        SLLNode(int data) {
            this.data = data;
            nextNode = null;
        }

        // returns the node next to the current node on which we will call this method.
        public SLLNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(SLLNode nextNode) {
            this.nextNode = nextNode;
        }

        // Returns the data stored in this node.
        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return Integer.toString(data);
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == this) {
                return true;
            }

            if(!(obj instanceof SLLNode)) {
                return false;
            }

            SLLNode other = (SLLNode) obj;

            return this.data == other.data;

        }
    }

    private SLLNode head, tail;
    private int length = 0;

    public void test() {
        //  push 10 elements.
        for (int i = 0; i < 10 ; i++) {
            enQueue(i);
            printQueue(" for Data = " +i);
        }

        // pop 2 elements.
        for (int i = 0; i < 2 ; i++) {
            deQueue();
            printQueue(" after dequeue ");
        }

        // pop 5 elements.
        for (int i = 0; i < 5 ; i++) {
            deQueue();
            printQueue(" after dequeue ");
        }

        // push 15 elements.

        for (int i = 10; i < 25 ; i++) {
            enQueue(i);
            printQueue(" for Data = " +i);
        }
    }

    private void enQueue(int data) {
        SLLNode newNode = new SLLNode(data);

        if (length == 0) {
            head = newNode;
            tail = newNode;
        }else {
            tail.setNextNode(newNode);
            tail = newNode;
        }
        length++;
    }

    private void deQueue() {
        if (length == 0) {
            System.out.println(ConsoleColors.RED + "Queue EMPTY");
        }
        head = head.getNextNode();
        length--;
    }

    private void printQueue( String message) {
        System.out.println(ConsoleColors.BLUE_BOLD + "Queue" + message);
        System.out.println(ConsoleColors.PURPLE_BRIGHT + toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (head == null) {
            return result.append("]").toString();
        }

        result.append(head.getData());
        SLLNode temp = head.getNextNode();
        while (temp != null) {
            result.append(",");
            result.append(temp.getData());
            temp = temp.getNextNode();
        }

        result.append("]");
        return result.toString();
    }
}
