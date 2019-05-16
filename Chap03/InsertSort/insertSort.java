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

        //------------  Project 3.5---------------------------------

        int numberOfComp = 0, numberOfCopies = 0;

        for (out = 1; out < nElems; out++) {

            long temp = a[out];
            numberOfCopies++;

            in = out;
            while (in > 0) {
                numberOfComp++;

                if (a[in - 1] >= temp) {
                    a[in] = a[in - 1];
                    numberOfCopies++;
                    in--;
                    a[in] = temp;
                    numberOfCopies++;

                } else {
                    a[in] = temp;
                    numberOfCopies++;
                    break;
                }
            }

        }  // end for


        //--------------------------------------------------------------
        // Original Version
       /* for (out = 1; out < nElems; out++)     // out is dividing line
        {
            long temp = a[out];            // remove marked item
            in = out;                      // start shifts at out
            while (in > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                a[in] = a[in - 1];            // shift item to right
                --in;                       // go left one position
            }
            a[in] = temp;                  // insert marked item
        }  // end for*/

        System.out.println("Number of Copies made: " + numberOfCopies);
        System.out.println("Number of Comparisons made: " + numberOfComp);
    }  // end insertionSort()

    //--------------------------------------------------------------
    //--------------Project 3.2-------
    // Return the median value in the array
    public long median() {
        insertionSort();

        return a[nElems / 2];

    }

    //--------------------------------------------------------------
    //--------------Project 3.3-------
    public void noDups() {

        long[] nodup_array = new long[nElems];
        int nodup_index = 0;

        for (int i = 0; i < nElems - 1; ) {
            int dups = 0;

            int j = i;
            while (a[j] == a[j + 1]) {
                dups++;
                j++;
            }

            nodup_array[nodup_index++] = a[j];
            i = i + dups + 1;
        }

        a = nodup_array;
    }

    //---------- Project 3.3 method 2--------
//    public void noDups() {
//        int nextAvailable = 1;
//        int numDups = 0;
//
//        for (int i = 1; i < nElems; i++) {
//            if (a[i] != a[i - 1]) {
//                if (a[nextAvailable] != a[i]) {
//                    a[nextAvailable] = a[i];
//                }
//                nextAvailable++;
//            }
//            else {
//                numDups++;
//            }
//        }
//        nElems -= numDups;
//    }


}  // end class ArrayIns

////////////////////////////////////////////////////////////////
class InsertSortApp {
    public static void main(String[] args) {
//        int maxSize = 10000;            // array size
        int maxSize = 20;

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

//        arr.insert(99);
//        arr.insert(77);
//        arr.insert(44);
//        arr.insert(55);
//        arr.insert(22);
//        arr.insert(88);
//        arr.insert(11);
//        arr.insert(00);
//        arr.insert(66);
//        arr.insert(33);


        // Test for project 3.5. An almost sorted array
        arr.insert(1);
        arr.insert(2);
        arr.insert(4);
        arr.insert(5);
        arr.insert(3);
        arr.insert(6);
        arr.insert(7);
        arr.insert(8);
        arr.insert(33);
        arr.insert(66);


        // Projet 3.3
//        arr.insert(00);
//        arr.insert(77);
//        arr.insert(99);
//        arr.insert(44);
//        arr.insert(44);

        long startTime = System.nanoTime();

        arr.display();                // display items

        arr.insertionSort();          // insertion-sort them

        arr.display();                // display them again

        long endTime = System.nanoTime();
        System.out.println("That took " + (double) (endTime - startTime) / 1_000_000 + " seconds");

        // Project 3.2
        System.out.println("Median value is " + arr.median());

        // Project 3.3
//        arr.noDups();
//        arr.display();                // display them again


    }  // end main()
}  // end class InsertSortApp
