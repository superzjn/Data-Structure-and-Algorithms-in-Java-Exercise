
//-------Project 4.2----------------------------------

public class Deque {

    private int nItems;
    private int front;
    private int rear;
    private int[] queueArray;
    private int maxSize;

    public Deque(int s) {

        this.maxSize = s;
        queueArray = new int[s];
        nItems = 0;
        rear = -1;
        front = 0;

    }

    public void insertLeft(int item) {

        if (!isFull()) {

            if (!isEmpty()) {
                if (front == 0) front = maxSize - 1;
                else --front;
            } else rear = 0;
            queueArray[front] = item;
            nItems++;

        } else {
            System.out.println("Queue is full");
        }

    }

    public void insertRight(int item) {

        if (!isFull()) {
            if (rear == maxSize - 1) rear = 0;
            else rear++;
            queueArray[rear] = item;
            nItems++;


        } else {
            System.out.println("Queue is full");
        }

    }

    public void removeLeft() {

        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            if (front == rear) {
                rear--;
            } else {
                if (front == maxSize - 1) front = 0;
                else front++;
            }
            nItems--;
        }

    }

    public void removeRight() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            if (front == rear) {
                rear--;
            } else {
                if (rear == 0) rear = maxSize - 1;
                else rear--;
            }
            nItems--;
        }
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public void showFromFront() {

        for (int i = 0; i < nItems; i++) {
            System.out.print(queueArray[front++] + " ");
            if (front == maxSize) front = 0;
        }
        System.out.println();
    }

    public void showFromRear() {
        for (int i = 0; i < nItems; i++) {
            System.out.print(queueArray[rear--] + " ");
            if (rear == -1) rear = maxSize - 1;
        }
        System.out.println();
    }


}

class dequeApp {

    public static void main(String[] args) {

        Deque deque = new Deque(5);
        deque.insertRight(5);
        deque.insertRight(10);
        deque.insertRight(15);
        deque.insertRight(20);
        deque.insertRight(25);

        deque.removeRight();
        deque.removeLeft();
        deque.insertLeft(5);
        deque.removeRight();
        deque.removeRight();
        deque.insertRight(25);

//        deque.insertLeft(10);
//        deque.insertLeft(15);
//        deque.insertLeft(20);
//        deque.insertLeft(25);


        deque.showFromFront();
//        deque.showFromRear();

    }
}








