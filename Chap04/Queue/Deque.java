
//-------Project 4.2----------------------------------

class Deque {

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
            System.out.println("queue.Queue is full");
        }

    }

    public void insertRight(int item) {

        if (!isFull()) {
            if (rear == maxSize - 1) rear = 0;
            else rear++;
            queueArray[rear] = item;
            nItems++;


        } else {
            System.out.println("queue.Queue is full");
        }

    }

    public void removeLeft() {

        if (isEmpty()) {
            System.out.println("queue.Queue is empty");
        } else {
            if (front == rear) {                    // Only one item in the queue
                rear--;
            } else {
                if (front == maxSize - 1) front = 0;
                else front++;
            }
            nItems--;
        }

    }

    public int removeRight() {
        int item = 0;
        if (isEmpty()) {
            System.out.println("queue.Queue is empty");
            return -1;
        } else {
            if (front == rear) {                    // Only one item in the queue
                item = queueArray[rear--];
            } else {
                if (rear == 0) {
                    item = queueArray[rear];
                    rear = maxSize - 1;
                } else item = queueArray[rear--];
            }
            nItems--;
        }
        return item;
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

    // return the rightmost item from the queue. Added for project 4.3
    public int rightmost() {
        return queueArray[rear];
    }


}

class DequeApp {

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

//--------------- Project 4.3 ----------------------------------
class StackXX {

    int maxSize;
    int top;
    Deque deque;

    public StackXX(int s) {
        this.maxSize = s;
        top = -1;
        deque = new Deque(s);
    }

    public void push(int item) {
        if (isFull()) System.out.println("Stack is full");
        else {
            deque.insertRight(item);
            top++;
        }
    }

    public int pop() {
        top--;
        return deque.removeRight();
    }

    public int peek() {
        return deque.rightmost();
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void showAll() {
        deque.showFromFront();
    }
}


class stackXXApp {
    public static void main(String[] args) {
        StackXX stackxx = new StackXX(5);
        stackxx.push(10);
        stackxx.push(20);
        stackxx.push(30);
        stackxx.push(40);
        stackxx.push(50);
        stackxx.showAll();

        System.out.println(stackxx.pop() + " poped");
        System.out.println(stackxx.pop() + " poped");

        stackxx.showAll();

        System.out.println("Peek is " + stackxx.peek());


    }

}








