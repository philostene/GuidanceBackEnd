/*
package intra.poleemploi.web;

import intra.poleemploi.dao.ContentRepository;
import intra.poleemploi.entities.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContentController {
    @Autowired
    private ContentRepository contentRepository;

    @GetMapping(value = "/contents")
    public List<Content> listContents(){
        return contentRepository.findAll();
    }

    @GetMapping(value = "/contents/{id}")
    public Content getContentById(@PathVariable(name = "id") Integer id){
        return contentRepository.findById(id).get();
    }

    @PutMapping(value = "/contents/{id}")
    public Content updateContent(@PathVariable(name = "id") Integer id, @RequestBody Content cnt) {
        cnt.setId(id);
        return contentRepository.save(cnt);
    }

    @PostMapping(value = "/contents")
    public Content saveContent(@RequestBody Content cnt){
        return contentRepository.save(cnt);
    }

    @DeleteMapping(value = "/contents/{id}")
    public void deleteContent(@PathVariable(name="id") Integer id){
        contentRepository.deleteById(id);
    }
}
*/
