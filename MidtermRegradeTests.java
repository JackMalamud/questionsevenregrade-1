package questionsevenregrade;

import junit.framework.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import net.datastructures.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for the second_smallest algorithm on different Binary Search Trees. Q7.2 assumes that the
 * algorithm is always passed a correct Binary Search Tree, so in each test I "hard code" a Binary
 * Search Tree by creating a new BSTNode for each node in the tree, passing the root BSTNode into
 * FakeBinarySearchTree as the root, and joining the rest of the nodes together using the setRight,
 * setLeft, and constructor methods in BSTNode. In each test I then pass the FakeBinarySearchTree,
 * which stores just one BSTNode as its "root," into a new MidtermRegradeMain. This class contains
 * the second_smallest algorithm in a private method called second_smallest(). Its constructor calls
 * this method, and outputs the second smallest value in the tree. Each test then checks if this value
 * is the same as the actual second smallest value in the tree.
 */
public class MidtermRegradeTests {

    @Test
    /**
     *               13
     *             /    \
     *            7     20
     *          /  \   /  \
     *         5  10  15   24
     *           /  \
     *          8   11
     *  This test creates the above basic BST (from Slide 10 on the June 9th lecture).
     *  The correct second-smallest value is 7.
     */
    public void basicBSTTest() {
        BSTNode root = new BSTNode(13, null);

        BSTNode root_left = new BSTNode(7, root);
        BSTNode root_right = new BSTNode(20, root);
        root.setLeft(root_left);
        root.setRight(root_right);

        BSTNode rootleft_left = new BSTNode(5, root_left);
        BSTNode rootleft_right = new BSTNode(10, root_left);
        root_left.setLeft(rootleft_left);
        root_left.setRight(rootleft_right);

        BSTNode rootright_left = new BSTNode(15, root_right);
        BSTNode rootright_right = new BSTNode(24, root_right);
        root_right.setLeft(rootright_left);
        root_right.setRight(rootright_right);

        BSTNode rootleftright_left = new BSTNode(8, rootleft_right);
        BSTNode rootleftright_right = new BSTNode(11, rootleft_right);
        rootleft_right.setLeft(rootleftright_left);
        rootleft_right.setRight(rootleftright_right);

        FakeBinarySearchTree tree = new FakeBinarySearchTree(root);

        MidtermRegradeMain my_main = new MidtermRegradeMain(tree);
        int second_smallest = my_main.second_smallest_runner();
        assertTrue(second_smallest == root_left.value());
    }

    /**
     *     28
     *       \
     *        30
     *          \
     *           33
     * This test creates the above BST with only right descendants.
     * The correct second-smallest value is 30.
     */
    @Test
    public void onlyRightDescendantsTest() {
        BSTNode root = new BSTNode(28,null);
        BSTNode root_right = new BSTNode(30, root);
        root.setRight(root_right);
        BSTNode rootright_right = new BSTNode( 34, root_right);
        root_right.setRight(rootright_right);

        FakeBinarySearchTree tree = new FakeBinarySearchTree(root);

        MidtermRegradeMain my_main = new MidtermRegradeMain(tree);
        int second_smallest = my_main.second_smallest_runner();

        assertTrue(second_smallest == root_right.value());
    }

    /**
     *     15
     *       \
     *        25
     *       /  \
     *      17   68
     * This test creates the above BST with only a right subtree.
     * The correct second-smallest value is 17.
     */
    @Test
    public void onlyRightSubtreeTest() {
        BSTNode root = new BSTNode(15,null);
        BSTNode root_right = new BSTNode(25, root);
        root.setRight(root_right);
        BSTNode rootright_left = new BSTNode(17, root_right);
        BSTNode rootright_right = new BSTNode( 68, root_right);
        root_right.setLeft(rootright_left);
        root_right.setRight(rootright_right);

        FakeBinarySearchTree tree = new FakeBinarySearchTree(root);

        MidtermRegradeMain my_main = new MidtermRegradeMain(tree);
        int second_smallest = my_main.second_smallest_runner();

        assertTrue(second_smallest == rootright_left.value());
    }


    /**
     *             8
     *           /   \
     *          3     10
     *        /   \     \
     *       1     6     14
     *        \   / \    /
     *        2  4  7  13
     * This test creates the above BST, in which the leftmost node
     * (with value 1) has a right child (with value 2).
     * The correct second-smallest value is 2.
     */
    @Test
    public void leftmostNodeHasRightChildTest() {
        BSTNode root = new BSTNode(8, null);
        BSTNode root_left = new BSTNode(3, root);
        BSTNode root_right = new BSTNode(10, root);
        root.setLeft(root_left);
        root.setRight(root_right);

        BSTNode rootleft_left = new BSTNode(1, root_left);
        BSTNode rootleft_right = new BSTNode(6, root_left);
        root_left.setLeft(rootleft_left);
        root_left.setRight(rootleft_right);

        BSTNode rootright_right = new BSTNode(14, root_right);
        root_right.setRight(rootright_right);

        BSTNode rootleftleft_right = new BSTNode(2, rootleft_left);
        rootleft_left.setRight(rootleftleft_right);

        BSTNode rootleftright_left = new BSTNode(4, rootleft_right);
        BSTNode rootleftright_right = new BSTNode(7, rootleft_right);
        rootleft_right.setLeft(rootleftright_left);
        rootleft_right.setRight(rootleftright_right);

        BSTNode rootrightright_left = new BSTNode(13, rootright_right);
        rootright_right.setLeft(rootrightright_left);

        FakeBinarySearchTree tree = new FakeBinarySearchTree(root);

        MidtermRegradeMain my_main = new MidtermRegradeMain(tree);
        int second_smallest = my_main.second_smallest_runner();

        assertTrue(second_smallest == rootleftleft_right.value());
    }

    /**
     *             45
     *           /   \
     *          35    82
     *        /   \
     *       20   40
     *         \
     *          31
     *         /
     *        28
     * This test creates the above BST, in which the leftmost node
     * (with value 20) has a right child (with value 31) that has its
     * own left child (with value 28).
     * The correct second-smallest value is 28.
     */
    @Test
    public void leftmostNodeHasRightChildWithLeftChildTest() {
        BSTNode root = new BSTNode(45, null);
        BSTNode root_left = new BSTNode(35, root);
        BSTNode root_right = new BSTNode(82, root);
        root.setLeft(root_left);
        root.setRight(root_right);

        BSTNode rootleft_left = new BSTNode(20, root_left);
        BSTNode rootleft_right = new BSTNode(40, root_left);
        root_left.setLeft(rootleft_left);
        root_left.setRight(rootleft_right);

        BSTNode rootleftleft_right = new BSTNode(31, rootleft_left);
        rootleft_left.setRight(rootleftleft_right);

        BSTNode rootleftleftright_left = new BSTNode(28, rootleftleft_right);
        rootleftleft_right.setLeft(rootleftleftright_left);

        FakeBinarySearchTree tree = new FakeBinarySearchTree(root);

        MidtermRegradeMain my_main = new MidtermRegradeMain(tree);
        int second_smallest = my_main.second_smallest_runner();

        assertTrue(second_smallest == rootleftleftright_left.value());
    }

}