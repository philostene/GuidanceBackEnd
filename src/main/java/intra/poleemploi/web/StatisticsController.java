package intra.poleemploi.web;

import intra.poleemploi.dao.ContentRepository;
import intra.poleemploi.dao.StatistiquesParJourRepository;
import intra.poleemploi.entities.Content;
import intra.poleemploi.entities.StatistiquesParJour;
import intra.poleemploi.entities.StatistiquesParJourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class StatisticsController {
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private StatistiquesParJourRepository statistiquesParJourRepository;

    @GetMapping(value = "/statistiquesparjour",produces = {"application/hal+json"})
    public CollectionModel<List<StatistiquesParJourDto>> statistiquesparjour(@RequestParam("pubId") String pubId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate ) throws ParseException, NoSuchMethodException {
       Class<StatisticsController> controllerClass = StatisticsController.class;
       WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).statistiquesparjour(pubId,fromDate,toDate));
       Link link1 = linkTo.withSelfRel();

       Link link = linkTo(methodOn(controllerClass).statistiquesparjour(pubId, fromDate, toDate)).withSelfRel();;
      // Link link2 = linkTo(controllerClass.getMethod("statistiquesparjour")).withSelfRel();
       Link link3 = new Link("http://localhost:8080/statistiquesparjour?pubId=61&fromDate=28%2f10%2f2019&toDate=19%2f11%2f2019");
       Link link4 = linkTo(StatisticsController.class).withSelfRel();
       List<StatistiquesParJourDto> statistiquesParJourDtoList = new ArrayList<>();
       System.out.println("pubId " + pubId);
       System.out.println("fromDate " + fromDate);
       System.out.println("toDate " + toDate);
       String sDate = "10/11/2019";
       Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
       sDate = "15/11/2019";
      // sDate = "15112019";
      // Date sDateTest = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
       Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
       Content content = contentRepository.findById(Integer.parseInt(pubId));
       List<StatistiquesParJour> statistiquesParJourList = statistiquesParJourRepository.findByContentAndDateDBBetween(content, date1, date2);

       for (StatistiquesParJour statJour :statistiquesParJourList) {
             StatistiquesParJourDto statistiquesParJourDto = new StatistiquesParJourDto();
             statistiquesParJourDto.setId(statJour.getId());
             statistiquesParJourDto.setDate(statJour.getDate());
             statistiquesParJourDto.setNbAffichage(statJour.getNbAffichage());
             statistiquesParJourDto.setNbUsersAyantAffichesLaPastille(statJour.getNbUsersAyantAffichesLaPastille());
             statistiquesParJourDto.setNbDeLectureDeLaPastille(statJour.getNbDeLectureDeLaPastille());
             statistiquesParJourDto.setNbUsersAyantLusLaPastille(statJour.getNbUsersAyantLusLaPastille());
             statistiquesParJourDto.setPubId(String.valueOf(statJour.getId()));
             statistiquesParJourDtoList.add(statistiquesParJourDto);
       }

        CollectionModel<List<StatistiquesParJourDto>> response = new CollectionModel(statistiquesParJourDtoList, link);
        response.add(linkTo(StatisticsController.class).withSelfRel());
        return response;
    }
}

