package intra.poleemploi.utility.datebaseManagement;

import intra.poleemploi.dto.ApplicationDto;
import intra.poleemploi.dto.ContentDto;
import intra.poleemploi.entities.*;
import intra.poleemploi.repository.AppliRepository;
import intra.poleemploi.repository.ContentRepository;
import intra.poleemploi.repository.StatistiquesParJourRepository;

import java.util.List;

public class FromKMDatabaseIntoLocalDatabase {

    public void writeApplications(AppliRepository appliRepository, ApplicationDto[] applicationDtoList) {
        appliRepository.deleteAll();

        for (ApplicationDto applicationDto : applicationDtoList) {
            Appli appli = new Appli();
            appli.setAppliName(applicationDto.getAppliName());
            appli.setIdAppliKM(applicationDto.getIdAppliKM());
            appli.setIcon(applicationDto.getIcon());
            appli.setAppliURL(applicationDto.getApplicationUrl());
            appli.setUrl(applicationDto.getUrl());

            appliRepository.save(appli);
        }
        appliRepository.findAll().forEach(System.out::println);
    }

    public void writeContents(ContentRepository contentRepository, List<ContentDto> contentDtoList) {
        int echec = 0;
        int succes = 0;
        contentRepository.deleteAll();

        for (ContentDto contentDto : contentDtoList) {
            Content content = new Content();
            content.setPubID(contentDto.getId());
            content.setIdContentKM(contentDto.getPid());
            content.setContentName(contentDto.getContent());
            content.setIcone(""); //à extraire de setcontentname
            content.setContentURL(contentDto.getRedirectUrl());
            content.setNbAffichages(contentDto.getHits());
            content.setNbLectures(contentDto.getReads());
            content.setAppli(contentDto.getAppli());
            content.setTypeService(contentDto.getType());
            System.out.println("content_name length= " + content.getContentName().length()+ " content id =" + content.getIdContentKM() + " appli= " + content.getAppli().getAppliName());
            try {
                contentRepository.save(content);
                System.out.println("Succes ecriture dans la table content " + content.getIdContentKM());
                succes++;
            }
            catch (Exception e) {
                System.out.println("Echec ecriture dans la table content "  + content.getIdContentKM() + " error " + e.getMessage());
                echec++;
            }
        }
        System.out.println("Succes = " + succes + " Echec = " + echec);
        contentRepository.findAll().forEach(System.out::println);
    }

    public void writeStatistics(StatistiquesParJourRepository statistiquesParJourRepository, List<StatistiquesParJour> listSatisticsParJourReturned) {
        int echec = 0;
        int succes = 0;
        statistiquesParJourRepository.deleteAll();

        for (StatistiquesParJour statistiquesParJour : listSatisticsParJourReturned) {
            System.out.println("content_name length= " + statistiquesParJour.getContent().getContentName().length()+ " content id =" + statistiquesParJour.getContent().getIdContentKM() + " appli= " + statistiquesParJour.getContent().getAppli().getAppliName());
            try {
                statistiquesParJourRepository.save(statistiquesParJour);
                System.out.println("Succes ecriture dans la table content " + statistiquesParJour.getContent().getIdContentKM());
                succes++;
            }
            catch (Exception e) {
                System.out.println("Echec ecriture dans la table content "  + statistiquesParJour.getContent().getIdContentKM() + " error " + e.getMessage());
                echec++;
            }
        }
        System.out.println("Succes = " + succes + " Echec = " + echec);
        statistiquesParJourRepository.findAll().forEach(System.out::println);
    }
}
