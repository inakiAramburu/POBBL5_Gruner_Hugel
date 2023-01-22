package gruner.huger.grunerhugel.simulation.enumeration;

public enum PlantType {
    WHEAT("Wheat", 28.16, 110, 10);

    private double seedPrice;
    private String plantType;
    private int kgsPerHa;
    private int tPerHa;

    PlantType(String plantType, double seedPrice, int kgsPerHa, int tPerHa) {
        this.plantType = plantType;
        this.seedPrice = seedPrice;
        this.kgsPerHa = kgsPerHa;
        this.tPerHa = tPerHa;
    }

    public String getPlantType(){
        return plantType;
    }

    public double getSeedPrice(){
        return seedPrice;
    }

    public int getKgsPerHa(){
        return kgsPerHa;
    }

    public int getTPerHa(){
        return tPerHa;
    }
}
