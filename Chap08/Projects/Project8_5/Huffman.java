package Projects.Project8_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


class Node {

    public char data;
    public int freq;
    public Node leftChild;
    public Node rightChild;

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

class HuffmanCode {

    public Map generateFreqTable(String input) {

        String text = input.trim().toUpperCase();
        Map table = new HashMap();
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

    public Tree generateTree(Map table) {
        return null;
    }
}

class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter a sentence:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        HuffmanCode tree = new HuffmanCode();
        tree.generateFreqTable(input);
    }
}
