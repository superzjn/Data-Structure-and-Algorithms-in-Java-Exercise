// Project 5.2
package Projects.Project5_2;

public class MyDequeue {

    public MyDoublyLinkedList list;

    public MyDequeue() {
        this.list = new MyDoublyLinkedList();
    }

    public void insertFromFront(int input) {
        this.list.insertFirst(input);
    }

    public void insertFromRear(int input) {
        this.list.insertLast(input);
    }

    public int removeFromFront() {
        return list.deleteFirst();
    }

    public int removeFromRear() {
        return list.deleteLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void displayAll() {
        list.displayAll();
    }

}

class MyDequeueApp {

    public static void main(String[] args) {
        MyDequeue myqueue = new MyDequeue();

        myqueue.insertFromRear(1);
        myqueue.insertFromRear(2);
        myqueue.insertFromRear(3);
        myqueue.displayAll();

        myqueue.insertFromFront(4);
        myqueue.insertFromFront(5);
        myqueue.displayAll();

        myqueue.removeFromFront();
        myqueue.displayAll();

        myqueue.removeFromRear();
        myqueue.displayAll();

    }
}
