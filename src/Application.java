import util.Tuple;
import util.models.*;
import util.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        String mapurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_cmt/" +
                "?$select=externalName,cust_legacy,cust_new&$format=json";
        String employeeurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_ecr/" +
                "?$select=externalName,cust_costCenter,cust_userId,cust_amtpernum,cust_wageType,cust_companyCode," +
                "cust_payPeriod,cust_payrollArea,cust_sourcetarget&$format=json";
        String runResulturl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_results/" +
                "?$select=externalName,cust_amtpernumSource,cust_amtpernumTarget,cust_deviation," +
                "cust_wageTypeSource,cust_wageTypeTarget,cust_status&$format=json";
        String latestRunNumberurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_ecr/?$orderby=externalName%20desc&$top=1&$select=externalName&$format=json";
        String kpiurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_kpi/?$format=json";

        String mapjson = SuccessFactors.getJSONString("", mapurl);
        String employeejson = SuccessFactors.getJSONString("", employeeurl);
        String resultjson = SuccessFactors.getJSONString("", runResulturl);
        String kpijson = SuccessFactors.getJSONString("", kpiurl);

        ComparisonMap comparisonMap = new ComparisonMap(JSONParser.parseComparisonMap(mapjson));
        List<EmployeeRecord> allEmployeeRecords = JSONParser.parseEmployeeRecords(employeejson);

        EmployeeHashMap map = new EmployeeHashMap();
        map.putManyEmployeeRecords(allEmployeeRecords);

        Comparer comparer = new Comparer(allEmployeeRecords, comparisonMap);
        List<Tuple<EmployeeHashMap, EmployeeHashMap>> sortedRecords = comparer.getRecordsPerRun();
        List<KPI> kpis = new ArrayList<>();

        for (int i = 0; i < sortedRecords.size(); i++) {
            Tuple<EmployeeHashMap, EmployeeHashMap> result = sortedRecords.get(i);
            Tuple<List<ComparisonResult>, KPI> comparerOutput = comparer.generateComparisonResults(result);
            List<ComparisonResult> results = comparerOutput.item1;
            KPI kpi = comparerOutput.item2;

            String comparisonResultJson = JSONParser.runResultToJson(results);
            SuccessFactors.PostResults(comparisonResultJson);
            kpis.add(kpi);
        }

        for (int i = 1; i < kpis.size(); i++) {
            KPI currentKPI = kpis.get(i);
            KPI prevKPI = kpis.get(i-1);

            currentKPI.calculatePercentages(prevKPI);
        }

        String kpiJson = JSONParser.kpiToJson(kpis);
        SuccessFactors.PostResults(kpiJson);
    }
}
