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

    // Enqueue as in order
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

    Map<String, String> huffmancodeTable;
    Tree huffmantree;

    public HuffmanCode() {
        huffmancodeTable = new HashMap<String, String>();
    }

    public Map generateFreqTable(String input) {

        String text = input.toUpperCase();
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

    public void generateTree(Map<String, Integer> table) {

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
            Node leftNode = tree1.root;
            Node rightNode = tree2.root;

            Node newNode = new Node("ep");
            newNode.freq = leftNode.freq + rightNode.freq;
            newNode.leftChild = leftNode;
            newNode.rightChild = rightNode;
            Tree newTree = new Tree(newNode);
            queue.add(newTree);
        }
        huffmantree = queue.deQueue();
    }

    public void generateCodeTable() {

        traverseAndRecord(huffmantree.root, "");

        for (Entry<String, String> entry : huffmancodeTable.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void traverseAndRecord(Node localroot, String recorder) {

        if (localroot.data == "ep") {
            traverseAndRecord(localroot.leftChild, recorder + "0");
            traverseAndRecord(localroot.rightChild, recorder + "1");
        } else {
            huffmancodeTable.putIfAbsent(localroot.data, recorder);
        }
    }

    public String decode(String text) {

        StringBuilder decode = new StringBuilder();
        Node node = huffmantree.root;

        for (int i = 0; i < text.length(); i++) {

            if (node.data.equals("ep")) {
                char ch = text.charAt(i);

                if (ch == '0') {
                    node = node.leftChild;
                } else {
                    node = node.rightChild;
                }
            }

            if (!node.data.equals("ep")) {    // If current node is leaf

                if (node.data.equals("Sp")) {
                    decode.append(" ");
                } else {
                    decode.append(node.data);
                }
                node = huffmantree.root;
            }

        }
        return decode.toString();
    }

    public String encode(String plaintext) {

        StringBuilder encodedText = new StringBuilder();
        String key = null;

        for (int i = 0; i < plaintext.length(); i++) {
            if (plaintext.charAt(i) == ' ') {
                key = "Sp";
            } else {
                key = Character.toString(plaintext.charAt(i)).toUpperCase();
            }
            String code = huffmancodeTable.get(key);
            encodedText.append(code);
        }

        return encodedText.toString();
    }
}

class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter a sentence:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        HuffmanCode coder = new HuffmanCode();

        Map freqmap = coder.generateFreqTable(input);
        coder.generateTree(freqmap);
        coder.huffmantree.displayTree();
        coder.generateCodeTable();

        System.out.println("-------------Encoding-----------");
        String encodeText = coder.encode(input);
        System.out.println(encodeText);


        System.out.println("-------------Decoding-----------");
        String decodeMsg = coder.decode(encodeText);
        System.out.println(decodeMsg);

    }
}
