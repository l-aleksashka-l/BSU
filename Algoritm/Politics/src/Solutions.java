import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solutions {

    static Map<Integer, Integer> VALUES = new HashMap<Integer, Integer>();
    static Stack<Integer> last_path = new Stack<>();
    //static Set<Integer> temp = new HashSet<>();
    //static Set<Integer> last_path = new HashSet<>();
    static Set<Integer> leaves = new HashSet<>();
    static int SUM = Integer.MAX_VALUE;
    static int tempSum = 0;

    public static void recursion(int digit, int[][] array, Stack<Integer> temp) {
        tempSum += VALUES.get(digit);
        temp.push(digit);

        int j = 0;
        while (true) {
            int node = array[digit - 1][j];
            j++;
            if (tempSum < SUM & leaves.contains(digit)) {
                SUM = tempSum;
                last_path = (Stack<Integer>) temp.clone();
                //while(!a.empty())
                //    last_path.add(a.pop());
            }
            if (node == 0) {
                break;
            }

            recursion(node, array, temp);

            tempSum -= VALUES.get(node);
            temp.pop();

        }
    }



    public static void main(String[] args) throws IOException {
        File in = new File("in.txt");
        File out = new File("out.txt");
        FileWriter fileWriter = new FileWriter(out);
        Scanner scanner = new Scanner(in);
        int Quantity = scanner.nextInt();
        int[][] pairs = new int[Quantity][5];
        Stack<Integer> temp = new Stack<>();


        ArrayList<Long>[] arrayLists = new ArrayList[Quantity];
        for (int i = 0; i < Quantity; i++) {
            arrayLists[i] = new ArrayList<>();
        }


        int I;
        int J;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        long[] size = new long[Quantity];
        long[] order = new long[Quantity];
        long[] parrentChilds = new long[Quantity];
        long index;
        for (int i = 0; i < Quantity; i++) {
            I = scanner.nextInt();
            J = scanner.nextInt();
            size[I - 1] = J;
            if (J == 0) {
                queue.addLast(I - 1);
            }
            while (J > 0) {
                index = scanner.nextLong();
                parrentChilds[(int) index - 1] = I - 1;
                arrayLists[I - 1].add(index);
                arrayLists[I - 1].add(scanner.nextLong());
                J--;
            }
        }


        long Node = 0;
        int idx = 0;
        while (!queue.isEmpty()) {
            Node = queue.pop();
            order[idx] = Node;
            idx++;
            size[(int) parrentChilds[(int) Node]]--;
            if (size[(int) parrentChilds[(int) Node]] == 0) {
                queue.addLast((int) parrentChilds[(int) Node]);
            }
        }



        long[] arrCost = new long[Quantity];
        Arrays.fill(arrCost, 0);

        int ordI = 0;
        int ir = 0;
        long index1;
        long bribe;
        long minIndex = 0;
        long minBribe = 0;

        while (ordI < Quantity) {
            ir = (int) order[ordI];
            if (!arrayLists[ir].isEmpty()) {
                Iterator<Long> it = arrayLists[ir].iterator();
                minIndex = it.next();
                arrCost[(int) minIndex - 1] += it.next();
                minBribe = arrCost[(int) minIndex - 1];
                while (it.hasNext()) {
                    index = it.next();
                    bribe = it.next();
                    arrCost[(int) index - 1] += bribe;
                    if (arrCost[(int) index - 1] <= minBribe) {
                        minBribe = bribe;
                        minIndex = index;
                    }
                }
                arrCost[ir] += arrCost[(int) minIndex - 1];
            }
            ordI++;
        }

        List<Integer> result = new ArrayList<>();
        result.add(1);

        int curIndex = 0;
        boolean key = true;
        while (key) {
            Iterator<Long> it = arrayLists[curIndex].iterator();
            key = false;
            while (it.hasNext()) {
                index = it.next();
                bribe = it.next();
                if (arrCost[(int) index - 1] == arrCost[curIndex]) {
                    result.add((int) index);
                    arrCost[(int) index - 1] -= bribe;
                    curIndex = (int) index - 1;
                    key = true;
                }
            }
        }


        while (scanner.hasNext()) {
            int line_number = scanner.nextInt();
            int volume = scanner.nextInt();
            if (volume != 0)
            for (int i = 0; i < volume; i++) {
                int node = scanner.nextInt();
                int value = scanner.nextInt();
                pairs[line_number - 1][i] = node;
                VALUES.put(node, value);
            }else{
                leaves.add(line_number);
            }
        }

        fileWriter.write(new Long(arrCost[0]).toString());
        fileWriter.write("\n");

        ir = result.size();
        for (Integer item : result) {
            fileWriter.write(item.toString());
            if (ir != 1) {
                fileWriter.write(" ");
            }
            ir--;
        }


        fileWriter.close();
        if(scanner.hasNext()) {
            int j = 0;
            while (true) {
                int node = pairs[0][j];
                j++;
                if (node == 0) break;

                tempSum = 0;
                if (!temp.empty()) temp.pop();
                recursion(node, pairs, temp);

            }
            fileWriter.write(SUM + "\n" + "1 ");
            Set<Integer> stack = new HashSet<>();
            while (!last_path.empty())
                stack.add(last_path.pop());
            for (Integer a : stack)
                fileWriter.write(a + " ");

            fileWriter.flush();
            System.out.println(Arrays.deepToString(pairs));
            System.out.println(VALUES);
            System.out.println(last_path);
            System.out.println(SUM);
        }
    }
}
