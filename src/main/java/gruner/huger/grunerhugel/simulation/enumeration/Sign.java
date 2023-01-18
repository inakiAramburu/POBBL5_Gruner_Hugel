package gruner.huger.grunerhugel.simulation.enumeration;

public enum Sign {
    PLUS("+"),
    MINUS("-");

    private String op;

    Sign(String op){
        this.op = op;
    }

    public String getSign(){
        return op;
    }
}
