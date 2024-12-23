/**
 * Project 4
 */

/**
 * Hash Table class
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
public class Hash {

    private Record[] allRecords; // Array to store records
    private int numberOfRecords; // Number of records in the hash table
    private int size;
    private String hashType;

    /**
     * Constructor to initialize the hash table
     * 
     * @param size
     *            The size of the hash table
     * @param hashType
     *            either song or artist hashtable
     * 
     */
    public Hash(int size, String hashType) {
        if (size < 1) {
            throw new IllegalArgumentException("bad capacity");
        }
        this.size = size;
        this.allRecords = fillArray(size);
        numberOfRecords = 0;
        this.hashType = hashType;
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }


    /**
     * Gets the current number of records in the hash table.
     *
     * @return The number of records.
     */
    public int getCurrSize() {
        return numberOfRecords;
    }


    /**
     * Gets the total size of the hash table.
     *
     * @return The total size of the hash table.
     */
    public int getSize() {
        return size;
    }


    /**
     * Fills an array with null records.
     *
     * @param initSize
     *            The initial size of the array.
     * @return An array filled with null records.
     */
    public Record[] fillArray(int initSize) {
        Record[] newArr = new Record[initSize];
        for (int i = 0; i < initSize; i++) {
            newArr[i] = null;
        }
        return newArr;
    }


    /**
     * Gets the type of hash (e.g., "song" or "artist").
     *
     * @return The hash type.
     */
    public String getHashType() {
        return hashType;
    }


    /**
     * Resizes the hash table when the load factor exceeds a threshold.
     */
    private void resize() {
        int biggerSize = size * 2;
        Record[] newRecArr = fillArray(biggerSize);
        for (Record r : allRecords) {
            if (r != null && !r.isTombstone()) {
                String key = r.getKey();
                int h = h(key, biggerSize);
                int pos = h;

                int i = 0;
                while (i < biggerSize) {
                    if (newRecArr[pos] == null || newRecArr[pos]
                        .isTombstone()) {
                        if (newRecArr[pos] != null) {
                            newRecArr[pos].setTombstone(false);
                        }
                        newRecArr[pos] = r;
                        break;
                    }
                    pos = (h + i * i) % biggerSize;
                    i++;
                }
            }
        }
        size = biggerSize;
        allRecords = newRecArr;

        System.out.println(hashType + " hash table size doubled.");
    }


    /**
     * Ensures that the hash table has sufficient capacity.
     *
     * @return True if resizing is required, false otherwise.
     */
    private boolean ensureCapacity() {
        return size / 2 <= numberOfRecords;
    }


    /**
     * Inserts a record into the hash table.
     *
     * @param key
     *            The key associated with the record to insert.
     * @param record
     *            The record to insert.
     * @return True if the insertion is successful, false if the record already
     *         exists or the hash table is full.
     */
    public boolean insert(String key, Record record) {
        if (ensureCapacity()) {
            resize();
        }

        int h = h(key, size);
        int pos = h;

        int i = 0;
        while (i < size) {
            if (allRecords[pos] == null || allRecords[pos].isTombstone()) {
                if (allRecords[pos] != null) {
                    allRecords[pos].setTombstone(false);
                }
                allRecords[pos] = record;
                numberOfRecords++;
                return true;
            }
            else if (allRecords[pos].getKey().equals(key)) {
                return false; // Record already exists
            }
            pos = (h + i * i) % size;
            i++;
        }
        return false;
    }


    /**
     * Checks if a record with the specified key exists in the hash table.
     *
     * @param key
     *            The key to search for.
     * @return True if the record exists, false otherwise.
     */
    public boolean recordExists(String key) {
        return searchHash(key) != null;
    }


    /**
     * Computes the next position for quadratic probing.
     *
     * @param home
     *            The home position where the probing started.
     * @param p
     *            The current probing iteration.
     * @param s
     *            The size of the hash table.
     * @return The next position for quadratic probing.
     */
    private int next(int home, int p, int s) {
        return (home + p) % s;
    }


    /**
     * Remove a record from the hash table
     * 
     * @param key
     *            The key of the record to remove
     * @return Record
     *         the Record that is removed
     */
    public Record removeHash(String key) {
        int h = h(key, size);
        int pos = h;
        for (int i = 1; i <= size; i++) {
            if (allRecords[pos] != null && !allRecords[pos].isTombstone()
                && allRecords[pos].getKey().equalsIgnoreCase(key)) {
                allRecords[pos].setTombstone(true);
                numberOfRecords--;
                return allRecords[pos];
            }
            pos = next(h, i, size);
            if (pos == h) {
                break;
            }
        }
        return null;
    }


    /**
     * Search for a record in the hash table
     * 
     * @param key
     *            The key to search for
     * @return
     *         The record if found, or null otherwise
     */
    public Record searchHash(String key) {
        if (size == 0) {
            return null;
        }

        int h = h(key, size);
        int pos = h;
        for (int i = 1; i <= size; i++) {
            if (allRecords[pos] != null && !allRecords[pos].isTombstone()
                && allRecords[pos].getKey().equals(key)) {
                return allRecords[pos];
            }
            pos = next(h, i, size);

            if (pos == h) {
                break;
            }
        }
        return null;
    }


    /**
     * Prints the records in the hash table of the specified type (e.g., "song"
     * or "artist").
     *
     * @param type
     *            The type of records to print.
     */
    public void printHash(String type) {
        int totalRecords = 0;
        for (int i = 0; i < size; i++) {
            if (allRecords[i] != null) {
                if (!allRecords[i].isTombstone()) {
                    if (type.equals("song")) {
                        System.out.println(i + ": |" + allRecords[i].getKey()
                            + "|");
                    }
                    else {
                        System.out.println(i + ": |" + allRecords[i].getKey()
                            + "|");
                    }
                    totalRecords++;
                }
                else {
                    System.out.println(i + ": TOMBSTONE");
                }
            }
        }
        System.out.println("total " + type + "s: " + totalRecords);
    }


    /**
     * Get the index of a record with the specified key in the hash table.
     *
     * @param key
     *            The key to search for.
     * @return The index if found, or -1 otherwise.
     */
    public int getIndex(String key) {
        int h = h(key, size);
        int pos = h;
        for (int i = 1; i <= size; i++) {
            if (allRecords[pos] != null && !allRecords[pos].isTombstone()
                && allRecords[pos].getKey().equals(key)) {
                return pos;
            }
            pos = next(h, i, size);

            if (pos == h) {
                break;
            }
        }
        return -1; // Key not found
    }


    /**
     * Get the key at the specified index in the hash table.
     *
     * @param index
     *            The index to retrieve the key.
     * @return The key if the index is valid, or null otherwise.
     */
    public String getKey(int index) {
        if (index < 0 || index >= size || allRecords[index] == null
            || allRecords[index].isTombstone()) {
            return null; // Invalid index or tombstone
        }
        return allRecords[index].getKey();
    }
}
