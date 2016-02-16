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
 * Created by Justin on 2/16/2016.
 */
public class SimpleDialogFragment2 extends DialogFragment
{
    View v;
    EditText etItem, etPrice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_edit, null);

        etItem = (EditText) v.findViewById(R.id.et_edititem);
        etPrice = (EditText) v.findViewById(R.id.et_editprice);

        etItem.setText(getArguments().getString("Item"));
        etPrice.setText(getArguments().getString("Price"));

        AlertDialog.Builder dialogBuilder
                = new AlertDialog.Builder(getActivity())
                .setTitle("Edit Expense")
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

                        Expense expense = new Expense(etItem.getText().toString(), Double.parseDouble(etPrice.getText().toString()));

                        ((MainActivity) getActivity()).editExpense(expense);
                    }
                });

        return dialogBuilder.create();
    }
}
