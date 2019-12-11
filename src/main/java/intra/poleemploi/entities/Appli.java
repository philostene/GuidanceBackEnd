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
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "appli", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Content> contents;
    @ManyToMany
    private Set<UserApp> users;
    @Column(name="AppliURL")
    private String appliURL;

    @Override
    public String toString() {
        return "Appli{" +
                "id=" + id +
                ", idAppliKM='" + idAppliKM + '\'' +
                ", appliName='" + appliName + '\'' +
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
