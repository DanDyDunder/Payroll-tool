import util.JSONParser;

public class Main {

    public static void main(String[] args) {
        String mapurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_cmt/" +
                "?$select=externalName,cust_legacy,cust_new&$format=json";
        String employeeurl = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_ecr/" +
                "?$select=externalName,cust_costCenter,cust_userId,cust_amtpernum,cust_wageType,cust_companyCode," +
                "cust_payPeriod,cust_payrollArea,cust_sourcetarget&$format=json";
        //System.out.println(SuccessFactors.getJSONString("", url));
        String mapjson = SuccessFactors.getJSONString("", mapurl);
        String employeejson = SuccessFactors.getJSONString("", employeeurl);
        JSONParser.parseComparisonMap(mapjson);
        JSONParser.parseEmployeeRecords(employeejson);
    }
}
