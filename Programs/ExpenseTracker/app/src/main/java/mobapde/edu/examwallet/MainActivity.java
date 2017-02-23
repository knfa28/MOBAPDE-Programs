package mobapde.edu.examwallet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView expenseRecyclerView;
    TextView tvExpenseName, tvDate, tvPrice, tvAdd, tvEdit, tvDelete;
    ExpenseAdapter mExpenseAdapter;
    Expense mCurrentExpense;

    final static String KEY_EXPENSE = "expense";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvExpenseName = (TextView) findViewById(R.id.tv_expense_name);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        expenseRecyclerView = (RecyclerView) findViewById(R.id.expense_list);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvEdit = (TextView) findViewById(R.id.tv_edit);
        tvDelete = (TextView) findViewById(R.id.tv_delete);

        ArrayList<Expense> expenseArrayList = new ArrayList<Expense>();
        expenseArrayList.add(new Expense("Spaghetti", 90.00, Calendar.getInstance().getTime()));

        mExpenseAdapter = new ExpenseAdapter(expenseArrayList);
        expenseRecyclerView.setAdapter(mExpenseAdapter);
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        mExpenseAdapter.setOnItemClickListener(new ExpenseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ExpenseAdapter.ExpenseViewHolder expenseViewHolder, int position) {
                mCurrentExpense = mExpenseAdapter.getItemAtPosition(position);
                mCurrentExpense.setPosition(position);

                tvEdit.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.VISIBLE);

                bindCurrentExpensetoView();
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenseDialogFragment addExpenseDialogFragment = new AddExpenseDialogFragment();
                addExpenseDialogFragment.setOnExpenseSubmitListener(new AddExpenseDialogFragment.OnExpenseSubmitListener() {
                    @Override
                    public void onExpenseSubmitted(Expense e) {
                        mExpenseAdapter.addExpense(e);
                    }
                });
                addExpenseDialogFragment.show(getFragmentManager(), "");
            }
        });

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditExpenseDialogFragment editExpenseDialogFragment = new EditExpenseDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_EXPENSE, mCurrentExpense);
                editExpenseDialogFragment.setArguments(bundle);
                editExpenseDialogFragment.setOnExpenseEditListener(new EditExpenseDialogFragment.OnExpenseEditListener() {
                    @Override
                    public void onExpenseEdit(Expense e) {
                        e.setPosition(mCurrentExpense.getPosition());
                        mExpenseAdapter.editExpense(e);
                        mCurrentExpense = e;
                        bindCurrentExpensetoView();
                    }
                });
                editExpenseDialogFragment.show(getFragmentManager(), "");
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpenseAdapter.deleteExpense(mCurrentExpense.getPosition());
                bindEmptyDataToView();
            }
        });
    }

    public String convertDateToString(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(d);
    }

    public String convertPriceToString(double price){
        DecimalFormat df = new DecimalFormat("#.00");
        return "Php " + df.format(price);
    }

    public void bindCurrentExpensetoView(){
        tvExpenseName.setText(mCurrentExpense.getExpenseName());
        tvPrice.setText(convertPriceToString(mCurrentExpense.getPrice()));
        tvDate.setText(convertDateToString(mCurrentExpense.getDate()));
    }

    public void bindEmptyDataToView(){
        tvExpenseName.setText("");
        tvPrice.setText("Click on an item to get started");
        tvDate.setText("");
        tvDelete.setVisibility(View.INVISIBLE);
        tvEdit.setVisibility(View.INVISIBLE);
    }
}