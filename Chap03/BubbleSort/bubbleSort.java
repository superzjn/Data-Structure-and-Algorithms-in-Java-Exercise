// bubbleSort.java
// demonstrates bubble sort
// to run this program: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
class ArrayBub {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayBub(int max)          // constructor
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
//    public void bubbleSort() {
//        int out, in;
//
//        // out need to be >=1 otherwise the 1st and 2nd elements may not be sorted if number of elements is 3
//        for (out = nElems - 1; out >= 1; out--)   // outer loop (backward)
//            for (in = 0; in < out; in++)        // inner loop (forward)
//                if (a[in] > a[in + 1])       // out of order?
//                    swap(in, in + 1);          // swap them
//    }  // end bubbleSort()


    //--------- Project 3.1

    public void bubbleSort() {
        int out_left, out_right, in;

        // out_left need to be >=1 otherwise the 1st and 2nd elements may not be sorted if number of elements is 3
        for (out_left = nElems - 1; out_left >= 1; out_left--) {  // outer loop (backward)
            for (in = 0; in < out_left; in++) {    // inner loop (forward)

                if (a[in] > a[in + 1]) {      // out_left of order?
                    swap(in, in + 1);
                }       // swap them
                display();
            }
            for (out_right = out_left - 1; out_right > 0; out_right--) {
                if (a[out_right] < a[out_right - 1]) {
                    swap(out_right, out_right - 1);
                }
                display();

            }
        }

    }  // end bubbleSort()

    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
}  // end class ArrayBub

////////////////////////////////////////////////////////////////
class BubbleSortApp {
    public static void main(String[] args) {

        int maxSize = 100;            // array size
//        int maxSize = 100000;            // array size

        ArrayBub arr;                 // reference to array
        arr = new ArrayBub(maxSize);  // create the array

        // Experiment
//        for (int j = 0; j < maxSize; j++) {
//            long n = (long)(java.lang.Math.random() * (maxSize - 1));
//            arr.insert(n);
//        }
//
//        for (long j=99999; j>=0 ; j--) {
//            long n = j;
//            arr.insert(n);
//        }
        arr.insert(99);
        arr.insert(77);               // insert 10 items
        arr.insert(55);
        arr.insert(44);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        long startTime = System.nanoTime();
        arr.display();                // display items
        arr.bubbleSort();             // bubble sort them
        arr.display();                // display them again
        long endTime = System.nanoTime();
        System.out.println("That took " + (double) (endTime - startTime) / 1_000_000 + " seconds");

    }  // end main()
}  // end class BubbleSortApp
////////////////////////////////////////////////////////////////
