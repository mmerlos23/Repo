package graphs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains JUnit tests for the Road class.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class Road_StudentTest {
    // Towns and roads for testing
    Town town1;
    Town town2;
    Town town3;

    Road road1;
    Road road2;
    Road road3;

    /**
     * Set up towns and roads for testing.
     *
     * @throws Exception If an exception occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        town1 = new Town("Gaithersburg");
        town2 = new Town("Germantown");
        town3 = new Town("Rockville");

        road1 = new Road(town1, town2, 4, "Lost Knife Road");
        road2 = new Road(town1, town3, 6, "Shakespeare Road");
        road3 = new Road(town2, town3, 5, "King Farm Road");
    }

    /**
     * Clean up towns and roads after testing.
     *
     * @throws Exception If an exception occurs during teardown.
     */
    @After
    public void tearDown() throws Exception {
        town1 = town2 = town3 = null;
        road1 = road2 = road3 = null;
    }

    /**
     * Test for the contains method in the Road class.
     */
    @Test
    public void testContains() {
        assertTrue(road1.contains(town1));
        assertTrue(road1.contains(town2));
        assertTrue(road3.contains(town2));
    }

    /**
     * Test for the equals method in the Road class.
     */
    @Test
    public void testEquals() {
        Road road4 = new Road(town1, town2, 4, "Lost Knife Road");
        assertTrue(road1.equals(road4));
    }

    /**
     * Test for the getName method in the Road class.
     */
    @Test
    public void testGetName() {
        assertTrue(road1.getName().equals("Lost Knife Road"));
        assertTrue(road2.getName().equals("Shakespeare Road"));
    }

    /**
     * Test for the getWeight method in the Road class.
     */
    @Test
    public void testGetWeight() {
        assertEquals(4, road1.getWeight());
        assertEquals(6, road2.getWeight());
    }

    /**
     * Test for the getDestination method in the Road class.
     */
    @Test
    public void testGetDestination() {
        assertEquals("Germantown", road1.getDestination().getName());
        assertEquals("Rockville", road3.getDestination().getName());
    }

    /**
     * Test for the getSource method in the Road class.
     */
    @Test
    public void testGetSource() {
        assertTrue(road1.getSource().getName().equals("Gaithersburg"));
        assertTrue(road2.getSource().getName().equals("Gaithersburg"));
    }

    /**
     * Test for the compareTo method in the Road class.
     */
    @Test
    public void testCompareTo() {
        assertTrue(road1.compareTo(road2) < 0);
        assertTrue(road2.compareTo(road3) > 0);
        assertTrue(road1.compareTo(road1) == 0);
    }

    /**
     * Test for the toString method in the Road class.
     */
    @Test
    public void testToString() {
        Road road = new Road(town1, town2, 4, "Lost Knife Road");
        String expected = "Lost Knife Road connects Gaithersburg and Germantown and is 4 miles long";
        String actual = road.toString();
        assertEquals(expected, actual);
    }
}