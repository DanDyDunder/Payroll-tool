import util.models.ComparisonMap;
import util.models.EmployeeHashMap;
import util.JSONParser;
import util.models.EmployeeRecord;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String mapurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_cmt/" +
                "?$select=externalName,cust_legacy,cust_new&$format=json";
        String employeeurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_ecr/" +
                "?$select=externalName,cust_costCenter,cust_userId,cust_amtpernum,cust_wageType,cust_companyCode," +
                "cust_payPeriod,cust_payrollArea,cust_sourcetarget&$format=json";
        String runResulturl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_results/" +
                "?$select=externalName,cust_amtpernumSource,cust_amtpernumTarget,cust_deviation," +
                "cust_wageTypeSource,cust_wageTypeTarget,cust_status&$format=json";
        //System.out.println(SuccessFactors.getJSONString("", url));
        String mapjson = SuccessFactors.getJSONString("", mapurl);
        String employeejson = SuccessFactors.getJSONString("", employeeurl);
        String resultjson = SuccessFactors.getJSONString("", runResulturl);

        JSONParser.parseRunResult(resultjson);
        ComparisonMap comparisonMap = new ComparisonMap(JSONParser.parseComparisonMap(mapjson));
        List<EmployeeRecord> smth = JSONParser.parseEmployeeRecords(employeejson);

        EmployeeHashMap map = new EmployeeHashMap();
        map.putManyEmployeeRecords(smth);


        System.out.println("COUNTS");
        // TODO DUPLICATES ARE STORED AS ONE DUE TO RUN NUMBER AND DIFFERENT WAGE TYPES UNDER SAME MONTH
        // fixed for wagetype as of now, so it returns 107

        System.out.println(smth.size());
        System.out.println(map.countEmployees());
        System.out.println(map.countTotalRecords());

        Comparer comparer = new Comparer(smth, comparisonMap);
        var lst = comparer.getRecordsPerRun();
/*
        System.out.println("Size of runlist: " + lst.size());
        System.out.println("Amount of records in run 1 of source" + lst.get(0).item1.countTotalRecords());
        System.out.println("Amount of records in run 1 of target" + lst.get(0).item2.countTotalRecords());
        System.out.println("Amount of records in run 2 of source" + lst.get(1).item1.countTotalRecords());
        System.out.println("Amount of records in run 2 of target" + lst.get(1).item2.countTotalRecords());
        System.out.println(lst.get(0).item1.countTotalRecords()+lst.get(0).item2.countTotalRecords()+lst.get(1).item1.countTotalRecords()+lst.get(1).item2.countTotalRecords());
*/
        var elseff = comparer.generateComparisonResults(lst.get(0));
       JSONParser.runResultToJson(elseff.item1);
        System.out.println(elseff.item2);
    }
}
