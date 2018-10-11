package com.deltacap019.RecursionAndBacktracking;

import com.deltacap019.utility.ConsoleColors;

/**
 * Generate all the strings of length n drawn from 0... k – 1.
 */
public class BackTrackingProblem4 {

    // region ## States
    private int arraytoHoldBits[];
    private static int depthCount = -1;
    //endregion

    public void generateStringsOfNBits(int numberOfBits, int typesOfBits) {
        arraytoHoldBits = new int[numberOfBits];
        generate(numberOfBits, typesOfBits);
    }

    // region ## Logic using "BackTracking"

    /**
     * 0101 [Bits order] ==> 0123 [Bit index in array]
     *
     * @param bitIndex current index for which we have to process the bit combination.
     * @param typesOfBits  type of bits that we want to use for manipulations array. Earlier in Problem 3 it was binray so results weere just in "0 / 1".
     *           This time it wil be k-ary, that means
     *           if k = 2 then bits to be used are "0 / 1 / 2".
     *           if k = 3 then bits to be used are "0 / 1 / 2 / 3".
     */
    private void generate(int bitIndex, int typesOfBits) {
        depthCount++;
        System.out.println(ConsoleColors.BLUE_BOLD + " For ## depthCount ## = " + depthCount);
        if (bitIndex == 0) { // means staring from rightmost index 3, 2, 1, 0 , -1 as in sample above.
            // Which means that we have processed everything can't process further hence time to print the combination so far.\
            printArray(ConsoleColors.RED + "## Final array ##", arraytoHoldBits);
        } else {
            for (int j = 0; j < typesOfBits; j++){
                System.out.println(ConsoleColors.GREEN + " For ## bitIndex ## = " + (bitIndex - 1));
                System.out.println(ConsoleColors.GREEN + " For ## j ## = " +j );
                arraytoHoldBits[bitIndex - 1] = j;
                printArray(ConsoleColors.RESET + "array AFTER manipulation" ,arraytoHoldBits);
                generate(bitIndex - 1, typesOfBits);
            }
        }
    }

    private void printArray(String message , int[] array){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
        }
        System.out.println(message + " = " + builder);
    }
}
