package graphs;

/**
 * Represents a road connecting two towns with a specific weight.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class Road implements Comparable<Road> {

    private Town source;       // The source town of the road
    private Town destination;  // The destination town of the road
    private int weight;        // The weight or distance of the road
    private String name;       // The name of the road

    /**
     * Constructs a road with specified source, destination, weight, and name.
     *
     * @param source      The source town.
     * @param destination The destination town.
     * @param weight      The weight or distance of the road.
     * @param name        The name of the road.
     */
    public Road(Town source, Town destination, int weight, String name) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

    /**
     * Constructs a road with specified source, destination, and name. Default weight is 1.
     *
     * @param source      The source town.
     * @param destination The destination town.
     * @param name        The name of the road.
     */
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name);
    }

    /**
     * Gets the source town of the road.
     *
     * @return The source town.
     */
    public Town getSource() {
        return source;
    }

    /**
     * Sets the source town of the road.
     *
     * @param source The new source town.
     */
    public void setSource(Town source) {
        this.source = source;
    }

    /**
     * Gets the destination town of the road.
     *
     * @return The destination town.
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Sets the destination town of the road.
     *
     * @param destination The new destination town.
     */
    public void setDestination(Town destination) {
        this.destination = destination;
    }

    /**
     * Gets the weight or distance of the road.
     *
     * @return The weight of the road.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the name of the road.
     *
     * @return The name of the road.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the road.
     *
     * @param name The new name of the road.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the distance of the road. (Possibly for deletion)
     *
     * @param distance The new distance of the road.
     */
    public void setDistance(int distance) {
        this.weight = distance;
    }

    /**
     * Checks if the road contains a specified town.
     *
     * @param town The town to check.
     * @return True if the road contains the town, false otherwise.
     */
    public boolean contains(Town town) {
        if (town == null)
            return false;

        if (source.equals(town)) {
            return true;
        } else return destination.equals(town);
    }

    /**
     * Compares this road to another road based on weight.
     *
     * @param r The road to compare to.
     * @return A negative integer, zero, or a positive integer as this road is less than, equal to, or greater than the specified road.
     */
    @Override
    public int compareTo(Road r) {
        return Integer.compare(this.weight, r.weight);
    }

    /**
     * Checks if this road is equal to another object.
     *
     * @param r The object to compare to.
     * @return True if the roads are equal, false otherwise.
     */
    @Override
    public boolean equals(Object r) {
        if (this == r) return true;
        if (!(r instanceof Road)) return false;
        Road road = (Road) r;
        return (road.destination.equals(destination) && road.source.equals(source)) ||
               (road.destination.equals(source) && road.source.equals(destination));
    }

    /**
     * Returns a string representation of the road.
     *
     * @return A string representation of the road.
     */
    @Override
    public String toString() {
        return name + " connects " + source.getName() + " and " + destination.getName() + " and is " + weight + " miles long";
    }
}