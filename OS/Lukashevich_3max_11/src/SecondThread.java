import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SecondThread implements Runnable {

    String testPath;
    String outPath;

    public SecondThread(String testPath, String outPath) {
        this.testPath = testPath;
        this.outPath = outPath;
    }
    public static String solver(String a, String b) {
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        Map<Character, Integer> MapA = new TreeMap<>();
        Map<Character, Integer> MapB = new TreeMap<>();
        Set<Character> checkerA = new HashSet<>();
        Set<Character> checkerB = new HashSet<>();
        if (A.length == (B.length)) {
            for (Character x : A) {
                if (!MapA.containsKey(x)) {
                    MapA.put(x, 1);
                    checkerA.add(x);
                } else {
                    int r = MapA.get(x);
                    MapA.remove(x);
                    MapA.put(x, r + 1);
                    if (checkerA.contains(x))
                        checkerA.remove(x);
                }
            }
            for (Character x : B) {
                if (!MapB.containsKey(x)) {
                    MapB.put(x, 1);
                    checkerB.add(x);
                } else {
                    int r = MapB.get(x);
                    MapB.remove(x);
                    MapB.put(x, r + 1);
                    if (checkerB.contains(x))
                        checkerB.remove(x);
                }
            }
            if (MapA.size() == MapB.size()) {


                Map<Character, Integer> resultA = MapA.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                Map<Character, Integer> resultB = MapB.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
               /* System.out.println(resultA);
                System.out.println(resultB);*/
                Stack<Integer> AA = new Stack<>();
                Stack<Integer> BB = new Stack<>();
                Map<Integer, Integer> checkerAA = new HashMap<>();
                Map<Integer, Integer> checkerBB = new HashMap<>();


                for (Map.Entry<Character, Integer> entry : MapA.entrySet()) {
                    int temp = entry.getValue();
                    AA.push(temp);
                    if (!checkerAA.containsKey(temp)) {
                        checkerAA.put(temp, 1);

                    } else {
                        int r = checkerAA.get(temp);
                        checkerAA.remove(temp);
                        checkerAA.put(temp, r + 1);
                    }
                }


                for (Map.Entry<Character, Integer> entry : MapB.entrySet()) {
                    int temp = entry.getValue();
                    BB.push(temp);
                    if (!checkerBB.containsKey(temp)) {
                        checkerBB.put(temp, 1);

                    } else {
                        int r = checkerBB.get(temp);
                        checkerBB.remove(temp);
                        checkerBB.put(temp, r + 1);
                    }
                }
                while (!AA.isEmpty()) {
                    if (!AA.pop().equals(BB.pop()))
                        return "NO";
                }

                if (checkerAA.containsValue(1))
                    for (Map.Entry<Integer, Integer> entry : checkerAA.entrySet()) {
                        if (entry.getValue() == 1) {
                            Optional<Character> aaa = MapA.entrySet()
                                    .stream()
                                    .filter(entry1 -> entry.getKey().equals(entry1.getValue()))
                                    .map(Map.Entry::getKey)
                                    .findFirst();
                            Optional<Character> bbb = MapB.entrySet()
                                    .stream()
                                    .filter(entry2 -> entry.getKey().equals(entry2.getValue()))
                                    .map(Map.Entry::getKey)
                                    .findFirst();
                            if (aaa.equals(bbb)) {
                                return "NO";
                            }
                        }
                    }
                return "YES";
            } else return "NO";
        } else return "NO";
    }

    public static void second(Scanner scanner, FileWriter fileWriter, String filename) throws IOException {
        scanner.nextLine();
        System.out.println("II: " + filename + " answers");
        while (scanner.hasNext()) {
            String str = solver(scanner.nextLine(), scanner.nextLine());
            fileWriter.write(str + "\n");
            System.out.println(str);
        }

    }

    public static void files( ArrayList<String> filess, String testPath) throws IOException {
        File file = new File("goodFiles.txt");
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);
        for(int i = 0; i < filess.size();i++)
            scanner.nextLine();
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            filess.add(a);
            FileWriter fileWriter = new FileWriter(new File("out.txt"), true);
            String[] str = a.split("\\.");
            fileWriter.append(new File(str[0] + ".OUT\n").getPath());
            fileWriter.close();
        }
    }

    @Override
    public void run() {
        ArrayList<String> files = new ArrayList<>();



        //String testPath = "/Users/aleksashka/Desktop/Projects/BSU/OS/3_lab/tests/";
        try {
            int i = 0;
            int counter = 0;
            while (i < 200) {
                sleep(1000);
                i++;
                files(files, outPath);
                for (int j = files.size(); j > counter; j--) {
                    String[] str = files.get(j - 1).split("\\.");

                    File filik = new File(testPath + files.get(j - 1));
                    File filik1 = new File(outPath + str[0] + ".OUT");
                    FileWriter fw = new FileWriter(filik1);
                    FileReader fr = new FileReader(filik);
                    String filename = files.get(j - 1);
                    Scanner scanner1 = new Scanner(fr);
                    second(scanner1, fw, filename);

                    fw.close();

                }
                counter=files.size();
                sleep(10);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
