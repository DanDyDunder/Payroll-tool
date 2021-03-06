package util.models;

import java.util.HashMap;
import java.util.List;

// In the maps, the keys are source-fields, wheras values are target-fields
// Goes from source to target, contains every kind of mapping in a mapping table from SuccessFactors
public class ComparisonMap implements IComparisonMap{
    private HashMap<String, String> idMap = new HashMap<>();
    private HashMap<String, String> payTypeMap = new HashMap<>();
    private HashMap<String, String> companyCodeMap = new HashMap<>();

    public ComparisonMap() {
    }

    public ComparisonMap(List<ComparisonMapRecord> records) {
        putManyComparisonMappings(records);
    }

    public String getPayTypeMapping(String payTypeMapping) {
        if(payTypeMap.containsKey(payTypeMapping)) return payTypeMap.get(payTypeMapping);
        return payTypeMapping;
    }

    public String getIdMapping(String idMapping) {
        if (idMap.containsKey(idMapping)) return idMap.get(idMapping);
        return idMapping;
    }

    public String getCompanyCodeMapping(String companyCodeMapping) {
        if (companyCodeMap.containsKey(companyCodeMapping)) return companyCodeMap.get(companyCodeMapping);
        return companyCodeMapping;
    }

    public void putComparisonMapping(ComparisonMapRecord comparisonMapRecord) {
        String key = comparisonMapRecord.sourceValue;
        String value = comparisonMapRecord.targetValue;
        switch (comparisonMapRecord.mapType) {
            case CompanyCode:
                companyCodeMap.put(key, value);
                break;
            case PayType:
                payTypeMap.put(key, value);
                break;
            case UserID:
                idMap.put(key, value);
                break;
            default:
                throw new Error("Invalid mapping type");
        }
    }

    public int countPayTypeMappings() {
        return payTypeMap.size();
    }

    public int countIdMappings() {
        return idMap.size();
    }

    public int countCompanyCodeMappings() {
        return companyCodeMap.size();
    }

    public int countTotalMappings() {
        return countCompanyCodeMappings()+countIdMappings()+countPayTypeMappings();
    }

    public boolean containsPayTypeMapping(String payTypeMapping) {
        return payTypeMap.containsKey(payTypeMapping);
    }

    public boolean containsIdMapping(String idMapping) {
        return idMap.containsKey(idMapping);
    }

    public boolean containsCompanyCodeMapping(String companyCodeMapping) {
        return companyCodeMap.containsKey(companyCodeMapping);
    }

    public boolean containsMapping(ComparisonMapRecord comparisonMapRecord) {
        String key = comparisonMapRecord.sourceValue;
        switch (comparisonMapRecord.mapType) {
            case CompanyCode:
                return containsCompanyCodeMapping(key);
            case PayType:
                return containsPayTypeMapping(key);
            case UserID:
                return containsIdMapping(key);
            default:
                return false;
        }
    }
}
