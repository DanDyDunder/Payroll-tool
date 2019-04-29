package util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static CloseableHttpResponse httpGet(String url, HashMap<String, String> headers) {
        int attempt = 0;
        boolean isSuccess = false;
        CloseableHttpResponse ajaxResponse = null;
        while (attempt < 3 && !isSuccess) {
            attempt++;

            try {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                // Retrieves the URL for the token endpoint
                HttpGet httpGet = new HttpGet(url);

                if (headers != null) {
                    addGetHeaders(httpGet, headers);
                }

                // Execute request
                ajaxResponse = httpClient.execute(httpGet);

                int statusCode = ajaxResponse.getStatusLine().getStatusCode();

                if ((statusCode == 200 || statusCode == 201)) {
                    isSuccess = true;
                }

            } catch (ClientProtocolException ex) {
                LOG.error(ex.getMessage());
                ex.printStackTrace();
            } catch (IOException ex) {
                LOG.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return ajaxResponse;
    }

    public static CloseableHttpResponse httpPost(String url, String payload, HashMap<String, String> headers) {

        int attempt = 0;
        boolean isSuccess = false;
        CloseableHttpResponse ajaxResponse = null;
        while (attempt < 3 && !isSuccess) {
            attempt++;
            try {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                // Retrieves the URL for the token endpoint
                HttpPost httpPost = new HttpPost(url);
                if (headers != null) {
                    addPostHeaders(httpPost, headers);
                }

                // Turn the JSON object to a String and make it a String entity for the POST
                // request
                String json = payload;
                StringEntity entity = new StringEntity(json, "UTF-8");

                entity.setContentType("application/json;charset=UTF-8");
                httpPost.setEntity(entity);

                // Execute request
                ajaxResponse = httpClient.execute(httpPost);

                int statusCode = ajaxResponse.getStatusLine().getStatusCode();

                if ((statusCode == 200 || statusCode == 201)) {
                    isSuccess = true;
                }

            } catch (ClientProtocolException ex) {
                LOG.error(ex.getMessage());
                ex.printStackTrace();
            } catch (IOException ex) {
                LOG.error(ex.getMessage());
                ex.printStackTrace();

            }

        }
        return ajaxResponse;
    }

    private static void addGetHeaders(HttpGet httpGet, HashMap<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
    }

    private static void addPostHeaders(HttpPost httpPost, HashMap<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
    }

    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());

            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static YearMonth textDateToDate(String textDate) {
        String[] yearAndMonth = textDate.split(" ");
        int year = Integer.parseInt(yearAndMonth[1]);
        Month month;
        switch ((yearAndMonth[0]).toLowerCase()) {
            case "januar":
            case "january":
                month = Month.JANUARY;
                break;
            case "february":
                month = Month.FEBRUARY;
                break;
            case "march":
                month = Month.MARCH;
                break;
            case "april":
                month = Month.APRIL;
                break;
            case "may":
                month = Month.MAY;
                break;
            case "june":
                month = Month.JUNE;
                break;
            case "july":
                month = Month.JULY;
                break;
            case "august":
                month = Month.AUGUST;
                break;
            case "september":
                month = Month.SEPTEMBER;
                break;
            case "october":
                month = Month.OCTOBER;
                break;
            case "november":
                month = Month.NOVEMBER;
                break;
            case "december":
                month = Month.DECEMBER;
                break;
            default:
                return null;
        }
        return YearMonth.of(year, month);
    }

}
