/**
 * Project 4
 */

/**
 * The Node class represents a node in the graph
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
public class Node {
    private int index;

    /**
     * Constructs a new Node with the specified index.
     *
     * @param index
     *            The unique identifier for the node within the graph.
     */
    public Node(int index) {
        this.index = index;
    }


    /**
     * Gets the index of the node.
     *
     * @return The unique identifier of the node within the graph.
     */
    public int getIndex() {
        return index;
    }


    /**
     * Sets the index of the node.
     *
     * @param index
     *            The new unique identifier for the node within the graph.
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
