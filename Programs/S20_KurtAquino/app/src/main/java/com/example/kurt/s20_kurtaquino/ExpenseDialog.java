package com.example.kurt.s20_kurtaquino;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.net.MalformedURLException;

/**
 * Created by Kurt on 2/16/2016.
 */
public class ExpenseDialog extends DialogFragment {
    View v;

    public Dialog onCreateDialog(Bundle savedInstanceState){

        v = LayoutInflater.from(getActivity())
                .inflate(R.layout.info_dialog, null);

        AlertDialog.Builder diagBuild = new AlertDialog.Builder(getActivity())
                .setTitle("Add New Expense")
                .setView(v)
                .setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText nameText = (EditText) v.findViewById(R.id.nameEdit);
                        EditText priceText = (EditText) v.findViewById(R.id.priceEdit);
                        ((MainActivity) getActivity()).OnSubmitSelected(nameText.getText().toString(), priceText.getText().toString());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return diagBuild.create();
    }

}
