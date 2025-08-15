package com.example.demo.Service;

import com.example.demo.Model.Flight;
import com.example.demo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {

    FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> retriveFlights(String origin, String destination, String date)  {
        Timestamp timestamp = null;
        try {
            timestamp = convertDateToTimestamp(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


        return flightRepository.findFlightByOrginDestinationDepartureTime(
                origin,
                destination,
                timestamp
        );
    }

    private Timestamp convertDateToTimestamp(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(date + " 00:00:00");
        return new Timestamp(d.getTime());
    }


}
