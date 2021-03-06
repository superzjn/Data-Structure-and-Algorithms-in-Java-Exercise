package Projects.Project8_3;// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

////////////////////////////////////////////////////////////////
class Node {
    public char data;
    public Node leftChild;         // this node's left child
    public Node rightChild;        // this node's right child

    public Node(char letter) {
        this.data = letter;
    }

    public void displayNode()      // display ourself
    {
        System.out.print(data);
    }
}  // end class Node

////////////////////////////////////////////////////////////////
class Tree {
    public Node root;             // first node of tree

    // -------------------------------------------------------------
    public Tree(Node node)                  // constructor
    {
        root = node;
    }

    // -------------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            localRoot.displayNode();
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            localRoot.displayNode();
            inOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            localRoot.displayNode();
        }
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
// -------------------------------------------------------------
}  // end class Tree

////////////////////////////////////////////////////////////////
class TreeMaker {

    private String inputString;
    private Tree[] treeArray;
    private Tree finalTree;

    TreeMaker(String s) {
        inputString = s;
        treeArray = new Tree[50];

        for (int i = 0; i < inputString.length(); i++) {
            Node node = new Node(inputString.charAt((i)));
            treeArray[i] = new Tree(node);
        }
    }

    public Tree makeUnbalancedTree() {
        for (int j = 0; j < inputString.length() - 1; j++) {
            Tree tree1 = treeArray[j];      // get first subtree
            Tree tree2 = treeArray[j + 1];    // get second subtree

            Node aNode = new Node('+');     // make node with '+'
            finalTree = new Tree(aNode);        // make new tree, with
            finalTree.root.leftChild = tree1.root;  // tree1 and tree2
            finalTree.root.rightChild = tree2.root; // as children
            treeArray[j + 1] = finalTree;         // replace tree2
        }  // end for

        return finalTree;
    }

    public Tree makeBalancedTree() {

        int stringLength = inputString.length();
        int nTrees = stringLength;
        int j = 0;

        while (nTrees > 1) {

            while (treeArray[j] == null && j < stringLength - 1)
                j++;
            if (j > stringLength - 2) {
                j = 0;
                continue;
            }
            Tree tree1 = treeArray[j];      // get first subtree
            treeArray[j++] = null;
            nTrees--;
            while (treeArray[j] == null && j < stringLength - 1)
                j++;

            Tree tree2 = treeArray[j];      // get second subtree
            Node aNode = new Node('+');     // make node with '+'
            finalTree = new Tree(aNode);        // make new tree, with
            finalTree.root.leftChild = tree1.root;  // tree1 and tree2
            finalTree.root.rightChild = tree2.root;
            treeArray[j++] = finalTree;
        }

        return finalTree;

    }

}

////////////////////////////////////////////////////////////////
class TreeApp {
    public static void main(String[] args) throws IOException {
        int value;
        Tree theTree = null;
        TreeMaker maker;
        String input;

        while (true) {
            System.out.print("Enter first letter of unbalanced, balanced ");
            System.out.print("show or traverse: ");
            int choice = getChar();
            switch (choice) {
                case 'u':
                    System.out.print("Enter a String ");
                    input = getString();
                    maker = new TreeMaker(input);
                    theTree = maker.makeUnbalancedTree();
                    theTree.displayTree();
                    break;
                case 'b':
                    System.out.print("Enter a String ");
                    input = getString();
                    maker = new TreeMaker(input);
                    theTree = maker.makeBalancedTree();
                    theTree.displayTree();
                    break;
                case 's':
                    theTree.displayTree();
                    break;
                case 't':
                    System.out.print("Enter type 1, 2 or 3: ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()

    // -------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    // -------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
// -------------------------------------------------------------
}  // end class TreeApp
////////////////////////////////////////////////////////////////
