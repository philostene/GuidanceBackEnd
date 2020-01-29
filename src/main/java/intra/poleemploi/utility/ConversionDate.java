package intra.poleemploi.utility;

import intra.poleemploi.entities.StatistiquesParJour;

import java.util.Date;
import java.util.List;

public class ConversionDate {
    Date dateKM;
    java.sql.Date dateSQL;
    String temp;
    String mois[] = {"janv.","févr.","mars","avr.","mai","juin","juil.","août","sept.","oct.","nov.","déc."};
     public void conversionDateKMToSQL(List<StatistiquesParJour> statistiquesParJourList) {

         for (int index = 1; index < statistiquesParJourList.size();index++){


                temp = statistiquesParJourList.get(index).getDate();

                for (int i = 0; i < mois.length; i++){
                    if (temp.contains(mois[i])) {

                        break;
                    }
                }



         }
     }
}
