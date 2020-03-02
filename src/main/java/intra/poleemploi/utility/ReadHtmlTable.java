package intra.poleemploi.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intra.poleemploi.constants.ErrorMessage;
import intra.poleemploi.dto.ApplicationDto;
import intra.poleemploi.dto.ContentDto;
import intra.poleemploi.entities.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadHtmlTable {

    private String applicationsLM = "var applications = JSON.parse(\"[{\\\"id\\\":\\\"appliSimulator\\\",\\\"name\\\":\\\"Bac \\u00E0 sable\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\/know\\/test\\/appliSimulator\\/\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=appliSimulator\\\"},{\\\"id\\\":\\\"pn119\\\",\\\"name\\\":\\\"pn119\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn119\\\"},{\\\"id\\\":\\\"everestfab\\\",\\\"name\\\":\\\"everestfab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=everestfab\\\"},{\\\"id\\\":\\\"hugov4fab\\\",\\\"name\\\":\\\"hugov4fab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=hugov4fab\\\"},{\\\"id\\\":\\\"proactivite\\\",\\\"name\\\":\\\"proactivite\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=proactivite\\\"},{\\\"id\\\":\\\"dunev4fab\\\",\\\"name\\\":\\\"dunev4fab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=dunev4fab\\\"},{\\\"id\\\":\\\"pn039\\\",\\\"name\\\":\\\"MAP Diagnostic et actions\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn039\\\"},{\\\"id\\\":\\\"pn007\\\",\\\"name\\\":\\\"BPM\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn007\\\"},{\\\"id\\\":\\\"pn059\\\",\\\"name\\\":\\\"BEN Pe.fr profil Professionnel\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn059\\\"},{\\\"id\\\":\\\"pn058\\\",\\\"name\\\":\\\"Mon Potentiel Professionnel\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn058\\\"},{\\\"id\\\":\\\"mrs\\\",\\\"name\\\":\\\"MRS Digitale\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=mrs\\\"},{\\\"id\\\":\\\"AUDEP\\\",\\\"name\\\":\\\"AUDE Presta\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=AUDEP\\\"},{\\\"id\\\":\\\"pn040\\\",\\\"name\\\":\\\"BEN PE.fr Inscription\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn040\\\"},{\\\"id\\\":\\\"pn140\\\",\\\"name\\\":\\\"Recherche de Profil Conseiller\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn140\\\"},{\\\"id\\\":\\\"pn063\\\",\\\"name\\\":\\\"MAP Vue DE\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn063\\\"},{\\\"id\\\":\\\"MESI\\\",\\\"name\\\":\\\"MESI\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=MESI\\\"},{\\\"id\\\":\\\"pn103\\\",\\\"name\\\":\\\"Pilotage par les r\\u00E9sultats\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn103\\\"},{\\\"id\\\":\\\"pn111\\\",\\\"name\\\":\\\"Widg'E\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn111\\\"},{\\\"id\\\":\\\"PEL\\\",\\\"name\\\":\\\"PEL\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=PEL\\\"},{\\\"id\\\":\\\"PR056\\\",\\\"name\\\":\\\"GID Partenaire\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=PR056\\\"},{\\\"id\\\":\\\"pn060\\\",\\\"name\\\":\\\"BEN PE.fr Recruteur\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn060\\\"},{\\\"id\\\":\\\"pn124\\\",\\\"name\\\":\\\"Portail des Activit\\u00E9s Agence\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn124\\\"},{\\\"id\\\":\\\"pn116\\\",\\\"name\\\":\\\"JRE Conseiller\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn116\\\"},{\\\"id\\\":\\\"po002\\\",\\\"name\\\":\\\"BEN Recruteur\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=po002\\\"},{\\\"id\\\":\\\"onearagon\\\",\\\"name\\\":\\\"WePlan\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=onearagon\\\"},{\\\"id\\\":\\\"pn098\\\",\\\"name\\\":\\\"MAP ---\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn098\\\"},{\\\"id\\\":\\\"pn068\\\",\\\"name\\\":\\\"OuiForm\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn068\\\"},{\\\"id\\\":\\\"pn106\\\",\\\"name\\\":\\\"Panda\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn106\\\"},{\\\"id\\\":\\\"pn133\\\",\\\"name\\\":\\\"Vers un Metier\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn133\\\"},{\\\"id\\\":\\\"AUDEV4\\\",\\\"name\\\":\\\"AUDE V4\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=AUDEV4\\\"},{\\\"id\\\":\\\"so017\\\",\\\"name\\\":\\\"BEN PE.fr Connexion Candidat\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=so017\\\"},{\\\"id\\\":\\\"pn087\\\",\\\"name\\\":\\\"NG AGEPI\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn087\\\"},{\\\"id\\\":\\\"eurecafab\\\",\\\"name\\\":\\\"eurecafab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=eurecafab\\\"},{\\\"id\\\":\\\"PRIOP\\\",\\\"name\\\":\\\"IOP\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=PRIOP\\\"},{\\\"id\\\":\\\"AGC\\\",\\\"name\\\":\\\"AGC\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=AGC\\\"},{\\\"id\\\":\\\"kairosfab\\\",\\\"name\\\":\\\"kairosfab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=kairosfab\\\"},{\\\"id\\\":\\\"pn069\\\",\\\"name\\\":\\\"MAP Recherche\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn069\\\"},{\\\"id\\\":\\\"so007\\\",\\\"name\\\":\\\"BEN Authentification E\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=so007\\\"},{\\\"id\\\":\\\"pn066\\\",\\\"name\\\":\\\"Profil de Comp\\u00E9tences\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn066\\\"},{\\\"id\\\":\\\"pn105\\\",\\\"name\\\":\\\"BEN PE.fr aides pn105\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn105\\\"},{\\\"id\\\":\\\"pn073\\\",\\\"name\\\":\\\"BEN PE.fr Espace Connexion\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn073\\\"},{\\\"id\\\":\\\"pn104\\\",\\\"name\\\":\\\"BEN Aide E\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn104\\\"},{\\\"id\\\":\\\"pn052\\\",\\\"name\\\":\\\"BEN PE.fr Formation pn052\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn052\\\"}]\");\n";
    private String testJson = "(\"[{\\\"id\\\":\\\"appliSimulator\\\",\\\"name\\\":\\\"Bac \\u00E0 sable\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\/know\\/test\\/appliSimulator\\/\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=appliSimulator\\\"},{\\\"id\\\":\\\"pn119\\\",\\\"name\\\":\\\"pn119\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn119\\\"},{\\\"id\\\":\\\"everestfab\\\",\\\"name\\\":\\\"everestfab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=everestfab\\\"},{\\\"id\\\":\\\"hugov4fab\\\",\\\"name\\\":\\\"hugov4fab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=hugov4fab\\\"},{\\\"id\\\":\\\"proactivite\\\",\\\"name\\\":\\\"proactivite\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=proactivite\\\"},{\\\"id\\\":\\\"dunev4fab\\\",\\\"name\\\":\\\"dunev4fab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=dunev4fab\\\"},{\\\"id\\\":\\\"pn039\\\",\\\"name\\\":\\\"MAP Diagnostic et actions\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn039\\\"},{\\\"id\\\":\\\"pn007\\\",\\\"name\\\":\\\"BPM\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn007\\\"},{\\\"id\\\":\\\"pn059\\\",\\\"name\\\":\\\"BEN Pe.fr profil Professionnel\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn059\\\"},{\\\"id\\\":\\\"pn058\\\",\\\"name\\\":\\\"Mon Potentiel Professionnel\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn058\\\"},{\\\"id\\\":\\\"mrs\\\",\\\"name\\\":\\\"MRS Digitale\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=mrs\\\"},{\\\"id\\\":\\\"AUDEP\\\",\\\"name\\\":\\\"AUDE Presta\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=AUDEP\\\"},{\\\"id\\\":\\\"pn040\\\",\\\"name\\\":\\\"BEN PE.fr Inscription\\\",\\\"icon\\\":\\\"\\\",\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn040\\\"},{\\\"id\\\":\\\"pn140\\\",\\\"name\\\":\\\"Recherche de Profil Conseiller\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn140\\\"},{\\\"id\\\":\\\"pn063\\\",\\\"name\\\":\\\"MAP Vue DE\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn063\\\"},{\\\"id\\\":\\\"MESI\\\",\\\"name\\\":\\\"MESI\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=MESI\\\"},{\\\"id\\\":\\\"pn103\\\",\\\"name\\\":\\\"Pilotage par les r\\u00E9sultats\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn103\\\"},{\\\"id\\\":\\\"pn111\\\",\\\"name\\\":\\\"Widg'E\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn111\\\"},{\\\"id\\\":\\\"PEL\\\",\\\"name\\\":\\\"PEL\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=PEL\\\"},{\\\"id\\\":\\\"PR056\\\",\\\"name\\\":\\\"GID Partenaire\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=PR056\\\"},{\\\"id\\\":\\\"pn060\\\",\\\"name\\\":\\\"BEN PE.fr Recruteur\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn060\\\"},{\\\"id\\\":\\\"pn124\\\",\\\"name\\\":\\\"Portail des Activit\\u00E9s Agence\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn124\\\"},{\\\"id\\\":\\\"pn116\\\",\\\"name\\\":\\\"JRE Conseiller\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn116\\\"},{\\\"id\\\":\\\"po002\\\",\\\"name\\\":\\\"BEN Recruteur\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=po002\\\"},{\\\"id\\\":\\\"onearagon\\\",\\\"name\\\":\\\"WePlan\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=onearagon\\\"},{\\\"id\\\":\\\"pn098\\\",\\\"name\\\":\\\"MAP ---\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn098\\\"},{\\\"id\\\":\\\"pn068\\\",\\\"name\\\":\\\"OuiForm\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn068\\\"},{\\\"id\\\":\\\"pn106\\\",\\\"name\\\":\\\"Panda\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn106\\\"},{\\\"id\\\":\\\"pn133\\\",\\\"name\\\":\\\"Vers un Metier\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn133\\\"},{\\\"id\\\":\\\"AUDEV4\\\",\\\"name\\\":\\\"AUDE V4\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=AUDEV4\\\"},{\\\"id\\\":\\\"so017\\\",\\\"name\\\":\\\"BEN PE.fr Connexion Candidat\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=so017\\\"},{\\\"id\\\":\\\"pn087\\\",\\\"name\\\":\\\"NG AGEPI\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn087\\\"},{\\\"id\\\":\\\"eurecafab\\\",\\\"name\\\":\\\"eurecafab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=eurecafab\\\"},{\\\"id\\\":\\\"PRIOP\\\",\\\"name\\\":\\\"IOP\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=PRIOP\\\"},{\\\"id\\\":\\\"AGC\\\",\\\"name\\\":\\\"AGC\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=AGC\\\"},{\\\"id\\\":\\\"kairosfab\\\",\\\"name\\\":\\\"kairosfab\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=kairosfab\\\"},{\\\"id\\\":\\\"pn069\\\",\\\"name\\\":\\\"MAP Recherche\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn069\\\"},{\\\"id\\\":\\\"so007\\\",\\\"name\\\":\\\"BEN Authentification E\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=so007\\\"},{\\\"id\\\":\\\"pn066\\\",\\\"name\\\":\\\"Profil de Comp\\u00E9tences\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn066\\\"},{\\\"id\\\":\\\"pn105\\\",\\\"name\\\":\\\"BEN PE.fr aides pn105\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn105\\\"},{\\\"id\\\":\\\"pn073\\\",\\\"name\\\":\\\"BEN PE.fr Espace Connexion\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn073\\\"},{\\\"id\\\":\\\"pn104\\\",\\\"name\\\":\\\"BEN Aide E\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn104\\\"},{\\\"id\\\":\\\"pn052\\\",\\\"name\\\":\\\"BEN PE.fr Formation pn052\\\",\\\"icon\\\":null,\\\"applicationUrl\\\":\\\"\\\",\\\"url\\\":\\\"\\/view\\/publication\\/batches?applicationId=pn052\\\"}]\");\n";

    public ApplicationDto[] getApplicationsListFromHTML(String responseBody) throws JsonProcessingException {
        ApplicationDto[] applicationDtoList = null;
        int ptrDebut = responseBody.indexOf("var applications = JSON.parse(");
        int ptrFin = responseBody.indexOf("var rootpath = ");
        String responseExtraction = responseBody.substring(ptrDebut + 31, ptrFin - 7);
        responseExtraction = responseExtraction.replaceAll("\\\\/", "/");
        responseExtraction = responseExtraction.replaceAll("/", "//");
        responseExtraction = responseExtraction.replaceAll("\\\\u00E0", "à");
        responseExtraction = responseExtraction.replaceAll("\\\\u00E9", "é");
        responseExtraction = responseExtraction.replaceAll("\\\\", "");

        ObjectMapper mapper = new ObjectMapper();
        return applicationDtoList = mapper.readValue(responseExtraction, ApplicationDto[].class);
    }

    public ContentDto[] getContentsListFromHTML(String responseBody, Appli appli) throws JsonProcessingException {
        ContentDto[] contentDtoList = null;
        int ptrDebut = responseBody.indexOf("var publicationItems = JSON.parse(");
        int ptrFin = responseBody.indexOf("var walkthroughItems =");
        String responseExtraction = responseBody.substring(ptrDebut, ptrFin );
        responseExtraction = responseExtraction.substring(responseExtraction.indexOf("["), responseExtraction.indexOf("]") + 1);
        responseExtraction = responseExtraction.replaceAll("\\\\/", "/");
        responseExtraction = responseExtraction.replaceAll("/", "//");
        responseExtraction = responseExtraction.replaceAll("\\\\u00E0", "à");
        responseExtraction = responseExtraction.replaceAll("\\\\u00E9", "é");
        responseExtraction = responseExtraction.replaceAll("\\\\", "");
        //to do tester la longueur de la chaine si 2 il y a rien...
        ObjectMapper mapper = new ObjectMapper();
        contentDtoList = mapper.readValue(responseExtraction, ContentDto[].class);
        for(int i = 0 ; i < contentDtoList.length ; i++) {
            contentDtoList[i].setAppli(appli);
        }
        return contentDtoList;
    }

//    public List<ContentDto> getContentsList(ResponseEntity<String> response, Appli appli) {
//        //  LoginKnowMore loginKnowMore = new LoginKnowMore();
//        List<ContentDto> listContentDto = new ArrayList<>();
//        int index = 0;
//        // File html = new File("c:/demo/KnowMore/Responses/reponse liste des contenus competence.html");
//        Document doc = Jsoup.parse(response.getBody());
//
//        try {
//            Element table = doc.select("table").get(0); //select the first table.
//            Elements rows = table.select("tr");
//
//            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
//                index = i;
//                Element row = rows.get(i);
//                Elements cols = row.select("td");
//                Attributes onClick = row.attributes();
//                String urlNonFiltrered = onClick.get(Consts.ONCLICK);
//                int posLocationHref = urlNonFiltrered.indexOf(Consts.PUBLICATIONHREF); //recherche y compris le guillement
//                ContentDto contentDto = new ContentDto();
//                if (posLocationHref != -1) {
//                    String url = urlNonFiltrered.substring(posLocationHref + "location.href=\'".length(), urlNonFiltrered.length() - 1);
//
//                    int posPubId = url.indexOf(Consts.PUBID);
//                    int posAmpersand = url.indexOf("&");
//                    String idContent = url.substring(posPubId + Consts.PUBID.length(), posAmpersand);
//                    url = Consts.URLBASEFORSTATISTICSDETAILS + idContent + "&fromDate=28%2f10%2f2019&toDate=19%2f11%2f2019";
//                    contentDto.setContentURL(url);
//                    contentDto.setPubId(idContent);
//                }
//                contentDto.setIdContentKM(cols.get(0).text()); //num pastille  col(1) ==> auteur col(3) ==> lot
//                if (cols.get(2).text().equals("Actif")) {
//                    contentDto.setPublished(true);
//                } else {
//                    contentDto.setPublished(false);
//                }
//                contentDto.setTypeService(cols.get(4).text()); //type de service
//                contentDto.setContentName(cols.get(5).text()); //contenu descritpion
//
//                contentDto.setNbAffichages(Integer.parseInt(cols.get(6).text())); //affichages
//                contentDto.setNbLectures(Integer.parseInt(cols.get(7).text())); //lectures
//                contentDto.setAppli(appli);
//                listContentDto.add(contentDto);
//                System.out.println("col1 " + cols.get(0).text() + " col2 " + cols.get(1).text()
//                        + " col3 " + cols.get(2).text() + " col4 " + cols.get(3).text()
//                        + " col5 " + cols.get(4).text() + " col6 " + cols.get(5).text()
//                        + " col7 " + cols.get(6).text());
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println(ErrorMessage.READHTMLTABLERRORONGETCONTENTSLIST + e.getMessage()
//                    + " index i =  " + index + " appli " + appli.getIdAppliKM());
//            return null;
//        }
//        return listContentDto;
//    }

    public List<StatistiquesParJour> getStatisticsList(String html, Content content) {
        //  LoginKnowMore loginKnowMore = new LoginKnowMore();
        List<StatistiquesParJour> listStatistics = new ArrayList<>();
        int index = 0;
        String sDate;
        WeekDay weekDay = new WeekDay();
        // File html = new File("c:/demo/KnowMore/Responses/reponse liste des contenus competence.html");
        Document doc = Jsoup.parse(html);

        try {
            Element table = doc.select("table").get(2); //select the second table.
            Elements rows = table.select("tr");
            StatistiquesParJour[] statistiquesParJour = new StatistiquesParJour[rows.size()];
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.

                index = i;
                Element row = rows.get(i);
                Elements cols = row.select("td");
                statistiquesParJour[i] = new StatistiquesParJour();

                sDate = cols.get(0).text();

                if (weekDay.isWeekDay(sDate)) {
                    statistiquesParJour[i].setDate(sDate);
                    statistiquesParJour[i].setNbAffichage(Long.parseLong(cols.get(1).text()));
                    statistiquesParJour[i].setNbUsersAyantAffichesLaPastille(Long.parseLong(cols.get(2).text()));
                    //         statistiquesParJour.setNbDeLectureDeLaPastille(Long.parseLong(cols.get(3).text()));
                    //         statistiquesParJour.setNbUsersAyantLusLaPastille(Long.parseLong(cols.get(5).text()));
                    //         statistiquesParJour.setTempsPasseSurLaPastilleMS(Long.parseLong(cols.get(6).text()));
                    //         statistiquesParJour.setContent(content);

                    //         listStatistics.add(statistiquesParJour);
                    //         System.out.println("col1 " + cols.get(0).text() + " col2 " + cols.get(1).text()
                    //                 + " col3 " + cols.get(2).text() + " col4 " + cols.get(3).text()
                    //                 + " col5 " + cols.get(4).text() + " col6 " + cols.get(5).text()
                    //                 + " col7 " + cols.get(6).text());
                }
            }

            table = doc.select("table").get(3); //select the second table.
            rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                index = i;
                Element row = rows.get(i);
                Elements cols = row.select("td");

                sDate = cols.get(0).text();
                if (weekDay.isWeekDay(sDate)) {
                    //   statistiquesParJour.setDate(cols.get(0).text());
                    //   statistiquesParJour.setNbAffichage(Long.parseLong(cols.get(1).text()));
                    //   statistiquesParJour.setNbUsersAyantAffichesLaPastille(Long.parseLong(cols.get(2).text()));
                    statistiquesParJour[i].setNbDeLectureDeLaPastille(Long.parseLong(cols.get(1).text()));
                    statistiquesParJour[i].setNbUsersAyantLusLaPastille(Long.parseLong(cols.get(2).text()));
//                       String strRemoveChar = cols.get(3).text();
//                       String strRes = strRemoveChar.replace(" s","");
//                      String strFinalRes = strRes.replace(",",".");
                    statistiquesParJour[i].setTempsPasseSurLaPastilleMS(cols.get(3).text());
                    statistiquesParJour[i].setContent(content);
                    listStatistics.add(statistiquesParJour[i]);
                    //    System.out.println("col1 " + cols.get(0).text() + " col2 " + cols.get(1).text()
                    //            + " col3 " + cols.get(2).text() + " col4 " + cols.get(3).text()
                    //            + " col5 " + cols.get(4).text() + " col6 " + cols.get(5).text()
                    //            + " col7 " + cols.get(6).text());
                }
            }

        } catch (IndexOutOfBoundsException | NumberFormatException | ParseException e) {
            System.out.println(ErrorMessage.READHTMLTABLERRORONGETCONTENTSLIST + e.getMessage()
                    + " index i =  " + index + " appli " + content.getIdContentKM() + " ContentId " + content.getAppli().getIdAppliKM());
            return null;
        }
//Reading second table
//        try {
//
//        } catch (IndexOutOfBoundsException | NumberFormatException e) {
//            System.out.println(ErrorMessage.READHTMLTABLERRORONGETCONTENTSLIST + e.getMessage()
//                    + " index i =  " + index + " Appli " + content.getIdContentKM() + " ContentId " + content.getAppli().getIdAppliKM());
//            return null;
//        }
        return listStatistics;
    }
}

