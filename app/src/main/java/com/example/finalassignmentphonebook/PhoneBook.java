package com.example.finalassignmentphonebook;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneBook implements Parcelable {
    protected PhoneBook(Parcel in) {
        id = in.readInt();
        fname = in.readString();
        lname = in.readString();
        address = in.readString();
        PhoneNumber = in.readInt();
        currentDate = in.readString();
    }

    public static final Creator<PhoneBook> CREATOR = new Creator<PhoneBook>() {
        @Override
        public PhoneBook createFromParcel(Parcel in) {
            return new PhoneBook(in);
        }

        @Override
        public PhoneBook[] newArray(int size) {
            return new PhoneBook[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    int id;
    String fname;
    String lname;
    String address;
    int PhoneNumber;
    String currentDate;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public PhoneBook(int id, String fname, String lname, String address, int phoneNumber, String currentDate) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        PhoneNumber = phoneNumber;
        this.currentDate = currentDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(address);
        dest.writeInt(PhoneNumber);
        dest.writeString(currentDate);
    }
}
