// Project 5.1

class MyLink {
    public int data;
    public MyLink next;

    public MyLink(int data) {
        this.data = data;
    }

    public void display() {
        System.out.print(data + " ");
    }
}

class MySortedList {

    private MyLink first;

    public MySortedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public MyLink remove() {

        if (!isEmpty()) {

            MyLink temp = first;
            first = first.next;
            temp.display();
            System.out.println(" removed");
            return temp;
        } else {
            System.out.println("The List is empty.");
            return null;
        }
    }

    public void insert(int key) {

        MyLink newLink = new MyLink(key);
        MyLink current = first;
        MyLink previous = null;

        if (isEmpty()) {
            first = newLink;
            newLink.next = null;
            return;
        }
        while (current != null && current.data < key) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            newLink.next = first;
            first = newLink;
        } else {
            previous.next = newLink;
            newLink.next = current;
        }

    }

    public void displayList() {

        MyLink current = first;
        if (!isEmpty()) {
            while (current != null) {
                current.display();
                current = current.next;
            }
            System.out.println("");

        } else {
            System.out.println("The List is empty.");
        }
    }

}
