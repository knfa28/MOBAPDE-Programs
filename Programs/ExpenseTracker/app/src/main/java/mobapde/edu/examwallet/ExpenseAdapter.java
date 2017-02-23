package mobapde.edu.examwallet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by courtneyngo on 1/31/16.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private ArrayList<Expense> mExpenseArrayList;
    private OnItemClickListener mOnItemClickListener;

    public ExpenseAdapter(ArrayList<Expense> expenseArrayList){
        this.mExpenseArrayList = expenseArrayList;
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{

        TextView mTvExpenseName;
        TextView mTvPrice;
        View mContainer;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            mTvExpenseName = (TextView) itemView.findViewById(R.id.tv_expense_name);
            mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            mContainer = itemView.findViewById(R.id.container);
        }
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list_item, null);
        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ExpenseViewHolder holder, int position) {
        Expense expense = mExpenseArrayList.get(position);
        holder.mTvExpenseName.setText(expense.getExpenseName());
        holder.mTvPrice.setText(convertPriceToString(expense.getPrice()));
        holder.mContainer.setTag(holder);

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick((ExpenseViewHolder) v.getTag(), ((ExpenseViewHolder) v.getTag()).getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExpenseArrayList.size();
    }


    public String convertPriceToString(double price){
        DecimalFormat df = new DecimalFormat("#.00");
        return "Php " + df.format(price);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public Expense getItemAtPosition(int position){
        return mExpenseArrayList.get(position);
    }

    public interface OnItemClickListener{
        public void onItemClick(ExpenseViewHolder expenseViewHolder, int position);
    }

    public void addExpense(Expense e){
        mExpenseArrayList.add(e);
        notifyItemInserted(getItemCount()-1);
    }

    public void editExpense(Expense e){
        mExpenseArrayList.set(e.getPosition(), e);
        notifyItemChanged(e.getPosition());
    }

    public void deleteExpense(int position){
        mExpenseArrayList.remove(position);
        notifyItemRemoved(position);
    }
}
