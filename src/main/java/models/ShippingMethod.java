package models;

public class ShippingMethod {

    private final String DHL = "DHL";
    private final String Fedex = "Fedex";
    private final String UPS = "UPS";

    public String getDHL() {
        return DHL;
    }

    public String getFedex() {
        return Fedex;
    }

    public String getUPS() {
        return UPS;
    }
}
