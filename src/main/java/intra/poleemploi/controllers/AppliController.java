package intra.poleemploi.controllers;

import intra.poleemploi.dto.ApplicationDto;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.repository.AppliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class AppliController {

    @Autowired
    private AppliRepository appliRepository;

    @GetMapping("/applis")
    public CollectionModel <List<ApplicationDto>> findAllapplis(){
        List<ApplicationDto> applicationDtoList = new ArrayList<>();
        Class<AppliController> appliClass = AppliController.class;
   //     WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllapplis());
        Link link = linkTo(methodOn(appliClass).findAllapplis()).withSelfRel();
        List<Appli> appliList = appliRepository.findAll();
        for (Appli appli : appliList) {
            ApplicationDto applicationDto = new ApplicationDto();
            applicationDto.setId(appli.getId());
            applicationDto.setIdAppliKM(appli.getIdAppliKM());
            applicationDto.setAppliName(appli.getAppliName());
            applicationDtoList.add(applicationDto);
        }
        CollectionModel<List<ApplicationDto>> response = new CollectionModel(applicationDtoList, link);
       // response.add(linkTo(AppliController.class).withSelfRel());
        return response;

    }

}
