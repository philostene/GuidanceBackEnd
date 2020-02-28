package intra.poleemploi.utility;

import intra.poleemploi.Consts.Consts;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.Content;
import intra.poleemploi.entities.StatistiquesParJour;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;

public class LoginKnowMore {

    private String jsessionId;
    private CookieStore cookieStore = new BasicCookieStore();

    public List<Appli> listAppli() throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String url = Consts.URLBASELOGINKM;
        ReadHtmlTable readHtmlTable = new ReadHtmlTable();
        ResponseEntity<String> response = httpPostListAppli(url);
        String toto = "toto";
        return readHtmlTable.getAppliList(toto);
    }

    public List<Content> listContents(List<Appli> listAppli) throws IOException {

        List<Content> listcontentToBeReturned = new ArrayList<>();

        String statisticBaseURL = Consts.URLBASEFORCONTENTSDETAILS;
        String statisticURL;
        String response;

        for (Appli appli : listAppli) {
            ReadHtmlTable readHtmlTable = new ReadHtmlTable();
            statisticURL = statisticBaseURL + appli.getIdAppliKM();
            response = httpGetListContent(statisticURL);
            List<Content> listContents = readHtmlTable.getContentsList(response, appli);
            try {
                listcontentToBeReturned.addAll(listContents);
            } catch (Exception e) {
                System.out.println(" LoginKnowmore.listContents error  " + e.getMessage());
            }
        }
        return listcontentToBeReturned;
    }

