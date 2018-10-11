package com.deltacap019.Trees;

import com.deltacap019.utility.ConsoleColors;
import com.deltacap019.utility.PrintTree;
import com.deltacap019.utility.PrintableTreeNode;

import java.util.Stack;

/**
 * InOrder - Left,Root,Right
 *  first left subtree is traversed then root then right node.
 * - Traverse the left subtree in Inorder.
 * - Visit the root.
 * - Traverse the right subtree in Inorder.
 */

public class InOrderTraversal {

    private class TreeNode implements PrintableTreeNode {

        private String data;
        private TreeNode leftNode = null, rightNode = null;

        public TreeNode(String data) {
            this.data = data;
        }

        public void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }

        @Override
        public PrintableTreeNode getLeft() {
            return leftNode;
        }

        @Override
        public PrintableTreeNode getRight() {
            return rightNode;
        }

        @Override
        public String getText() {
            return data;
        }
    }

    private TreeNode root;
    private StringBuilder inOrder = new StringBuilder();

    public InOrderTraversal() {
        createTree();
    }

    private void createTree () {

        TreeNode nodeP = new TreeNode("P");
        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeS = new TreeNode("S");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeH = new TreeNode("H");
        TreeNode nodeG = new TreeNode("G");
        TreeNode nodeR = new TreeNode("R");
        TreeNode nodeY = new TreeNode("Y");
        TreeNode nodeT = new TreeNode("T");
        TreeNode nodeZ = new TreeNode("Z");
        TreeNode nodeW = new TreeNode("W");

        nodeP.setLeftNode(nodeF);
        nodeP.setRightNode(nodeS);

        nodeF.setLeftNode(nodeB);
        nodeF.setRightNode(nodeH);

        nodeS.setLeftNode(nodeR);
        nodeS.setRightNode(nodeY);

        nodeB.setLeftNode(null);
        nodeB.setRightNode(null);

        nodeH.setLeftNode(nodeG);
        nodeH.setRightNode(null);

        nodeG.setLeftNode(null);
        nodeG.setRightNode(null);

        nodeR.setLeftNode(null);
        nodeR.setRightNode(null);

        nodeY.setLeftNode(nodeT);
        nodeY.setRightNode(nodeZ);

        nodeT.setLeftNode(null);
        nodeT.setRightNode(nodeW);

        nodeZ.setLeftNode(null);
        nodeZ.setRightNode(null);

        nodeW.setLeftNode(null);
        nodeW.setRightNode(null);

        root = nodeP;

        PrintTree.print(root);
    }

    public void traverse() {
        print(ConsoleColors.RED, "Tree Traversal: RECURSIVE");
        way1Recursive(root);
        print(ConsoleColors.BLUE_BOLD, "InOrder: " +inOrder.toString());

        print(ConsoleColors.RED, "Tree Traversal: ITERATIVE");
        inOrder.delete(0, inOrder.length());
        way2Iterative();
        print(ConsoleColors.BLUE_BOLD, "InOrder: " +inOrder.toString());
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way1Recursive (TreeNode node) {
        if(node == null) {
            return;
        }
        way1Recursive((TreeNode) node.getLeft());
        inOrder.append(node.getText() + ", ");
        way1Recursive((TreeNode) node.getRight());
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way2Iterative () {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;

        while (!stack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = (TreeNode) currentNode.getLeft();

            }else {
                currentNode = stack.pop();
                inOrder.append(currentNode.getText() + ", ");

                currentNode = (TreeNode) currentNode.getRight();
            }
        }


    }

    private void print(String consoleColor, String message) {
        System.out.println(consoleColor + message);
    }
}
