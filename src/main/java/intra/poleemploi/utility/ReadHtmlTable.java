package intra.poleemploi.utility;

import intra.poleemploi.Consts.Consts;

import intra.poleemploi.Consts.ErrorMessage;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

class ReadHtmlTable {

    List<Appli> getAppliList(String html) {
//        LoginKnowMore loginKnowMore = new LoginKnowMore();
//      File html = new File("c:/demo/KnowMore/Responses/reponse liste applications.html");  //lecture fichier html
        List<Appli> listAppli = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");
        int pos;
        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");
            Attributes href = row.attributes();
            String urlAppliNonFiltrered = href.get(Consts.ONCLICK);
  //          pos = urlAppliNonFiltrered.indexOf(Consts.APPLICATIONID);
            String urlAppli = urlAppliNonFiltrered.substring(16, urlAppliNonFiltrered.length() - 1);
            pos = urlAppli.indexOf(Consts.APPLICATIONID);
            String idApplication = urlAppli.substring(pos + 14);
           // urlAppli = "http://kmore-gfpe-fkqt507.sii24.pole-emploi.intra:15071/"+urlAppli;
            urlAppli = Consts.URLBASEFORCONTENTSDETAILS+idApplication;
            Appli appli = new Appli();
            appli.setIdAppliKM(idApplication);
            appli.setAppliName(cols.get(0).text());
            appli.setAppliURL(urlAppli); //Url content
            //System.out.println("col1 " + cols.get(0).text() + " col2 " + cols.get(1).text() + " idApplication " + idApplication);
            listAppli.add(appli);
        }
        return listAppli;
    }
    List<Content> getContentsList(String html, Appli appli) {
      //  LoginKnowMore loginKnowMore = new LoginKnowMore();
        List<Content> listContent = new ArrayList<>();
        int index =0;
        // File html = new File("c:/demo/KnowMore/Responses/reponse liste des contenus competence.html");
        Document doc = Jsoup.parse(html);

        try {
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");

        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            index = i;
            Element row = rows.get(i);
            Elements cols = row.select("td");
            Attributes onClick = row.attributes();
            String urlNonFiltrered = onClick.get(Consts.ONCLICK);
            int posLocationHref = urlNonFiltrered.indexOf(Consts.PUBLICATIONHREF); //recherche y compris le guillement
            Content content = new Content();
            if (posLocationHref != -1) {
                String url = urlNonFiltrered.substring(posLocationHref + "location.href=\'".length(), urlNonFiltrered.length() - 1);

                int posPubId = url.indexOf(Consts.PUBID);
                int posAmpersand = url.indexOf("&");
                String idContent = url.substring(posPubId + Consts.PUBID.length(), posAmpersand);
                url = Consts.URLBASEFORSTATISTICSDETAILS +idContent+"&fromDate=28%2f10%2f2019&toDate=19%2f11%2f2019";
                content.setContentURL(url);
            }

            content.setIdContentKM(cols.get(0).text());
            if (cols.get(2).text().equals("Publiée")) {
                content.setPublished(true);
            } else {
                content.setPublished(false);
            }
            content.setTypeService(cols.get(3).text());
            content.setContentName( cols.get(4).text());

            content.setNbAffichages(Integer.parseInt(cols.get(5).text()));
            content.setNbLectures(Integer.parseInt(cols.get(6).text()));
            content.setAppli(appli);
            listContent.add(content);
            System.out.println("col1 " + cols.get(0).text() + " col2 " + cols.get(1).text()
                    + " col3 " + cols.get(2).text() + " col4 " + cols.get(3).text()
                    + " col5 " + cols.get(4).text() + " col6 " + cols.get(5).text()
                    + " col7 " + cols.get(6).text());
        }

        }
        catch (IndexOutOfBoundsException e ) {
            System.out.println(ErrorMessage.READHTMLTABLERRORONGETCONTENTSLIST + e.getMessage()
            + " index i =  " + index + " appli " + appli.getIdAppliKM() );
            return null;
        }

        return listContent;
    }


}