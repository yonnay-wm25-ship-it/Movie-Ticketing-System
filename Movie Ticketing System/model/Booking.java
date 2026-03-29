package model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String bookingID;
    private Movie movie;
    private List<Ticket> tickets;
    private List<ConcessionItem> snacks;
    private double totalAmount;

    public Booking(String bookingID, Movie movie) {
        this.bookingID = bookingID;
        this.movie = movie;
        this.tickets = new ArrayList<>();
        this.snacks = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        totalAmount += ticket.calculateFinalPrice();
    }//put a ticket and also update the total amount

    public void addConcession(ConcessionItem item) {
        this.snacks.add(item); 
        this.totalAmount += item.calculateSubtotal();
    }//same as above but change to snacks

    public void printReceipt() {
        System.out.println("\n===============================");
        System.out.println("       CINEMA RECEIPT          ");
        System.out.println("===============================");
        System.out.println("Booking ID: " + bookingID);
        System.out.println("Movie     : " + movie.getTitle());
        
        System.out.println("\n--- Tickets ---");
        for (Ticket t : tickets) {
            System.out.println("Seat: " + t.getSeatNumber() + " | RM " + t.calculateFinalPrice());
        } //print ticket foe every seat and its price

        if (!snacks.isEmpty()) {
            System.out.println("\n--- Concessions ---");
            for (ConcessionItem s : snacks) {
                System.out.println(s.toString());
            }
        }

        System.out.println("-------------------------------");
        System.out.println("TOTAL AMOUNT: RM " + String.format("%.2f", totalAmount));
        System.out.println("===============================");
    }

    
    public String getBookingID() { return bookingID; }

    public Movie getMovie() { return movie; }

    public List<Ticket> getTickets() { return tickets; }

    public double getTotalAmount() { return totalAmount; }

    // can change no of bookig id
    public void setBookingID(String bookingID) { this.bookingID = bookingID; }
}
