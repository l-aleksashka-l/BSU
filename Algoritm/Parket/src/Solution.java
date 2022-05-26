import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        File in = new File("in.txt");
        FileReader fileReader = new FileReader(in);
        Scanner sc = new Scanner(in);
        File out = new File("out.txt");
        FileWriter fileWriter = new FileWriter(out);
        int a = sc.nextInt();
        int x = 1;
        if(a%2==0){
            while(a>0){
                x*=5;
                a-=2;
            }
            int w = 1000000007;
            x = x%w;
            fileWriter.write(Integer.toString(x));

        }else{
            fileWriter.write(Integer.toString(0));
        }
        fileWriter.flush();
    }
}
