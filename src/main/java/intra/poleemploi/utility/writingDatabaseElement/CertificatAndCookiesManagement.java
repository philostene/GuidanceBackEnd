package intra.poleemploi.utility.writingDatabaseElement;


import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.List;

public class CertificatAndCookiesManagement {

    public static CookieStore cookieStore = new BasicCookieStore();

    public void addingCookiesInCookiesStore(ResponseEntity<String> response) {
        HttpHeaders headers = new HttpHeaders();
        headers = response.getHeaders();
        List<String> cookieList = headers.get("Set-Cookie");
        StringBuilder requestCookie;
  //      CookieStore cookieStore = new BasicCookieStore();
        for (int i = 0; i < cookieList.size(); i++) {
            String[] cookieAttribute = cookieList.get(i).split(";");
            BasicClientCookie cookie = null;
            for (int iAttribute = 0; iAttribute < cookieAttribute.length; iAttribute++) {

                if (cookieAttribute[iAttribute].contains("=")) {
                    String paramName = cookieAttribute[iAttribute].substring(0, cookieAttribute[iAttribute].indexOf("="));
                    String paramValue = cookieAttribute[iAttribute].substring(cookieAttribute[iAttribute].indexOf("=") + 1);
                    if (iAttribute == 0) {
                        cookie = new BasicClientCookie(paramName, paramValue); //cookie name + value
                    } else {
                        cookie.setAttribute(paramName, paramValue); //all param followed by = in the cookie ex: Path = "/"
                    }
                }
            }
            cookieStore.addCookie(cookie);
        }
    }

    public StringBuilder loginCheckRequestedCookies(){
        StringBuilder requestCookie;
        List<Cookie> listCookies = cookieStore.getCookies();
        requestCookie = new StringBuilder();
        for (Cookie cookie : listCookies) {
            if (cookie.getName().equals("JSESSIONID") || cookie.getName().equals("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS")) {
                requestCookie.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
            }
        }
        return requestCookie;
    }

    public StringBuilder indexRequestedCookies(CookieStore cookieStore){
        StringBuilder requestCookie;
        List<Cookie> listCookies = cookieStore.getCookies();
        requestCookie = new StringBuilder();
        for (Cookie cookie : listCookies) {
            if (cookie.getName().equals("JSESSIONID") || cookie.getName().equals("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS")) {
                requestCookie.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
            }
        }
        return requestCookie;
    }

    public StringBuilder contentsRequestedCookies(){
        StringBuilder requestCookie;
        List<Cookie> listCookies = cookieStore.getCookies();
        requestCookie = new StringBuilder();
        for (Cookie cookie : listCookies) {
            if (cookie.getName().equals("JSESSIONID")
                    || cookie.getName().equals("BIGipServerVS_PR051-VIPA-A0RXPR_HTTPS.app~POOL_PR051-VIPA-A0RXPR_HTTPS")
                    || cookie.getName().equals("ADRUM_BTa")
                    || cookie.getName().equals("ADRUM_BT1")) {
                requestCookie.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
            }
        }
        return requestCookie;
    }

    public ClientHttpRequestFactory getCertficatHttps() throws KeyStoreException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileInputStream(new File("src\\main\\resources\\guidance.jks")),
                "secret".toCharArray());
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                        .loadKeyMaterial(keyStore, "secret".toCharArray()).build());
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return requestFactory;
    }
}
