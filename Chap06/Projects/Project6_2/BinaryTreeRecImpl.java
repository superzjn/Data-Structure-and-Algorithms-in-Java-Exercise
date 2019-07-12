package Projects.Project6_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinaryTreeRecImpl {
    int depth;
    int width;
    char array[][];
    int level = 0;

    public BinaryTreeRecImpl(int width) {
        this.width = width;
        depth = 1;
        int temp = width;
        while (temp != 1) {
            temp = temp / 2;
            depth++;
        }

        array = new char[depth][width];

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = '-';
            }
        }
    }


    public void makeBranches(int left, int right) {

        level++;
        int width = right - left;
        int center = left + width / 2;

        array[level - 1][center] = 'X';   // insert 'I' in array
        //    at center of range
        if (width > 1)                   // if not yet on
        {                            //    bottom row,
            makeBranches(left, center);  // left branch
            makeBranches(center, right); // right branch
        }
        level--;
        return;
    }

    public void display() {

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int width = Integer.parseInt(reader.readLine());

            BinaryTreeRecImpl tree = new BinaryTreeRecImpl(width);
            tree.display();

            tree.makeBranches(0, width);

            tree.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


