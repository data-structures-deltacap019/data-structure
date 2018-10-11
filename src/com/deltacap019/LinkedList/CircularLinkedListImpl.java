package com.deltacap019.LinkedList;

import com.deltacap019.utility.ConsoleColors;

/**
 * Basic CIRCULAR LINKED LIST implementation.
 * <p>
 * The idea of implementing your own instead of using the predefined Doubly Linked List in Java
 * is to get the basic idea how it works and to learn optimizations on it.
 */

public class CircularLinkedListImpl {

    /**
     * Holds data and pointers
     */

    private static class CLLNode {

        private int data;
        private CLLNode nextNode;

        CLLNode(int data) {
            this.data = data;
            nextNode = null;
        }

        int getData() {
            return data;
        }

        void setData(int data) {
            this.data = data;
        }

        CLLNode getNextNode() {
            return nextNode;
        }

        void setNextNode(CLLNode nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public boolean equals(Object obj) {

            if(!(obj instanceof CLLNode)) {
                return false;
            }

            CLLNode node = (CLLNode) obj;

            return this.data == node.data;
        }
    }
    public void test() {

        // create list here
        int noOfElements = 5;
        int  count = 0;

        while (count < noOfElements) {
            insertAtEnd(new CLLNode(count));
            count++;
        }
        printLinkedList("Initial list");

        // insert at beginning
        insertAtBeginning(new CLLNode(10));
        printLinkedList("Inserting 10 at beginning");

        insertAtBeginning(null);
        printLinkedList("Inserting null at beginning");

        // insert at end
        insertAtEnd(new CLLNode(11));
        printLinkedList("inserting 11 at end");

        insertAtEnd(null);
        printLinkedList("inserting null at end");

//        // insert at position
        insertAtPosition(new CLLNode(33), 3);
        printLinkedList("inserting 33 at 3");

        insertAtPosition(new CLLNode(44), 0);
        printLinkedList("inserting 44 at 0");

        insertAtPosition(new CLLNode(55), length);
        printLinkedList("inserting 55 at " + length);

        insertAtPosition(null, 3);
        printLinkedList("inserting null at 3");

        // delete at beginning
        deleteAtBeginning();
        printLinkedList("deleting from beginning");

        // delete at end
        deleteAtEnd();
        printLinkedList("deleting from end");

        // delete at position
        deleteAtPosition(0);
        printLinkedList("deleting at position 0");

        deleteAtPosition(3);
        printLinkedList("deleting at position 3");

        deleteAtPosition(length + 1);
        printLinkedList("deleting at position " + (length + 1));

        // delete particular node
        deleteMatchedNode(new CLLNode(3));
        printLinkedList("deleting matched node 3" );

    }

    private int length = 0;
    private CLLNode tail; // we should save tail here instead of head as with getNext()
                        // we can easily access next element as we are maintaining next node.

    //region inserting node at the beginning

    private void insertAtBeginning(CLLNode node) {

        if(node == null) {
            return;
        }

        if(tail == null) {
            tail = node;
            tail.setNextNode(tail); // make it circular
            length++;
            return;
        }

        CLLNode oldHead = tail.getNextNode();
        tail.setNextNode(node);
        node.setNextNode(oldHead);
        length++;
    }
    //endregion

    //region inserting node a the end

    private void insertAtEnd(CLLNode node) {

        if(node == null) {
            return;
        }

        if(tail == null) {
            tail = node;
            tail.setNextNode(tail); // make it circular
            length++;
            return;
        }

        insertAtBeginning(node); // as it is circular we can insert it on head and later update tail.
        tail = tail.getNextNode();
    }
    //endregion

    //region insert at position

    private void insertAtPosition(CLLNode node, int position) {

        if (node == null) {
            return;
        }

        if (position < 0) {
            position = 0;
        }

        if (position >= length) {
            position = length;
        }

        if (position == 0) {
            insertAtBeginning(node);
            return;
        }

        if (position == length) {
            insertAtEnd(node);
            return;
        }

        CLLNode temp = tail.getNextNode();
        int count = 1;

        while (count < position) {
            temp = temp.getNextNode();
            count++;
        }

        node.setNextNode(temp.getNextNode());
        temp.setNextNode(node);
        length++;
    }
    //endregion

    //region deleting first node

    private void deleteAtBeginning() {
        if (tail == null) {
            return;
        }else if (tail.getNextNode() == tail) { // means only one element
            tail = null;
            length--;
            return;
        }

        tail.setNextNode(tail.getNextNode().getNextNode());
        length--;
    }
    //endregion

    //region deleting last node

    private void deleteAtEnd() {

        if (tail == null) {
            return;
        }  else if (tail.getNextNode() == tail) {
            tail = null;
            length--;
            return;
        }

        CLLNode temp = tail.getNextNode();

        while (temp.getNextNode() != tail) {
            temp = temp.getNextNode();
        }
        temp.setNextNode(tail.getNextNode());
        tail = temp;
        length--;

    }
    //endregion

    //region delete at position

    private void deleteAtPosition(int position) {

        if (position < 0) {
            position = 0;
        }

        if (position > length) {
            position = length;
        }

        if (position == 0) {
            deleteAtBeginning();
            return;
        }

        if (position == length) {
            deleteAtEnd();
            return;
        }

        int count = 1;
        CLLNode temp = tail.getNextNode();

        while (count < position) {
            temp = temp.getNextNode();
            count++;
        }
        temp.setNextNode(temp.getNextNode().getNextNode());
        length--;
    }
    //endregion

    //region delete matched

    private void deleteMatchedNode(CLLNode node) {

        if (node == null) {
            return;
        }

        if(tail.equals(node)) {
            deleteAtEnd();
            return;
        }

        if(tail.getNextNode().equals(node)) {
            deleteAtBeginning();
            return;
        }

        CLLNode temp = tail.getNextNode().getNextNode();
        CLLNode prev = tail.getNextNode();
        while (!temp.equals(tail)) {
            if (temp.equals(node)) {
                prev.setNextNode(temp.getNextNode());
            }
            prev = temp;
            temp = temp.getNextNode();
        }

    }
    //endregion

    private void printLinkedList(String message) {
        System.out.println(ConsoleColors.RED + "Linked List: " + ConsoleColors.BLUE + message + ConsoleColors.BLACK + toString());
        System.out.println(ConsoleColors.BLUE + "HEAD = "  + tail.getNextNode().getData());
        System.out.println(ConsoleColors.BLUE + "TAIL = "  + tail.getData());
    }

    @Override
    public String toString() {
        String result = "[";
        if (tail == null) {
            return result+"]";
        }

        CLLNode temp = tail.getNextNode();
        while (temp != tail) {
            result = result + temp.getData() + ",";
            temp = temp.getNextNode();
        }
        result = result + tail.getData();

        return result + "]";
    }
}
