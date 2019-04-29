package util.models.JSON;

public class MetaData {
    //TODO Modify, so it works generically
    public final String uri;

    public MetaData(String runNumber, String id, String period, String wagetype) {
        uri = String.format("https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_results(%sL)", JsonUtils.generateExternalCode(runNumber, id, period, wagetype));
    }

    public MetaData (String runNumber) {
        uri = String.format("https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_kpi(%s)", runNumber);
    }
}
