package util.models;

import java.util.Objects;

public class ComparisonMapRecord {
    public final MapType mapType;
    public final String sourceValue;
    public final String targetValue;

    public ComparisonMapRecord(String mapType, String sourceValue, String targetValue) {
        this.mapType = stringToMapType(mapType);
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;

    }

    private MapType stringToMapType(String mapType) {
        switch (mapType){
            case "Company Code":
                return MapType.CompanyCode;
            case "Pay Component":
                return MapType.PayType;
            case "User ID":
                return MapType.UserID;
            default:
                throw new Error(String.format("Invalid mapping type: %s", mapType));
        }
    }

    public enum MapType {
        CompanyCode, PayType, UserID
    }

    @Override
    public String toString() {
        return String.format("Map type: %s\nLegacy value: %s\nTarget value: %s", mapType, sourceValue, targetValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComparisonMapRecord)) return false;
        ComparisonMapRecord that = (ComparisonMapRecord) o;
        return mapType == that.mapType &&
                sourceValue.equals(that.sourceValue) &&
                targetValue.equals(that.targetValue);
    }
}
