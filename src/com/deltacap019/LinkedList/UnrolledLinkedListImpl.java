package com.deltacap019.LinkedList;

import com.deltacap019.utility.ConsoleColors;

/**
 * Basic UNROLLED LINKED LIST implementation.
 *   There are two ways to perform it each sub node as
 *      - ARRAY.
 *      - LINKED LIST.
 *   With array it will be easy to get any nth element but insertion and deletion wont be efficient, as with time as number of nodes will
 *   grow so will be the array size for each node. And we need to create new arrays all the time and copy data form one array to other array,
 *   which will cost us extra O(n) time. But with linked list it will be easy and can be done without extra time for copying the elements.
 *
 *
 *  ## Array as sub-node. ##
 *      Here we are not taking any default initialCapacity for array size. We are growing it as the number of elements grows.
 *      This is not efficient as we have to create arrays every time the number of elements grows, and then we have to iterate all the elements
 *      to reshuffle them to fill arrays of all nodes.
 *
 *  ## Efficient solution.
 *      * Pre-Initialise the array to some constant say it should be greater than 8 or equal to 16 the way ArrayList initialises to some default value
 *        when we create a new array.
 *      * Keep the utilization of an array to more than 50% and keep it near 70 - 75 %. (i.e as soon as tha number of elements grow more than initialCapacity
 *                                                                                       move half of the elements to newly created node. In this way
 *                                                                                       our previous node will be have half occupancy but that's what
 *                                                                                       we had discussed earlier about occupancy of array.
 *                                                                                       Maintaining occupancy this way provides flexibility of moving
 *                                                                                       elements from one node to other without haveing the need to reshuffle
 *                                                                                       elements for all nodes which will lead to extra O(n) cost as we
 *                                                                                       have to reshuffle all n elements. So we trade space for time here).
 *
 *   ## SRC :- https://brilliant.org/wiki/unrolled-linked-list/
 *             https://www.geeksforgeeks.org/insertion-unrolled-linked-list/
 *
 */

public class UnrolledLinkedListImpl {

    /**
     * Holds data and pointers
     */

    private static class ULLNode {

        private ULLNode nextNode;
        private int noOfElements = 0;// helps in keeping track of how many elements are there in node and
                                    // keeps let us know the index of array to which we want to add item,
                                    // without it we have to loop thru the array to find an eligible index
                                    // for insertion. after comparing it with sqrt(number of elements) we will be able to find out
                                    // shuffling of elements is required or not.
        private int[] elements;


        // creates a node with data provided
        ULLNode(int size) {
            nextNode = null;
            elements = new int[size];
        }

        // returns the node next to the current node on which we will call this method.
        public ULLNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(ULLNode nextNode) {
            this.nextNode = nextNode;
        }

        public int[] getElements() {
            return elements;
        }

        public void setElements(int[] elements) {
            this.elements = elements;
        }
    }


    public UnrolledLinkedListImpl() {

    }

    /**
     *  //#### ARRAY IMPLEMENTATION (IN-EFFICIENT WAY - "100% Occupancy of elements nodes")####
     */
    public static class InEfficientImplementation {

        private int length = 1; // initializing to 1 as during insertion of first element it will help in creating array of length 1.
        private ULLNode head, tail = null;

        public void test() {

            // create list here
            int noOfElements = 15;
            int count = 0;

            while (count < noOfElements) {
                insert(count);
                count++;
            }
            printLinkedList("Initial list");

        }

        private void insert(int data) {

            if (head == null) {
                head = new ULLNode(length);
                head.getElements()[length - 1] = data;
                head.noOfElements++;
                tail = head;
                length++;
                return;
            }

            if(getCapacity() > tail.getElements().length) {
                reArrangeList();
            }
            // insert new element
            if(tail.getElements().length == tail.noOfElements) {
                ULLNode node = new ULLNode(getCapacity());
                node.getElements()[0] = data;
                node.noOfElements++;
                tail.setNextNode(node);
                tail = node;
                length++;
            }else {
                tail.getElements()[tail.noOfElements] = data;
                tail.noOfElements++;
                length++;
            }
        }

        private void delete(ULLNode node) {
            // STEPS
            // - look for node
            // - bring each element to one step back in position
            // - update tail.
        }

        private void delete(int position) {

        }

        private int getCapacity() {
            return (int)Math.ceil(Math.sqrt(length));
        }

        private void reArrangeList() {
            ULLNode temp = head;
            ULLNode prev = null;
            int elementsProcessedSoFarNode = 0;
            while (temp != null) {
                ULLNode node = new ULLNode(getCapacity());

                if (prev != null) {
                    prev.setNextNode(node);
                }else {
                    head = node;
                }

                int count = 0;
                while(true) {
                    if (elementsProcessedSoFarNode >= temp.noOfElements) {
                        temp = temp.getNextNode();
                        elementsProcessedSoFarNode = 0;
                    }
                    if (temp == null || count >= getCapacity() ) {
                        break;
                    }

                    node.elements[count] = temp.getElements()[elementsProcessedSoFarNode];
                    node.noOfElements++;
                    elementsProcessedSoFarNode++;
                    count++;
                }
                tail = node;
                prev = node;
            }
        }

