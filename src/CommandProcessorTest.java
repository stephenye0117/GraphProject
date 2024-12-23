import student.TestCase;

/**
 * Project 4
 */

/**
 * This class contains test cases for the CommandProcessor class.
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
public class CommandProcessorTest extends TestCase {

    private CommandProcessor tester;

    /**
     * Sets up the test fixture. Before every test case method, this method will
     * run.
     */
    public void setUp() {

        tester = new CommandProcessor(10, "myInput.txt");

    }


    /**
     * Test the insert command's output.
     */
    public void testInsertCommand() {

        assertTrue(systemOut().getHistory().contains(
            "|Blind Lemon Jefferson| is added to the Artist database.\n"
                + "|Long Lonesome Blues| is added to the Song database.\n"
                + "|Ma Rainey| is added to the Artist database."));
    }


    /**
     * Test the delete command's output.
     */
    public void testDeleteCommand() {

        assertTrue(systemOut().getHistory().contains(
            "|Long Lonesome Blues| is removed from the Song database.\n"
                + "|Ma Rainey| is removed from the Artist database."));
    }


    /**
     * Test the print command's output.
     */
    public void testPrintCommand() {

        assertTrue(systemOut().getHistory().contains(
            "2: |See That My Grave Is Kept Clean|\n" + "3: |Cross Road Blues|\n"
                + "5: TOMBSTONE\n" + "6: |Ma Rainey's Black Bottom|\n"
                + "total songs: 3\n" + "0: |Blind Lemon Jefferson|\n"
                + "4: |Robert Johnson|\n" + "7: TOMBSTONE\n"
                + "total artists: 2\n" + "There are 2 connected components\n"
                + "The largest connected component has 1 elements\n"
                + "The diameter of the largest component is 2147483647"));

    }


    /**
     * Test case for handling an invalid command scenario.
     */
    public void testInvalidCommand() {

        assertTrue(systemOut().getHistory().contains(
            "Unrecognized command: wtf"));
    }


    /**
     * Test when the file path given is invalid.
     */
    public void testInvalidFilePath() {
        tester.processCommands("doesntexist");
        assertTrue(systemOut().getHistory().contains(
            "Error processing commands: doesntexist "
                + "(No such file or directory)"));

    }

}
