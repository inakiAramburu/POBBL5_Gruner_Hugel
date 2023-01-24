package gruner.huger.grunerhugel.simulation;

import gruner.huger.grunerhugel.simulation.enumeration.Sign;

public class Message {
    public static final String SPLITTER = "|";
    private String concept;
    private double quantity;
    private Sign sign;

    public Message(Sign sign, double cost, String concept) {
        this.sign = sign;
        this.quantity = cost;
        this.concept = concept;
    }

    public String getOperation(){
        return sign.getSign()+" "+quantity;
    }

    public String getConcept(){
        return concept;
    }

    public Sign getSign(){
        return sign;
    }

    public double getQuantity(){
        return quantity;
    }
}
