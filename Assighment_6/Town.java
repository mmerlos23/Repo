package graphs;

import java.util.Objects;

/**
 * Represents a town and implements Comparable interface for sorting.
 * 
 * @author Moises Merlos
 * @date 12/03/2023
 * 
 */
public class Town implements Comparable<Town> {

    private String name;

    /**
     * Constructs a new Town with the given name.
     *
     * @param name The name of the town.
     */
    public Town(String name) {
        this.name = name;
    }

    /**
     * Copy constructor that creates a new Town using the name of the templateTown.
     *
     * @param templateTown The template town to copy.
     */
    public Town(Town templateTown) {
        this.name = templateTown.getName();
    }

    /**
     * Gets the name of the town.
     *
     * @return The name of the town.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this town with another town based on their names.
     *
     * @param o The town to compare with.
     * @return A negative integer, zero, or a positive integer as this town is less than, equal to,
     *         or greater than the specified town.
     */
    @Override
    public int compareTo(Town o) {
        return this.name.compareTo(o.name);
    }

    /**
     * Returns a string representation of the town.
     *
     * @return A string representation of the town.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Computes a hash code for the town based on its name.
     *
     * @return A hash code for the town.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object to compare with.
     * @return True if this object is the same as the obj argument, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Town otherTown = (Town) obj;
        return Objects.equals(name, otherTown.name);
    }
}