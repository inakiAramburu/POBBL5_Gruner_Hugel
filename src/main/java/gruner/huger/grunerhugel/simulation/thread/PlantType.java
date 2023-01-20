package gruner.huger.grunerhugel.simulation.thread;

public enum PlantType {
    WHEAT("Trigo"),
    CORN("Maiz");

    private String plantType;

    PlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantType(){
        return plantType;
    }
}
