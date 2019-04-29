package util.models.JSON;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import util.models.ComparisonResult;
import util.models.KPI;

import static util.Utils.textDateToDate;

public class KpiJsonAdapter {
    @FromJson
    KPI KpiFromJson(KpiJson kpiJson) {
        KPI kpi = new KPI();
        kpi.runNumber = -1;
        kpi.total = Integer.parseInt(kpiJson.cust_total);
        kpi.matches = Integer.parseInt(kpiJson.cust_matches);
        kpi.matchesPercentage = Double.parseDouble(kpiJson.cust_matchesPer);
        kpi.deviations = Integer.parseInt(kpiJson.cust_deviations);
        kpi.redundancies = Integer.parseInt(kpiJson.cust_redundancies);
        kpi.missing = Integer.parseInt(kpiJson.cust_missing);
        kpi.runMatchImprovement = Integer.parseInt(kpiJson.cust_runMatchImp);
        kpi.runMatchImprovementPercentage = Double.parseDouble(kpiJson.cust_runMatchImpPer);
        return kpi;

    }

    @ToJson
    KpiJson KpiToJson(KPI kpi) {
        return new KpiJson(
                Integer.toString(kpi.runNumber),
                Integer.toString(kpi.total),
                Integer.toString(kpi.matches),
                Double.toString(kpi.matchesPercentage),
                Integer.toString(kpi.deviations),
                Integer.toString(kpi.redundancies),
                Integer.toString(kpi.missing),
                Integer.toString(kpi.runMatchImprovement),
                Double.toString(kpi.runMatchImprovementPercentage)
        );
    }
}
