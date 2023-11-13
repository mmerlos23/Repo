package morseCode;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The MorseCodeTreeStudentTest class contains JUnit tests for the MorseCodeTree class.
 * It covers various methods such as getRoot, fetch, insert, and toArrayList.
 * 
 * IMPORTANT: This test assumes that the MorseCodeTree class has been implemented correctly.
 * 
 * @author Moises Merlos
 * @version 11/12/2023
 */
public class MorseCodeTreeStudentTest {

    MorseCodeTree tree;
    TreeNode<String> newNode = new TreeNode<>("RANDOM");

    /**
     * Set up the MorseCodeTree instance before each test.
     *
     * @throws Exception if an exception occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        // Initialize a new MorseCodeTree before each test.
        tree = new MorseCodeTree();
    }

    /**
     * Clean up resources after each test.
     *
     * @throws Exception if an exception occurs during teardown
     */
    @After
    public void tearDown() throws Exception {
        // Set the MorseCodeTree instance to null after each test.
        tree = null;
    }

    /**
     * Test the getRoot method.
     * 
     * @throws AssertionError if the root does not match the expected value
     */
    @Test
    public void getRootTest() {
        // Ensure the root of the MorseCodeTree is initially an empty string.
        assertTrue(tree.getRoot().getData().equals(""));

        // Replace the root with a new node and verify the change.
        newNode.left = tree.getRoot().left;
        newNode.right = tree.getRoot().right;
        tree.setRoot(newNode);
        assertEquals(newNode, tree.getRoot());
    }

    /**
     * Test the fetch method.
     * 
     * @throws AssertionError if the fetched value does not match the expected value
     */
    @Test
    public void fetchTest() {
        // Test various Morse code fetches and their corresponding English letters.
        assertTrue(tree.fetch(".-..").equals("l"));
        assertTrue(tree.fetch("---").equals("o"));
        assertTrue(tree.fetch("-.-.").equals("c"));
        assertTrue(tree.fetch("--.").equals("g"));
        assertTrue(tree.fetch("-..").equals("d"));
        assertTrue(tree.fetch(".---").equals("j"));
        assertTrue(tree.fetch("..-.").equals("f"));
    }

    /**
     * Test the insert method.
     * 
     * @throws AssertionError if the inserted values do not match the expected values
     */
    @Test
    public void insertTest() {
        // Insert Morse code representations and verify the tree structure.
        tree.insert(".", "M");
        tree.insert(".-", "S");
        assertEquals("M", tree.getRoot().left.getData());
        assertEquals("S", tree.getRoot().left.right.getData());
    }

    /**
     * Test the toArrayList method.
     * 
     * @throws AssertionError if the ArrayList does not match the expected values
     */
    @Test
    public void toArrayListTest() {
        // Test the initial tree and after inserting new Morse code representations.
        assertTrue(tree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]"));
        tree.insert("----", "GG");
        assertTrue(tree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o, GG]"));
        tree.insert("---.", "KD");
        assertTrue(tree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, KD, o, GG]"));
    }
}