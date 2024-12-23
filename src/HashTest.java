import student.TestCase;

/**
 * Project 4
 */

/**
 * The HashTest class contains test cases for the Hash class,
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
public class HashTest extends TestCase {

    private Hash hashTable;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        hashTable = new Hash(10, "simpleHash");
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertEquals(97, Hash.h("a", 10000));
        assertEquals(98, Hash.h("b", 10000));
        assertEquals(1873, Hash.h("aaaa", 10000));
        assertEquals(9089, Hash.h("aaab", 10000));
        assertEquals(1874, Hash.h("baaa", 10000));
        assertEquals(3794, Hash.h("aaaaaaa", 10000));
        assertEquals(4635, Hash.h("Long Lonesome Blues", 10000));
        assertEquals(4159, Hash.h("Long   Lonesome Blues", 10000));
        assertEquals(4667, Hash.h("long Lonesome Blues", 10000));
    }


    /**
     * Test case for the insert method.
     */
    public void testInsert() {
        hashTable = new Hash(10, "Test");

        Record record1 = new Record("Key1", null);
        Record record2 = new Record("Key2", null);
        assertTrue(hashTable.insert("Key1", record1));
        assertTrue(hashTable.insert("Key2", record2));

        assertFalse(hashTable.insert("Key1", new Record("DuplicateKey", null)));

        assertEquals(10, hashTable.getSize());
        assertEquals(2, hashTable.getCurrSize());
    }


    /**
     * Test case for the removeHash method.
     */
    public void testRemoveHash() {
        hashTable = new Hash(10, "Test");

        // Insert records
        Record record1 = new Record("Key1", null);
        Record record2 = new Record("Key2", null);
        hashTable.insert("Key1", record1);
        hashTable.insert("Key2", record2);

        // Remove a record
        Record removedRecord = hashTable.removeHash("Key1");
        assertNotNull(removedRecord);
        assertEquals("Key1", removedRecord.getKey());

        // Try to remove a non-existent record
        assertNull(hashTable.removeHash("NonExistentKey"));

        // Check the size and current size
        assertEquals(10, hashTable.getSize());
        assertEquals(1, hashTable.getCurrSize());
    }


    /**
     * Test case for the searchHash method.
     */
    public void testSearchHash() {
        hashTable = new Hash(10, "Test");

        // Insert records
        Record record1 = new Record("Key1", null);
        Record record2 = new Record("Key2", null);
        hashTable.insert("Key1", record1);
        hashTable.insert("Key2", record2);

        // Search for existing and non-existent records
        assertNotNull(hashTable.searchHash("Key1"));
        assertNotNull(hashTable.searchHash("Key2"));
        assertNull(hashTable.searchHash("NonExistentKey"));
    }


    /**
     * Test case for the printHash method.
     */
    public void testPrintHash() {
        hashTable = new Hash(10, "Test");

        // Insert records
        Record record1 = new Record("Key1", null);
        Record record2 = new Record("Key2", null);
        hashTable.insert("Key1", record1);
        hashTable.insert("Key2", record2);

        // Print the hash table
        hashTable.printHash("Test");
        assertEquals(systemOut().getHistory(), "1: |Key1|\r\n"
            + "7: |Key2|\r\n"
            + "total Tests: 2\r\n"
            + "");
    }


    /**
     * Test case for the getHashType method.
     */
    public void testGetHashType() {
        hashTable = new Hash(10, "Test");

        assertEquals("Test", hashTable.getHashType());

        Hash anotherHashTable = new Hash(10, "AnotherTest");

        assertEquals("AnotherTest", anotherHashTable.getHashType());
    }


    /**
     * Test case for the getIndex method.
     */
    public void testGetIndex() {
        hashTable = new Hash(10, "Test");

        // Insert records
        Record record1 = new Record("Key1", null);
        Record record2 = new Record("Key2", null);
        hashTable.insert("Key1", record1);
        hashTable.insert("Key2", record2);

        assertEquals(1, hashTable.getIndex("Key1"));
        assertEquals(7, hashTable.getIndex("Key2"));
        assertEquals(-1, hashTable.getIndex("NonExistentKey"));
    }


    /**
     * Test case for the getKey method.
     */
    public void testGetKey() {
        hashTable = new Hash(10, "Test");

        // Insert records
        Record record1 = new Record("Key1", null);
        Record record2 = new Record("Key2", null);
        hashTable.insert("Key1", record1);
        hashTable.insert("Key2", record2);

        assertNull(hashTable.getKey(0));
        assertEquals("Key1", hashTable.getKey(1));
        assertNull(hashTable.getKey(2));
        assertNull(hashTable.getKey(3));
    }


    /**
     * Test case for the resize method.
     */
    public void testResize() {
        hashTable = new Hash(5, "Test");

        for (int i = 1; i <= 8; i++) {
            hashTable.insert("Key" + i, new Record("Key" + i, null));
        }

        assertEquals(20, hashTable.getSize());

        for (int i = 1; i <= 8; i++) {
            assertNotNull(hashTable.searchHash("Key" + i));
        }
        for (int i = 9; i <= 12; i++) {
            hashTable.insert("Key" + i, new Record("Key" + i, null));
        }

        assertEquals(40, hashTable.getSize());

    }

}
