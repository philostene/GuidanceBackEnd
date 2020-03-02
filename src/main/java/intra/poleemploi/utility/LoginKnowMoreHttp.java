package intra.poleemploi.utility;

import intra.poleemploi.constants.Consts;
import intra.poleemploi.entities.Content;
import intra.poleemploi.entities.StatistiquesParJour;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginKnowMoreHttp {

    private static String jsessionId;

//    public List<Appli> listAppli() throws IOException {
//        String url = Consts.URLBASELOGINKM;
//        ReadHtmlTable readHtmlTable = new ReadHtmlTable();
//        String response = httpPostListAppli(url);
//        return readHtmlTable.getAppliList(response);
//    }


//    public List<Content> listContents(List<Appli> listAppli) throws IOException {
//
//        List<Content> listcontentToBeReturned = new ArrayList<>();
//
//        String statisticBaseURL = Consts.URLBASEFORCONTENTSDETAILS;
//        String statisticURL;
//        String response;
//
//        for (Appli appli : listAppli) {
//            ReadHtmlTable readHtmlTable = new ReadHtmlTable();
//            statisticURL = statisticBaseURL + appli.getIdAppliKM();
//            response = httpGetListContent(statisticURL);
//            List<Content> listContents = readHtmlTable.getContentsList(response,appli);
//            try {
//                listcontentToBeReturned.addAll(listContents);
//            }
//            catch ( Exception e) {
//                System.out.println(" LoginKnowmore.listContents error  " + e.getMessage());
//            }
//        }
//        return listcontentToBeReturned;
//    }
    
    public List<StatistiquesParJour> listStatistics(List <Content> listContents) throws IOException, InterruptedException {
        List<StatistiquesParJour> listSatisticsParJourReturned = new ArrayList<>();
      //  String statisticBaseURL = Consts.URLBASEFORSTATISTICSDETAILS;
        String statisticsURL;
        String response;
        for (Content content : listContents){
            ReadHtmlTable readHtmlTable = new ReadHtmlTable();
            statisticsURL = content.getContentURL();
            response = httpGetListStatistics(statisticsURL);
            if (response.contains("Vous n'êtes pas identifié")) {   //Affichage de la mire d identification si jamais
                String url = Consts.URLBASELOGINKM;
                String responseLogin = httpPostListAppli(url);
                response = httpGetListStatistics(statisticsURL);
            }
            List<StatistiquesParJour> listStatistiquesParJour= readHtmlTable.getStatisticsList(response, content);
            try {
                listSatisticsParJourReturned.addAll(listStatistiquesParJour);
            }
            catch ( Exception e) {
                System.out.println(" LoginKnowmore.listContents error  " + e.getMessage());
            }
            Thread.sleep(100); // pour éviter de saturer le serveur
        }
        return listSatisticsParJourReturned;
    }

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

    private String httpPostListAppli(String url) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/png,*/*;q=0.8,application/signed-exchange;v=b3");
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Mobile Safari/537.36");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        httpPost.setHeader("Upgrade-Insecure-Requests", "1");
        httpPost.setHeader("cache-control", "no-cache");
        httpPost.setHeader("Connection", "keep-alive");

        String entityData = "name=ipco2530&password=Exchange91210";
        StringEntity entity = new StringEntity(entityData, "UTF8");

        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        String location;
        String responseKM;

        if (statusCode == 200) {
            responseKM = EntityUtils.toString(response.getEntity(), "UTF8");
            return responseKM;
        }
        if (statusCode == 302) {
            Header[] headers = response.getHeaders("Location");
            location = headers[0].getValue();
            //extraction du cookie jsessionid
            int posJsession = location.lastIndexOf("jsessionid=");
            jsessionId = location.substring(posJsession);
            jsessionId = jsessionId.replace("jsessionid","JSESSIONID");
            responseKM = httpGetListAppli(location);
            return responseKM;
        }
        httpclient.close();
        throw new RuntimeException("Failed with HTTP error code : " + statusCode);
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
