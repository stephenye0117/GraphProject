import java.util.Arrays;

/**
 * Project 4
 */

/**
 * The HashDB class manages a database with hash tables for artists and songs,
 * as well as a graph representing relationships between artists and songs.
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
public class HashDB {

    private Hash artistHash;
    private Hash songHash;
    private Graph graph;

    /**
     * Initializes a new instance of the HashDB with a given world size.
     *
     * @param worldSize
     *            The size constraint for hash tables.
     */
    public HashDB(int worldSize) {
        artistHash = new Hash(worldSize, "Artist");
        songHash = new Hash(worldSize, "Song");
        graph = new Graph(worldSize); // Assuming world size is
                                      // suitable for the graph.
    }


    /**
     * Inserts an artist and a song into the hash tables and establishes a
     * relationship in the graph.
     *
     * @param artist
     *            The artist to insert.
     * @param song
     *            The song to insert.
     * 
     * @return True if the insertion is successful, false if the record already
     *         exists or the hash table is full.
     */
    public boolean insertHash(String artist, String song) {
        boolean artistExists = artistHash.recordExists(artist);
        boolean songExists = songHash.recordExists(song);

        if (!artistExists) {
            Node artistNode = new Node(artist.hashCode());
            Record artistRecord = new Record(artist, artistNode);
            artistHash.insert(artist, artistRecord);
            graph.init(graph.nodeCount() + 1);
            System.out.println("|" + artist
                + "| is added to the Artist database.");
        }

        if (!songExists) {
            Node songNode = new Node(song.hashCode());
            Record songRecord = new Record(song, songNode);
            if (songHash.insert(song, songRecord)) {
                System.out.println("|" + song
                    + "| is added to the Song database.");
            }
        }

        int artistIndex = artistHash.getIndex(artist);
        int songIndex = songHash.getIndex(song);
        
        if (!graph.addEdge(artistIndex, songIndex, 1)) {
            System.out.println("|" + artist + "<SEP>" + song
                + "| duplicates a record already in the database.");
            return false;
        }

        return true;
    }


    /**
     * Removes an artist or a song from the hash tables and removes related
     * edges from the graph.
     *
     * @param type
     *            The type of the key ("artist" or "song").
     * @param key
     *            The key to remove (artist or song).
     * @return The removed record if found, otherwise null.
     */
    public Record deleteHash(String type, String key) {
        Record removedRecord = null;

        if (type.equals("artist")) {
            removedRecord = artistHash.removeHash(key);
            if (removedRecord == null) {
                System.out.println("|" + key
                    + "| does not exist in the Artist database.");
            }
            else {
                System.out.println("|" + key
                    + "| is removed from the Artist database.");

                // Remove related edges from the graph
                int artistIndex = artistHash.getIndex(key);
                int[] songNeighbors = graph.neighbors(artistIndex);
                for (int songIndex : songNeighbors) {
                    graph.removeEdge(artistIndex, songIndex);
                }
            }
        }
        else if (type.equals("song")) {
            removedRecord = songHash.removeHash(key);
            if (removedRecord == null) {
                System.out.println("|" + key
                    + "| does not exist in the Song database.");
            }
            else {
                System.out.println("|" + key
                    + "| is removed from the Song database.");

                // Remove related edges from the graph
                int songIndex = graph.getIndex(key);
                int[] artistNeighbors = graph.neighbors(songIndex);
                for (int artistIndex : artistNeighbors) {
                    graph.removeEdge(artistIndex, songIndex);
                }
            }
        }

        return removedRecord;
    }


    /**
     * Prints the hash tables for artists and songs.
     * 
     * @param type
     *            either song or artist
     */
    public void printHash(String type) {
        if (type.equals("song")) {
            songHash.printHash(type);
        }
        else if (type.equals("artist")) {
            artistHash.printHash(type);
        }
        else {
            System.out.println("Invalid print type. Use 'song' or 'artist'.");
        }
    }


    /**
     * Prints the artist-song graph.
     */
    /**
     * Prints the artist-song graph.
     */
    public void printGraph() {
        int numComponents = 0;
        int largestComponentSize = 0;
        int diameterOfLargestComponent = 0;

        for (int i = 0; i < graph.nodeCount(); i++) {
            int[] neighbors = graph.neighbors(i);

            if (neighbors.length > 0) {
                // Connected component found
                numComponents++;

                // Update largest component information
                if (neighbors.length > largestComponentSize) {
                    largestComponentSize = neighbors.length;

                    // Convert neighbors to an adjacency matrix for Floyd's
                    // Algorithm
                    int[][] adjacencyMatrix =
                        new int[largestComponentSize][largestComponentSize];
                    for (int j = 0; j < largestComponentSize; j++) {
                        Arrays.fill(adjacencyMatrix[j], Integer.MAX_VALUE);
                    }

                    for (int j = 0; j < largestComponentSize; j++) {
                        int songIndex = neighbors[j];
                        int[] songNeighbors = graph.neighbors(songIndex);
                        for (int k = 0; k < songNeighbors.length; k++) {
                            int artistIndex = songNeighbors[k];
                            adjacencyMatrix[j][k] = graph.weight(artistIndex,
                                songIndex);
                        }
                    }

                    diameterOfLargestComponent = largestDiameter(
                        adjacencyMatrix);
                }
            }
        }

        System.out.println("There are " + numComponents
            + " connected components");

        System.out.println("The largest connected component has "
            + largestComponentSize + " elements");
        System.out.println("The diameter of the largest component is "
            + diameterOfLargestComponent);

    }

