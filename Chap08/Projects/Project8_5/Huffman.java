package Projects.Project8_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;
import java.util.Stack;


class Node {

    String data;
    int freq;
    Node leftChild;
    Node rightChild;

    public Node(String data) {
        this.data = data;
    }

    public void displayNode() {
        System.out.print(data);
    }
}

class Tree {
    public Node root;

    public Tree(Node node) {
        this.root = node;
    }

    // -------------------------------------------------------------
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    temp.displayNode();
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }  // end displayTree()
}

class PriorityQueue {
    Tree[] queArray;
    int nItems;

    public PriorityQueue(int size) {
        queArray = new Tree[size];
        nItems = 0;
    }

    public void add(Tree newitem) {

        if (queArray[0] == null) {
            queArray[0] = newitem;
            nItems++;

        } else {
            int i;
            // Need to shift from the end otherwise the later element will be overwritten
            for (i = nItems - 1; i >= 0; i--) {
                if (newitem.root.freq > queArray[i].root.freq) {
                    queArray[i + 1] = queArray[i];
                } else {
                    break;
                }
            }
            queArray[i + 1] = newitem;

            nItems++;
        }
        //   showQueue();

    }

    public Tree deQueue() {
        return queArray[--nItems];
    }

    public void showQueue() {
        System.out.println("Current in Queue");
        for (Tree item : queArray) {
            if (item != null)
                System.out.println(item.root.data + " " + item.root.freq);

        }
    }
}

class HuffmanCode {

    public Map generateFreqTable(String input) {

        String text = input.trim().toUpperCase();
        Map<String, Integer> table = new HashMap<String, Integer>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            StringBuilder builder = new StringBuilder();
            if (ch == ' ') {
                builder.append("Sp");
            } else {
                builder.append(ch);
            }
            String key = builder.toString();

            table.putIfAbsent(key, 0);
            if (table.containsKey(key)) {
                int count = (int) table.get(key);
                table.replace(key, ++count);
            }
        }
        return table;
    }

    public Tree generateTree(Map<String, Integer> table) {

        PriorityQueue queue = new PriorityQueue(table.size());

        for (Entry<String, Integer> entry : table.entrySet()) {
            Node node = new Node(entry.getKey());
            node.freq = entry.getValue();
            Tree tree = new Tree(node);
            queue.add(tree);
        }

        while (queue.nItems > 1) {
            Tree tree1 = queue.deQueue();
            Tree tree2 = queue.deQueue();
            Node leftnode = tree1.root;
            Node rightnode = tree2.root;

            Node newnode = new Node("ep");
            newnode.freq = leftnode.freq + rightnode.freq;
            newnode.leftChild = leftnode;
            newnode.rightChild = rightnode;
            Tree newtree = new Tree(newnode);
            queue.add(newtree);
        }
        return queue.deQueue();
    }
}

class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter a sentence:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        HuffmanCode tree = new HuffmanCode();
        Map map = tree.generateFreqTable(input);
        Tree huffmantree = tree.generateTree(map);
        huffmantree.displayTree();
    }
}
