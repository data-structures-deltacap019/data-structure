package com.deltacap019.RecursionAndBacktracking;

import com.deltacap019.utility.ConsoleColors;

/**
 * src = "https://www.geeksforgeeks.org/generate-binary-strings-without-consecutive-1s/"
 * Generate all binary strings without consecutive 1’s
 *
 * Time Complexity: - exponential i.e T(2^n).
 */
public class BinaryStringWithoutConsecutive1 {

    // region ## States
    private int arraytoHoldBits[];
    private static int depthCount = -1;
    //endregion

    public void generateStringsWithoutConsecutive1(int numberOfBits) {
        arraytoHoldBits = new int[numberOfBits];
        generate(0);
    }

    // region ## Logic using "Recursion"

    /**
     * 0101 [Bits order] ==> 0123 [Bit index in array]
     *
     * @param bitIndex current index for which we have to process the bit combination.
     */
    private void generate(int bitIndex) {
        depthCount++;
        System.out.println(ConsoleColors.BLUE_BOLD + " For ## depthCount ## = " + depthCount);
        if (bitIndex == arraytoHoldBits.length) { // means staring from rightmost index 3, 2, 1, 0 , -1 as in sample above.
            // Which means that we have processed everything can't process further hence time to print the combination so far.\
            printArray(ConsoleColors.RED + "## Final array ##", arraytoHoldBits);
            return;
        }
        int prevIndex = bitIndex - 1;
        if (prevIndex >= 0) {

            if (arraytoHoldBits[prevIndex] == 0) { // if previous character is '0' than we put both '1' and '0' at end of string
                                                    // example str = "00" then new  string "001" and "000"
                arraytoHoldBits[bitIndex] = 0;
                printArray(ConsoleColors.RESET + "array AFTER manipulation", arraytoHoldBits);
                generate(bitIndex + 1);

                arraytoHoldBits[bitIndex] = 1;
                printArray(ConsoleColors.RESET + "array AFTER manipulation", arraytoHoldBits);
                generate(bitIndex + 1);
            }

            if (arraytoHoldBits[prevIndex] == 1) {
                arraytoHoldBits[bitIndex] = 0;
                printArray(ConsoleColors.RESET + "array AFTER manipulation", arraytoHoldBits);
                generate(bitIndex + 1);
            }
        } else {
            for (int j = 0; j < 2; j++) {
                arraytoHoldBits[bitIndex] = j; // calling recursion with elements starting with zero i.e when j = 0
                generate(bitIndex + 1);// calling recursion with elements starting with zero i.e when j = 0
            }
        }
    }

    private void printArray(String message, int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
        }
        System.out.println(message + " = " + builder);
    }
}
