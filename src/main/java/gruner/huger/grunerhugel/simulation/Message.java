package gruner.huger.grunerhugel.simulation;

import gruner.huger.grunerhugel.simulation.enumeration.Sign;

public class Message {
    public static final String SPLITTER = "|";
    private String concept;
    private int quantity;
    private Sign sign;

    public Message(Sign sign, int quantity, String concept) {
        this.sign = sign;
        this.quantity = quantity;
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

    public int getQuantity(){
        return quantity;
    }
}
