package Vio.test;

import java.io.*;

public class BuffedWriterTest01 {
    public static void main(String[] args) {
        File file = new File("file01.txt");
        try(FileWriter fw = new FileWriter(file, true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello World <3");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
