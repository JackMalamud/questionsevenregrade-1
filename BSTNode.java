package questionsevenregrade;

/**
 * This class represents a node in the Binary Search Tree assumed by Q7.2. It stores
 * an integer variable representing its value, and BSTNode variables representing its left-
 * and right-children and its parent. It has a constructor which takes a value and a BSTNode parent,
 * sets those variables accordingly, and sets its children to null. This class contains all the
 * properties of nodes assumed by Q7.2. It also contains other methods to set its value, children,
 * and parents. These other methods are NOT used in the implemenation of my algorithm in the second_smallest()
 * method in the MidtermRegradeMain class, they are only used to construct trees in the MidtermRegradeTests class.
 */
public class BSTNode {
    private int value;
    private BSTNode left;
    private BSTNode right;
    private BSTNode parent;

    // Constructor: takes a value and a parent
    BSTNode(int val, BSTNode new_parent) {
        setValue(val);
        setParent(new_parent);
        setLeft(null);
        setRight(null);
    }

    // Sets the value of the node
    private void setValue(int val) {
        this.value = val;
    }

    // Sets the parent of the node
    private void setParent(BSTNode prnt) {
        this.parent = prnt;
    }

    // Sets the left child
    public void setLeft(BSTNode new_left) {
        this.left = new_left;
    }

    // Sets the right child
    public void setRight(BSTNode new_right) {
        this.right = new_right;
    }

    // Returns the value
    public int value() {
        return this.value;
    }

    // Returns the left child
    public BSTNode left() {
        return this.left;
    }

    // Returns the right child
    public BSTNode right() {
        return this.right;
    }

    // Returns the parent
    public BSTNode parent() {
        return this.parent;
    }

    // Checks if has left child
    public boolean hasLeft() {
        if (this.left == null) {
            return false;
        } else {
            return true;
        }
    }

    // Checks if has right child
    public boolean hasRight() {
        if (this.right == null) {
            return false;
        } else {
            return true;
        }
    }

}