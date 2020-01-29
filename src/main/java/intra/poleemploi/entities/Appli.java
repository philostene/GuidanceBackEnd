package intra.poleemploi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="appli")
public class Appli implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idAppliKM;
    @Column(unique = true)
    private String appliName;
    @OneToMany (mappedBy = "appli", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Content> contents = new ArrayList<>();
    @Column(name="AppliURL")
    private String appliURL;

    @ManyToMany (mappedBy = "applis")
    private List<UserApp> userApps = new ArrayList<>();

    @Override
    public String toString() {
        return "Appli{" +
                "id=" + id +
                ", appliName='" + appliName + '\'' +
                ", idAppliKM='" + idAppliKM + '\'' +
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
        Appli appli = (Appli) o;
        // field comparison
        return Objects.equals(id, appli.id)
                && Objects.equals(idAppliKM, appli.idAppliKM);
    }
}
