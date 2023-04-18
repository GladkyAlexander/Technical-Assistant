package ru.greatlarder.technicalassistant.services.manager.impl;


import ru.greatlarder.technicalassistant.services.manager.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManagerImpl implements FileManager {

    private static final String currentFolder =  javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().toString();
    private final String directoryProject = "Technical_Assistant";
    private final String directoryDB = "Data";
    private final String directoryCompany = "Company";
    private final String directoryImage = "Image";
    private final String directoryManual = "Manual";
    private final String directoryDocumentations = "Documentations";
    private final String directorySeatingArrangements = "SeatingArrangements";
    private final String directoryInstructions = "Instructions";

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
        File dir = new File(folderProject() + "\\" + directoryCompany + "\\" + nameCompany);

        if(!dir.exists()) {
            dir.mkdir();
            //directoryDocumentations = dir.getAbsolutePath();
        }
        File dirDoc = new File(folderProject() + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directoryDocumentations);
        if(!dirDoc.exists()){
            dirDoc.mkdir();
            //directoryDocumentations = dirDoc.getAbsolutePath();
        }
        File dirSettingArg = new File(folderProject() + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directorySeatingArrangements);
        if(!dirSettingArg.exists()){
            dirSettingArg.mkdir();
            //directorySeatingArguments = dirSettingArg.getAbsolutePath();
        }
        File dirInstruction = new File(folderProject() + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directoryInstructions);
        if(!dirInstruction.exists()){
            dirInstruction.mkdir();
            //directoryInstructions = dirInstruction.getAbsolutePath();
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
        return currentFolder + "\\" + directoryProject + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directoryDocumentations;
    }

    @Override
    public String folderCompanyInstruction(String nameCompany) {
        return currentFolder + "\\" + directoryProject + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directoryInstructions;
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
        File currentFolderAsFile = new File(currentFolder + "\\" + directoryProject + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directoryDocumentations);
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
    public String getUrlFileSeatingArrangements(String nameCompany, String nameFile) {
        File currentFolderAsFile = new File(currentFolder + "\\" + directoryProject + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directorySeatingArrangements);
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
    public String getUrlFileInstructions(String nameCompany, String nameFile) {
        File currentFolderAsFile = new File(currentFolder + "\\" + directoryProject + "\\" + directoryCompany + "\\" + nameCompany + "\\" + directoryInstructions);
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
    public List<String> getListOfFileNamesInTheDirectory(String nameCompany, String nameDirectory) {

        createDirectoryCompany(nameCompany);

        List<String> list = new ArrayList<>();

        if(nameDirectory.equals(directoryDocumentations)) {
            File currentFolderAsFile = new File(folderCompanyDocumentation(nameCompany));
            File[] files = currentFolderAsFile.listFiles();
            if(files != null) {
                for (File file : files) {
                    list.add(file.getName());
                }
            }
        }
        if(nameDirectory.equals(directoryInstructions)) {
            File currentFolderAsFile = new File(folderCompanyInstruction(nameCompany));
            File[] files = currentFolderAsFile.listFiles();
            if(files != null) {
                for (File file : files) {
                    list.add(file.getName());
                }
            }
        }
        return list;
    }

    @Override
    public boolean checkingPresenceFileDirectoryImage(String fileName) {

        File currentFolderAsFile = new File(folderImage());
        File[] files = currentFolderAsFile.listFiles();
        if(files != null) {
            for (File file : files) {
                if(fileName.equals(file.getName())) return true;
            }
        }
        return false;
    }


}
