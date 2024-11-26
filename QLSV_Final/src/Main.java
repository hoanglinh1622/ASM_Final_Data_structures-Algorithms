import java.util.*;

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class StudentManagement {
    private List<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public void addStudent(int id, String name, double marks) {
        if (name == null || name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }
        if (marks < 0 || marks > 10) {
            System.out.println("Error: Marks must be in the range [0, 10].");
            return;
        }
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Error: Student ID already exists.");
                return;
            }
        }
        Student newStudent = new Student(id, name, marks);
        students.add(newStudent);
        System.out.println("Student added successfully: " + newStudent);
    }

    public void editStudent(int id, String newName, Double newMarks) {
        for (Student student : students) {
            if (student.getId() == id) {
                if (newName != null && !newName.isEmpty()) {
                    student.setName(newName);
                } else if (newName != null) {
                    System.out.println("Error: Name cannot be empty.");
                    return;
                }
                if (newMarks != null) {
                    if (newMarks < 0 || newMarks > 10) {
                        System.out.println("Error: Marks must be in the range [0, 10].");
                        return;
                    }
                    student.setMarks(newMarks);
                }
                System.out.println("Student updated successfully: " + student);
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    public void deleteStudent(int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        if (!removed) {
            System.out.println("Error: Student not found.");
        } else {
            System.out.println("Student deleted successfully.");
        }
    }

    public void searchStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Found: " + student);
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    private void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private int partition(List<Student> list, int low, int high) {
        double pivot = list.get(high).getMarks();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getMarks() > pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    public void displaySortedStudents() {
        if (students.isEmpty()) {
            System.out.println("Error: No students to display.");
            return;
        }
        List<Student> sortedStudents = new ArrayList<>(students);
        quickSort(sortedStudents, 0, sortedStudents.size() - 1);

        System.out.println("Sorted Students by Marks (Descending Order):");
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagement sm = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display Sorted Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    sm.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.print("Enter ID of student to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Name (leave empty to skip): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Marks (-1 to skip): ");
                    double newMarks = scanner.nextDouble();
                    sm.editStudent(editId, newName.isEmpty() ? null : newName, newMarks == -1 ? null : newMarks);
                    break;
                case 3:
                    System.out.print("Enter ID of student to delete: ");
                    int deleteId = scanner.nextInt();
                    sm.deleteStudent(deleteId);
                    break;
                case 4:
                    System.out.print("Enter ID of student to search: ");
                    int searchId = scanner.nextInt();
                    sm.searchStudent(searchId);
                    break;
                case 5:
                    sm.displaySortedStudents();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
