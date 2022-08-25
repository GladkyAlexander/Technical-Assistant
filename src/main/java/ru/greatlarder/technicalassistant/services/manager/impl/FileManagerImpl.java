package ru.greatlarder.technicalassistant.services.manager.impl;


import ru.greatlarder.technicalassistant.services.manager.FileManager;

import java.io.File;

public class FileManagerImpl implements FileManager {

    private static final String currentFolder =  javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().toString();
    private final String directoryProject = "Technical_Assistant";
    private final String directoryDB = "Data";
    private final String directoryCompany = "Company";
    private final String directoryImage = "Image";
    private final String directoryManual = "Manual";
    private final String directoryDocumentations = "Documentations";

    @Override
    public void createProjectDirectories() {
        File dir = new File( currentFolder + "\\" + directoryProject);
        File dirDB = new File( currentFolder + "\\" + directoryProject + "\\" + directoryDB);
        File dirCompany = new File( currentFolder + "\\" + directoryProject + "\\" + directoryCompany);
        File dirImage = new File( currentFolder + "\\" + directoryProject + "\\" + directoryImage);
        File dirManual = new File( currentFolder + "\\" + directoryProject + "\\" + directoryManual);
        if(!dir.exists()) {
            dir.mkdir();
            //directoryProject = dir.getAbsolutePath();
        }
        if(!dirDB.exists()) {
            dirDB.mkdir();
            //directoryDB = dirDB.getAbsolutePath();
        }
        if(!dirCompany.exists()) {
            dirCompany.mkdir();
            //directoryCompany = dirCompany.getAbsolutePath();
        }
        if(!dirImage.exists()) {
            dirImage.mkdir();
            //directoryImage = dirImage.getAbsolutePath();
        }
        if(!dirManual.exists()) {
            dirManual.mkdir();
            //directoryManual = dirManual.getAbsolutePath();
        }
    }

    @Override
    public void createDirectoryCompany(String nameCompany) {
        createProjectDirectories();
        File dir = new File( directoryCompany + "\\" + nameCompany);
        File dirDoc = new File(directoryCompany + "\\" + nameCompany + "\\" + directoryDocumentations);
        if(!dir.exists()) {
            dir.mkdir();
            //directoryDocumentations = dir.getAbsolutePath();
        }
        if(!dirDoc.exists()){
            dirDoc.mkdir();
            //directoryDocumentations = dirDoc.getAbsolutePath();
        }
    }

    @Override
    public String folderDB() {
        return currentFolder + "\\" + directoryProject + "\\" + directoryDB;
    }

    @Override
    public String folderImage() {
        return currentFolder + "\\" + directoryProject + "\\" + directoryImage;
    }

    @Override
    public String folderProject() {
        return currentFolder + "\\" + directoryProject;
    }

    @Override
    public String folderManual() {
        return currentFolder + "\\" + directoryProject + "\\" + directoryManual;
    }

    @Override
    public String folderCompanyDocumentation(String nameCompany) {
        return currentFolder + "\\" + directoryProject + "\\" + nameCompany + "\\" + directoryDocumentations;
    }

    @Override
    public String getUrlFileManual(String nameFile) {
        File currentFolderAsFile = new File(currentFolder + "\\" + directoryProject + "\\" + directoryManual);
        File[] files = currentFolderAsFile.listFiles();

        assert files != null;
        for (File file : files){
            if (file.getName().equals(nameFile)){
                return file.getAbsolutePath();
            }
        }
        return null;
    }
    @Override
    public String getUrlFileImage(String nameFile) {
        File currentFolderAsFile = new File(currentFolder + "\\" + directoryProject + "\\" + directoryImage);
        File[] files = currentFolderAsFile.listFiles();

        assert files != null;
        for (File file : files){
            if (file.getName().equals(nameFile)){
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    @Override
    public String getUrlFileDocumentations(String nameCompany, String nameFile) {
        File currentFolderAsFile = new File(currentFolder + "\\" + directoryProject + "\\" + nameCompany + "\\" + directoryDocumentations);
        File[] files = currentFolderAsFile.listFiles();

        assert files != null;
        for (File file : files){
            if (file.getName().equals(nameFile)){
                return file.getAbsolutePath();
            }
        }
        return null;
    }
}
