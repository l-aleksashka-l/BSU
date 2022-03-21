public class Node {

    public int key; //ключ узла
    public String data; //некоторые данные в узле
    public Node leftChild; //левый потомок
    public Node rightChild; //правый потомок

    /**
     * Метод который выводит на экран содержимое узла
     */
    public void printNode(){
        System.out.println("KEY " + key + " DATA: " + data);
    }
}

public class Tree {

    Node root;

    /**
     * Поиск элемента в дереве по ключу
     * @param key
     * @return
     */
    public Node find(int key){
        Node current = root;
        while(current.key!=key){
            if(current.key<key){
                current = current.leftChild;
            }else{
                current = current.rightChild;
            }
            if(current==null){
                return null;
            }
        }
        return current;
    }

    /**
     * Вставка в дерево. Суть таже что и поиск
     * Только вместо вывода элемента к нему левым или правым потомком
     * добавляем новый элемент
     * @param key
     * @param data
     */
    public void insert(int key, String data){
        Node node = new Node();
        node.key = key;
        node.data = data;
        if(root==null){
            root = node;
        }else{
            Node current = root;
            Node prev = null;
            while (true){
                prev = current;
                if(key<prev.key){
                    current = current.leftChild;
                    if(current==null){
                        prev.leftChild = node;
                        return;
                    }
                }else{
                    current = current.rightChild;
                    if(current==null){
                        prev.rightChild = node;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Вывод всех элементов дерева методом асиметричного обхода
     * @param startNode
     */
    public void print(Node startNode){
        if(startNode != null){//условие сработает, когда мы достигним конца дерева и потомков не останется
            print(startNode.leftChild);//рекурсивно вызываем левых потомков
            startNode.printNode();//вызов метода принт
            print(startNode.rightChild);//вызов правых
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(3, "John");
        tree.insert(8, "T1000");
        tree.insert(1, "Sara");
        tree.insert(2, "T800");

        Node findJohn = tree.find(3);
        findJohn.printNode();

        tree.print(findJohn);
    }
}