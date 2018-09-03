package com.example.jun.travelreminder.Databasenya.ROOM.model_entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Jun on 22/04/2018.
 */
@Entity(tableName = "user_belonging")
public class item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_barang")
    private int IDnya;
    private String item;
    private String destination;

    public item(int IDnya, String item, String destination) {
        this.IDnya = IDnya;
        this.item = item;
        this.destination = destination;
    }

    @Ignore
    public item(String item, String destination) {
        this.item = item;
        this.destination = destination;

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
