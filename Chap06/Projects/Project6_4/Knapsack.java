package Projects.Project6_4;

public class Knapsack {

    private int capacity;
    private int[] items;
    int index = 0;
    int shift = 0;

    public Knapsack(int capacity, int[] items) {
        this.capacity = capacity;
        this.items = items;
    }

    public void knapsack(int target, int shift) {

        if (index == items.length - 1) return;

        if (items[shift] < target) {

            System.out.print(items[shift] + " ");

            target = target - items[shift];

            if (target == 0) return;
            else {
                knapsack(target, ++shift);
            }

        } else {

            if (++shift <= items.length-1) {
                knapsack(target, shift);
            }


        }

    }


    public static void main(String[] args) {

        int[] items = {11, 8, 7, 6, 5};

        Knapsack pack = new Knapsack(20, items);

        pack.knapsack(20, 0);
    }
}
