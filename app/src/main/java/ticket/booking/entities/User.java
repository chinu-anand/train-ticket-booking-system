package ticket.booking.entities;

import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private String userId;
    private List<Ticket> bookedTickets;

    public User(String userId, String name, String password, String hashedPassword, List<Ticket> bookedTickets){
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.bookedTickets = bookedTickets;
    }

    public User() {}

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getUserId(){
        return userId;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void printTickets(){
        for(Ticket t: bookedTickets){
            System.out.println(t.getTicketInfo());
        }
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}
