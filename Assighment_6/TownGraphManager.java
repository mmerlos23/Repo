package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

/**
 * Implementation of the TownGraphManagerInterface using a Graph data structure.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class TownGraphManager implements TownGraphManagerInterface {
    Graph tGraph;

    /**
     * Constructor to initialize the TownGraphManager.
     */
    public TownGraphManager() {
        tGraph = new Graph();
    }

    /**
     * Adds a road between two towns with the specified weight and road name.
     *
     * @param town1    the name of the first town
     * @param town2    the name of the second town
     * @param weight   the weight (distance) of the road
     * @param roadName the name of the road
     * @return true if the road is added successfully, false otherwise
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        Town sourceTown = findTownByName(town1);
        Town destinationTown = findTownByName(town2);

        if (sourceTown == null || destinationTown == null || tGraph.containsEdge(sourceTown, destinationTown)) {
            return false;
        }

        Road road = tGraph.addEdge(sourceTown, destinationTown, weight, roadName);

        return road != null;
    }

    /**
     * Helper method to find a town by name in the graph.
     *
     * @param townName the name of the town to find
     * @return the Town object if found, null otherwise
     */
    private Town findTownByName(String townName) {
        Set<Town> towns = tGraph.vertexSet();

        for (Town town : towns) {
            if (town.getName().equalsIgnoreCase(townName)) {
                return town;
            }
        }

        return null;
    }

    /**
     * Retrieves the name of the road connecting two towns.
     *
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @return the name of the road connecting the towns, or null if not found
     */
    @Override
    public String getRoad(String town1, String town2) {
        Town t1 = getTown(town1);
        Town t2 = getTown(town2);

        for (Road road : tGraph.edgeSet()) {
            if (road.contains(t1) && road.contains(t2)) {
                return road.getName();
            }
        }

        return null;
    }

    /**
     * Adds a town to the graph.
     *
     * @param v the name of the town to add
     * @return true if the town is added successfully, false otherwise
     */
    @Override
    public boolean addTown(String v) {
        return tGraph.addVertex(new Town(v));
    }

    /**
     * Retrieves the Town object with the specified name.
     *
     * @param name the name of the town to retrieve
     * @return the Town object if found, null otherwise
     */
    @Override
    public Town getTown(String name) {
        for (Town town : tGraph.vertexSet()) {
            if (town.getName().equals(name)) {
                return town;
            }
        }
        return null;
    }

    /**
     * Checks if the graph contains a town with the specified name.
     *
     * @param v the name of the town to check
     * @return true if the town is in the graph, false otherwise
     */
    @Override
    public boolean containsTown(String v) {
        return tGraph.containsVertex(new Town(v));
    }

    /**
     * Checks if there is a road connection between two towns.
     *
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @return true if there is a road connection, false otherwise
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        Town sourceTown = new Town(town1);
        Town destinationTown = new Town(town2);
        return tGraph.containsEdge(sourceTown, destinationTown);
    }

    /**
     * Retrieves a list of all road names in the graph, sorted alphabetically.
     *
     * @return a sorted list of road names
     */
    @Override
    public ArrayList<String> allRoads() {
        Set<Road> roads = tGraph.edgeSet();
        ArrayList<String> otherRoads = new ArrayList<String>();
        for (Road road : roads) {
            otherRoads.add(road.getName());
        }
        Collections.sort(otherRoads);
        return otherRoads;
    }

    /**
     * Deletes a road connection between two towns.
     *
     * @param town1 the name of the first town
     * @param town2 the name of the second town
     * @param road  the name of the road to delete
     * @return true if the road connection is deleted successfully, false otherwise
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        Town sourceTown = getTown(town1);
        Town destinationTown = getTown(town2);

        if (sourceTown == null || destinationTown == null) {
            return false;
        }

        Road roads = tGraph.getEdge(sourceTown, destinationTown);

        if (roads == null) {
            return false;
        }

        if (roads.getName().equals(road)) {
            tGraph.removeEdge(sourceTown, destinationTown, roads.getWeight(), road);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a town from the graph.
     *
     * @param v the name of the town to delete
     * @return true if the town is deleted successfully, false otherwise
     */
    @Override
    public boolean deleteTown(String v) {
        return tGraph.removeVertex(getTown(v));
    }

    /**
     * Retrieves a list of all town names in the graph, sorted alphabetically.
     *
     * @return a sorted list of town names
     */
    @Override
    public ArrayList<String> allTowns() {
        Set<Town> towns = tGraph.vertexSet();
        ArrayList<String> allTowns = new ArrayList<String>();
        for (Town town : towns) {
            allTowns.add(town.getName());
        }
        Collections.sort(allTowns);
        return allTowns;
    }

    /**
     * Retrieves the shortest path between two towns.
     *
     * @param town1 the name of the starting town
     * @param town2 the name of the destination town
     * @return a list of road names representing the shortest path
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return tGraph.shortestPath(getTown(town1), getTown(town2));
    }

    /**
     * Populates the town graph from a file.
     *
     * @param selectedFile the file containing road information
     * @throws FileNotFoundException if the file is not found
     * @throws IOException           if there is an error reading the file
     */
    public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
        try (Scanner in = new Scanner(selectedFile)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] splitLine = line.split("[,;]");

                if (splitLine.length >= 4) {
                    String roadName = splitLine[0].trim();
                    int weight = Integer.parseInt(splitLine[1].trim());
                    String town1Name = splitLine[2].trim();
                    String town2Name = splitLine[3].trim();

                    Town town1 = new Town(town1Name);
                    Town town2 = new Town(town2Name);

                    tGraph.addVertex(town1);
                    tGraph.addVertex(town2);
                    tGraph.addEdge(town1, town2, weight, roadName);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
