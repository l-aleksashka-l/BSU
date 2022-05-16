import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BST_class {
    static int counter = -1;
    static int depth = 0;
    class Node {
        long key;
        Node left, right;
        long cL = 0, cR = 0, height = 0;

        public Node(long data) {
            key = data;
            left = right = null;
        }
    }
    Node root;
    BST_class() throws IOException {
        root = null;
    }
    Node delete_Recursive(Node root, long key) {
        //tree is empty
        if (root == null) return root;

        //traverse the tree
        if (key < root.key)     //traverse left subtree 
            root.left = delete_Recursive(root.left, key);
        else if (key > root.key)  //traverse right subtree
            root.right = delete_Recursive(root.right, key);
        else {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children; 
            //get inorder successor (min value in the right subtree) 
            root.key = minValue(root.right);

            // Delete the inorder successor 
            root.right = delete_Recursive(root.right, root.key);
        }
        return root;
    }
    long minValue(Node root) {
        long minval = root.key;
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }
    void insert(long key) {
        root = insert_Recursive(root, key);
    }
    Node insert_Recursive(Node root, long key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key < root.key)     //insert in the left subtree
        {
            root.cL++;
            root.left = insert_Recursive(root.left, key);
        } else if (key > root.key)    //insert in the right subtree
        {
            root.cR++;
            root.right = insert_Recursive(root.right, key);
        }
        // return pointer
        return root;
    }
    int maxDepth(Node node) {
        if (node == null)
            return -1;
        else {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
    void checker(Node root, BST_class bst, List list, int center) {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            if (left == right && center==depth/2 )
            {list.add(root.key);
            System.out.println(root.key);}

    };
    void inorder(FileWriter fileWriter, BSTimport java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    class BST_class {
        static int counter = -1;
        static int depth = 0;
        class Node {
            long key;
            Node left, right;
            long cL = 0, cR = 0, height = 0;

            public Node(long data) {
                key = data;
                left = right = null;
            }
        }
        Node root;
        BST_class() throws IOException {
            root = null;
        }
        Node delete_Recursive(Node root, long key) {
            //tree is empty
            if (root == null) return root;

            //traverse the tree
            if (key < root.key)     //traverse left subtree
                root.left = delete_Recursive(root.left, key);
            else if (key > root.key)  //traverse right subtree
                root.right = delete_Recursive(root.right, key);
            else {
                // node contains only one child
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;

                // node has two children;
                //get inorder successor (min value in the right subtree)
                root.key = minValue(root.right);

                // Delete the inorder successor
                root.right = delete_Recursive(root.right, root.key);
            }
            return root;
        }
        long minValue(Node root) {
            long minval = root.key;
            while (root.left != null) {
                minval = root.left.key;
                root = root.left;
            }
            return minval;
        }
        void insert(long key) {
            root = insert_Recursive(root, key);
        }
        Node insert_Recursive(Node root, long key) {
            //tree is empty
            if (root == null) {
                root = new Node(key);
                return root;
            }
            //traverse the tree
            if (key < root.key)     //insert in the left subtree
            {
                root.cL++;
                root.left = insert_Recursive(root.left, key);
            } else if (key > root.key)    //insert in the right subtree
            {
                root.cR++;
                root.right = insert_Recursive(root.right, key);
            }
            // return pointer
            return root;
        }
        int maxDepth(Node node) {
            if (node == null)
                return -1;
            else {
                /* compute the depth of each subtree */
                int lDepth = maxDepth(node.left);
                int rDepth = maxDepth(node.right);

                /* use the larger one */
                if (lDepth > rDepth)
                    return (lDepth + 1);
                else
                    return (rDepth + 1);
            }
        }
        void checker(Node root, BST_class bst, List list, int center) {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            if (left == right && center==depth/2 )
            {list.add(root.key);
                System.out.println(root.key);}

        };
        void inorder(FileWriter fileWriter, BST_class bst, List list) throws IOException {
            depth = maxDepth(root);
            if (depth%2!=0)
                depth++;
            preorder_Recursive_1(root, bst, list);
            if(!list.isEmpty() && list.size()%2!=0) {
                Collections.sort(list);
                bst.delete_Recursive(root, (Long) list.get(list.size() / 2));
            }
            preorder_Recursive_2(root, fileWriter);
        }
        void preorder_Recursive_1(Node root, BST_class bst, List list) throws IOException {
            if (root != null) {
                counter++;
                checker(root, bst, list, counter);
                preorder_Recursive_1(root.left, bst, list);
                preorder_Recursive_1(root.right, bst, list);
                counter--;
            }
        }
        void preorder_Recursive_2(Node root, FileWriter fileWriter) throws IOException {
            if (root != null) {
                fileWriter.write(root.key + "\n");
                preorder_Recursive_2(root.left, fileWriter);
                preorder_Recursive_2(root.right, fileWriter);
            }
        }
    }

    public class Solution implements Runnable {
        static List list = new ArrayList();

        public static void main(String[] args) throws IOException {
            new Thread(null, new Solution(), "", 64 * 1024 * 1024).start();
        }

        @Override
        public void run() {
            BST_class bst = null;
            try {
                bst = new BST_class();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File in = new File("in.txt");
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            try {
                line = reader.readLine();
                while (line != null) {
                    bst.insert(Long.parseLong(line));
                    line = reader.readLine();
                }
                File out = new File("out.txt");
                FileWriter fileWriter = new FileWriter(out);
                bst.inorder(fileWriter, bst, list);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }_class bst, List list) throws IOException {
        depth = maxDepth(root);
        if (depth%2!=0)
            depth++;
        preorder_Recursive_1(root, bst, list);
        if(!list.isEmpty() && list.size()%2!=0) {
            Collections.sort(list);
            bst.delete_Recursive(root, (Long) list.get(list.size() / 2));
        }
        preorder_Recursive_2(root, fileWriter);
    }
    void preorder_Recursive_1(Node root, BST_class bst, List list) throws IOException {
        if (root != null) {
            counter++;
            checker(root, bst, list, counter);
            preorder_Recursive_1(root.left, bst, list);
            preorder_Recursive_1(root.right, bst, list);
            counter--;
        }
    }
    void preorder_Recursive_2(Node root, FileWriter fileWriter) throws IOException {
        if (root != null) {
            fileWriter.write(root.key + "\n");
            preorder_Recursive_2(root.left, fileWriter);
            preorder_Recursive_2(root.right, fileWriter);
        }
    }
}

public class Solution implements Runnable {
    static List list = new ArrayList();

    public static void main(String[] args) throws IOException {
        new Thread(null, new Solution(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        BST_class bst = null;
        try {
            bst = new BST_class();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File in = new File("in.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        try {
            line = reader.readLine();
            while (line != null) {
                bst.insert(Long.parseLong(line));
                line = reader.readLine();
            }
            File out = new File("out.txt");
            FileWriter fileWriter = new FileWriter(out);
            bst.inorder(fileWriter, bst, list);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}