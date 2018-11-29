package com.example.cmc.retrofittrial;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addEmployees(Employee employee);

    @Query("select * from new_employees")
    public List<Employee> getEmployees();

    @Update
    public void updateEmployee(Employee employee);
}

