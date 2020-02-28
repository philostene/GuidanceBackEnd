package intra.poleemploi.dto;

import intra.poleemploi.entities.Appli;
import lombok.Data;

import java.util.Objects;

@Data
public class ContentDto {
    private Integer id;
  //  private String idContentKM;  //id content
    private String pid; //id content
    private String status;
    private String batch_status;
    private String batch_name;
    private String admin;
    private String type;
    private String bulb;
    private String content;
    private Integer hits;
    private Integer reads;
    private String redirectUrl;
  //  private String contentName;  //description
  //  private boolean published;
  //  private String typeService;
  //  private Integer nbAffichages;
  //  private Integer nbLectures;
  //  private String icone;
  //  private String contentURL;
    private Appli appli;
  //  private String pubId;

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", idContentKM='" + pid + '\'' +
                ", content Name='" + content + '\'' +
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
        ContentDto objetATester = (ContentDto) o;
        // field comparison
        return Objects.equals(id, objetATester.id)
                && Objects.equals(pid, objetATester.pid);
    }


}
