package mobapde.edu.examwallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by courtneyngo on 1/31/16.
 */
public class AddExpenseDialogFragment extends DialogFragment{

    private OnExpenseSubmitListener mOnExpenseSubmitListener;
    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.add_expense_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Add New Expense")
                .setView(v)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etExpenseName = (EditText) v.findViewById(R.id.et_expense_name);
                        EditText etPrice = (EditText) v.findViewById(R.id.et_price);

                        Expense expense = new Expense(
                                etExpenseName.getText().toString(),
                                Double.parseDouble(etPrice.getText().toString()),
                                Calendar.getInstance().getTime());
                        mOnExpenseSubmitListener.onExpenseSubmitted(expense);
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    public interface OnExpenseSubmitListener{
        public void onExpenseSubmitted(Expense e);
    }

    public void setOnExpenseSubmitListener(OnExpenseSubmitListener e){
        this.mOnExpenseSubmitListener = e;
    }
}
