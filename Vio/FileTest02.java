package Vio.test;

import java.io.*;

public class FileTest02 {
    public static void main(String[] args) throws IOException {
        File fileFolder = new File("folder");
        boolean isFolderCreated = fileFolder.mkdir();
        System.out.println("Folder created: " + isFolderCreated);




        File file = new File(fileFolder, "file01.txt");

        boolean isFileCreated = file.createNewFile();
        System.out.println("File created: " + isFileCreated);

        File fileRenamed = new File(fileFolder, "file01_renamed.txt");
        boolean isRenamed = file.renameTo(fileRenamed);
        System.out.println("File renamed: " + isRenamed);

        File fileFolderRenamed = new File(fileFolder, "folder_renamed");
        boolean isFolderRenamed = fileFolder.renameTo(fileFolderRenamed);
        System.out.println("Folder renamed: " + isFolderRenamed);

    }


}
