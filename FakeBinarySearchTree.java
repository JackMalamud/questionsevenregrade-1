package questionsevenregrade;

/**
 * This class represents the Binary Search Tree assumed by Q7.2. It contains only
 * the property assumed by the question, root(), which returns the node that it
 * stores as the root of the tree. Since the midterm question assumes that all BSTs
 * passed into the algorithm will be correct BSTs, this class does not actually maintain
 * the correct structure of a BST (hence the name FakeBinarySearchTree). In the test class
 * MidtermRegradeTests, I manually create proper BSTs by linking BSTNodes together, before
 * passing the root BSTNode into an instance of FakeBinarySearchTree, which "stores" the tree
 * (in a literal sense, this class only stores a single BSTNode as a "root", but this BSTNode
 * links to other BSTNodes to form a BST).
 */
public class FakeBinarySearchTree {
    private BSTNode root;

    /**
     * Constructor, takes a BSTNode root and passes it through setRoot().
     * @param root
     */
    FakeBinarySearchTree(BSTNode root) {
        setRoot(root);
    }

    /**
     *
     * @return root, the BSTNode stored as the root of the tree
     */
    public BSTNode root() {
        return this.root;
    }

    /**
     * Takes a BSTNode root parameter and stores it as the root.
     * @param root
     */
    private void setRoot(BSTNode root) {
        this.root = root;
    }

}