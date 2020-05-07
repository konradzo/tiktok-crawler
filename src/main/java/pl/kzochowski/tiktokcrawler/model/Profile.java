package pl.kzochowski.tiktokcrawler.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String userId;
    @NonNull
    private String nickname;
    @NonNull
    private String uniqueId;
    @NonNull
    private String profilePageUrl;
    private LocalDateTime lastExecution;

}
