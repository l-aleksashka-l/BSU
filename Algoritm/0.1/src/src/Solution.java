import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BST_class {


    class Node {
        long key;
        Node left, right;
        long cL = 0, cR = 0, height = 0;

        public Node(long data) {
            key = data;
            left = right = null;
        }
    }

    // BST root node
    Node root;

    // Constructor for BST =>initial empty tree
    BST_class() throws IOException {
        root = null;
    }

    //delete a node from BST
    void deleteKey(long key) {
        root = delete_Recursive(root, key);
    }

    //recursive delete function
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
        //initially minval = root
        long minval = root.key;
        //find minval
        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    // insert a node in BST 
    void insert(long key) {
        root = insert_Recursive(root, key);
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

    //recursive insert function
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

    void checker(Node root, BST_class bst, List list) {
        if (root.left != null && root.right != null) {
            if (maxDepth(root.left) != maxDepth(root.right) && root.cL == root.cR)
                list.add(root.key);
        }
    }

    // method for inorder traversal of BST
    void inorder(FileWriter fileWriter, BST_class bst, List list) throws IOException {
        preorder_Recursive_1(root, bst, list);
        if(!list.isEmpty() && list.size()%2!=0) {
            Collections.sort(list);
            bst.delete_Recursive(root, (Long) list.get(list.size() / 2));
        }
        preorder_Recursive_2(root, fileWriter);
    }

    // recursively traverse the BST  
    void preorder_Recursive_1(Node root, BST_class bst, List list) throws IOException {
        if (root != null) {
            checker(root, bst, list);
            preorder_Recursive_1(root.left, bst, list);
            preorder_Recursive_1(root.right, bst, list);
        }
    }

    void preorder_Recursive_2(Node root, FileWriter fileWriter) throws IOException {
        if (root != null) {
            fileWriter.write(root.key + "\n");
            preorder_Recursive_2(root.left, fileWriter);
            preorder_Recursive_2(root.right, fileWriter);
        }
    }

    boolean search(int key) {
        root = search_Recursive(root, key);
        if (root != null)
            return true;
        else
            return false;
    }

    //recursive insert function
    Node search_Recursive(Node root, int key) {
        // Base Cases: root is null or key is present at root 
        if (root == null || root.key == key)
            return root;
        // val is greater than root's key 
        if (root.key > key)
            return search_Recursive(root.left, key);
        // val is less than root's key 
        return search_Recursive(root.right, key);
    }
}

class Solution implements Runnable {
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
        File in = new File("input.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        try {
//            line = reader.readLine();
//            long d = Long.parseLong(line);
//
//            line = reader.readLine();
            line = reader.readLine();


            while (line != null) {
                bst.insert(Long.parseLong(line));
                line = reader.readLine();

            }

            File out = new File("output.txt");
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(out);


            bst.inorder(fileWriter, bst, list);


            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}