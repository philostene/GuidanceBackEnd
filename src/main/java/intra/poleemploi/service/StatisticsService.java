package intra.poleemploi.service;

import intra.poleemploi.repository.StatistiquesParJourRepository;
import intra.poleemploi.entities.StatistiquesParJour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class StatisticsService {

    public void updateStatistic (StatistiquesParJourRepository statistiquesParJourRepository) throws ParseException {

        List<StatistiquesParJour> statistiquesParJourList;
        statistiquesParJourList = statistiquesParJourRepository.findAll();

       for (int i = 0; i< statistiquesParJourList.size(); i++){
           StatistiquesParJour statistiquesParJour = new StatistiquesParJour();
           statistiquesParJour = statistiquesParJourList.get(i);
           String sDate = statistiquesParJour.getDate();
           statistiquesParJour.setDateDB(new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).parse(sDate));
           statistiquesParJourRepository.save(statistiquesParJour);
           System.out.println("index " + i);
        }
    }
}
