package intra.poleemploi.repository;

import intra.poleemploi.entities.Content;
import intra.poleemploi.entities.StatistiquesParJour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface StatistiquesParJourRepository extends JpaRepository<StatistiquesParJour, Long> {
    List<StatistiquesParJour> findByContent(Content content);
    List<StatistiquesParJour> findByContentAndDateDBAfter(Content content, String date);
    List<StatistiquesParJour> findByDateDBAfter( Date date);
    List<StatistiquesParJour> findByDateDBBetween( Date date, Date date1);
    List<StatistiquesParJour> findByContentAndDateDBBetween(Content content, Date date, Date date1);
  //  List<StatistiquesParJour> findById(int id);
}
