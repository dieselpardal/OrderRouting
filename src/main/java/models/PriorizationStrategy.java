package models;

public class PriorizationStrategy {

    public final String NONE = "None";
    public final String LARGEST_AVAILABILITY = "LargestAvailability";
    public final String SHORTEST_AVALIBILITY = "ShortestAvailability";
    public final String LARGEST_CAPACITY = "LargestCapacity";

    public String getNone() {
        return NONE;
    }

    public String getLargestAvailability() {
        return LARGEST_AVAILABILITY;
    }

    public String getShortestAvailability() {
        return SHORTEST_AVALIBILITY;
    }

    public String getLargestCapacity() {
        return LARGEST_CAPACITY;
    }

}
