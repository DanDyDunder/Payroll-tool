package util.models.JSON;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import util.models.ComparisonMapRecord;
import util.models.ComparisonResult;

import static util.Utils.textDateToDate;

public class RunResultJsonAdapter {
    @FromJson
    ComparisonResult ComparisonResultFromJson(RunResultJson rresultJSON) {

        return new ComparisonResult(
                Integer.parseInt(rresultJSON.externalName),
                rresultJSON.cust_userIdSource,
                rresultJSON.cust_userIdTarget,
                textDateToDate(rresultJSON.cust_payPeriod),
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
                Integer.toString(result.runNumber),
                result.userIdSource,
                result.userIdTarget,
                result.payPeriod.toString(),
                Double.toString(result.amtpernumSource),
                Double.toString(result.amtpernumTarget),
                Double.toString(result.deviation),
                result.wageTypeSource,
                result.wageTypeTarget,
                result.status
                );
    }
}
