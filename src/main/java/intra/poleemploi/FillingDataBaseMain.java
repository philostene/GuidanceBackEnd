package intra.poleemploi;//package intra.poleemploi.utility;

import intra.poleemploi.repository.*;
import intra.poleemploi.entities.*;
import intra.poleemploi.service.AuthService;
import intra.poleemploi.utility.writingDatabaseElement.WritingDatabaseElement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
    //(scanBasePackages={"intra.poleemploi"})

class FillingDataBaseMain {
    //    @Autowired
    //   private RepositoryRestConfiguration repositoryRestConfiguration;
    // Décommenter l'option désirée
    // private String option = "WriteDataBaseFromExcel"; //write database from Excel
    private String option = "WriteDataBaseFromKM";  //Write database from KM
    //private String option = "WriteExcelFromDataBase"; //Write Excel from database

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FillingDataBaseMain.class, args);
        //  ctx.close();
    }

    @Bean
    CommandLineRunner startFillingDataBase(StatistiquesParJourRepository statistiquesParJourRepository, RepositoryRestConfiguration repositoryRestConfiguration, AppliRepository appliRepository, ContentRepository contentRepository, AuthService authService, UserAppRepository userAppRepository, RoleAppRepository roleAppRepository) {
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Appli.class, Content.class, UserApp.class, RoleApp.class, AuthService.class, StatistiquesParJour.class);
//            String testJson = "[{/"id/":/"appliSimulator/",/"name/":/"Bac /u00E0 sable/",/"icon/":null,/"applicationUrl/":/"//know//test//appliSimulator///",/"url/":/"//view//publication//batches?applicationId=appliSimulator/"},{/"id/":/"pn119/",/"name/":/"pn119/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn119/"},{/"id/":/"everestfab/",/"name/":/"everestfab/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=everestfab/"},{/"id/":/"hugov4fab/",/"name/":/"hugov4fab/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=hugov4fab/"},{/"id/":/"proactivite/",/"name/":/"proactivite/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=proactivite/"},{/"id/":/"dunev4fab/",/"name/":/"dunev4fab/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=dunev4fab/"},{/"id/":/"pn039/",/"name/":/"MAP Diagnostic et actions/",/"icon/":/"/",/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn039/"},{/"id/":/"pn007/",/"name/":/"BPM/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn007/"},{/"id/":/"pn059/",/"name/":/"BEN Pe.fr profil Professionnel/",/"icon/":/"/",/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn059/"},{/"id/":/"pn058/",/"name/":/"Mon Potentiel Professionnel/",/"icon/":/"/",/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn058/"},{/"id/":/"mrs/",/"name/":/"MRS Digitale/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=mrs/"},{/"id/":/"AUDEP/",/"name/":/"AUDE Presta/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=AUDEP/"},{/"id/":/"pn040/",/"name/":/"BEN PE.fr Inscription/",/"icon/":/"/",/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn040/"},{/"id/":/"pn140/",/"name/":/"Recherche de Profil Conseiller/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn140/"},{/"id/":/"pn063/",/"name/":/"MAP Vue DE/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn063/"},{/"id/":/"MESI/",/"name/":/"MESI/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=MESI/"},{/"id/":/"pn103/",/"name/":/"Pilotage par les r/u00E9sultats/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn103/"},{/"id/":/"pn111/",/"name/":/"Widg'E/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn111/"},{/"id/":/"PEL/",/"name/":/"PEL/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=PEL/"},{/"id/":/"PR056/",/"name/":/"GID Partenaire/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=PR056/"},{/"id/":/"pn060/",/"name/":/"BEN PE.fr Recruteur/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn060/"},{/"id/":/"pn124/",/"name/":/"Portail des Activit/u00E9s Agence/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn124/"},{/"id/":/"pn116/",/"name/":/"JRE Conseiller/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn116/"},{/"id/":/"po002/",/"name/":/"BEN Recruteur/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=po002/"},{/"id/":/"onearagon/",/"name/":/"WePlan/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=onearagon/"},{/"id/":/"pn098/",/"name/":/"MAP ---/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn098/"},{/"id/":/"pn068/",/"name/":/"OuiForm/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn068/"},{/"id/":/"pn106/",/"name/":/"Panda/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn106/"},{/"id/":/"pn133/",/"name/":/"Vers un Metier/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn133/"},{/"id/":/"AUDEV4/",/"name/":/"AUDE V4/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=AUDEV4/"},{/"id/":/"so017/",/"name/":/"BEN PE.fr Connexion Candidat/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=so017/"},{/"id/":/"pn087/",/"name/":/"NG AGEPI/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn087/"},{/"id/":/"eurecafab/",/"name/":/"eurecafab/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=eurecafab/"},{/"id/":/"PRIOP/",/"name/":/"IOP/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=PRIOP/"},{/"id/":/"AGC/",/"name/":/"AGC/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=AGC/"},{/"id/":/"kairosfab/",/"name/":/"kairosfab/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=kairosfab/"},{/"id/":/"pn069/",/"name/":/"MAP Recherche/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn069/"},{/"id/":/"so007/",/"name/":/"BEN Authentification E/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=so007/"},{/"id/":/"pn066/",/"name/":/"Profil de Comp/u00E9tences/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn066/"},{/"id/":/"pn105/",/"name/":/"BEN PE.fr aides pn105/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn105/"},{/"id/":/"pn073/",/"name/":/"BEN PE.fr Espace Connexion/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn073/"},{/"id/":/"pn104/",/"name/":/"BEN Aide E/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn104/"},{/"id/":/"pn052/",/"name/":/"BEN PE.fr Formation pn052/",/"icon/":null,/"applicationUrl/":/"/",/"url/":/"//view//publication//batches?applicationId=pn052/"}]";
//            ObjectMapper mapper = new ObjectMapper();
//            Applications applications = new Applications();
//            Applications[] applicationsList = mapper.readValue("[{\"id\":\"appliSimulator\",\"name\":\"Bac \u00E0 sable\",\"icon\":null,\"applicationUrl\":\"\/know\/test\/appliSimulator\/\",\"url\":\"\/view\/publication\/batches?applicationId=appliSimulator\"},{\"id\":\"pn119\",\"name\":\"pn119\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn119\"},{\"id\":\"everestfab\",\"name\":\"everestfab\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=everestfab\"},{\"id\":\"hugov4fab\",\"name\":\"hugov4fab\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=hugov4fab\"},{\"id\":\"proactivite\",\"name\":\"proactivite\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=proactivite\"},{\"id\":\"dunev4fab\",\"name\":\"dunev4fab\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=dunev4fab\"},{\"id\":\"pn039\",\"name\":\"MAP Diagnostic et actions\",\"icon\":\"\",\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn039\"},{\"id\":\"pn007\",\"name\":\"BPM\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn007\"},{\"id\":\"pn059\",\"name\":\"BEN Pe.fr profil Professionnel\",\"icon\":\"\",\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn059\"},{\"id\":\"pn058\",\"name\":\"Mon Potentiel Professionnel\",\"icon\":\"\",\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn058\"},{\"id\":\"mrs\",\"name\":\"MRS Digitale\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=mrs\"},{\"id\":\"AUDEP\",\"name\":\"AUDE Presta\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=AUDEP\"},{\"id\":\"pn040\",\"name\":\"BEN PE.fr Inscription\",\"icon\":\"\",\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn040\"},{\"id\":\"pn140\",\"name\":\"Recherche de Profil Conseiller\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn140\"},{\"id\":\"pn063\",\"name\":\"MAP Vue DE\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn063\"},{\"id\":\"MESI\",\"name\":\"MESI\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=MESI\"},{\"id\":\"pn103\",\"name\":\"Pilotage par les r\u00E9sultats\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn103\"},{\"id\":\"pn111\",\"name\":\"Widg'E\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn111\"},{\"id\":\"PEL\",\"name\":\"PEL\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=PEL\"},{\"id\":\"PR056\",\"name\":\"GID Partenaire\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=PR056\"},{\"id\":\"pn060\",\"name\":\"BEN PE.fr Recruteur\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn060\"},{\"id\":\"pn124\",\"name\":\"Portail des Activit\u00E9s Agence\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn124\"},{\"id\":\"pn116\",\"name\":\"JRE Conseiller\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn116\"},{\"id\":\"po002\",\"name\":\"BEN Recruteur\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=po002\"},{\"id\":\"onearagon\",\"name\":\"WePlan\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=onearagon\"},{\"id\":\"pn098\",\"name\":\"MAP ---\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn098\"},{\"id\":\"pn068\",\"name\":\"OuiForm\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn068\"},{\"id\":\"pn106\",\"name\":\"Panda\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn106\"},{\"id\":\"pn133\",\"name\":\"Vers un Metier\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn133\"},{\"id\":\"AUDEV4\",\"name\":\"AUDE V4\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=AUDEV4\"},{\"id\":\"so017\",\"name\":\"BEN PE.fr Connexion Candidat\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=so017\"},{\"id\":\"pn087\",\"name\":\"NG AGEPI\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn087\"},{\"id\":\"eurecafab\",\"name\":\"eurecafab\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=eurecafab\"},{\"id\":\"PRIOP\",\"name\":\"IOP\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=PRIOP\"},{\"id\":\"AGC\",\"name\":\"AGC\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=AGC\"},{\"id\":\"kairosfab\",\"name\":\"kairosfab\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=kairosfab\"},{\"id\":\"pn069\",\"name\":\"MAP Recherche\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn069\"},{\"id\":\"so007\",\"name\":\"BEN Authentification E\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=so007\"},{\"id\":\"pn066\",\"name\":\"Profil de Comp\u00E9tences\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn066\"},{\"id\":\"pn105\",\"name\":\"BEN PE.fr aides pn105\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn105\"},{\"id\":\"pn073\",\"name\":\"BEN PE.fr Espace Connexion\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn073\"},{\"id\":\"pn104\",\"name\":\"BEN Aide E\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn104\"},{\"id\":\"pn052\",\"name\":\"BEN PE.fr Formation pn052\",\"icon\":null,\"applicationUrl\":\"\",\"url\":\"\/view\/publication\/batches?applicationId=pn052\"}]" , Applications[].class);
//            System.out.println("toto");

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
            //      appliRepository.findAll().forEach(System.out::println);
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
            //       authService.saveRoleApp(new RoleApp( "ADMIN",null));

            // ajout users
            //        Stream.of("user1", "user2", "user3", "user4", "admin").forEach(username -> authService.saveUserApp(username, "1234", "1234"));

            // ajout role ADMIN a l'admin
            //         authService.addRoleToUser("admin", "ADMIN");
            //         authService.addRoleToUser("user1", "USER");
            //         authService.addRoleToUser("user2", "USER");
            //         authService.addRoleToUser("user3", "USER");
            //         authService.addRoleToUser("user4", "USER");

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

            //writing all appalications in local database for Https protocol
  //          WritingDatabaseElement writingDatabaseElement = new WritingDatabaseElement();
  //          writingDatabaseElement.writingApplications(appliRepository);

            //writing all appalications in local database
  //          writingDatabaseElement.writingContents(contentRepository, appliRepository);

  //          writingDatabaseElement.writingStatistics(contentRepository, statistiquesParJourRepository);
