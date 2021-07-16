# questionsevenregrade
This is a program implementing and proving my psuedocode for Q7.2 on the midterm.

How to Run This Program:
In IntelliJ, just run MidtermRegradeTests.java. This contains a number of tests; each test creates a new Binary Search Tree to represent an edge case, and tests my algorithm
against that tree.

High Level Program Design:
My algorithm for Q7.2 is implemented line-by-line in the second_smallest() method in the MidtermRegradeMain class. In order to implement and test the algorithm, I wrote this 
program that also includes a BSTNode class, with all the properties of the nodes assumed by Q7.2, and a FakeBinarySearchTree class, which represents the Binary Search Tree 
(BST) that Q7.2 assumes exists but asks us to assume only has a tree.root() property, which returns its root. As such, FakeBinarySearchTree does not actually implement a BST, 
it simply stores a BSTNode as its "root," which is connected to other BSTNodes using their properties (setLeft, setRight, setParent, etc.) to form a tree. For each test, I 
"manually" create a Binary Search Tree by instantiating new BSTNodes for each nodes in the tree, linking them together, and passing the root BSTNode into a new instance of 
FakeBinarySearchTree. For simplicity's sake, BSTNode (and the rest of the program) is hard-coded to take only an Integer as a value; an actual implementation of my algorithm
would of course assume that the implementation of the relevant node class used generics to represent its value. As a result, my algorithm utilizes a 2-index long Array that 
is coded to hold Integers, but the algorithm itself does not rely on the values being Integers; as long as the type of Array is changed accordingly, my algorithm will 
function identically regardless of what type of data (String, Float, or any other class) is stored as the nodes' values, since it does not perform any operations on and in no 
way manipulates those values. It identifies the second-smallest value in the tree based solely on the structure of the BST.

BSTNode.java:
This class is an implementation of a node in the BST that Q7.2 asks us to assume will be passed to our algorithm. According to Q7.2, we should assume that the node has the
properties and methods: node.hasLeft(), node.left, node.hasRight(), node.right, node.parent, node.value. BSTNode implements each of these, with the same name, as methods.
It stores an integer variable representing its value, and three BSTNode variables, representing its parent, left-child, and right-child. BSTNode also implements a constructor
and the methods setParent(), setLeft(), and setRight(), but these are only used in the test class to construct Binary Search Trees against which my algorithm is tested. My
algorithm itself ONLY uses the BSTNode methods that Q7.2 allows us to.

FakeBinarySearchTree.java:
This class represents the BTS that Q7.2 asks us to assume will be passed into our algorithm. According to Q7.2, this BTS only has the property tree.root, which returns its 
root. As a result, FakeBinarySearchTree stores a single BSTNode as its "root," and contains only a constructor, a method to set the root, and a method to get the root. The 
latter method, which represents tree.root from Q7.2 is the only method that my algorithm uses. The constructor (which calls the setRoot() method) is used only in the test 
class to construct a BST against which my algorithm is tested. Since Q7.2 assumes that the input is always a proper BST, this class does not actually implement or maintain a 
BST; each test method "manually" creates a BST, and this class stores each BST's root and allows my algorithm to access it.

MidtermRegradeMain.java:
This class is used by the test class to test my algorithm to find the second-smallest value in a given BST. It stores a FakeBinarySearchTree (which holds the root of the tree
against which the algorithm will be tested), which is passed as a parameter through its constructor. It has only one public method, second_smallest_runner(), which is called
by a test class to test the algorithm; this method passes the stored FakeBinarySearchTree into the second_smallest(), the private method which implements my algorithm, and
returns the private method's output. The final method in this class is second_smallest(), a private method which is a line-by-line implementation of my psuedocode answer for
Q7.2. It takes a FakeBinarySearchTree (representing the tree with only the property tree.root from Q7.2) as a parameter, and returns the smallest value in the BST whose root
is stored in the FakeBinarySearchTree.

MidtermRegradeTests.java:
This class tests my algorithm against a number of BSTs, representing a basic case and each edge case mentioned by the TA comment on my solution as a case in which my
algorithm would fail. In each test method, I "manually" build a BST by instantiating and linking new BSTNodes and then pass the root BSTNode into a new FakeBinarySearchTree.
I then create a new MidtermRegradeMain instance, passing the FakeBinarySearchTree into its constructor, and call second_smallest_runner() on the MidtermRegradeMain (which 
returns the output of my algorithm when it is passed the given FakeBinarySearchTree), storing the output in a variable called second_smallest. Finally, I use asserTrue() to
confirm that the value stored in second_smallest (the output of my algorithm) is the same as the second-smallest value stored in the tree. The comment above test class also
includes a visual representation of the tree aginst my algorithm is being tested, for readability.

Test Cases:
In MidtermRegradeTests.java, basicBSTTest() tests my algorithm against a a simple BST of height 3, in which the root has both left- and right-subtree and in which the 
left-most node has no children. The method onlyRightDescendantsTest() tests my algorithm against a BST in which every node only has right-descendants. The method 
onlyRightSubtreeTest() tests my algorithm against a BST in which the root only has a right-subtree, but the root of that subtree has left-descendants. The method 
leftmostNodeHasRightChildTest() tests my algorithm against a BST in which the left-most node has a right-child which itself has no children. The method 
leftmostNodeHasRightChildWithLeftChildTest() tests my algorithm against a BST in which the left-most node has a right-child which has its own left-child. All tests are 
passed.
