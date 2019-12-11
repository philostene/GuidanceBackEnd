package intra.poleemploi.service;

import intra.poleemploi.dao.AppliRepository;
import intra.poleemploi.entities.Appli;
import intra.poleemploi.utility.ReadExcel;

import java.io.IOException;
import java.util.List;

public class WriteApplisIntoDataBase {

    public void WriteApplisIntoDataBase() {
    }

    public void writeApplisIntoDabase(AppliRepository appliRepository) throws IOException {
        List<Appli> listAppli;
        ReadExcel readExcel = new ReadExcel();
        listAppli = readExcel.getAppliList();
        for (Appli tempAppli : listAppli) {
            appliRepository.save(tempAppli);
        }
        appliRepository.findAll().forEach(System.out::println);

    }
}
