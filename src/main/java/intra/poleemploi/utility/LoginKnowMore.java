package intra.poleemploi.utility;

import intra.poleemploi.utility.writingDatabaseElement.CertificatAndCookiesManagement;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class LoginKnowMore {

    public ResponseEntity<String> httpsGetLoginMenu() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        ResponseEntity<String> response;
        CertificatAndCookiesManagement certificatAndCookiesManagement = new CertificatAndCookiesManagement();

        String url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp";
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

        RestTemplate restTemplate = new RestTemplate(certificatAndCookiesManagement.getCertficatHttps());

        try {
            //============= Get Menu =====================
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            if (response.getStatusCode().value() == 200) {
                certificatAndCookiesManagement.addingCookiesInCookiesStore(response); //ajout des cookies dans le cookies store
                System.out.println("response " + response.toString());

                return response;
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        return null;
    }

    public ResponseEntity<String> httpPostLoginCheck() throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {
        ResponseEntity<String> response = null;
        CertificatAndCookiesManagement certificatAndCookiesManagement = new CertificatAndCookiesManagement();

        HttpHeaders headers;
        HttpEntity<String> requestEntity;
        RestTemplate restTemplate = new RestTemplate(certificatAndCookiesManagement.getCertficatHttps());

        String url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/servlet/LoginCheck";
        // CertificatAndCookiesManagement certificatAndCookiesManagement = new CertificatAndCookiesManagement();
        certificatAndCookiesManagement.loginCheckRequestedCookies();
        String entityData = "csrf=&name=ipco2530&password=Exchange91210&Submit=Envoyer";
        //    StringEntity entity = new StringEntity(entityData, "UTF8");
        headers = new HttpHeaders();
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        //headers.add("Content-Type" , "application/x-www-form-urlencoded");
        headers.setContentLength(entityData.length());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Cookie", certificatAndCookiesManagement.loginCheckRequestedCookies().toString());
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
        if (response != null) {
            certificatAndCookiesManagement.addingCookiesInCookiesStore(response); //ajout des cookies dans le cookies store
            if (response.getStatusCode().value() == 200) {
                System.out.println("tout va bien normalement on passe pas là");
                return response;
            }
            if (response.getStatusCode().value() == 302) {
                //gérer la redirection au lieu de la faire dans writingdatabaseelement
                return response;
            }
        }
        return  null;
    }

    public ResponseEntity<String> httpGetIndexApplications() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        ResponseEntity<String> response;
        CertificatAndCookiesManagement certificatAndCookiesManagement = new CertificatAndCookiesManagement();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        headers.add("Cookie", certificatAndCookiesManagement.loginCheckRequestedCookies().toString());
        headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp");
        headers.add("Sec-Fetch-Mode", "navigate");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("Sec-Fetch-User", "?1");
        headers.add("Upgrade-Insecure-Requests", "1");
        headers.add("Origin", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

        HttpEntity<String> requestEntity;
        RestTemplate restTemplate = new RestTemplate(certificatAndCookiesManagement.getCertficatHttps());

        // List<String> locations = responseHeaders.get("Location"); //A finir
        requestEntity = new HttpEntity<>(headers);
        String url = "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/index.jsp";

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            if (response.getStatusCodeValue() == 200) {
                certificatAndCookiesManagement.addingCookiesInCookiesStore(response); //ajout des cookies dans le cookies store
                return response;
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        return null;
    }

    public ResponseEntity<String> httpGetListContent(String url) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        ResponseEntity<String> response;
        CertificatAndCookiesManagement certificatAndCookiesManagement = new CertificatAndCookiesManagement();

        HttpHeaders headers;
        headers = new HttpHeaders();

        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
    //  headers.add("Cache-Control", "max-age=0");
    //  headers.add("Connection", "keep-alive");
        headers.add("Cookie", certificatAndCookiesManagement.contentsRequestedCookies().toString());
        headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/admin/statistic/?applicationId=AGC");
        headers.add("Sec-Fetch-Mode", "navigate");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("Sec-Fetch-User", "?1");
        headers.add("Upgrade-Insecure-Requests", "1");
    //  headers.add("Origin", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate(certificatAndCookiesManagement.getCertficatHttps());

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
                if (response.getStatusCodeValue() == 200) {
                    certificatAndCookiesManagement.addingCookiesInCookiesStore(response); //ajout des cookies dans le cookies store
                    return response;
                }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        return null;
    }

        public ResponseEntity<String> httpGetListStatistics(String url) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

            ResponseEntity<String> response;
            CertificatAndCookiesManagement certificatAndCookiesManagement = new CertificatAndCookiesManagement();

            new HttpHeaders();
            HttpHeaders headers = new HttpHeaders();

            headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            headers.add("Accept-Encoding", "gzip, deflate, br");
            headers.add("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
            headers.add("Cache-Control", "max-age=0");
            headers.add("Connection", "keep-alive");
            headers.add("Cookie", certificatAndCookiesManagement.loginCheckRequestedCookies().toString());
            headers.add("Host", "pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
            headers.add("Referer", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra/pr051-guidanceapplicative/login.jsp");
            headers.add("Sec-Fetch-Mode", "navigate");
            headers.add("Sec-Fetch-Site", "same-origin");
            headers.add("Sec-Fetch-User", "?1");
            headers.add("Upgrade-Insecure-Requests", "1");
            headers.add("Origin", "https://pr051-vipa-a0rxpr.sii24.pole-emploi.intra");
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate(certificatAndCookiesManagement.getCertficatHttps());

            try {
                response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
                    if (response.getStatusCodeValue() == 200) {
                        certificatAndCookiesManagement.addingCookiesInCookiesStore(response); //ajout des cookies dans le cookies store
                        return response;
                    }
            } catch (Exception e) {
                System.out.println("error " + e.getMessage());
            }
            return null;
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
