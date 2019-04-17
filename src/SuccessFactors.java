import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Utils;


/**
 * Class handles SuccessFactors specific function calls.<br>
 * <br>
 * Copyright (C) GP Strategies - All Rights Reserved Unauthorized copying of this file, <br>
 * via any medium is strictly prohibited Proprietary and confidential. <br>
 * Written by Emil Sarkisi Stepanian, estepanian@gpstrategies.com, 10.12.18 <br>
 * @author Emil Sarkisi Stepanian, estepanian@gpstrategies.com
 * @version 1.1.0
 * @since 11.12.18
 */
public class SuccessFactors {

    private static final Logger LOG = LoggerFactory.getLogger(SuccessFactors.class);

    /**
     * Creates a basic Authentication header specified for SuccessFactors OData calls
     * @param tenant The username of the tenant calling the function
     * @param headers The Map of headers to put auth header inside
     */
    private static void createSFBasicAuthHeader(String tenant, HashMap<String, String> headers) {
        //String companyId = Utils.getConfigProperty(tenant, "success_factors_company_id");
        //String username = Utils.getConfigProperty(tenant, "success_factors_user_id");
        //String password = Utils.getConfigProperty(tenant, "success_factors_password");

        //headers.put("Authorization", "Basic " + Utils.base64Encode(username + "@" + companyId + ":" + password));

        headers.put("Authorization", "Basic c2ZhZG1pbkBTRlBBUlQwMzE3MDQ6cGFydDE4MDJEQzI=");
    }

    private static void createHeader(String tenant, HashMap<String, String> headers) {
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json;charset=UTF-8");
        createSFBasicAuthHeader(tenant, headers);
    }

    private static String getJsonFromHttpResponse(CloseableHttpResponse ajaxResponse) {
        HttpEntity ajaxEntity = ajaxResponse.getEntity();
        try {

            // Get the content of the response payload and make it a JSON object
            InputStream inStream;

            inStream = ajaxEntity.getContent();
            String result = Utils.convertStreamToString(inStream);

            return result;

        } catch (UnsupportedOperationException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to make a SuccessFactors OData query
     * @param tenant The username of the tenant calling the function
     * @param url The URL String to query
     * @return Returns the query as a JSONObject
     */
    public static String getJSONString(String tenant, String url) {

        //String environmentUrl = Utils.getConfigProperty(tenant, "success_factors_environment", context);

        //String urlSuffix = url.replace(" ", "%20");

        HashMap<String, String> headers = new HashMap<>();
        createHeader(tenant, headers);

        CloseableHttpResponse ajaxResponse = Utils.httpGet(url, headers);

        String jsonString = getJsonFromHttpResponse(ajaxResponse);
        return jsonString;

    }


}
