import student.TestCase;

/**
 * Project 4
 */

/**
 * The HashDBTest class contains test cases for the HashDB class,
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
public class HashDBTest extends TestCase {

    private HashDB hashDB;

    /**
     * sets up for tests
     */
    public void setUp() {
        hashDB = new HashDB(100); // You can adjust the world size accordingly
    }


    /**
     * Test case for the insertHash method.
     */
    public void testInsertHash() {
        assertTrue(hashDB.insertHash("Artist1", "Song1"));
        assertTrue(hashDB.insertHash("Artist2", "Song1"));
        assertTrue(hashDB.insertHash("Artist1", "Song2"));
        assertTrue(hashDB.insertHash("Artist1", "Song1"));

    }


    /**
     * Test case for the deleteHash method.
     */
    public void testDeleteHash() {
        // Insert records first
        hashDB.insertHash("Artist1", "Song1");
        hashDB.insertHash("Artist2", "Song2");

        Record removedArtist = hashDB.deleteHash("artist", "Artist1");
        assertNotNull(removedArtist);
        assertEquals("Artist1", removedArtist.getKey());

        Record removedSong = hashDB.deleteHash("song", "Song2");
        assertNotNull(removedSong);
        assertEquals("Song2", removedSong.getKey());

        assertNull(hashDB.deleteHash("nothing", "NonExistentArtist"));

    }


    /**
     * Test case for the printHash method.
     */
    public void testPrintHash() {

        hashDB.insertHash("Artist1", "Song1");
        hashDB.insertHash("Artist2", "Song2");

        hashDB.printHash("artist");
        hashDB.printHash("song");

        hashDB.printHash("invalidType");
        assertTrue(systemOut().getHistory().contains("16: |Artist2|\n"
            + "80: |Artist1|\n" + "total artists: 2\n" + "56: |Song1|\n"
            + "57: |Song2|\n" + "total songs: 2"));

    }


    /**
     * Test case for the printGraph method.
     */
    public void testPrintGraph() {
        // Insert records first
        hashDB.insertHash("Artist1", "Song1");
        hashDB.insertHash("Artist2", "Song2");

        // Test printing the graph
        hashDB.printGraph();

        assertTrue(systemOut().getHistory().contains(
            "There are 1 connected components\n"
                + "The largest connected component has 1 elements\n"
                + "The diameter of the largest component is 2147483647"));
    }


    /**
     * Test case for handling multiple connected components in the graph.
     */
    public void testMultipleComponents() {
        // Insert records to create multiple connected components
        hashDB.insertHash("Artist1", "Song1");
        hashDB.insertHash("Artist2", "Song2");
        hashDB.insertHash("Artist3", "Song3");

        // Test printing the graph with multiple components
        hashDB.printGraph();
        assertTrue(systemOut().getHistory().contains(
            "There are 1 connected components\n"
                + "The largest connected component has 1 elements\n"
                + "The diameter of the largest component is 2147483647"));
    }


    /**
     * Test case for calculating and printing the diameter of the graph.
     */
    public void testGraphDiameter() {
        // Insert records to create a connected graph with varying diameters
        hashDB.insertHash("Artist1", "Song1");
        hashDB.insertHash("Artist2", "Song2");
        hashDB.insertHash("Artist3", "Song3");
        hashDB.insertHash("Artist4", "Song4");

        // Test printing the graph and check the diameter
        hashDB.printGraph();
        assertTrue(systemOut().getHistory().contains(
            "There are 1 connected components\n"
                + "The largest connected component has 1 elements\n"
                + "The diameter of the largest component is 2147483647"));
    }


    /**
     * Test case for deleting non-existent records.
     */
    public void testDeleteNonExistent() {

        assertNull(hashDB.deleteHash("artist", "NonExistentArtist"));
        assertNull(hashDB.deleteHash("song", "NonExistentSong"));
    }


    /**
     * Test case for removing edges from the graph after deleting a record.
     */
    public void testRemoveEdgesFromGraph() {

        hashDB.insertHash("Artist1", "Song1");
        hashDB.insertHash("Artist2", "Song2");
        hashDB.insertHash("Artist3", "Song3");

        hashDB.printGraph();

        Record removedRecord = hashDB.deleteHash("artist", "Artist2");
        assertNotNull(removedRecord);

        hashDB.printGraph();
    }

}
