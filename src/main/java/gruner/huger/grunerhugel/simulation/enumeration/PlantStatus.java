package gruner.huger.grunerhugel.simulation.enumeration;

public enum PlantStatus {
    GROWING("Growing"),
    RIPE("Ripe");

    private String status;

    PlantStatus(String state) {
        status = state;
    }

    public String getStatus() {
        return status;
    }
}
