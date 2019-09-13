package com.example.charmimehta.parkingsystem.modal;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Ticket")
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "carPlateNo")
    private String carPlateNo;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "modal")
    private String modal;

    @ColumnInfo(name = "zone")
    private String zone;

    @ColumnInfo(name = "lane")
    private String lane;

    @ColumnInfo(name = "cost")
    private String cost;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "carType")
    private String cardType;

    @Override
    public String toString() {
        return "Ticket{" +
                "carPlateNo='" + carPlateNo + '\'' +
                ", color='" + color + '\'' +
                ", modal='" + modal + '\'' +
                ", zone='" + zone + '\'' +
                ", lane='" + lane + '\'' +
                ", cost='" + cost + '\'' +
                ", time='" + time + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }

    public Ticket() {
    }

    public Ticket(String carPlateNo, String color, String modal, String zone, String lane, String cost, String time, String cardType) {
        this.carPlateNo = carPlateNo;
        this.color = color;
        this.modal = modal;
        this.zone = zone;
        this.lane = lane;
        this.cost = cost;
        this.time = time;
        this.cardType = cardType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarPlateNo() {
        return carPlateNo;
    }

    public void setCarPlateNo(String carPlateNo) {
        this.carPlateNo = carPlateNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
