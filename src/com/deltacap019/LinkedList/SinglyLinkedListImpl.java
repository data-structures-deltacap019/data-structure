package com.deltacap019.LinkedList;

import com.deltacap019.utility.ConsoleColors;

/**
 * Basic LINKED LIST implementation.
 * <p>
 * The idea of implementing your own instead of using the predefined Linked List in Java
 * is to get the basic idea how it works and to learn optimizations on it.
 */

public class SinglyLinkedListImpl {

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

        // Sets the data stored in this node.   "If we can pass the data in constructor what is the actual need of it? "
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

    private int length;
    private SLLNode head;

    public SinglyLinkedListImpl() {
        length = 0; // set to 0 as we are creating a new list as it will not haveany elements.
    }

    public void test() {

        // create list here
        int noOfElements = 5;
        int  count = 0;

        while (count < noOfElements) {
            insertAtEnd(new SLLNode(count));
            count++;
        }
        printLinkedList("Initial list");

        // insert at beginning
        insertAtBeginning(new SLLNode(10));
        printLinkedList("Inserting 10 at beginning");

        insertAtBeginning(null);
        printLinkedList("Inserting null at beginning");

        // insert at end
        insertAtEnd(new SLLNode(11));
        printLinkedList("inserting 11 at end");

        insertAtEnd(null);
        printLinkedList("inserting null at end");

        // insert at position
        insertAtPosition(new SLLNode(33), 3);
        printLinkedList("inserting 33 at 3");

        insertAtPosition(new SLLNode(44), 0);
        printLinkedList("inserting 44 at 0");

        insertAtPosition(new SLLNode(55), length);
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
        deleteMatchedNode(new SLLNode(3));
        printLinkedList("deleting matched node 3" );

    }

    //region Insertion

    private void insertAtEnd(SLLNode node) {

        if(node == null){
            return;
        } else if (head == null) {
            head = node;
        } else {
            SLLNode temp, q; // we can store the value of "p.nextNode" in q to remove one call and make use of local reference.
            temp = head;
            while (temp.getNextNode() != null) {
                temp = temp.getNextNode();
            }
            temp.setNextNode(node); // p contains the tail now so we updated its next pointer.
        }

        // as we are inserting element in this method we can update the "length" of the this list. so as to avoid iterating
        // the whole list for counting elements.
        length++;
    }

    private void insertAtBeginning(SLLNode node) {

        if(node == null)
        {
            return;
        }
        if (head != null) {
            node.setNextNode(head);
        }
        head = node;
        length++;
    }

    private void insertAtPosition(SLLNode node, int position) {

        if(node == null){
            return;
        }
        if (position < 0) {
            position = 0;
        }

        if(position > length) {
            position = length;
        }

        // if head = null
        if (head == null) {
            head = node;
        }else if(position == 0) {
            node.nextNode = head;
            head = node;
        }else {
            int count = 1; //counting position as we traverse through linked list
            SLLNode temp = head;

            while (count < position) { // or < position
                temp = temp.getNextNode();
                count++;
            }
            node.setNextNode(temp.getNextNode());
            temp.setNextNode(node);
        }
        length++;
    }

    //endregion

    //region deletion

    private void deleteAtEnd() {
        if (head == null ) {
            return;
        }

        SLLNode temp = head, prev = null;
        while (temp.getNextNode() != null) {
            prev = temp;
            temp = temp.getNextNode();
        }
        prev.setNextNode(null);
        length--;
    }

    private void deleteAtBeginning() {

        if(head == null) {
            return;
        }
        SLLNode node = head;
        head = node.getNextNode();  // we have moved the head to new node but previous node still have next pointer set
                                    // 1(head) -> 2 -> 3 -> null
                                    // 1 -> 2(head) -> 3 -> null
                                    // so w nee to get rid of 1st node by setting its next pointer to null and which will
                                    // allow it for garbage collection.
        node.setNextNode(null);
        length--;
        return;
    }

    private void deleteAtPosition(int position) {
        if (position < 0) {
            position = 0;
        }
        if (position == 0){
            deleteAtBeginning();
            return;
        }

        if (position > length){
            deleteAtEnd();
            return;
        }

        SLLNode temp = head;
        int count = 1;
        while (count < position) {
            temp = temp.getNextNode();
            count++;
        }
        temp.setNextNode(temp.getNextNode().getNextNode());
        length--;
    }

    private void deleteMatchedNode(SLLNode nodeToBeDeleted) {
        if(nodeToBeDeleted == null){
            return;
        }

        if(head == null) {
            return;
        }

        if(nodeToBeDeleted.equals(head))
        {
            deleteAtBeginning();
            length--;
        }

        SLLNode temp = head, prevNode = null;
        while (temp.getNextNode() != null) {
            if(temp.equals(nodeToBeDeleted)) {
                prevNode.setNextNode(temp.getNextNode());
                length--;
            }
            prevNode = temp;
            temp = temp.getNextNode();
        }
    }

    //endregion

    //region utility methods

    private int getPosition(int data) {

        if(head == null) {
            return -1;
        }
        SLLNode temp = head;
        int pos = 0;
        while (temp.getNextNode() != null) {
            if(data == temp.getData()){
                return pos;
            }
            temp = temp.getNextNode();
            pos ++;
        }
        return -1;
    }

    private int getLength() {
        return length;
    }

    // Size of the list.
    private boolean isEmpty(){
        return length==0;
    }

    // Remove everything from the list.
    public void clearList(){
        head = null;
        length = 0;
    }

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
        SLLNode temp = head.getNextNode();
        while (temp != null) {
            result = result + "," + temp.getData();
            temp = temp.getNextNode();
        }
        return result + "]";
    }

    //endregion
}
