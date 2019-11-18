//package intra.poleemploi.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Data @NoArgsConstructor @AllArgsConstructor
//@Table(name="statistiquesParJour")
//public class StatistiquesParJour implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Date date;
//    @Column(name="NbAffichage")
//    private Long nbAffichage;
//    @Column(name="NbUsersAyantAffichesLaPastille")
//    private Long nbUsersAyantAffichesLaPastille;
//    @Column(name="NbDeLectureDeLaPastille")
//    private Long nbDeLectureDeLaPastille;
//    @Column(name="NbUsersAyantLusLaPastille")
//    private Long nbUsersAyantLusLaPastille;
//    @Column(name="TempsPasseSurLaPastilleMS")
//    private Long tempsPasseSurLaPastilleMS;
//    @Column(name="Content")
//    private Content content;
//}
//
//
