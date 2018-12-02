package com.example.cmc.retrofittrial;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AddEmployeeDialogue.AddEmployeeDialogueListener, DeleteEmployeeDialogue.DeleteEmployeeDialogueListener,UpdateEmployeeDialogue.UpdateEmployeeDialogueListener{
    private TextView textViewResult;
    private Button AddButton;
    private Button DeleteButton;
    private Button DisplayFromDBButton;
    private Button DisplayFromServerButton;
    private Button UpdateServerButton;
    private Button UpdateEmployeeButton;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    public static MyAppDatabase myAppDatabase;
    static Context context;

    //BlockingQueue<Employee> Added_employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         context=getApplicationContext();

        textViewResult = findViewById(R.id.text_view_result);
        AddButton=findViewById(R.id.add_button);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialogue();
            }
        });

        DeleteButton=findViewById(R.id.delete_button);
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialogue();
            }
        });


        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"new_employee_db").fallbackToDestructiveMigration().build();
        if(isNetworkConnected()) {
            showData();
        }
        else {
            DisplayEmployeesFromDatabase();
            Toast.makeText(getApplicationContext(),"You Are Working Offline",Toast.LENGTH_LONG).show();
            //textViewResult.setText("you are offline");
        }


        DisplayFromDBButton=findViewById(R.id.get_all_from_database_bn);
        DisplayFromDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayEmployeesFromDatabase();
                //Toast.makeText().show();


            }
        });

        DisplayFromServerButton=(Button)findViewById(R.id.get_all_from_server_bn);
        DisplayFromServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    showData();
                }
                else {
                    Toast.makeText(getApplicationContext(),"You Are Offline, \nPlease connect to the internet",Toast.LENGTH_LONG).show();

                }
            }
        });


        UpdateServerButton=(Button)findViewById(R.id.update_server_bn);
        UpdateServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateServer();
                showData();
            }
        });


        UpdateEmployeeButton=(Button)findViewById(R.id.update_employee_bn);
        UpdateEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateDialogue();
            }
        });
       /* wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled())
        {
            updateServer();
            Toast.makeText(getApplicationContext(),"Server Is Updated",Toast.LENGTH_LONG).show();

        }*/
    }

   /* private BroadcastReceiver wifiStateReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
              //      WifiManager.WIFI_STATE_UNKNOWN);

           /* switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLED:
                   updateServer();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(getApplicationContext(),"You Are Offline",Toast.LENGTH_LONG).show();
                    break;
            }

           if(isNetworkConnected())
           {
               updateServer();
               Toast.makeText(getApplicationContext(),"Server Is Updated",Toast.LENGTH_LONG).show();
           }
        }
    };
*/
   //CheckInternetBroadcast checkInternetBroadcast=new CheckInternetBroadcast();
   // NetworkChangeReceiver networkChangeReceiver=new NetworkChangeReceiver();
   // NetworkChangeReceiver networkChangeReceiver=new NetworkChangeReceiver();

    //---------------------------------------------------------------------------------------------------
    //FUNCTIONS ELLI BETNADY 3ALA JSONPLACEHOLDERAPI FUNCTIONS (ELLI BTET3AMEL MA3 EL SERVER THROUGH EL API ) -> show & insert & delete
    private void showData(){
        if(isNetworkConnected()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://createapitask.conveyor.cloud/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<List<Employee>> call = jsonPlaceHolderApi.getEmployees();

            call.enqueue(new Callback<List<Employee>>() {
                @Override
                public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code: " + response.code());
                        return;
                    }

                    List<Employee> employees = response.body();
                    textViewResult.setText("");
                    for (Employee employee : employees) {
                        String content = "";
                        content += "ID: " + employee.getID() + "\n";
                        content += "Job Description: " + employee.getJob_Description() + "\n";
                        content += "Working Hours: " + employee.getWorking_Hours() + "\n";
                        content += "Name: " + employee.getName() + "\n\n";

                        textViewResult.append(content);
                    }
                }


                @Override
                public void onFailure(Call<List<Employee>> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });
        }
        else {
            //Toast.makeText(getApplicationContext(),"You Are Offline, \nPlease connect to the internet",Toast.LENGTH_LONG).show();
        }
    }

    private void createEmployee(final Employee employee) {
         //int serverID;
       // final BlockingQueue<Employee> blockingQueue = new ArrayBlockingQueue<>(1);
        Call call = jsonPlaceHolderApi.createEmployee(employee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                  //int serverID = response.body().getID();
                //blockingQueue.add(response.body());
                //employee.setServerID(response.body().getID());
                //SaveToDatabase(employee);
                showData();



            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        //return blockingQueue;

    }

    private void deleteEmployee(int id ) {
        Call<Void> call = jsonPlaceHolderApi.deleteEmployee(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: " + response.code());
                showData();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    private void updateEmployee(int id,Employee employee ) {
        employee.setID(id);
        Call<Void> call = jsonPlaceHolderApi.updateEmployee(id,employee);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: " + response.code());
                showData();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    //-----------------------------------------------------------------------------------------------
    //FUNCTIONS OF DIALOGUES
    public void openAddDialogue(){
        AddEmployeeDialogue addEmployeeDialogue=new AddEmployeeDialogue();
        addEmployeeDialogue.show(getSupportFragmentManager(),"Add Employee Dialogue");
    }

    //from AddDialogueListener
    @Override
    public void AddEmployee(String Name, String jobDescription, int workingHours) {
        //HANE7TAG N3ADEL HNA
        //Employee employee = new Employee(jobDescription, workingHours, Name);
        //Employee employee = new Employee(jobDescription, workingHours, Name);
        Employee employee = new Employee();
        employee.setName(Name);
        employee.setJob_Description(jobDescription);
        employee.setWorking_Hours(workingHours);

        if(isNetworkConnected()){
            employee.setIs_added(true);
           // Employee Added_employee=createEmployee(employee);
            //Added_employee=createEmployee(employee);
            //int ddd= 7;
            createEmployee(employee);

        }

        //User user=new User();
       // user.setID();
        SaveToDatabase(employee);
        if(!isNetworkConnected()){
            DisplayEmployeesFromDatabase();
        }
    }

    public void openDeleteDialogue(){
        DeleteEmployeeDialogue deleteEmployeeDialogue=new DeleteEmployeeDialogue();
        deleteEmployeeDialogue.show(getSupportFragmentManager(),"Delete Employee Dialogue");
    }

    public void openUpdateDialogue(){
        UpdateEmployeeDialogue updateEmployeeDialogue=new UpdateEmployeeDialogue();
        updateEmployeeDialogue.show(getSupportFragmentManager(),"Update Employee Dialogue");
    }

    //from deleteDialogueListener
    @Override
    public void DeleteEmployee(int IdToDelete) {
        deleteEmployee(IdToDelete);
    }

    @Override
    public void UpdateEmployee(int IdToUpdate, final Employee employee) {
        //MainActivity.myAppDatabase.myDao().updateEmployee(employee);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                             MainActivity.myAppDatabase.myDao().updateEmployee(employee);
                                                        }
                                                    });
                updateEmployee(IdToUpdate,employee);
    }
    //-------------------------------------------------------------------------------------
    //WHEN OFFLINE
    private void DisplayEmployeesFromDatabase(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Employee> employees;
                employees=MainActivity.myAppDatabase.myDao().getEmployees();

               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String info = "";
                        for (Employee employee : employees){
                            info+="\n\n"+"id : "+employee.getID()+"\n"+"Name : "+employee.getName()+"\n"+"Job Description : "+employee.getJob_Description()+"\n"+"Working Hours : "+employee.getWorking_Hours()+"\n"+"Is Added : "+employee.isIs_added()+"\n"+"Server Id: "+employee.getServerID();
                        }
                        textViewResult.setText(info);

                    }
                });
            }
        });
    }

    private void SaveToDatabase(final Employee employee){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.myAppDatabase.myDao().addEmployees(employee);
            }
        });


    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    public static void dialog(boolean value){
        Toast.makeText(context,"here is the dialog , connection is : "+value,Toast.LENGTH_LONG);
    }

    public  void updateServer() {
        if (isNetworkConnected()) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    List<Employee> employees;
                    employees = MainActivity.myAppDatabase.myDao().getEmployees();
                    for (Employee employee : employees) {
                        if (!employee.isIs_added()) {
                            employee.setIs_added(true);
                            MainActivity.myAppDatabase.myDao().updateEmployee(employee);
                            createEmployee(employee);
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"You Are Offline, \nPlease connect to the internet",Toast.LENGTH_LONG).show();
        }
    }



}

