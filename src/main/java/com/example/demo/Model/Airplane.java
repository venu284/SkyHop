package com.example.demo.Model;
import jakarta.persistence.*;


@Entity
//@Data
@Table(name="Airplane")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airplaneID;

    @Column(nullable = false)
    private String airline;

    @Column(nullable = false)
    private String planeModel;

    @Column(nullable = false)
    private Integer capacity;

    public Long getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(Long airplaneID) {
        this.airplaneID = airplaneID;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
