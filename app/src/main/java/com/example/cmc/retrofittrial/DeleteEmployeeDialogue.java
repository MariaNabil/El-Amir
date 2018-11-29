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

public class DeleteEmployeeDialogue extends AppCompatDialogFragment {

    private EditText editTextDeleteID;
    private DeleteEmployeeDialogue.DeleteEmployeeDialogueListener listener;

    public DeleteEmployeeDialogue() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_delete_dialogue,null);

        editTextDeleteID=(EditText) view.findViewById(R.id.edit_deleted_id);
        builder.setView(view)
                .setTitle("Delete Employee")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int IdToDelete=Integer.parseInt(editTextDeleteID.getText().toString());

                listener.DeleteEmployee(IdToDelete);
            }
        });

        return builder.create();
    }

    public interface DeleteEmployeeDialogueListener{
        void DeleteEmployee(int IdToDelete);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(DeleteEmployeeDialogue.DeleteEmployeeDialogueListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement DeleteEmployeeDialogueListener");
        }
    }

}
