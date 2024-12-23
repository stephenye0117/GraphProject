/**
 * Project 4
 */

/**
 * Represents an edge in a graph, connecting two vertices with a specified
 * weight.
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
public class Edge {
    private int vertex;
    private int weight;
    private Edge prev;
    private Edge next;

    /**
     * Constructs a new Edge with the given parameters.
     *
     * @param vertex
     *            The vertex at the other end of the edge.
     * @param weight
     *            The weight associated with the edge.
     * @param prev
     *            Reference to the previous edge in the adjacency list.
     * @param next
     *            Reference to the next edge in the adjacency list.
     */
    public Edge(int vertex, int weight, Edge prev, Edge next) {
        this.vertex = vertex;
        this.weight = weight;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Gets the vertex at the other end of the edge.
     *
     * @return The vertex.
     */
    public int getVertex() {
        return vertex;
    }

    /**
     * Sets the vertex at the other end of the edge.
     *
     * @param vertex The vertex to set.
     */
    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    /**
     * Gets the weight associated with the edge.
     *
     * @return The weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight associated with the edge.
     *
     * @param weight The weight to set.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets the reference to the previous edge in the adjacency list.
     *
     * @return The previous edge.
     */
    public Edge getPrev() {
        return prev;
    }

    /**
     * Sets the reference to the previous edge in the adjacency list.
     *
     * @param prev The previous edge to set.
     */
    public void setPrev(Edge prev) {
        this.prev = prev;
    }

    /**
     * Gets the reference to the next edge in the adjacency list.
     *
     * @return The next edge.
     */
    public Edge getNext() {
        return next;
    }

    /**
     * Sets the reference to the next edge in the adjacency list.
     *
     * @param next The next edge to set.
     */
    public void setNext(Edge next) {
        this.next = next;
    }
}
