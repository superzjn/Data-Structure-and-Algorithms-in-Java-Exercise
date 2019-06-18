package Projects.Project5_3;

public class CircularList {
    Link current;

    public CircularList() {
        current = null;
    }

    public boolean isEmpty() {
        return this.current == null;
    }

    public void insert(int data) {

        Link newlink = new Link(data);
        if (!isEmpty()) {
            if (current.next == null) {// When there is only 1 item in the list
                newlink.next = current;     // They need to point to each other to form a circular list
            } else {
                newlink.next = current.next;
            }
            current.next = newlink;
            step();
        } else {     // if it is empty
            current = newlink;
            current.next = null;
        }
    }

    public Link find(int data) {

        while (current != null && current.data != data) {
            step();
        }
        if (current != null) {
            System.out.println(current.data + " Found");
        } else {
            System.out.println("Not Found");
        }
        return current;
    }

    public void delete(int data) {

        Link itemToDelete = find(data);

        if (itemToDelete != null) {
            while (itemToDelete.data != current.next.data) {
                step();
            }
            current.next = itemToDelete.next;
            System.out.println("Item " + itemToDelete.data + " deleted");
        } else {
            System.out.println("Deletion failed. Item not found");
        }

        //   System.out.println("Point at " + current.data);
    }

    public void step() {
        current = current.next;
    }

    public void displayAll() {

        if (current.data == 4) {
            step();
        }
        while (current.data != 4) {
            current.display();
            step();
        }
        current.display();
        System.out.println();
    }
}


class Link {

    int data;
    Link next;

    public Link(int data) {
        this.data = data;
    }

    public void display() {
        System.out.print(data + " ");
    }
}

class CircularListApp {

    public static void main(String[] args) {
        CircularList list = new CircularList();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.displayAll();
        list.find(3);
        list.delete(2);
        list.displayAll();

    }
}