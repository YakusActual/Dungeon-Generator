/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

/**
 *
 * @author YakusActual
 */
public class TreeCollection {

    public TreeNode theRoot;

    /**
     * Creates a new instance of BinaryTree
     */
    public TreeCollection() {
        theRoot = null;
    }

    //return the root of the tree
    public TreeNode root() {
        return theRoot;
    }

    //check if the tree is empty
    public boolean isEmpty() {
        if (theRoot == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertNode(TreeNode startNode, TreeNode theNewNode) {
        // if the tree is empty theNewNode becames the root of the tree
        if (theRoot == null) {
            theRoot = theNewNode;
        } else { // find the location and insert theNewNode in the non-empty tree
            if (startNode.getLeft() == null) {
                startNode.setLeft(theNewNode);
            } else if (startNode.getRight() == null) {
                startNode.setRight(theNewNode);
            } else {

            }
        }
    }

    // search and find the node that contains the given element. It returns the node that has that element
//    public TreeNode search(Object searchelem, TreeNode startNode) {
//                if (startNode == null) {
//            return null;
//        } else {
//            if (searchelem == startNode.getElement()) {
//                return startNode;
//            } else if (searchelem < startNode.getElement()) {
//                return search(searchelem, startNode.getLeft());
//            } else {
//                return search(searchelem, startNode.getRight());
//            }
//        }
//    }

    // in-order traversal is used to parse and print all the nodes of the tree
    public void inOrderTraversal(TreeNode theStart) {
        if (theStart != null) {
            inOrderTraversal(theStart.getLeft());
            System.out.println("Node: " + theStart.getElement());
            inOrderTraversal(theStart.getRight());
        }
    }

    //pre-order traversal is used to parse and print all the nodes of the tree
    public void preOrderTraversal(TreeNode theStart) {
        if (theStart != null) {
            System.out.println("Node:" + theStart.getElement());
            preOrderTraversal(theStart.getLeft());
            preOrderTraversal(theStart.getRight());
        }
    }

    //post-order traversal is used to parse and print all the nodes of the tree
    public void postOrderTraversal(TreeNode theStart) {
        if (theStart != null) {
            postOrderTraversal(theStart.getLeft());
            postOrderTraversal(theStart.getRight());
            System.out.println("Node:" + theStart.getElement());
        }
    }

    //counts the number of nodes in the BinaryTree.
    public int countNodes(TreeNode theStart) {
        if (theStart == null) {
            return 0;
        } else {
            return 1 + countNodes(theStart.getLeft())
                    + countNodes(theStart.getRight());
        }
    }

    //determines the height of a tree
    public int height(TreeNode theStartNode) {
        if (theStartNode == null || theStartNode.isExternal()) {
            return 0;
        } else {
            int leftChildH = height((TreeNode) theStartNode.getLeft());
            int rightChildH = height((TreeNode) theStartNode.getRight());
            return 1 + Math.max(leftChildH, rightChildH);
        }
    }

}
