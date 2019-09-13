package com.example.charmimehta.parkingsystem.modal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by macstudent on 2018-04-19.
 */


    @Entity(tableName = "User")
    public class User {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "username")
        private String username;

        @ColumnInfo(name = "email")
        private String email;

        @ColumnInfo(name = "password")
        private String password;

        @ColumnInfo(name = "contact")
        private String contact;

        @ColumnInfo(name = "carPlateNo")
        private String carPlateNo;



        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", contact='" + contact + '\'' +
                    ", carPlateNo='" + carPlateNo + '\'' +
                    '}';
        }

        public User() {
        }

        public User(int id, String username, String email, String password, String contact, String carPlateNo) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.contact = contact;
            this.carPlateNo = carPlateNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCarPlateNo() {
            return carPlateNo;
        }

        public void setCarPlateNo(String carPlateNo) {
            this.carPlateNo = carPlateNo;
        }
    }

