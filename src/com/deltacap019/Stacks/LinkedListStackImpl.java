package com.deltacap019.Stacks;

import com.deltacap019.utility.ConsoleColors;

/**
 * Implements stack implementation using Linked List.
 * We PUSH element at the beginning of the list and POP from same position.
 * We can implement it using the PUSH and POP at the end of the list also, but in that case we have to
 * make extra arrangements to come back to previous node of the tail after POP operation. Whereas it is
 * straight forward if we do operation at tha beginning as in that case after pop operation from the beginning
 * we can easily get the next node that need to be assigned as top.
 */

public class LinkedListStackImpl {

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

    private SLLNode top = null; // stores tail of the Linked List
    private int length = 0;

    public void test() {
        //  push 5 elements.
        for (int i = 0; i < 5 ; i++) {
            push(i);
            printStack(" for Data = " +i);
        }

        // pop 2 elements.
        for (int i = 0; i < 2 ; i++) {
            pop();
            printStack(" after popping ");
        }

        // pop 5 elements.
        for (int i = 0; i < 5 ; i++) {
            pop();
            printStack(" after popping ");
        }

        // push 15 elements.

        for (int i = 10; i < 25 ; i++) {
            push(i);
            printStack(" for Data = " +i);
        }
    }

    private void push(int data) {
       SLLNode newNode = new SLLNode(data);
       newNode.setNextNode(top);
       top = newNode;
       length++;
    }

    private void pop() {
        if(top == null) {
            System.out.println(ConsoleColors.RED + "Stack EMPTY");
            return;
        }
        top = top.getNextNode();
        length--;
    }

    private void printStack( String message) {
        System.out.println(ConsoleColors.BLUE_BOLD + "Stack" + message);
        System.out.println(ConsoleColors.PURPLE_BRIGHT + toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (top == null) {
            return result.append("]").toString();
        }

        result.append(top.getData());
        SLLNode temp = top.getNextNode();
        while (temp != null) {
            result.append("\n");
            result.append(temp.getData());
            result.append(",");
            temp = temp.getNextNode();
        }

        result.append("\n");
        result.append("]");
        return result.toString();
    }
}
