import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void addStudent() {

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Grade: ");
            char grade = sc.nextLine().charAt(0);

            String query =
                    "INSERT INTO students VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, String.valueOf(grade));

            ps.executeUpdate();

            System.out.println("Student Added Successfully");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    static void viewStudents() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM students";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println("\nStudent Records");

            while (rs.next()) {

                System.out.println(
                        rs.getString("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("grade")
                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    static void searchStudent() {

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter Student ID: ");

            String id = sc.nextLine();

            String query =
                    "SELECT * FROM students WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                        rs.getString("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("grade")
                );

            } else {

                System.out.println("Student Not Found");
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    static void updateStudent() {

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Grade: ");
            char grade = sc.nextLine().charAt(0);

            String query =
                    "UPDATE students SET name=?, age=?, grade=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, String.valueOf(grade));
            ps.setString(4, id);

            int rows =
                    ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Updated");
            else
                System.out.println("Student Not Found");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    static void deleteStudent() {

        try {

            Connection con =
                    DBConnection.getConnection();

            System.out.print("Enter Student ID: ");

            String id = sc.nextLine();

            String query =
                    "DELETE FROM students WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, id);

            int rows =
                    ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Deleted");
            else
                System.out.println("Student Not Found");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}