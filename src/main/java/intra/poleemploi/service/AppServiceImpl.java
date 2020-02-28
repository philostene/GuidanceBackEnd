package intra.poleemploi.service;


import intra.poleemploi.entities.Appli;
import intra.poleemploi.repository.AppliRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    // interfaces DAO
    private AppliRepository appliRepository;
//    private ContentRepository contentRepository;

    // Injection des dépendances
   public AppServiceImpl(AppliRepository appliRepository) {
       this.appliRepository = appliRepository;
//       this.contentRepository = contentRepository;
    }
    @Override
    public List<Appli> findAllAppli() {
        return appliRepository.findAll();
    }
//
//    @Override
//    public Appli saveAppli(String appliName) {
//        Appli appli = appliRepository.findAppliByAppliName(appliName);
//        if(appli != null) throw new RuntimeException("Appli already exist !!!");
//        Appli appli = new Appli();
//        appli.setAppliName(appliName);
//
//        appliRepository.save(appli);
//        //addContentToAppli(appliName, "Pastille");
//        return appli;
//    }

//    @Override
//    public Content saveContent(Content content) {
//        return contentRepository.save(content);
//    }
//
//    @Override
//    public void addContentToAppli(String appliName, String contentName) {
//        Appli appli = appliRepository.findAppliByAppliName(appliName);
//        Content content = contentRepository.findContentByContentName(contentName);
//        appli.getContents().add(content);
//    }
//
//    }

//    @Override
//    public Appli findAppliById(int id) {
//        return null;
//    }

//    @Override
//    public void deleteAppli() {
//        appliRepository.deleteAll();
//    }

//    @Override
//    @GetMapping(value="/listApplis")
//    public List<Appli> getListAppli() {
//        return appliRepository.findAll();
//    }

}
