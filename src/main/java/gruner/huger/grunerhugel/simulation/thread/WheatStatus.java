package gruner.huger.grunerhugel.simulation.thread;

public enum WheatStatus {
    GERMINATION("GERMINATION"),
    VEGETATIVE("VEGETATIVE"),
    TILLERING("TILLERING"),
    ANTHESIS("ANTHESIS"),
    MILKY("MILKY"),
    PASTY("PASTY"),
    MATURATION("MATURATION");

    private String status;

    WheatStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
