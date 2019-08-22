package Projects.Project8_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;


class Node {

    char data;
    int freq;
    Node leftChild;
    Node rightChild;

    public Node(char data) {
        this.data = data;
    }
}

class Tree {
    public Node root;

    public Tree(Node node) {
        this.root = node;
    }
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
        showQueue();

    }

    public Tree get() {
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
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            table.putIfAbsent(ch, 0);
            if (table.containsKey(ch)) {
                int count = (int) table.get(ch);
                table.replace(ch, ++count);
            }
        }
        return table;
    }

    public Tree generateTree(Map<Character, Integer> table) {

        PriorityQueue queue = new PriorityQueue(table.size());

        for (Entry<Character, Integer> entry : table.entrySet()) {
            Node node = new Node(entry.getKey());
            node.freq = entry.getValue();
            Tree tree = new Tree(node);
            queue.add(tree);
        }

        while (queue.nItems > 1) {
            Tree tree1 = queue.get();
            Tree tree2 = queue.get();

            Node newnode = new Node(' ');
            newnode.leftChild = tree1.root;
            newnode.rightChild = tree2.root;
            // TODO p 419 step2
        }
        return null;
    }
}

class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter a sentence:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        HuffmanCode tree = new HuffmanCode();
        Map map = tree.generateFreqTable(input);
        tree.generateTree(map);
    }
}
