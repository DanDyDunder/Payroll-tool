package util.models.JSON;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import util.models.ComparisonMapRecord;
import util.models.ComparisonResult;

public class RunResultJsonAdapter {
    @FromJson
    ComparisonResult ComparisonResultFromJson(RunResultJson rresultJSON) {

        return new ComparisonResult(
                rresultJSON.externalName,
                Double.parseDouble(rresultJSON.cust_amtpernumSource),
                Double.parseDouble(rresultJSON.cust_amtpernumTarget),
                Double.parseDouble(rresultJSON.cust_deviation),
                rresultJSON.cust_wageTypeSource,
                rresultJSON.cust_wageTypeTarget,
                rresultJSON.cust_status
        );
    }

    @ToJson
    RunResultJson ComparisonResultToJson(ComparisonResult result) {
        return new RunResultJson(
                result.userId,
                Double.toString(result.amtpernumSource),
                Double.toString(result.amtpernumTarget),
                Double.toString(result.deviation),
                result.wageTypeSource,
                result.wageTypeTarget,
                result.status
                );
    }
}