//    public List<StatistiquesParJour> listStatistics(List <Content> listContents) throws IOException, InterruptedException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        List<StatistiquesParJour> listSatisticsParJourReturned = new ArrayList<>();
//      //  String statisticBaseURL = Consts.URLBASEFORSTATISTICSDETAILS;
//        String statisticsURL;
//        String response;
//        for (Content content : listContents){
//            ReadHtmlTable readHtmlTable = new ReadHtmlTable();
//            statisticsURL = content.getContentURL();
//            response = httpGetListStatistics(statisticsURL);
//            if (response.contains("Vous n'êtes pas identifié")) {   //Affichage de la mire d identification si jamais
//                String url = Consts.URLBASELOGINKM;
//                String responseLogin = httpPostListAppli(url);
//                response = httpGetListStatistics(statisticsURL);
//            }
//            List<StatistiquesParJour> listStatistiquesParJour= readHtmlTable.getStatisticsList(response, content);
//            try {
//                listSatisticsParJourReturned.addAll(listStatistiquesParJour);
//            }
//            catch ( Exception e) {
//                System.out.println(" LoginKnowmore.listContents error  " + e.getMessage());
//            }
//        }
//        Thread.sleep(100); // pour éviter de saturer le serveur
//        return listSatisticsParJourReturned;
//    }

    public String httpGetListStatistics(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/png,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Mobile Safari/537.36");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("cache-control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "KM_INFO=&id:27e3fz5ggta; " + jsessionId);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseKM;
        if (statusCode == 200) {
            responseKM = EntityUtils.toString(response.getEntity(), "UTF8");
            httpclient.close();
            return responseKM;
        }
        throw new RuntimeException("Failed with HTTP error code : " + statusCode);
    }

    private ResponseEntity<String> httpPostListAppli(String url) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {

        ResponseEntity<String> response = null;
        List<String> cookieList = null;
        url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp";
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(new File("src\\main\\resources\\guidance.jks")),
                "secret".toCharArray());
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                        .loadKeyMaterial(keyStore, "secret".toCharArray()).build());
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        HttpHeaders headers = new HttpHeaders();
        // headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.));
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/index.jsp");
        headers.add("Sec-Fetch-Mode", "navigate");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("Sec-Fetch-User", "?1");
        headers.add("Upgrade-Insecure-Requests", "1");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        try {
            //============= Get Menu =====================
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        if (response.getStatusCode().value() == 200) {
            System.out.println("response " + response.toString());
        }
        ;

        // to do  extraire csrf
        //extraction cookies JSESSIONID et BIGipServer
        headers = response.getHeaders();
        cookieList = headers.get("Set-Cookie");
        String requestCookie = "";
        CookieStore cookieStore = new BasicCookieStore();
        for (int i = 0; i < cookieList.size(); i++) {
            String[] cookieAttribute = cookieList.get(i).split(";");
            BasicClientCookie cookie = null;
            for (int iAttribute = 0; iAttribute < cookieAttribute.length; iAttribute++) {

                if (cookieAttribute[iAttribute].contains("=")) {
                    String paramName = cookieAttribute[iAttribute].substring(0, cookieAttribute[iAttribute].indexOf("="));
                    String paramValue = cookieAttribute[iAttribute].substring(cookieAttribute[iAttribute].indexOf("=") + 1, cookieAttribute[iAttribute].length());
                    if (iAttribute == 0) {
                        cookie = new BasicClientCookie(paramName, paramValue); //cookie name + value
                    } else {
                        cookie.setAttribute(paramName, paramValue); //all param followed by = in the cookie ex: Path = "/"
                    }
                }
            }

            cookieStore.addCookie(cookie);
        }

        //Retrieving the cookies
        List<Cookie> listCookies = cookieStore.getCookies();
        requestCookie = "";
        for (Cookie cookie : listCookies) {
            if (cookie.getName().equals("JSESSIONID") || cookie.getName().equals("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS")) {
                requestCookie = requestCookie + cookie.getName() + "=" + cookie.getValue() + ";";
            }
        }

        url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/servlet/LoginCheck";
        String entityData = "csrf=&name=ipco2530&password=Exchange91210&Submit=Envoyer";
        StringEntity entity = new StringEntity(entityData, "UTF8");
        headers = new HttpHeaders();
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        //headers.add("Content-Type" , "application/x-www-form-urlencoded");
        headers.setContentLength(entityData.length());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Cookie", requestCookie);
        headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("Origin", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp");
        headers.add("Sec-Fetch-Mode", "navigate");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("Sec-Fetch-User", "?1");
        headers.add("Upgrade-Insecure-Requests", "1");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

        requestEntity = new HttpEntity<>(entityData, headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        if (response.getStatusCode().value() == 200) {
            System.out.println("toto");
        }

        if (response.getStatusCode().value() == 302) {

            //extraction cookies JSESSIONID et BIGipServer
            headers = response.getHeaders();
            cookieList = headers.get("Set-Cookie");
            requestCookie = "";
            // Update cookieStore
            for (int i = 0; i < cookieList.size(); i++) {
                String[] cookieAttribute = cookieList.get(i).split(";");
                BasicClientCookie cookie = null;
                for (int iAttribute = 0; iAttribute < cookieAttribute.length; iAttribute++) {

                    if (cookieAttribute[iAttribute].contains("=")) {
                        String paramName = cookieAttribute[iAttribute].substring(0, cookieAttribute[iAttribute].indexOf("="));
                        String paramValue = cookieAttribute[iAttribute].substring(cookieAttribute[iAttribute].indexOf("=") + 1, cookieAttribute[iAttribute].length());
                        if (iAttribute == 0) {
                            cookie = new BasicClientCookie(paramName, paramValue); //cookie name + value
                        } else {
                            cookie.setAttribute(paramName, paramValue); //all param followed by = in the cookie ex: Path = "/"
                        }
                    }
                }

                cookieStore.addCookie(cookie);
            }

            //Retrieving the cookies
            listCookies = cookieStore.getCookies();
            requestCookie = "";
            for (Cookie cookie : listCookies) {
                if (cookie.getName().equals("JSESSIONID") || cookie.getName().equals("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS") || cookie.getName().equals("ADRUM_BTa") || cookie.getName().equals("ADRUM_BT1")) {
                    requestCookie = requestCookie + cookie.getName() + "=" + cookie.getValue() + ";";
                }
            }

            //Retrieving the cookies
            listCookies = cookieStore.getCookies();

            requestCookie = "";
            for (Cookie cookie : listCookies) {
                requestCookie = requestCookie + cookie.getName() + "=" + cookie.getValue();
            }

            headers = new HttpHeaders();

            headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            headers.add("Accept-Encoding", "gzip, deflate, br");
            headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.add("Cache-Control", "max-age=0");
            headers.add("Connection", "keep-alive");
            headers.add("Cookie", requestCookie);
            headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
            headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp");
            headers.add("Sec-Fetch-Mode", "navigate");
            headers.add("Sec-Fetch-Site", "same-origin");
            headers.add("Sec-Fetch-User", "?1");
            headers.add("Upgrade-Insecure-Requests", "1");
            headers.add("Origin", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
//              HttpHeaders responseHeaders = new HttpHeaders();
//                responseHeaders = response.getHeaders();
//      //          cookieList.clear();
//                for (int i=0;i>cookieList.size();i++){
//                    cookieList.remove(i);
//                }
//                cookieList = responseHeaders.get("Set-Cookie");
//                requestCookie="";
//                for ( int i=0 ;i < cookieList.size();i++){
//                    if ((cookieList.get(i).contains("JSESSIONID")) ||
//                        (cookieList.get(i).contains("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS"))) {
//                        requestCookie = requestCookie +  cookieList.get(i).substring(0,cookieList.get(i).indexOf(";")+1);}
//                    if (cookieList.get(i).contains("JSESSIONID")) {}
//                }
//                headers.remove("Cookie");
            //               headers.add("Cookie",requestCookie);

            //            List<String> locations = responseHeaders.get("Location"); //A finir
            requestEntity = new HttpEntity<>(headers);
            url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/index.jsp";

            try {
                response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            } catch (Exception e) {
                System.out.println("error " + e.getMessage());
            }

            if (response.getStatusCode().value() == 302) {

                //extraction cookies JSESSIONID et BIGipServer
                headers = response.getHeaders();
                cookieList = headers.get("Set-Cookie");
                requestCookie = "";
                // Update cookieStore
                for (int i = 0; i < cookieList.size(); i++) {
                    String[] cookieAttribute = cookieList.get(i).split(";");
                    BasicClientCookie cookie = null;
                    for (int iAttribute = 0; iAttribute < cookieAttribute.length; iAttribute++) {

                        if (cookieAttribute[iAttribute].contains("=")) {
                            String paramName = cookieAttribute[iAttribute].substring(0, cookieAttribute[iAttribute].indexOf("="));
                            String paramValue = cookieAttribute[iAttribute].substring(cookieAttribute[iAttribute].indexOf("=") + 1, cookieAttribute[iAttribute].length());
                            if (iAttribute == 0) {
                                cookie = new BasicClientCookie(paramName, paramValue); //cookie name + value
                            } else {
                                cookie.setAttribute(paramName, paramValue); //all param followed by = in the cookie ex: Path = "/"
                            }
                        }
                    }

                    cookieStore.addCookie(cookie);
                }

                //Retrieving the cookies
                listCookies = cookieStore.getCookies();
                requestCookie = "";
                for (Cookie cookie : listCookies) {
                    if (cookie.getName().equals("JSESSIONID") || cookie.getName().equals("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS") || cookie.getName().equals("ADRUM_BTa") || cookie.getName().equals("ADRUM_BT1")) {
                        requestCookie = requestCookie + cookie.getName() + "=" + cookie.getValue() + ";";
                    }
                }

                //Retrieving the cookies
                listCookies = cookieStore.getCookies();

                requestCookie = "";
                for (Cookie cookie : listCookies) {
                    requestCookie = requestCookie + cookie.getName() + "=" + cookie.getValue();
                }

                headers = new HttpHeaders();

                headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
                headers.add("Accept-Encoding", "gzip, deflate, br");
                headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
                headers.add("Cache-Control", "max-age=0");
                headers.add("Connection", "keep-alive");
                headers.add("Cookie", requestCookie);
                headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
                headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
                headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp");
                headers.add("Sec-Fetch-Mode", "navigate");
                headers.add("Sec-Fetch-Site", "same-origin");
                headers.add("Sec-Fetch-User", "?1");
                headers.add("Upgrade-Insecure-Requests", "1");
                headers.add("Origin", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
                headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
//              HttpHeaders responseHeaders = new HttpHeaders();
//                responseHeaders = response.getHeaders();
//      //          cookieList.clear();
//                for (int i=0;i>cookieList.size();i++){
//                    cookieList.remove(i);
//                }
//                cookieList = responseHeaders.get("Set-Cookie");
//                requestCookie="";
//                for ( int i=0 ;i < cookieList.size();i++){
//                    if ((cookieList.get(i).contains("JSESSIONID")) ||
//                        (cookieList.get(i).contains("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS"))) {
//                        requestCookie = requestCookie +  cookieList.get(i).substring(0,cookieList.get(i).indexOf(";")+1);}
//                    if (cookieList.get(i).contains("JSESSIONID")) {}
//                }
//                headers.remove("Cookie");
                //               headers.add("Cookie",requestCookie);

                //            List<String> locations = responseHeaders.get("Location"); //A finir
                requestEntity = new HttpEntity<>(headers);
                url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/index.jsp";

                try {
                    response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
                } catch (Exception e) {
                    System.out.println("error " + e.getMessage());
                }

//            //extraction du cookie jsessionid
//            int posJsession = location.lastIndexOf("jsessionid=");
//            jsessionId = location.substring(posJsession);
//            jsessionId = jsessionId.replace("jsessionid","JSESSIONID");
//            responseKM = httpGetListAppli(location);
//            return responseKM;


                return response;

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>("body", headers);
//
//        response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//        return  response;
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//
//        httpPost.setHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/png,*/*;q=0.8,application/signed-exchange;v=b3");
//        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
//        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Mobile Safari/537.36");
//        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
//        httpPost.setHeader("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
//        httpPost.setHeader("Upgrade-Insecure-Requests", "1");
//        httpPost.setHeader("cache-control", "no-cache");
//        httpPost.setHeader("Connection", "keep-alive");
//
//        String entityData = "name=ipco2530&password=Exchange91210";
//        StringEntity entity = new StringEntity(entityData, "UTF8");
//
//        httpPost.setEntity(entity);

//        CloseableHttpResponse
//                response = httpclient.execute(httpPost);
//
//        int statusCode = response.getStatusLine().getStatusCode();
//        String location;
//        String responseKM;
//
//        if (statusCode == 200) {
//            responseKM = EntityUtils.toString(response.getEntity(), "UTF8");
//            return responseKM;
//        }
//        if (statusCode == 302) {
//            Header[] headers = response.getHeaders("Location");
//            location = headers[0].getValue();
//            //extraction du cookie jsessionid
//            int posJsession = location.lastIndexOf("jsessionid=");
//            jsessionId = location.substring(posJsession);
//            jsessionId = jsessionId.replace("jsessionid","JSESSIONID");
//            responseKM = httpGetListAppli(location);
//            return responseKM;
//        }
//        httpclient.close();
//        throw new RuntimeException("Failed with HTTP error code : " + statusCode);

            }

        }
        return response;
    }

    private String httpGetListAppli(String url) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/png,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Mobile Safari/537.36");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("cache-control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");

        CloseableHttpResponse response = httpclient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseKM;
        if (statusCode == 200) {
            responseKM = EntityUtils.toString(response.getEntity(), "UTF8");
            httpclient.close();
            return responseKM;
        }
        throw new RuntimeException("Failed with HTTP error code : " + statusCode);
    }

    private String httpGetListContent(String url) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/png,*/*;q=0.8,application/signed-exchange;v=b3");
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Mobile Safari/537.36");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("cache-control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "KM_INFO=&id:27e3fz5ggta; " + jsessionId);

        CloseableHttpResponse response = httpclient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        String responseKM;
        if (statusCode == 200) {
            responseKM = EntityUtils.toString(response.getEntity(), "UTF8");
            httpclient.close();
            return responseKM;
        }
        throw new RuntimeException("Failed with HTTP error code : " + statusCode);
    }
}

//Example stack overflow
//            RestTemplate template = new RestTemplate();
//            CreateObjectInput payload = new CreateObjectInput();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<CreateObjectOutput> requestEntity =
//                    new HttpEntity<>(payload, headers);
//            CreateObjectOutput response =
//                    template.exchange("url", HttpMethod.POST, requestEntity,
//                            CreateObjectOutput.class);