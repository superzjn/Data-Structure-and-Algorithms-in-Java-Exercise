package Projects.Project5_6;

public class SpreadSheet {

    Link origin;
    int noOfColumns;
    int noOfRows;

    public SpreadSheet(int rows, int columns) {
        this.noOfColumns = columns;
        this.noOfRows = rows;
        init();
    }

    public void init() {
        int x, y;
        Link above = null;
        Link onLeft = null;
        Link newLink = null;
        Link leftEnd = null;    // Use as a pointer on the Y axis.

        // Create a matrix row after row
        for (y = 0; y < noOfRows; y++) {
            for (x = 0; x < noOfColumns; x++) {
                newLink = new Link(x * 10 + y);
                if (x == 0 && y == 0) {
                    origin = newLink;
                }
                if (x != 0) {  // not the left most column
                    onLeft.right = newLink;
                    onLeft = newLink;
                } else {    // is the left most column
                    onLeft = newLink;
                    above = leftEnd;
                    leftEnd = newLink;
                }
                if (y != 0) {    // Not top row
                    above.down = newLink;
                    above = above.right;
                }
            }
        }
    }

    public Link moveRight(Link current, int x) {

        for (int i = 0; i < x; i++) {
            current = current.right;
        }
        return current;
    }

    public Link moveDown(Link current, int y) {
        for (int i = 0; i < y; i++) {
            current = current.down;
        }
        return current;

    }

    public void insert(int x, int y, int data) {

        Link toInsert = moveRight(origin, x);
        toInsert = moveDown(toInsert, y);
        toInsert.iData = data;

    }

    public void displayAll() {

        Link currentX = origin;
        Link currentY = origin;

        for (int y = 0; y < this.noOfRows; y++) {
            for (int x = 0; x < this.noOfColumns; x++) {
                currentX.display();
                currentX = currentX.right;
            }
            System.out.println();
            currentY = currentY.down;
            currentX = currentY;
        }
    }
}

class Link {

    int iData;
    Link right;
    Link down;

    public Link(int data) {
        iData = data;
    }

    public void display() {
        System.out.print(this.iData + " ");
    }
}

class SpreadSheetApp {

    public static void main(String[] args) {

        SpreadSheet sheet = new SpreadSheet(4, 5);
        sheet.displayAll();
        System.out.println("=======================");

        sheet.insert(1, 1, 99);
        sheet.displayAll();

    }
}