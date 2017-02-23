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
public class EditExpenseDialogFragment extends DialogFragment{

    private OnExpenseEditListener mOnExpenseEditListener;
    View v;
    Expense mCurrentExpense;
    EditText mEtExpenseName, mEtPrice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mCurrentExpense = (Expense) getArguments().get(MainActivity.KEY_EXPENSE);

        v = LayoutInflater.from(getActivity()).inflate(R.layout.add_expense_dialog, null);
        mEtExpenseName = (EditText) v.findViewById(R.id.et_expense_name);
        mEtPrice = (EditText) v.findViewById(R.id.et_price);
        mEtExpenseName.setText(mCurrentExpense.getExpenseName());
        mEtPrice.setText(String.valueOf(mCurrentExpense.getPrice()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Edit Expense")
                .setView(v)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Expense expense = new Expense(
                                mEtExpenseName.getText().toString(),
                                Double.parseDouble(mEtPrice.getText().toString()),
                                Calendar.getInstance().getTime());
                        mOnExpenseEditListener.onExpenseEdit(expense);
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

    public interface OnExpenseEditListener{
        public void onExpenseEdit(Expense e);
    }

    public void setOnExpenseEditListener(OnExpenseEditListener e){
        this.mOnExpenseEditListener = e;
    }
}
