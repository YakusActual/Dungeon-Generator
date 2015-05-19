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
public class TreeNode {

    private Object element;
    private TreeNode leftChild, rightChild;

    public TreeNode(Object element) {
        this.element = element;
        leftChild = null;
        rightChild = null;
    }

    public void setLeft(TreeNode child) {
        leftChild = child;
    }

    public void setRight(TreeNode child) {
        rightChild = child;
    }

    public boolean isLeaf() {
        return ((leftChild == null) && (rightChild == null));

    }

    public TreeNode getLeft() {
        return leftChild;
    }

    public TreeNode getRight() {
        return rightChild;
    }

    public Object getElement() {
        return element;
    }

    public boolean isExternal() {
        if ((leftChild == null) && (rightChild == null)) {
            return true;
        } else {
            return false;
        }
    }

}
