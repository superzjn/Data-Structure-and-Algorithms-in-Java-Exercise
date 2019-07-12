package Projects.Project6_3;

public class Power {
    long product;

    public long cal(long x, long y) {

        if (y == 1) return x;
        if (y == 0) return 1;
        else {
            product = cal(x * x, y / 2);
        }

        if (y % 2 == 0)
            return product;
        else
            return product * x;
    }

    public static void main(String[] args) {

        Power pwr = new Power();
        System.out.println(pwr.cal(2, 5));


    }
}
