package util.models;

public class PayMap {
    public final String externalName;
    public final String cust_legacy;
    public final String cust_new;

    public PayMap(String externalName, String cust_legacy, String cust_new) {
        this.externalName = externalName;
        this.cust_legacy = cust_legacy;
        this.cust_new = cust_new;

    }

    @Override
    public String toString() {
        return String.format("External name: %s\nLegacy value: %s\nNew value: %s", externalName, cust_legacy, cust_new);

    }
}
