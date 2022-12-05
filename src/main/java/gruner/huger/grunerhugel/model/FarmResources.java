package gruner.huger.grunerhugel.model;

public class FarmResources {
    int id;
    String name;
    int townId;
    int resourcesId;

    public FarmResources(int id, String name, int townId, int resourcesId) {
        this.id = id;
        this.name = name;
        this.townId = townId;
        this.resourcesId = resourcesId;
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

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(int resourcesId) {
        this.resourcesId = resourcesId;
    }

}
