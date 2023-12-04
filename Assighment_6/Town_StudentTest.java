package graphs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Town class.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class Town_StudentTest {
    Town town1;
    Town town2;
    Town town3;

    /**
     * Sets up the towns for testing.
     */
    @Before
    public void setUp() throws Exception {
        town1 = new Town("Gaithersburg");
        town2 = new Town("Germantown");
        town3 = new Town("Rockville");
    }

    /**
     * Tears down the towns after testing.
     */
    @After
    public void tearDown() {
        town1 = null;
        town2 = null;
        town3 = null;
    }

    /**
     * Tests the copy constructor of the Town class.
     */
    @Test
    public void testCopyConstructor() {
        Town originalTown = new Town("Original Town");
        Town copiedTown = new Town(originalTown);
        assertEquals("Original Town", copiedTown.getName());
    }

    /**
     * Tests the equals method of the Town class.
     */
    @Test
    public void testEquals() {
        town1 = new Town("Gaithersburg");
        Town town1Copy = new Town("Gaithersburg");

        town2 = new Town("Germantown");

        assertTrue(town1.equals(town1Copy));
        assertTrue(town1Copy.equals(town1));
        assertFalse(town1.equals(town2));
        assertFalse(town2.equals(town1));
    }

    /**
     * Tests the getName method of the Town class.
     */
    @Test
    public void testGetName() {
        assertTrue(town1.getName().equals("Gaithersburg"));
        assertTrue(town2.getName().equals("Germantown"));
    }

    /**
     * Tests the compareTo method of the Town class.
     */
    @Test
    public void testCompareTo() {
        assertTrue(town1.compareTo(town2) < 0);
        assertTrue(town2.compareTo(town1) > 0);
        assertTrue(town1.compareTo(town1) == 0);
        assertTrue(town2.compareTo(town3) < 0);
        assertTrue(town3.compareTo(town2) > 0);
    }

    /**
     * Tests the toString method of the Town class.
     */
    @Test
    public void testToString() {
        Town town = new Town("Test Town");
        assertEquals("Test Town", town.toString());
    }

    /**
     * Tests the hashCode method of the Town class.
     */
    @Test
    public void testHashCode() {
        assertFalse(town1.hashCode() == town2.hashCode());
    }
}