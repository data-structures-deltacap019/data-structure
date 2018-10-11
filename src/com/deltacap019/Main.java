package com.deltacap019;

import com.deltacap019.Queues.LinkedListQueueImpl;
import com.deltacap019.Trees.InOrderTraversal;
import com.deltacap019.Trees.LevelOrderTraversalBFS;
import com.deltacap019.Trees.PostOrderTraversal;
import com.deltacap019.Trees.PreOrderTraversal;

public class Main {

    public static void main(String[] args) {
        // region ## BACKTRACKING

            //new BackTrackingProblem3().generateStringsOfNBits(3);
            //new BackTrackingProblem4().generateStringsOfNBits(3, 2);
            //new BinaryStringWithoutConsecutive1().generateStringsWithoutConsecutive1(3);
            //new AllBinaryStringsFromPattern().generateStringsFromPattern("1??0?101");

        //endregion

        //region ## LINKED LISTS

            //region Singly linked list
                //new SinglyLinkedListImpl().test();
            //endregion

            //region Doubly linked list
                //new DoublyLinkedListImpl().test();
            //endregion

            //region Circular linked list
                //new CircularLinkedListImpl().test();
            //endregion

            //region MemoryEfficient linked list
                //new MemoryEfficientDLLImpl().test();
            //endregion

            //region MemoryEfficient linked list
              //new MemoryEfficientDLLImpl().test();
            //endregion

            //region Skip List
                //new SkipListImpl().test();
            //endregion

            //region Unrolled Linked List
                //new UnrolledLinkedListImpl.InEfficientImplementation().test();
                //new UnrolledLinkedListImpl.EfficientImplementation().test();
            //endregion

        //endregion

        //region ## STACKS

            //region Array Implementation
                //new FixedCapacityArrayStackImpl().test();
            //endregion

            //region Dynamic Array Incremental Implementation
                //new DynamicIncrementalArrayStackImpl().test();
            //endregion

            //region Dynamic Array Doubling Implementation
                //new DynamicDoublingArrayStackImpl().test();
            //endregion

            //region Linked List Implementation
                //new LinkedListStackImpl().test();
            //endregion

        //endregion

        //region ## QUEUE

            //region Circular Array Implementation
                //new FixedCapacityArrayQueueImpl().test();
            //endregion

            //region Dynamic Circular Array Implementation
                //new DynamicArrayQueueImpl().test();
            //endregion

            //region Linked List Implementation
                //new LinkedListQueueImpl().test();
            //endregion

        //endregion


        //region ## TREES

            //region Traversals
                //new PreOrderTraversal().traverse();
                //new InOrderTraversal().traverse();
                //new PostOrderTraversal().traverse();
                //new LevelOrderTraversalBFS().traverse();

            //endregion

            //region Dynamic Circular Array Implementation
            //new DynamicArrayQueueImpl().test();
            //endregion

            //region Linked List Implementation
            //endregion

        //endregion


        //testBitwisePacking();
    }

    private static void testBitwisePacking(){
        // age is 8-bit
        // gender is 1-bit
        // weight is 8-bit
        // height is 8-bit
        int age=60, gender=1, weight=82, height=182;
        int packed1 = 0, packed2 = 0;

        System.out.println("\nage in binary form is " + Integer.toBinaryString(age));
        System.out.println("gender in binary form is " + Integer.toBinaryString(gender));
        System.out.println("weight in binary form is " + Integer.toBinaryString(weight));
        System.out.println("height in binary form is " + Integer.toBinaryString(height));

        // packing - 2 approaches
        packed2 |= height;
        System.out.println("\npacked2 in binary form is after HEIGHT " + Integer.toBinaryString(packed2));
        packed2 |= (weight << 8);
        System.out.println("packed2 in binary form is after WEIGHT " + Integer.toBinaryString(packed2));
        packed2 |= (gender << 16);
        System.out.println("packed2 in binary form is after GENDER " + Integer.toBinaryString(packed2));
        packed2 |= (age << 17);
        System.out.println("packed2 in binary form is after AGE    " + Integer.toBinaryString(packed2));

        // "111100 10 1010010 10110110"
        packed1 = (((((age << 1) | gender) << 8) | weight) << 8) | height;

        System.out.println("\npacked1 in binary form is " + Integer.toBinaryString(packed1));
        System.out.println("packed2 in binary form is " + Integer.toBinaryString(packed2));
        assert packed1 == packed2;

        // unpacking
        assert height == (packed1 & 0xff);
        assert weight == (packed1 >>> 8 & 0xff);
        assert gender == ((packed1 >>> 16) & 1);
        assert age == (packed1 >>> 17);

        height = (packed1 & 0xff);  // 0xff in Decimal = 255, Binary = c
        // 11110010101001010110110 & 11111111 = 10110110 = 182

        //"111100 1 01010010 10110110"
        System.out.println("\nAND HEIGHT " + height);
        weight = (packed1 >>> 8 & 0xff);
        System.out.println("SHIFT WEIGHT " + Integer.toBinaryString(packed1 >>> 8)); // 111100101010010
                                                                                        //        11111111
                                                                                        //         1010010 (Output)
        gender = ((packed1 >>> 16) & 1);
        System.out.println("SHIFT GENDER " + Integer.toBinaryString(packed1 >>> 16));
        age = (packed1 >>> 17);
        System.out.println("SHIFT AGE " + Integer.toBinaryString(packed1 >>> 17));

        System.out.println("\nunpacked HEIGHT " + height);
        System.out.println("unpacked WEIGHT " + weight);
        System.out.println("unpacked GENDER " + gender);
        System.out.println("unpacked   AGE " + age);
    }
}
