package intra.poleemploi.utility;//package intra.poleemploi.utility;

import intra.poleemploi.dao.AppliRepository;
import intra.poleemploi.dao.ContentRepository;
import intra.poleemploi.dao.RoleAppRepository;
import intra.poleemploi.dao.UserAppRepository;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.Content;
import intra.poleemploi.entities.RoleApp;
import intra.poleemploi.entities.UserApp;
import intra.poleemploi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

//
@SpringBootApplication(scanBasePackages={"intra.poleemploi"})
public class FillingExcelFileFromDataBaseMain {
//    @Autowired
//    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FillingExcelFileFromDataBaseMain.class, args);
        ctx.close();
    }

    @Bean
    CommandLineRunner startFillingExcelFile(RepositoryRestConfiguration repositoryRestConfiguration, AppliRepository appliRepository, ContentRepository contentRepository, AuthService authService, UserAppRepository userAppRepository, RoleAppRepository roleAppRepository) {
        return args -> {
             repositoryRestConfiguration.exposeIdsFor(Appli.class, Content.class, UserApp.class, RoleApp.class);

             WriteExcelForGuidanceDB writeExcelForGuidanceDB = new WriteExcelForGuidanceDB();
             writeExcelForGuidanceDB.appliInsertIntoExcelFile(appliRepository.findAll());
             writeExcelForGuidanceDB.ContentInsertIntoExcelFile(contentRepository.findAll());

        };
    }
}

