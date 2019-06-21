package Projects.Project5_4;

public class CircularList {

    public Link current;
    public int nItems;

    public CircularList() {
        current = null;
        nItems = 0;
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public void insert(int data) {

        Link newlink = new Link(data);

        if (isEmpty()) {
            current = newlink;
            current.next = newlink;

        } else {
            newlink.next = current.next;
            current.next = newlink;
        }
        nItems++;
    }

    public Link delete() {

        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        } else {
            Link toDelete = current.next;

            // Delete itself if there is only one item
            if (nItems == 1) current = null;
            else {
                current.next = current.next.next;
            }
            nItems--;
            return toDelete;
        }

    }

    public Link search(int data) {
        Link result = null;
        if (!isEmpty()) {
            for (int i = 0; i < nItems; i++) {
                if (current.iData != data) {
                    step();
                } else {
                    result = current;
                    System.out.println("Found");
                }
            }
        }
        return result;
    }

    public void step() {
        this.current = current.next;
    }

    public Link peek() {
        if (nItems > 0)
            return current.next;
        else
            return null;
    }

    public void displayAll() {

        Link display = current.next;
        if (!isEmpty()) {
            for (int i = 0; i < nItems; i++) {
                display.display();
                display = display.next;
            }
            System.out.println();
        } else {
            System.out.println("The list is empty");
        }


    }
}

class Link {

    int iData;
    Link next;

    public Link(int data) {
        this.iData = data;
    }

    public void display() {
        System.out.print(this.iData + "");
    }

}

class CirularListApp {
    public static void main(String[] args) {

        CircularList list = new CircularList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.displayAll();

        list.delete();
        list.displayAll();
    }
}

