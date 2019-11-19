package intra.poleemploi.dao;

import intra.poleemploi.entities.RoleApp;
import intra.poleemploi.entities.StatistiquesParJour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatistiquesParJourRepository extends JpaRepository<StatistiquesParJour, Long> {
}
