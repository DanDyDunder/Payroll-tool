package util.models;

import util.Tuple;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;

public interface IComparisonMap {
    String getPayTypeMapping(String payTypeMapping);
    String getIdMapping(String idMapping);
    String getCompanyCodeMapping(String companyCodeMapping);

    void putComparisonMapping(ComparisonMapRecord comparisonMapRecord);
    default void putManyComparisonMappings(List<ComparisonMapRecord> comparisonMapRecords) {
        for (ComparisonMapRecord comparisonMapRecord : comparisonMapRecords) {
            putComparisonMapping(comparisonMapRecord);
        }
    }

    int countPayTypeMappings();
    int countIdMappings();
    int countCompanyCodeMappings();
    int countTotalMappings();

    boolean containsPayTypeMapping(String payTypeMapping);
    boolean containsIdMapping(String idMapping);
    boolean containsCompanyCodeMapping(String companyCodeMapping);
    boolean containsMapping(ComparisonMapRecord comparisonMapRecord);
}
