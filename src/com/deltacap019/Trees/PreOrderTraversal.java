package com.deltacap019.Trees;

import com.deltacap019.utility.ConsoleColors;
import com.deltacap019.utility.PrintTree;
import com.deltacap019.utility.PrintableTreeNode;

import java.util.Stack;

/**
 * PreOrder - Root,Left,Right
 *  first root is accessed thn left / right node.
 *  - Visit the root
 *  - Traverse left subtree
 *  - Traverse right subtree
 */
public class PreOrderTraversal {

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
    private StringBuilder preOrder = new StringBuilder();

    public PreOrderTraversal() {
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
        print(ConsoleColors.BLUE_BOLD, "PreOrder: " +preOrder.toString());

        print(ConsoleColors.RED, "Tree Traversal: ITERATIVE");
        preOrder.delete(0, preOrder.length());
        way2Iterative();
        print(ConsoleColors.BLUE_BOLD, "PreOrder: " +preOrder.toString());
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way1Recursive (TreeNode node) {
        if(node == null) {
            return;
        }
        preOrder.append(node.getText() + ", ");
        way1Recursive((TreeNode) node.getLeft());
        way1Recursive((TreeNode) node.getRight());
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way2Iterative () {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            preOrder.append(temp.getText() + ", ");
            if (temp.getRight() != null)
            stack.push((TreeNode) temp.getRight());
            if (temp.getLeft() != null)
            stack.push((TreeNode) temp.getLeft());
        }

    }

    private void print(String consoleColor, String message) {
        System.out.println(consoleColor + message);
    }
}
