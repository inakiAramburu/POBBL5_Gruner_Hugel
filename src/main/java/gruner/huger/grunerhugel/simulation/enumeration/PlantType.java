package gruner.huger.grunerhugel.simulation.enumeration;

public enum PlantType {
    WHEAT("Wheat", 28.16, 110, 10);

    private double seedPrice;
    private String pType;
    private int kgsPerHa;
    private int tPerHa;

    PlantType(String pType, double seedPrice, int kgsPerHa, int tPerHa) {
        this.pType = pType;
        this.seedPrice = seedPrice;
        this.kgsPerHa = kgsPerHa;
        this.tPerHa = tPerHa;
    }

    public String getPlantType() {
        return pType;
    }

    public double getSeedPrice() {
        return seedPrice;
    }

    public int getKgsPerHa() {
        return kgsPerHa;
    }

    public int getTPerHa() {
        return tPerHa;
    }
}
