import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        File file = new File("input.txt");

        File out = new File("output.txt");


        Scanner sc = new Scanner(file);
        Set<Long> nums = new HashSet<>();
        long sum = 0;
        if(!sc.hasNextLong()){return;}
        while (sc.hasNextLong()) {
            long a = sc.nextLong();
            nums.add(a);
        }
        for(Long a:nums)
            sum+=a;

            FileWriter writer = new FileWriter(out);
            writer.write(Long.toString(sum));
            writer.flush();
            writer.close();

    }

}
