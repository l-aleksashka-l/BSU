import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class FirstThread implements Runnable {

    //String testPath = "/Users/aleksashka/Desktop/Projects/BSU/OS/3_lab/tests";

    String testPath;
    ArrayList<File> goodFiles = new ArrayList<>();
    File file = new File("goodFiles.txt");

    public FirstThread(String testPath) {
        this.testPath = testPath;
    }

    public static ArrayList<File> listFilesForFolder(final File folder) {
        ArrayList<File> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            files.add(fileEntry);
        }

        return files;
    }


    public static Boolean chekerByFilesValue(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().length() > 100)
                return false;
        }
        return true;
    }


    @Override
    public void run() {

        final File folder = new File(testPath);
        try {
            for (File f : listFilesForFolder(folder)) {
                try {
                    if (chekerByFilesValue(f)) {
                        FileWriter fileWriter = new FileWriter(file, true);
                        fileWriter.append(f.getName() + "\n");
                        fileWriter.close();
                        System.out.println("I: " + f.getName() + " good file!\n" );
                    }else{
                        System.out.println("I: " + f.getName() + " bad file!\n" );
                    }
                    sleep(1000);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
