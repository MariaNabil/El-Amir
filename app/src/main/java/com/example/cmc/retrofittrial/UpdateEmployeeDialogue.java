package com.example.cmc.retrofittrial;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateEmployeeDialogue extends AppCompatDialogFragment {

    private EditText editTextUpdateID,editTextName,editTextJobDescription,editTextWorkingHours;
    private UpdateEmployeeDialogue.UpdateEmployeeDialogueListener listener;

    public UpdateEmployeeDialogue() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.fragment_blank,null);

        editTextUpdateID=(EditText) view.findViewById(R.id.edit_updated_id);
        editTextName=(EditText)view.findViewById(R.id.edit_name_update) ;
        editTextJobDescription=(EditText)view.findViewById(R.id.edit_job_description_update) ;
        editTextWorkingHours=(EditText)view.findViewById(R.id.edit_working_hours_update) ;
        builder.setView(view)
                .setTitle("Update Employee")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int IdToUpdate=Integer.parseInt(editTextUpdateID.getText().toString());
                String Name=editTextName.getText().toString();
                String jobDescription=editTextJobDescription.getText().toString();
                int workingHours=Integer.parseInt(editTextWorkingHours.getText().toString());
                Employee employee=new Employee();
                employee.setName(Name);
                employee.setJob_Description(jobDescription);
                employee.setWorking_Hours(workingHours);
                listener.UpdateEmployee(IdToUpdate,employee);
            }
        });

        return builder.create();
    }

    public interface UpdateEmployeeDialogueListener{
        void UpdateEmployee(int IdToUpdate
                ,Employee employee);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(UpdateEmployeeDialogue.UpdateEmployeeDialogueListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement UpdateEmployeeDialogueListener");
        }
    }

}
