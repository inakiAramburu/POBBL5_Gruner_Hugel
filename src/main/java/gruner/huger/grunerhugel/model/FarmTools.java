package gruner.huger.grunerhugel.model;
public class FarmTools {
    int id;
    String name;
    int farmId;
    int toolsId;

    public FarmTools(int id, String name, int farmId, int toolsId) {
        this.id = id;
        this.name = name;
        this.farmId = farmId;
        this.toolsId = toolsId;
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

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getToolsId() {
        return toolsId;
    }

    public void setToolsId(int toolsId) {
        this.toolsId = toolsId;
    }

}
