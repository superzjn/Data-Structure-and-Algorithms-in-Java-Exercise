import java.util.Arrays;

// highArray.java
// demonstrates array class with high-level interface
// to run this program: C>java HighArrayApp
////////////////////////////////////////////////////////////////
class HighArray {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //-----------------------------------------------------------
    public HighArray(int max)         // constructor
    {
        a = new long[max];                 // create the array
        nElems = 0;                        // no items yet
    }

    //-----------------------------------------------------------
    public boolean find(long searchKey) {                              // find specified value
        int j;
        for (j = 0; j < nElems; j++)            // for each element,
            if (a[j] == searchKey)           // found item?
                break;                       // exit loop before end
        if (j == nElems)                    // gone to end?
            return false;                   // yes, can't find it
        else
            return true;                    // no, found it
    }  // end find()

    //-------------sadfsdfssd----------------------------------------------
    public void insert(long value)    // put element into array
    {
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }

    //-----------------------------------------------------------
    public boolean delete(long value) {
        int j;
        for (j = 0; j < nElems; j++)        // look for it
            if (value == a[j])
                break;
        if (j == nElems)                  // can't find it
            return false;
        else                           // found it
        {
            for (int k = j; k < nElems; k++) // move higher ones down
                a[k] = a[k + 1];
            nElems--;                   // decrement size
            return true;
        }
    }  // end delete()

    //-----------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }

    //-----------------------------------------------------------

    // Project 2.1
    public long getMax() {
        if (nElems == 0) return -1;
        long max = 0;
        for (int i = 0; i < nElems; i++) {
            if (a[i] > max) max = a[i];
        }
        return max;
    }
    //-----------------------------------------------------------


    // Project 2.2
    public void removeMax() {

        if (nElems == 0) return;
        int maxIndex = 0;
        for (int i = 0; i < nElems; i++) {
            if (a[i] > a[maxIndex]) maxIndex = i;
        }

        for (int i = maxIndex; i < nElems; i++) {
            a[i] = a[i + 1];
        }
        nElems--;
    }

    //-----------------------------------------------------------

    // Project 2.3
    public int getnElems() {
        return nElems;
    }


    // Project 2.6
    public void noDups() {

        if (nElems == 0) return;

        for (int i = 0; i < nElems - 1; i++) {
            for (int j = i + 1; j < nElems; j++) {
                if (a[i] == a[j]) {
                    a[j] = Long.MAX_VALUE;
                }
            }
        }

        for (int i=0; i<nElems;i++) {
            if (a[i] == Long.MAX_VALUE) {
                for (int j=i; j<nElems; j++) {
                    a[j] = a[j+1];
                    nElems--;
                }
            }
        }

    }

    //-----------------------------------------------------------
}  // end class HighArray

////////////////////////////////////////////////////////////////
class HighArrayApp {
    public static void main(String[] args) {
        int maxSize = 100;            // array size
        HighArray arr;                // reference to array
        arr = new HighArray(maxSize); // create the array

        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        arr.display();                // display items

        //-----------------------------------------------------------

        // Project 2.3
        int size = arr.getnElems();
        long[] sortedArray = new long[size];

        for (int i = 0; i < size; i++) {
            sortedArray[i] = arr.getMax();
            arr.removeMax();
        }

        System.out.println(Arrays.toString(sortedArray));

        //-----------------------------------------------------------

        // Project 2.1
        long max = arr.getMax();
        System.out.println("Max number is " + max);
        //-----------------------------------------------------------
        // Project 2.2
        arr.removeMax();
        arr.display();                  // display after removing max

        int searchKey = 35;           // search for item
        if (arr.find(searchKey))
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.delete(00);               // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                // display items again


        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(99);         // Add for project 2.6
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);
        arr.insert(33);
        arr.insert(88);
        arr.insert(77);
        System.out.println("Before removing dups");
        arr.display();
        arr.noDups();
        System.out.println("After removing dups");
        arr.display();
    }  // end main()
}  // end class HighArrayApp
