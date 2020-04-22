package pl.kzochowski.tiktokcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kzochowski.tiktokcrawler.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
