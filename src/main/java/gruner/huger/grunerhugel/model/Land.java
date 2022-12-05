package gruner.huger.grunerhugel.model;

public class Land {
    int id;
    float size;
    String status;
    int farmId;
    int townId;

    public Land(int id, float size, String status, int farmId, int townId) {
        this.id = id;
        this.size = size;
        this.status = status;
        this.farmId = farmId;
        this.townId = townId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

}
