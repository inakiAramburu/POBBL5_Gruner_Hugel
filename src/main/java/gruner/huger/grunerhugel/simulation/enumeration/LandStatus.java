package gruner.huger.grunerhugel.simulation.enumeration;

public enum LandStatus {
    EMPTY("Empty", 0),
    PLANTING("Planting", 0.75),
    GROWING("Growing", 1.5),
    RIPE("Ripe", 0),
    HARVESTING("Harvesting", 0.65);

    private String status;
    private double cost;   //  hours for every 10m2

    LandStatus(String status, double cost) {
        this.status = status;
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public double getCost(){
        return cost;
    }
}
