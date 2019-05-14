// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayIns(int max)          // constructor
    {
        a = new long[max];                 // create the array
        nElems = 0;                        // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }

    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void insertionSort() {
        int in, out;

        for (out = 1; out < nElems; out++)     // out is dividing line
        {
            long temp = a[out];            // remove marked item
            in = out;                      // start shifts at out
            while (in > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                a[in] = a[in - 1];            // shift item to right
                --in;                       // go left one position
            }
            a[in] = temp;                  // insert marked item
        }  // end for
    }  // end insertionSort()

    //--------------------------------------------------------------
    //--------------Project 3.2-------
    // Return the median value in the array
    public long median() {
        insertionSort();

        return a[nElems / 2];

    }

    //--------------Project 3.3-------
    public void noDups() {

        for (int i = 0; i < nElems - 1; i++) {
            int shift = 0;

            int j = i;
            while (a[j] == a[j + 1]) {
                shift++;
                j++;
            }

            for (;shift>=0;shift--)
            a[j+1-shift] = a[j+1];
        }

    }


}  // end class ArrayIns

////////////////////////////////////////////////////////////////
class InsertSortApp {
    public static void main(String[] args) {
//        int maxSize = 10000;            // array size
        int maxSize = 10;

        ArrayIns arr;                 // reference to array
        arr = new ArrayIns(maxSize);  // create the array

        // Experiment
//        for (int j = 0; j < maxSize; j++) {
//            long n = (long) (java.lang.Math.random() * (maxSize - 1));
//            arr.insert(n);
//        }

//        for (long j=99999; j>=0 ; j--) {
//            long n = j;
//            arr.insert(n);
//
//        }


        arr.insert(77);               // insert 10 items
        arr.insert(99);
        arr.insert(44);
//        arr.insert(55);
//        arr.insert(22);
//        arr.insert(88);
//        arr.insert(11);
//        arr.insert(00);
//        arr.insert(66);
//        arr.insert(33);

        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(44);

        long startTime = System.nanoTime();

        arr.display();                // display items

        arr.insertionSort();          // insertion-sort them

        arr.display();                // display them again

        long endTime = System.nanoTime();
        System.out.println("That took " + (double) (endTime - startTime) / 1_000_000 + " seconds");

        // Project 3.2
        System.out.println("Median value is " + arr.median());

        arr.noDups();
        arr.display();                // display them again


    }  // end main()
}  // end class InsertSortApp