//            String fromDate = "12/01/2019";
//            String toDate = "02/27/2020";
 //           String pubId = "988";
//            Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(fromDate);
//            Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
//            Content content = contentRepository.findById(Integer.parseInt(pubId));
//            List<StatistiquesParJour> statistiquesParJourList = statistiquesParJourRepository.findByContentAndDateDBBetween(content, date1, date2);
//             statistiquesParJourList = statistiquesParJourRepository.findByContent(content);
//            statistiquesParJourList = statistiquesParJourRepository.findByContent(content);
//            statistiquesParJourList = statistiquesParJourRepository.findByContentAndDateDBAfter(content, "7 févr. 2020");

            //mise à jour dateDB
            /*List<StatistiquesParJour> statistiquesParJourList = statistiquesParJourRepository.findAll();
            List<StatistiquesParJour> statistiquesParJourList1 = new ArrayList<>();
            statistiquesParJourRepository.deleteAll();
            for(StatistiquesParJour statistiquesParJour : statistiquesParJourList){
                Date date =new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE).parse(statistiquesParJour.getDate());
                statistiquesParJour.setDateDB(date);
                statistiquesParJourRepository.save(statistiquesParJour);
                statistiquesParJourList1.add(statistiquesParJour);

                System.out.println("date =" + statistiquesParJour.getDate() + " ==> " + date);
            } */

            System.out.println("option " + option);
        };
    }

    // créer BCryptPasswordEncoder au démarrage de l'appli pour injection dans couche Service
    @Bean
    BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

}

