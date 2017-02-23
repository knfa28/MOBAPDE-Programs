package com.example.kurt.s20_kurtaquino;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kurt on 2/16/2016.
 */
public class ExpenseSkeletonAdapter extends RecyclerView.Adapter<ExpenseSkeletonAdapter.ExpenseViewHolder> {

    private ArrayList<Expense> mExpenseArrayList;
    private String displayName;
    private String displayPrice;
    private String displayDate;

    public ExpenseSkeletonAdapter(ArrayList<Expense> expenseArrayList){
        this.mExpenseArrayList = expenseArrayList;
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView priceText;
        TextView nameText;
        View container;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.expenseName);
            priceText = (TextView) itemView.findViewById(R.id.expensePrice);
        }
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, null);

        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ExpenseViewHolder holder, int position) {

        // TODO

        final Expense expense = mExpenseArrayList.get(position);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setDetails(expense.getName(), expense.getPrice(), expense.getDate());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExpenseArrayList.size();
    }

    public Expense getItemAtPosition(int position){
        return mExpenseArrayList.get(position);
    }

    public interface OnItemClickListener{
        public void onItemClick(int positionOfItemClicked);
    }

    public void addExpense(Expense e){
        mExpenseArrayList.add(e);
        notifyItemInserted(getItemCount()-1);
    }

    public void editExpense(Expense e){
        // TODO
    }

    public void deleteExpense(String name){
        mExpenseArrayList.remove(name);
        notifyDataSetChanged();
    }
}
