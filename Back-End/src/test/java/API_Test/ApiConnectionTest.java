package API_Test;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ApiConnectionTest {
    @Test

    //Login without Credentials will fail
    public void readFromUrl() {
        try (InputStream in = getInputStreamFromUrl("https://huteam01.atlassian.net/rest/api/2/project/")) {
            System.out.println(convertInputStreamToString(in));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    //Login with credentials
    //Encoding username password with Base64 will pass test
    public void readFromUrlWithBasicAuth() {
        String user = "@gmail.com";
        String passwd = "8kqf1U398F44";
        try (InputStream in = getInputStreamFromUrl("https://huteam01.atlassian.net/rest/api/2/project/", user, passwd)) {
            System.out.println(convertInputStreamToString(in));
        } catch (Exception e) {
            System.out.println("If basic auth is provided, it should be correct: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private InputStream getInputStreamFromUrl(String urlString, String user, String passwd) throws IOException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        String encoded = Base64.getEncoder().encodeToString((user + ":" + passwd).getBytes(StandardCharsets.UTF_8));
        conn.setRequestProperty("Authorization", "Basic " + encoded);
        return conn.getInputStream();
    }

    private InputStream getInputStreamFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn.getInputStream();
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}