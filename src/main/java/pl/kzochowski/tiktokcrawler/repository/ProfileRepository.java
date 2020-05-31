package pl.kzochowski.tiktokcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kzochowski.tiktokcrawler.model.Profile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByUniqueId(String uniqueId);

    Optional<Profile> findByProfilePageUrl(String profilePageUrl);

    Optional<Profile> findFirstByOrderByLastExecutionAsc();

    Optional<List<Profile>> findTop5ByOrderByLastExecutionAsc();

}
