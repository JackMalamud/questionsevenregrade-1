package questionsevenregrade;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the main class for this program. It stores a FakeBinarySearchTree my_tree, which its
 * constructor receives as a parameter. It has one public method, second_smallest_runner(), which
 * passes the stored my_tree into the private method second_smallest(), the only other method in the class.
 * The second_smallest() implements my algorithm from Q7.2.
 */
public class MidtermRegradeMain {

    // Stores a FakeBinarySearchTree
    FakeBinarySearchTree my_tree;

    /**
     * This is the constructor. It takes a FakeBinarySearchTree as a parameter and stores it
     * in my_tree.
     * @param tree, a FakeBinarySearchTree containing a root which links to other nodes.
     */
    MidtermRegradeMain(FakeBinarySearchTree tree) {
        this.my_tree = tree;
    }

    /**
     * This public method passes my_tree into the private method second_smallest(), which implements my
     * algorithm from Q7.2.
     * @return the smallest value in the tree "stored" in my_tree
     */
    public int second_smallest_runner() {
        return this.second_smallest(this.my_tree);
    }

    /**
     * MY Q7.2 ANSWER:
     * This is the line-by-line direct implementation of my psuedocode from Q7.2.
     * It has the same number of lines of code and utilizes the same datastructures
     * in the same way. The only difference (beyond Java syntax) is that I have corrected
     * my typo in the final return line to make the algorithm functional.
     *
     * As in the midterm question, this method takes a binary search tree as a
     * parameter (in this implementation a FakeBinarySearchTree), which has just
     * one property, tree.root(), which returns its root. The root, and all the
     * nodes linked to it, are instances of BSTNode, which has all the properties
     * specified in the question (node.hasLeft(), node.left, node.hasRight(),
     * node.right, node.parent, node.value).
     *
     * The method takes the root from the tree, and returns its value if it has no children.
     * Otherwise, it then creates a stack of nodes which will be used to track which nodes
     * to inspect, and pushes the root to the stack. It then creates a 2-index array of values,
     * smallvals, which will be used to store the two smallest values, and a counter variable
     * "index" which will be used to index to this array. The Test file proves that this method
     * will always populate smallvals[0] with the smallest value in the tree, and smallvals[1]
     * with the second-smallest value in the tree.
     *
     * As long as the stack is not empty and the counter variable is less than 2, the method
     * uses a while loop to search for the smallest node in O(treeheight) time. It pops the top
     * node from the stack and, if it has a left-child, returns the node to the stack and then
     * pushes its left-child to the stack on top of it, before starting the while loop again.
     * This ensures that the loop will always navigate directly to the left-most node in the tree.
     * Since this is a BST, the left-most node will always have the smallest value. Once the loop
     * reaches the left-most node, it adds it to smallvals[0] and increments the counter from
     * index=0 to index=1. If the left-most node has NO children, the loop will always check its
     * parent next, which in this case (left-most node has no children) will always have the
     * second-smallest value, add it to smallvals[1], and increment the counter from index=1 to
     * index=2, which prevents the loop from running again. If the left-most node DOES have a child,
     * it must always be a right child; in this case, the algorithm navigates directly to the
     * left-most node in the left-most node's right sub-tree, which in this case (left-most node has
     * right subtree) will always have the second smallest value, add it to smallvals[1], and increment
     * the counter to index=2, stopping the loop. (If the root has no left-descendants it is thus the
     * left-most node, and will be added to smallvals[0] in the first run of the while loop, which will
     * then add the left-most node in its right subtree to smallvals[1]). Finally, the algorithm returns
     * smallvals[1] which will always be the second smallest value in the tree.
     *
     * @param tree, a Binary Search Tree, with one property, tree.root().
     * @return the second smallest value in the tree
     */
    private int second_smallest(FakeBinarySearchTree tree) {

        // Grab the root of the tree
        BSTNode root = tree.root();

        // If the root has no right or left child, return its value
        if ((root.hasRight() == false) && (root.hasLeft() == false)) {
            return root.value();
        }

        // Create a new stack of Nodes, push the root to the stack
        Stack<BSTNode> to_check = new Stack<BSTNode>();
        to_check.push(root);

        /*
         * Create a 2-index list, smallvals, which will store the smallest value in the tree
         * at smallvals[0] and the second-smallest value in smallvals[1].
         */
        Integer[] smallvals = new Integer[2];
        // Create a counter variable, index, which will be used to index through smallvals and break the while loop
        int index = 0;

        // While the stack is not empty and the counter variable is less than two...
        while ((to_check.isEmpty() == false) && (index < 2)) {
            // Pop the Node last added to the stack
            BSTNode current = to_check.pop();

            // If the Node has a left child that has not yet been added to smallvals...
            if ((current.hasLeft()) && (Arrays.asList(smallvals).contains(current.left().value()) == false)) {
                // Return the Node to the stack
                to_check.push(current);
                // Push the Node's left child to the stack as well
                to_check.push(current.left());
            }
            // Otherwise (if the Node does not have a left child or its left child is in smallvals)...
            else {
                // Add the Node's value to smallvals at "index" location
                smallvals[index] = current.value();
                // Increment the "index" counter by 1
                index++;

                // If the Node has a right child that is not already in smallvals...
                if ((current.hasRight()) && (Arrays.asList(smallvals).contains(current.right().value()) == false)) {
                    // Push the Node's right child to the stack (and NOT the node itself)
                    to_check.push(current.right());
                }
            }
        }

        // Return the second value stored in smallvals. This will always be the second-smallest value in the tree
        return smallvals[1];
    }
}