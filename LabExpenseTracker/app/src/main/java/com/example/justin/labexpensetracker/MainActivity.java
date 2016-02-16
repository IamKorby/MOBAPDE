package com.example.justin.labexpensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    TextView tvItem, tvPrice, tvDate;
    Button buttonAdd, buttonEdit, buttonDelete;

    ExpenseAdapter expenseAdapter;

    int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvItem = (TextView) findViewById(R.id.tv_item);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvDate = (TextView) findViewById(R.id.tv_date);

        buttonAdd = (Button) findViewById(R.id.button_add);

        tvPrice.setText("Click on an item to get started");

        View.OnClickListener openDialog = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SimpleDialogFragment df = new SimpleDialogFragment();
                df.show(getFragmentManager(), "");
            }
        };

        buttonAdd.setOnClickListener(openDialog);

        buttonEdit = (Button) findViewById(R.id.button_edit);
        buttonDelete = (Button) findViewById(R.id.button_delete);

        // Step 1: create recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // Step 2: create our data
        ArrayList<Expense> expenses = new ArrayList<>();

        // Step 3: create our adapter
        expenseAdapter = new ExpenseAdapter(expenses);

        // Step 4: attach adapter to UI
        recyclerView.setAdapter(expenseAdapter);

        // Step 5: create layout manager to UI
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        expenseAdapter.setOnItemClickListener(new ExpenseAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int positionOfItemClicked)
            {
                currentItem = positionOfItemClicked;
                Expense expense = expenseAdapter.getItemAtPosition(positionOfItemClicked);

                tvItem.setText(expense.getItem());
                tvPrice.setText(expenseAdapter.convertPriceToString(expense.getPrice()));
                tvDate.setText(expenseAdapter.convertDateToString(expense.getDate()));
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                expenseAdapter.deleteExpense(currentItem);
                tvItem.setText("");
                tvPrice.setText("");
                tvDate.setText("");
            }
        });

        View.OnClickListener openDialog2 = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SimpleDialogFragment2 df = new SimpleDialogFragment2();

                Expense expense = expenseAdapter.getItemAtPosition(currentItem);

                Bundle args = new Bundle();
                args.putString("Item", expense.getItem());
                args.putString("Price", Double.toString(expense.getPrice()));
                df.setArguments(args);

                df.show(getFragmentManager(), "");
            }
        };

        buttonEdit.setOnClickListener(openDialog2);
    }

    public void addExpense( Expense expense )
    {
        expenseAdapter.addExpense(expense);
    }

    public void editExpense( Expense expense )
    {
        expenseAdapter.editExpense(expense, currentItem);
        tvItem.setText(expense.getItem());
        tvPrice.setText(expenseAdapter.convertPriceToString(expense.getPrice()));
        tvDate.setText(expenseAdapter.convertDateToString(expense.getDate()));
    }

}
