import util.JSONParser;

public class Main {

    public static void main(String[] args) {
        String url = "https://apisalesdemo2.successfactors.eu/odata/v2/cust_gp_app_prc_cmt";
        //System.out.println(SuccessFactors.getJSONString("", url));
        String json = SuccessFactors.getJSONString("", url);

        JSONParser.parseJSON(json);
    }
}
