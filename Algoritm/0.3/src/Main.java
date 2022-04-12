import java.io.*;

public class Main implements Runnable {
    static class Node {
        int key;
        Node parent;
        int max;
        int min;

        Node(int key) {
            this.key = key;
            this.parent = null;
            this.min = Integer.MIN_VALUE;
            this.max = Integer.MAX_VALUE;
        }

        Node() {
        }
    }

    boolean BST(BufferedReader bufferedReader) throws IOException {
        Node[] bst = new Node[Integer.parseInt(bufferedReader.readLine())];
        bst[0] = new Node(Integer.parseInt(bufferedReader.readLine()));
        int i = 1;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Node node = new Node();
            String[] fields = line.split(" ");
            node.key = Integer.parseInt(fields[0]);
            node.parent = bst[Integer.parseInt(fields[1]) - 1];
            if (fields[2].equals("L")) {
                if (node.parent.key == Integer.MIN_VALUE)
                    return false;
                node.min = node.parent.min;
                node.max = node.parent.key - 1;
            } else {
                node.min = node.parent.key;
                node.max = node.parent.max;
            }
            if (node.key < node.min || node.key > node.max)
                return false;
            bst[i] = node;
            i++;
        }
        bufferedReader.close();
        return true;
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        boolean isBinarySearchTree = false;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("bst.in"));
            isBinarySearchTree = BST(bufferedReader);
            FileWriter fw = new FileWriter("bst.out");
            if (isBinarySearchTree) {
                fw.write("YES");
            } else {
                fw.write("NO");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

