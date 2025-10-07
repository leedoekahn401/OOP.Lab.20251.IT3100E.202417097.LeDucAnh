import java.util.Scanner;

public class Table {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;
        while (true) {
            System.out.print("Enter a valid year (non-negative): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) {
                    scanner.nextLine(); 
                    break; 
                }
            } else {
                scanner.next(); 
            }
            System.out.println("Invalid year! Please enter a valid year.");
            scanner.nextLine(); 
        }

        int month;
        while (true) {
            System.out.print("Enter a month (number 1-12, full name, or abbreviation): ");
            String input = scanner.nextLine().trim().toLowerCase();
            month = convertMonth(input);
            if (month != -1) {
                break; 
            }
            System.out.println("Invalid month! Please enter a valid month.");
        }

        int days = getDaysInMonth(month, year);
        System.out.println("The month you entered has " + days + " days.");
        scanner.close();
    }

    private static int convertMonth(String input) {
        switch (input) {
            case "1": case "january": case "jan.": case "jan": return 1;
            case "2": case "february": case "feb.": case "feb": return 2;
            case "3": case "march": case "mar.": case "mar": return 3;
            case "4": case "april": case "apr.": case "apr": return 4;
            case "5": case "may": return 5;
            case "6": case "june": case "jun.": case "jun": return 6;
            case "7": case "july": case "jul.": case "jul": return 7;
            case "8": case "august": case "aug.": case "aug": return 8;
            case "9": case "september": case "sep.": case "sep": return 9;
            case "10": case "october": case "oct.": case "oct": return 10;
            case "11": case "november": case "nov.": case "nov": return 11;
            case "12": case "december": case "dec.": case "dec": return 12;
            default: return -1;
        }
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
        }
        return -1; 
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
