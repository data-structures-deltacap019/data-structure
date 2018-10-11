package com.deltacap019.LinkedList;

import com.deltacap019.utility.ConsoleColors;

/**
 * Basic DOUBLY LINKED LIST implementation.
 * <p>
 * The idea of implementing your own instead of using the predefined Doubly Linked List in Java
 * is to get the basic idea how it works and to learn optimizations on it.
 */

public class DoublyLinkedListImpl {

    /**
     * Holds data and pointers
     */

    private static class DLLNode {

        private int data;
        private DLLNode nextNode;
        private DLLNode prevNode;

        DLLNode(int data) {
            this.data = data;
            nextNode = null;
            prevNode = null;
        }

        int getData() {
            return data;
        }

        void setData(int data) {
            this.data = data;
        }

        DLLNode getNextNode() {
            return nextNode;
        }

        void setNextNode(DLLNode nextNode) {
            this.nextNode = nextNode;
        }

        DLLNode getPrevNode() {
            return prevNode;
        }

        void setPrevNode(DLLNode prevNode) {
            this.prevNode = prevNode;
        }

        @Override
        public boolean equals(Object obj) {

            if(!(obj instanceof DLLNode)) {
                return false;
            }

            DLLNode node = (DLLNode) obj;

            return this.data == node.data;
        }
    }

    private int length = 0;
    private DLLNode head = null;

    public DoublyLinkedListImpl() {

    }

    public void test() {

        // create list here
        int noOfElements = 5;
        int  count = 0;

        while (count < noOfElements) {
            insertAtEnd(new DLLNode(count));
            count++;
        }
        printLinkedList("Initial list");

        // insert at beginning
        insertAtBeginning(new DLLNode(10));
        printLinkedList("Inserting 10 at beginning");

        insertAtBeginning(null);
        printLinkedList("Inserting null at beginning");

        // insert at end
        insertAtEnd(new DLLNode(11));
        printLinkedList("inserting 11 at end");

        insertAtEnd(null);
        printLinkedList("inserting null at end");

        // insert at position
        insertAtPosition(new DLLNode(33), 3);
        printLinkedList("inserting 33 at 3");

        insertAtPosition(new DLLNode(44), 0);
        printLinkedList("inserting 44 at 0");

        insertAtPosition(new DLLNode(55), length);
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
//
        deleteAtPosition(3);
        printLinkedList("deleting at position 3");
//
        deleteAtPosition(length);
        printLinkedList("deleting at position " + length);

        // delete particular node
        deleteMatchedNode(new DLLNode(3));
        printLinkedList("deleting matched node 3" );

    }

    //region Insertion

    private void insertAtBeginning(DLLNode node) {
        if(node == null) {
            return;
        }
        if (head == null) {
            head = node;
            return;
        }

        node.setNextNode(head);
        head.setPrevNode(node);
        head = node;

        length++;
    }

    private void insertAtEnd(DLLNode node) {
        if(node == null) {
            return;
        }

        if(head == null){
            head = node;
            length++;
            return;
        }

        DLLNode temp = head;
        while (temp.getNextNode()!=null) {
            temp = temp.getNextNode();
        }

        node.setNextNode(temp.getNextNode());
        node.setPrevNode(temp);
        temp.setNextNode(node);

        length++;
    }

    private void insertAtPosition(DLLNode node, int position) {
        if(node == null){
            return;
        }
        if(position < 0) {
            position = 0;
        }
        if(position > length) {
            position = length;
        }
        if(head == null){
            head = node;
            length++;
            return;
        }

        if(position == 0) {
//            node.setPtrDiff(head);
//            head.setPrevNode(node);
//            head = node;
            insertAtBeginning(node);
            return;
        }

        if(position == length) {
            insertAtEnd(node);
            return;
        }

        int count = 0;
        DLLNode temp = head;
        while (count < position){
            count++;
            temp = temp.getNextNode();
        }

        node.setNextNode(temp);
        node.setPrevNode(temp.getPrevNode());
        temp.getPrevNode().setNextNode(node); // causes crash for position zero that's
                                            // why it was handled for position 0 differently
        //                                  // in example
        temp.setPrevNode(node);

        length++;
    }

    //endregion

    //region deletion

    private void deleteAtBeginning() {
        if(head == null) {
            return;
        }
        DLLNode node = head.getNextNode();
        node.setPrevNode(null);
        head = node;

        length--;
    }

    private void deleteAtEnd() {
        if (head == null) {
            return;
        }

        DLLNode temp = head;
        while (temp.getNextNode() != null) {
            temp = temp.getNextNode();
        }
        temp.getPrevNode().setNextNode(null);
        length--;
    }

    private void deleteAtPosition(int position) {

        if (position < 0) {
            position = 0;
        }

        if (position >= length) {
            position = length;
        }

        if(position == 0) {
            deleteAtBeginning();
            return;
        }

        if(position == length) {
            deleteAtEnd();
            return;
        }

        int count = 0;
        DLLNode temp = head, prevNode, nextNode;

        while (count < position) {
            temp =  temp.getNextNode();
            count++;
        }

        prevNode = temp.getPrevNode();
        nextNode = temp.getNextNode();
        prevNode.setNextNode(nextNode);
        nextNode.setPrevNode(prevNode);

        length--;
    }

    private void deleteMatchedNode(DLLNode node) {

        if (node == null) {
            return;
        }

        if (head == null) {
            return;
        }

        if (head.equals(node)) {
            deleteAtBeginning();
            return;
        }

        DLLNode temp = head, prevNode = null, nextNode = null;
        while (temp.getNextNode() != null) {
            if (temp.equals(node)) {
                prevNode = temp.getPrevNode();
                nextNode = temp.getNextNode();

                prevNode.setNextNode(nextNode);
                nextNode.setPrevNode(prevNode);
                length--;
            }
            //prevNode = temp.getPrevNode();
            temp = temp.getNextNode();

        }

    }
    //endregion

    //region utility

    private void printLinkedList(String message) {
        System.out.println(ConsoleColors.RED + "Linked List: " + ConsoleColors.BLUE + message + ConsoleColors.BLACK + toString());
        System.out.println(ConsoleColors.BLUE + "HEAD ="  + head.getData());
    }

    @Override
    public String toString() {
        String result = "[";
        if (head == null) {
            return result+"]";
        }
        result = result + head.getData();
        DLLNode temp = head.getNextNode();
        while (temp != null) {
            result = result + "," + temp.getData();
            temp = temp.getNextNode();
        }
        return result + "]";
    }
    //endregion
}
