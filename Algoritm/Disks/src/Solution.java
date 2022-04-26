import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    static double sum = 0;
    static int VALUE = 0;
    //static Stack<Double> temp = new Stack<>();



   /* public static void get_sum(double a, double b) {
        sum += Math.sqrt((a + b) * (a + b) - (a - b) * (a - b));
    }*/

    public static double HOW_MUCH(double r, double R) {
        return Math.sqrt((r + R) * (r + R) - (r - R) * (r - R));
    }

   /* public static void checker(int i, double r, double R) {
        if (temp.isEmpty()) temp.push(r);
        if (temp.size() == 1) if (R < r + HOW_MUCH(r, R)) temp.push(HOW_MUCH(r, R));
        else temp.push(R);
        else if (R + HOW_MUCH(r, R) > r)
            if (HOW_MUCH(r, R) + HOW_MUCH(r, allDisks.get(i)) >= HOW_MUCH(R, allDisks.get(i)))
                temp.push(HOW_MUCH(r, R));
            else {
                temp.pop();
                checker(i - 1, allDisks.get(i), allDisks.get(i + 1));
            }

    }
*/
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("in.txt");
        FileWriter writer = new FileWriter("out.txt");
        Scanner scanner = new Scanner(reader);
        VALUE = scanner.nextInt();
        double[][] allDisks = new double[(VALUE+1)][2];

        for (int i = 0; i < VALUE; i++) {
            allDisks[i][0]=scanner.nextDouble();

        }

        allDisks[0][1]=allDisks[0][0];
        for(int i=0; i<VALUE-1; i++){
            double dist = HOW_MUCH(allDisks[i][0], allDisks[i+1][0]);
            allDisks[i+1][1]= allDisks[i][1]+dist;
            for(int j=i-1; j>-1; j--){
                dist=HOW_MUCH(allDisks[i+1][0], allDisks[j][0]);
                if(allDisks[j][1]+dist>allDisks[i+1][1]){
                    allDisks[i+1][1]=allDisks[j][1]+dist;
                }
            }
        }


        double leftDistMax=allDisks[0][0]-allDisks[0][1];
        double rightDistMax=allDisks[0][0]+allDisks[0][1];
        for(int i=1; i<VALUE; i++){
            if(leftDistMax<allDisks[i][0] - allDisks[i][1])
                leftDistMax=allDisks[i][0]-allDisks[i][1];
        }
        for(int i=1; i<VALUE; i++){
            if(rightDistMax<allDisks[i][0] + allDisks[i][1])
                rightDistMax=allDisks[i][0]+allDisks[i][1];
        }

        double allDist = leftDistMax+rightDistMax;

       /* for (int i = 0; i < allDisks.size() - 1; i++) {
            checker(i-1, allDisks.get(i), allDisks.get(i + 1));
        }
        temp.push(allDisks.get(allDisks.size() - 1));
        int a = temp.size();
        for (int i = 0; i < a; i++) {
            sum += temp.pop();
        }*/
        writer.write(String.format("%.5f", allDist));
        System.out.println(String.format("%.5f", allDist));
        writer.flush();
    }
}




