// orderedArray.java
// demonstrates ordered array class
// to run this program: C>java OrderedApp
////////////////////////////////////////////////////////////////
class OrdArray {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //-----------------------------------------------------------
    public OrdArray(int max)          // constructor
    {
        a = new long[max];             // create array
        nElems = 0;
    }

    //-----------------------------------------------------------
    public int size() {
        return nElems;
    }

    //-----------------------------------------------------------
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey)
                return curIn;              // found it
            else if (lowerBound > upperBound)
                return nElems;             // can't find it
            else                          // divide range
            {
                if (a[curIn] < searchKey)
                    lowerBound = curIn + 1; // it's in upper half
                else
                    upperBound = curIn - 1; // it's in lower half
            }  // end else divide range
        }  // end while
    }  // end find()

    //-----------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        int j;
        for (j = 0; j < nElems; j++)        // find where it goes
            if (a[j] > value)            // (linear search)
                break;
        for (int k = nElems; k > j; k--)    // move bigger ones up
            a[k] = a[k - 1];
        a[j] = value;                  // insert it
        nElems++;                      // increment size
    }  // end insert()
    //-----------------------------------------------------------
    // Project 2.4

    public void binaryInsert(long value) {
        int cursor = 0;
        int upperBound = nElems - 1;
        int lowerBound = 0;

        if (nElems != 0 && value > a[nElems - 1]) a[nElems] = value;
        else {
            while (true) {

                cursor = (upperBound + lowerBound) / 2;

                if (upperBound > lowerBound) {

                    if (value > a[cursor]) {
                        lowerBound = cursor + 1;
                        continue;
                    } else {
                        upperBound = cursor - 1;
                        continue;
                    }
                } else {
                    break;
                }
            }

            for (int i = nElems - 1; i >= cursor; i--) {
                a[i + 1] = a[i];
            }

            a[cursor] = value;
        }
        nElems++;

    }
    //-----------------------------------------------------------
    // Project 2.5

    public void merge(OrdArray array1, OrdArray array2) {

        long[] a1 = array1.a;
        long[] a2 = array2.a;

        int j = 0;

        while (array1.nElems != 0 && array2.nElems != 0) {

            if (a1[0] >= a2[0]) {
                a[j] = a2[0];
                array2.delete(a2[0]);
            } else {
                a[j] = a1[0];
                array1.delete(a1[0]);
            }
            j++;
            nElems++;

        }

        if (array1.nElems != 0) {
            for (int i = 0; i < array1.nElems; i++) {
                this.binaryInsert(a1[i]);
            }
        } else if (array2.nElems != 0) {
            for (int i = 0; i < array2.nElems; i++) {
                this.binaryInsert(a2[i]);
            }
        }

    }

    //-----------------------------------------------------------
    public boolean delete(long value) {
        int j = find(value);
        if (j == nElems)                  // can't find it
            return false;
        else                           // found it
        {
            for (int k = j; k < nElems; k++) // move bigger ones down
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
}  // end class OrdArray

////////////////////////////////////////////////////////////////
class OrderedApp {
    public static void main(String[] args) {
        int maxSize = 100;             // array size
        OrdArray arr;                  // reference to array
        arr = new OrdArray(maxSize);   // create the array

        arr.insert(77);                // insert 10 items
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        int searchKey = 55;            // search for item
        if (arr.find(searchKey) != arr.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        arr.display();                 // display items

        arr.delete(00);                // delete 3 items
        arr.delete(55);
        arr.delete(99);

        arr.display();                 // display items again

        //------- project 2.4
        arr.binaryInsert(63);
        arr.display();

        //------- project 2.5
        OrdArray arr2 = new OrdArray(maxSize);

        arr2.binaryInsert(35);
        arr2.binaryInsert(25);
        arr2.binaryInsert(45);
        arr2.binaryInsert(86);
        arr2.binaryInsert(123);

        arr2.display();

        OrdArray marr = new OrdArray(maxSize);
        marr.merge(arr, arr2);
        marr.display();

        //------- project 2.6


    }  // end main()
}  // end class OrderedApp
