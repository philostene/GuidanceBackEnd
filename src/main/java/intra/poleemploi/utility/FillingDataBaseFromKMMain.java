//package intra.poleemploi.utility;
//
//
//import intra.poleemploi.dao.*;
//import intra.poleemploi.entities.*;
//import intra.poleemploi.service.AuthService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Stream;
//
//
//@SpringBootApplication(scanBasePackages={"intra.poleemploi"})
//class FillingDataBaseMainFromKM {
////    @Autowired
////    private RepositoryRestConfiguration repositoryRestConfiguration;
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext ctx = SpringApplication.run(FillingDataBaseMainFromKM.class, args);
//        ctx.close();
//    }
///*
//    @Bean
//    CommandLineRunner startFillingDataBaseKM(AppliRepository appliRepository, ContentRepository contentRepository, StatistiquesParJourRepository statistiquesParJourRepository, AuthService authService, UserAppRepository userAppRepository, RoleAppRepository roleAppRepository) {
//        return args -> {
//            repositoryRestConfiguration.exposeIdsFor(Appli.class, Content.class, StatistiquesParJour.class, UserApp.class, RoleApp.class);
//*/
//
//    @Bean
//    CommandLineRunner startFillingDataBaseKM(RepositoryRestConfiguration repositoryRestConfiguration, AppliRepository appliRepository, ContentRepository contentRepository, StatistiquesParJourRepository statistiquesParJourRepository, AuthService authService, UserAppRepository userAppRepository, RoleAppRepository roleAppRepository) {
//        return args -> {
//            repositoryRestConfiguration.exposeIdsFor(Appli.class, Content.class, UserApp.class, RoleApp.class);
//
//            userAppRepository.deleteAll();
//            authService.delAllAppToAllUser();
//            roleAppRepository.deleteAll();
//            contentRepository.deleteAll();
//            appliRepository.deleteAll();
//            statistiquesParJourRepository.deleteAll();
//
//            List<Appli> listAppli;
//            LoginKnowMore loginKnowMore = new LoginKnowMore();
//
//            listAppli = loginKnowMore.listAppli();                             //readHtmlTable.getAppliList();
//            for (Appli tempAppli : listAppli) {
//                appliRepository.save(tempAppli);
//            }
//            appliRepository.findAll().forEach(System.out::println);
//            // Table Content filling
//            contentRepository.deleteAll();
//
//            List<Content> listContents;
//
//          //  listContent = readHtmlTable.getContentList(appliRepository.findAll());
//            listContents = loginKnowMore.listContents(listAppli);
//
//            for (Content tempContent : listContents) {
//                try {
//                    contentRepository.save(tempContent);
//                }
//                catch (Exception e) {System.out.println("error FillingDataBaseMainMenuFromKM "+ e.getMessage() + Arrays.toString(e.getStackTrace()));}
//            }
//           // contentRepository.findAll().forEach(System.out::println);
//
//            // Table statistique par jour filling
//            statistiquesParJourRepository.deleteAll();
//            List<StatistiquesParJour> listStatistiquesParJour;
//            listStatistiquesParJour = loginKnowMore.listStatistics(contentRepository.findAll());
//            for (StatistiquesParJour tempStempStatistiquesParJour : listStatistiquesParJour) {
//                statistiquesParJourRepository.save(tempStempStatistiquesParJour);
//            }
//            statistiquesParJourRepository.findAll().forEach(System.out::println);
//
//            // AUTHENTICATION
//            // ajout de 2 roles
//            authService.saveRoleApp(new RoleApp(null, "USER"));
//            authService.saveRoleApp(new RoleApp(null, "ADMIN"));
//
//            // ajout users
//            Stream.of("user1", "user2", "user3", "user4", "admin").forEach((String username) -> authService.saveUserApp(username, "1234", "1234"));
//
//            // ajout role ADMIN a l'admin
//            authService.addRoleToUser("admin", "ADMIN");
//
//            // ajout appli à user
//            authService.addAppliToUser("user1", "MAP Vue DE");
//            authService.addAppliToUser("user1", "Profil de compétences");
//            authService.addAppliToUser("user1", "AUDE Presta");
//            authService.addAppliToUser("user2", "MRS Digitale");
//            authService.addAppliToUser("user2", "MAP Vue DE");
//            authService.addAppliToUser("user3", "Profil de compétences");
//            userAppRepository.findAll().forEach(System.out::println);
//            appliRepository.findAll().forEach(System.out::println);
//            contentRepository.findAll().forEach(System.out::println);
//        };
//    }
//
//}
//
