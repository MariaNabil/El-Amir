package com.example.cmc.retrofittrial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
public class AddEmployeeDialogue extends AppCompatDialogFragment {

    private EditText editTextName;
    private EditText editTextJobDescription;
    private EditText editTextWorkingHours;
    private AddEmployeeDialogueListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialogue,null);

        editTextName=(EditText) view.findViewById(R.id.edit_name);
        editTextJobDescription=(EditText) view.findViewById(R.id.edit_job_description);
        editTextWorkingHours=(EditText) view.findViewById(R.id.edit_working_hours);
        builder.setView(view)
                .setTitle("New Employee Data")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Name=editTextName.getText().toString();
                String jobDescription=editTextJobDescription.getText().toString();
                int workingHours=Integer.parseInt(editTextWorkingHours.getText().toString());

                listener.AddEmployee(Name,jobDescription,workingHours);
            }
        });

        return builder.create();
    }
    public interface AddEmployeeDialogueListener{
        void AddEmployee(String Name , String jobDescription,int workingHours);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(AddEmployeeDialogueListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement AddEmployeeDialogueListener");
        }
    }

   /* private void SaveToDatabase(final User user){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                MainActivity.myAppDatabase.myDao().addUser(user);
            }
        });


    }*/
}
