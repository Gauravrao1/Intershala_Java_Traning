import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Assignment3 {
        public static void main(String[] args) {
                Set<String> fruits = new HashSet<>();
                fruits.add("apple");
                fruits.add("banana");
                fruits.add("cherry");
                fruits.add("date");
                fruits.add("fig");

                fruits.add("banana");

                System.out.println("HashSet size: " + fruits.size());
                System.out.println("HashSet elements:");
                Iterator<String> iterator = fruits.iterator();
                while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                }
        }
}
