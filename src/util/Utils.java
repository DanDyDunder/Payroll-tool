package util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static void addGetHeaders(HttpGet httpGet, HashMap<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
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

}
