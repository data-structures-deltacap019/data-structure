package com.deltacap019.Trees;

import com.deltacap019.utility.ConsoleColors;
import com.deltacap019.utility.PrintTree;
import com.deltacap019.utility.PrintableTreeNode;
import com.sun.tools.javac.util.StringUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * PostOrder - Left,Right, Root
 *  first left subtree is traversed then root then right node.
 * - Traverse the left subtree in Postorder
 * - Traverse the right subtree in Postorder.
 * - Visit the root.
 */
public class PostOrderTraversal {

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
    }

    private TreeNode root;
    private StringBuilder postOrder = new StringBuilder();

    public PostOrderTraversal() {
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
        print(ConsoleColors.BLUE_BOLD, "InOrder: " +postOrder.toString());

        print(ConsoleColors.RED, "Tree Traversal: ITERATIVE single stack");
        postOrder.delete(0, postOrder.length());
        way2IterativeWith1Stack();
        print(ConsoleColors.BLUE_BOLD, "InOrder: " +postOrder.toString());

        print(ConsoleColors.RED, "Tree Traversal: ITERATIVE double stack");
        postOrder.delete(0, postOrder.length());
        way2IterativeWith2Stacks();

    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way1Recursive (TreeNode node) {
        if(node == null) {
            return;
        }
        way1Recursive((TreeNode) node.getLeft());
        way1Recursive((TreeNode) node.getRight());
        postOrder.append(node.getText() + ", ");
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way2IterativeWith1Stack () {

        // double stack solution
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode currentNode = null;
        TreeNode prevNode = null;


        while (!stack.isEmpty()) {

            currentNode = stack.peek();

            // making decisions on prevNode as currentNode cannot be used, as we visit the parent element in the last.
            if(prevNode == null || prevNode.getLeft() == currentNode || prevNode.getRight() == currentNode) { // either root node for which prev == null or leaf nodes

                if (currentNode.getLeft() != null) {
                    stack.push((TreeNode) currentNode.getLeft());
                }else if (currentNode.getRight() != null) {
                    stack.push((TreeNode) currentNode.getRight());
                }
            } else if (currentNode.getLeft() == prevNode) { // traversing up
                if (currentNode.getRight() != null) {
                    stack.push((TreeNode) currentNode.getRight());
                }
            }else {
                // print stack sate
                postOrder.append(currentNode.getText() + ", ");
                stack.pop();
            }

            prevNode = currentNode;
        }
    }

    /**
     * Time Complexity: O(n). Space Complexity: O(n).
     */
    private void way2IterativeWith2Stacks () {

        // double stack solution
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {

            TreeNode temp = stack1.pop();
            stack2.push(temp); // stack 2 contains elements in post order.

            // print stack sate
            print(ConsoleColors.GREEN_BOLD, "STACK 1 : " +  printStack(stack1.clone()));
            print(ConsoleColors.GREEN_BOLD, "STACK 2 (postOrder): " +  printStack(stack2.clone()));
//            // print post order state
//            print(ConsoleColors.BLUE_BOLD, "PostOrder: " +postOrder.toString());

            // stack 1 has [right (top), left]
            if (temp.getLeft() != null) {
                stack1.push((TreeNode) temp.getLeft());
            }
            if (temp.getRight() != null) {
                stack1.push((TreeNode) temp.getRight());
            }
        }
    }

    private String printStack (Object stack){
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (((Stack<TreeNode>) stack).isEmpty()) {
            return result.append("]").toString();
        }
        while (!((Stack<TreeNode>) stack).isEmpty()) {
            result.append(((Stack<TreeNode>) stack).pop().getText());
            result.append(",");
        }
        return result.append("]").toString();
    }

    private void print(String consoleColor, String message) {
        System.out.println(consoleColor + message);
    }

}
