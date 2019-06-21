package Projects.Project5_4;

class Stack {

    CircularList list;

    public Stack() {
        list = new CircularList();
    }

    public int pop() {
        return list.delete().iData;
    }

    public void push(int data) {
        list.insert(data);
    }

    public void displayAll() {
        list.displayAll();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

class StackApp {

    public static void main(String[] args) {

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.displayAll();

        while (!stack.isEmpty()) {

            System.out.println(stack.pop() + " popped");

        }
    }
}

