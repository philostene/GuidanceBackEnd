package intra.poleemploi.service;

import intra.poleemploi.entities.Appli;

import java.util.List;

public interface AppService {
    // enregistrer une Appli
    //public Appli saveAppli(String appliName);

    // enregistrer un content
    //public Content saveContent(Content content);

    // ajouter un content à une Appli
    // public void addContentToAppli(String appliName, String contentName);

    // supprimer les données
    //public void deleteAppli();

    //récupérer liste des Appli
    public List<Appli> findAllAppli();

    // récupérer une Appli par son Id
    //public Appli findAppliById(int id);


}
