package gruner.huger.grunerhugel.simulation.enumeration;

public enum PlantType {
    WHEAT("Wheat"),
    CORN("Corn");

    private String plantType;

    PlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantType(){
        return plantType;
    }
}
