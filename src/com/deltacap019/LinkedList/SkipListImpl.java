package com.deltacap019.LinkedList;

import com.deltacap019.utility.ConsoleColors;

import java.util.Random;

/**
 * Basic SKIP LINKED LIST implementation with
 *  -> PREV pointer in every node.
 *  -> Padding Nodes at start and end (Here we are using Integer.MIN_VALUE and Integer.MAX_VALUE as left and right padding.
 * <p>
 * The idea of implementing your own instead of using the predefined Linked List in Java
 * is to get the basic idea how it works and to learn optimizations on it.
 *
 * Complexity for 2 sorted list = 2(sqrt(n))
 * Complexity for 3 sorted list = 3(cubeRoot(n))
 * Complexity for log-n sorted list = log(log-n root(n))
 *
 * so complexity can be (log-n) for this list. it will become like tree with log-n levels.
 */

public class SkipListImpl {

    /**
     * Holds data and pointers
     */

    private static class SkipListImplNode {

        private int data;
        private SkipListImplNode prev;
        private SkipListImplNode next;
        private SkipListImplNode up;
        private SkipListImplNode down;
        private int level;

        SkipListImplNode(int data) {
            this.data = data;
            prev = null;
            next = null;
            down = null;
            level = 0;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public SkipListImplNode getPrev() {
            return prev;
        }

        public void setPrev(SkipListImplNode prev) {
            this.prev = prev;
        }

        public SkipListImplNode getNext() {
            return next;
        }

        public void setNext(SkipListImplNode next) {
            this.next = next;
        }

        public SkipListImplNode getUp() {
            return up;
        }

        public void setUp(SkipListImplNode up) {
            this.up = up;
        }

        public SkipListImplNode getDown() {
            return down;
        }

        public void setDown(SkipListImplNode down) {
            this.down = down;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public boolean equals(Object obj) {

            if(!(obj instanceof SkipListImplNode)) {
                return false;
            }

            SkipListImplNode node = (SkipListImplNode) obj;

            return this.data == node.data;
        }
    }

    private int length = 0;
    private SkipListImplNode head = null, tail = null;
    private double probability = 0.5;   // it is required in deciding if for the new node we need to level up or not.
                                        // Here it is random and we are giving a fair case by taking probability of 0.5.

    private int height = -1; // Will tell the total height of the list so far. In case some node qualifies for level up
                            // than we have to creating padding nodes and add to that level first.

    public SkipListImpl() {
        addEmptyLayer();
    }

    public void test() {

        // create list here
        int noOfElements = 15;
        int count = 0;

        while (count < noOfElements) {
            insert(new SkipListImplNode(count));
            count++;
        }

       // insert
        insert(new SkipListImplNode(110));

        insert(null);

        insert(new SkipListImplNode(111));

        insert(new SkipListImplNode(33));

        insert(new SkipListImplNode(4));

        insert(new SkipListImplNode(55));

        insert(null);

        printLinkedList("AFTER INSERTION");

       // delete

        remove(new SkipListImplNode(33));

        remove(new SkipListImplNode(99));

        remove(0);

        remove(length);

        remove(3);

        printLinkedList("AFTER DELETION");
    }

    private void insert(SkipListImplNode node) {

        if(node == null) {
            return;
        }

        SkipListImplNode temp = lookUpForNode(node);

        // updating references for next and previous level in 0th level, like we do it in normal linked list by updating
        // next and previous references.
        node.setNext(temp.getNext());
        temp.getNext().setPrev(node);
        temp.setNext(node);
        node.setPrev(temp);

        // now see if we need to upgrade this node for new level using probability factor (Head / Tail) in general sense.

        int count = 0; // variable maintaining how many level ups for this node have been called so far.
        while(isLevelUpRequired()) {
            if(count >= height) {
                addEmptyLayer();
            }

            SkipListImplNode levelUpNode = new SkipListImplNode(node.getData());

            // iterate for the left most node that has up pointer from the new inserted node so as that we can
            // updates its right pointer to new levelUpNode.

            temp = node;
            while (temp.getUp() == null) {
                temp = temp.getPrev();
            }

            // updating left right pointers for level++
            temp = temp.getUp();
            levelUpNode.setNext(temp.getNext());
            temp.setNext(levelUpNode);
            levelUpNode.setPrev(temp);
            levelUpNode.setDown(node);
            node.setUp(levelUpNode);

            node = levelUpNode; // updating newly inserted node status. as now the level up node is

            count++;
        }
        length++;
    }

    private void remove(int positionOfNode) {
        // For error corrections as we don't want to remove the nodes used as padding nodes at the start and end of list.
        if(positionOfNode == 0 ) {
            positionOfNode++;
        }
//        if(positionOfNode== length) {
//            positionOfNode--;
//        }
        SkipListImplNode foundNode = lookUpForPosition(positionOfNode);

        // we are at level 0 move to top as long as up pointer is present and link left and right nodes to each other

        while (foundNode != null){
            // adjust left and right links
            foundNode.getPrev().setNext(foundNode.getNext());
            foundNode.getNext().setPrev(foundNode.getPrev());
            // update node with upper level node
            foundNode = foundNode.getUp();
            length--;
        }
    }

    private void remove(SkipListImplNode node) {

        SkipListImplNode foundNode = lookUpForNode(node);
        if(foundNode.getData() != node.getData()) {
            return;
        }

        // we are at level 0 move to top as long as up pointer is present and link left and right nodes to each other

        while (foundNode != null){
            // adjust left and right links
            foundNode.getPrev().setNext(foundNode.getNext());
            foundNode.getNext().setPrev(foundNode.getPrev());
            // update node with upper level node
            foundNode = foundNode.getUp();
            length--;
        }

    }

    /**
     * returns a eligible place for the node to be inserted or exact node if match is found
     * for 33, 44, 66 if node supplied as parameter is 44 it will return 44.
     * if node supplied as parameter is 55 in this case also it will return 44, as nest to 44 is best place to insert 55
     *
     * @param node
     * @return
     */
    private SkipListImplNode lookUpForNode(SkipListImplNode node) {

        SkipListImplNode temp = head;
        for (int i = height; i >= 0; i--) {
            while (temp.getNext().getData() <= node.getData() ) {
                temp = temp.getNext();
            }
            if (temp.getDown() != null){
                temp = temp.getDown();
            }
            else {
                break;
            }
        }

        return temp;
    }

    /**
     * returns a eligible place for the node to be inserted or exact node if match is found
     * for 33, 44, 66 if node supplied as parameter is 44 it will return 44.
     * if node supplied as parameter is 55 in this case also it will return 44, as nest to 44 is best place to insert 55
     *
     * FIXME: NEED TO BE FIXED: position not coming correct, its always coming +1;
     * @param nodePosition
     * @return
     */
    private SkipListImplNode lookUpForPosition(int nodePosition) {

        SkipListImplNode firstNode = head;
        SkipListImplNode requiredNode = null;
        for (int i = height; i > -1; i--) {
            int count = 0;
            SkipListImplNode temp = firstNode;
            while (temp!= null && count < nodePosition) {
                temp = temp.getNext();
                count++;
            }
            firstNode = firstNode.getDown();
            requiredNode = temp;
        }

        return requiredNode;
    }

    private boolean isLevelUpRequired() {
        Random rand = new Random();
        return rand.nextDouble() > probability;
    }

    private void addEmptyLayer() {
        SkipListImplNode leftMostNode, rightMostNode;
        leftMostNode = new SkipListImplNode(Integer.MIN_VALUE);
        rightMostNode = new SkipListImplNode((Integer.MAX_VALUE));
        leftMostNode.setNext(rightMostNode);
        rightMostNode.setPrev(leftMostNode);
        if (head != null) {
            leftMostNode.setDown(head);
            rightMostNode.setDown(tail);
            head.setUp(leftMostNode);
            tail.setUp(rightMostNode);
        }
        head = leftMostNode;
        tail = rightMostNode;
        height++;
    }

    private void printLinkedList(String message) {

        if (head == null) {
            return;
        }

        System.out.println("\n" + ConsoleColors.RED + message);

        SkipListImplNode firstNode = head;
        for (int i = height; i >= 0; i--) {
            StringBuffer result = new StringBuffer();
            SkipListImplNode temp = firstNode;
            System.out.println("\n" + ConsoleColors.BLUE_BOLD_BRIGHT + "Level: " + ConsoleColors.BLUE + i);
            result.append("[");
            result.append(firstNode.getData());
            while (temp != null) {
                if(temp.getData() != Integer.MIN_VALUE && temp.getData() != Integer.MAX_VALUE) {
                    result = result.append(",");
                    result = result.append(temp.getData());
                }
                temp = temp.getNext();
            }
            firstNode  = firstNode.getDown();
            result = result.append(",");
            result = result.append(Integer.MAX_VALUE);
            result = result.append("]");
            System.out.println("\n" + ConsoleColors.BLUE_BOLD_BRIGHT  + ConsoleColors.BLACK + result.toString());
        }

    }
}
