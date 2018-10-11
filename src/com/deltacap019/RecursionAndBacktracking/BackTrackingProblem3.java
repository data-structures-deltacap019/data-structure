package com.deltacap019.RecursionAndBacktracking;

import com.deltacap019.utility.ConsoleColors;

/**
 * Generate all the strings of n bits. Assume A[0..n – 1] is an array of size n.
 */
public class BackTrackingProblem3 {

    // as binary representation of a number are consecutive characters specially 0/1.
    // so we can store them in an "array".

    // region ## States
    private int arraytoHoldBits[];
    private static int depthCount = -1;
    //endregion

    public void generateStringsOfNBits(int numberOfBits) {
        arraytoHoldBits = new int[numberOfBits];
        generate(numberOfBits);
    }

    // region ## Logic using "BackTracking"

    /**
     * 0101 [Bits order] ==> 0123 [Bit index in array]
     *
     * @param bitIndex current index for which we have to process the bit combination.
     */
    private void generate(int bitIndex) {
        depthCount++;

        if (bitIndex == 0) { // means staring from rightmost index 3, 2, 1, 0 , -1 as in sample above.
            // Which means that we have processed everything can't process further hence time to print the combination so far.\
            printArray(ConsoleColors.RED + "## Final array ##", arraytoHoldBits);
        } else {
            // for 0 bits at all places
            arraytoHoldBits[bitIndex - 1] = 0; // (n-1) because lets say "3" are the number of bits thn array index will {0,1,2}
            System.out.println(ConsoleColors.GREEN + " bit = 0" + " for ## bitIndex ## = " + (bitIndex - 1));
            printArray(ConsoleColors.RESET + "array AFTER manipulation with 0" ,arraytoHoldBits);
            // so as to start with rightmost digit we have to give it index 2 whihc i one less than the number of bits.
            generate(bitIndex - 1);

            // 1 bits at all places
            arraytoHoldBits[bitIndex - 1] = 1;
            System.out.println(ConsoleColors.GREEN + " for bit = 1" + " for ## bitIndex ## = " + (bitIndex - 1));
            printArray(ConsoleColors.RESET + "array AFTER manipulation with 1", arraytoHoldBits);
            generate(bitIndex - 1);
        }
    }

    private void printArray(String message , int[] array){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
        }
        System.out.println(message + " = " + builder);
    }

    //region ## Solution print for numberOfBits = 3
//            ## DepthCount = 0 for bit = 0 for bitIndex = 2
//            ## DepthCount = 1 for bit = 0 for bitIndex = 1
//            ## DepthCount = 2 for bit = 0 for bitIndex = 0
//            Array content =000
//            ## DepthCount = 3 for bit = 1 for bitIndex = 0
//            Array content =100
//            ## DepthCount = 4 for bit = 1 for bitIndex = 1
//            ## DepthCount = 5 for bit = 0 for bitIndex = 0
//            Array content =010
//            ## DepthCount = 6 for bit = 1 for bitIndex = 0
//            Array content =110
//            ## DepthCount = 7 for bit = 1 for bitIndex = 2
//            ## DepthCount = 8 for bit = 0 for bitIndex = 1
//            ## DepthCount = 9 for bit = 0 for bitIndex = 0
//            Array content =001
//            ## DepthCount = 10 for bit = 1 for bitIndex = 0
//            Array content =101
//            ## DepthCount = 11 for bit = 1 for bitIndex = 1
//            ## DepthCount = 12 for bit = 0 for bitIndex = 0
//            Array content =011
//            ## DepthCount = 13 for bit = 1 for bitIndex = 0
//            Array content =111
    // endregion


}
