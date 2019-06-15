// Project 5.1

class PriorityQueue {

    private MySortedList list;

    public PriorityQueue(MySortedList list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public MyLink remove() {
        return list.remove();
    }

    public void insert(int key) {
        list.insert(key);
    }

    public void display() {
        list.displayList();
    }
}

class PriorityQueueApp {
    public static void main(String[] args) {

        PriorityQueue pq = new PriorityQueue(new MySortedList());
        pq.insert(323);
        pq.insert(423);
        pq.insert(223);
        pq.insert(123);
        pq.display();

        pq.remove();
        pq.display();

        pq.remove();
        pq.display();

    }
}