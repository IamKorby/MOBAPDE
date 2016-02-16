package com.example.justin.labexpensetracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Justin on 2/16/2016.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>
{
    private ArrayList<Expense> mExpenseArrayList;
    private OnItemClickListener mOnItemClickListener;

    public ExpenseAdapter(ArrayList<Expense> expenseArrayList)
    {
        this.mExpenseArrayList = expenseArrayList;
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvItem, tvPrice;
        View container;

        public ExpenseViewHolder(View itemView)
        {
            super(itemView);
            // TODO

            tvItem = (TextView) itemView.findViewById(R.id.tv_listItem);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_listPrice);
            container = itemView.findViewById(R.id.container);
        }
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // TODO
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, null);

        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ExpenseViewHolder holder, int position)
    {
        // TODO

        Expense expense = mExpenseArrayList.get(position);

        holder.tvItem.setText(expense.getItem());
        holder.tvPrice.setText(convertPriceToString(expense.getPrice()));

        holder.container.setTag(holder);

        holder.container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mOnItemClickListener.onItemClick(((ExpenseViewHolder) v.getTag()).getAdapterPosition());
                //Toast.makeText(v.getContext(), "ON CLICK THING", Toast.LENGTH_LONG).show();
            }
        });

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
    public int getItemCount()
    {
        return mExpenseArrayList.size();
    }

    public String convertPriceToString(double price)
    {
        // This will make your expense's price ready for display
        DecimalFormat df = new DecimalFormat("#.00");
        return "Php " + df.format(price);
    }

    public String convertDateToString(Date d)
    {
        // This will make your expense's date ready for display
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(d);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }

    public Expense getItemAtPosition(int position)
    {
        return mExpenseArrayList.get(position);
    }

    public interface OnItemClickListener
    {
        public void onItemClick(int positionOfItemClicked);
    }

    public void addExpense(Expense e)
    {
        // TODO
        mExpenseArrayList.add(e);
        notifyItemInserted(getItemCount() - 1);
    }

    public void editExpense(Expense e, int currentItem)
    {
        // TODO
        mExpenseArrayList.set(currentItem, e);
        notifyItemChanged(currentItem);
    }

    public void deleteExpense(int position)
    {
        mExpenseArrayList.remove(position);
        notifyItemRemoved(position);
    }
}
