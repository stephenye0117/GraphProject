import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 *
 * @author {Stephen Ye}
 * @version {11/20/2023}
 */
public class GraphProjectTest extends TestCase {
    // ----------------------------------------------------------
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() { // Nothing needed yet

    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }


    /**
     * Test the main method of GraphProject.
     */
    public void testMain() {
        // Redirect System.out and System.err to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {

            String[] args = { "10", "myInput.txt" };

            GraphProject.main(args);

            String expectedOutput =
                "|Blind Lemon Jefferson| is added to the Artist database.\r\n"
                    + "|Long Lonesome Blues| is added to the Song database.\r\n"
                    + "|Ma Rainey| is added to the Artist database.\r\n"
                    + "|Ma Rainey's Black Bottom| is added to the "
                    + "Song database.\r\n"
                    + "|Robert Johnson| is added to the Artist database.\r\n"
                    + "|Cross Road Blues| is added to the Song database.\r\n"
                    + "|See That My Grave Is Kept Clean| is added to the Song "
                    + "database.\r\n"
                    + "2: |See That My Grave Is Kept Clean|\r\n"
                    + "3: |Cross Road Blues|\r\n"
                    + "5: |Long Lonesome Blues|\r\n"
                    + "6: |Ma Rainey's Black Bottom|\r\n" + "total songs: 4\r\n"
                    + "0: |Blind Lemon Jefferson|\r\n"
                    + "4: |Robert Johnson|\r\n" + "7: |Ma Rainey|\r\n"
                    + "total artists: 3\r\n"
                    + "|Long Lonesome Blues| is removed from the Song "
                    + "database.\r\n"
                    + "|Ma Rainey| is removed from the Artist database.\r\n"
                    + "2: |See That My Grave Is Kept Clean|\r\n"
                    + "3: |Cross Road Blues|\r\n" + "5: TOMBSTONE\r\n"
                    + "6: |Ma Rainey's Black Bottom|\r\n" + "total songs: "
                    + "3\r\n" + "0: |Blind Lemon Jefferson|\r\n"
                    + "4: |Robert Johnson|\r\n" + "7: TOMBSTONE\r\n"
                    + "total artists: 2\r\n"
                    + "There are 2 connected components\r\n"
                    + "The largest connected component has 1 elements\r\n"
                    + "The diameter of the largest component is "
                    + "2147483647\r\n" + "Unrecognized command: wtf";
            assertEquals(expectedOutput, outContent.toString().trim());

            try {
                String[] args2 = { "10", "myInput.txt", "bad" };
                GraphProject.main(args2);

            }
            catch (Exception e) {
                // Handle the exception if needed
                e.printStackTrace();
            }
            assertTrue(outContent.toString().trim().contains("bad input"));
        }
        finally {
            // Restore the original System.out and System.err
            System.setOut(System.out);
            System.setErr(System.err);
        }
    }
}