//    public Applications[] getApplicationsListFromHTML(String responseBody) throws JsonProcessingException {
//        Applications[] applicationsList = null;
//        LoginKnowMore loginKnowMore = new LoginKnowMore();
//      File html = new File("c:/demo/KnowMore/Responses/reponse liste applications.html");  //lecture fichier html
//        List<Appli> listAppli = new ArrayList<>();
//
//        Document doc = Jsoup.parse(html);
//        Element table = doc.select("table").get(0); //select the first table.
//        Elements rows = table.select("tr");
//        int pos;
//        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
//            Element row = rows.get(i);
//            Elements cols = row.select("td");
//            Attributes href = row.attributes();
//            String urlAppliNonFiltrered = href.get(Consts.ONCLICK);
//            //          pos = urlAppliNonFiltrered.indexOf(Consts.APPLICATIONID);
//            String urlAppli = urlAppliNonFiltrered.substring(16, urlAppliNonFiltrered.length() - 1);
//            pos = urlAppli.indexOf(Consts.APPLICATIONID);
//            String idApplication = urlAppli.substring(pos + 14);
//            // urlAppli = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/"+urlAppli;
//            urlAppli = Consts.URLBASEFORCONTENTSDETAILS + idApplication;
//            Appli appli = new Appli();
//            appli.setIdAppliKM(idApplication);
//            appli.setAppliName(cols.get(0).text());
//            appli.setAppliURL(urlAppli); //Url content
//            //System.out.println("col1 " + cols.get(0).text() + " col2 " + cols.get(1).text() + " idApplication " + idApplication);
//            listAppli.add(appli);
//        }
//        return listAppli;