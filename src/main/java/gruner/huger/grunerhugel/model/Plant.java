package gruner.huger.grunerhugel.model;

public class Plant {
    int id;
    String name;
    int healthPoints;
    String type; // Conditions type
    int landId;

    public Plant(int id, String name, int healthPoints, String type, int landId) {
        this.id = id;
        this.name = name;
        this.healthPoints = healthPoints;
        this.type = type;
        this.landId = landId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLandId() {
        return landId;
    }

    public void setLandId(int landId) {
        this.landId = landId;
    }

}
