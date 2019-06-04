//-----------Project 4.5-----------------------------
// Use 2 thread to simulate 2 check out lines and user can input 1 or 2
// to specify which line new customer wants to join

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class CheckOutLine implements Runnable {

    int sizeOfLine = 5;
    int lineNumber;
    Queue queue;

    public CheckOutLine(int lineNumber) {

        this.queue = new Queue(sizeOfLine);
        this.lineNumber = lineNumber;
        initilizeLine();
    }

    public void initilizeLine() {

        for (int i = 0; i < sizeOfLine; i++) {
            queue.insert(ThreadLocalRandom.current().nextLong(100));
        }
    }

    public void joinLine() {

        if (!queue.isFull()) {
            long newComer = ThreadLocalRandom.current().nextLong(100);
            System.out.println("New Customer " + newComer + " is joining Line " + this.getLineNumber());
            queue.insert(newComer);
        }
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public Queue getQueue() {
        return this.queue;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void showLine() {
        System.out.println("Line " + this.getLineNumber());
        queue.display();
    }

    @Override
    public void run() {
        System.out.println("Line " + this.getLineNumber() + " starting Checkout......." + Thread.currentThread().getName());

        while (!isEmpty()) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(20000));
                long customer = this.queue.remove();
                System.out.println("Line " + this.getLineNumber() + " Customer " + customer + " has checked out..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (isEmpty()) System.out.println("Line " + this.getLineNumber() + " cleared");

    }
}

class CheckOutLineApp {

    public static void main(String[] args) {

        CheckOutLine lineOne = new CheckOutLine(1);
        CheckOutLine lineTwo = new CheckOutLine(2);
        CheckOutLine lineThree = new CheckOutLine(3);

        lineOne.showLine();
        lineTwo.showLine();

        Thread t1 = new Thread(lineOne);
        t1.start();

        Thread t2 = new Thread(lineTwo);
        t2.start();

        Scanner reader = new Scanner(System.in);
        do {
            while (reader.hasNextInt()) {
                int lineNum = reader.nextInt();
                System.out.println("New customer wants to join line " + lineNum);

                if (lineNum == 1) {
                    lineOne.joinLine();
                    lineOne.showLine();
                } else if (lineNum == 2) {
                    lineTwo.joinLine();
                    lineTwo.showLine();
                } else {
                    System.out.println("No Such line");
                }
            }
        } while (!reader.nextLine().equals("q"));
        reader.close();
        System.exit(0);


    }
}
