import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ThirdThread implements Runnable{

    String outPath;
    String answerPath;

    public ThirdThread(String outPath, String answerPath) {
        this.outPath = outPath;
        this.answerPath = answerPath;
    }

    public static void files(ArrayList<String> filess) throws IOException {
        File file = new File("out.txt");
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);
        for(int i = 0; i < filess.size();i++)
            scanner.nextLine();
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            filess.add(a);

        }
    }

    public static boolean checker(String filename,String outPath, String answerPath) throws FileNotFoundException {
        Scanner out = new Scanner(new FileReader(new File(outPath + filename)));
        Scanner answer = new Scanner(new FileReader(new File(answerPath + filename)));
        while(out.hasNextLine()){
            if(!out.nextLine().equals(answer.nextLine()))
                return false;
        }
        return true;
    }


    @Override
    public void run() {
        ArrayList<String> files = new ArrayList<>();
        try {
            int i = 0;
            int counter = 0;
            while (i < 50) {
                sleep(1000);
                i++;
                files(files);
                for (int j = files.size(); j > counter; j--) {
                    String str = files.get(j - 1);

                    if(checker(str, outPath, answerPath)){
                        FileWriter fileWriter = new FileWriter(new File("result.txt"), true);
                        fileWriter.append(str + ": test approved\n");
                        System.out.println("III: " + str + ": test approved\n");
                        fileWriter.close();
                    }else{
                        FileWriter fileWriter = new FileWriter(new File("result.txt"), true);
                        fileWriter.append(str + ": test not approved\n");
                        System.out.println("III: " + str + ": test not approved\n");
                        fileWriter.close();
                    }

                }
                counter=files.size();
                sleep(10);

            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
