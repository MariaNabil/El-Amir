package com.example.cmc.retrofittrial;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "new_employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    /*public Employee(String job_Description, int working_Hours, String name) {
        Job_Description = job_Description;
        Working_Hours = working_Hours;
        Name = name;
        //is_added=true;
    }*/
    private String Name;

    private String Job_Description;

    private int Working_Hours;

    private boolean is_added;
    private boolean is_updated;
    private int ServerID;

    public boolean isIs_updated() {
        return is_updated;
    }

    public void setIs_updated(boolean is_updated) {
        this.is_updated = is_updated;
    }

    public int getServerID() {
        return ServerID;
    }

    public void setServerID(int serverID) {
        ServerID = serverID;
    }

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

    public boolean isIs_added() {
        return is_added;
    }

    public void setIs_added(boolean is_added) {
        this.is_added = is_added;
    }
}
