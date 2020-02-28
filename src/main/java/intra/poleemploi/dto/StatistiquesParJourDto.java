package intra.poleemploi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StatistiquesParJourDto implements Serializable {  //resourceSupport has been renamed ReprensentationModel

    private Set<StatistiquesParJourDto> statistiquesParJourDtoSet = new HashSet<>();
    private Long id;
    private String date;
    private Long nbAffichage;
    private Long nbUsersAyantAffichesLaPastille;
    private Long nbDeLectureDeLaPastille;
    private Long nbUsersAyantLusLaPastille;
    private String tempsPasseSurLaPastilleMS;
    private String pubId;
    private Date dateDB;

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", date ='" + date + '\'' +
                ", pubId ='" + pubId+ '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        StatistiquesParJourDto valeurATester = (StatistiquesParJourDto) o;
        // field comparison
        return Objects.equals(id, valeurATester.id)
                && Objects.equals(date, valeurATester.date)
                && Objects.equals(pubId, valeurATester.pubId);
    }
}


