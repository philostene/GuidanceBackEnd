package intra.poleemploi;//package intra.poleemploi.utility;

import intra.poleemploi.dao.AppliRepository;
import intra.poleemploi.dao.ContentRepository;
import intra.poleemploi.dao.RoleAppRepository;
import intra.poleemploi.dao.UserAppRepository;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.Content;
import intra.poleemploi.entities.RoleApp;
import intra.poleemploi.entities.UserApp;
import intra.poleemploi.service.AuthService;
import intra.poleemploi.service.WriteApplisIntoDataBase;
import intra.poleemploi.utility.ReadExcel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication //(scanBasePackages={"intra.poleemploi"})

class FillingDataBaseFromExcelFileMain {
//    @Autowired
//    private RepositoryRestConfiguration repositoryRestConfiguration;
      private String option ="creation";

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FillingDataBaseFromExcelFileMain.class, args);
        ctx.close();
    }

    @Bean
    CommandLineRunner startFillingDataBase(RepositoryRestConfiguration repositoryRestConfiguration, AppliRepository appliRepository, ContentRepository contentRepository, AuthService authService, UserAppRepository userAppRepository, RoleAppRepository roleAppRepository) {
        return args -> {
            //repositoryRestConfiguration.exposeIdsFor(Appli.class, Content.class, UserApp.class, RoleApp.class);
            userAppRepository.deleteAll();
            authService.delAllAppToAllUser();
            roleAppRepository.deleteAll();
            contentRepository.deleteAll();
            appliRepository.deleteAll();

//            List<Appli> listAppli;
            ReadExcel readExcel = new ReadExcel();
//            listAppli = readExcel.getAppliList();
//            for (Appli tempAppli : listAppli) {
//                appliRepository.save(tempAppli);
//            }
//            appliRepository.findAll().forEach(System.out::println);

            WriteApplisIntoDataBase writeApplisIntoDataBase = new WriteApplisIntoDataBase();
            writeApplisIntoDataBase.writeApplisIntoDabase(appliRepository);

            // Table Content filling
            contentRepository.deleteAll();
            List<Content> listContent = readExcel.getContentList(appliRepository.findAll());
            for (Content tempContent : listContent) {
                try {
                    contentRepository.save(tempContent);
                }
            catch (DataIntegrityViolationException e) {System.out.println("Error contentRepository " + e.getMessage());}
            }
            contentRepository.findAll().forEach(System.out::println);

//            // Table statistique par jour filling
//            statistiquesParJourRepository.deleteAll();
//            List<StatistiquesParJour> listStatistiquesParJour = new ArrayList<>();
//            listStatistiquesParJour = readExcel.getStatistiquesParJourList(contentRepository.findAll());
//            for (StatistiquesParJour tempStempStatistiquesParJour : listStatistiquesParJour) {
//                statistiquesParJourRepository.save(tempStempStatistiquesParJour);
//            }
//            statistiquesParJourRepository.findAll().forEach(System.out::println);
//
            // AUTHENTICATION
            // ajout de 2 roles
            authService.saveRoleApp(new RoleApp(null, "USER"));
            authService.saveRoleApp(new RoleApp(null, "ADMIN"));

            // ajout users
            Stream.of("user1", "user2", "user3", "user4", "admin").forEach(username -> authService.saveUserApp(username, "1234", "1234"));

            // ajout role ADMIN a l'admin
            authService.addRoleToUser("admin", "ADMIN");

            // ajout appli à user
            authService.addAppliToUser("user1", "MAP Vue DE");
            authService.addAppliToUser("user1", "Profil de compétences");
            authService.addAppliToUser("user1", "AUDE Presta");
            authService.addAppliToUser("user2", "MRS Digitale");
            authService.addAppliToUser("user2", "MAP Vue DE");
            authService.addAppliToUser("user3", "Profil de compétences");
            userAppRepository.findAll().forEach(System.out::println);
            System.out.println("option " + option);
        };
    }
    // créer BCryptPasswordEncoder au démarrage de l'appli pour injection dans couche Service
    @Bean
    BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

}

