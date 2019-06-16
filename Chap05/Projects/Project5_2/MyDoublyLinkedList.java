// Project 5.2
package Projects.Project5_2;

class MyLink {
    public int data;
    public MyLink next;
    public MyLink previous;

    public MyLink(int data) {
        this.data = data;
    }

    public void display() {
        System.out.println(this.data + " ");
    }

}

public class MyDoublyLinkedList {
    MyLink first;
    MyLink last;

    public MyDoublyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int data) {
        MyLink newLink = new MyLink(data);

        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(int data) {
        MyLink newLink = new MyLink(data);

        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        newLink.previous = last;
        last = newLink;

    }

    public int deleteFirst() {

        int temp = first.data;
        if (first.next == null) {// if only one item
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public int deleteLast() {
        int temp = last.data;
        if (last.previous == null) {// if only one item
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;

    }
}
