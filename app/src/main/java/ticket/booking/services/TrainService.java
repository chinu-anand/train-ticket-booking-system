package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainService {
    private final String TRAINS_PATH = "/Users/chibi/Documents/IRCTC/app/src/main/java/ticket/booking/localDb/trains.json";
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE);
    private Map<String, List<Train>> trainFromStationsList;
    private final List<Train> trainsList;


    public TrainService() throws IOException {
        File trains = new File(TRAINS_PATH);
        trainsList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }


    public List<Train> searchTrains(String fromStation, String toStation){
        return trainsList.stream().filter(trains -> isValidTrain(trains,fromStation, toStation)).collect(Collectors.toList());
    }

    private boolean isValidTrain(Train train, String source, String destination) {
        List<String> stations = train.getStations();

        int sourceIndex = stations.indexOf(source.toLowerCase());
        int destinationIndex = stations.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

    public int isSeatAvailable(Train train) {
        int seat;
        for(List<Integer> row: train.getSeats()){
            if(row.contains(0)){
                seat = row.indexOf(0);
                row.set(seat, 1);
                return seat;
            }
        }

        return -1;
    }
}
