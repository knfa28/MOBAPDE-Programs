package mobapde.edu.examwallet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by courtneyngo on 1/31/16.
 */
public class ExpenseSkeletonAdapter extends RecyclerView.Adapter<ExpenseSkeletonAdapter.ExpenseViewHolder> {

    private ArrayList<Expense> mExpenseArrayList;
    private OnItemClickListener mOnItemClickListener;

    public ExpenseSkeletonAdapter(ArrayList<Expense> expenseArrayList){
        this.mExpenseArrayList = expenseArrayList;
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
        public ExpenseViewHolder(View itemView) {
            super(itemView);
            // TODO
        }
    }


    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO
        return null;
    }

    @Override
    public void onBindViewHolder(final ExpenseViewHolder holder, int position) {

        // TODO

        Expense expense = mExpenseArrayList.get(position);

        /*
            Use the holder's view to set a tag.
            For example, the holder has a view called "container"
            You can call:
                holder.container.setTag(holder)

                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(((ExpenseViewHolder) v.getTag()).getAdapterPosition());
                    }
                });

        */
    }

    @Override
    public int getItemCount() {
        return mExpenseArrayList.size();
    }

    public String convertPriceToString(double price){
        // This will make your expense's price ready for display
        DecimalFormat df = new DecimalFormat("#.00");
        return "Php " + df.format(price);
    }

    public String convertDateToString(Date d){
        // This will make your expense's date ready for display
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(d);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public Expense getItemAtPosition(int position){
        return mExpenseArrayList.get(position);
    }

    public interface OnItemClickListener{
        public void onItemClick(int positionOfItemClicked);
    }

    public void addExpense(Expense e){
        // TODO
    }

    public void editExpense(Expense e){
        // TODO
    }

    public void deleteExpense(int position){
        // TODO
    }
}
