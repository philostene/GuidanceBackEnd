package intra.poleemploi.utility.writingDatabaseElement;

import intra.poleemploi.constants.Consts;
import intra.poleemploi.dto.ApplicationDto;
import intra.poleemploi.dto.ContentDto;
import intra.poleemploi.repository.AppliRepository;
import intra.poleemploi.repository.ContentRepository;
import intra.poleemploi.entities.*;
import intra.poleemploi.repository.StatistiquesParJourRepository;
import intra.poleemploi.utility.LoginKnowMore;
import intra.poleemploi.utility.ReadHtmlTable;
import intra.poleemploi.utility.datebaseManagement.FromKMDatabaseIntoLocalDatabase;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WritingDatabaseElement {

    public void writingApplications(AppliRepository appliRepository) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        ApplicationDto[] applications = null;
        ResponseEntity<String> response;
        //ouverture de la base Knowlegde à écrire d'abord dans le package hhtps

        //vérification si connecté

        //Récupération des applcations dans la base KM
        LoginKnowMore loginKnowMore = new LoginKnowMore();
        response = loginKnowMore.httpsGetLoginMenu(); //call loginMenu
        if (response != null) {
            if (response.getStatusCode().value() == 200) {
                response = loginKnowMore.httpPostLoginCheck();
                if (response != null) {
                    if (response.getStatusCode().value() == 200) {
                        response = loginKnowMore.httpGetIndexApplications();
                        if (response != null) {
                            if (response.getStatusCode().value() == 200) {
                                ReadHtmlTable readHtmlTable = new ReadHtmlTable();
                                applications = readHtmlTable.getApplicationsListFromHTML(response.getBody());
                            } else {
                                System.out.println("status code call httpPostLoginCheck " + response.getStatusCode().value());
                            }
                        } else {
                            System.out.println("status code call httpPostLoginCheck null à voir peut être pas sur le bon  réseau ");
                        }
                    }
                } else {
                    System.out.println("status code call httpPostLoginCheck null à voir peut être pas sur le bon  réseau ");
                }
            } else {
                System.out.println("status code call httpPostLoginCheck " + response.getStatusCode().value());
            }
            // Récupération des données dans la page html
            if (response.getStatusCodeValue() == 302) {
                response = loginKnowMore.httpGetIndexApplications();
                if (response != null) {
                    if (response.getStatusCode().value() == 200) {
                        ReadHtmlTable readHtmlTable = new ReadHtmlTable();
                        applications = readHtmlTable.getApplicationsListFromHTML(response.getBody());
                    } else {
                        System.out.println("status code call httpPostLoginCheck " + response.getStatusCode().value());
                    }
                } else {
                    System.out.println("status code call httpPostLoginCheck null à voir peut être pas sur le bon  réseau ");
                }
            }

        }
        //Writing applications into database
        FromKMDatabaseIntoLocalDatabase fromKMDatabaseIntoLocalDatabase = new FromKMDatabaseIntoLocalDatabase();
        fromKMDatabaseIntoLocalDatabase.writeApplications(appliRepository, applications);
    }

    public void writingContents(ContentRepository contentRepository, AppliRepository appliRepository) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        LoginKnowMore loginKnowMore = new LoginKnowMore();
        ResponseEntity<String> response;
        List<ContentDto> listcontentDtoToBeSaved = new ArrayList<>();
        //test de connexion
        if (loginCheck()) {
            //Récupération des applcations dans la base KM
            String contentURL;
            String contentsBaseURL = Consts.URLBASEFORCONTENTSDETAILS;
            List<Appli> listAppli = appliRepository.findAll();

            for (Appli appli : listAppli) {
                ReadHtmlTable readHtmlTable = new ReadHtmlTable();
                contentURL = contentsBaseURL + appli.getIdAppliKM();
                try {
                    response = loginKnowMore.httpGetListContent(contentURL);
                    if (response != null && response.getStatusCodeValue() == 200) {
                        if (checkIfContentExist(response.getBody())) {
                            ContentDto[] listContentDto = readHtmlTable.getContentsListFromHTML(response.getBody().toString(), appli);
                            listcontentDtoToBeSaved.addAll(Arrays.asList(listContentDto));
                            System.out.println("guidage pour appli " + appli.getAppliName());
                        } else {
                            System.out.println("pas de guidage " + appli.getAppliName());
                        }

                    } else {
                        System.out.println("error sur writingContents httpGetListContent réponse nulle");
                    }
                } catch (Exception e) {
                    System.out.println("LoginKnowmore.listContents error  " + e.getMessage());
                }
            }
            //Writing contents into database
            FromKMDatabaseIntoLocalDatabase fromKMDatabaseIntoLocalDatabase = new FromKMDatabaseIntoLocalDatabase();
            fromKMDatabaseIntoLocalDatabase.writeContents(contentRepository, listcontentDtoToBeSaved);
        } else {
            System.out.println("error sur writingContents  à priori on arrive pas à se connecter");
        }
    }

    public List<StatistiquesParJour> writingStatistics(ContentRepository contentRepository, StatistiquesParJourRepository statistiquesParJourRepository) throws IOException, InterruptedException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        int statisticsCounter = 0;
        List<Content> listContents = contentRepository.findAll();
        List<StatistiquesParJour> listSatisticsParJourReturned = new ArrayList<>();
        LoginKnowMore loginKnowMore = new LoginKnowMore();
        String statisticsURL;
        ResponseEntity<String> response;
        if (loginCheck()) {
            for (Content content : listContents) {
                ReadHtmlTable readHtmlTable = new ReadHtmlTable();
                statisticsURL = content.getContentURL();
                statisticsURL = statisticsURL.replaceAll("//", "/");
                int ptrL = statisticsURL.indexOf("pubId=");
                statisticsURL = statisticsURL.substring(ptrL + 6);
                statisticsURL = Consts.URLBASEFORSTATISTICSDETAILS + statisticsURL;
                try {
                    response = loginKnowMore.httpGetListStatistics(statisticsURL);
                    if (response != null && response.getStatusCodeValue() == 200) {
                        if (checkIfStatisticsExist(response.getBody())) {
                            List<StatistiquesParJour> listStatistiquesParJour = readHtmlTable.getStatisticsList(response.getBody(), content);
                            listSatisticsParJourReturned.addAll(listStatistiquesParJour);
                            statisticsCounter++;
                            System.out.println("counter stats= "+ statisticsCounter + " guidage pour appli " + content.getContentName());
                        } else {
                            System.out.println("pas de guidage " + content.getContentName());
                        }
                    } else {
                        System.out.println("error sur writingContents httpGetListContent réponse nulle");
                    }
                } catch (Exception e) {
                    System.out.println(" LoginKnowmore.listContents error  " + e.getMessage());
                }
                Thread.sleep(10); // pour éviter de saturer le serveur
            }
            //Writing contents into database
            FromKMDatabaseIntoLocalDatabase fromKMDatabaseIntoLocalDatabase = new FromKMDatabaseIntoLocalDatabase();
            fromKMDatabaseIntoLocalDatabase.writeStatistics(statistiquesParJourRepository, listSatisticsParJourReturned);
            return listSatisticsParJourReturned;
        } else {
            System.out.println("error sur writingContents  à priori on arrive pas à se connecter");
        }
        return null;
    }

    public boolean loginCheck() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        LoginKnowMore loginKnowMore = new LoginKnowMore();
        ResponseEntity response;
        response = loginKnowMore.httpGetIndexApplications();  //test si connected
        if (response.getStatusCodeValue() == 200) {   //not connected
            String notConnectedResponse = response.getBody().toString();
            if (notConnectedResponse.contains("<input type=\"text\" name=\"name\" autofocus placeholder=\"Login\">")) {
                response = loginKnowMore.httpsGetLoginMenu();
                if (response.getStatusCode().value() == 200) {
                    response = loginKnowMore.httpPostLoginCheck();
                    if (response != null) {
                        if (response.getStatusCode().value() == 200) {
                            return true; //response = loginKnowMore.httpGetIndexApplications();
                        }
                    } else {
                        System.out.println("status code call httpPostLoginCheck null à voir peut être pas sur le bon  réseau ");
                    }
                } else {
                    System.out.println("status code call httpPostLoginCheck " + response.getStatusCode().value());
                }
                if (response.getStatusCodeValue() == 302) {
                    response = loginKnowMore.httpGetIndexApplications();
                    if (response != null) {
                        if (response.getStatusCode().value() == 200) {
                            return true;
                        } else {
                            System.out.println("status code call httpPostLoginCheck " + response.getStatusCode().value());
                        }
                    } else {
                        System.out.println("status code call httpPostLoginCheck null à voir peut être pas sur le bon  réseau ");
                    }
                }
            }
        }
        return false;
    }

    private boolean checkIfContentExist(String contentStringToCheck) {
        String criteria1 = "var publicationItems = JSON.parse(\"";
        String criteria2 = "\");";
        int ptrDebut = contentStringToCheck.indexOf(criteria1);
        int ptrFin = contentStringToCheck.indexOf(criteria2, ptrDebut);
        contentStringToCheck = contentStringToCheck.substring(ptrDebut + criteria1.length(), ptrFin);
        if (contentStringToCheck.length() > 3) {
            return true;  // contains some contents
        }
        return false; // no content found
    }

    private boolean checkIfStatisticsExist(String contentStringToCheck) {
        String criteria1 = "<table class=\"table\"";
        String criteria2 = "</tr>";  //at least one row (the end of first row)
        int ptrDebut = contentStringToCheck.indexOf(criteria1);
        int ptrFin = contentStringToCheck.indexOf(criteria2, ptrDebut);
        contentStringToCheck = contentStringToCheck.substring(ptrDebut + criteria1.length(), ptrFin);
        if (contentStringToCheck.length() > 3) {
            return true;  // contains one row
        }
        return false; // no row found
    }

}

