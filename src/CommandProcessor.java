import java.io.File;
import java.util.Scanner;

/**
 * Project 4
 */

/**
 * This class processes commands from a specified file and performs operations
 * on a Hash Database
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
public class CommandProcessor {

    private HashDB database;

    /**
     * Constructs a CommandProcessor object with the specified hash size and
     * file path.
     *
     * @param hashSize
     *            The size of the hash tables in the HashDB.
     * @param filePath
     *            The path to the file containing commands.
     */
    public CommandProcessor(int hashSize, String filePath) {
        database = new HashDB(hashSize);
        processCommands(filePath);
    }


    /**
     * Processes commands from the specified file and performs corresponding
     * operations.
     *
     * @param filePath
     *            The path to the file containing commands.
     */
    void processCommands(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String command = scanner.next();

                switch (command) {
                    case "insert":
                        caseInsert(scanner, database);
                        break;

                    case "remove":
                        caseDelete(scanner, database);
                        break;

                    case "print":
                        casePrint(scanner, database);
                        break;

                    default:
                        System.out.println("Unrecognized command: " + command);
                        break;
                }
            }
            scanner.close();

        }
        catch (Exception e) {
            System.out.println("Error processing commands: " + e.getMessage());
        }
    }


    /**
     * Processes an "insert" command by extracting information from the scanner
     * and inserting a new record into the HashDB.
     *
     * @param scanner
     *            The scanner containing the command details.
     * @param database
     *            The HashDB to insert the record into.
     * @throws Exception
     *             If there is an error during insertion.
     */
    private void caseInsert(Scanner scanner, HashDB db) throws Exception {
        String input = scanner.nextLine();
        String[] parts = input.split("<SEP>");

        db.insertHash(parts[0].trim(), parts[1].trim());
    }


    /**
     * Processes a "remove" command by extracting information from the scanner
     * and removing a record from the HashDB.
     *
     * @param scanner
     *            The scanner containing the command details.
     * @param database
     *            The HashDB to remove the record from.
     * @throws Exception
     *             If there is an error during removal.
     */
    private void caseDelete(Scanner scanner, HashDB db) throws Exception {
        String type = scanner.next();
        String name = scanner.nextLine();

        db.deleteHash(type, name.trim());
    }


    /**
     * Processes a "print" command by extracting information from the scanner
     * and printing either the hash tables or the graph in the HashDB.
     *
     * @param scanner
     *            The scanner containing the command details.
     * @param database
     *            The HashDB to print information from.
     * @throws Exception
     *             If there is an error during printing.
     */
    private void casePrint(Scanner scanner, HashDB db) throws Exception {
        String type = scanner.next();
        if (type.equals("graph")) {
            database.printGraph();
        }
        else {
            db.printHash(type);
        }

    }

}
