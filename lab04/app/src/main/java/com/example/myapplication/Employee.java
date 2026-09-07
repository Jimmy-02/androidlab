package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private String name;
    private String dob;
    private String gender;
    private String hobby;
    private String englishLevel;

    public Employee(String name, String dob, String gender, String hobby, String englishLevel) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.hobby = hobby;
        this.englishLevel = englishLevel;
    }

    protected Employee(Parcel in) {
        name = in.readString();
        dob = in.readString();
        gender = in.readString();
        hobby = in.readString();
        englishLevel = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getName() { return name; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getHobby() { return hobby; }
    public String getEnglishLevel() { return englishLevel; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(hobby);
        dest.writeString(englishLevel);
    }
}