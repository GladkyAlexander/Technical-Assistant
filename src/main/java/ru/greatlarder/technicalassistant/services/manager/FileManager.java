package ru.greatlarder.technicalassistant.services.manager;

public interface FileManager {
    void createProjectDirectories();
    void createDirectoryCompany(String directoryName);
    String folderDB();
    String folderImage();
    String folderProject();
    String folderManual();
    String folderCompanyDocumentation(String nameCompany);
    String getUrlFileManual(String nameFile);
    String getUrlFileImage(String nameFile);
    String getUrlFileDocumentations(String nameCompany, String nameFile);
}
