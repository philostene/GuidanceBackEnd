package intra.poleemploi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name="statistiquesParJour")
public class StatistiquesParJour implements Serializable  {  //resourceSupport has been renamed ReprensentationModel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    @Column(name="NbAffichage")
    private Long nbAffichage;
    @Column(name="NbUsersAyantAffichesLaPastille")
    private Long nbUsersAyantAffichesLaPastille;
    @Column(name="NbDeLectureDeLaPastille")
    private Long nbDeLectureDeLaPastille;
    @Column(name="NbUsersAyantLusLaPastille")
    private Long nbUsersAyantLusLaPastille;
    @Column(name="TempsPasseSurLaPastilleMS")
    private String tempsPasseSurLaPastilleMS;
    @ManyToOne
    @JoinColumn(name = "ContentID",nullable = false)
    private Content content;
    private Date dateDB;

    public StatistiquesParJour(String s, Content content) {
        this.date = s;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", date ='" + date + '\'' +
                ", content ='" + content.toString() + '\'' +
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
        StatistiquesParJour valeurATester = (StatistiquesParJour) o;
        // field comparison
        return Objects.equals(id, valeurATester.id)
                && Objects.equals(date, valeurATester.date)
                && Objects.equals(content.getId(), valeurATester.content.getId());
    }
}


