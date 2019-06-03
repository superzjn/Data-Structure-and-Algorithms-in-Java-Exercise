// Project 4.4-----------------------------------------------------

public class priorityQ_revised {

    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private long[] queArray;
    private int nItems;

    //-------------------------------------------------------------
    public priorityQ_revised(int s)          // constructor
    {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    //-------------------------------------------------------------
    public void insert(long item)    // insert item
    {
        if (!isFull())
            queArray[nItems++] = item;
        else
            System.out.println("Queue is full");
    }  // end insert()

    //-------------------------------------------------------------
    public long remove()             // remove minimum item
    {
        int index = getminIndex();
        long toRemove = queArray[index];

        for (int i = index; i < nItems - 1; i++) {
            queArray[i] = queArray[i + 1];
        }
        nItems--;
        return toRemove;
    }

    //-------------------------------------------------------------
    public long peekMin()            // peek at minimum item
    {
        return queArray[getminIndex()];
    }

    //-------------------------------------------------------------
    public int getminIndex() {
        long min = queArray[0];
        int index = 0;

        for (int i = 0; i < nItems; i++) {
            if (queArray[i] < min) {
                min = queArray[i];
                index = i;
            }
        }
        return index;
    }


    //-------------------------------------------------------------
    public boolean isEmpty()         // true if queue is empty
    {
        return (nItems == 0);
    }

    //-------------------------------------------------------------
    public boolean isFull()          // true if queue is full
    {
        return (nItems == maxSize);
    }

    //-------------------------------------------------------------
    public void showAll() {

        if(isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            for (long number:queArray) {
                System.out.print (number + " ");
            }
            System.out.println("");
        }

    }


//-------------------------------------------------------------
}  // end class PriorityQ

////////////////////////////////////////////////////////////////
class priorityQ_revisedApp {
    public static void main(String[] args) {
        priorityQ_revised thePQ = new priorityQ_revised(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);

        System.out.println("peek " + thePQ.peekMin());

        thePQ.showAll();
        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " ");  // 10, 20, 30, 40, 50
        }  // end while
        System.out.println("");

        thePQ.showAll();

    }  // end main()
//-------------------------------------------------------------
}  // end class PriorityQApp
////////////////////////////////////////////////////////////////


