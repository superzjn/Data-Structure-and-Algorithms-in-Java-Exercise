package Projects.Project8_4;
// postfix.java
// parses postfix arithmetic expressions
// to run this program: C>java PostfixApp

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////////////////////////////////////////////////////////////////
class StackX {
    private int maxSize;
    private Tree[] stackArray;
    private int top;

    //--------------------------------------------------------------
    public StackX(int size)      // constructor
    {
        maxSize = size;
        stackArray = new Tree[maxSize];
        top = -1;
    }

    //--------------------------------------------------------------
    public void push(Tree j)     // put item on top of stack
    {
        stackArray[++top] = j;
    }

    //--------------------------------------------------------------
    public Tree pop()            // take item from top of stack
    {
        return stackArray[top--];
    }

    //--------------------------------------------------------------
    public Tree peek()           // peek at top of stack
    {
        return stackArray[top];
    }

    //--------------------------------------------------------------
    public boolean isEmpty()    // true if stack is empty
    {
        return (top == -1);
    }

    //--------------------------------------------------------------
    public boolean isFull()     // true if stack is full
    {
        return (top == maxSize - 1);
    }

    //--------------------------------------------------------------
    public int size()           // return size
    {
        return top + 1;
    }

    //--------------------------------------------------------------
    public Tree peekN(int n)     // peek at index n
    {
        return stackArray[n];
    }

    //--------------------------------------------------------------
    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            System.out.print(peekN(j));
            System.out.print(' ');
        }
        System.out.println("");
    }
//--------------------------------------------------------------
}  // end class StackX

////////////////////////////////////////////////////////////////
class ParsePost {
    private StackX theStack;
    private String input;

    //--------------------------------------------------------------
    public ParsePost(String s) {
        input = s;
    }

    //--------------------------------------------------------------
    public Tree doParse() {
        theStack = new StackX(20);             // make new stack
        char ch;
        int j;
        Tree num1, num2, interAns;

        for (j = 0; j < input.length(); j++)       // for each char,
        {
            ch = input.charAt(j);              // read from input
            theStack.displayStack("" + ch + " ");  // *diagnostic*
            if (ch > 48)         // if it's a number
            {
                Tree newtree = new Tree(ch, ch);
                theStack.push(newtree);
                //   theStack.push( (int)(ch-'0') ); //   push it
            } else                               // it's an operator
            {
                num2 = theStack.pop();          // pop operands
                num1 = theStack.pop();

                Tree newtree = new Tree(ch, ch);
                newtree.root.rightChild = num2.root;
                newtree.root.leftChild = num1.root;
                theStack.push(newtree);
            }  // end else
        }  // end for
        interAns = theStack.pop();            // get answer
        return interAns;
    }  // end doParse()
}  // end class ParsePost

////////////////////////////////////////////////////////////////
// ********************  Project 8.4 **********************************
class PostfixApp {
    public static void main(String[] args) throws IOException {
        String input;
        Tree output;

        while (true) {
            System.out.print("Enter postfix: ");
            System.out.flush();
            input = getString();         // read a string from kbd
            if (input.equals(""))       // quit if [Enter]
                break;
            // make a parser
            ParsePost aParser = new ParsePost(input);
            output = aParser.doParse();  // do the evaluation
            System.out.println("Tree Generated from Postfix");
            output.displayTree();
            System.out.println("Prefix equivalents of the algebraic expression");
            output.traverse(1);
            System.out.println("Postfix equivalents of the algebraic expression");
            output.traverse(3);
            System.out.println("Infix equivalents of the algebraic expression");
            output.traverse(2);

        }  // end while
    }  // end main()

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
//--------------------------------------------------------------
}  // end class PostfixApp
////////////////////////////////////////////////////////////////
