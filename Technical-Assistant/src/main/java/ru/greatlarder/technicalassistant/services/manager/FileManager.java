package ru.greatlarder.technicalassistant.services.manager;

import java.util.List;

public interface FileManager {
    void createProjectDirectories();
    void createDirectoryCompany(String directoryName);
    String folderDB();
    String folderImage();
    String folderProject();
    String folderManual();
    String folderCompanyDocumentation(String nameCompany);
    String folderCompanyInstruction(String nameCompany);
    String getUrlFileManual(String nameFile);
    String getUrlFileImage(String nameFile);
    String getUrlFileDocumentations(String nameCompany, String nameFile);
    String getUrlFileSeatingArrangements(String nameCompany, String nameFile);
    String getUrlFileInstructions(String nameCompany, String nameFile);
    List<String> getListOfFileNamesInTheDirectory(String nameCompany, String nameFolder);
    boolean checkingPresenceFileDirectoryImage(String fileName);
}
