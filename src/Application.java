import util.Tuple;
import util.models.*;
import util.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        // Urls with JSON-data
        String mapurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_cmt/" +
                "?$select=externalName,cust_legacy,cust_new&$format=json";
        String employeeurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_ecr/" +
                "?$select=externalName,cust_costCenter,cust_userId,cust_amtpernum,cust_wageType,cust_companyCode," +
                "cust_payPeriod,cust_payrollArea,cust_sourcetarget&$format=json";
        String runResulturl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_results/" +
                "?$select=externalName,cust_amtpernumSource,cust_amtpernumTarget,cust_deviation," +
                "cust_wageTypeSource,cust_wageTypeTarget,cust_status&$format=json";
        String kpiurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_kpi/?$format=json";

        // Url with the current latest run
        String latestRunNumberurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_ecr/?$orderby=externalName%20desc&$top=1&$select=externalName&$format=json";

        // Fetched JSON data from Url
        String mapjson = SuccessFactors.getJSONString("", mapurl);
        String employeejson = SuccessFactors.getJSONString("", employeeurl);
        String resultjson = SuccessFactors.getJSONString("", runResulturl);
        String kpijson = SuccessFactors.getJSONString("", kpiurl);

        // Comparison map, maps the different fields from source to target
        IComparisonMap comparisonMap = new ComparisonMap(JSONParser.parseComparisonMap(mapjson));
        // allEmployeeRecords contains all employee information, from both source and target
        List<EmployeeRecord> allEmployeeRecords = JSONParser.parseEmployeeRecords(employeejson);

        // EmployeeHashmap is the data structure for the employee information, both source and target
        IRecordsStructure map = new EmployeeHashMap();
        // Putting all information into the map at once
        map.putManyEmployeeRecords(allEmployeeRecords);

        // Algorithm that compares all employee information
        Comparer comparer = new Comparer(allEmployeeRecords, comparisonMap);
        // Sorted firstly by run, then source and target, then id, date, wagetype - the list contains a source and target map for every run.
        List<Tuple<IRecordsStructure, IRecordsStructure>> sortedRecords = comparer.getRecordsPerRun();
        // KPIs for every run
        List<KPI> kpis = new ArrayList<>();

        // For every run, compare source and target, and get results, post them to SuccessFactors, and generate KPI.
        for (int i = 0; i < sortedRecords.size(); i++) {
            Tuple<IRecordsStructure, IRecordsStructure> result = sortedRecords.get(i);
            Tuple<List<ComparisonResult>, KPI> comparerOutput = comparer.generateComparisonResults(result);
            List<ComparisonResult> results = comparerOutput.item1;
            KPI kpi = comparerOutput.item2;

            String comparisonResultJson = JSONParser.runResultToJson(results);
            SuccessFactors.PostResults(comparisonResultJson);
            kpis.add(kpi);
        }


        // Finalize every KPI and post to SuccessFactors
        for (int i = 1; i < kpis.size(); i++) {
            KPI currentKPI = kpis.get(i);
            KPI prevKPI = kpis.get(i-1);

            currentKPI.calculatePercentages(prevKPI);
        }

        String kpiJson = JSONParser.kpiToJson(kpis);
        SuccessFactors.PostResults(kpiJson);
    }
}
