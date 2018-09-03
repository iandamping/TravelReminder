package com.example.jun.travelreminder.Databasenya.ROOM.model_entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.jun.travelreminder.Databasenya.ROOM.date_converter;

import java.util.Date;

@Entity(tableName = "date_users")
@TypeConverters(date_converter.class)
public class DateUsers {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_date")
    private int ID;
    private Date DepartureDate;
    private Date Arrivaldate;

    public DateUsers(int ID, Date DepartureDate, Date Arrivaldate) {
        this.ID = ID;
        this.DepartureDate = DepartureDate;
        this.Arrivaldate = Arrivaldate;
    }

    @Ignore
    public DateUsers(Date DepartureDate, Date Arrivaldate) {
        this.DepartureDate = DepartureDate;
        this.Arrivaldate = Arrivaldate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
