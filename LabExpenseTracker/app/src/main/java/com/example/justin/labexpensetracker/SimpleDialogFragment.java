package com.example.justin.labexpensetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Angelo Amadora on 1/19/2016.
 */
public class SimpleDialogFragment extends DialogFragment
{
    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_input, null);

        AlertDialog.Builder dialogBuilder
                = new AlertDialog.Builder(getActivity())
                .setTitle("Add New Expense")
                /*.setMessage("Are you sure you want to do this?")*/
                .setView(v)
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();
                    }
                })
                /*.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();
                    }
                })*/
                .setPositiveButton("Submit", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dismiss();

                        EditText etItem = (EditText) v.findViewById(R.id.et_item);
                        EditText etPrice = (EditText) v.findViewById(R.id.et_price);

                        Expense expense = new Expense(etItem.getText().toString(), Double.parseDouble(etPrice.getText().toString()));

                        ((MainActivity) getActivity()).addExpense(expense);
                    }
                });

        return dialogBuilder.create();
    }
}
