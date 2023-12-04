package graphs;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for TownGraphManager.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class TownGraphManager_StudentTest {
    private TownGraphManagerInterface tGraph;
    private String[] town;

    /**
     * Initializes the TownGraphManager and creates an array of town names for testing.
     *
     * @throws Exception if an error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        tGraph = new TownGraphManager();
        town = new String[10];

        for (int i = 1; i <= 10; i++) {
            town[i - 1] = "City_" + i;
            tGraph.addTown(town[i - 1]);
        }

        tGraph.addRoad(town[0], town[1], 3, "Road_A");
        tGraph.addRoad(town[1], town[2], 5, "Road_B");
        tGraph.addRoad(town[2], town[3], 2, "Road_C");
        tGraph.addRoad(town[3], town[4], 4, "Road_D");
        tGraph.addRoad(town[4], town[5], 6, "Road_E");
        tGraph.addRoad(town[5], town[6], 1, "Road_F");
        tGraph.addRoad(town[6], town[7], 3, "Road_G");
        tGraph.addRoad(town[7], town[8], 2, "Road_H");
        tGraph.addRoad(town[8], town[9], 4, "Road_I");
    }

    /**
     * Cleans up resources after testing.
     *
     * @throws Exception if an error occurs during teardown.
     */
    @After
    public void tearDown() throws Exception {
        tGraph = null;
    }

    /**
     * Tests the addition of a road and verifies if the road list is updated correctly.
     */
    @Test
    public void testAddRoad() {
        ArrayList<String> roads = tGraph.allRoads();
        assertEquals("Road_A", roads.get(0));
        assertEquals("Road_B", roads.get(1));
        assertEquals("Road_C", roads.get(2));

        tGraph.addRoad(town[0], town[2], 4, "Road_X");
        roads = tGraph.allRoads();
        assertEquals("Road_A", roads.get(0));
        assertEquals("Road_B", roads.get(1));
        assertEquals("Road_C", roads.get(2));
        assertEquals("Road_D", roads.get(3));
    }

    /**
     * Tests the retrieval of a road between two towns.
     */
    @Test
    public void testGetRoad() {
        assertEquals("Road_C", tGraph.getRoad(town[2], town[3]));
        assertEquals("Road_F", tGraph.getRoad(town[5], town[6]));
    }

    // Add tests for other methods as needed...

    /**
     * Tests the retrieval of a list of all towns in the graph.
     */
    @Test
    public void testAllTowns() {
        ArrayList<String> towns = tGraph.allTowns();
        assertEquals("City_1", towns.get(0));
        assertEquals("City_4", towns.get(4));
        assertEquals("City_9", towns.get(9));
    }
    
    /**
     * Tests the addition of a town and verifies if the town list is updated correctly.
     */
    @Test
    public void testAddTown() {
        assertEquals(false, tGraph.containsTown("City_11"));
        tGraph.addTown("City_11");
        assertEquals(true, tGraph.containsTown("City_11"));
    }

    /**
     * Tests if a path is not found in a disjoint graph.
     */
    @Test
    public void testDisjointGraph() {
        assertEquals(false, tGraph.containsTown("City_11"));
        tGraph.addTown("City_11");
        ArrayList<String> path = tGraph.getPath(town[0], "City_11");
        assertFalse(path.size() > 0);
    }

    /**
     * Tests if a town is correctly identified as part of the graph.
     */
    @Test
    public void testContainsTown() {
        assertEquals(true, tGraph.containsTown("City_3"));
        assertEquals(false, tGraph.containsTown("City_11"));
    }

    /**
     * Tests if a road connection between two towns is correctly identified.
     */
    @Test
    public void testContainsRoadConnection() {
        assertEquals(true, tGraph.containsRoadConnection(town[5], town[6]));
        assertEquals(false, tGraph.containsRoadConnection(town[2], town[7]));
    }

    /**
     * Tests the retrieval of a list of all roads in the graph.
     */
    @Test
    public void testAllRoads() {
        ArrayList<String> roads = tGraph.allRoads();
        assertEquals("Road_A", roads.get(0));
        assertEquals("Road_B", roads.get(1));
        assertEquals("Road_D", roads.get(3));
        assertEquals("Road_G", roads.get(6));
    }

    /**
     * Tests the deletion of a road connection between two towns.
     */
    @Test
    public void testDeleteRoadConnection() {
        assertEquals(true, tGraph.containsRoadConnection(town[6], town[7]));
        tGraph.deleteRoadConnection(town[6], town[7], "Road_G");
        assertEquals(false, tGraph.containsRoadConnection(town[6], town[7]));
    }

    /**
     * Tests the deletion of a town from the graph.
     */
    @Test
    public void testDeleteTown() {
        assertEquals(true, tGraph.containsTown("City_3"));
        tGraph.deleteTown(town[2]);
        assertEquals(false, tGraph.containsTown("City_3"));
    }

    /**
     * Tests the retrieval of a path between two towns.
     */
    @Test
    public void testGetPath() {
        ArrayList<String> path = tGraph.getPath(town[0], town[9]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("City_1 via Road_A to City_2 3 mi", path.get(0).trim());
        assertEquals("City_2 via Road_B to City_3 5 mi", path.get(1).trim());
        assertEquals("City_3 via Road_C to City_4 2 mi", path.get(2).trim());
        assertEquals("City_4 via Road_D to City_5 4 mi", path.get(3).trim());
        assertEquals("City_5 via Road_E to City_6 6 mi", path.get(4).trim());
        assertEquals("City_6 via Road_F to City_7 1 mi", path.get(5).trim()); 
        assertEquals("City_7 via Road_G to City_8 3 mi", path.get(6).trim());
        assertEquals("City_8 via Road_H to City_9 2 mi", path.get(7).trim());
    }

    /**
     * Tests the population of a town graph from a file.
     *
     * @throws FileNotFoundException if the file is not found.
     */
    @Test
    public void testPopulateTownGraph() throws FileNotFoundException {
        File newFile = new File("testFile.txt");
        
        PrintWriter pw = new PrintWriter(newFile);
        pw.println("Road_A,14;City_1;City_2");
        pw.println("Road_B,13;City_2;City_3");
        pw.println("Road_C,8;City_3;City_4");
        
        pw.close();
        
        try {
            ((TownGraphManager) tGraph).populateTownGraph(newFile);
            assertTrue("No exception thrown", true);
        } catch (Exception e) {
            assertTrue("Exception thrown", false);
        }
        
        assertTrue(tGraph.containsTown("City_3"));
        assertTrue(tGraph.containsTown("City_4"));
        
        newFile.delete();
    }
}

