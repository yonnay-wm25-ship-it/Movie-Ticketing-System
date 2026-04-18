package main;

import service.BookingService;
import service.ConcessionService;
import util.InputValidator;

import java.util.Scanner;

public class StaffMenuHandler {
    private final Scanner scanner;
    private final BookingService bookingService;
    private final ConcessionService concessionService;

    public StaffMenuHandler(Scanner scanner, BookingService bookingService, ConcessionService concessionService) {
        this.scanner = scanner;
        this.bookingService = bookingService;
        this.concessionService = concessionService;
    }

    public void run() {
        boolean keepStaffMenuOpen = true;
        do {
            System.out.println(this);
            int choice = readInt("Select: ");
            try {
                switch (choice) {
                    case 1:
                        String orderId = readText("Enter order ID: ");
                        boolean valid = bookingService.validateTicket(orderId);
                        System.out.println(valid ? "Ticket VALID." : "Ticket INVALID.");
                        break;
                   case 2:
                        concessionService.displayConcessions();

                        if (concessionService.readAllItems().length == 0) {
                            System.out.println("No concession items available.");
                            break;
                         }

                        int index = chooseIndex("Select concession index: ", concessionService.readAllItems().length);
                        if (index == -1) break;

                        int stockToAdd = readInt("Stock to add: ");

                        if (stockToAdd <= 0) {
                        System.out.println("Stock must be positive.");
                            break;
                        }

                        concessionService.addStock(index, stockToAdd);
                            System.out.println("Stock added.");
                            break;
                    case 0:
                        System.out.println("Logging out...");
                        keepStaffMenuOpen = false;
                        break;
                    default:
                        System.out.println("Invalid menu option.");
                }
            } catch (Exception e) {
                System.out.println("Staff flow error: " + e.getMessage());
            }
        } while (keepStaffMenuOpen);
    }

    public String toString() {
        return "\n--- STAFF DASHBOARD ---\n"
            + "1. Validate Ticket\n"
            + "2. Update Concession Stock\n"
            + "0. Logout";
    }

    private int readInt(String prompt) {
        boolean validIntegerReceived = false;
        int parsedValue = 0;
        do {
            try {
                System.out.print(prompt);
                parsedValue = InputValidator.parseIntInRange(scanner.nextLine(), Integer.MIN_VALUE, Integer.MAX_VALUE);
                validIntegerReceived = true;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        } while (!validIntegerReceived);
        return parsedValue;
    }

    private String readText(String prompt) {
        boolean validTextReceived = false;
        String parsedText = "";
        do {
            try {
                System.out.print(prompt);
                parsedText = InputValidator.requireText(scanner.nextLine(), "Input");
                validTextReceived = true;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        } while (!validTextReceived);
        return parsedText;
    }

    private int chooseIndex(String prompt, int length) {
    if (length <= 0) {
        System.out.println("No records available.");
        return -1;
    }

    while (true) {
        int selected = readInt(prompt) - 1;

        if (selected >= 0 && selected < length) {
            return selected;
        }

        System.out.println("Index out of range. Try again.");
    }
}
}
