package intra.poleemploi.repository;

import intra.poleemploi.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContentRepository extends JpaRepository<Content, Integer> {
    //public Content findContentByContentName(String contentName);
    Content findById(int id);
}
