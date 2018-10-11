package com.deltacap019.RecursionAndBacktracking;

import com.deltacap019.utility.ConsoleColors;
import com.sun.istack.internal.NotNull;

/**
 * src = "https://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/"
 * <p>
 * Generate all binary strings from given pattern. Given a string containing of ‘0’, ‘1’ and ‘?’ wildcard characters,
 * generateUsingRecursion all binary strings that can be formed by replacing each wildcard character by ‘0’ or ‘1’
 * <p>
 * Input str = "1??0?101"
 * Output:
 * 10000101 -
 * 10001101 -
 * 10100101
 * 10101101 -
 * 11000101
 * 11001101
 * 11100101
 * 11101101 -
 *
 * Time Complexity T(2^n)  as 2 is the number of bits we are placing and n is the length of the input string pattern
 */
public class AllBinaryStringsFromPattern {

    // region ## States
    private static int depthCount = -1;
    private char arraytoHoldBits[];
    //endregion

    public void generateStringsFromPattern(@NotNull String pattern) {
        //arraytoHoldBits = pattern.toCharArray();

        generateUsingRecursion(pattern, 0);

        arraytoHoldBits = pattern.toCharArray();
        generateUsingBackTracking( 0);
    }

    // region ## Logic using "Recursion"

    /**
     * 0101 [Bits order] ==> 0123 [Bit index in array]
     *
     * We are passing input string in function so as that function always has its input instance and
     * do manipulations on it. Int his way we will not loose some of the combinations
     *@param  input input string with patterns
     * @param bitIndex current index for which we have to process the bit combination.
     */
    private void generateUsingRecursion(String input, int bitIndex) {
        depthCount++;
        System.out.println(ConsoleColors.BLUE_BOLD + " For ## depthCount ## = " + depthCount);
        if (bitIndex == input.length()) { // means staring from rightmost index 3, 2, 1, 0 , -1 as in sample above.
            // Which means that we have processed everything can't process further hence time to print the combination so far.\
            printArray(ConsoleColors.RED + "## Final array ##", input);
            return;
        }
        if (input.charAt(bitIndex) == '?') {
            StringBuilder builder = new StringBuilder((input));

            builder.setCharAt(bitIndex, '0');
            printArray(ConsoleColors.RESET + "array AFTER manipulation for 0", builder.toString());
            generateUsingRecursion(builder.toString(), bitIndex + 1);

            builder.setCharAt(bitIndex, '1');
            printArray(ConsoleColors.RESET + "array AFTER manipulation for 1", builder.toString());
            generateUsingRecursion(builder.toString(), bitIndex + 1);

        } else{
            generateUsingRecursion(input,bitIndex + 1);
        }
    }

    private void printArray(String message, String input) {
        System.out.println(message + " "+ input);
    }

    /**
     * 0101 [Bits order] ==> 0123 [Bit index in array]
     *
     * We are passing input string in function so as that function always has its input instance and
     * do manipulations on it. Int his way we will not loose some of the combinations
     * @param bitIndex current index for which we have to process the bit combination.
     */

    private void generateUsingBackTracking( int bitIndex) {
        depthCount++;
        System.out.println(ConsoleColors.BLUE_BOLD + " For ## depthCount ## = " + depthCount);
        if (bitIndex == arraytoHoldBits.length) { // means staring from rightmost index 3, 2, 1, 0 , -1 as in sample above.
            // Which means that we have processed everything can't process further hence time to print the combination so far.\
            printArray(ConsoleColors.RED + "## Final array ##");
            return;
        }
        if (arraytoHoldBits[bitIndex] == '?') {

            arraytoHoldBits[bitIndex] = '0';
            printArray(ConsoleColors.RESET + "array AFTER manipulation for 0");
            generateUsingBackTracking( bitIndex + 1);

            arraytoHoldBits[bitIndex] = '1';
            printArray(ConsoleColors.RESET + "array AFTER manipulation for 1");
            generateUsingBackTracking( bitIndex + 1);

            arraytoHoldBits[bitIndex] = '?'; // we are backtracking to previous step as value is passed by reference.

        } else{
            generateUsingBackTracking(bitIndex + 1);
        }
    }

    private void printArray(String message) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arraytoHoldBits.length; i++) {
            builder.append(arraytoHoldBits[i]);
        }
        System.out.println(message + " = " + builder);
    }
}
