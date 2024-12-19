package Exam;

import java.util.*;

abstract class SchoolTransfer {
    public abstract double calculateAverage(int[] marks);
}

class CalculateAverageMarks extends SchoolTransfer {
    @Override
    public double calculateAverage(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total / (double) marks.length;
    }
}

class TransferStudent extends SchoolTransfer {
    private String name;
    private int age;
    private String gender;
    private String currentSchool;
    private int[] marks;
    private int motherAge;
    private int fatherAge;
    private String address;

    public TransferStudent(String name, int age, String gender, String currentSchool, int[] marks, int motherAge, int fatherAge, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.currentSchool = currentSchool;
        this.marks = marks;
        this.motherAge = motherAge;
        this.fatherAge = fatherAge;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCurrentSchool() {
        return currentSchool;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getMotherAge() {
        return motherAge;
    }

    public int getFatherAge() {
        return fatherAge;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public double calculateAverage(int[] marks) {
        CalculateAverageMarks calc = new CalculateAverageMarks();
        return calc.calculateAverage(marks);
    }
}

public class Main {
    public static void main(String[] args) {
        String[] schools = {"Kigali Parents", "Green Hills", "Excellent School", "New Vision School", "King David School"};

        // Input data
        List<TransferStudent> students = new ArrayList<>();
        students.add(new TransferStudent("Alice", 11, "Female", "La Colombiere", new int[]{85, 90, 88, 92}, 35, 38, "Kicukiro"));
        students.add(new TransferStudent("Bob", 12, "Male", "La Colombiere", new int[]{88, 84, 87, 89}, 40, 39, "Kicukiro"));
        students.add(new TransferStudent("Cathy", 13, "Female", "La Colombiere", new int[]{83, 85, 86, 88}, 36, 37, "Kicukiro"));
        students.add(new TransferStudent("Daniel", 12, "Male", "La Colombiere", new int[]{90, 92, 91, 93}, 38, 39, "Kicukiro"));
        students.add(new TransferStudent("Eva", 12, "Female", "La Colombiere", new int[]{84, 86, 88, 90}, 39, 40, "Kicukiro"));
        
       
        students.sort((s1, s2) -> {
            if (s1.getAge() != s2.getAge()) {
                return s1.getAge() - s2.getAge(); 
            }
            return Double.compare(new CalculateAverageMarks().calculateAverage(s2.getMarks()),
                                   new CalculateAverageMarks().calculateAverage(s1.getMarks())); 
        });


        System.out.println("Transfer Details:");
        for (int i = 0; i < schools.length; i++) {
            TransferStudent student = students.get(i);
            System.out.println("Student Name: " + student.getName());
            System.out.println("Transferred to: " + schools[i]);
            System.out.println("Average Marks: " + student.calculateAverage(student.getMarks()));
            System.out.println("----------------------------");
        }
    }
}
