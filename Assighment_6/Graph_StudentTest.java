package graphs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains JUnit tests for the Graph class, which represents a
 * graph with towns and roads.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class Graph_StudentTest {
    private GraphInterface<Town, Road> graph;
    private Town[] town;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        town = new Town[5];

        // Initialize towns and roads for testing
        for (int i = 1; i < 5; i++) {
            town[i] = new Town("City_" + i);
            graph.addVertex(town[i]);
        }

        graph.addEdge(town[1], town[2], 3, "Road_A");
        graph.addEdge(town[1], town[3], 5, "Road_B");
        graph.addEdge(town[2], town[4], 2, "Road_C");
        graph.addEdge(town[3], town[4], 4, "Road_D");
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    /**
     * Tests the getEdge method.
     * @param  town[2]    the source town
     * @param  town[4]    the target town
     * @return            void
     */
    @Test
    public void testGetEdge() {
        assertEquals(new Road(town[2], town[4], 2, "Road_C"), graph.getEdge(town[2], town[4]));
        assertEquals(new Road(town[1], town[3], 5, "Road_B"), graph.getEdge(town[1], town[3]));
    }

    /**
     * Tests the addEdge method.
     * @param  town[2]    the source town
     * @param  town[3]    the target town
     * @param  1          the weight of the road
     * @param  Road_E     the name of the road
     * @return            void
     */
    @Test
    public void testAddEdge() {
        assertEquals(false, graph.containsEdge(town[2], town[3]));
        graph.addEdge(town[2], town[3], 1, "Road_E");
        assertEquals(true, graph.containsEdge(town[2], town[3]));
    }

    /**
     * Tests the addVertex method.
     * @param  City_5     the new town to be added
     * @return            void
     */
    @Test
    public void testAddVertex() {
        Town newTown = new Town("City_5");
        assertEquals(false, graph.containsVertex(newTown));
        graph.addVertex(newTown);
        assertEquals(true, graph.containsVertex(newTown));
    }

    /**
     * Tests the containsEdge method.
     * @param  town[1]    the source town
     * @param  town[3]    the target town
     * @return            void
     */
    @Test
    public void testContainsEdge() {
        assertEquals(true, graph.containsEdge(town[1], town[3]));
        assertEquals(true, graph.containsEdge(town[2], town[4]));
    }

    /**
     * Tests the containsVertex method.
     * @param  City_2     the town to check for existence
     * @param  City_6     the non-existent town
     * @return            void
     */
    @Test
    public void testContainsVertex() {
        assertEquals(true, graph.containsVertex(new Town("City_2")));
        assertEquals(false, graph.containsVertex(new Town("City_6")));
    }

    /**
     * Tests the edgeSet method.
     * @param  Road_A     the first road
     * @param  Road_B     the second road
     * @param  Road_C     the third road
     * @param  Road_D     the fourth road
     * @return            void
     */
    @Test
    public void testEdgeSet() {
        Set<Road> roads = graph.edgeSet();
        ArrayList<String> roadArrayList = new ArrayList<>();
        for (Road road : roads)
            roadArrayList.add(road.getName());
        Collections.sort(roadArrayList);
        assertEquals("Road_A", roadArrayList.get(0));
        assertEquals("Road_B", roadArrayList.get(1));
        assertEquals("Road_C", roadArrayList.get(2));
        assertEquals("Road_D", roadArrayList.get(3));
    }

    /**
     * Tests the edgesOf method.
     * @param  town[1]    the source town
     * @param  Road_A     the first road
     * @param  Road_B     the second road
     * @return            void
     */
    @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(town[1]);
        ArrayList<String> roadArrayList = new ArrayList<>();
        for (Road road : roads)
            roadArrayList.add(road.getName());
        Collections.sort(roadArrayList);
        assertEquals("Road_A", roadArrayList.get(0));
        assertEquals("Road_B", roadArrayList.get(1));
    }

    /**
     * Tests the removeEdge method.
     * @param  town[1]    the source town
     * @param  town[2]    the target town
     * @param  3          the weight of the road
     * @param  Road_A     the name of the road
     * @return            void
     */
    @Test
    public void testRemoveEdge() {
        assertEquals(true, graph.containsEdge(town[1], town[2]));
        graph.removeEdge(town[1], town[2], 3, "Road_A");
        assertEquals(false, graph.containsEdge(town[1], town[2]));
    }

    /**
     * Tests the removeVertex method.
     * @param  town[3]    the town to be removed
     * @return            void
     */
    @Test
    public void testRemoveVertex() {
        assertEquals(true, graph.containsVertex(town[3]));
        graph.removeVertex(town[3]);
        assertEquals(false, graph.containsVertex(town[3]));
    }

    /**
     * Tests the vertexSet method.
     * @param  town[1]    the first town
     * @param  town[4]    the second town
     * @param  City_6     a non-existent town
     * @return            void
     */
    @Test
    public void testVertexSet() {
        Set<Town> towns = graph.vertexSet();
        assertEquals(true, towns.contains(town[1]));
        assertEquals(true, towns.contains(town[4]));
        assertEquals(false, towns.contains(new Town("City_6")));
    }

    /**
     * Tests the shortestPath method.
     * @param  beginTown    the starting town name
     * @param  endTown      the destination town name
     * @return              void
     */
    @Test
    public void testShortestPath() {
        String beginTown = "City_1", endTown = "City_4";
        Town beginIndex = null, endIndex = null;
        Set<Town> towns = graph.vertexSet();
        Iterator<Town> iterator = towns.iterator();

        // Find the towns for the given names
        while (iterator.hasNext()) {
            Town t = iterator.next();
            if (t.getName().equals(beginTown))
                beginIndex = t;
            if (t.getName().equals(endTown))
                endIndex = t;
        }

        // Check if towns are found and calculate the shortest path
        if (beginIndex != null && endIndex != null) {
            ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
            assertNotNull(path);
            assertTrue(path.size() > 0);
            assertEquals("City_1 via Road_A to City_2 3 mi", path.get(0).trim());
            assertEquals("City_2 via Road_C to City_4 2 mi", path.get(1).trim());
        } else {
            fail("Town names are not valid");
        }
    }
}