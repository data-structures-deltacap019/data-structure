package com.deltacap019.Trees;

import apple.laf.JRSUIUtils;
import com.deltacap019.utility.ConsoleColors;
import com.deltacap019.utility.PrintTree;
import com.deltacap019.utility.PrintableTreeNode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Level order traversal is also called "BFS - Breadth First Search"
 * we first traverse nodes level wise here instead of going to depth
 * in search of left-most node.
 */
public class LevelOrderTraversalBFS {

    private class TreeNode implements PrintableTreeNode {

        private String data;
        private boolean childrenVisited = false;
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

        @Override
        public String toString() {
            return data;
        }
    }

    private TreeNode root;
    private StringBuilder levelOrder = new StringBuilder();

    public LevelOrderTraversalBFS() {
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
        print(ConsoleColors.BLUE_BOLD, "LevelOrder: " + levelOrder.toString());

        print(ConsoleColors.RED, "Tree Traversal: ITERATIVE");
        levelOrder.delete(0, levelOrder.length());
        way2Iterative();
        print(ConsoleColors.BLUE_BOLD, "LevelOrder: " + levelOrder.toString());

    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    ArrayList<TreeNode> sameLevelNodes = new ArrayList<>();
    private void way1Recursive (TreeNode node) {
        if(node == null) {
            return;
        }
        sameLevelNodes.add(node);
        recursiveLogic(sameLevelNodes);
    }

    private void recursiveLogic (ArrayList<TreeNode> sameLevelNodes) {
        ArrayList<TreeNode> nextLevelNodes = new ArrayList<>();
        for (TreeNode currentNode:  sameLevelNodes) {
            levelOrder.append(currentNode.getText() + ", ");
            if (currentNode.getLeft() != null) {
                nextLevelNodes.add((TreeNode) currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                nextLevelNodes.add((TreeNode) currentNode.getRight());
            }
        }
        if (!nextLevelNodes.isEmpty()) {
            recursiveLogic(nextLevelNodes);
        }
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way2Iterative () {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            levelOrder.append(currentNode.getText() + ", ");

            if (currentNode.getLeft() != null) {
                queue.add((TreeNode) currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add((TreeNode) currentNode.getRight());
            }
        }
    }

    private void print(String consoleColor, String message) {
        System.out.println(consoleColor + message);
    }

}
