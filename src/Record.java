/**
 * Project 4
 */

/**
 * Represents a record in Project 4
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
public class Record {
    private String key;
    private Node node;
    private boolean tombstone;

    /**
     * Constructor to initialize a record with a key, associated node, and
     * default tombstone status.
     *
     * @param key
     *            The key associated with the record.
     * @param node
     *            The node associated with the record.
     */
    public Record(String key, Node node) {
        this.key = key;
        this.node = node;
        this.tombstone = false; // Default value
    }


    /**
     * Getter method to retrieve the key of the record.
     *
     * @return The key associated with the record.
     */
    public String getKey() {
        return key;
    }


    /**
     * Getter method to retrieve the node associated with the record.
     *
     * @return The node associated with the record.
     */
    public Node getNode() {
        return node;
    }


    /**
     * Getter method to check if the record is marked as a tombstone.
     *
     * @return True if the record is marked as a tombstone, false otherwise.
     */
    public boolean isTombstone() {
        return tombstone;
    }


    /**
     * Setter method to update the key of the record.
     *
     * @param key
     *            The new key to be associated with the record.
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * Setter method to update the node associated with the record.
     *
     * @param node
     *            The new node to be associated with the record.
     */
    public void setNode(Node node) {
        this.node = node;
    }


    /**
     * Setter method to update the tombstone status of the record.
     *
     * @param tombstone
     *            True to mark the record as a tombstone, false otherwise.
     */
    public void setTombstone(boolean tombstone) {
        this.tombstone = tombstone;
    }
}
