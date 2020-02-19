package com.example.finalassignmentphonebook;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneBook implements Parcelable {

    int id;
    String fname;
    String lname;
    String address;
    String PhoneNumber;

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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public static Creator<PhoneBook> getCREATOR() {
        return CREATOR;
    }

    public PhoneBook(int id, String fname, String lname, String address, String phoneNumber) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.PhoneNumber = phoneNumber;
    }

    protected PhoneBook(Parcel in) {
        id = in.readInt();
        fname = in.readString();
        lname = in.readString();
        address = in.readString();
        PhoneNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(address);
        dest.writeString(PhoneNumber);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
