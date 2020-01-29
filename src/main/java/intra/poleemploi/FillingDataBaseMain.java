package intra.poleemploi;//package intra.poleemploi.utility;

import intra.poleemploi.dao.*;
import intra.poleemploi.entities.*;
import intra.poleemploi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication //(scanBasePackages={"intra.poleemploi"})

class FillingDataBaseMain {
//    @Autowired
//   private RepositoryRestConfiguration repositoryRestConfiguration;
       // Décommenter l'option désirée
      //private String option = "WriteDataBaseFromExcel"; //write database from Excel
        private String option  = "WriteDataBaseFromKM";  //Write database from KM
      //private String option = "WriteExcelFromDataBase"; //Write Excel from database

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FillingDataBaseMain.class, args);
      //  ctx.close();
    }

    @Bean
    CommandLineRunner startFillingDataBase(StatistiquesParJourRepository statistiquesParJourRepository, RepositoryRestConfiguration repositoryRestConfiguration, AppliRepository appliRepository, ContentRepository contentRepository, AuthService authService, UserAppRepository userAppRepository, RoleAppRepository roleAppRepository) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Appli.class, Content.class, UserApp.class, RoleApp.class, AuthService.class, StatistiquesParJour.class);


     //       userAppRepository.deleteAll();
     //       authService.delAllAppToAllUser();
     //       roleAppRepository.deleteAll();
     //       statistiquesParJourRepository.deleteAll();
     //       contentRepository.deleteAll();
     //       appliRepository.deleteAll();



//            switch (option) {
//                case "WriteDataBaseFromExcel":
//
////            List<Appli> listAppli;
//            ReadExcel readExcel = new ReadExcel();
////            listAppli = readExcel.getAppliList();
////            for (Appli tempAppli : listAppli) {
////                appliRepository.save(tempAppli);
////            }
           appliRepository.findAll().forEach(System.out::println);
//
//            WriteApplisIntoDataBase writeApplisIntoDataBase = new WriteApplisIntoDataBase();
//            writeApplisIntoDataBase.writeApplisIntoDabase(appliRepository);
//
//            // Table Content filling
//            contentRepository.deleteAll();
//            List<Content> listContent = readExcel.getContentList(appliRepository.findAll());
//            for (Content tempContent : listContent) {
//                try {
//                    contentRepository.save(tempContent);
//                }
//            catch (DataIntegrityViolationException e) {System.out.println("Error contentRepository " + e.getMessage());}
//            }
//            contentRepository.findAll().forEach(System.out::println);
//
////            // Table statistique par jour filling
////            statistiquesParJourRepository.deleteAll();
////            List<StatistiquesParJour> listStatistiquesParJour = new ArrayList<>();
////            listStatistiquesParJour = readExcel.getStatistiquesParJourList(contentRepository.findAll());
////            for (StatistiquesParJour tempStempStatistiquesParJour : listStatistiquesParJour) {
////                statistiquesParJourRepository.save(tempStempStatistiquesParJour);
////            }
////            statistiquesParJourRepository.findAll().forEach(System.out::println);
//                    break;
//                case "WriteDataBaseFromKM" :
//                    List<Appli> listAppli;
//                   LoginKnowMore loginKnowMore = new LoginKnowMore();
//                    listAppli = loginKnowMore.listAppli();                             //readHtmlTable.getAppliList();
//                    for (Appli tempAppli : listAppli) {
//                        appliRepository.save(tempAppli);
//                    }
 //                  appliRepository.findAll().forEach(System.out::println);

//                    List<Content> listContents = null;
//                    //listContent = readHtmlTable.getContentList(appliRepository.findAll());
//                    listContents = loginKnowMore.listContents(listAppli);
//                    for (Content tempContent : listContents) {
//                        try {
//                            contentRepository.save(tempContent);
//                        }
//                        catch (Exception e) {System.out.println("error FillingDataBaseMainMenuFromKM "+ e.getMessage() + Arrays.toString(e.getStackTrace()));}
//                    }
              //      contentRepository.findAll().forEach(System.out::println);
//
//            listContents = contentRepository.findAll();
//            List<StatistiquesParJour> listStatistiquesParJour = new ArrayList<>();
//                    listStatistiquesParJour = loginKnowMore.listStatistics(listContents);
//                    int i =0;
//                    for (StatistiquesParJour tempStempStatistiquesParJour : listStatistiquesParJour) {
//                        statistiquesParJourRepository.save(tempStempStatistiquesParJour);
//                        System.out.print(" i " + i++ );
//                    }
//                    statistiquesParJourRepository.findAll().forEach(System.out::println);
//                    break;
//
//            }
//
            // AUTHENTICATION
            // ajout de 2 roles
  //        authService.saveRoleApp(new RoleApp( "USER",null));
  //        authService.saveRoleApp(new RoleApp( "ADMIN",null));

            // ajout users
    //       Stream.of("user1", "user2", "user3", "user4", "admin").forEach(username -> authService.saveUserApp(username, "1234", "1234"));

            // ajout role ADMIN a l'admin
   //        authService.addRoleToUser("admin", "ADMIN");

            // ajout appli à user
          //  authService.addAppliToUser("user1", "MAP Vue DE");
          //  authService.addAppliToUser("user1", "Profil de compétences");
         //   authService.addAppliToUser("user1", "AUDE Presta");
          //  authService.addAppliToUser("user2", "MRS Digitale");
          //  authService.addAppliToUser("user2", "MAP Vue DE");
          //  authService.addAppliToUser("user3", "Profil de compétences");
          //  userAppRepository.findAll().forEach(System.out::println);
        //    StatisticsService statisticsService = new StatisticsService();  //conversion date string to date
        //    statisticsService.updateStatistic(statistiquesParJourRepository);
//              List<Content> contents = contentRepository.findAll();
//              Content content = new Content();
//              content = contents.get(1);
//              List<StatistiquesParJour> statistiquesParJourList = statistiquesParJourRepository.findByContent(content);
//              statistiquesParJourList.forEach(System.out::println);
//              statistiquesParJourList.clear();
//            String sDate = "10/11/2019";
//            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
//            sDate = "15/11/2019";
//            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
//        //    statistiquesParJourList = statistiquesParJourRepository.findByContentAndDateDBAfter(content, date1);
//            statistiquesParJourList = statistiquesParJourRepository.findByDateDBAfter(date1);
//
//            statistiquesParJourList = statistiquesParJourRepository.findByDateDBBetween(date1, date2);
//            statistiquesParJourList.forEach(System.out::println);
//            statistiquesParJourList.clear();
//            statistiquesParJourList = statistiquesParJourRepository.findByContentAndDateDBBetween(content, date1, date2);
//            statistiquesParJourList.forEach(System.out::println);
              System.out.println("option " + option);
        };
    }
    // créer BCryptPasswordEncoder au démarrage de l'appli pour injection dans couche Service
    @Bean
    BCryptPasswordEncoder getBCPE() {
       return new BCryptPasswordEncoder();
    }

}

