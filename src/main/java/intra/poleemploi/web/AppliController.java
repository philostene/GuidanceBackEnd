/*

package intra.poleemploi.web;

import intra.poleemploi.dao.AppliRepository;
import intra.poleemploi.entities.Appli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AppliController {
    @Autowired
    private AppliRepository appliRepository;

    @GetMapping(value = "/applis")
    public List<Appli> listApplis(){
        return appliRepository.findAll();
    }

    @GetMapping(value = "/applis/{id}")
    public ResponseEntity<Appli> getAppliById(@PathVariable(value = "id") Integer idAppliKM) throws ResourceNotFoundException {
        Appli appli = appliRepository.findById(idAppliKM)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found for this id :: " + idAppliKM));
        return ResponseEntity.ok().body(appli);
    }

    @PutMapping(value = "/applis/{id}")
    public Appli updateAppli(@PathVariable(name = "id") Integer id, @RequestBody Appli app) {
        app.setId(id);
        return appliRepository.save(app);
    }

    @PostMapping(value = "/applis")
    public Appli saveAppli(@RequestBody Appli app){
        return appliRepository.save(app);
    }

    @DeleteMapping(value = "/applis/{id}")
    public void deleteAppli(@PathVariable(name="id") Integer id){
        appliRepository.deleteById(id);
    }
}

*/
