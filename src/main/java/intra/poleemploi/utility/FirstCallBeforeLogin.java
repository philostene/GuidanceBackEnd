//package intra.poleemploi.utility;
//
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//
//public class FirstCallBeforeLogin {
//
//    public void get() {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet httpget = new HttpGet("http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/know/index.jsp");
//
//        try {
//            CloseableHttpResponse response = httpclient.execute(httpget);
//            String responseJSON = EntityUtils.toString(response.getEntity(), "UTF8");
//            response.close();
//            System.out.println(responseJSON);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}

