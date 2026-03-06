package Vio.test;

import java.io.*;

public class FileWriterTest01 {
    public static void main(String[] args) {
        File file = new File("file01.txt");
        try(FileWriter fw = new FileWriter(file, true)) {
            fw.write("Hello World <3");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
