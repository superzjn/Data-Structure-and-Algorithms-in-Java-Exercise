package Projects.Project5_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CircularList {

    int nItems;
    Link current;

    public CircularList() {
        nItems = 0;
        current = null;
    }

    public void insert(int data) {

        Link newLink = new Link(data);
        if (isEmpty()) {
            current = newLink;
        } else {
            newLink.next = current.next;
        }
        current.next = newLink;
        step();
        nItems++;
    }

    public Link delete() {

        Link toDelete = null;
        switch (nItems) {
            case 0:
                System.out.println("Empty List");
                break;
            case 1:
                toDelete = current.next;
                current.next = null;
                current = null;
                nItems--;
                break;
            default:
                toDelete = current.next;
                current.next = current.next.next;
                nItems--;
                break;
        }
        return toDelete;
    }

    public void displayAll() {
        for (int i = 0; i < nItems; i++) {
            current.next.display();
            step();
        }
        System.out.println();
    }

    public Link search(int data) {

        while (current.data != data) {
            step();
        }
        return current;
    }

    public void step() {
        current = current.next;
    }

    public boolean isEmpty() {
        return nItems == 0;
    }
}


class Link {

    int data;
    Link next;

    public Link(int data) {
        this.data = data;
    }

    public void display() {
        System.out.print(this.data + " ");
    }
}

class JosephusApp {
    public static void main(String[] args) {

        int numOfPeople = 0;
        int countOff = 0;
        int startPoint = 0;

        try {
            System.out.println("Number of People");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            numOfPeople = Integer.parseInt(reader.readLine());
            System.out.println("Counting off number");
            countOff = Integer.parseInt(reader.readLine());
            System.out.println("Start Point");
            startPoint = Integer.parseInt(reader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }

        CircularList list = new CircularList();
        System.out.println("Initializing...");
        for (int i = 1; i <= numOfPeople; i++) {
            list.insert(i);
        }
        list.displayAll();

        Link start = list.search(startPoint);
        list.current = start;

        System.out.println("Eliminating...");
        while (list.nItems > 1) {
            for (int i = 0; i < countOff - 1; i++) {
                list.step();
            }
            list.delete().display();
            System.out.println("Eliminated");
            list.step();
        }
        list.current.display();
        System.out.println("Survived");
    }
}

