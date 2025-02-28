import student.TestCase;

/**
 * Project 4
 */

/**
 * Test cases for the Node class
 *
 * @author {Stephen Ye}
 * @version {11/20/2023}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
public class NodeTest extends TestCase {

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        // empty on purpose
    }


    /**
     * Tests the getIndex method of the Node class.
     * It creates a node with a specific index and verifies if getIndex returns
     * the correct value.
     */
    public void testGetIndex() {
        Node node = new Node(1);
        assertEquals(1, node.getIndex());
    }


    /**
     * Tests the setIndex method of the Node class.
     * It creates a node, sets a new index using setIndex, and verifies if
     * getIndex returns the updated value.
     */
    public void testSetIndex() {
        Node node = new Node(2);
        assertEquals(2, node.getIndex());

        node.setIndex(99);
        assertEquals(99, node.getIndex());
    }

}
