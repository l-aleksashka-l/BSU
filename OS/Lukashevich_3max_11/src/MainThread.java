import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainThread {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter path to folder with tests:");

        // /Users/aleksashka/Desktop/Projects/BSU/OS/3_lab/tests/

        Scanner s = new Scanner(System.in);
        String testPath = s.nextLine();

        System.out.println("Enter path to folder with outs:");

        // /Users/aleksashka/Desktop/Projects/BSU/OS/3_lab/outs/

        String outPath = s.nextLine();

        System.out.println("Enter path to folder with answers:");

        // /Users/aleksashka/Desktop/Projects/BSU/OS/3_lab/answers/


        String answerPath = s.nextLine();

        System.out.println("\n\n");


        Thread t1 = new Thread(new FirstThread(testPath));
        t1.start();

        Thread t2 = new Thread(new SecondThread(testPath, outPath));
        t2.start();

        Thread t3 = new Thread(new ThirdThread(outPath, answerPath));
        t3.start();




    }
}