// public void printGraph() {
// int numComponents = 0;
// int largestComponentSize = 0;
// int diameterOfLargestComponent = 0;
//
// for (int i = 0; i < graph.nodeCount(); i++) {
// int[] neighbors = graph.neighbors(i);
// floydsAlgorithm(neighbors);
// }
//
//
//
// System.out.println("There are " + numComponents + " connected components");
// }
//


// private void floydsAlgorithm(int[] array) {
// int maxElements = 0;
// int diameter = 0;
//
// int[][] floydsArray = null;
// for (int i = 0; i < array.length; i++) {
// if (array[i] == -1) {
// int tempMax = 0;
// for (int j = 0; j < array.length; j++) {
// if ((array[j] >= -1) && (find(array, j) == i)) {
// tempMax++;
// }
// }
// if (tempMax > maxElements) {
// maxElements = tempMax;
// floydsArray = initFloydsArray(array, i, maxElements);
// if (floydsArray != null) {
// diameter = largestDiameter(floydsArray);
// }
//
// }
// else if (tempMax == maxElements) {
// floydsArray = initFloydsArray(array, i, maxElements);
//
// if (floydsArray != null) {
// int tempDiameter = largestDiameter(floydsArray);
// if (tempDiameter > diameter) {
// diameter = tempDiameter;
// }
// }
// }
// }
//
// }
// System.out.println("The largest connected component has " + maxElements
// + " elements");
// System.out.println("The diameter of the largest component is "
// + diameter);
// }
//
//
// /**
// * Finds the representative element in the array using the Union-Find
// * algorithm.
// *
// * @param array
// * The array representing disjoint sets.
// * @param j
// * The element to find.
// * @return The representative element.
// */
// private int find(int[] array, int j) {
// if (array[j] == -1) {
// return j;
// }
// return find(array, array[j]);
// }
//
//
// /**
// * Initializes the adjacency matrix for Floyd's Algorithm.
// *
// * @param array
// * The array representing disjoint sets.
// * @param i
// * The index of the representative element.
// * @param maxElements
// * The number of elements in the connected component.
// * @return The initialized adjacency matrix.
// */
// private int[][] initFloydsArray(int[] array, int startIndex, int capacity) {
// int[] vertexArray = new int[capacity];
// int empty = 0;
//
// for (int i = 0; i < array.length; i++) {
//
// if ((array[i] >= -1) && (find(array, i) == startIndex)) {
// vertexArray[empty] = i;
// empty++;
// }
// }
// int[][] floyd = new int[capacity][capacity];
//
// for (int i = 0; i < capacity; i++) {
//
// for (int j = 0; j < capacity; j++) {
//
// int firstNode = vertexArray[i];
// int secondNode = vertexArray[j];
//
// if (graph.weight(firstNode, secondNode) != 0) {
// floyd[i][i] = graph.weight(firstNode, secondNode);
// }
// else {
// floyd[i][j] = Integer.MAX_VALUE;
// }
// }
// }
// for (int k = 0; k < capacity; k++) {
// for (int i = 0; i < capacity; i++) {
// for (int j = 0; j < capacity; j++) {
//
// if ((floyd[i][k] != Integer.MAX_VALUE)
// && (floyd[k][j] != Integer.MAX_VALUE)
// && (floyd[i][j] > floyd[i][k] + floyd[k][j])) {
// if (i == j) {
// floyd[i][j] = floyd[i][k];
// }
// else {
// floyd[i][j] = floyd[i][k] + floyd[k][j];
// }
// }
// }
// }
// }
// return floyd;
// }
//
    /**
     * Finds the largest diameter in the adjacency matrix using Floyd's
     * Algorithm.
     *
     * @param floydsArray
     *            The adjacency matrix for the connected component.
     * @return The largest diameter.
     */
    private int largestDiameter(int[][] floydsArray) {
        int diameter = 0;

        for (int i = 0; i < floydsArray.length; i++) {
            for (int j = 0; j < floydsArray[0].length; j++) {
                if (floydsArray[i][j] > diameter) {
                    diameter = floydsArray[i][j];
                }
            }
        }

        return diameter;
    }
}