        private void printLinkedList(String message) {

            System.out.println(ConsoleColors.RED + "Linked List: " + ConsoleColors.BLUE + message + ConsoleColors.BLACK + toString());
            System.out.println(ConsoleColors.BLUE + "HEAD =");
        }

        @Override
        public String toString() {
            String result = "[";

            if (head == null) {
                return result+"]";
            }

            ULLNode temp = head;
            result = result + "";
            while(temp != null) {
                for(int i = 0; i< temp.noOfElements; i++) {
                    result = result + temp.getElements()[i] +  "," ;
                }
                temp = temp.getNextNode();
                result = result + "\n";
            }
            return result + "]";
        }
    }

    /**
     *  //#### ARRAY IMPLEMENTATION (Keep the utilization of an array to more than 50% and keep it near 70 - 75 %)
     */
    public static class EfficientImplementation {
        private int length = 0; // initializing to 1 as during insertion of first element it will help in creating array of length 1.
        private ULLNode head, tail = null;
        private int initialCapacity = 5; // pre-initialising initialCapacity to some offset, to improve performance the way it happens in case of ArrayList in Java.


        public void test() {

            // create list here
            int noOfElements = 100;
            int count = 0;

            while (count < noOfElements) {
                insert(count);
                count++;
            }
            printLinkedList("Initial list");
        }

        private void insert(int data) {
            length++;
            if (head == null) {
                head = new ULLNode(getCapacity());
                head.getElements()[0] = data;
                head.noOfElements++;
                tail = head;
                return;
            }

            if (getCapacity() > initialCapacity) {
                // rearrange elements according to new capacity.
                initialCapacity = getCapacity();
                reArrangeList();
            }
            if (initialCapacity == 7) {
                System.out.print("");
            }

            if (tail.noOfElements + 1 < getCapacity()) { // "+ 1" because we are considering the element to be added. and comparing it to initialCapacity.
                // i.e if it still fits in initialCapacity or not.
                tail.getElements()[tail.noOfElements] = data;
                tail.noOfElements++;
                return;
            }

            if (initialCapacity == 7) {
                System.out.print("");
            }
            ULLNode newNode = new ULLNode(getCapacity());
            int j = 0;
            for (int i = tail.getElements().length / 2 + 1; i < tail.noOfElements; i++) {
                newNode.getElements()[j++] = tail.getElements()[i];
                newNode.noOfElements++;
            }

            tail.setNextNode(newNode);
            tail.noOfElements = tail.getElements().length / 2 + 1;
            tail = newNode;
            newNode.getElements()[newNode.noOfElements] = data;
            newNode.noOfElements++;
        }

        private void delete(){
            //  if we remove an element and the number of values in that node drop below 50%, we move elements from the neighboring array over to fill it back up above 50%. If that array also drops below 50%, we merge the two nodes.
        }

        private int getCapacity() {
            return initialCapacity > (int)Math.ceil(Math.sqrt(length))? initialCapacity : (int)Math.ceil(Math.sqrt(length));
        }

        /**
         * Modified according to 50% occupancy
         */
        private void reArrangeList() {

            printLinkedList("REARRANGING for Capacity: " + getCapacity() );
            ULLNode temp = head;
            ULLNode prev = null;
            int elementsProcessedSoFarNode = 0;
            while (temp != null) {
                ULLNode node = new ULLNode(getCapacity());

                if (prev != null) {
                    prev.setNextNode(node);
                }else {
                    head = node;
                }

                int count = 0;
                while(true) {
                    if (initialCapacity == 7) {
                        System.out.print("");
                    }
                    if (elementsProcessedSoFarNode >= temp.noOfElements) {
                        temp = temp.getNextNode();
                        elementsProcessedSoFarNode = 0;
                    }
                    if (temp == null || count > getCapacity()/2) {
                        break;
                    }

                    node.elements[count] = temp.getElements()[elementsProcessedSoFarNode];
                    node.noOfElements++;
                    elementsProcessedSoFarNode++;
                    count++;
                }
                tail = node;
                prev = node;
            }
        }

        private void printLinkedList(String message) {

            System.out.println(ConsoleColors.RED + "Linked List: " + ConsoleColors.BLUE + message + ConsoleColors.BLACK + toString());
            System.out.println(ConsoleColors.BLUE + "HEAD =");
        }

        @Override
        public String toString() {
            String result = "[";

            if (head == null) {
                return result+"]";
            }

            ULLNode temp = head;
            result = result + "";
            while(temp != null) {
                for(int i = 0; i< temp.noOfElements; i++) {
                    result = result + temp.getElements()[i] +  "," ;
                }
                temp = temp.getNextNode();
                result = result + "\n";
            }
            return result + "]";
        }
    }
}
