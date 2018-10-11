package com.deltacap019.Stacks;

import com.deltacap019.utility.ConsoleColors;

/**
 * Implements stack implementation using array of fixed size.
 * In this there is no provision of growing array ones it becomes full.
 * We will create an array of capacity 10 and thereafter we will implement PUSH and POP
 * operations on it.
 */

public class FixedCapacityArrayStackImpl {

    private int[] stack;
    private int capacity = 10;
    private int top = -1; // stores current index of tha array.

    public FixedCapacityArrayStackImpl() {
        stack = new int[capacity];
    }

    public FixedCapacityArrayStackImpl(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }

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
        if (top + 1 == capacity) {
            System.out.println(ConsoleColors.RED + "Stack FULL");
            return;
        }

        stack[++top] =  data; // first increment top than use it to set value. As we have initialized the top to -1.
    }

    private void pop() {
        if(top == -1) {
            System.out.println(ConsoleColors.RED + "Stack EMPTY");
            return;
        }

        stack[top--] = Integer.MIN_VALUE; // first change value of top and then decrement it.
    }

    private void printStack( String message) {
        System.out.println(ConsoleColors.BLUE_BOLD + "Stack" + message);
        System.out.println(ConsoleColors.PURPLE_BRIGHT + toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (top == -1) {
            return result.append("]").toString();
        }
        for (int i = 0; i <= top; i++) {
            result.append("\n");
            result.append(stack[i]);
            result.append(",");
        }
        result.append("\n");
        result.append("]");
        return result.toString();
    }
}
