package Vio.test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class FileTest01 {
    public static void main(String[] args) {
        File file = new File("file.txt");
        try {
            boolean isCreated = file.createNewFile();
            System.out.println("Created: " + isCreated);
            boolean exists = file.exists();
            System.out.println("Exists: " + exists);
            System.out.println("Path: " + file.getPath());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("File: " + file.isFile());
            System.out.println("Directory: " + file.isDirectory());
            System.out.println("Hidden: " + file.isHidden());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Last modified: " + new Date(file.lastModified()));
            System.out.println("Last Modified: " + Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.systemDefault()));
            if(exists){
                boolean isDeleted = file.delete();
                System.out.println("Deleted: " + isDeleted);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
