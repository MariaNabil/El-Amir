package com.example.cmc.retrofittrial;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "employees")
public class User {
    @PrimaryKey
    private int ID;

    private String Job_Description;

    private int Working_Hours;

    private String Name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getJob_Description() {
        return Job_Description;
    }

    public void setJob_Description(String job_Description) {
        Job_Description = job_Description;
    }

    public int getWorking_Hours() {
        return Working_Hours;
    }

    public void setWorking_Hours(int working_Hours) {
        Working_Hours = working_Hours;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
