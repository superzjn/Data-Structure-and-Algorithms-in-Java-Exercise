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


    }  // end insert()

    //-------------------------------------------------------------
    public long remove()             // remove minimum item
    {
        return queArray[--nItems];
    }

    //-------------------------------------------------------------
    public long peekMin()            // peek at minimum item
    {
        return queArray[nItems - 1];
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

        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " ");  // 10, 20, 30, 40, 50
        }  // end while
        System.out.println("");
    }  // end main()
//-------------------------------------------------------------
}  // end class PriorityQApp
////////////////////////////////////////////////////////////////


