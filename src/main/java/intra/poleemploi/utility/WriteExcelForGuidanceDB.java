package intra.poleemploi.utility;

import intra.poleemploi.entities.Appli;
import intra.poleemploi.entities.Content;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcelForGuidanceDB {

//    @Autowired
//    private RepositoryRestConfiguration repositoryRestConfiguration;
//    @Autowired
//    AppliRepository appliRepository;

    public WriteExcelForGuidanceDB() {
      //  repositoryRestConfiguration.exposeIdsFor(Appli.class);
    }

    public void appliInsertIntoExcelFile(List<Appli> listAppli) throws IOException {

        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet("Applications");

  //      List<Appli> listAppli;
 //       listAppli = appliRepository.findAll();

        int rowCount = 0;

        for (Appli appli : listAppli) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            Cell cell = row.createCell(++columnCount);
            cell.setCellValue((String) appli.getIdAppliKM());
            cell = row.createCell(++columnCount);
            cell.setCellValue((String) appli.getAppliName());
            cell = row.createCell(++columnCount);
            cell.setCellValue((String) appli.getAppliURL());

        }
        try (FileOutputStream outputStream = new FileOutputStream("C:/demo/KnowMore/Appli.xlsx")) {
            workBook.write(outputStream);
        }
    }

    public void ContentInsertIntoExcelFile(List<Content> listContents) throws IOException {

        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet("Contents");

        //      List<Appli> listAppli;
        //       listAppli = appliRepository.findAll();

        int rowCount = 0;

        for (Content content : listContents) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            Cell cell = row.createCell(++columnCount);
            cell.setCellValue((String) content.getIdContentKM());
            cell = row.createCell(++columnCount);
            cell.setCellValue((Boolean) content.getPublished());
            cell = row.createCell(++columnCount);
            cell.setCellValue((String) content.getTypeService());
            cell = row.createCell(++columnCount);
            cell.setCellValue((String) content.getContentName());
            cell = row.createCell(++columnCount);
            cell.setCellValue((Integer) content.getNbAffichages());
            cell = row.createCell(++columnCount);
            cell.setCellValue((Integer) content.getNbLectures());
            cell = row.createCell(++columnCount);
            cell.setCellValue((String) content.getAppli().getIdAppliKM());
           // content.setIdContentKM(cols.get(0).text());
//            if (cols.get(2).text().equals("Publiée")) {
//                content.setPublished(true);
//            } else {
//                content.setPublished(false);
//            }
//            content.setTypeService(cols.get(3).text());
//            content.setContentName( cols.get(4).text());
//
//            content.setNbAffichages(Integer.valueOf(cols.get(5).text()));
//            content.setNbLectures(Integer.valueOf(cols.get(6).text()));
//            content.setAppli(appli);

        }
        try (FileOutputStream outputStream = new FileOutputStream("C:/demo/KnowMore/contents.xlsx")) {
            workBook.write(outputStream);
        }
    }
}

