package level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * Demonstrates how to use Level 3 Media Portal APIs.
 * 
 * @author boutwell.jason
 * 
 */
public class SampleJavaApiImpl {
    public static void main(String[] args) throws Exception {
        String key = ""; // key and secret key that you create in the Media Portal
        String secretKey = "";
        String method = "GET"; // or "POST"
        String operation = "key"; // "key" or "accessGroups"
        String postMessage = "<paths><path>/valid/but/fake/path</path></paths>";// if you are doing
                                                                                // invalidation
                                                                                // POST, post body
                                                                                // looks like this
        String queryString = "?serviceType=c"; // required only for some operations
        String response = callApi(key, secretKey, method, operation, postMessage, queryString);
        System.out.println(response);
    }

    //
    // This sample function will only successfully call the "key" or "accessGroups" resources, and
    // is intended to demonstrate
    // how to successfully format the header. Please see the documentation for the URI format for
    // additional calls, such as usage, rtm, etc,
    // which require the SCOPE specifier.
    //
    private static String callApi(String key, String secretKey, String method, String operation,
            String postMessage, String queryString) throws Exception {
        String baseUrl = "/" + operation + "/v1.0";
        String contentType = "text/xml";
        String date = getCurrentFormattedDate();
        String contentMD5 = "";

        // Generate signature
        StringBuffer buf = new StringBuffer();
        buf.append(date).append("\n");
        buf.append(baseUrl).append("\n");
        buf.append(contentType).append("\n");
        buf.append(method).append("\n");
        buf.append(contentMD5);
        String signature = sign(buf.toString(), secretKey);

        // add query string here
        URL url = new URL("https://ws.level3.com" + baseUrl + queryString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Date", date);
        conn.setRequestProperty("Content-Type", contentType);
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-MD5", contentMD5);
        conn.setRequestProperty("Authorization", "MPA " + key + ":" + signature);

        if ("POST".equals(method)) {
            conn.setRequestProperty("Content-Length", String.valueOf(postMessage.length()));
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(postMessage);
            out.close();
        } else {

            conn.setRequestProperty("Content-Length", "0");
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK)

            return extractResponseBody(conn.getErrorStream());
        else

            return extractResponseBody(conn.getInputStream());

    }

    // Signed String must be BASE64 encoded.
    private static String sign(String data, String secretKey) throws NoSuchAlgorithmException,
                                                             UnsupportedEncodingException,
                                                             InvalidKeyException {
        javax.crypto.Mac mac = Mac.getInstance("HmacSHA1");
        byte[] keyBytes = secretKey.getBytes("UTF8");

        javax.crypto.spec.SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
        mac.init(signingKey);
        byte[] signBytes = mac.doFinal(data.getBytes("UTF8"));
        // Use any base64 encoder. This is using apache-codec-1.4
        String base64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(signBytes));
        if (base64.endsWith("\r\n"))
            base64 = base64.substring(0, base64.length() - 2);
        return base64;
    }

    // simple method to extract xml from InputStream
    private static String extractResponseBody(InputStream is) throws IOException {
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null)
                    sb.append(line);
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    private static String getCurrentFormattedDate() {
        String fmt = "EEE, dd MMM yyyy HH:mm:ss ";
        SimpleDateFormat df = new SimpleDateFormat(fmt, Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = df.format(new Date()) + "GMT";
        return date;
    }

}
