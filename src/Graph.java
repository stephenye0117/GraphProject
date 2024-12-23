
/**
 * Project 4
 */

/**
 * Represents an undirected graph with weighted edges.
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
public class Graph {

    private Edge[] nodeArray;
    private Object[] values;
    private int numEdge;

    /**
     * Constructs a new graph with the specified initial size.
     *
     * @param initialSize
     *            The initial size of the graph.
     */
    public Graph(int initialSize) {
        init(initialSize);
    }


    /**
     * Initializes the graph with the given number of vertices.
     *
     * @param n
     *            The number of vertices in the graph.
     */
    public void init(int n) {
        nodeArray = new Edge[n];
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Edge(-1, -1, null, null);
        }
        values = new Object[n];
        numEdge = 0;
    }


    /**
     * Sets the value associated with a vertex.
     *
     * @param v
     *            The vertex for which the value is set.
     * @param val
     *            The value to associate with the vertex.
     */
    public void setValue(int v, Object val) {
        values[v] = val;
    }


    /**
     * Returns the number of edges in the graph.
     *
     * @return The number of edges in the graph.
     */
    public int edgeCount() {
        return numEdge;
    }


    /**
     * Returns the number of vertices in the graph.
     *
     * @return The number of vertices in the graph.
     */
    public int nodeCount() {
        return nodeArray.length;
    }


    /**
     * Returns the value associated with a vertex.
     *
     * @param v
     *            The vertex for which the value is retrieved.
     * @return The value associated with the vertex.
     */
    public Object getValue(int v) {
        return values[v];
    }


    /**
     * Finds the edge between two vertices or the location to insert a new edge.
     *
     * @param v
     *            The source vertex.
     * @param w
     *            The destination vertex.
     * @return The edge prior to the specified destination vertex.
     */
    private Edge find(int v, int w) {
        Edge curr = nodeArray[v];
        while ((curr.getNext() != null) && (curr.getNext().getVertex() < w)) {
            curr = curr.getNext();
        }
        return curr;
    }


    /**
     * Adds a weighted edge between two vertices.
     *
     * @param v
     *            The source vertex.
     * @param w
     *            The destination vertex.
     * @param wgt
     *            The weight of the edge.
     * @return True if the edge is added successfully, false otherwise.
     */
    public boolean addEdge(int v, int w, int wgt) {
        if (wgt == 0) {
            return false; // Edge with weight 0 is not allowed
        }

        Edge curr = find(v, w);

        // Check if the edge already exists
        if ((curr.getNext() != null) && (curr.getNext().getVertex() == w)) {
            curr.getNext().setWeight(wgt);
        }
        else {
            curr.setNext(new Edge(w, wgt, curr, curr.getNext()));
            numEdge++;

            // Update the previous and next pointers
            if (curr.getNext().getNext() != null) {
                curr.getNext().getNext().setPrev(curr.getNext());
            }

            return true;
        }

        return false;
    }


    /**
     * Removes the edge between two vertices.
     *
     * @param v
     *            The source vertex.
     * @param w
     *            The destination vertex.
     */
    public void removeEdge(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.getNext() == null) || (curr.getNext().getVertex() != w)) {
            return;
        }
        else {
            curr.setNext(curr.getNext().getNext());
            if (curr.getNext() != null) {
                curr.getNext().setPrev(curr);
            }
        }
        numEdge--;
    }


    /**
     * Returns the weight of the edge between two vertices.
     *
     * @param v
     *            The source vertex.
     * @param w
     *            The destination vertex.
     * @return The weight of the edge, or 0 if no edge exists.
     */
    public int weight(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.getNext() == null) || (curr.getNext().getVertex() != w)) {
            return 0;
        }
        else {
            return curr.getNext().getWeight();
        }
    }


    /**
     * Checks if there is an edge between two vertices.
     *
     * @param v
     *            The source vertex.
     * @param w
     *            The destination vertex.
     * @return True if there is an edge, false otherwise.
     */
    public boolean hasEdge(int v, int w) {
        return weight(v, w) != 0;
    }


    /**
     * Returns an array of vertices that are neighbors of the specified vertex.
     *
     * @param v
     *            The vertex for which neighbors are retrieved.
     * @return An array of neighboring vertices.
     */
    public int[] neighbors(int v) {
        if (v < 0 || v >= nodeArray.length) {

            return new int[0];
        }

        int cnt = 0;
        Edge curr;
        for (curr = nodeArray[v].getNext(); curr != null; curr = curr
            .getNext()) {
            cnt++;
        }

        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = nodeArray[v].getNext(); curr != null; curr = curr
            .getNext()) {
            temp[cnt++] = curr.getVertex();
        }

        return temp;
    }


    /**
     * Returns the index of a vertex with the specified value.
     *
     * @param key
     *            The value to search for.
     * @return The index of the vertex if found, or -1 otherwise.
     */
    public int getIndex(Object key) {
        for (int i = 0; i < nodeArray.length; i++) {
            if (values[i] != null && values[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

}
