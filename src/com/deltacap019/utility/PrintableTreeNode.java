package com.deltacap019.utility;

/**
 *
 * Source : https://stackoverflow.com/a/46279667/1206052
 */
public interface PrintableTreeNode {
    /** Get left child */
    PrintableTreeNode getLeft();


    /** Get right child */
    PrintableTreeNode getRight();


    /** Get text to be printed */
    String getText();
}
