package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a graph with towns and roads and implements the GraphInterface.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class Graph implements GraphInterface<Town, Road> {

    // Sets to store towns and roads
    private Set<Town> town;
    private Set<Road> road;

    // Sets and maps for algorithmic calculations
    private Set<Town> known;
    private Set<Town> unknown;
    private Map<Town, Town> past;
    private Map<Town, Integer> size;

    /**
     * Constructs a new Graph with empty sets for towns, roads, known, and unknown sets,
     * and empty maps for past and size.
     */
    public Graph() {
        town = new HashSet<>();
        road = new HashSet<>();

        known = new HashSet<>();
        unknown = new HashSet<>();
        size = new HashMap<>();
        past = new HashMap<>();
    }

    /**
     * Gets the edge (road) between two towns.
     *
     * @param sourceVertex      The source town.
     * @param destinationVertex The destination town.
     * @return The road between the source and destination towns.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        // Find and return the road between two towns
        return road.stream()
                .filter(road -> road.contains(sourceVertex) && road.contains(destinationVertex))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new road to the graph.
     *
     * @param sourceVertex      The source town.
     * @param destinationVertex The destination town.
     * @param weight             The weight (distance) of the road.
     * @param description        The description (name) of the road.
     * @return The newly added road.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        // Add a new road to the graph
        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
        road.add(newRoad);
        return newRoad;
    }

    /**
     * Adds a new town to the graph.
     *
     * @param v The town to be added.
     * @return true if the town is added successfully, false otherwise.
     */
    @Override
    public boolean addVertex(Town v) {
        // Add a new town to the graph
        return town.add(v);
    }

    /**
     * Checks if there is an edge (road) between two towns.
     *
     * @param sourceVertex      The source town.
     * @param destinationVertex The destination town.
     * @return true if there is an edge between the source and destination towns, false otherwise.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        // Check if there is an edge (road) between two towns
        return road.stream().anyMatch(road -> road.contains(sourceVertex) && road.contains(destinationVertex));
    }

    /**
     * Checks if the graph contains a specific town.
     *
     * @param v The town to be checked.
     * @return true if the town is in the graph, false otherwise.
     */
    @Override
    public boolean containsVertex(Town v) {
        // Check if the graph contains a specific town
        return town.contains(v);
    }

    /**
     * Returns a set containing all roads in the graph.
     *
     * @return A set of all roads in the graph.
     */
    @Override
    public Set<Road> edgeSet() {
        // Return a new set containing all roads in the graph
        return new HashSet<>(road);
    }

    /**
     * Returns a set containing all roads connected to a specific town.
     *
     * @param vertex The town to find connected roads for.
     * @return A set of roads connected to the specified town.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {
        // Return a set containing all roads connected to a specific town
        return road.stream().filter(road -> road.contains(vertex)).collect(Collectors.toSet());
    }

    /**
     * Removes a specific road from the graph.
     *
     * @param sourceVertex      The source town.
     * @param destinationVertex The destination town.
     * @param weight             The weight (distance) of the road.
     * @param description        The description (name) of the road.
     * @return The removed road, or null if the road was not found.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
        // Remove a specific road from the graph
        Road removedRoad = road.stream()
                .filter(road -> road.contains(sourceVertex) && road.contains(destinationVertex)
                        && road.getWeight() == weight && road.getName().equals(description))
                .findFirst()
                .orElse(null);

        if (removedRoad != null) {
            road.remove(removedRoad);
        }

        return removedRoad;
    }

    // Helper method to remove all roads connected to a specific town
    private void _removeConnectedRoads(Town v) {
        edgesOf(v).forEach(road::remove);
    }

    /**
     * Removes a specific town and all connected roads from the graph.
     *
     * @param v The town to be removed.
     * @return true if the town is removed successfully, false otherwise.
     */
    @Override
    public boolean removeVertex(Town v) {
        // Remove a specific town and all connected roads from the graph
        town.remove(v);
        _removeConnectedRoads(v);
        return true;
    }

    /**
     * Returns a set containing all towns in the graph.
     *
     * @return A set of all towns in the graph.
     */
    @Override
    public Set<Town> vertexSet() {
        // Return a new set containing all towns in the graph
        return new HashSet<>(town);
    }

    /**
     * Finds the shortest path between two towns using Dijkstra's algorithm.
     *
     * @param sourceVertex      The source town.
     * @param destinationVertex The destination town.
     * @return An ArrayList of strings representing the shortest path.
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        // Find the shortest path between two towns using Dijkstra's algorithm
        dijkstraShortestPath(sourceVertex);

        ArrayList<String> path = new ArrayList<>();
        Town pastTown = destinationVertex;

        while (pastTown != null) {
            Town newTown = pastTown;
            pastTown = past.get(pastTown);
            Road roadPath = getEdge(newTown, pastTown);

            if (pastTown != null) {
                path.add(pastTown.getName() + " via " + roadPath.getName() + " to " + newTown.getName() + " " + roadPath.getWeight() + " mi");
            }
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Applies Dijkstra's algorithm to find the shortest paths from a source town to all other towns.
     *
     * @param sourceVertex The source town.
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        // Implement Dijkstra's algorithm to find the shortest paths from a source town to all other towns
        for (Town town : town) {
            size.put(town, Integer.MAX_VALUE);
            past.put(town, null);
            unknown.add(town);
        }

        size.put(sourceVertex, 0);

        while (!unknown.isEmpty()) {
            Town closestTown = null;
            int lowCost = Integer.MAX_VALUE;

            for (Town town : unknown) {
                if (size.get(town) <= lowCost) {
                    lowCost = size.get(town);
                    closestTown = town;
                }
            }

            unknown.remove(closestTown);

            Set<Town> neighborsNextDoor = new HashSet<>();
            for (Road road : edgesOf(closestTown)) {
                Town neighbors = road.getSource() == closestTown ? road.getDestination() : road.getSource();
                if (unknown.contains(neighbors) && !known.contains(neighbors)) {
                    neighborsNextDoor.add(neighbors);
                }
            }

            for (Town neighbors : neighborsNextDoor) {
                int weight = size.get(closestTown) + getEdge(closestTown, neighbors).getWeight();

                if (weight < size.get(neighbors)) {
                    size.put(neighbors, weight);
                    past.put(neighbors, closestTown);
                }
            }
        }
    }

    // Helper method to get unvisited neighbors of a specific town
    private Set<Town> _unvisitedNeighborsOfTown(Town town) {
        return edgesOf(town).stream()
                .map(road -> (road.getSource() == town) ? road.getDestination() : road.getSource())
                .filter(neighbor -> unknown.contains(neighbor) && !known.contains(neighbor))
                .collect(Collectors.toSet());
    }

    // Helper method to get the nearest unvisited town
    private Town _getNearestUnvisitedTown() {
        return unknown.stream()
                .min(Comparator.comparingInt(size::get))
                .orElse(null);
    }
}