package com.teamsecurity.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpSender {

    private List<String> cookies = new ArrayList<String>();
    private HttpURLConnection conn;

    private final String USER_AGENT = "Mozilla/5.0";

    private StringBuilder outputString = new StringBuilder();

    public String hitUrl(String hitUrl) throws Exception {
        String url = hitUrl;
        //String url = "https://accounts.google.com/ServiceLoginAuth";
        //String gmail = "https://mail.google.com/mail/";  
//        String url = "http://google-gruyere.appspot.com/375977543527783452191139736174858884320/login";
//        String gmail = "http://google-gruyere.appspot.com/375977543527783452191139736174858884320/snippets.gtl?uid=cheddar";
        //String url = "http://demo.testfire.net/bank/login.aspx";
        //String gmail = "http://demo.testfire.net/bank/main.aspx";
        //String logout = "http://demo.testfire.net/bank/logout.aspx";

        HttpSender http = new HttpSender();

        // make sure cookies is turn on
        //CookieHandler.setDefault(new CookieManager());
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        // 1. Send a "GET" request, so that you can extract the form's data.
        String page = http.GetPageContent(url);
        String postParams = http.getFormParams(page, "hacker", "hacker");

        // 2. Construct above post's content and then send a POST request for
        // authentication
        String outputString = http.sendPost(url, postParams);

        // 3. success then go to gmail.
//        String result = http.GetPageContent(gmail);
        //System.out.println(result);

        // 4. logout
        //String result1 = http.GetPageContent(logout);
        return outputString;
    }

    public String sendPost(String url, String postParams) throws Exception {

        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", "accounts.google.com");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        /*if (!getCookies().isEmpty()) {
			for (String cookie : this.cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}*/
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "url");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

        conn.setDoOutput(true);
        conn.setDoInput(true);

        // Send post request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in
                = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

        System.out.println("header fields:" + conn.getHeaderFields());

        for (Map.Entry<String, List<String>> pair : conn.getHeaderFields().entrySet()) {

            if (("X-XSS-Protection").equals(pair.getKey())) {
                List<String> values = pair.getValue();
                if (values.contains("0")) {
//                    System.out.println("Security misconfiguration: X-XSS-Protection is 0");
//                    outputString.append("Security misconfiguration: X-XSS-Protection is 0").append("\n");
                    outputString.append("Security misconfiguration: Site is vulnerable to XSS since response header X-XSS-Protection is 0").append("\n");
                }
            } else if (("X-Frame-Options").equals(pair.getKey())) {
                List<String> values = pair.getValue();
                for (String temp : values) {
                    if (temp.contains("SAMEORIGIN")) {
                        System.out.println("Security misconfiguration: X-Frame-Options-SAMEORIGIN");
                        outputString.append("Security misconfiguration: Site is vulnerable to xframe since response header X-Frame-Options is SAMEORIGIN").append("\n");
                    } else if (temp.contains("ALLOW-FROM")) {
                        System.out.println("Security misconfiguration: X-Frame-Options-ALLOW-FROM");
                        outputString.append("Security misconfiguration: Site is vulnerable to xframe since response header X-Frame-Options is ALLOW-FROM").append("\n");
                    } else {
                        outputString.append("Security misconfiguration: Site is not vulnerable to xframe since response header X-Frame-Options is DENY").append("\n");
                    }
                }
            } else if (("Strict-Transport-Security").equals(pair.getKey())) {
                List<String> values = pair.getValue();
                for (String temp : values) {
                    if (!temp.contains("max-age")) {
                        System.out.println("Security misconfiguration: Site is vulnerable for MIME-TYPE changes since response header Strict-Transport-Security does not have max-age");
                        outputString.append("Security misconfiguration: Site is vulnerable for MIME-TYPE changes since response header Strict-Transport-Security does not have max-age").append("\n");
                    }
                }
            } else if (("X-Content-Type-Options").equals(pair.getKey())) {
                List<String> values = pair.getValue();
                for (String temp : values) {
                    if (!temp.contains("nosniff")) {
                        //System.out.println("Security misconfiguration: Strict-Transport-Security");
                        outputString.append("Security misconfiguration: Site is vulnerable since X-Content-Type-Options is not nosniff").append("\n");

                    }
                }
            }
        }

        if (conn.getHeaderFields().get("X-XSS-Protection") == null) {
            System.out.println("Security misconfiguration: X-XSS-Protection does not exist");
            outputString.append("Security misconfiguration: Site is vulnerable since X-XSS-Protection does not exist in response header").append("\n");
        }
        if (conn.getHeaderFields().get("X-Frame-Options") == null) {
            System.out.println("Security misconfiguration: X-Frame-Options does not exist");
            outputString.append("Security misconfiguration: Site is vulnerable since X-Frame-Options does not exist in response header").append("\n");
        }
        if (conn.getHeaderFields().get("Strict-Transport-Security") == null) {
            System.out.println("Security misconfiguration: Strict-Transport-Security does not exist");
            outputString.append("Security misconfiguration: Site is vulnerable since Strict-Transport-Security does not exist in response header").append("\n");
        }
        if (conn.getHeaderFields().get("X-Content-Type-Options") == null) {
            System.out.println("Security misconfiguration: X-Content-Type-Options does not exist");
            outputString.append("Security misconfiguration: Site is vulnerable since X-Content-Type-Options does not exist in response header").append("\n");
        }
        if (conn.getHeaderFields().get("Content-Security-Policy") == null) {
            System.out.println("Security misconfiguration: Content-Security-Policy does not exist");
            outputString.append("Security misconfiguration: Site is vulnerable since Content-Security-Policy does not exist in response header").append("\n");
        }
        System.out.println("before printing output");
        System.out.println("outputString.tostring:" + outputString.toString());
        return outputString.toString();
    }

    public String GetPageContent(String url) throws Exception {

        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        if (cookies != null) {
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in
                = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("Set Cookies:" + conn.getHeaderFields().get("Set-Cookie"));
        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));
        System.out.println("final response headerfields: " + conn.getHeaderFields());
        return response.toString();

    }

    public String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
//        Element loginform = doc.getElementById("login");
        Elements inputElements = doc.getElementsByTag("input");
        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("uid")) {
                value = username;
            } else if (key.equals("pw")) {
                value = password;
            }
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
        }
//        Element loginform = doc.getElementById("login");
//        Elements inputElements = loginform.getElementsByTag("input");
//        List<String> paramList = new ArrayList<String>();
//        for (Element inputElement : inputElements) {
//            String key = inputElement.attr("name");
//            String value = inputElement.attr("value");
//
//            if (key.equals("uid")) {
//                value = username;
//            } else if (key.equals("passw")) {
//                value = password;
//            }
//            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
//        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        return result.toString();
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
}
