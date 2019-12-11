package intra.poleemploi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Table(name="role")
public class RoleApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<UserApp> userApps;
}
