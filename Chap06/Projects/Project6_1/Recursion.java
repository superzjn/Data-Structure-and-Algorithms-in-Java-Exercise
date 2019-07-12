package Projects.Project6_1;

public class Recursion {

    public int mult(int x, int y) {

        if (y == 1) return x;
        else
            return x + mult(x, --y);

    }

    public static void main(String[] args) {
        Recursion rec = new Recursion();

        System.out.println(rec.mult(9, 3));
    }

}
