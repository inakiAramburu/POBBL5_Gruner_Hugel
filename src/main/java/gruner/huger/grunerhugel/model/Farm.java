package gruner.huger.grunerhugel.model;

public class Farm {
    int id;
    String name;
    float money;
    int userId;

    public Farm(int id, String name, float money, int userId) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.userId = userId;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
