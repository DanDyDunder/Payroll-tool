package util.models;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;


public class ComparisonMapJsonAdapter {
    @FromJson
    ComparisonMapRecord comparisonMapRecordFromJson(ComparisonMapJson cmapJSON) {

        return new ComparisonMapRecord(cmapJSON.externalName, cmapJSON.cust_legacy, cmapJSON.cust_new);
    }

    @ToJson
    ComparisonMapJson comparisonMapRecordToJson(ComparisonMapRecord cmap) {
        return new ComparisonMapJson(cmap.mapType.toString(), cmap.sourceValue, cmap.targetValue);
    }
}
