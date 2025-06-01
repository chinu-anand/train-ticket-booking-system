package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserBookingService {
    private User user;
    List<User> userList;
    private static final String USERS_PATH = "/Users/chibi/Documents/IRCTC/app/src/main/java/ticket/booking/localDb/users.json";

    private final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE);

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUsers();
    }

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public User getUser() {
        return user;
    }

    private void loadUsers() throws IOException{
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public boolean loginUser(String username, String password){
        Optional<User> findUser = userList.stream().filter(user -> {
            return user.getName().equals(username) && UserServiceUtil.checkPassword(password, user.getHashedPassword());
        }).findFirst();

        if(findUser.isPresent()){
            this.user = findUser.get();
            return true;
        }

        return false;
    }

    public void signUp(User user){
        try{
            this.userList.add(user);
            System.out.println("Signed Up successfully");
            saveUserListToFile();
        } catch (IOException e) {
            System.out.println("Unable to Sign Up");
        }
    }

    private void saveUserListToFile() throws IOException{
        File users = new File(USERS_PATH);
        objectMapper.writeValue(users, userList);
    }

    public void fetchBookings(){
        user.printTickets();
    }

    public List<Ticket> getTickets() {
        return user.getBookedTickets();
    }

    public void bookTicket(Train train, String source, String destination) throws IOException {
        Ticket newTicket = new Ticket(
                UUID.randomUUID().toString(),
                user.getUserId(),
                source,
                destination,
                String.valueOf(new Date()),
                train
        );

        List<Ticket> ticketList = user.getBookedTickets();
        ticketList.add(newTicket);
        user.setBookedTickets(ticketList);
        try {
            saveUserListToFile();
        } catch (IOException e) {
            System.out.println("Error in book Ticket");
        }
    }

    public void cancelBooking(String ticketId) throws IOException {
        user.getBookedTickets().removeIf(ticket -> ticket.getTicketId().equals(ticketId));
        try{
            saveUserListToFile();
        } catch (IOException e) {
            System.out.println("Error :"+ e.getMessage());
        }
    }
}
