import java.util.Arrays;
import java.util.Scanner;

public class ArraySorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();
        int[] myArray = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            myArray[i] = scanner.nextInt();
        }
        scanner.close();

        Arrays.sort(myArray);

        int sum = 0;
        for (int number : myArray) {
            sum += number;
        }

        double average = 0;
        if (myArray.length > 0) {
             average = (double) sum / myArray.length;
        }

        System.out.println("\n Results ");
        System.out.println("Sorted array: " + Arrays.toString(myArray));
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Average of array elements: " + average);
    }
}

