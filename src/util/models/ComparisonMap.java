package util.models;

import java.util.HashMap;

// In the maps, the keys are source-fields, wheras values are target-fields
public class ComparisonMap implements IComparisonMap{
    private HashMap<String, String> idMap = new HashMap<>();
    private HashMap<String, String> payTypeMap = new HashMap<>();
    private HashMap<String, String> companyCodeMap = new HashMap<>();

    public ComparisonMap() {
    }

    public String getPayTypeMapping(String payTypeMapping) {
        return payTypeMap.get(payTypeMapping);
    }

    public String getIdMapping(String idMapping) {
        return idMap.get(idMapping);
    }

    public String getCompanyCodeMapping(String companyCodeMapping) {
        return companyCodeMap.get(companyCodeMapping);
    }

    public void putComparisonMapping(ComparisonMapRecord comparisonMapRecord) {

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
        switch (comparisonMapRecord.mapType) {
            default:
                return true;
        }
    }
}
