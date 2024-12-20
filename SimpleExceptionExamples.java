import java.io.*;
import java.sql.*;

public class SimpleExceptionExamples {

    public static void main(String[] args) {
        System.out.println("Checked Exceptions:");
        simpleIOExceptionExample();
        simpleFileNotFoundExceptionExample();
        simpleEOFExceptionExample();
        simpleSQLExceptionExample();
        simpleClassNotFoundExceptionExample();

        System.out.println("\nUnchecked Exceptions:");
        simpleArithmeticExceptionExample();
        simpleNullPointerExceptionExample();
        simpleArrayIndexOutOfBoundsExceptionExample();
        simpleClassCastExceptionExample();
        simpleIllegalArgumentExceptionExample();
        simpleNumberFormatExceptionExample();
    }

    // Checked Exceptions

    // 1. IOException
    public static void simpleIOExceptionExample() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            reader.readLine();
        } catch (IOException e) {
            System.out.println("IOException: An input/output error occurred.");
        }
    }

    // 2. FileNotFoundException
    public static void simpleFileNotFoundExceptionExample() {
        try {
            FileReader reader = new FileReader("missingfile.txt");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: The file does not exist.");
        }
    }

    // 3. EOFException
    public static void simpleEOFExceptionExample() {
        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new byte[0]))) {
            dis.readInt();
        } catch (EOFException e) {
            System.out.println("EOFException: End of file reached unexpectedly.");
        } catch (IOException e) {
            System.out.println("IOException: Error reading from the stream.");
        }
    }

    // 4. SQLException
    public static void simpleSQLExceptionExample() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:invalid:url", "user", "password");
        } catch (SQLException e) {
            System.out.println("SQLException: Database access error.");
        }
    }

    // 5. ClassNotFoundException
    public static void simpleClassNotFoundExceptionExample() {
        try {
            Class.forName("non.existent.ClassName");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: Class not found.");
        }
    }

    // Unchecked Exceptions

    // 6. ArithmeticException
    public static void simpleArithmeticExceptionExample() {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: Division by zero.");
        }
    }

    // 7. NullPointerException
    public static void simpleNullPointerExceptionExample() {
        try {
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: Attempted to access a null object.");
        }
    }

    // 8. ArrayIndexOutOfBoundsException
    public static void simpleArrayIndexOutOfBoundsExceptionExample() {
        try {
            int[] arr = {1, 2, 3};
            int value = arr[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: Invalid array index.");
        }
    }

    // 9. ClassCastException
    public static void simpleClassCastExceptionExample() {
        try {
            Object obj = new String("test");
            Integer num = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: Invalid type casting.");
        }
    }

    // 10. IllegalArgumentException
    public static void simpleIllegalArgumentExceptionExample() {
        try {
            Thread.sleep(-1000);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: Invalid argument.");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: Thread was interrupted.");
        }
    }

    // 11. NumberFormatException
    public static void simpleNumberFormatExceptionExample() {
        try {
            int number = Integer.parseInt("invalid_number");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Invalid number format.");
        }
    }
}