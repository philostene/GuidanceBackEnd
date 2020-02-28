package intra.poleemploi.utility;

import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.Content;
//import intra.poleemploi.entities.StatistiquesParJour;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ReadExcel {


    public ReadExcel() {
    }

    private Iterator<Row> readExcelFile(String pathName) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(pathName));
        // Get the workbook instance for XLS file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        // Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Get iterator to all the rows in current sheet
        return sheet.iterator();
    }

    public List<Appli> getAppliList() throws IOException {
        String pathName = "C:/demo/KnowMore/Appli.xlsx";
        //lecture excel file
        Iterator<Row> rowIterator = readExcelFile(pathName);

        List<Appli> appliList = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            //List<String> listRow = new ArrayList<>();
            Appli appli = new Appli();
            if (row.getRowNum() > 0) {
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            appli.setIdAppliKM(cell.getStringCellValue());
                            break;
                        case 1:
                            appli.setAppliName((cell.getStringCellValue()));
                            break;
                        case 2:
                            appli.setAppliURL(cell.getStringCellValue());
                            break;
                    }
                }
                appliList.add(appli);
            }
        }
        return appliList;
    }

//    public List<Content> getContentList(List<Appli> appliList) throws IOException {
//        String pathName = "C:/demo/KnowMore/contents.xlsx";
//        // Read XSL file
//        //lecture excel file
//        Iterator<Row> rowIterator = readExcelFile(pathName);
//
//        List<Content> contentList = new ArrayList<>();
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            // Get iterator to all cells of current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//            //List<String> listRow = new ArrayList<>();
//            Content content = new Content();
//            if (row.getRowNum() > 0) {
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//
//                    switch (cell.getColumnIndex()) {
//                        case 0:
//                        //    content.setContentId((int) cell.getNumericCellValue());
//                            break;
//                        case 1:
//                            content.setContentName(cell.getStringCellValue());
//                            break;
//                        case 2:
//                            try {
//                                content.setPublished(Boolean.parseBoolean(cell.getStringCellValue()));
//                            }
//                            catch (IllegalStateException e) {System.out.println("error" + content.getContentName() + " error " + e.getMessage());}
//                            break;
//                        case 3:
//                      //      content.setDescription(cell.getStringCellValue());
//                            break;
//                        case 4:
//                            try {
//                                content.setNbLectures((int) cell.getNumericCellValue());
//                            }
//                             catch (IllegalStateException e) {System.out.println("error" + content.getContentName() + " error " + e.getMessage());}
//                            break;
//                        case 5:
//                            content.setNbAffichages((int) cell.getNumericCellValue());
//                            break;
//                        case 6:
//                  //          content.setIcone(cell.getStringCellValue());
//                            break;
//                        case 7:
//                   //         content.setContentURL(cell.getStringCellValue());
//                            break;
//                        case 8:
//                           // content.setDebut(cell.getDateCellValue());
//                            break;
//                        case 9:
//                         //   content.setFin(cell.getDateCellValue());
//                            break;
//                        case 10:
//                            for (Appli appli : appliList) {
//                                if (appli.getIdAppliKM().equals(cell.getStringCellValue())) {
//                                    content.setAppli(appli);
//                                    break;
//                                }
//                            }
//                            break;
//                        default:
//                            throw new IllegalStateException("Unexpected value: " + cell.getColumnIndex());
//                    }
//                }
//                contentList.add(content);
//            }
//        }
//        return contentList;
//    }

//    public List<StatistiquesParJour> getStatistiquesParJourList(List<Content> contentList) throws IOException {
//        String pathName = "C:/demo/KnowMore/dailyStatistics_pn066_2018-10-02_2019-10-02.xlsx";
//        Iterator<Row> rowIterator = readExcelFile(pathName);
//
//        List<StatistiquesParJour> statistiquesParJourList = new ArrayList<>();
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            // Get iterator to all cells of current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//            //List<String> listRow = new ArrayList<>();
//            if (row.getRowNum() > 0) {
//                StatistiquesParJour statistiquesParJour = new StatistiquesParJour();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//
//                    switch (cell.getColumnIndex()) {
//                        case 0:
//                            statistiquesParJour.setDate(cell.getDateCellValue());
//                            break;
//                        case 1:
//                            statistiquesParJour.setNbAffichage((long) cell.getNumericCellValue());
//                            break;
//                        case 2:
//                            statistiquesParJour.setNbUsersAyantAffichesLaPastille((long) cell.getNumericCellValue());
//                            break;
//                        case 3:
//                            statistiquesParJour.setNbDeLectureDeLaPastille((long) cell.getNumericCellValue());
//                            break;
//                        case 4:
//                            statistiquesParJour.setNbUsersAyantLusLaPastille((long) cell.getNumericCellValue());
//                            break;
//                        case 5:
//                            statistiquesParJour.setTempsPasseSurLaPastilleMS((long) cell.getNumericCellValue());
//                            break;
//                        case 6:
//                    //        for (Content content : contentList) {
//                    //            if (content.getContentId() == cell.getNumericCellValue()) {
//                    //                statistiquesParJour.setContent(content);
//                    //                break;
//                    //            }
//                    //        }
//                            break;
//                    }
//                }
//                statistiquesParJourList.add(statistiquesParJour);
//            }
//        }
//        return statistiquesParJourList;
//    }
}