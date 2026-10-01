import java.util.ArrayList;
import java.util.Arrays;

public class Assignment2 {
        public static void main(String[] args) {
                ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
                System.out.println("Initial ArrayList: " + numbers);

                numbers.add(6);
                System.out.println("After adding 6: " + numbers);

                numbers.add(2, 10);
                System.out.println("After inserting 10 at index 2: " + numbers);

                numbers.remove(3);
                System.out.println("After removing the value at index 3: " + numbers);
        }
}
