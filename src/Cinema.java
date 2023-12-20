import java.util.Scanner;

public class Cinema {
    private static final int STANDARD_TICKET_PRICE = 10;
    private static final int DISCOUNT_TICKET_PRICE = 8;
    private static final int TOTAL_SEATS = 60;

    private int[][] matrix;

    public void run() {
        int[] dimensions = readRowsAndSeats();
        int rows = dimensions[0];
        int columns = dimensions[1];
        matrix = new int[rows][columns];

        mainCinemaMenu(matrix, rows, columns);
    }

    private static void mainCinemaMenu(int[][] matrix, int rows, int columns) {
        System.out.println();
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println();
        switch (choice) {
            case 1:
                printCinemaLayout(matrix, rows, columns);
                break;
            case 2:
                int[] seatCoordinates = readSeatCoordinates();
                bookAndCalculatePrice(matrix, rows, columns, seatCoordinates[0], seatCoordinates[1]);
                break;
            case 3:
                showStatistics(rows, columns, matrix);
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        mainCinemaMenu(matrix, rows, columns);
    }

//    private static void showStatistics(int rows, int columns, int[][] matrix) {
//        int totalSeats = rows * columns;
//        int bookedSeats = 0;
//        int currentIncome = 0;
//        int result = 0;
//
//        for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < columns; j++) {
//                if(matrix[i][j] == 1) {
//                    bookedSeats++;
//                    currentIncome += calculateTicketPrice(matrix, rows, i + 1, columns, totalSeats);
//                }
//            }
//        }
//
//        double percentage = ((double) bookedSeats / totalSeats) * 100;
//
//        // Calculate ticket price based on front and back rows
//        int frontRows = rows / 2;
//        int backRows = rows - frontRows;
//
//        if (bookedSeats == 0) {
//            // No purchased tickets, set result to STANDARD_TICKET_PRICE
//            result = STANDARD_TICKET_PRICE;
//        } else if (bookedSeats <= backRows * columns) {
//            // Calculate based on back rows
//            result = DISCOUNT_TICKET_PRICE;
//        } else {
//            // Calculate based on front rows
//            result = STANDARD_TICKET_PRICE;
//        }
//
//        System.out.println("Number of purchased tickets: " + bookedSeats);
//        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
//        System.out.println("Current income: $" + currentIncome);
//        System.out.println("Total income: $" + totalSeats * result);
//
//    }
private static void showStatistics(int rows, int columns, int[][] matrix) {
    int totalSeats = rows * columns;
    int bookedSeats = 0;
    int currentIncome = 0;
    int result = 0;

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            if (matrix[i][j] == 1) {
                bookedSeats++;
                currentIncome += calculateTicketPrice(matrix, rows, i + 1, columns, totalSeats);
            }
        }
    }

    double percentage = ((double) bookedSeats / totalSeats) * 100;

    // Calculate ticket price based on front and back rows
    int frontRows = rows / 2;
    int backRows = rows - frontRows;

    if (bookedSeats == 0) {
        // No purchased tickets, set result to STANDARD_TICKET_PRICE
        result = STANDARD_TICKET_PRICE;
    } else if (bookedSeats <= backRows * columns) {
        // Calculate based on back rows
        result = DISCOUNT_TICKET_PRICE;
    } else {
        // Calculate based on front rows
        result = STANDARD_TICKET_PRICE;
    }

    // Correct the total income calculation
    int totalIncome = frontRows * columns * STANDARD_TICKET_PRICE + backRows * columns * DISCOUNT_TICKET_PRICE;

    System.out.println("Number of purchased tickets: " + bookedSeats);
    System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
    System.out.println("Current income: $" + currentIncome);
    System.out.println("Total income: $" + totalIncome);
}


    private static int[] readRowsAndSeats() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        return new int[]{rows, seats};
    }

    private static void printMenu() {
        System.out.println("1. Show the seats:");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static int[] readSeatCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        return new int[]{rowNumber, seatNumber};
    }

    private static void bookAndCalculatePrice(int[][] matrix, int rows, int columns, int rowNumber, int seatNumber) {
        Scanner scanner = new Scanner(System.in);

       while(true) {
           if (rowNumber <= rows && seatNumber <= columns) {
               if (matrix[rowNumber - 1][seatNumber - 1] == 0) {
                   matrix[rowNumber - 1][seatNumber - 1] = 1;

                   int totalSeats = rows * columns;
                   int ticketPrice = calculateTicketPrice(matrix, rows, rowNumber, columns, totalSeats);

                   System.out.println();
                   System.out.println("Ticket price: $" + ticketPrice);
                   break;
               } else {
                   System.out.println();
                   System.out.println("That ticket has already been purchased!");

                   System.out.println();
                   System.out.println("Enter a row number:");
                   rowNumber = scanner.nextInt();
                   System.out.println("Enter a seat number in that row:");
                   seatNumber = scanner.nextInt();

               }
           } else {
               System.out.println();
               System.out.println("Wrong input!");

               System.out.println();
               System.out.println("Enter a row number:");
               rowNumber = scanner.nextInt();
               System.out.println("Enter a seat number in that row:");
               seatNumber = scanner.nextInt();

           }
       }
    }

    private static void printCinemaLayout(int[][] matrix, int rows, int columns) {
        int count = 0;
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= columns; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j);
                    if (j != columns) {
                        System.out.print(" ");
                    }
                } else if (j == 0) {
                    System.out.print(i);
                    System.out.print(" ");
                } else {
                    System.out.print(matrix[i - 1][j - 1] == 0 ? "S" : "B");
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private static int calculateTicketPrice(int[][] matrix, int rows, int rowNumber, int columns, int totalSeats) {
        int bookedSeats = 0;
        int boughtSeats = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 1) {
                    bookedSeats++;
                }
            }
        }

        if (totalSeats <= TOTAL_SEATS) {
            return STANDARD_TICKET_PRICE;
        } else if (rowNumber <= rows / 2) {
            return STANDARD_TICKET_PRICE;
        } else {
            return DISCOUNT_TICKET_PRICE;
        }
    }
}
