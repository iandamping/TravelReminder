package com.example.jun.travelreminder.Databasenya.ROOM.model_entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.jun.travelreminder.Databasenya.ROOM.date_converter;

import java.util.Date;

/**
 * Created by Jun on 22/04/2018.
 */
@Entity(tableName = "user_belonging")
@TypeConverters(date_converter.class)
public class item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_barang")
    private int IDnya;
    private String item;
    private String destination;
    private Date DepartureDate;
    private Date Arrivaldate;
    private int selectedHour;
    private int selectedMin;

    public item(int IDnya, String item, String destination, Date DepartureDate, Date Arrivaldate, int selectedHour, int selectedMin) {
        this.IDnya = IDnya;
        this.item = item;
        this.destination = destination;
        this.DepartureDate = DepartureDate;
        this.Arrivaldate = Arrivaldate;
        this.selectedHour = selectedHour;
        this.selectedMin = selectedMin;
    }

    @Ignore
    public item(String item, String destination, Date DepartureDate, Date Arrivaldate, int selectedHour, int selectedMin) {
        this.item = item;
        this.destination = destination;
        this.DepartureDate = DepartureDate;
        this.Arrivaldate = Arrivaldate;
        this.selectedHour = selectedHour;
        this.selectedMin = selectedMin;

    }

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(Date departureDate) {
        DepartureDate = departureDate;
    }

    public Date getArrivaldate() {
        return Arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        Arrivaldate = arrivaldate;
    }

    public int getSelectedHour() {
        return selectedHour;
    }

    public void setSelectedHour(int selectedHour) {
        this.selectedHour = selectedHour;
    }

    public int getSelectedMin() {
        return selectedMin;
    }

    public void setSelectedMin(int selectedMin) {
        this.selectedMin = selectedMin;
    }

    public int getIDnya() {
        return IDnya;
    }

    public void setIDnya(int IDnya) {
        this.IDnya = IDnya;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
