import java.util.Arrays;

class Student {
    private String name;
    private int rollNumber;
    private int[] marks;

    public Student(String name, int rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        setMarks(marks);
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int[] getMarks() {
        return marks.clone();
    }

    public void setMarks(int[] marks) {
        if (marks == null || marks.length == 0) {
            throw new IllegalArgumentException("Marks must contain at least one value.");
        }
        this.marks = marks.clone();
    }

    public double calculateAverage() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return (double) total / marks.length;
    }
}

public class Main {
    public static void main(String[] args) {
        Student firstStudent = new Student("Aarav", 101, new int[]{85, 90, 78});
        Student secondStudent = new Student("Meera", 102, new int[]{92, 88, 95});

        displayStudent(firstStudent);
        displayStudent(secondStudent);

        firstStudent.setMarks(new int[]{90, 94, 86});
        System.out.println("Updated marks for " + firstStudent.getName() + ": "
                + Arrays.toString(firstStudent.getMarks()));
        System.out.println("Updated average: " + firstStudent.calculateAverage());
    }

    private static void displayStudent(Student student) {
        System.out.println("Name: " + student.getName());
        System.out.println("Roll number: " + student.getRollNumber());
        System.out.println("Marks: " + Arrays.toString(student.getMarks()));
        System.out.println("Average marks: " + student.calculateAverage());
        System.out.println();
    }
}
