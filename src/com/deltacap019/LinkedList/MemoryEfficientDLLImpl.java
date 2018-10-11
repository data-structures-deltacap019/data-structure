package com.deltacap019.LinkedList;

import com.deltacap019.utility.ConsoleColors;

import java.util.HashMap;

/**
 * Basic MEMORY EFFICIENT DOUBLY LINKED LIST implementation.
 * <p>
 * The idea of implementing your own instead of using the predefined Linked List in Java
 * is to get the basic idea how it works and to learn optimizations on it.
 */

public class MemoryEfficientDLLImpl {

    /**
     * Holds data and pointers
     */

    private static class MemoryEfficientDLLNode {

        private int data;
        private int ptrDiff = 0; //

        MemoryEfficientDLLNode(int data) {
            this.data = data;
        }

        int getData() {
            return data;
        }

        void setData(int data) {
            this.data = data;
        }

        public int getPtrDiff() {
            return ptrDiff;
        }

        public void setPtrDiff(int ptrDiff) {
            this.ptrDiff = ptrDiff;
        }

        @Override
        public boolean equals(Object obj) {

            if(!(obj instanceof MemoryEfficientDLLNode)) {
                return false;
            }

            MemoryEfficientDLLNode node = (MemoryEfficientDLLNode) obj;

            return this.data == node.data;
        }
    }

    private int length = 0;
    private MemoryEfficientDLLNode head = null;

    private HashMap<Integer, MemoryEfficientDLLNode> pointerMap = new HashMap<>();

    public MemoryEfficientDLLImpl() {

    }

    public void test() {

        // create list here
        int noOfElements = 5;
        int count = 0;

        while (count < noOfElements) {
            insertAtEnd(new MemoryEfficientDLLNode(count));
            count++;
        }
        printLinkedList("Initial list");

        // insert at beginning
        //insertAtBeginning(new MemoryEfficientDLLNode(10));
        //printLinkedList("Inserting 10 at beginning");

        //insertAtBeginning(null);
        //printLinkedList("Inserting null at beginning");

        // insert at end
        //insertAtEnd(new MemoryEfficientDLLNode(11));
        //printLinkedList("inserting 11 at end");

        //insertAtEnd(null);
        //printLinkedList("inserting null at end");

        // insert at position
        //insertAtPosition(new DLLNode(33), 3);
        //printLinkedList("inserting 33 at 3");

        //insertAtPosition(new DLLNode(44), 0);
        //printLinkedList("inserting 44 at 0");

        //insertAtPosition(new DLLNode(55), length);
        //printLinkedList("inserting 55 at " + length);

        //insertAtPosition(null, 3);
        //printLinkedList("inserting null at 3");

        // delete at beginning
        //deleteAtBeginning();
        //printLinkedList("deleting from beginning");

        // delete at end
        //deleteAtEnd();
        //printLinkedList("deleting from end");

        // delete at position
        //deleteAtPosition(0);
        //printLinkedList("deleting at position 0");
//
        //deleteAtPosition(3);
        //printLinkedList("deleting at position 3");
//
        //deleteAtPosition(length);
        //printLinkedList("deleting at position " + length);

        // delete particular node
        //deleteMatchedNode(new DLLNode(3));
        //printLinkedList("deleting matched node 3" );

    }
    private void insertAtBeginning(MemoryEfficientDLLNode node) {


    }

    private void insertAtEnd(MemoryEfficientDLLNode node) {
        int previous = 0;
        if (node == null) {
            return;
        }

        if (head == null) {
            head = node;
            pointerMap.put(node.hashCode(), node);
            length++;
            return;
        }

        MemoryEfficientDLLNode temp = head;
        while(temp.getPtrDiff()!= 0) {
            int next = previous ^ temp.getPtrDiff();
            temp = pointerMap.get(next);
            previous = temp.hashCode();
        }

        temp.setPtrDiff(previous ^ node.hashCode()); // saving pointer diff as next node.
        pointerMap.put(node.hashCode(), node);
    }

    private void printLinkedList(String message) {

        System.out.println(ConsoleColors.RED + "Linked List: " + ConsoleColors.BLUE + message + ConsoleColors.BLACK + toString());
        System.out.println(ConsoleColors.BLUE + "HEAD ="  + head.getData());
    }

    @Override
    public String toString() {
        String result = "[";
        int previous = 0;

        if (head == null) {
            return result+"]";
        }

        MemoryEfficientDLLNode temp = head;
        result = result + temp.getData();
        while(temp.getPtrDiff()!= 0) {
            int next = previous ^ temp.getPtrDiff();
            temp = pointerMap.get(next);
            previous = temp.hashCode();
            result = result + "," + temp.getData();
        }
        return result + "]";
    }
}
