import student.TestCase;

/**
 * Project 4
 */

/**
 * Test class for the Graph class.
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
public class GraphTest extends TestCase {

    private Graph graph;

    /**
     * Sets up the tests by initializing a Graph instance.
     */

    public void setUp() {
        graph = new Graph(5);
    }


    /**
     * Test the initialization of the graph.
     */

    public void testGraphInitialization() {
        assertEquals(5, graph.nodeCount());
        assertEquals(0, graph.edgeCount());
    }


    /**
     * Test adding an edge to the graph.
     */

    public void testAddEdge() {
        graph.addEdge(0, 1, 10);
        assertEquals(1, graph.edgeCount());
        assertTrue(graph.hasEdge(0, 1));
        assertEquals(10, graph.weight(0, 1));

        graph.addEdge(0, 1, 20);
        assertEquals(1, graph.edgeCount()); 
        assertTrue(graph.hasEdge(0, 1));
        assertEquals(20, graph.weight(0, 1));

        graph.addEdge(1, 2, 15);
        assertEquals(2, graph.edgeCount());
        assertTrue(graph.hasEdge(1, 2));
        assertEquals(15, graph.weight(1, 2));

        graph.addEdge(2, 3, 0);
        assertEquals(2, graph.edgeCount()); // No change in edge count
        assertFalse(graph.hasEdge(2, 3));
        assertEquals(0, graph.weight(2, 3));

        graph.addEdge(3, 4, -5);
        assertEquals(3, graph.edgeCount()); // No change in edge count
        assertTrue(graph.hasEdge(3, 4));
        assertEquals(-5, graph.weight(3, 4));
    }


    /**
     * Test removing an edge from the graph.
     */

    public void testRemoveEdge() {
        graph.addEdge(0, 1, 10);
        graph.removeEdge(0, 1);
        assertEquals(0, graph.edgeCount());
        assertFalse(graph.hasEdge(0, 1));
        assertEquals(0, graph.weight(0, 1));
    }


    /**
     * Test getting neighbors of a node in the graph.
     */

    public void testGetNeighbors() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 20);
        int[] neighbors = graph.neighbors(0);
        assertNotNull(neighbors);
    }


    /**
     * Test getting the index of a value in the graph.
     */

    public void testGetIndex() {
        graph.setValue(0, "NodeA");
        graph.setValue(1, "NodeB");
        assertEquals(0, graph.getIndex("NodeA"));
        assertEquals(1, graph.getIndex("NodeB"));
        assertEquals(-1, graph.getIndex("NodeC"));
    }
}
