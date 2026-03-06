package Vio.test;

import java.io.*;

public class FileReaderTest01 {
    public static void main(String[] args) {

        File file = new File("file01.txt");
        try(FileReader fr = new FileReader(file)) {
//            char[] buffer = new char[14];
//            int read = fr.read(buffer);
//            for(char c : buffer) {
//                System.out.print(c);
//            }
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
