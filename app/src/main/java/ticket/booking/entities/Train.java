package ticket.booking.entities;

import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNo;
    private String name;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;
    private List<String> stations;
    private String trainInfo;

    public Train(String trainId, String trainNo, String trainInfo, String name, List<List<Integer>> seats, Map<String, String> stationTimes, List<String> stations){
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.name = name;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
        this.trainInfo = trainInfo;
    }

    public Train() {}

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getName() {
        return name;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public List<String> getStations() {
        return stations;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public String getTrainInfo(){
        return String.format("Train No: %s Train Name: %s", trainNo, name);
    }
}

