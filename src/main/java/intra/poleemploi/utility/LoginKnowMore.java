package intra.poleemploi.utility;

import intra.poleemploi.entities.Appli;
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

public class LoginKnowMore {

    // http://pr051-gfpe-3upxjf0.sip91.pole-emploi.intra:22391/know/login.jsp    //prod
    // http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/index.jsp    //recette

    private static String jsessionId;

//    public String getJsessionId() {
//        return jsessionId;
//    }

    public List<Appli> listAppli() throws IOException {
        String url = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/servlet/LoginCheck";
        ReadHtmlTable readHtmlTable = new ReadHtmlTable();
        String response = httpPostListAppi(url);
        return readHtmlTable.getAppliList(response);
    }


    public List<Content> listContents(List<Appli> listAppli) throws IOException {

        List<Content> listcontentToBeReturned = new ArrayList<>();

       // String url = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/servlet/LoginCheck";
        String statisticBaseURL = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/admin/statistic/?applicationId=";
        String statisticURL;

      //  String location = null;
        String response;

        for (Appli appli : listAppli) {
            ReadHtmlTable readHtmlTable = new ReadHtmlTable();
            statisticURL = statisticBaseURL + appli.getIdAppliKM();
            response = httpGetListContent(statisticURL);
            List<Content> listContents = readHtmlTable.getContentsList(response,appli);
            try {
                listcontentToBeReturned.addAll(listContents);
            }
            catch ( Exception e) {
                System.out.println(" LoginKnowmore.listContents error  " + e.getMessage());
            }
        }
        return listcontentToBeReturned;
    }

    List<StatistiquesParJour> listStatistiquesParJour(List<Content> listContents){
        List<StatistiquesParJour> listStatistiquesParJourReturned = new ArrayList<>();
        String statisticPerDayBaseURL = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/view/statistics/publicationStatistics?pubId="; //535&fromDate=27%2f10%2f2019&toDate=18%2f11%2f2019"

        return listStatistiquesParJourReturned;
    }

    private String httpPostListAppi(String url) throws IOException {

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
